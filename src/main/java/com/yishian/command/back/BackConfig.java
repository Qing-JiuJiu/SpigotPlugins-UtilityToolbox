package com.yishian.command.back;

import com.yishian.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * @author XinQi
 */
public class BackConfig {

    public static YamlConfiguration BackFileYaml;

    public static File file;

    public static void loadBackConfigFile() throws IOException {
        //得到插件配置目录下的homes.yml文件
        file = new File(JavaPlugin.getPlugin(Main.class).getDataFolder(), "back.yml");
        //判断文件是否存在
        if (!file.exists()) {
            //创建文件并加载该文件
            if (file.createNewFile()) {
                BackFileYaml = YamlConfiguration.loadConfiguration(file);
            }
        } else {
            //直接加载文件
            BackFileYaml = YamlConfiguration.loadConfiguration(file);
        }
    }

}
