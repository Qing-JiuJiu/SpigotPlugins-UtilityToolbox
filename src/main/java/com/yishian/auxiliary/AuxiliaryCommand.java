package com.yishian.auxiliary;

import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author XinQi
 */
public class AuxiliaryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //恢复血量
        if (AuxiliaryCommandEnum.HEAL_COMMAND.getCommand().equalsIgnoreCase(label)) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission(AuxiliaryCommandEnum.HEAL_PERMISSION.getCommand())) {
                    //恢复最大生命值
                    double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                    player.setHealth(maxHealth);
                    player.sendMessage("已治愈生命");
                } else {
                    player.sendMessage("你没有执行治愈生命指令的权限");
                }
                return true;
            }
        }

        //恢复饱食
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

        //恢复全部状态，当有恢复生命值和恢复饱食度权限时可以执行
        if (AuxiliaryCommandEnum.HEALANDFEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
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
}
