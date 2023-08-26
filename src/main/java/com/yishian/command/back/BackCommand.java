package com.yishian.command.back;

import com.yishian.command.teleport.TeleportCommand;
import com.yishian.common.CommonMessageEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

/**
 * @author XinQi
 * 返回指令触发
 */
public class BackCommand implements CommandExecutor {

    /**
     * 指令触发事件
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + CommonMessageEnum.CONSOLE_COMMAND_NO_USE.getMsg()));
            return true;
        }

        //获取玩家back的记录信息
        Player player = (Player) sender;
        ConfigurationSection playerConfig = BackConfig.BackFileYaml.getConfigurationSection(player.getName());

        //判断该用户是否拥有back的位置
        if (playerConfig == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + BackConfigEnum.BACK_NO_LOCATION.getMsg()));
            return true;
        }

        //判断服务器是否允许传送，允许传送玩家就发送传送成功消息，不允许的话，传送将会被传送监听器拦截。
        player.teleport(new Location(Bukkit.getWorld(playerConfig.getString("world")), playerConfig.getDouble("x"), playerConfig.getDouble("y"), playerConfig.getDouble("z"), Float.parseFloat(playerConfig.getString("yaw")), Float.parseFloat(playerConfig.getString("pitch"))));
        if (TeleportCommand.allowTp) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + BackConfigEnum.BACK_APPLY.getMsg()));
        }

        return true;
    }

}
