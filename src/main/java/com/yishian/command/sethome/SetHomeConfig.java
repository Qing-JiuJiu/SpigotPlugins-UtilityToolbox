package com.yishian.command.sethome;


import com.yishian.Main;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * @author XinQi
 */
public class SetHomeConfig {

    public static YamlConfiguration homeFileYaml;

    public static File file;

    public static void loadHomeConfigFile() throws IOException {
        //得到插件配置目录下的homes.yml文件
        file = new File(JavaPlugin.getPlugin(Main.class).getDataFolder(), "homes.yml");
        //判断文件是否存在
        if (!file.exists()) {
            //创建文件并加载该文件
            if (file.createNewFile()) {
                homeFileYaml = YamlConfiguration.loadConfiguration(file);
            }
        } else {
            //直接加载文件
            homeFileYaml = YamlConfiguration.loadConfiguration(file);
        }
    }

}
