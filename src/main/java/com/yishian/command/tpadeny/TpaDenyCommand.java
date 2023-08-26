package com.yishian.command.tpadeny;

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
public class TpaDenyCommand implements TabExecutor {

    /**
     * 指令设置
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断执行指令是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + CommonMessageEnum.CONSOLE_COMMAND_NO_USE.getMsg()));
            return true;
        }

        //判断指令是否带参数
        if (args.length == 0) {
            //判断传送列表是否为空
            Player player = (Player) sender;
            String playerName = player.getName();
            Set<Player> tpaPlayers = TpaCommand.transfeMap.get(player);
            if (CommonUtil.collectionIsEmpty(tpaPlayers)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + TpaDenyConfigEnum.TPADENY_NO_TPA_ERROR.getMsg()));
                return true;
            }

            //拒绝传送
            tpaPlayers.forEach(tpaPlayer -> {
                TpaCommand.transfeRecordMap.remove(tpaPlayer);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + TpaDenyConfigEnum.TPADENY_APPLY.getMsg()).replaceAll("%others-player%", tpaPlayer.getName()));
                tpaPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + TpaDenyConfigEnum.TPADENY_APPLY_OTHERS.getMsg()).replaceAll("%player%", playerName));
            });

            //传送完后删除自己所有传送信息
            tpaPlayers.clear();
            TpaCommand.transfeMap.put(player, tpaPlayers);

        } else {
            //获得玩家跟传送的玩家名字
            Player player = (Player) sender;
            String playerName = player.getName();
            String othersPlayerName = args[0];

            //判断传送的名字是否是自己
            if (playerName.equals(othersPlayerName)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + TpaDenyConfigEnum.TPADENY_APPLY_IS_SELF.getMsg()));
                return true;
            }

            //判断玩家是否存在
            Player othersPlayer = Bukkit.getPlayerExact(othersPlayerName);
            if (othersPlayer == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + TpaDenyConfigEnum.TPADENY_OTHERS_NO_EXIST.getMsg()).replaceAll("%others-player%", othersPlayerName));
                return true;
            }

            //传送指定玩家
            Set<Player> tpaPlayers = TpaCommand.transfeMap.get(player);
            for (Player tpaPlayer : tpaPlayers) {
                if (tpaPlayer == othersPlayer) {
                    //删除对应传送信息
                    TpaCommand.transfeRecordMap.remove(tpaPlayer);
                    tpaPlayers.remove(tpaPlayer);
                    TpaCommand.transfeMap.put(player, tpaPlayers);

                    //发送相关信息提示
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + TpaDenyConfigEnum.TPADENY_APPLY.getMsg()).replaceAll("%others-player%", tpaPlayer.getName()));
                    tpaPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + TpaDenyConfigEnum.TPADENY_APPLY_OTHERS.getMsg()).replaceAll("%player%", playerName));

                    return true;
                }
            }

            //到这里还没结束就发送没有待处理的请求
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + TpaDenyConfigEnum.TPADENY_NO_OTHERS_PLAYER_TPA_ERROR.getMsg()).replaceAll("%others-player%", othersPlayerName));
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
        if (TpaDenyEnum.TPADENY_COMMAND.getCommand().equalsIgnoreCase(label) && sender instanceof Player) {
            return CommonUtil.playerSetToTips(args, TpaCommand.transfeMap.get((Player) sender));
        }
        return null;
    }
}
