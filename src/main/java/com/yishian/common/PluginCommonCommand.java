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
public class PluginCommonCommand implements TabExecutor {

    /**
     * 获取配置文件
     */
    ConfigurationSection pluginMessage = ServerUtils.getServerConfig().getConfigurationSection("plugin-message");
    String messagePrefix = pluginMessage.getString("message-prefix");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission(CommandEnum.RELOAD_CONFIG_PERMISSION.getCommand())) {
            if (CommandEnum.PLUGHIN_NAME.getCommand().equalsIgnoreCase(label)) {
                if (args.length == 1 && CommandEnum.RELOAD_CONFIG_COMMAND.getCommand().equalsIgnoreCase(args[0])) {
                    //重载配置文件
                    Main.getProvidingPlugin(Main.class).reloadConfig();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + pluginMessage.getString("reload-message")));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + pluginMessage.getString("command-args-error")));
                }
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + pluginMessage.getString("command-no-permission")));
        }
        return true;
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
