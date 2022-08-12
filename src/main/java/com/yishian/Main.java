package com.yishian;

import com.yishian.auxiliary.AuxiliaryCommand;
import com.yishian.auxiliary.AuxiliaryCommandEnum;
import com.yishian.customjoinandleave.CustomJoinAndLeaveCommand;
import com.yishian.customjoinandleave.CustomJoinAndLeaveCommandEnum;
import com.yishian.customjoinandleave.CustomJoinAndLeaveListener;
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
        //启动服务器时发送公告
        this.getLogger().info("欢迎使用本插件");

        //服务注册监听事件
        getServer().getPluginManager().registerEvents(new CustomJoinAndLeaveListener(), this);

        //使服务注册指令
        AuxiliaryCommand auxiliaryCommand = new AuxiliaryCommand();
        CustomJoinAndLeaveCommand customJoinAndLeaveCommand = new CustomJoinAndLeaveCommand();
        getCommand(CustomJoinAndLeaveCommandEnum.RELOAD.getCommand()).setExecutor(customJoinAndLeaveCommand);
        getCommand(AuxiliaryCommandEnum.HEAL_COMMAND.getCommand()).setExecutor(auxiliaryCommand);
        getCommand(AuxiliaryCommandEnum.FEED_COMMAND.getCommand()).setExecutor(auxiliaryCommand);
        getCommand(AuxiliaryCommandEnum.HEALANDFEED_COMMAND.getCommand()).setExecutor(auxiliaryCommand);

    }

    /**
     * 这是插件停止时执行的方法
     */
    @Override
    public void onDisable() {
    }
}
