package com.yishian.command.gm;

import com.yishian.common.CommonPluginEnum;
import com.yishian.common.CommonUtil;
import com.yishian.common.CommonMessageEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XinQi
 */
public class GmCommand implements TabExecutor {

    /**
     * 指令提示
     */
    static ArrayList<String> modeList = new ArrayList<>();

    //将需要提示的内容添加进列表
    static {
        modeList.add(GmModeEnum.CREATIVE.getCommand());
        modeList.add(GmModeEnum.SURVIVAL.getCommand());
        modeList.add(GmModeEnum.ADVENTURE.getCommand());
        modeList.add(GmModeEnum.SPECTATOR.getCommand());
    }

    /**
     * 指令设置
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断执行指令是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + CommonMessageEnum.CONSOLE_USE_OFFICIAL_COMMAND_TIPS.getMsg()));
            return true;
        }

        //判断指令是否带参数，否则发送指令格式错误提示
        if (args.length != 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + GmConfigEnum.GM_COMMAND_ERROR.getMsg()));
            return true;
        }
        //得到模式
        String gameModeString = args[0];

        //将所有模式存入列表
        ArrayList<String> modeList = new ArrayList<>();
        ArrayList<String> playHavePermissionList = new ArrayList<>();
        modeList.add(GmModeEnum.CREATIVE.getCommand());
        modeList.add(GmModeEnum.SURVIVAL.getCommand());
        modeList.add(GmModeEnum.ADVENTURE.getCommand());
        modeList.add(GmModeEnum.SPECTATOR.getCommand());

        //判断参数是否正确
        if (!modeList.contains(gameModeString)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + GmConfigEnum.GM_COMMAND_ERROR.getMsg()));
            return true;
        }

        //判断是否有子权限，如果有判断切换的模式是否跟子权限一致，否则报无权限
        Player player = (Player) sender;
        modeList.forEach(mode -> {
            if (player.hasPermission(GmModeEnum.GM_PERMISSION.getCommand() + CommonPluginEnum.POINT + mode)) {
                playHavePermissionList.add(mode);
            }
        });

        //判断玩家切换的模式是否拥有权限，没权限就报没权限的提示
        if (!CommonUtil.collectionIsEmpty(playHavePermissionList) && !playHavePermissionList.contains(gameModeString)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + GmConfigEnum.GM_NO_PERMISSION_ERROR.getMsg().toString().replaceAll("%gamemode%", gameModeString)));
            return true;
        }

        //使用服务器官方指令切换模式
        Server server = Bukkit.getServer();
        server.dispatchCommand(server.getConsoleSender(), "gamemode " + gameModeString + " " + player.getName());

        //发送对应消息
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + GmConfigEnum.GM_APPLY.getMsg()));

        //打印服务器日志
        CommonUtil.logger.info("玩家" + player.getName() + "切换了模式：" + gameModeString);

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
        if (GmModeEnum.GM_COMMAND.getCommand().equalsIgnoreCase(label) && sender instanceof Player) {
            return CommonUtil.tipsListToTips(args, modeList);
        }
        return null;
    }
}
