package com.yishian.command.tpp;

import com.yishian.command.settpp.SetTppConfig;
import com.yishian.command.teleport.TeleportCommand;
import com.yishian.common.CommonEnum;
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
public class TppCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否携带过多参数
        if (args.length > 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TppConfigEnum.TPP_COMMAND_ERROR.getMsg()));
            return true;
        }

        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TppConfigEnum.TPP_CONSOLE_ERROR.getMsg()));
            return true;
        }

        //获取玩家临时传送点的配置信息
        Player player = (Player) sender;

        //读取临时传送点数据文件
        String playName = player.getName();
        String tpName = "default";
        //获得参数值，也就是坐标名称，否则当默认处理
        if (args.length == 1) {
            tpName = args[0];
        }
        YamlConfiguration snapFileYaml = SetTppConfig.snapFileYaml;
        ConfigurationSection playerConfig = snapFileYaml.getConfigurationSection(playName + "." + tpName);

        //判断该用户是否有传送点
        if (playerConfig == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TppConfigEnum.TPP_NO_EXIST.getMsg()).replaceAll("%tp-name%", tpName));
            return true;
        }

        //传送玩家并发送对应消息
        player.teleport(new Location(Bukkit.getWorld(playerConfig.getString("world")), playerConfig.getDouble("x"), playerConfig.getDouble("y"), playerConfig.getDouble("z"), Float.parseFloat(playerConfig.getString("yaw")), Float.parseFloat(playerConfig.getString("pitch"))));
        if (TeleportCommand.allowTp) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TppConfigEnum.TPP_APPLY.getMsg()).replaceAll("%tp-name%", tpName));
        }

        return true;
    }
}
