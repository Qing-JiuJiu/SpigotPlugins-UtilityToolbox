package com.yishian.command.tpaccept;


import com.yishian.command.tpa.TpaCommand;
import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;


/**
 * @author XinQi
 */
public class TpaCceptCommand implements TabExecutor {

    /**
     * 指令
     */
    String tpaCceptCommand = TpaCceptEnum.TPA_CCEPT_COMMAND.getCommand();


    /**
     * 指令设置
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件里该指令的消息提示
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
        ConfigurationSection tpaCceptMessage = configurationSection.getConfigurationSection(tpaCceptCommand).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

        //判断参数是否大于1
        if (args.length > 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaCceptMessage.getString("tpaccept-command-error")));
            return true;
        }

        //判断指令是否带参数
        if (args.length == 0) {
            //判断执行指令是用户还是控制台
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaCceptMessage.getString("tpaccept-console-error")));
                return true;
            }

            //判断传送列表是否为空
            Player player = (Player) sender;
            String playerName = player.getName();
            Set<Player> tpaPlayers = TpaCommand.transfeMap.get(player);
            if (PluginUtils.collectionIsEmpty(tpaPlayers)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaCceptMessage.getString("tpaccept-no-tpa-error")));
                return true;
            }

            //开始传送所有玩家，传送完后删除传送信息并向对应玩家发送提醒
            tpaPlayers.forEach(tpaPlayer -> {
                tpaPlayer.teleport(player);
                TpaCommand.transfeRecordMap.remove(tpaPlayer);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaCceptMessage.getString("tpaccept-apply")).replaceAll("%others-player%", tpaPlayer.getName()));
                tpaPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaCceptMessage.getString("tpaccept-apply-others")).replaceAll("%player%", playerName));
            });

            //传送完后删除自己所有传送信息
            tpaPlayers.clear();
            TpaCommand.transfeMap.put(player, tpaPlayers);

            //判断参数数量是否为1，参数为1为指定同意玩家
        } else {
            //判断执行的是用户还是控制台
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaCceptMessage.getString("tpaccept-console-error")));
                return true;
            }

            //判断传送的名字是否是自己
            Player player = (Player) sender;
            String playerName = player.getName();
            String othersPlayerName = args[0];
            if (playerName.equals(othersPlayerName)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaCceptMessage.getString("tpaccept-apply-is-self")));
                return true;
            }

            //判断玩家是否存在
            Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
            if (othersPlayer == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaCceptMessage.getString("tpaccept-others-no-exist").replaceAll("%others-player%", othersPlayerName)));
                return true;
            }

            //传送指定玩家
            Set<Player> tpaPlayers = TpaCommand.transfeMap.get(player);
            for (Player tpaPlayer : tpaPlayers) {
                if (tpaPlayer == othersPlayer) {
                    //传送玩家
                    tpaPlayer.teleport(player);
                    //删除对应传送信息
                    TpaCommand.transfeRecordMap.remove(tpaPlayer);
                    tpaPlayers.remove(tpaPlayer);
                    TpaCommand.transfeMap.put(player, tpaPlayers);
                    //发送相关信息提示并结束
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaCceptMessage.getString("tpaccept-apply")).replaceAll("%others-player%", tpaPlayer.getName()));
                    tpaPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaCceptMessage.getString("tpaccept-apply-others")).replaceAll("%player%", playerName));
                    return true;
                }
            }
            //到这里还没结束就发送没有待处理的请求
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaCceptMessage.getString("tpaccept-no-others-player-tpa-error")).replaceAll("%others-player%", othersPlayerName));
        }
        return true;
    }

    /**
     * 指令补全提示
     *
     * @return 返回的提示内容
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否是上面执行的指令
        if (tpaCceptCommand.equalsIgnoreCase(label) && sender instanceof Player) {
            return PluginUtils.playerSetToTips(args, TpaCommand.transfeMap.get((Player) sender));
        }
        return null;
    }
}
