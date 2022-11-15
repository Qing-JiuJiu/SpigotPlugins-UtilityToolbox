package com.yishian.command.teleport;



import com.yishian.Main;
import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * @author XinQi
 */
public class TeleportListener implements Listener {

    String teleportCommand = TeleportEnum.TELEPORT_COMMAND.getCommand();

    /**
     * 禁止传送
     * @param playerTeleportEvent 即将传送的玩家
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void playerTeleport(PlayerTeleportEvent playerTeleportEvent) {
        PlayerTeleportEvent.TeleportCause teleportCause = playerTeleportEvent.getCause();
        //判断是否允许传送,
        if (!TeleportCommand.allowTp && (teleportCause == PlayerTeleportEvent.TeleportCause.COMMAND || teleportCause == PlayerTeleportEvent.TeleportCause.PLUGIN)){
            //获取配置文件里该指令的消息提示
            ConfigurationSection configurationSection = PluginUtils.getServerConfig();
            String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
            ConfigurationSection teleportMessage = configurationSection.getConfigurationSection(teleportCommand).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
            playerTeleportEvent.setCancelled(true);

            //发送消息，让消息显示在最后一行
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class),()-> playerTeleportEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + teleportMessage.getString("teleport-deny-apply"))));
        }
    }

}
