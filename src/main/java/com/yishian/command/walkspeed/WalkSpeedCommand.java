package com.yishian.command.walkspeed;

import com.yishian.common.CommonUtil;
import com.yishian.common.CommonMessageEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * @author XinQi
 */
public class WalkSpeedCommand implements TabExecutor {

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
        //如果参数为0，直接就是重置自己移动速度，控制台则报错
        if (args.length == 0) {
            //判断执行该指令的是否是玩家
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + WalkSpeedConfigEnum.WALKSPEED_CONSOLE_ERROR.getMsg()));
                return true;
            }

            //重置玩家移动速度
            Player player = (Player) sender;
            //默认移动速度是0.2
            player.setWalkSpeed(0.2F);

            //发送对应消息
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + WalkSpeedConfigEnum.WALKSPEED_SELF_RESET.getMsg()).replaceAll("%player%", player.getName()));

            return true;
        }

        //如果是玩家直接当第一个参数是移动速度，而且是就是设置移动速度，控制台则要继续判断该参数是否为玩家，是该玩家就重置该玩家移动速度，不是玩家则需要第二个参数当作是移动速度
        if (sender instanceof Player) {
            //判断参数1移动速度值是否正确
            Float originalWalkSpeed = checkOriginalWalkSpeed(sender, args);
            if (originalWalkSpeed == null) {
                return true;
            }

            //获得游戏真正需要的移动速度参数
            float properWalkSpeed = (originalWalkSpeed / 10);
            properWalkSpeed = (float) (Math.round(properWalkSpeed * 100)) / 100;

            //设置玩家移动速度
            Player player = (Player) sender;
            player.setWalkSpeed(properWalkSpeed);

            //发送对应消息
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + WalkSpeedConfigEnum.WALKSPEED_SELF.getMsg()).replaceAll("%walk-speed%", Float.toString(originalWalkSpeed)));

            return true;
        }

        //如果控制台指令长度为1，则当作是玩家名字
        if (args.length == 1) {
            //得到该玩家
            String otherPlayerName = args[0];
            Player otherPlayer = Bukkit.getPlayerExact(otherPlayerName);

            //判断用户存不存在
            if (otherPlayer == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + CommonMessageEnum.PLAYER_NO_EXIST.getMsg()).replaceAll("%others-player%", otherPlayerName));
                return true;
            }

            //重置玩家移动速度
            otherPlayer.setWalkSpeed(0.2F);
            otherPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + WalkSpeedConfigEnum.WALKSPEED_BY_CONSOLE_RESET.getMsg()));

            //发送对应消息
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + WalkSpeedConfigEnum.WALKSPEED_OTHERS_RESET.getMsg()).replaceAll("%others-player%", otherPlayerName));

            return true;
        }

        //如果控制台指令长度为2，则1当作是移动速度，2是玩家名字
        //判断参数1移动速度是否正确
        Float originalWalkSpeed = checkOriginalWalkSpeed(sender, args);
        if (originalWalkSpeed == null) {
            return true;
        }

        //得到参数2玩家名字
        String otherPlayerName = args[1];
        Player otherPlayer = Bukkit.getPlayerExact(otherPlayerName);

        //判断用户存不存在
        if (otherPlayer == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + CommonMessageEnum.PLAYER_NO_EXIST.getMsg()).replaceAll("%others-player%", otherPlayerName));
            return true;
        }

        //获得游戏真正需要的移动速度参数
        float properWalkSpeed = (originalWalkSpeed / 10);
        properWalkSpeed = (float) (Math.round(properWalkSpeed * 100)) / 100;

        //设置玩家的移动速度
        otherPlayer.setWalkSpeed(properWalkSpeed);

        //发送对应消息
        String originalWalkSpeedString = originalWalkSpeed.toString();
        otherPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + WalkSpeedConfigEnum.WALKSPEED_BY_CONSOLE.getMsg()).replaceAll("%walk-speed%", originalWalkSpeedString));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + WalkSpeedConfigEnum.WALKSPEED_OTHERS.getMsg()).replaceAll("%others-player%", otherPlayerName).replaceAll("%walk-speed%", originalWalkSpeedString));
        
        return true;
        
    }

    /**
     * 校验原始速度数值是否正确
     *
     * @return 原始速度
     */
    private static Float checkOriginalWalkSpeed(CommandSender sender, String[] args) {
        float originalWalkSpeed;
        //转换参数为浮点型
        try {
            originalWalkSpeed = Float.parseFloat(args[0]);
        } catch (IllegalArgumentException illegalArgumentException) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + WalkSpeedConfigEnum.WALKSPEED_SELF_ARGS_ERROR.getMsg()));
            return null;
        }
        //判断数字是否合规
        BigDecimal bigDecimalOriginalWalkSpeed = new BigDecimal(originalWalkSpeed);
        String maxWalkSpeedString = "10";
        if (bigDecimalOriginalWalkSpeed.compareTo(new BigDecimal(0)) < 0 || bigDecimalOriginalWalkSpeed.compareTo(new BigDecimal(maxWalkSpeedString)) > 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + WalkSpeedConfigEnum.WALKSPEED_SELF_ARGS_ERROR.getMsg()));
            return null;
        }
        return originalWalkSpeed;
    }

    /**
     * 指令提示
     *
     * @param sender  Source of the command.  For players tab-completing a
     *                command inside a command block, this will be the player, not
     *                the command block.
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed
     * @return 返回的提示内容
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        //如果是玩家只提示移动速度
        if (sender instanceof Player) {
            return Collections.singletonList("[0-10]");
        }

        //控制台参数输入到第2个时只提示玩家名字
        if (args.length == 2) {
            return CommonUtil.arg2CommandPlayerTips(args);
        }

        //控制台参数输入到第1个时提示移动速度跟玩家名字
        List<String> tips = CommonUtil.arg1CommandPlayerTip(args);
        tips.add("[0-10]");
        return tips;
    }
}
