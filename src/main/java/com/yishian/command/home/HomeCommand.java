package com.yishian.command.home;

import com.yishian.command.sethome.SetHomeConfig;
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
public class HomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否带参数
        if (args.length > 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HomeConfigEnum.HOME_COMMAND_ERROR.getMsg()));
            return true;
        }

        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HomeConfigEnum.HOME_CONSOLE_ERROR.getMsg()));
            return true;
        }

        //获取玩家家的配置信息
        Player player = (Player) sender;
        //读取临时传送点数据文件
        String playName = player.getName();
        String homeName = "default";
        //获得参数值，也就是坐标名称，否则当默认处理
        if (args.length == 1) {
            homeName = args[0];
        }

        ConfigurationSection playerConfig = SetHomeConfig.homeFileYaml.getConfigurationSection(playName + "." + homeName);

        //判断该用户是否有家
        if (playerConfig == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HomeConfigEnum.HOME_NO_EXIST.getMsg()).replaceAll("%name%", homeName));
            return true;
        }

        //传送玩家并发送对应消息
        player.teleport(new Location(Bukkit.getWorld(playerConfig.getString("world")), playerConfig.getDouble("x"), playerConfig.getDouble("y"), playerConfig.getDouble("z"), Float.parseFloat(playerConfig.getString("yaw")), Float.parseFloat(playerConfig.getString("pitch"))));
        if (TeleportCommand.allowTp) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HomeConfigEnum.HOME_APPLY.getMsg()).replaceAll("%name%", homeName));
        }

        return true;
    }
}
