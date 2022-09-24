package com.yishian.function.server_list_display_modification;

import com.yishian.Main;
import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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
    @EventHandler(priority = EventPriority.MONITOR)
    public void onServerList(ServerListPingEvent serverListPingEvent) {
        //获取配置文件里该指令的消息提示
        ConfigurationSection serverConfig = PluginUtils.getServerConfig();
        ConfigurationSection functionConfiguration = serverConfig.getConfigurationSection("server-list-display-modification");
        ConfigurationSection functionMessage = functionConfiguration.getConfigurationSection(CommonEnum.MESSAGE.getCommand());

        //设置显示内容 使用§特殊字符
        serverListPingEvent.setMotd(PluginUtils.replaceColorCode(functionMessage.getString("first-line")) + "\n§r" +
                PluginUtils.replaceColorCode(functionMessage.getString("second-line")));

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

