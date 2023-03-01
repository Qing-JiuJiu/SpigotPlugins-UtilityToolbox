package com.yishian.command.heal;

import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
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
        //判断参数数量是否大于1，大于1则提示错误
        if (args.length > 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealConfigEnum.HEAL_COMMAND_ERROR.getMsg()));
            return true;
        }

        //判断指令是否带参数
        if (args.length == 0) {
            //判断执行恢复自己指令的是用户还是控制台
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealConfigEnum.HEAL_CONSOLE_ERROR.getMsg()));
                return true;
            }
            Player player = (Player) sender;
            double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            player.setHealth(maxHealth);
            player.setFoodLevel(20);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealConfigEnum.HEAL_SELF.getMsg()).replaceAll("%player%", player.getName()));

            //如果参数不为0，那么参数数量就是1
        } else {
            //判断执行的是用户还是控制台
            if (sender instanceof Player) {
                Player player = (Player) sender;
                String playerName = player.getName();
                String othersPlayerName = args[0];
                //判断参数指向的是否是自己
                if (!playerName.equals(othersPlayerName)) {
                    //判断执行恢复他人指令的玩家权限
                    if (!player.hasPermission(HealEnum.HEAL_OTHERS_PERMISSION.getCommand()) && !player.isOp()) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealConfigEnum.HEAL_OTHERS_NO_PERMISSION.getMsg()));
                        return true;
                    }
                    Player othersPlayer = healOthersPlayer(sender, othersPlayerName);
                    if (othersPlayer == null) {
                        return true;
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealConfigEnum.HEAL_OTHERS.getMsg()).replaceAll("%others-player%", othersPlayerName));
                    othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealConfigEnum.HEAL_BY_OTHERS.getMsg()).replaceAll("%player%", playerName));
                } else {
                    //参数指向的是自己，恢复自己，并给出对应提示
                    double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                    player.setHealth(maxHealth);
                    player.setFoodLevel(20);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealConfigEnum.HEAL_OTHERS_IS_SELF.getMsg()).replaceAll("%player%", player.getName()));
                }
            } else {
                String othersPlayerName = args[0];
                Player othersPlayer = healOthersPlayer(sender, othersPlayerName);
                if (othersPlayer == null) {
                    return true;
                }
                othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealConfigEnum.HEAL_BY_CONSOLE.getMsg()));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealConfigEnum.HEAL_OTHERS.getMsg()).replaceAll("%others-player%", othersPlayerName));
            }
        }
        return true;
    }

    private static Player healOthersPlayer(CommandSender sender, String othersPlayerName) {
        Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
        //判断该玩家是否存在
        if (othersPlayer == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealConfigEnum.HEAL_OTHERS_NO_EXIST.getMsg()).replaceAll("%others-player%", othersPlayerName));
            return null;
        }
        othersPlayer.setHealth(othersPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        othersPlayer.setFoodLevel(20);
        return othersPlayer;
    }

    /**
     * 指令提示
     *
     * @return 返回的提示内容
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否是上面执行的指令
        if (HealEnum.HEAL_COMMAND.getCommand().equalsIgnoreCase(label)) {
            return CommonUtils.arg1CommandPlayerTip(args);
        }
        return null;
    }
}
