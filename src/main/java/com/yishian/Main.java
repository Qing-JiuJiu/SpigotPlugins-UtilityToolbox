package com.yishian;

import com.yishian.antihighfrequencyredstone.AntiHighFrequencyRedStoneListener;
import com.yishian.antihighfrequencyredstone.AntiHighFrequencyRedStoneRunnable;
import com.yishian.auxiliary.*;
import com.yishian.common.CommandEnum;
import com.yishian.common.CommonCommand;
import com.yishian.customjoinandleave.CustomJoinAndLeaveListener;
import com.yishian.joinserverwelcome.JoinServerWelcomeListener;
import com.yishian.autosendservermessage.AutoSendServerMessageRunnable;
import com.yishian.limithighaltitudewater.LimitHighAltitudeWaterFlowListener;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * @author XinQi
 */
public final class Main extends JavaPlugin {

    /**
     * 得到配置文件
     */
    FileConfiguration config = getConfig();
    /**
     * 得到插件管理器
     */
    PluginManager pluginManager = getServer().getPluginManager();
    /**
     * 得到控制台消息
     */
    ConsoleCommandSender consoleSender = getServer().getConsoleSender();
    /**
     * 得到消息前缀
     */
    String messagePrefix = "&e[" + CommandEnum.PLUGHIN_NAME.getCommand() + "] &7";

    /**
     * 这是插件启动时执行的方法
     */
    @Override
    public void onEnable() {

        //如果插件目录下没配置文件，保存默认配置文件到插件目录
        saveDefaultConfig();

        //启动服务器时发送插件消息
        consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "欢迎使用本插件，插件制作者QQ:592342403"));

        //注册相关功能
        registerCommand();
        registerListener();
        registerTask();
    }

    /**
     * 这是插件停止时执行的方法
     */
    @Override
    public void onDisable() {
    }

    /**
     * 注册指令
     */
    public void registerCommand() {
        //重载配置文件
        PluginCommand reloadCommand = getCommand(CommandEnum.PLUGHIN_NAME.getCommand());
        reloadCommand.setPermission(CommandEnum.RELOAD_CONFIG_PERMISSION.getCommand());
        reloadCommand.setExecutor(new CommonCommand());

        //恢复生命值注册
        PluginCommand healCommand = getCommand(AuxiliaryCommandEnum.HEAL_COMMAND.getCommand());
        healCommand.setPermission(AuxiliaryCommandEnum.HEAL_PERMISSION.getCommand());
        healCommand.setExecutor(new HealCommand());

        //恢复饱食度注册
        PluginCommand feedCommand = getCommand(AuxiliaryCommandEnum.FEED_COMMAND.getCommand());
        feedCommand.setPermission(AuxiliaryCommandEnum.FEED_PERMISSION.getCommand());
        feedCommand.setExecutor(new FeedCommand());

        //恢复生命值和饱食度完全恢复
        PluginCommand healAndFeedCommand = getCommand(AuxiliaryCommandEnum.HEAL_AND_FEED_COMMAND.getCommand());
        healAndFeedCommand.setPermission(AuxiliaryCommandEnum.HEAL_PERMISSION.getCommand());
        healAndFeedCommand.setPermission(AuxiliaryCommandEnum.FEED_PERMISSION.getCommand());
        healAndFeedCommand.setExecutor(new HealAndFeedCommand());

        //开关飞行
        PluginCommand flyCommand = getCommand(AuxiliaryCommandEnum.FLY_COMMAND.getCommand());
        flyCommand.setPermission(AuxiliaryCommandEnum.FLY_PERMISSION.getCommand());
        flyCommand.setExecutor(new FlyCommand());

        //修改飞行速度
        PluginCommand flySpeedCommand = getCommand(AuxiliaryCommandEnum.FLY_SPEED_COMMAND.getCommand());
        flySpeedCommand.setPermission(AuxiliaryCommandEnum.FLY_SPEED_PERMISSION.getCommand());
        flySpeedCommand.setExecutor(new FlySpeedCommand());

        //修改移动速度
        PluginCommand walkSpeedCommand = getCommand(AuxiliaryCommandEnum.WALK_SPEED_COMMAND.getCommand());
        walkSpeedCommand.setPermission(AuxiliaryCommandEnum.WALK_SPEED_PERMISSION.getCommand());
        walkSpeedCommand.setExecutor(new WalkSpeedCommand());
    }

    /**
     * 注册监听器
     */
    public void registerListener() {
        //加入/离开服务器提醒消息
        if (config.getConfigurationSection("join-and-leave-server-message").getBoolean(CommandEnum.FUNCTION_IS_ENABLE.getCommand())) {
            pluginManager.registerEvents(new CustomJoinAndLeaveListener(), this);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启自定义加入和离开服务器消息"));
        }

        //加入欢迎
        if (config.getConfigurationSection("join-server-welcome").getBoolean(CommandEnum.FUNCTION_IS_ENABLE.getCommand())) {
            pluginManager.registerEvents(new JoinServerWelcomeListener(), this);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启加入服务器欢迎"));
        }

        //检测高频红石
        if (config.getConfigurationSection("anti-high-frequency-red-stone").getBoolean(CommandEnum.FUNCTION_IS_ENABLE.getCommand())) {
            pluginManager.registerEvents(new AntiHighFrequencyRedStoneListener(), this);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启防止高频红石"));
        }

        //检测高空流水
        if (config.getConfigurationSection("limit-high-Altitude-water-flow").getBoolean(CommandEnum.FUNCTION_IS_ENABLE.getCommand())) {
            pluginManager.registerEvents(new LimitHighAltitudeWaterFlowListener(), this);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启限制高空流体"));
        }
    }

    /**
     * 注册定时任务
     */
    public void registerTask() {
        //自动发送服务器消息
        ConfigurationSection configurationSection = config.getConfigurationSection("auto-send-server-messages");
        if (configurationSection.getBoolean(CommandEnum.FUNCTION_IS_ENABLE.getCommand())) {
            //按照tick时间计算，1tick=0.05s，20tick=1s
            new AutoSendServerMessageRunnable().runTaskTimer(this, 0, configurationSection.getInt("time") * 20L);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启自动发送服务器消息"));
        }

        //高频红石定时检测
        configurationSection = config.getConfigurationSection("anti-high-frequency-red-stone");
        if (configurationSection.getBoolean(CommandEnum.FUNCTION_IS_ENABLE.getCommand())) {
            //按照tick时间计算，1tick=0.05s，20tick=1s
            new AntiHighFrequencyRedStoneRunnable().runTaskTimer(this, 0, configurationSection.getInt("time") * 20L);
        }
    }
}
