package com.yishian.command.tpa;

import com.yishian.Main;
import com.yishian.common.CommonEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Set;

/**
 * @author XinQi
 */
public class TpaPlayerLeaveServerListener implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void playerOnLeave(PlayerQuitEvent playerQuitEvent) {
        //执行异步任务，用于删除玩家的传送记录
        Bukkit.getScheduler().runTaskAsynchronously(Main.getProvidingPlugin(Main.class), () -> {
            //主功能执行，删除该玩家的传送请求
            Player player = playerQuitEvent.getPlayer();
            Player transfeRecordPlayer = TpaCommand.transfeRecordMap.get(player);
            if (transfeRecordPlayer != null) {
                Set<Player> playerSet = TpaCommand.transfeMap.get(transfeRecordPlayer);
                playerSet.removeIf(judgmentPlayer -> judgmentPlayer == player);
                transfeRecordPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaConfigEnum.TPA_OTHERS_LEAVE_SERVER.getMsg()).replaceAll("%others-player%", player.getName()));
            }
        });
    }
}
