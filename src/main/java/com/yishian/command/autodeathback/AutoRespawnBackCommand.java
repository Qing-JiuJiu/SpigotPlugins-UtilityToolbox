package com.yishian.command.autodeathback;


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
public class AutoRespawnBackCommand implements CommandExecutor {

    String autoRespawnBackCommand = AutoRespawnBackEnum.AUTO_RESPAWN_BACK_COMMAND.getCommand();

    /**
     * 允许自动死亡返回列表
     */
    public static ArrayList<UUID> autoRespawnBackList = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件里该指令的消息提示
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
        ConfigurationSection autoRespawnBackMessage = configurationSection.getConfigurationSection(autoRespawnBackCommand).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + autoRespawnBackMessage.getString("autorespawnback-console-error")));
            return true;
        }

        Player player = (Player) sender;
        //如果包含该玩家的UUID则移除，否则添加
        if (autoRespawnBackList.contains(player.getUniqueId())) {
            autoRespawnBackList.remove(player.getUniqueId());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + autoRespawnBackMessage.getString("autorespawnback-apply-close")));
        } else {
            autoRespawnBackList.add(player.getUniqueId());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + autoRespawnBackMessage.getString("autorespawnback-apply-open")));
        }
        return true;
    }
}


