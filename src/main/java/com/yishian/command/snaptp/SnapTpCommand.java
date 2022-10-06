package com.yishian.command.snaptp;


import com.yishian.command.setsnaptp.SetSnapTpCommand;

import com.yishian.command.teleport.TeleportCommand;
import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

import org.bukkit.entity.Player;

/**
 * @author XinQi
 */
public class SnapTpCommand implements CommandExecutor {

    String snapTpCommand = SnapTpEnum.SNAP_TP_COMMAND.getCommand();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件里该指令的消息提示
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
        ConfigurationSection snapTpMessage = configurationSection.getConfigurationSection(snapTpCommand).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

            //判断指令是否带参数
            if (args.length != 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + snapTpMessage.getString("snaptp-command-error")));
                return true;
            }

            //判断执行指令的是用户还是控制台
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + snapTpMessage.getString("snaptp-console-error")));
                return true;
            }

            //获取玩家家的配置信息
            Player player = (Player) sender;
            Location location = SetSnapTpCommand.transfeRecordMap.get(player);

            //判断该用户是否有临时传送点
            if (location == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + snapTpMessage.getString("snaptp-no-exist")));
                return true;
            }

            //传送玩家并发送对应消息
            player.teleport(location);
            if (TeleportCommand.allowTp) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + snapTpMessage.getString("snaptp-apply")));
            }
            return true;
    }
}
