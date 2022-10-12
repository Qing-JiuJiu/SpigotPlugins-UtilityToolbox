package com.yishian.command.back;


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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author XinQi
 */
public class BackCommand implements CommandExecutor {

    String backCommand = BackEnum.BACK_COMMAND.getCommand();

    static Map<UUID, Location> playerBackMap = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件里该指令的消息提示
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
        ConfigurationSection backMessage = configurationSection.getConfigurationSection(backCommand).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

        //判断指令是否带参数
        if (args.length != 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + backMessage.getString("back-command-error")));
            return true;
        }

        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + backMessage.getString("back-console-error")));
            return true;
        }

        //获取玩家家的配置信息
        Player player = (Player) sender;
        Location location = playerBackMap.get(player.getUniqueId());

        //判断该用户是否拥有返回的位置
        if (location == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + backMessage.getString("back-no-location")));
            return true;
        }

        //判断服务器是否允许传送传送玩家并发送对应消息
        player.teleport(location);
        if (TeleportCommand.allowTp) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + backMessage.getString("back-apply")));
        }

        //到这已经执行功能成功
        return true;
    }
}
