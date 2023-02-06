package com.yishian.command.setsnaptp;


import com.yishian.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * @author XinQi
 */
public class SetSnapTpConfig {

    public static YamlConfiguration snapFileYaml;

    public static File file;

    public static void loadSnapConfigFile() throws IOException {
        file = new File(JavaPlugin.getPlugin(Main.class).getDataFolder(), "snaps.yml");
        if (!file.exists()) {
            if (file.createNewFile()) {
                snapFileYaml = YamlConfiguration.loadConfiguration(file);
            }
        } else {
            snapFileYaml = YamlConfiguration.loadConfiguration(file);
        }
    }
}
