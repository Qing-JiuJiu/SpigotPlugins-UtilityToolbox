package com.yishian.common;

import com.yishian.Main;
import org.bukkit.configuration.file.FileConfiguration;


/**
 * @author XinQi
 */
public class ServerUtils {

    /**
     * 得到服务器配置文件
     * @return 返回配置文件
     */
    public static FileConfiguration getServerConfig() {
        return Main.getProvidingPlugin(Main.class).getConfig();
    }
}
