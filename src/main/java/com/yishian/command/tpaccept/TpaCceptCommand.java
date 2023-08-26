package com.yishian.command.tpaccept;

import com.yishian.command.tpa.TpaCommand;
import com.yishian.common.CommonUtil;
import com.yishian.common.CommonMessageEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

/**
 * @author XinQi
 */
public class TpaCceptCommand implements TabExecutor {

    /**
     * 指令设置
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断执行指令是用户还是控制台，控制台直接报错
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + CommonMessageEnum.CONSOLE_COMMAND_NO_USE.getMsg()));
            return true;
        }

        //判断指令是否带参数，不带参数则传送所有玩家，带参数则传送指定玩家
        if (args.length == 0) {
            //判断传送列表是否为空
            Player player = (Player) sender;
            String playerName = player.getName();
            Set<Player> tpaPlayers = TpaCommand.transfeMap.get(player);
            if (CommonUtil.collectionIsEmpty(tpaPlayers)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + TpaCceptConfigEnum.TPACCEPT_NO_TPA_ERROR.getMsg()));
                return true;
            }

            //开始传送所有玩家，传送完后删除传送信息并向对应玩家发送提醒
            tpaPlayers.forEach(tpaPlayer -> {
                tpaPlayer.teleport(player);
                TpaCommand.transfeRecordMap.remove(tpaPlayer);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + TpaCceptConfigEnum.TPACCEPT_APPLY.getMsg()).replaceAll("%others-player%", tpaPlayer.getName()));
                tpaPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + TpaCceptConfigEnum.TPACCEPT_APPLY_OTHERS.getMsg()).replaceAll("%player%", playerName));
            });

            //传送完后删除自己所有传送信息
            tpaPlayers.clear();
            TpaCommand.transfeMap.put(player, tpaPlayers);
        } else {
            //判断传送的名字是否是自己
            Player player = (Player) sender;
            String playerName = player.getName();
            String othersPlayerName = args[0];
            if (playerName.equals(othersPlayerName)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + TpaCceptConfigEnum.TPACCEPT_APPLY_IS_SELF.getMsg()));
                return true;
            }

            //判断玩家是否存在
            Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
            if (othersPlayer == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + TpaCceptConfigEnum.TPACCEPT_OTHERS_NO_EXIST.getMsg()).replaceAll("%others-player%", othersPlayerName));
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

                    //发送相关信息提示
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + TpaCceptConfigEnum.TPACCEPT_APPLY.getMsg()).replaceAll("%others-player%", tpaPlayer.getName()));
                    tpaPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + TpaCceptConfigEnum.TPACCEPT_APPLY_OTHERS.getMsg()).replaceAll("%player%", playerName));

                    return true;
                }
            }

            //到这里还没结束就发送没有待处理的请求
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + TpaCceptConfigEnum.TPACCEPT_NO_OTHERS_PLAYER_TPA_ERROR.getMsg()).replaceAll("%others-player%", othersPlayerName));
        }

        return true;
    }

    /**
     * 指令提示
     *
     * @return 返回的提示内容
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否是上面执行的指令
        if (TpaCceptEnum.TPACCEPT_COMMAND.getCommand().equalsIgnoreCase(label) && sender instanceof Player) {
            return CommonUtil.playerSetToTips(args, TpaCommand.transfeMap.get((Player) sender));
        }
        return null;
    }
}
