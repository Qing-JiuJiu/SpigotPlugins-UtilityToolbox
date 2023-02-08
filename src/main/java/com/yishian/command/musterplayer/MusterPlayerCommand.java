package com.yishian.command.musterplayer;

import com.yishian.Main;
import com.yishian.command.teleport.TeleportCommand;
import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


/**
 * @author XinQi
 */
public class MusterPlayerCommand implements TabExecutor {
    /**
     * 获取配置文件里该指令的配置信息
     */
    static ConfigurationSection musterPlayerConfig = CommonUtils.ServerConfig.getConfigurationSection(MusterPlayerEnum.MUSTER_PLAYER_COMMAND.getCommand());
    /**
     * 获取配置文件里该指令的消息提示
     */
    static ConfigurationSection musterPlayerMessage = musterPlayerConfig.getConfigurationSection(CommonEnum.MESSAGE.getCommand());
    /**
     * 传送等待时间
     */
    static Integer time = musterPlayerConfig.getInt(CommonEnum.TIME.getCommand());

    /**
     * 此次召集的玩家列表(所有召集列表)
     */
    static ArrayList<Player> musterPlayers = new ArrayList<>();

    /**
     * 还未处理的召集玩家
     */
    protected static ArrayList<Player> notProcessedPlayers = new ArrayList<>();

    /**
     * 记录正在召集的玩家
     */
    protected static Player musterPlayer = null;
    /**
     * 记录召集原因
     */
    String musterReason = null;
    /**
     * 记录召集位置
     */
    static Location musterLocation = null;
    /**
     * 召集玩家同意/拒绝列表
     */
    static HashMap<Player, Boolean> playersMap = new HashMap<>();

    /**
     * 参数提示列表
     */
    static ArrayList<String> tipList = new ArrayList<>();

