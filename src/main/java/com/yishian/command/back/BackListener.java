package com.yishian.command.back;

import com.yishian.Main;
import com.yishian.command.teleport.TeleportCommand;

import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * @author XinQi
 */
public class BackListener implements Listener {

    String backCommand = BackEnum.BACK_COMMAND.getCommand();

    /**
     * 记录Back的位置
     *
     * @param playerTeleportEvent 即将传送的玩家
     */
    @EventHandler
    public void playerTeleport(PlayerTeleportEvent playerTeleportEvent) {
        //获取玩家被传送的原因跟玩家
        PlayerTeleportEvent.TeleportCause teleportCause = playerTeleportEvent.getCause();

        //当玩家被传送的原因是因为指令/插件时记录位置
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
            Player player = playerDeathEvent.getEntity();
            //判断玩家是否有back的指令权限
            if (player.hasPermission(BackEnum.BACK_PERMISSION.getCommand())) {
                //获取配置文件里该指令的消息提示
                ConfigurationSection configurationSection = PluginUtils.getServerConfig();
                String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
                ConfigurationSection backMessage = configurationSection.getConfigurationSection(backCommand).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

                BackCommand.playerBackMap.put(player.getUniqueId(), player.getLocation());
                if (TeleportCommand.allowTp) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + backMessage.getString("back-died-tips")));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + backMessage.getString("back-died-no-tp-tips")));
                }
            }
        });
    }
}
