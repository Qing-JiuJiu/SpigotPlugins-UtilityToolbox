package com.yishian.command.snaptp;

import com.yishian.command.setsnaptp.SetSnapTpConfig;
import com.yishian.command.teleport.TeleportCommand;
import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

/**
 * @author XinQi
 */
public class SnapTpCommand implements CommandExecutor {

    /**
     * 获取配置文件里该指令的消息提示
     */
    ConfigurationSection snapTpMessage = CommonUtils.ServerConfig.getConfigurationSection(SnapTpEnum.SNAP_TP_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否带参数
        if (args.length != 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + snapTpMessage.getString("snaptp-command-error")));
            return true;
        }

        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + snapTpMessage.getString("snaptp-console-error")));
            return true;
        }

        //获取玩家临时传送点的配置信息
        Player player = (Player) sender;
        String playerName = player.getName();
        YamlConfiguration snapFileYaml = SetSnapTpConfig.snapFileYaml;
        ConfigurationSection playerConfig = snapFileYaml.getConfigurationSection(playerName);

        //判断该用户是否有临时传送点
        if (playerConfig == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + snapTpMessage.getString("snaptp-no-exist")));
            return true;
        }

        //传送玩家并发送对应消息
        player.teleport(new Location(Bukkit.getWorld(playerConfig.getString("world")), playerConfig.getDouble("x"), playerConfig.getDouble("y"), playerConfig.getDouble("z"), Float.parseFloat(playerConfig.getString("yaw")), Float.parseFloat(playerConfig.getString("pitch"))));
        if (TeleportCommand.allowTp) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + snapTpMessage.getString("snaptp-apply")));
        }

        return true;
    }
}
