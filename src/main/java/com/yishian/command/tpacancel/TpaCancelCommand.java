package com.yishian.command.tpacancel;

import com.yishian.command.tpa.TpaCommand;
import com.yishian.common.CommonEnum;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * @author XinQi
 */
public class TpaCancelCommand implements CommandExecutor {

    /**
     * 指令设置
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return 返回的提示内容
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断执行指令是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaCancelConfigEnum.TPACANCEL_CONSOLE_ERROR.getMsg()));
            return true;
        }

        //移出传送记录
        Player othersPlayer = TpaCommand.transfeRecordMap.remove(sender);
        if (othersPlayer == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaCancelConfigEnum.TPACANCEL_NO_TPA_ERROR.getMsg()));
            return true;
        }

        //删除对应的传送信息
        Set<Player> playerSet = TpaCommand.transfeMap.get(othersPlayer);
        playerSet.removeIf(player -> player == sender);
        //发送提示信息
        othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaCancelConfigEnum.TPACANCEL_OTHERS.getMsg().toString().replaceAll("%player%", sender.getName())));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + TpaCancelConfigEnum.TPACANCEL_APPLY.getMsg().toString().replaceAll("%others-player%", othersPlayer.getName())));

        return true;
    }
}
