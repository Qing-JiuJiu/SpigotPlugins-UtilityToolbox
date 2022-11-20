package com.yishian.command.playmode;


import com.yishian.common.CommonEnum;
import com.yishian.common.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


/**
 * @author XinQi
 */
public class PlayModeCommand implements TabExecutor {

    /**
     * 指令
     */
    String playModeCommand = PlayModeEnum.PLAY_MODE_COMMAND.getCommand();


    /**
     * 指令设置
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //获取配置文件里该指令的消息提示
        ConfigurationSection configurationSection = PluginUtils.getServerConfig();
        String messagePrefix = configurationSection.getConfigurationSection(CommonEnum.PLUGIN_MESSAGE.getCommand()).getString(CommonEnum.MESSAGE_PREFIX.getCommand());
        ConfigurationSection playModeMessage = configurationSection.getConfigurationSection(playModeCommand).getConfigurationSection(CommonEnum.MESSAGE.getCommand());


        //判断执行指令是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + playModeMessage.getString("playmode-console-error")));
            return true;
        }

        //判断指令是否带参数，否则发送指令格式错误提示
        if (args.length != 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + playModeMessage.getString("playmode-command-error")));
            return true;
        }
        //得到模式
        String gameModeString = args[0];

        //将所有模式存入列表
        ArrayList<String> modeList = new ArrayList<>();
        ArrayList<String> playHavePermissionList = new ArrayList<>();
        modeList.add(PlayModeEnum.CREATIVE.getCommand());
        modeList.add(PlayModeEnum.SURVIVAL.getCommand());
        modeList.add(PlayModeEnum.ADVENTURE.getCommand());
        modeList.add(PlayModeEnum.SPECTATOR.getCommand());

        //判断参数是否正确
        if (!modeList.contains(gameModeString)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + playModeMessage.getString("playmode-command-error")));
            return true;
        }

        //判断是否有子权限，如果有判断切换的模式是否跟子权限一致，否则报无权限
        Player player = (Player) sender;
        modeList.forEach(mode -> {
            if (player.hasPermission(PlayModeEnum.PLAY_MODE_PERMISSION.getCommand() + "." + mode)) {
                playHavePermissionList.add(mode);
            }
        });

        //判断玩家切换的模式是否拥有权限，没权限就报没权限的提示
        if (!PluginUtils.collectionIsEmpty(playHavePermissionList) && !playHavePermissionList.contains(gameModeString)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + playModeMessage.getString("playmode-no-permission-error").replaceAll("%gamemode%", gameModeString)));
            return true;
        }

        //使用服务器官方指令切换模式
        Server server = Bukkit.getServer();
        server.dispatchCommand(server.getConsoleSender(), "gamemode " + gameModeString + " " + player.getName());
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + playModeMessage.getString("playmode-apply")));

        PluginUtils.sendConsoleMessage("玩家" + player.getName() + "切换了模式：" + gameModeString);

        return true;
    }

    /**
     * 指令补全提示
     *
     * @return 返回的提示内容
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        //将所有模式存入列表用于提示
        ArrayList<String> modeList = new ArrayList<>();
        modeList.add(PlayModeEnum.CREATIVE.getCommand());
        modeList.add(PlayModeEnum.SURVIVAL.getCommand());
        modeList.add(PlayModeEnum.ADVENTURE.getCommand());
        modeList.add(PlayModeEnum.SPECTATOR.getCommand());

        //判断指令是否是上面执行的指令
        if (playModeCommand.equalsIgnoreCase(label) && sender instanceof Player) {
            return PluginUtils.tipsListToTips(args, modeList);
        }
        return null;
    }
}
