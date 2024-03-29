package com.yishian.command.sethome;

import com.yishian.common.CommonPluginEnum;
import com.yishian.common.CommonUtil;
import com.yishian.common.CommonMessageEnum;
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
public class SetHomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + CommonMessageEnum.CONSOLE_COMMAND_NO_USE.getMsg()));
            return true;
        }

        //获取玩家位置变量
        Player player = (Player) sender;
        Location playerLocation = player.getLocation();

        //判断是否是允许在当前世界设置家
        String worldName = playerLocation.getWorld().getName();
        //获得允许设置家的世界列表
        List<?> allowHomeWorldList = CommonUtil.objectToList(SetHomeConfigEnum.ALLOW_WORLD.getMsg());
        if (!allowHomeWorldList.contains(worldName) && !allowHomeWorldList.contains(CommonPluginEnum.ALL.getCommand())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + SetHomeConfigEnum.SETHOME_WORLD_ERROR.getMsg()).replaceAll("%world%", worldName));
            return true;
        }

        //获取玩家位置跟朝向信息
        double playerLocationX = playerLocation.getX();
        double playerLocationY = playerLocation.getY();
        double playerLocationZ = playerLocation.getZ();
        float playerLocationYaw = playerLocation.getYaw();
        float playerLocationPitch = playerLocation.getPitch();

        //写入家数据文件，用于重启服务器后能读取，也防止内存泄露想象
        String homeName = "default";
        if (args.length == 1) {
            homeName = args[0];
        }
        String prefix = player.getName() + "." + homeName + ".";
        YamlConfiguration homeFileYaml = SetHomeConfig.homeFileYaml;
        homeFileYaml.set(prefix +"world", worldName);
        homeFileYaml.set(prefix + "x", playerLocationX);
        homeFileYaml.set(prefix + "y", playerLocationY);
        homeFileYaml.set(prefix + "z", playerLocationZ);
        homeFileYaml.set(prefix + "yaw", playerLocationYaw);
        homeFileYaml.set(prefix + "pitch", playerLocationPitch);
        CommonUtil.saveYamlConfig(homeFileYaml, SetHomeConfig.file.toPath());

        //发送设置家成功消息
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + SetHomeConfigEnum.SETHOME_APPLY.getMsg()).replaceAll("%name%", homeName));
        return true;
    }
}
