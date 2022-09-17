package com.yishian.command.tpa;

import com.yishian.Main;
import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Set;

public class TpaPlayerLeaveServerListener implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void playerOnLeave(PlayerQuitEvent playerQuitEvent) {

        //得到消息前缀
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
        ConfigurationSection tpaMessage = configurationSection.getConfigurationSection(TpaEnum.TPA_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

        Bukkit.getScheduler().runTaskAsynchronously(Main.getProvidingPlugin(Main.class), () -> {
            //主功能执行，删除该玩家的传送请求
            Player player = playerQuitEvent.getPlayer();
            Player transfeRecordPlayer = TpaCommand.transfeRecordMap.get(player);
            if (transfeRecordPlayer != null) {
                Set<Player> playerSet = TpaCommand.transfeMap.get(transfeRecordPlayer);
                playerSet.removeIf(judgmentPlayer -> judgmentPlayer == player);
                transfeRecordPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaMessage.getString("tpa-others-leave-server").replaceAll("%others-player%", player.getName())));
            }
        });
    }
}
