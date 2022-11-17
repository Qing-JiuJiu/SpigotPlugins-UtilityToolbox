package com.yishian.command.autodeathback;

import com.yishian.Main;
import com.yishian.command.back.BackCommand;
import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.UUID;

/**
 * @author XinQi
 */
public class AutoRespawnBackListener implements Listener {

    /**
     * 玩家重生后回到死亡位置
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void playerRespawn(PlayerRespawnEvent playerRespawnEvent) {
        Player player = playerRespawnEvent.getPlayer();
        UUID playerUniqueId = player.getUniqueId();
        if (AutoRespawnBackCommand.autoDeathBackList.contains(playerUniqueId)) {
            //获取配置文件消息
            ConfigurationSection configurationSection = PluginUtils.getServerConfig();
            String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
            ConfigurationSection autoDeathBackMessage = configurationSection.getConfigurationSection(AutoRespawnBackEnum.AUTO_RESPAWN_BACK_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

            //等玩家重生后，传送玩家并发送消息
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class), () -> {
                Location location = BackCommand.playerBackMap.get(playerUniqueId);
                player.teleport(location);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + autoDeathBackMessage.getString("autorespawnback-apply")));
            });
        }
    }
}
