package com.yishian.command.back;

import com.yishian.Main;
import com.yishian.command.autodeathback.AutoRespawnBackCommand;
import com.yishian.command.teleport.TeleportCommand;
import com.yishian.common.CommonEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.UUID;

/**
 * @author XinQi
 */
public class BackListener implements Listener {
    /**
     * 记录Back的位置
     *
     * @param playerTeleportEvent 即将传送的玩家
     */
    @EventHandler
    public void playerTeleport(PlayerTeleportEvent playerTeleportEvent) {
        //获取玩家被传送的原因跟玩家
        PlayerTeleportEvent.TeleportCause teleportCause = playerTeleportEvent.getCause();

        //当玩家被传送是因为指令/插件时记录位置
        if (TeleportCommand.allowTp && (teleportCause == PlayerTeleportEvent.TeleportCause.COMMAND || teleportCause == PlayerTeleportEvent.TeleportCause.PLUGIN)) {
            Player player = playerTeleportEvent.getPlayer();
            BackCommand.playerBackMap.put(player.getUniqueId(), player.getLocation());
        }
    }

    /**
     * 记录Back位置
     *
     * @param playerDeathEvent 即将死亡的玩家
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void playerDeath(PlayerDeathEvent playerDeathEvent) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class), () -> {
            //记录玩家死亡位置
            Player player = playerDeathEvent.getEntity();
            UUID playerUniqueId = player.getUniqueId();
            BackCommand.playerBackMap.put(playerUniqueId, player.getLocation());

            //判断玩家是否有back的指令权限
            if (player.hasPermission(BackEnum.BACK_PERMISSION.getCommand())) {
                //判断服务器是否允许传送，如果允许且玩家没有开启自动重生死亡返回则提醒玩家可以使用/back指令返回到死亡位置
                if (TeleportCommand.allowTp) {
                    if (!AutoRespawnBackCommand.autoRespawnBackList.contains(playerUniqueId)) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + BackConfigEnum.BACK_DIED_TIPS.getMsg()));
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + BackConfigEnum.BACK_DIED_NO_TP_TIPS.getMsg()));
                }
            }
        });
    }
}
