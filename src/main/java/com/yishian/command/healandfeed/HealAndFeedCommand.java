package com.yishian.command.healandfeed;

import com.yishian.command.feed.FeedEnum;
import com.yishian.command.heal.HealEnum;
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
public class HealAndFeedCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //判断参数数量是否大于1
        if (args.length > 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealAndFeedConfigEnum.HEAL_AND_FEED_COMMAND_ERROR.getMsg()));
            return true;
        }

        //判断指令是否带参数
        if (args.length == 0) {
            //判断执行恢复自己指令的是用户还是控制台
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealAndFeedConfigEnum.HEAL_AND_FEED_CONSOLE_ERROR.getMsg()));
                return true;
            }
            Player player = (Player) sender;
            player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            player.setFoodLevel(20);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealAndFeedConfigEnum.HEAL_AND_FEED_SELF.getMsg().toString().replaceAll("%player%", player.getName())));

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
                    if ((!player.hasPermission(HealEnum.HEAL_OTHERS_PERMISSION.getCommand()) && player.hasPermission(FeedEnum.FEED_OTHERS_PERMISSION.getCommand())) && !player.isOp()) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealAndFeedConfigEnum.HEAL_AND_FEED_OTHERS_NO_PERMISSION.getMsg()));
                        return true;
                    }
                    Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
                    //判断玩家是否存在
                    if (othersPlayer == null) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealAndFeedConfigEnum.HEAL_AND_FEED_OTHERS_NO_EXIST.getMsg().toString().replaceAll("%others-player%", othersPlayerName)));
                        return true;
                    }
                    othersPlayer.setHealth(othersPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                    othersPlayer.setFoodLevel(20);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealAndFeedConfigEnum.HEAL_AND_FEED_OTHERS.getMsg().toString().replaceAll("%others-player%", othersPlayerName)));
                    othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealAndFeedConfigEnum.HEAL_AND_FEED_BY_OTHERS.getMsg().toString().replaceAll("%player%", playerName)));
                } else {
                    //参数指向的是自己，恢复自己，并给出对应提示
                    player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                    player.setFoodLevel(20);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealAndFeedConfigEnum.HEAL_AND_FEED_OTHERS_IS_SELF.getMsg().toString().replaceAll("%player%", player.getName())));
                }
            } else {
                String othersPlayerName = args[0];
                Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
                //判断该玩家是否存在
                if (othersPlayer == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealAndFeedConfigEnum.HEAL_AND_FEED_OTHERS_NO_EXIST.getMsg().toString().replaceAll("%others-player%", othersPlayerName)));
                    return true;
                }
                othersPlayer.setHealth(othersPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                othersPlayer.setFoodLevel(20);
                othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealAndFeedConfigEnum.HEAL_AND_FEED_BY_CONSOLE.getMsg()));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + HealAndFeedConfigEnum.HEAL_AND_FEED_OTHERS.getMsg().toString().replaceAll("%others-player%", othersPlayerName)));
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
        if (HealAndFeedEnum.HEAL_AND_FEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
            return CommonUtils.arg1CommandPlayerTip(args);
        }
        return null;
    }
}
