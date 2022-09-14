package com.yishian.command.tpacancel;

import com.yishian.command.tpa.TpaCommand;
import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Set;


/**
 * @author XinQi
 */
public class TpaCancelCommand implements CommandExecutor {

    /**
     * 指令
     */
    String tpaCancelCommand = TpaCancelEnum.TPA_CANCEL_COMMAND.getCommand();

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
        ConfigurationSection tpaCancelMessage = configurationSection.getConfigurationSection(tpaCancelCommand).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

        //判断执行的指令内容
        if (tpaCancelCommand.equalsIgnoreCase(label)) {
                //判断执行指令是用户还是控制台
                if (sender instanceof Player) {
                    Player othersPlayer = TpaCommand.transfeRecordMap.remove(sender);
                    if (othersPlayer != null){
                        //删除对应的传送信息
                        Set<Player> playerSet = TpaCommand.transfeMap.get(othersPlayer);
                        playerSet.removeIf(player -> player == sender);
                        //发送提示信息
                        othersPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaCancelMessage.getString("tpacancel-others").replaceAll("%player%", sender.getName())));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaCancelMessage.getString("tpacancel-apply").replaceAll("%others-player%", othersPlayer.getName())));
                    }else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaCancelMessage.getString("tpacancel-no-tpa-error")));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + tpaCancelMessage.getString("tpacancel-console-error")));
                }
            }
            return true;
    }
}
