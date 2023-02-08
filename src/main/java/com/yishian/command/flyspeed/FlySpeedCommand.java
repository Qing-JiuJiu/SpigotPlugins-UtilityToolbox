package com.yishian.command.flyspeed;

import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

import java.util.List;


/**
 * @author XinQi
 */
public class FlySpeedCommand implements TabExecutor {

    /**
     * 获取配置文件里该指令的消息提示
     */
    static ConfigurationSection flySpeedMessage = CommonUtils.ServerConfig.getConfigurationSection(FlySpeedEnum.FLY_SPEED_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

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
        //如果参数数量大于2个就报指令错误
        if (args.length > 2) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-command-error")));
            return true;
        }

        //判断指令是否带参数，没参数就是重置飞行
        if (args.length == 0) {
            //如果不是玩家
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-console-error")));
                return true;
            }
            Player player = (Player) sender;
            //默认飞行速度为0.1
            player.setFlySpeed(0.1F);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-self-reset").replaceAll("%player%", player.getName())));

            //判断参数数量是否为1
        } else if (args.length == 1) {
            Player othersPlayer = Bukkit.getPlayerExact(args[0]);
            //判断该参数是玩家还是速度
            if (othersPlayer == null) {
                //判断执行的是用户还是控制台
                if (!(sender instanceof Player)) {
                    //当只有一个参数，而且该参数是飞行速度，而且还是是控制台，则提示参数错误
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-console-error")));
                    return true;
                }
                Float originalFlySpeed = checkOriginalFlySpeed(sender, args, flySpeedMessage);
                if (originalFlySpeed == null) {
                    return true;
                }
                //获得正确速度保留两位小数并判断范围是否在1-10
                float properFlySpeed = (originalFlySpeed / 10);
                properFlySpeed = (float) (Math.round(properFlySpeed * 100)) / 100;
                Player player = (Player) sender;
                player.setFlySpeed(properFlySpeed);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-self").replaceAll("%fly-speed%", Float.toString(originalFlySpeed))));
            } else {
                //第一个参数是玩家
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    String playerName = player.getName();
                    String othersPlayerName = args[0];
                    //判断参数指向的是否是自己
                    if (!playerName.equals(othersPlayerName)) {
                        //判断执行修改他人飞行速度指令的玩家权限
                        if (!player.hasPermission(FlySpeedEnum.FLY_SPEED_OTHERS_PERMISSION.getCommand()) && !player.isOp()) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-others-no-permission")));
                            return true;
                        }
                        othersPlayer.setFlySpeed(0.1F);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-others-reset").replaceAll("%others-player%", othersPlayerName)));
                        othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-by-others-reset").replaceAll("%player%", playerName)));

                    } else {
                        //参数指向的是自己，修改自己，并给出对应提示
                        player.setFlySpeed(0.1F);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-others-reset-is-self")));
                    }
                } else {
                    //该指令是控制台发出
                    String othersPlayerName = args[0];
                    othersPlayer.setFlySpeed(0.1F);
                    othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-by-console-reset")));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-others-reset").replaceAll("%others-player%", othersPlayerName)));
                }
            }
        } else {
            Float originalFlySpeed = checkOriginalFlySpeed(sender, args, flySpeedMessage);
            if (originalFlySpeed == null) {
                return true;
            }
            String originalFlySpeedString = Float.toString(originalFlySpeed);
            //获得正确速度保留两位小鼠并判断范围是否在1-10 //判断参数是否正确
            float properFlySpeed = (originalFlySpeed / 10);
            properFlySpeed = (float) (Math.round(properFlySpeed * 100)) / 100;

            //判断执行的是用户还是控制台-----------------------------
            if (sender instanceof Player) {
                Player player = (Player) sender;
                String playerName = player.getName();
                String othersPlayerName = args[1];
                //判断参数指向的是否是自己
                if (!playerName.equals(othersPlayerName)) {
                    //判断执行修改他人飞行速度指令的玩家权限
                    if (!player.hasPermission(FlySpeedEnum.FLY_SPEED_OTHERS_PERMISSION.getCommand()) && !player.isOp()) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-others-no-permission")));
                        return true;
                    }
                    Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
                    //判断玩家是否存在
                    if (othersPlayer == null) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-others-no-exist").replaceAll("%others-player%", othersPlayerName)));
                        return true;
                    }
                    othersPlayer.setFlySpeed(properFlySpeed);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-others").replaceAll("%others-player%", othersPlayerName).replaceAll("%fly-speed%", originalFlySpeedString)));
                    othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-by-others").replaceAll("%player%", playerName).replaceAll("%fly-speed%", originalFlySpeedString)));
                } else {
                    //参数指向的是自己，修改自己，并给出对应提示
                    player.setFlySpeed(properFlySpeed);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-others-is-self").replaceAll("%fly-speed%", originalFlySpeedString)));
                }
            } else {
                String othersPlayerName = args[1];
                Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
                //判断该玩家是否存在
                if (othersPlayer == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-others-no-exist").replaceAll("%others-player%", othersPlayerName)));
                    return true;
                }
                othersPlayer.setFlySpeed(properFlySpeed);
                othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-by-console").replaceAll("%fly-speed%", originalFlySpeedString)));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-others").replaceAll("%others-player%", othersPlayerName).replaceAll("%fly-speed%", originalFlySpeedString)));
            }
        }
        return true;
    }

    /**
     * 用于判断是否数值是否合规校验
     * @return 原始飞行速度
     */
    private static Float checkOriginalFlySpeed(CommandSender sender, String[] args, ConfigurationSection flySpeedMessage) {
        float originalFlySpeed;
        //转换参数为浮点型
        try {
            originalFlySpeed = Float.parseFloat(args[0]);
        } catch (IllegalArgumentException illegalArgumentException) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-self-args-error")));
            return null;
        }
        //判断数字是否合规
        BigDecimal bigDecimalOriginalFlySpeed = new BigDecimal(originalFlySpeed);
        if (bigDecimalOriginalFlySpeed.compareTo(new BigDecimal(0)) < 0 || bigDecimalOriginalFlySpeed.compareTo(new BigDecimal(10)) > 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + flySpeedMessage.getString("fly-speed-self-args-error")));
            return null;
        }
        return originalFlySpeed;
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
        //判断指令是否是上面执行的指令
        if (FlySpeedEnum.FLY_SPEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
            return CommonUtils.arg2CommandPlayerTips(args);
        }
        return null;
    }
}
