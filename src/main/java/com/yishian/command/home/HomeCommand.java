package com.yishian.command.home;


import com.yishian.command.sethome.SetHomeConfig;
import com.yishian.command.teleport.TeleportCommand;
import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

/**
 * @author XinQi
 */
public class HomeCommand implements CommandExecutor {

    String homeCommand = HomeEnum.HOME_COMMAND.getCommand();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件里该指令的消息提示
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
        ConfigurationSection homeMessage = configurationSection.getConfigurationSection(homeCommand).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

            //判断指令是否带参数
            if (args.length != 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + homeMessage.getString("home-command-error")));
                return true;
            }

            //判断执行指令的是用户还是控制台
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + homeMessage.getString("home-console-error")));
                return true;
            }

            //获取玩家家的配置信息
            Player player = (Player) sender;
            String playerName = player.getName();
            YamlConfiguration homeFileYaml = SetHomeConfig.homeFileYaml;
            ConfigurationSection playerConfig = homeFileYaml.getConfigurationSection(playerName);

            //判断该用户是否有家
            if (playerConfig == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + homeMessage.getString("home-no-exist")));
                return true;
            }

            //传送玩家并发送对应消息
            player.teleport(new Location(Bukkit.getWorld(playerConfig.getString("world")), playerConfig.getDouble("x"), playerConfig.getDouble("y"), playerConfig.getDouble("z"), Float.parseFloat(playerConfig.getString("yaw")), Float.parseFloat(playerConfig.getString("pitch"))));
            if (TeleportCommand.allowTp) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + homeMessage.getString("home-apply")));
            }

            return true;
    }
}
