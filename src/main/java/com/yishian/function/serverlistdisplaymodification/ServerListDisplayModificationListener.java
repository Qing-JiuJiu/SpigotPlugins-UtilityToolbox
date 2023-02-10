package com.yishian.function.serverlistdisplaymodification;

import com.yishian.Main;
import com.yishian.common.CommonUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * @author XinQi
 */
public class ServerListDisplayModificationListener implements Listener {
    /**
     * 修改显示
     */
    @EventHandler
    public void onServerList(ServerListPingEvent serverListPingEvent) {
        //设置显示内容 使用§特殊字符
        serverListPingEvent.setMotd(CommonUtils.replaceColorCode(ServerListDisplayModificationConfigEnum.FIRST_LINE.getMsg().toString()) + "\n§r" +
                CommonUtils.replaceColorCode(ServerListDisplayModificationConfigEnum.SECOND_LINE.getMsg().toString()));

        //设置最大玩家
        serverListPingEvent.setMaxPlayers((Integer) ServerListDisplayModificationConfigEnum.MAX_PLAYER.getMsg());

        //设置icon
        File iconFile = new File(Main.getPlugin(Main.class).getDataFolder() + "/" + ServerListDisplayModificationConfigEnum.ICON.getMsg().toString());
        if (iconFile.exists()) {
            try {
                serverListPingEvent.setServerIcon(Bukkit.getServer().loadServerIcon(ImageIO.read(iconFile)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

