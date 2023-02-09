package com.yishian.command.killself;

import com.yishian.common.CommonConfigLoad;
import com.yishian.common.CommonEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

/**
 * @author XinQi
 */
public class KillSelfCommand implements CommandExecutor {

    /**
     * 获取配置文件里该指令的消息提示
     */
    ConfigurationSection homeMessage = CommonConfigLoad.ServerConfig.getConfigurationSection(KillSelfEnum.KILL_SELF_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否带参数
        if (args.length != 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + homeMessage.getString("killself-command-error")));
            return true;
        }

        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + homeMessage.getString("killself-console-error")));
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + homeMessage.getString("killself-apply")));
        Server server = Bukkit.getServer();
        server.dispatchCommand(server.getConsoleSender(), "kill " + player.getName());
        return true;
    }
}
