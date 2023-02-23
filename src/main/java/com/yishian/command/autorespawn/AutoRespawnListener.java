package com.yishian.command.autorespawn;

import com.yishian.common.CommonEnum;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * @author XinQi
 */
public class AutoRespawnListener implements Listener {
    /**
     * 玩家死亡后立即重生
     * @param playerDeathEvent 即将死亡的玩家
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void autoRespawn(PlayerDeathEvent playerDeathEvent) {
        Player player = playerDeathEvent.getEntity();
        //判断是否要自动重生
        if (AutoRespawnConfig.autoRespawnFileYaml.getBoolean(player.getName() + "." + CommonEnum.FUNCTION_IS_ENABLE.getCommand())){
                //玩家重生并发送对应消息
                player.spigot().respawn();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + AutoRespawnConfigEnum.AUTORESPAWN_APPLY.getMsg()));
        }
    }
}
