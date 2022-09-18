package com.yishian.command.showtextcolor;


import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class ShowTextCodeCommand implements CommandExecutor {

    String showTextCodeCommand = ShowTextCodeEnum.SHOW_TEXT_CODE_COMMAND.getCommand();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件里该指令的消息提示
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
        ConfigurationSection showTextCodeMessage = configurationSection.getConfigurationSection(showTextCodeCommand).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        List<String> sendMessageList = showTextCodeMessage.getStringList("show-list");

        //判断执行的指令内容
        if (showTextCodeCommand.equalsIgnoreCase(label)) {
            //判断指令是否带参数
            if (args.length != 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + showTextCodeMessage.getString("showtextcode-command-error")));
                return true;
            }

            //发送消息
            sendMessageList.forEach(message ->  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + message)));
            return true;
        }
        return false;
    }
}
