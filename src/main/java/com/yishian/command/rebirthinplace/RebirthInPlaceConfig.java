package com.yishian.command.rebirthinplace;


import com.yishian.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * @author XinQi
 */
public class RebirthInPlaceConfig {

    public static YamlConfiguration rebirthInPlaceFileYaml;

    public static File file;

    public static void loadRebirthInPlaceConfigFile() throws IOException {
        //得到插件配置目录下的homes.yml文件
        file = new File(JavaPlugin.getPlugin(Main.class).getDataFolder(), "RebirthInPlace.yml");
        //判断文件是否存在
        if (!file.exists()) {
            //创建文件并加载该文件
            if (file.createNewFile()) {
                rebirthInPlaceFileYaml = YamlConfiguration.loadConfiguration(file);
            }
        } else {
            //直接加载文件
            rebirthInPlaceFileYaml = YamlConfiguration.loadConfiguration(file);
        }
    }

}
