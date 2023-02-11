package com.yishian.command.autorespawn;

import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

/**
 * @author XinQi
 */
public class AutoRespawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + AutoRespawnConfigEnum.AUTORESPAWN_CONSOLE_ERROR.getMsg()));
            return true;
        }

        //写入家数据文件，用于重启服务器后能读取，也防止内存泄露想象
        YamlConfiguration autoRespawnFileYaml = AutoRespawnConfig.autoRespawnFileYaml;
        String path = sender.getName() + "." + CommonEnum.FUNCTION_IS_ENABLE.getCommand();
        //判断玩家是否写入过记录，没写入过或本身为false则写入true，否则写入false
        if (autoRespawnFileYaml.getBoolean(path)) {
            autoRespawnFileYaml.set(path, false);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + AutoRespawnConfigEnum.AUTORESPAWN_APPLY_CLOSE.getMsg()));
        } else {
            autoRespawnFileYaml.set(path, true);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + AutoRespawnConfigEnum.AUTORESPAWN_APPLY_OPEN.getMsg()));
        }

        //保存记录文件
        CommonUtils.saveYamlConfig(autoRespawnFileYaml, AutoRespawnConfig.file.toPath());
        return true;
    }
}


