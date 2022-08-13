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

/**
 * @author XinQi
 */
public class HealAndFeedCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //恢复全部状态，当有恢复生命值和恢复饱食度权限时可以执行，恢复他人同理
        if (AuxiliaryCommandEnum.HEAL_AND_FEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.hasPermission(AuxiliaryCommandEnum.HEAL_PERMISSION.getCommand()) && player.hasPermission(AuxiliaryCommandEnum.HEAL_PERMISSION.getCommand())) {
                        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                        player.setFoodLevel(20);
                        player.sendMessage("已恢复生命值和饱食度");
                    } else {
                        player.sendMessage("你没有执行恢复生命值和饱食度的权限");
                    }
                } else {
                    sender.sendMessage("控制台请执行恢复他人生命值和饱食度的指令：healandfeed <玩家名称>");
                }
            } else if (args.length == 1) {
                //恢复他人生命和饱食度
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.hasPermission(AuxiliaryCommandEnum.HEAL_OTHERS_PERMISSION.getCommand()) && player.hasPermission(AuxiliaryCommandEnum.FEED_OTHERS_PERMISSION.getCommand())) {
                        String othersPlayerName = args[0];
                        Player othersPlayer = Bukkit.getPlayer(othersPlayerName);
                        othersPlayer.setHealth(othersPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                        othersPlayer.setFoodLevel(20);
                        player.sendMessage("你已恢复" + othersPlayerName + "的生命值和饱食度");
                        othersPlayer.sendMessage("你已被" + player.getName() + "恢复生命值和饱食度");
                    } else {
                        player.sendMessage("你没有执行恢复他人生命值和饱食度指令的权限");
                    }
                } else {
                    String othersPlayerName = args[0];
                    Player othersPlayer = Bukkit.getPlayer(othersPlayerName);
                    othersPlayer.setHealth(othersPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                    othersPlayer.setFoodLevel(20);
                    othersPlayer.sendMessage("你已被控制台恢复生命值和饱食度");
                }
            } else {
                sender.sendMessage("恢复生命值和饱食度指令格式错误，正确格式：/healandfeed [玩家名称]");
            }
            return true;
        }
        return false;
    }

    /**
     * 指令补全提示
     * @param sender Source of the command.  For players tab-completing a
     *     command inside of a command block, this will be the player, not
     *     the command block.
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args The arguments passed to the command, including final
     *     partial argument to be completed
     * @return
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> tips = new ArrayList<>();
        if (AuxiliaryCommandEnum.HEAL_AND_FEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
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
