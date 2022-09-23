package com.yishian.command.musterplayer;

import com.yishian.Main;
import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author XinQi
 */
public class MusterPlayerListener implements Listener {

    /**
     * 有人离开服务器时，如果是召集者/被召集者，则取消/移除召集信息
     *
     * @param playerQuitEvent 玩家离开服务器监听
     */
    @EventHandler
    public void musterPlayerOnLeave(PlayerQuitEvent playerQuitEvent) {
        //召集者
        Player musterPlayer = MusterPlayerCommand.musterPlayer;
        //判断是否有召集事件
        if (musterPlayer != null) {
            //获取配置文件信息
            ConfigurationSection configurationSection = PluginUtils.getServerConfig();
            String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
            ConfigurationSection musterPlayerConfig = configurationSection.getConfigurationSection(MusterPlayerEnum.MUSTER_PLAYER_COMMAND.getCommand());
            ConfigurationSection musterPlayerMessage = musterPlayerConfig.getConfigurationSection(CommonEnum.MESSAGE.getCommand());

            //判断是否是召集者
            Player leavePlayer = playerQuitEvent.getPlayer();
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), () -> {
                if (leavePlayer == musterPlayer) {
                    //发送相关召集取消信息
                    MusterPlayerCommand.musterPlayers.forEach(messagePlayer -> messagePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + musterPlayerMessage.getString("musterplayer-player-leave-convenor").replaceAll("%player%", leavePlayer.getName()))));

                    //取消召集事件
                    MusterPlayerCommand.clearMusterMessage();
                } else if (MusterPlayerCommand.notProcessedPlayers.contains(leavePlayer)) {
                    //如果是被召集者还未处理请求，那移除被召集者，并发送相关信息
                    MusterPlayerCommand.notProcessedPlayers.remove(leavePlayer);
                    MusterPlayerCommand.musterPlayers.remove(leavePlayer);
                    MusterPlayerCommand.musterPlayers.forEach(messagePlayer -> messagePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + musterPlayerMessage.getString("musterplayer-player-leave-callee").replaceAll("%player%", leavePlayer.getName()))));
                    //判断召集是否已经结束
                    if (PluginUtils.collectionIsEmpty(MusterPlayerCommand.notProcessedPlayers)) {
                        //结束召集事件
                        MusterPlayerCommand.endMuster(musterPlayerConfig.getInt(CommonEnum.TIME.getCommand()), messagePrefix, musterPlayerMessage);
                    }
                }
            });
        }

    }

}
