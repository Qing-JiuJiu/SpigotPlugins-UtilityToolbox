package com.yishian.command.sethome;


import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

/**
 * @author XinQi
 */
public class SetHomeCommand implements CommandExecutor {

    String setHomeCommand = SetHomeEnum.SET_HOME_COMMAND.getCommand();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件里该指令的消息提示
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
        ConfigurationSection setHomeconfigurationSection = configurationSection.getConfigurationSection(setHomeCommand);
        ConfigurationSection setHomeMessage = setHomeconfigurationSection.getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        List<String> allowHomeWorldList = setHomeconfigurationSection.getStringList("allow-world");

            //判断指令是否带参数
            if (args.length != 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + setHomeMessage.getString("sethome-command-error")));
                return true;
            }

            //判断执行指令的是用户还是控制台
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + setHomeMessage.getString("sethome-console-error")));
                return true;
            }

            //获取玩家位置变量
            Player player = (Player) sender;
            Location playerLocation = player.getLocation();

            //判断是否是允许在当前世界设置家
            String worldName = playerLocation.getWorld().getName();
            if (!allowHomeWorldList.contains(worldName) && !allowHomeWorldList.contains(CommonEnum.ALL.getCommand())) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + setHomeMessage.getString("sethome-world-error").replaceAll("%world%", worldName)));
                return true;
            }

            //获取玩家位置跟朝向信息
            double playerLocationX = playerLocation.getX();
            double playerLocationY = playerLocation.getY();
            double playerLocationZ = playerLocation.getZ();
            float playerLocationYaw = playerLocation.getYaw();
            float playerLocationPitch = playerLocation.getPitch();

            //写入家数据文件，用于重启服务器后读取
            String playerName = player.getName();
            YamlConfiguration homeFileYaml = SetHomeConfig.homeFileYaml;
            homeFileYaml.set(playerName + ".world", worldName);
            homeFileYaml.set(playerName + ".x", playerLocationX);
            homeFileYaml.set(playerName + ".y", playerLocationY);
            homeFileYaml.set(playerName + ".z", playerLocationZ);
            homeFileYaml.set(playerName + ".yaw", playerLocationYaw);
            homeFileYaml.set(playerName + ".pitch", playerLocationPitch);
            try {
                Writer writer = new OutputStreamWriter(Files.newOutputStream(SetHomeConfig.file.toPath()), StandardCharsets.UTF_8);
                writer.write(homeFileYaml.saveToString());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + setHomeMessage.getString("sethome-apply").replaceAll("%world%", worldName).replaceAll("%x%", String.valueOf((int) playerLocationX)).replaceAll("%y%", String.valueOf((int) playerLocationY)).replaceAll("%z%", String.valueOf((int) playerLocationZ))));
                throw new RuntimeException(e);
            }

            //发送设置家成功消息
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + setHomeMessage.getString("sethome-apply").replaceAll("%world%", worldName).replaceAll("%x%", String.valueOf((int) playerLocationX)).replaceAll("%y%", String.valueOf((int) playerLocationY)).replaceAll("%z%", String.valueOf((int) playerLocationZ))));
            return true;
    }
}
