package com.yishian;

import com.yishian.auxiliary.AuxiliaryCommandEnum;
import com.yishian.auxiliary.FeedCommand;
import com.yishian.auxiliary.HealAndFeedCommand;
import com.yishian.auxiliary.HealCommand;
import com.yishian.customjoinandleave.CustomJoinAndLeaveListener;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * @author XinQi
 */
public final class Main extends JavaPlugin {

    /**
     * 这是插件启动时执行的方法
     */
    @Override
    public void onEnable() {

        //如果没读取到配置文件，保存默认配置文件到插件目录
        saveDefaultConfig();
        FileConfiguration config = getConfig();

        //启动服务器时发送公告
        this.getLogger().info("欢迎使用本插件");

        //服务注册监听事件
        if (((MemorySection) config.get("custom-join-and-leave")).getBoolean("enable")) {
            getServer().getPluginManager().registerEvents(new CustomJoinAndLeaveListener(), this);
        }

        //服务注册指令，判断配置文件
        boolean healHasEnable = ((MemorySection) config.get("heal")).getBoolean("enable");
        boolean feedHasEnable = ((MemorySection) config.get("feed")).getBoolean("enable");
        if (healHasEnable) {
            getCommand(AuxiliaryCommandEnum.HEAL_COMMAND.getCommand()).setExecutor(new HealCommand());
        }
        if (feedHasEnable) {
            getCommand(AuxiliaryCommandEnum.FEED_COMMAND.getCommand()).setExecutor(new FeedCommand());
        }
        if (healHasEnable && feedHasEnable) {
            getCommand(AuxiliaryCommandEnum.HEAL_AND_FEED_COMMAND.getCommand()).setExecutor(new HealAndFeedCommand());
        }
    }

    /**
     * 这是插件停止时执行的方法
     */
    @Override
    public void onDisable() {
    }
}
