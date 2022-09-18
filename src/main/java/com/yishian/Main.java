package com.yishian;

import com.yishian.command.home.HomeCommand;
import com.yishian.command.home.HomeEnum;
import com.yishian.command.killself.KillSelfCommand;
import com.yishian.command.killself.KillSelfEnum;
import com.yishian.command.sethome.SetHomeCommand;
import com.yishian.command.sethome.SetHomeConfig;
import com.yishian.command.sethome.SetHomeEnum;
import com.yishian.command.feed.FeedEnum;
import com.yishian.command.fly.FlyEnum;
import com.yishian.command.flyspeed.FlySpeedEnum;
import com.yishian.command.heal.HealEnum;
import com.yishian.command.healandfeed.HealAndFeedEnum;
import com.yishian.command.showtextcolor.ShowTextCodeCommand;
import com.yishian.command.showtextcolor.ShowTextCodeEnum;
import com.yishian.command.tpa.TpaCommand;
import com.yishian.command.tpa.TpaEnum;
import com.yishian.command.tpa.TpaPlayerLeaveServerListener;
import com.yishian.command.tpacancel.TpaCancelCommand;
import com.yishian.command.tpacancel.TpaCancelEnum;
import com.yishian.command.tpaccept.TpaCceptCommand;
import com.yishian.command.tpaccept.TpaCceptEnum;
import com.yishian.command.tpadeny.TpaDenyCommand;
import com.yishian.command.tpadeny.TpaDenyEnum;
import com.yishian.command.walkspeed.WalkSpeedEnum;
import com.yishian.common.CommonEnum;
import com.yishian.function.antihighfrequencyredstone.AntiHighFrequencyRedStoneListener;
import com.yishian.function.antihighfrequencyredstone.AntiHighFrequencyRedStoneRunnable;
import com.yishian.command.feed.FeedCommand;
import com.yishian.command.fly.FlyCommand;
import com.yishian.command.flyspeed.FlySpeedCommand;
import com.yishian.command.heal.HealCommand;
import com.yishian.command.healandfeed.HealAndFeedCommand;
import com.yishian.command.walkspeed.WalkSpeedCommand;
import com.yishian.common.CommonCommand;
import com.yishian.function.autorespawn.AutoRespawnListener;
import com.yishian.function.customjoinandleave.CustomJoinAndLeaveListener;
import com.yishian.function.joinserverwelcome.JoinServerWelcomeListener;
import com.yishian.function.autosendservermessage.AutoSendServerMessageRunnable;
import com.yishian.function.limithighaltitudewater.LimitHighAltitudeFluidListener;
import com.yishian.function.nodeathdrop.NoDeathDropListener;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;


/**
 * @author XinQi
 */
public final class Main extends JavaPlugin {

    /**
     * 通用字符串
     */
    String timeString = CommonEnum.TIME.getCommand();

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
     * 得到插件名称
     */
    String messagePrefix = "&e[" + CommonEnum.PLUGHIN_NAME.getCommand() + "] &7";

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
        registerCommandConfig();
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
        PluginCommand reloadCommand = getCommand(CommonEnum.PLUGHIN_NAME.getCommand());
        reloadCommand.setPermission(CommonEnum.RELOAD_CONFIG_PERMISSION.getCommand());
        reloadCommand.setExecutor(new CommonCommand());

        //恢复生命值注册
        PluginCommand healCommand = getCommand(HealEnum.HEAL_COMMAND.getCommand());
        healCommand.setPermission(HealEnum.HEAL_PERMISSION.getCommand());
        healCommand.setExecutor(new HealCommand());

        //恢复饱食度注册
        PluginCommand feedCommand = getCommand(FeedEnum.FEED_COMMAND.getCommand());
        feedCommand.setPermission(FeedEnum.FEED_PERMISSION.getCommand());
        feedCommand.setExecutor(new FeedCommand());

        //恢复生命值和饱食度完全恢复
        PluginCommand healAndFeedCommand = getCommand(HealAndFeedEnum.HEAL_AND_FEED_COMMAND.getCommand());
        healAndFeedCommand.setPermission(HealEnum.HEAL_PERMISSION.getCommand());
        healAndFeedCommand.setPermission(FeedEnum.FEED_PERMISSION.getCommand());
        healAndFeedCommand.setExecutor(new HealAndFeedCommand());

        //开关飞行
        PluginCommand flyCommand = getCommand(FlyEnum.FLY_COMMAND.getCommand());
        flyCommand.setPermission(FlyEnum.FLY_PERMISSION.getCommand());
        flyCommand.setExecutor(new FlyCommand());

        //修改飞行速度
        PluginCommand flySpeedCommand = getCommand(FlySpeedEnum.FLY_SPEED_COMMAND.getCommand());
        flySpeedCommand.setPermission(FlySpeedEnum.FLY_SPEED_PERMISSION.getCommand());
        flySpeedCommand.setExecutor(new FlySpeedCommand());

        //修改移动速度
        PluginCommand walkSpeedCommand = getCommand(WalkSpeedEnum.WALK_SPEED_COMMAND.getCommand());
        walkSpeedCommand.setPermission(WalkSpeedEnum.WALK_SPEED_PERMISSION.getCommand());
        walkSpeedCommand.setExecutor(new WalkSpeedCommand());

        //申请传送至该玩家位置
        PluginCommand TpaCommand = getCommand(TpaEnum.TPA_COMMAND.getCommand());
        TpaCommand.setPermission(TpaEnum.TPA_PERMISSION.getCommand());
        TpaCommand.setExecutor(new TpaCommand());