    static {
        tipList.add("again");
        tipList.add("cancel");
        tipList.add("end");
        tipList.add("deny");
        tipList.add("ccept");
        tipList.add("start");
        tipList.add("list");
    }

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
        //判断指令参数长度是否不为0，否则报指令错误
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-command-error")));
            return true;
        }

        //判断是否是玩家
        boolean isPlayer = sender instanceof Player;

        //获取第一个参数
        String parameter = args[0];

        //判断是否是控制台发出的指令、是的话判断是否是控制台能执行的cancel指令
        if (!isPlayer && !"cancel".equalsIgnoreCase(parameter)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-console-error")));
            return true;
        }

        //如果参数为2个，但却不是start 那就提醒报错
        if (args.length == 2 && !"start".equalsIgnoreCase(parameter)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-command-error")));
            return true;
        }

        //获取玩家名称
        String playerName = sender.getName();

        //如果参数是start
        if ("start".equalsIgnoreCase(parameter)) {
            //判断目前是否有玩家正在召集，否则提示玩家正在召集
            if (musterPlayer != null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-start-repeat").replaceAll("%player%", playerName)));
                return true;
            }

            //判断玩家人数
            Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
            if (onlinePlayers.size() == 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-start-error")));
                return true;
            }

            //正式开始召集
            musterPlayerStart((Player) sender, args, musterPlayerMessage, onlinePlayers, false);
            return true;
        }

        //如果参数是cancel
        if ("cancel".equalsIgnoreCase(parameter)) {
            //判断是否有召集请求取消
            if (musterPlayer == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-error")));
                return true;
            }

            //判断是否是玩家发起者，玩家则是发起者/管理员才能取消，控制台可直接取消
            if (isPlayer) {
                //判断发送指令的人是否是召集发起者/管理员
                if (sender != musterPlayer && !sender.isOp()) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-error")));
                    return true;
                }
                //广播传送者取消的消息
                musterPlayers.forEach(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-cancel-player-others").replaceAll("%player%", playerName))));
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-cancel-consloe")));
                musterPlayers.forEach(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-cancel-consloe"))));
            }

            //清除本次召集信息
            clearMusterMessage();
            return true;
        }

        //如果是end
        if ("end".equalsIgnoreCase(parameter)) {
            //判断是否有召集请求结束
            if (musterPlayer == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-error")));
                return true;
            }

            //判断是否是发起者
            if (musterPlayer != sender) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-error")));
                return true;
            }

            //判断传送时间
            if (time == 0) {
                //发送广播消息
                musterPlayers.forEach(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-end").replaceAll("%player%", musterPlayer.getName()))));

                //传送所有同意的玩家
                playersMap.forEach((judgePlayer, isAllow) -> {
                    if (isAllow) {
                        judgePlayer.teleport(musterLocation);
                    }
                });
                //清除本次召集信息
                clearMusterMessage();
            } else {
                //发送广播消息
                musterPlayers.forEach(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-end-time").replaceAll("%player%", musterPlayer.getName()).replaceAll("%time%", String.valueOf(time)))));

                //延迟传送
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class), () -> {
                    //传送所有同意的玩家
                    playersMap.forEach((judgePlayer, isAllow) -> {
                        if (isAllow) {
                            judgePlayer.teleport(musterLocation);
                        }
                    });
                    //清除本次召集信息
                    clearMusterMessage();
                }, time * 20L);
            }

            return true;
        }

        //如果是ccept
        if ("ccept".equalsIgnoreCase(parameter)) {
            //判断处理列表是否有自己
            if (!notProcessedPlayers.contains(sender)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-error")));
                return true;
            }

            //广播同意信息
            musterPlayers.forEach(musterPlayer -> musterPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-ccept").replaceAll("%player%", sender.getName()))));

            //设置自己的传送信息
            playersMap.put((Player) sender, true);
            notProcessedPlayers.remove(sender);

            //判断是否已经结束了召集
            if (CommonUtils.collectionIsEmpty(notProcessedPlayers)) {
                endMuster(time, musterPlayerMessage);
            }
            return true;
        }

        //如果是deny
        if ("deny".equalsIgnoreCase(parameter)) {
            //判断处理列表是否有自己
            if (!notProcessedPlayers.contains(sender)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-error")));
                return true;
            }

            //广播拒绝信息
            musterPlayers.forEach(musterPlayer -> musterPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-deny").replaceAll("%player%", sender.getName()))));

            //设置自己的传送信息
            playersMap.put((Player) sender, false);
            notProcessedPlayers.remove(sender);

            //判断是否已经结束了召集
            if (CommonUtils.collectionIsEmpty(notProcessedPlayers)) {
                endMuster(time, musterPlayerMessage);
            }
            return true;
        }

        //如果是again
        if ("again".equalsIgnoreCase(parameter)) {
            //判断目前是否有玩家正在召集，否则提示玩家正在召集
            if (musterPlayer != null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-start-repeat").replaceAll("%player%", playerName)));
                return true;
            }

            //判断是否有之前召集的位置
            if (musterLocation == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-again-error")));
                return true;
            }

            //判断玩家数量
            Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
            if (onlinePlayers.size() == 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-start-error")));
                return true;
            }

            //正式开始召集
            musterPlayerStart((Player) sender, args, musterPlayerMessage, onlinePlayers, true);
            return true;
        }

        //如果参数是list
        if ("list".equalsIgnoreCase(parameter)) {
            //判断是否有召集
            if (musterPlayer == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-error")));
                return true;
            }

            //获取已处理的召集玩家列表
            ArrayList<Player> allowPlayers = new ArrayList<>();
            ArrayList<Player> denyPlayers = new ArrayList<>();
            playersMap.forEach((player, isAllow) -> {
                if (isAllow) {
                    allowPlayers.add(player);
                } else {
                    denyPlayers.add(player);
                }
            });

            //已同意召集列表的玩家
            if (!CommonUtils.collectionIsEmpty(allowPlayers)) {
                StringBuilder allowPlayersString = new StringBuilder();
                allowPlayers.forEach(player -> allowPlayersString.append(player.getName()).append(" "));
                allowPlayersString.deleteCharAt(allowPlayersString.lastIndexOf(" "));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-list-allow").replaceAll("%player-list%", allowPlayersString.toString())));
            }

            //已拒绝召集列表的玩家
            if (!CommonUtils.collectionIsEmpty(denyPlayers)) {
                StringBuilder denyPlayersString = new StringBuilder();
                denyPlayers.forEach(player -> denyPlayersString.append(player.getName()).append(" "));
                denyPlayersString.deleteCharAt(denyPlayersString.lastIndexOf(" "));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-list-deny").replaceAll("%player-list%", denyPlayersString.toString())));
            }

            //还未处理召集请求的玩家
            if (!CommonUtils.collectionIsEmpty(notProcessedPlayers)) {
                StringBuilder notProcessedPlayersString = new StringBuilder();
                notProcessedPlayers.forEach(player -> notProcessedPlayersString.append(player.getName()).append(" "));
                notProcessedPlayersString.deleteCharAt(notProcessedPlayersString.lastIndexOf(" "));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-list-deal-with").replaceAll("%player-list%", notProcessedPlayersString.toString())));
            }
            return true;
        }

        //到这还没return发送指令错误信息
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-command-error")));
        return true;
    }

    /**
     * 开始召集
     */
    private void musterPlayerStart(Player player, String[] args, ConfigurationSection musterPlayerMessage, Collection<? extends Player> onlinePlayers, Boolean isAgain) {
        //发送相关召集信息
        if (isAgain) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-again")));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-start")));
        }
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-end-tips")));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-cancel-tips")));

        //设置本次召集信息
        musterPlayer = player;
        musterPlayers.addAll(onlinePlayers);
        notProcessedPlayers.addAll(musterPlayers);
        notProcessedPlayers.remove(player);
        playersMap.put(player, true);

        //广播消息
        String playerName = player.getName();
        //判断是否是重复上一次
        if (isAgain) {
            //重复上一次召集
            if (musterReason != null) {
                sendMusterMessageHaveReason(musterPlayerMessage, playerName);
            } else {
                sendMusterMessageNoReason(musterPlayerMessage, playerName);
            }
        } else {
            //重新发起召集
            musterLocation = player.getLocation();
            if (args.length == 2) {
                musterReason = args[1];
                sendMusterMessageHaveReason(musterPlayerMessage, playerName);
            } else {
                musterReason = null;
                sendMusterMessageNoReason(musterPlayerMessage, playerName);
            }
        }
    }

    /**
     * 发送召集信息没原因
     */
    private void sendMusterMessageNoReason(ConfigurationSection musterPlayerMessage, String playerName) {
        notProcessedPlayers.forEach(onlinePlayer -> {
            onlinePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-start-others").replaceAll("%player%", playerName)));
            onlinePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-accept-tips")));
            onlinePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-deny-tips")));
            onlinePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-others-tips")));
        });
    }

    /**
     * 发送召集信息有原因
     */
    private void sendMusterMessageHaveReason(ConfigurationSection musterPlayerMessage, String playerName) {
        notProcessedPlayers.forEach(onlinePlayer -> {
            onlinePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-start-others-reason").replaceAll("%player%", playerName).replaceAll("%reason%", musterReason)));
            onlinePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-accept-tips")));
            onlinePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-deny-tips")));
            onlinePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-others-tips")));
        });
    }

    /**
     * 结束召集进行传送
     */
    protected static void endMuster(int time, ConfigurationSection musterPlayerMessage) {
        //判断传送时间
        if (time == 0) {
            //发送召集完成信息
            musterPlayers.forEach(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-end"))));

            //传送所有同意的玩家
            playersMap.forEach((judgePlayer, isAllow) -> {
                if (isAllow) {
                    judgePlayer.teleport(musterLocation);
                    if (TeleportCommand.allowTp) {
                        judgePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-end-tp")));
                    }
                }
            });
            //清除此处召集信息
            clearMusterMessage();
        } else {
            //发送广播消息
            musterPlayers.forEach(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-end-time").replaceAll("%player%", musterPlayer.getName()).replaceAll("%time%", String.valueOf(time)))));

            //延迟传送
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getProvidingPlugin(Main.class), () -> {
                //传送所有同意的玩家
                playersMap.forEach((judgePlayer, isAllow) -> {
                    if (isAllow) {
                        judgePlayer.teleport(musterLocation);
                        if (TeleportCommand.allowTp) {
                            judgePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + musterPlayerMessage.getString("musterplayer-apply-end-tp")));
                        }
                    }
                });
                //清除此处召集信息
                clearMusterMessage();
            }, time * 20L);
        }
    }

    /**
     * 清除本次召集信息
     */
    protected static void clearMusterMessage() {
        playersMap.clear();
        musterPlayers.clear();
        notProcessedPlayers.clear();
        musterPlayer = null;
    }

    /**
     * 指令提示
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否是上面执行的指令
        if (MusterPlayerEnum.MUSTER_PLAYER_COMMAND.getCommand().equalsIgnoreCase(label)) {
            return CommonUtils.tipsListToTips(args, tipList);
        }
        return null;
    }
}
