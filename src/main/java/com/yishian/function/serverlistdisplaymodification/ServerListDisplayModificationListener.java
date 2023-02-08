package com.yishian.function.serverlistdisplaymodification;

import com.yishian.Main;
import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
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
     * 获取配置文件
     */
    static ConfigurationSection functionConfiguration = CommonUtils.ServerConfig.getConfigurationSection("server-list-display-modification");
    /**
     * 获取配置文件里消息列表
     */
    static ConfigurationSection functionMessage = functionConfiguration.getConfigurationSection(CommonEnum.MESSAGE.getCommand());

    /**
     * 修改显示
     */
    @EventHandler
    public void onServerList(ServerListPingEvent serverListPingEvent) {
        //设置显示内容 使用§特殊字符
        serverListPingEvent.setMotd(CommonUtils.replaceColorCode(functionMessage.getString("first-line")) + "\n§r" +
                CommonUtils.replaceColorCode(functionMessage.getString("second-line")));

        //设置最大玩家
        serverListPingEvent.setMaxPlayers(functionConfiguration.getInt("max-player"));

        //设置icon
        File iconFile = new File(Main.getPlugin(Main.class).getDataFolder() + "/" + functionConfiguration.getString("icon"));
        if (iconFile.exists()) {
            try {
                serverListPingEvent.setServerIcon(Bukkit.getServer().loadServerIcon(ImageIO.read(iconFile)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

