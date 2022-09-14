package com.yishian.command.tpa;

import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author XinQi
 */
public class TpaCommand implements TabExecutor {

    /**
     * 指令
     */
    String tpaCommand = TpaEnum.TPA_COMMAND.getCommand();

    /**
     * 用于存储每个玩家自身被别人传送列表信息
     */
    public static HashMap<Player, Set<Player>> transfeMap = new HashMap<>();

    /**
     * 用于记录自己是否有一个传送请求
     */
    public static HashMap<Player, Player> transfeRecordMap = new HashMap<>();

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
        String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
        ConfigurationSection tpaMessage = configurationSection.getConfigurationSection(tpaCommand).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

        //判断执行的指令内容
        if (tpaCommand.equalsIgnoreCase(label)) {
            //判断指令是否带参数
            if (args.length == 0) {
                //判断执行指令是用户还是控制台
                if (sender instanceof Player) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaMessage.getString("tpa-command-error")));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaMessage.getString("tpa-console-error")));
                }
                //判断参数数量是否为1
            } else if (args.length == 1) {
                //判断执行的是用户还是控制台
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    String playerName = player.getName();
                    String othersPlayerName = args[0];
                    //判断传送的名字是否是自己
                    if (!playerName.equals(othersPlayerName)) {
                        Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
                        //判断玩家是否存在
                        if (othersPlayer != null) {
                            //添加传送信息
                            Set<Player> playerSet = transfeMap.get(othersPlayer);
                            //判断该列表是否为空
                            if (playerSet != null){
                                playerSet.add(player);
                            }else{
                                playerSet = new HashSet<>();
                                playerSet.add(player);
                            }
                            //添加至传送列表
                            transfeMap.put(othersPlayer,playerSet);

                            //添加自身传送信息
                            transfeRecordMap.put(player, othersPlayer);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaMessage.getString("tpa-apply").replaceAll("%others-player%", othersPlayerName)));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaMessage.getString("tpa-apply-tpacancel-tips")));

                            othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaMessage.getString("tpa-apply-others").replaceAll("%others-player%", othersPlayerName)));
                            othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaMessage.getString("tpa-apply-accept-tips")));
                            othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaMessage.getString("tpa-apply-deny-tips")));
                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaMessage.getString("tpa-others-no-exist").replaceAll("%others-player%", othersPlayerName)));
                        }
                    }else{
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaMessage.getString("tpa-command-error")));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaMessage.getString("tpa-console-error")));
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaMessage.getString("tpa-command-error")));
            }
            return true;
        }
        return false;
    }

    /**
     * 指令补全提示
     *
     * @return 返回的提示内容
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否是上面执行的指令
        if (tpaCommand.equalsIgnoreCase(label)) {
            return PluginUtils.arg1CommandPlayerTip(args, sender);
        }
        return null;
    }
}
