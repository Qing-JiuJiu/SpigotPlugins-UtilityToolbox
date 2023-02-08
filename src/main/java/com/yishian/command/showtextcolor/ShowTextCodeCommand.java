package com.yishian.command.showtextcolor;

import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

/**
 * @author XinQi
 */
public class ShowTextCodeCommand implements CommandExecutor {

    /**
     * 获取配置文件里该指令的消息提示
     */
    static ConfigurationSection showTextCodeMessage = CommonUtils.ServerConfig.getConfigurationSection(ShowTextCodeEnum.SHOW_TEXT_CODE_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否带参数
        if (args.length != 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + showTextCodeMessage.getString("showtextcode-command-error")));
            return true;
        }

        //发送消息
        showTextCodeMessage.getStringList("show-list").forEach(message -> sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + message)));
        return true;
    }
}
