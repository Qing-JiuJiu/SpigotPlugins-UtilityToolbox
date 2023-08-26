package com.yishian.command.settpp;

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
public class SetTppCommand implements CommandExecutor {

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

        //判断是否是允许在当前世界设置临时传送点
        String worldName = playerLocation.getWorld().getName();
        //获得允许设置临时传送点的世界列表
        List<?> allowWorldList = CommonUtil.objectToList(SetTppConfigEnum.ALLOW_WORLD.getMsg());
        if (!allowWorldList.contains(worldName) && !allowWorldList.contains(CommonPluginEnum.ALL.getCommand())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + SetTppConfigEnum.SETTPP_WORLD_ERROR.getMsg().toString().replaceAll("%world%", worldName)));
            return true;
        }

        //获取玩家位置跟朝向信息
        double playerLocationX = playerLocation.getX();
        double playerLocationY = playerLocation.getY();
        double playerLocationZ = playerLocation.getZ();
        float playerLocationYaw = playerLocation.getYaw();
        float playerLocationPitch = playerLocation.getPitch();

        //写入传送点数据文件，用于重启服务器后能读取
        String tpName = "default";
        if (args.length == 1) {
            tpName = args[0];
        }
        String prefix = player.getName() + "." + tpName + ".";
        YamlConfiguration snapFileYaml = SetTppConfig.snapFileYaml;
        snapFileYaml.set(prefix + "world", worldName);
        snapFileYaml.set(prefix + "x", playerLocationX);
        snapFileYaml.set(prefix + "y", playerLocationY);
        snapFileYaml.set(prefix + "z", playerLocationZ);
        snapFileYaml.set(prefix + "yaw", playerLocationYaw);
        snapFileYaml.set(prefix + "pitch", playerLocationPitch);
        CommonUtil.saveYamlConfig(snapFileYaml, SetTppConfig.file.toPath());

        //发送设置临时传送点消息成功消息
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + SetTppConfigEnum.SETTPP_APPLY.getMsg()).replaceAll("%tp-name%", tpName));
        return true;
    }
}
