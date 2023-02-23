package com.yishian.command.teleport;

import com.yishian.Main;
import com.yishian.common.CommonEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * @author XinQi
 */
public class TeleportListener implements Listener {

    /**
     * 禁止传送
     * @param playerTeleportEvent 即将传送的玩家
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void playerTeleport(PlayerTeleportEvent playerTeleportEvent) {
        PlayerTeleportEvent.TeleportCause teleportCause = playerTeleportEvent.getCause();
        //判断是否允许传送,
        if (!TeleportCommand.allowTp && (teleportCause == PlayerTeleportEvent.TeleportCause.COMMAND || teleportCause == PlayerTeleportEvent.TeleportCause.PLUGIN)){
            playerTeleportEvent.setCancelled(true);

            //发送消息，让消息显示在最后一行
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class),()-> playerTeleportEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TeleportConfigEnum.TELEPORT_DENY_APPLY.getMsg())));
        }
    }

}
