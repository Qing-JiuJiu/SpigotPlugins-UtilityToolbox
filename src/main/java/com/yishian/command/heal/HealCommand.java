package com.yishian.command.heal;

import com.yishian.common.CommonUtil;
import com.yishian.common.CommonMessageEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;


/**
 * @author XinQi
 */
public class HealCommand implements TabExecutor {

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
        //如果是用户执行指令直接恢复自己，不判断参数长度
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            player.setFoodLevel(20);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + HealConfigEnum.HEAL_SELF.getMsg()).replaceAll("%player%", player.getName()));
        } else {
            //控制台需要判断长度，必须长度大于1，否则提示指令使用提示
            if (args.length == 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + HealConfigEnum.HEAL_CONSOLE_ERROR.getMsg()));
                return true;
            } else {
                //得到该用户
                String otherPlayerName = args[0];
                Player otherPlayer = Bukkit.getPlayerExact(otherPlayerName);
                //判断该玩家是否存在
                if (otherPlayer == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + CommonMessageEnum.PLAYER_NO_EXIST.getMsg()).replaceAll("%others-player%", otherPlayerName));
                    return true;
                }
                otherPlayer.setHealth(otherPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                otherPlayer.setFoodLevel(20);
                otherPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + HealConfigEnum.HEAL_BY_CONSOLE.getMsg()));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + HealConfigEnum.HEAL_OTHERS.getMsg()).replaceAll("%others-player%", otherPlayerName));
            }
        }
        return true;
    }

    /**
     * 指令提示
     *
     * @return 返回的提示内容
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否是上面执行的指令
        if (!(sender instanceof Player)) {
            return CommonUtil.arg1CommandPlayerTip(args);
        }
        return null;
    }
}
