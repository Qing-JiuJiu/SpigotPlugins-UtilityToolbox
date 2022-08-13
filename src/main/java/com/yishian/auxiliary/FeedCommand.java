package com.yishian.auxiliary;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FeedCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //恢复自己饱食 未完善
        if (AuxiliaryCommandEnum.FEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.hasPermission(AuxiliaryCommandEnum.FEED_PERMISSION.getCommand())) {
                        player.setFoodLevel(20);
                        player.sendMessage("已恢复饱食度");
                    } else {
                        player.sendMessage("你没有执行治愈生命指令的权限");
                    }
                    return true;
                } else {
                    sender.sendMessage("控制台请执行恢复他人饱食度的指令：feed <玩家名称>");
                }
            } else if (args.length == 1) {
                //治愈他人生命
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.hasPermission(AuxiliaryCommandEnum.FEED_OTHERS_PERMISSION.getCommand())) {
                        String othersPlayerName = args[0];
                        Player othersPlayer = Bukkit.getPlayer(othersPlayerName);
                        othersPlayer.setFoodLevel(20);
                        player.sendMessage("你已恢复" + othersPlayerName + "的饱食度");
                        othersPlayer.sendMessage("你已被" + player.getName() + "恢复饱食度");
                    } else {
                        player.sendMessage("你没有执行恢复他人饱食度指令的权限");
                    }
                } else {
                    String othersPlayerName = args[0];
                    Player othersPlayer = Bukkit.getPlayer(othersPlayerName);
                    othersPlayer.setFoodLevel(20);
                    othersPlayer.sendMessage("你已被控制台恢复饱食度");
                }
            } else {
                sender.sendMessage("恢复饱食度指令格式错误，正确格式：/feed [玩家名称]");
            }
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> tips = new ArrayList<>();
        if (AuxiliaryCommandEnum.FEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
            if (StringUtils.isEmpty(args[0])) {
                Bukkit.getOnlinePlayers().forEach(player -> tips.add(player.getName()));
                return tips;
            }
        } else {
            Bukkit.getOnlinePlayers().forEach(player -> {
                String playerName = player.getName();
                if (playerName.startsWith(args[0])) {
                    tips.add(playerName);
                }
            });
            return tips;
        }
        return null;
    }
}
