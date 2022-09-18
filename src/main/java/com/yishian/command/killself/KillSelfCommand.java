package com.yishian.command.killself;


import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class KillSelfCommand implements CommandExecutor {

    String killSelfCommand = KillSelfEnum.KILL_SELF_COMMAND.getCommand();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件里该指令的消息提示
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
        ConfigurationSection homeMessage = configurationSection.getConfigurationSection(killSelfCommand).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

        //判断执行的指令内容
        if (killSelfCommand.equalsIgnoreCase(label)) {
            //判断指令是否带参数
            if (args.length != 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + homeMessage.getString("killself-command-error")));
                return true;
            }

            //判断执行指令的是用户还是控制台
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + homeMessage.getString("killself-console-error")));
                return true;
            }

            Player player = (Player) sender;
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + homeMessage.getString("killself-apply")));
            Server server = Bukkit.getServer();
            server.dispatchCommand(server.getConsoleSender(),"kill "+ player.getName());
            return true;
        }
        return false;
    }
}
