package com.yishian.command.sethome;


import com.yishian.Main;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class SetHomeConfig {

    public static YamlConfiguration homeFileYaml;

    public static File file;

    public static void loadHomeConfigFile() throws IOException {
        file = new File(JavaPlugin.getPlugin(Main.class).getDataFolder(), "homes.yml");
        if (!file.exists()) {
            if (file.createNewFile()) {
                homeFileYaml = YamlConfiguration.loadConfiguration(file);
            }
        } else {
            homeFileYaml = YamlConfiguration.loadConfiguration(file);
        }
    }

}