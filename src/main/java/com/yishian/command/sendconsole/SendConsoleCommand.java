package com.yishian.command.sendconsole;


import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

/**
 * @author XinQi
 */
public class SendConsoleCommand implements CommandExecutor {

    /**
     * 发送控制台指令
     */
    String sendConsoleCommand = SendConsoleEnum.SEND_CONSOLE_COMMAND.getCommand();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件里该指令的消息提示
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
        ConfigurationSection sendConsoleMessage = configurationSection.getConfigurationSection(sendConsoleCommand).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

        //判断指令是否带参数
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + sendConsoleMessage.getString("sendconsole-command-error")));
            return true;
        }

        StringBuilder stringBuilder = new StringBuilder();
        //参数拼接
        for (int i = 0; i < args.length; i++) {
            stringBuilder.append(args[i]);
            if (i != args.length - 1) {
                stringBuilder.append(" ");
            }
        }

        //打包成字符串并发送消息给用户
        String argString = stringBuilder.toString();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + sendConsoleMessage.getString("sendconsole-apply").replaceAll("%command%", argString)));

        //控制台执行指令
        Server server = Bukkit.getServer();
        server.dispatchCommand(server.getConsoleSender(), argString);

        return true;
    }
}
