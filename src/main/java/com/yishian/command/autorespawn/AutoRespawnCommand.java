package com.yishian.command.autorespawn;

import com.yishian.common.CommonPluginEnum;
import com.yishian.common.CommonUtil;
import com.yishian.common.CommonMessageEnum;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

/**
 * @author XinQi
 * 自动重生指令触发
 */
public class AutoRespawnCommand implements CommandExecutor {

    /**
     * 指令触发事件
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + CommonMessageEnum.CONSOLE_COMMAND_NO_USE.getMsg()));
            return true;
        }

        //写入家数据文件，用于重启服务器后能读取
        YamlConfiguration autoRespawnFileYaml = AutoRespawnConfig.autoRespawnFileYaml;
        String path = sender.getName() + "." + CommonPluginEnum.FUNCTION_IS_ENABLE.getCommand();
        //判断玩家是否写入过记录，没写入过或本身为 false 则写入 true，否则写入 false
        if (autoRespawnFileYaml.getBoolean(path)) {
            autoRespawnFileYaml.set(path, false);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + AutoRespawnConfigEnum.AUTORESPAWN_APPLY_CLOSE.getMsg()));
        } else {
            autoRespawnFileYaml.set(path, true);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonMessageEnum.MESSAGE_PREFIX.getMsg() + AutoRespawnConfigEnum.AUTORESPAWN_APPLY_OPEN.getMsg()));
        }

        //保存记录文件
        CommonUtil.saveYamlConfig(autoRespawnFileYaml, AutoRespawnConfig.file.toPath());

        return true;
    }

}


