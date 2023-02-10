package com.yishian.command.autorespawn;

import com.yishian.common.CommonEnum;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + AutoRespawnConfigEnum.AUTORESPAWN_CONSOLE_ERROR.getMsg()));
            return true;
        }

        Player player = (Player) sender;
        //如果包含该玩家的UUID则移除，否则添加
        if (autoRespawnList.contains(player.getUniqueId())) {
            autoRespawnList.remove(player.getUniqueId());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + AutoRespawnConfigEnum.AUTORESPAWN_APPLY_CLOSE.getMsg()));
        } else {
            autoRespawnList.add(player.getUniqueId());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + AutoRespawnConfigEnum.AUTORESPAWN_APPLY_OPEN.getMsg()));
        }
        return true;
    }
}


