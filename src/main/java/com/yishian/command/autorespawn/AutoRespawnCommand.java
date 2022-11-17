package com.yishian.command.autorespawn;


import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;
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

    String autoRespawnCommand = AutoRespawnEnum.AUTO_RESPAWN_COMMAND.getCommand();

    /**
     * 允许自动重生列表
     */
    public static ArrayList<UUID> autoRespawnList = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件里该指令的消息提示
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
        ConfigurationSection autoRespawnMessage = configurationSection.getConfigurationSection(autoRespawnCommand).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + autoRespawnMessage.getString("autorespawn-console-error")));
            return true;
        }

        Player player = (Player) sender;
        //如果包含该玩家的UUID则移除，否则添加
        if (autoRespawnList.contains(player.getUniqueId())) {
            autoRespawnList.remove(player.getUniqueId());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + autoRespawnMessage.getString("autorespawn-apply-close")));
        } else {
            autoRespawnList.add(player.getUniqueId());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + autoRespawnMessage.getString("autorespawn-apply-open")));
        }
        return true;
    }
}


