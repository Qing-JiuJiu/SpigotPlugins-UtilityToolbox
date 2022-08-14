package com.yishian.common;

import com.yishian.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class ServerUtils {

    public static FileConfiguration getServerConfig(){
        return Main.getProvidingPlugin(Main.class).getConfig();
    }

}
