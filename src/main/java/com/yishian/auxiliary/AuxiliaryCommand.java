package com.yishian.auxiliary;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XinQi
 */
public class AuxiliaryCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //恢复血量
        if (AuxiliaryCommandEnum.HEAL_COMMAND.getCommand().equalsIgnoreCase(label)) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.hasPermission(AuxiliaryCommandEnum.HEAL_PERMISSION.getCommand())) {
                        //获取最大生命值
                        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                        player.setHealth(maxHealth);
                        player.sendMessage("已治愈生命");
                    } else {
                        player.sendMessage("你没有执行治愈生命指令的权限");
                    }
                    return true;
                } else {
                    sender.sendMessage("控制台请执行治愈他人指令");
                }
            } else if (args.length == 1) {
                //治愈他人生命 未完善
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.hasPermission(AuxiliaryCommandEnum.HEAL_OTHERS_PERMISSION.getCommand())) {
                        String arg = args[0];
                        player.sendMessage("你已治愈他人");
                    } else {
                        player.sendMessage("你没有执行治愈他人生命指令的权限");
                    }
                }

            } else {
                sender.sendMessage("指令格式错误，正确格式：/heal [玩家名称]");
            }
        }

        //恢复自己饱食 未完善
        if (AuxiliaryCommandEnum.FEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission(AuxiliaryCommandEnum.FEED_PERMISSION.getCommand())) {
                    player.setFoodLevel(20);
                    player.sendMessage("已恢复饱食度");
                } else {
                    player.sendMessage("你没有执行恢复饱食度指令的权限");
                }
                return true;
            }
        }

        //恢复全部状态，当有恢复生命值和恢复饱食度权限时可以执行 未完善
        if (AuxiliaryCommandEnum.HEAL_AND_FEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission(AuxiliaryCommandEnum.HEAL_PERMISSION.getCommand()) && player.hasPermission(AuxiliaryCommandEnum.FEED_PERMISSION.getCommand())) {
                    //恢复最大生命值
                    double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                    player.setHealth(maxHealth);
                    player.setFoodLevel(20);
                    player.sendMessage("已治愈生命和恢复饱食度");
                } else {
                    player.sendMessage("你没有执行治愈生命和恢复饱食度的权限");
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> tips = new ArrayList<>();
        if (AuxiliaryCommandEnum.HEAL_COMMAND.getCommand().equalsIgnoreCase(label) || AuxiliaryCommandEnum.FEED_COMMAND.getCommand().equalsIgnoreCase(label) || AuxiliaryCommandEnum.HEAL_AND_FEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
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

