package com.yishian.command.utilitytoolbox;

import com.yishian.common.CommonConfigLoad;
import com.yishian.common.CommonPluginEnum;
import com.yishian.common.CommonUtil;
import com.yishian.common.CommonMessageEnum;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XinQi
 */
public class UtilityToolboxCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断执行的指令内容
        if (CommonPluginEnum.PLUGHIN_NAME.getCommand().equalsIgnoreCase(label)) {
            //判断参数长度是否为1 且是否是需要的参数
            if (args.length == 1 && UtilityToolboxEnum.RELOAD_CONFIG_COMMAND.getCommand().equalsIgnoreCase(args[0])) {
                //重载配置文件;
                CommonUtil.javaPlugin.reloadConfig();
                CommonConfigLoad.loadConfig();

                //发送成功信息和打印日志
                String message = CommonMessageEnum.MESSAGE_PREFIX.getMsg() + UtilityToolboxConfigEnum.UTILITYTOOLBOX_APPLY_RELOAD.getMsg();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                //发送控制台日志
                CommonUtil.javaPlugin.getLogger().info(message);
            } else {
                //提示参数错误
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + UtilityToolboxConfigEnum.UTILITYTOOLBOX_COMMAND_ERROR.getMsg()));
            }
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> tips = new ArrayList<>();
        //判断输入的字符是否是插件名称的第一个字符
        if (label.startsWith(CommonPluginEnum.PLUGHIN_NAME.getCommand().substring(0, 1)) && args.length == 0) {
            tips.add(CommonPluginEnum.PLUGHIN_NAME.getCommand());
            return tips;
        }

        //重载指令的提示
        tips.add(UtilityToolboxEnum.RELOAD_CONFIG_COMMAND.getCommand());
        //判断输入的主指令是否是插件名称
        if (CommonPluginEnum.PLUGHIN_NAME.getCommand().equalsIgnoreCase(label)) {
            //判断是否有输入第一个参数，没有就显示所有子指令
            if (StringUtils.isEmpty(args[0])) {
                return tips;
                //判断是否输入了一个参数，提示这个参数的提示
            } else if (args.length == 1) {
                List<String> argNoEmptyTips = new ArrayList<>();
                tips.forEach(tip -> {
                    if (tip.startsWith(args[0])) {
                        argNoEmptyTips.add(tip);
                    }
                });
                return argNoEmptyTips;
            }
        }
        return null;
    }
}
