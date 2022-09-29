package com.yishian.command.setsnaptp;


import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

/**
 * @author XinQi
 */
public class SetSnapTpCommand implements CommandExecutor {

    String setSnapTpCommand = SetSnapTpEnum.SET_SNAP_TP_COMMAND.getCommand();

    /**
     * 用于记录自己的临时传送点
     */
    public static HashMap<Player, Location> transfeRecordMap = new HashMap<>();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件里该指令的消息提示
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
        ConfigurationSection setSnapTpconfigurationSection = configurationSection.getConfigurationSection(setSnapTpCommand);
        ConfigurationSection setSnapTpMessage = setSnapTpconfigurationSection.getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        List<String> allowWorldList = setSnapTpconfigurationSection.getStringList("allow-world");

            //判断指令是否带参数
            if (args.length != 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + setSnapTpMessage.getString("setsnaptp-command-error")));
                return true;
            }

            //判断执行指令的是用户还是控制台
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + setSnapTpMessage.getString("setsnaptp-console-error")));
                return true;
            }

            //获取玩家位置变量
            Player player = (Player) sender;
            Location playerLocation = player.getLocation();

            //判断是否是允许在当前世界设置家
            String worldName = playerLocation.getWorld().getName();
            if (!allowWorldList.contains(worldName) && !allowWorldList.contains(CommonEnum.ALL.getCommand())) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + setSnapTpMessage.getString("setsnaptp-world-error").replaceAll("%world%", worldName)));
                return true;
            }

            //发送设置临时传送点消息成功消息
            transfeRecordMap.put(player, playerLocation);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + setSnapTpMessage.getString("setsnaptp-apply")));
            return true;
    }
}