        //取消申请传送
        PluginCommand TpaCancelCommand = getCommand(TpaCancelEnum.TPA_CANCEL_COMMAND.getCommand());
        TpaCancelCommand.setPermission(TpaEnum.TPA_PERMISSION.getCommand());
        TpaCancelCommand.setExecutor(new TpaCancelCommand());

        //同意申请传送
        PluginCommand TpaCceptCommand = getCommand(TpaCceptEnum.TPA_CCEPT_COMMAND.getCommand());
        TpaCceptCommand.setPermission(TpaEnum.TPA_PERMISSION.getCommand());
        TpaCceptCommand.setExecutor(new TpaCceptCommand());

        //拒绝申请传送
        PluginCommand TpaDenyCommand = getCommand(TpaDenyEnum.TPA_DENY_COMMAND.getCommand());
        TpaDenyCommand.setPermission(TpaEnum.TPA_PERMISSION.getCommand());
        TpaDenyCommand.setExecutor(new TpaDenyCommand());

        //设置家
        PluginCommand SetHomeCommand = getCommand(SetHomeEnum.SET_HOME_COMMAND.getCommand());
        SetHomeCommand.setPermission(SetHomeEnum.SET_HOME_PERMISSION.getCommand());
        SetHomeCommand.setExecutor(new SetHomeCommand());

        //回家
        PluginCommand homeCommand = getCommand(HomeEnum.HOME_COMMAND.getCommand());
        homeCommand.setPermission(SetHomeEnum.SET_HOME_PERMISSION.getCommand());
        homeCommand.setExecutor(new HomeCommand());

        //展示文本代码
        PluginCommand showTextCodeCommand = getCommand(ShowTextCodeEnum.SHOW_TEXT_CODE_COMMAND.getCommand());
        showTextCodeCommand.setPermission(ShowTextCodeEnum.SHOW_TEXT_CODE_PERMISSION.getCommand());
        showTextCodeCommand.setExecutor(new ShowTextCodeCommand());

        //自杀
        PluginCommand killSelfCommand = getCommand(KillSelfEnum.KILL_SELF_COMMAND.getCommand());
        killSelfCommand.setPermission(KillSelfEnum.KILL_SELF_PERMISSION.getCommand());
        killSelfCommand.setExecutor(new KillSelfCommand());
    }

    /**
     * 注册监听器
     */
    public void registerListener() {
        //加入/离开服务器提醒消息
        if (config.getConfigurationSection("join-and-leave-server-message").getBoolean(CommonEnum.FUNCTION_IS_ENABLE.getCommand())) {
            pluginManager.registerEvents(new CustomJoinAndLeaveListener(), this);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启自定义加入和离开服务器消息"));
        }

        //加入欢迎
        if (config.getConfigurationSection("join-server-welcome").getBoolean(CommonEnum.FUNCTION_IS_ENABLE.getCommand())) {
            pluginManager.registerEvents(new JoinServerWelcomeListener(), this);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启加入服务器欢迎"));
        }

        //检测高频红石
        if (config.getConfigurationSection("anti-high-frequency-red-stone").getBoolean(CommonEnum.FUNCTION_IS_ENABLE.getCommand())) {
            pluginManager.registerEvents(new AntiHighFrequencyRedStoneListener(), this);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启防止高频红石"));
        }

        //检测高空流水
        if (config.getConfigurationSection("limit-high-altitude-fluid").getBoolean(CommonEnum.FUNCTION_IS_ENABLE.getCommand())) {
            pluginManager.registerEvents(new LimitHighAltitudeFluidListener(), this);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启限制高空流体"));
        }

        //玩家死亡无掉落
        if (config.getConfigurationSection("no-death-drop").getBoolean(CommonEnum.FUNCTION_IS_ENABLE.getCommand())) {
            pluginManager.registerEvents(new NoDeathDropListener(), this);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启玩家无死亡掉落"));
        }

        //玩家死亡自动重生
        if (config.getConfigurationSection("auto-respawn").getBoolean(CommonEnum.FUNCTION_IS_ENABLE.getCommand())) {
            pluginManager.registerEvents(new AutoRespawnListener(), this);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启玩家死亡自动重生"));
        }

        //离开服务器删除tpa相关信息
        pluginManager.registerEvents(new TpaPlayerLeaveServerListener(), this);

    }

    /**
     * 注册定时任务
     */
    public void registerTask() {
        //自动发送服务器消息
        ConfigurationSection configurationSection = config.getConfigurationSection("auto-send-server-messages");
        if (configurationSection.getBoolean(CommonEnum.FUNCTION_IS_ENABLE.getCommand())) {
            //按照tick时间计算，1tick=0.05s，20tick=1s
            new AutoSendServerMessageRunnable().runTaskTimer(this, 0, configurationSection.getInt(timeString) * 20L);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启自动发送服务器消息"));
        }

        //高频红石定时检测
        configurationSection = config.getConfigurationSection("anti-high-frequency-red-stone");
        if (configurationSection.getBoolean(CommonEnum.FUNCTION_IS_ENABLE.getCommand())) {
            //按照tick时间计算，1tick=0.05s，20tick=1s
            new AntiHighFrequencyRedStoneRunnable().runTaskTimer(this, 0, configurationSection.getInt(timeString) * 20L);
        }
    }

    /**
     * 注册指令配置
     */
    public void registerCommandConfig(){
        //创建home配置文件
        try {
            SetHomeConfig.loadHomeConfigFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
