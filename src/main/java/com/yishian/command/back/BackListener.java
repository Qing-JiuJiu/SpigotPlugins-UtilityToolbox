package com.yishian.command.back;

import com.yishian.Main;
import com.yishian.command.autodeathback.AutoRespawnBackConfig;
import com.yishian.command.teleport.TeleportCommand;
import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
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
            recordPlayerLocation(playerTeleportEvent.getPlayer());
        }
    }

    /**
     * 记录Back位置
     *
     * @param playerDeathEvent 即将死亡的玩家
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void playerDeath(PlayerDeathEvent playerDeathEvent) {
        //使用同步任务让消息显示在死亡消息后面
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class), () -> {
            //记录玩家死亡位置
            Player player = playerDeathEvent.getEntity();
            recordPlayerLocation(player);

            //判断玩家是否有back的指令权限
            if (player.hasPermission(BackEnum.BACK_PERMISSION.getCommand())) {
                //判断服务器是否允许传送，如果允许且玩家没有开启自动重生死亡返回则提醒玩家可以使用/back指令返回到死亡位置
                if (TeleportCommand.allowTp) {
                    if (!AutoRespawnBackConfig.autoRespawnBackFileYaml.getBoolean(player.getName() + "." + CommonEnum.FUNCTION_IS_ENABLE.getCommand())) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + BackConfigEnum.BACK_DIED_TIPS.getMsg()));
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + BackConfigEnum.BACK_DIED_NO_TP_TIPS.getMsg()));
                }
            }
        });
    }

    private void recordPlayerLocation(Player player){
        Location playerLocation = player.getLocation();
        //获取玩家位置跟朝向信息
        String worldName = playerLocation.getWorld().getName();
        double playerLocationX = playerLocation.getX();
        double playerLocationY = playerLocation.getY();
        double playerLocationZ = playerLocation.getZ();
        float playerLocationYaw = playerLocation.getYaw();
        float playerLocationPitch = playerLocation.getPitch();
        //写入家数据文件，用于重启服务器后能读取，也防止内存泄露想象
        String playerName = player.getName();
        YamlConfiguration backFileYaml = BackConfig.BackFileYaml;
        backFileYaml.set(playerName + ".world", worldName);
        backFileYaml.set(playerName + ".x", playerLocationX);
        backFileYaml.set(playerName + ".y", playerLocationY);
        backFileYaml.set(playerName + ".z", playerLocationZ);
        backFileYaml.set(playerName + ".yaw", playerLocationYaw);
        backFileYaml.set(playerName + ".pitch", playerLocationPitch);
        //写入back文件
        CommonUtils.saveYamlConfig(backFileYaml, BackConfig.file.toPath());
    }
}

