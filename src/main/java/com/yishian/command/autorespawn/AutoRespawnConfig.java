package com.yishian.command.autorespawn;


import com.yishian.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * @author XinQi
 */
public class AutoRespawnConfig {

    public static YamlConfiguration autoRespawnFileYaml;

    public static File file;

    public static void loadAutoRespawnConfigFile() throws IOException {
        //得到插件配置目录下的homes.yml文件
        file = new File(JavaPlugin.getPlugin(Main.class).getDataFolder(), "AutoRespawn.yml");
        //判断文件是否存在
        if (!file.exists()) {
            //创建文件并加载该文件
            if (file.createNewFile()) {
                autoRespawnFileYaml = YamlConfiguration.loadConfiguration(file);
            }
        } else {
            //直接加载文件
            autoRespawnFileYaml = YamlConfiguration.loadConfiguration(file);
        }
    }

}
