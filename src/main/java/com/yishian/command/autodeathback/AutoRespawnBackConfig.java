package com.yishian.command.autodeathback;


import com.yishian.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * @author XinQi
 */
public class AutoRespawnBackConfig {

    public static YamlConfiguration autoRespawnBackFileYaml;

    public static File file;

    public static void loadAutoRespawnBackConfigFile() throws IOException {
        //得到插件配置目录下的homes.yml文件
        file = new File(JavaPlugin.getPlugin(Main.class).getDataFolder(), "AutoRespawnBack.yml");
        //判断文件是否存在
        if (!file.exists()) {
            //创建文件并加载该文件
            if (file.createNewFile()) {
                autoRespawnBackFileYaml = YamlConfiguration.loadConfiguration(file);
            }
        } else {
            //直接加载文件
            autoRespawnBackFileYaml = YamlConfiguration.loadConfiguration(file);
        }
    }

}
