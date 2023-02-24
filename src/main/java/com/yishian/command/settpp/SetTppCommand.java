package com.yishian.command.settpp;

import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author XinQi
 */
public class SetTppCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否携带过多参数
        if (args.length > 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + SetTppConfigEnum.SETTPP_COMMAND_ERROR.getMsg()));
            return true;
        }

        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + SetTppConfigEnum.SETTPP_CONSOLE_ERROR.getMsg()));
            return true;
        }

        //获取玩家位置变量
        Player player = (Player) sender;
        Location playerLocation = player.getLocation();

        //判断是否是允许在当前世界设置临时传送点
        String worldName = playerLocation.getWorld().getName();
        //获得允许设置临时传送点的世界列表
        List<?> allowWorldList = CommonUtils.objectToList(SetTppConfigEnum.ALLOW_WORLD.getMsg());
        if (!allowWorldList.contains(worldName) && !allowWorldList.contains(CommonEnum.ALL.getCommand())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + SetTppConfigEnum.SETTPP_WORLD_ERROR.getMsg().toString().replaceAll("%world%", worldName)));
            return true;
        }

        //获取玩家位置跟朝向信息
        double playerLocationX = playerLocation.getX();
        double playerLocationY = playerLocation.getY();
        double playerLocationZ = playerLocation.getZ();
        float playerLocationYaw = playerLocation.getYaw();
        float playerLocationPitch = playerLocation.getPitch();

        //写入临时传送点数据文件，用于重启服务器后能读取
        String playerName = player.getName();
        String tpName = "default";

        if (args.length == 1) {
            tpName = args[0];
        }
        YamlConfiguration snapFileYaml = SetTppConfig.snapFileYaml;
        snapFileYaml.set(playerName + "." + tpName + ".world", worldName);
        snapFileYaml.set(playerName + "." + tpName + ".x", playerLocationX);
        snapFileYaml.set(playerName + "." + tpName + ".y", playerLocationY);
        snapFileYaml.set(playerName + "." + tpName + ".z", playerLocationZ);
        snapFileYaml.set(playerName + "." + tpName + ".yaw", playerLocationYaw);
        snapFileYaml.set(playerName + "." + tpName + ".pitch", playerLocationPitch);
        CommonUtils.saveYamlConfig(snapFileYaml, SetTppConfig.file.toPath());

        //发送设置临时传送点消息成功消息
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + SetTppConfigEnum.SETTPP_APPLY.getMsg()).replaceAll("%tp-name%", tpName));
        return true;
    }
}
