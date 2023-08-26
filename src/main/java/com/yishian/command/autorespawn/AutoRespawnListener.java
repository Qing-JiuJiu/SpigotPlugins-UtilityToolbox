package com.yishian.command.autorespawn;

import com.yishian.Main;
import com.yishian.common.CommonPluginEnum;
import com.yishian.common.CommonMessageEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * @author XinQi
 * 自动重生监听
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
        if (AutoRespawnConfig.autoRespawnFileYaml.getBoolean(player.getName() + CommonPluginEnum.POINT + CommonPluginEnum.FUNCTION_IS_ENABLE.getCommand())){
            //在事件结束后发送玩家重生信息
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class), () -> {
                //玩家重生，此处为 Spigot 服务端方法，Bukkit 服务端端无法使用
                player.spigot().respawn();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + AutoRespawnConfigEnum.AUTORESPAWN_APPLY.getMsg()));
            });
        }
    }

}
