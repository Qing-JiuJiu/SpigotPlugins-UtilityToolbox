package com.yishian.common;

import com.yishian.Main;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XinQi
 */
public class CommonCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件对应的提示消息
        ConfigurationSection pluginMessage = PluginUtils.getServerConfig().getConfigurationSection("plugin-message");
        String messagePrefix = pluginMessage.getString("message-prefix");
        //判断执行的指令内容
        if (CommandEnum.PLUGHIN_NAME.getCommand().equalsIgnoreCase(label)) {
            //判断参数长度是否为1 且是否是需要的参数
            if (args.length == 1 && CommandEnum.RELOAD_CONFIG_COMMAND.getCommand().equalsIgnoreCase(args[0])) {
                //重载配置文件
                Main.getProvidingPlugin(Main.class).reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + pluginMessage.getString("reload-message")));
            } else {
                //提示参数错误
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + pluginMessage.getString("command-args-error")));
            }
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> tips = new ArrayList<>();
        if (label.startsWith(CommandEnum.PLUGHIN_NAME.getCommand().substring(0, 1)) && args.length == 0) {
            tips.add(CommandEnum.PLUGHIN_NAME.getCommand());
            return tips;
        }
        //重载指令的提示
        tips.add(CommandEnum.RELOAD_CONFIG_COMMAND.getCommand());
        if (CommandEnum.PLUGHIN_NAME.getCommand().equalsIgnoreCase(label)) {
            if (StringUtils.isEmpty(args[0])) {
                return tips;
            } else if (args.length == 1) {
                List<String> argNoEmptyTips = new ArrayList<>();
                tips.forEach(tip -> {
                    if (tip.startsWith(args[0])) {
                        argNoEmptyTips.add(tip);
                    }
                });
                return argNoEmptyTips;
            }
        }
        return null;
    }
}
