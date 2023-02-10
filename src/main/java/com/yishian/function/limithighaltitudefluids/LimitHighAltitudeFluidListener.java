package com.yishian.function.limithighaltitudefluids;

import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author XinQi
 */
public class LimitHighAltitudeFluidListener implements Listener {

    @EventHandler
    public void onWaterLavaTo(BlockFromToEvent blockFromToEvent) {
        //得到物品类
        Block block = blockFromToEvent.getBlock();

        //得到物品位置
        Location blockLocation = block.getLocation();
        int blockX = blockLocation.getBlockX();
        int blockY = blockLocation.getBlockY();
        int blockZ = blockLocation.getBlockZ();

        //先判断高度是否被限制
        if (blockY > (Integer) LimitHighAltitudeFluidConfigEnum.LIMIT_HIGH.getMsg()) {
            //得到被限制的世界列表
            List<?> limitWorldList = CommonUtils.objectToList(LimitHighAltitudeFluidConfigEnum.LIMIT_WORLD_LIST.getMsg());
            //判断是否是被限制的世界
            if (limitWorldList.contains(CommonEnum.ALL.getCommand()) || limitWorldList.contains(block.getWorld().getName())) {
                //循环流体限制
                if (CommonUtils.objectToList(LimitHighAltitudeFluidConfigEnum.LIMIT_FLUID_LIST.getMsg()).contains(block.getType().getKey().toString())) {
                    //禁止该方块的事件
                    blockFromToEvent.setCancelled(true);
                    //得到距离这个流体最近的玩家
                    TreeMap<Double, Player> playerDistanceTreeMap = CommonUtils.calculatePlayerAroundTheItem(blockLocation);
                    String recentPlayerDistanceName = "&c(未找到)";
                    //判断是否附近有玩家
                    if (!CommonUtils.mapIsEmpty(playerDistanceTreeMap)) {
                        recentPlayerDistanceName = playerDistanceTreeMap.pollFirstEntry().getValue().getName();
                    }
                    //发送消息内容模板
                    String messageTemplate = ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + LimitHighAltitudeFluidConfigEnum.DESTROY_MESSAGE.getMsg()).replaceAll("%player%", recentPlayerDistanceName).replaceAll("%x%", String.valueOf(blockX)).replaceAll("%y%", String.valueOf(blockY)).replaceAll("%z%", String.valueOf(blockZ));
                    //判断是否要广播消息
                    if ((Boolean) LimitHighAltitudeFluidConfigEnum.IS_BROADCAST_MESSAGE.getMsg()) {
                        //广播消息
                        Bukkit.getServer().broadcastMessage(messageTemplate);
                    } else {
                        //获取拥有能收到限制流体消息的玩家,并发送消息
                        ArrayList<Player> players = CommonUtils.hasPermissionPlayerList(LimitHighAltitudeFluidEnum.LIMIT_FLOW_MESSAGE_PERMISSION.getCommand());
                        players.forEach(player -> player.sendMessage(messageTemplate));
                    }

                    //发送控制台消息
                    CommonUtils.consoleCommandSender.sendMessage(messageTemplate);
                }
            }
        }
    }
}
