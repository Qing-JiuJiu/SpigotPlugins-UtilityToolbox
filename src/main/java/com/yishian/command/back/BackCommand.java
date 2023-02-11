package com.yishian.command.back;

import com.yishian.command.teleport.TeleportCommand;
import com.yishian.common.CommonEnum;
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
 */
public class BackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否带参数
        if (args.length != 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + BackConfigEnum.BACK_COMMAND_ERROR.getMsg()));
            return true;
        }

        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + BackConfigEnum.BACK_CONSOLE_ERROR.getMsg()));
            return true;
        }

        //获取玩家back的记录信息
        Player player = (Player) sender;
        ConfigurationSection playerConfig = BackConfig.BackFileYaml.getConfigurationSection(player.getName());

        //判断该用户是否拥有back的位置
        if (playerConfig == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + BackConfigEnum.BACK_NO_LOCATION.getMsg()));
            return true;
        }

        //判断服务器是否允许传送，允许传送玩家就发送传送成功消息，不允许的话，传送将会被传送监听器拦截。
        player.teleport(new Location(Bukkit.getWorld(playerConfig.getString("world")), playerConfig.getDouble("x"), playerConfig.getDouble("y"), playerConfig.getDouble("z"), Float.parseFloat(playerConfig.getString("yaw")), Float.parseFloat(playerConfig.getString("pitch"))));
        if (TeleportCommand.allowTp) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + BackConfigEnum.BACK_APPLY.getMsg()));
        }

        //执行成功
        return true;
    }
}
