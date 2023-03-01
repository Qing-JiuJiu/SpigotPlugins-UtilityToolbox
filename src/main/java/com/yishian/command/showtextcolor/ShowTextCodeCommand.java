package com.yishian.command.showtextcolor;

import com.yishian.common.CommonUtils;
import com.yishian.common.PluginMessageConfigEnum;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author XinQi
 */
public class ShowTextCodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否带参数
        if (args.length != 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + ShowTextCodeConfigEnum.SHOWTEXTCODE_COMMAND_ERROR.getMsg()));
            return true;
        }

        //发送消息
        CommonUtils.objectToList(ShowTextCodeConfigEnum.SHOW_LIST.getMsg()).forEach(message -> sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + message)));
        return true;
    }
}
