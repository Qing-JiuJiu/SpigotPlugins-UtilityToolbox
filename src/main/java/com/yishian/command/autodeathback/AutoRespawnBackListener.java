package com.yishian.command.autodeathback;

import com.yishian.command.back.BackConfig;
import com.yishian.command.teleport.TeleportCommand;
import com.yishian.command.teleport.TeleportConfigEnum;
import com.yishian.common.CommonEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * @author XinQi
 */
public class AutoRespawnBackListener implements Listener {

    /**
     * 玩家重生后回到死亡位置
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerRespawn(PlayerRespawnEvent playerRespawnEvent) {
        //获得玩家和名称
        Player player = playerRespawnEvent.getPlayer();
        String playerName = player.getName();

        //判断玩家是否开启自动死亡返回
        if (AutoRespawnBackConfig.autoRespawnBackFileYaml.getBoolean(playerName + "." + CommonEnum.FUNCTION_IS_ENABLE.getCommand())) {
            //玩家重生后，获得玩家back信息并设置玩家重生位置和发送消息
            ConfigurationSection playerConfig = BackConfig.BackFileYaml.getConfigurationSection(playerName);
            if (TeleportCommand.allowTp) {
                playerRespawnEvent.setRespawnLocation(new Location(Bukkit.getWorld(playerConfig.getString("world")), playerConfig.getDouble("x"), playerConfig.getDouble("y"), playerConfig.getDouble("z"), Float.parseFloat(playerConfig.getString("yaw")), Float.parseFloat(playerConfig.getString("pitch"))));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + AutoRespawnBackConfigEnum.AUTORESPAWNBACK_APPLY.getMsg()));
            }else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TeleportConfigEnum.TELEPORT_DENY_APPLY.getMsg()));
            }
        }
    }
}
