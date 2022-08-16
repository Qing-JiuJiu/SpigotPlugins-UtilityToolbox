package com.yishian.auxiliary;

import com.yishian.common.ServerUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * @author XinQi
 */
public class FlySpeedCommand implements TabExecutor {

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
        ConfigurationSection configurationSection = ServerUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection("plugin-message").getString("message-prefix");
        ConfigurationSection flySpeedMessage = configurationSection.getConfigurationSection("fly-speed").getConfigurationSection("message");

        //判断执行的指令内容
        if (AuxiliaryCommandEnum.FLY_SPEED_COMMAND.getCommand().equalsIgnoreCase(label)) {
            //判断指令是否带参数，没参数就是重置飞行
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    //默认飞行速度为0.1
                    player.setFlySpeed(0.1F);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + flySpeedMessage.getString("fly-speed-self-reset").replaceAll("%player%", player.getName())));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + flySpeedMessage.getString("fly-speed-console-error")));
                }
                //判断参数数量是否为1
            } else if (args.length == 1) {
                //判断执行的是用户还是控制台
                if (sender instanceof Player) {
                    Float originalFlySpeed = null;
                    //转换参数为浮点型
                    try {
                        originalFlySpeed = Float.valueOf(args[0]);
                    } catch (IllegalArgumentException illegalArgumentException) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + flySpeedMessage.getString("fly-speed-self-args-error")));
                    }
                    //判断数字是否合规
                    BigDecimal bigDecimalOriginalFlySpeed = new BigDecimal(originalFlySpeed);
                    if (bigDecimalOriginalFlySpeed.compareTo(new BigDecimal(1)) < 0 || bigDecimalOriginalFlySpeed.compareTo(new BigDecimal(10)) > 0) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + flySpeedMessage.getString("fly-speed-self-args-error")));
                        return true;
                    }
                    //获得正确速度保留两位小鼠并判断范围是否在1-10 //判断参数是否正确
                    float properFlySpeed = (originalFlySpeed / 10);
                    properFlySpeed = (float) (Math.round(properFlySpeed * 100)) / 100;

                    Player player = (Player) sender;
                    player.setFlySpeed(properFlySpeed);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + flySpeedMessage.getString("fly-speed-self").replaceAll("%fly-speed%", originalFlySpeed.toString())));

                } else {
                    //当只有一个参数，而且是控制台发出则提示参数错误
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + flySpeedMessage.getString("fly-speed-console-error")));
                }
            } else if (args.length == 2) {
                System.out.println("cc");
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + flySpeedMessage.getString("fly-speed-command-error")));
            }
            return true;
        }
        return false;
    }

    /**
     * 指令补全提示
     *
     * @param sender  Source of the command.  For players tab-completing a
     *                command inside of a command block, this will be the player, not
     *                the command block.
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed
     * @return 返回的提示内容
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> tips = new ArrayList<>();
        //判断指令是否是上面执行的指令
        if (AuxiliaryCommandEnum.HEAL_COMMAND.getCommand().equalsIgnoreCase(label)) {
            //判断参数是否为空，是的话就给出全部提示
            if (StringUtils.isEmpty(args[0])) {
                Bukkit.getOnlinePlayers().forEach(player -> tips.add(player.getName()));
                return tips;
                //判断参数数量是否为1，证明输入了内容给出根据输入的参数前缀给出对应的提示
            } else if (args.length == 1) {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    String playerName = player.getName();
                    if (playerName.startsWith(args[0])) {
                        tips.add(playerName);
                    }
                });
                return tips;
            }
        }
        return null;
    }
}
