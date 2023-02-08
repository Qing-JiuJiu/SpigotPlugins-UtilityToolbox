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
        //得到插件配置目录下的snaps.yml文件
        file = new File(JavaPlugin.getPlugin(Main.class).getDataFolder(), "snaps.yml");
        //判断文件是否存在
        if (!file.exists()) {
            //创建文件并加载该文件
            if (file.createNewFile()) {
                snapFileYaml = YamlConfiguration.loadConfiguration(file);
            }
        } else {
            //直接加载文件
            snapFileYaml = YamlConfiguration.loadConfiguration(file);
        }
    }
}
