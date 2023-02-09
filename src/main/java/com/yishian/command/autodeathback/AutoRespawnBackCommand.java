package com.yishian.command.autodeathback;

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
public class AutoRespawnBackCommand implements CommandExecutor {

    /**
     * 允许自动死亡返回列表
     */
    public static ArrayList<UUID> autoRespawnBackList = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + AutoRespawnBackConfigEnum.AUTORESPAWNBACK_CONSOLE_ERROR.getMsg()));
            return true;
        }

        Player player = (Player) sender;
        //如果包含该玩家的UUID则移除，否则添加
        if (autoRespawnBackList.contains(player.getUniqueId())) {
            autoRespawnBackList.remove(player.getUniqueId());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + AutoRespawnBackConfigEnum.AUTORESPAWNBACK_APPLY_OPEN.getMsg()));
        } else {
            autoRespawnBackList.add(player.getUniqueId());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + AutoRespawnBackConfigEnum.AUTORESPAWNBACK_APPLY_CLOSE.getMsg()));
        }
        return true;
    }
}


