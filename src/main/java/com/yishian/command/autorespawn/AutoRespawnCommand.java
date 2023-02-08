package com.yishian.command.autorespawn;


import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author XinQi
 */
public class AutoRespawnCommand implements CommandExecutor {

    /**
     * 允许自动重生列表
     */
    public static ArrayList<UUID> autoRespawnList = new ArrayList<>();
    /**
     * 获取配置文件里该指令的消息提示
     */
    static ConfigurationSection autoRespawnMessage = CommonUtils.ServerConfig.getConfigurationSection(AutoRespawnEnum.AUTO_RESPAWN_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + autoRespawnMessage.getString("autorespawn-console-error")));
            return true;
        }

        Player player = (Player) sender;
        //如果包含该玩家的UUID则移除，否则添加
        if (autoRespawnList.contains(player.getUniqueId())) {
            autoRespawnList.remove(player.getUniqueId());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + autoRespawnMessage.getString("autorespawn-apply-close")));
        } else {
            autoRespawnList.add(player.getUniqueId());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + autoRespawnMessage.getString("autorespawn-apply-open")));
        }
        return true;
    }
}


