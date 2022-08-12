package com.yishian.customjoinandleave;

import com.yishian.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * @author XinQi
 */
public class CustomJoinAndLeaveCommand implements CommandExecutor {

    Logger logger = JavaPlugin.getPlugin(Main.class).getLogger();

    /**
     * 这是指令设置
     *
     * @param sender  调用者
     * @param command Command which was executed
     * @param label   指令
     * @param args    Passed command arguments
     * @return 返回执行是否完毕
     */
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (CustomJoinAndLeaveCommandEnum.RELOAD.getCommand().equalsIgnoreCase(label)) {
            if (sender.hasPermission(CustomJoinAndLeaveCommandEnum.CUSTOMJOINANDLEAVE_PERMISSION.getCommand())) {
                logger.info(CustomJoinAndLeaveCommandEnum.RELOAD.getMsg());
            } else {
                sender.sendMessage(ChatColor.RED + "你没有权限执行此指令");
            }
            return true;
        }
        return false;
    }
}
