package com.yishian.auxiliary;

import com.yishian.common.PluginUtils;
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
        //获取配置文件里该指令的消息提示
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection("plugin-message").getString("message-prefix");
        ConfigurationSection walkSpeedMessage = configurationSection.getConfigurationSection("walk-speed").getConfigurationSection("message");

        //判断执行的指令内容
        if (AuxiliaryCommandEnum.WALK_SPEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
            //判断指令是否带参数，没参数就是重置移动速度
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    //默认移动速度为0.2
                    player.setWalkSpeed(0.2F);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-self-reset").replaceAll("%player%", player.getName())));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-console-error")));
                }
                //判断参数数量是否为1
            } else if (args.length == 1) {
                Player othersPlayer = Bukkit.getPlayerExact(args[0]);
                //判断该参数是玩家还是速度
                if (othersPlayer == null) {
                    //判断执行的是用户还是控制台
                    if (sender instanceof Player) {
                        float originalWalkSpeed;
                        //转换参数为浮点型
                        try {
                            originalWalkSpeed = Float.parseFloat(args[0]);
                        } catch (IllegalArgumentException illegalArgumentException) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-self-args-error")));
                            return true;
                        }
                        //判断数字是否合规
                        BigDecimal bigDecimalOriginalWalkSpeed = new BigDecimal(originalWalkSpeed);
                        if (bigDecimalOriginalWalkSpeed.compareTo(new BigDecimal(0)) < 0 || bigDecimalOriginalWalkSpeed.compareTo(new BigDecimal(10)) > 0) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-self-args-error")));
                            return true;
                        }
                        //获得正确速度保留两位小鼠并判断范围是否在1-10 //判断参数是否正确
                        float properWalkSpeed = (originalWalkSpeed / 10);
                        properWalkSpeed = (float) (Math.round(properWalkSpeed * 100)) / 100;
                        Player player = (Player) sender;
                        player.setWalkSpeed(properWalkSpeed);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-self").replaceAll("%walk-speed%", Float.toString(originalWalkSpeed))));
                    } else {
                        //当只有一个参数，而且该参数是移动速度，而且还是是控制台，则提示参数错误
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-console-error")));
                    }
                } else {
                    //第一个参数是玩家
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        String playerName = player.getName();
                        String othersPlayerName = args[0];
                        //判断参数指向的是否是自己
                        if (!playerName.equals(othersPlayerName)) {
                            //判断执行修改他人移动速度指令的玩家权限
                            if (player.hasPermission(AuxiliaryCommandEnum.WALK_SPEED_OTHERS_PERMISSION.getCommand())) {
                                othersPlayer.setWalkSpeed(0.2F);
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-others-reset").replaceAll("%others-player%", othersPlayerName)));
                                othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-by-others-reset").replaceAll("%player%", playerName)));
                            } else {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-others-no-permission")));
                            }
                        } else {
                            //参数指向的是自己，修改自己，并给出对应提示
                            player.setWalkSpeed(0.2F);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-others-reset-is-self")));
                        }
                    } else {
                        //该指令是控制台发出
                        String othersPlayerName = args[0];
                        othersPlayer.setWalkSpeed(0.2F);
                        othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-by-console-reset")));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-others-reset").replaceAll("%others-player%", othersPlayerName)));
                    }
                }
            } else if (args.length == 2) {
                float originalWalkSpeed;
                //转换参数为浮点型
                try {
                    originalWalkSpeed = Float.parseFloat(args[0]);
                } catch (IllegalArgumentException illegalArgumentException) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-self-args-error")));
                    return true;
                }
                //判断数字是否合规
                BigDecimal bigDecimalOriginalWalkSpeed = new BigDecimal(originalWalkSpeed);
                if (bigDecimalOriginalWalkSpeed.compareTo(new BigDecimal(0)) < 0 || bigDecimalOriginalWalkSpeed.compareTo(new BigDecimal(10)) > 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-self-args-error")));
                    return true;
                }
                String originalWalkSpeedString = Float.toString(originalWalkSpeed);
                //获得正确速度保留两位小鼠并判断范围是否在1-10 //判断参数是否正确
                float properWalkSpeed = (originalWalkSpeed / 10);
                properWalkSpeed = (float) (Math.round(properWalkSpeed * 100)) / 100;

                //判断执行的是用户还是控制台-----------------------------
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    String playerName = player.getName();
                    String othersPlayerName = args[1];
                    //判断参数指向的是否是自己
                    if (!playerName.equals(othersPlayerName)) {
                        //判断执行修改他人移动速度指令的玩家权限
                        if (player.hasPermission(AuxiliaryCommandEnum.WALK_SPEED_OTHERS_PERMISSION.getCommand())) {
                            Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
                            //判断玩家是否存在
                            if (othersPlayer != null) {
                                othersPlayer.setWalkSpeed(properWalkSpeed);
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-others").replaceAll("%others-player%", othersPlayerName).replaceAll("%walk-speed%", originalWalkSpeedString)));
                                othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-by-others").replaceAll("%player%", playerName).replaceAll("%walk-speed%", originalWalkSpeedString)));
                            } else {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-others-no-exist").replaceAll("%others-player%", othersPlayerName)));
                            }
                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-others-no-permission")));
                        }
                    } else {
                        //参数指向的是自己，修改自己，并给出对应提示
                        player.setWalkSpeed(properWalkSpeed);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-others-is-self").replaceAll("%walk-speed%", originalWalkSpeedString)));
                    }
                } else {
                    String othersPlayerName = args[1];
                    Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
                    //判断该玩家是否存在
                    if (othersPlayer != null) {
                        othersPlayer.setWalkSpeed(properWalkSpeed);
                        othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-by-console").replaceAll("%walk-speed%", originalWalkSpeedString)));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-others").replaceAll("%others-player%", othersPlayerName).replaceAll("%walk-speed%", originalWalkSpeedString)));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-others-no-exist").replaceAll("%others-player%", othersPlayerName)));
                    }
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + walkSpeedMessage.getString("walk-speed-command-error")));
            }
            return true;
        }
        return false;
    }

    /**
     * 指令补全提示
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
        if (AuxiliaryCommandEnum.WALK_SPEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
            return PluginUtils.arg2CommandPlayerTips(args);
        }
        return null;
    }

}