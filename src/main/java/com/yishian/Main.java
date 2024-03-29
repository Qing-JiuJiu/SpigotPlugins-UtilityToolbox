package com.yishian;

import com.yishian.command.rebirthinplace.RebirthInPlaceCommand;
import com.yishian.command.rebirthinplace.RebirthInPlaceConfig;
import com.yishian.command.rebirthinplace.RebirthInPlaceEnum;
import com.yishian.command.rebirthinplace.RebirthInPlaceListener;
import com.yishian.command.autorespawn.AutoRespawnCommand;
import com.yishian.command.autorespawn.AutoRespawnConfig;
import com.yishian.command.autorespawn.AutoRespawnEnum;
import com.yishian.command.autorespawn.AutoRespawnListener;
import com.yishian.command.back.BackCommand;
import com.yishian.command.back.BackConfig;
import com.yishian.command.back.BackEnum;
import com.yishian.command.back.BackListener;
import com.yishian.command.copyres.CopyResCommand;
import com.yishian.command.copyres.CopyResEnum;
import com.yishian.command.fly.FlyCommand;
import com.yishian.command.fly.FlyEnum;
import com.yishian.command.flyspeed.FlySpeedCommand;
import com.yishian.command.flyspeed.FlySpeedEnum;
import com.yishian.command.heal.HealCommand;
import com.yishian.command.heal.HealEnum;
import com.yishian.command.home.HomeCommand;
import com.yishian.command.home.HomeEnum;
import com.yishian.command.killself.KillSelfCommand;
import com.yishian.command.killself.KillSelfEnum;
import com.yishian.command.musterplayer.MusterPlayerCommand;
import com.yishian.command.musterplayer.MusterPlayerEnum;
import com.yishian.command.musterplayer.MusterPlayerListener;
import com.yishian.command.gm.GmCommand;
import com.yishian.command.gm.GmModeEnum;
import com.yishian.command.sendconsole.SendConsoleCommand;
import com.yishian.command.sendconsole.SendConsoleEnum;
import com.yishian.command.sethome.SetHomeCommand;
import com.yishian.command.sethome.SetHomeConfig;
import com.yishian.command.sethome.SetHomeEnum;
import com.yishian.command.settpp.SetTppCommand;
import com.yishian.command.settpp.SetTppConfig;
import com.yishian.command.settpp.SetTppEnum;
import com.yishian.command.showtextcolor.ShowTextCodeCommand;
import com.yishian.command.showtextcolor.ShowTextCodeEnum;
import com.yishian.command.tpp.TppCommand;
import com.yishian.command.tpp.TppEnum;
import com.yishian.command.teleport.TeleportCommand;
import com.yishian.command.teleport.TeleportEnum;
import com.yishian.command.teleport.TeleportListener;
import com.yishian.command.tpa.TpaCommand;
import com.yishian.command.tpa.TpaEnum;
import com.yishian.command.tpa.TpaPlayerLeaveServerListener;
import com.yishian.command.tpacancel.TpaCancelCommand;
import com.yishian.command.tpacancel.TpaCancelEnum;
import com.yishian.command.tpaccept.TpaCceptCommand;
import com.yishian.command.tpaccept.TpaCceptEnum;
import com.yishian.command.tpadeny.TpaDenyCommand;
import com.yishian.command.tpadeny.TpaDenyEnum;
import com.yishian.command.tpr.TprCommand;
import com.yishian.command.tpr.TprEnum;
import com.yishian.command.utilitytoolbox.UtilityToolboxCommand;
import com.yishian.command.utilitytoolbox.UtilityToolboxEnum;
import com.yishian.command.walkspeed.WalkSpeedCommand;
import com.yishian.command.walkspeed.WalkSpeedEnum;
import com.yishian.common.CommonConfigLoad;
import com.yishian.common.CommonPluginEnum;
import com.yishian.common.CommonUtil;
import com.yishian.function.antihighfrequencyredstone.AntiHighFrequencyRedStoneConfigEnum;
import com.yishian.function.antihighfrequencyredstone.AntiHighFrequencyRedStoneListener;
import com.yishian.function.antihighfrequencyredstone.AntiHighFrequencyRedStoneRunnable;
import com.yishian.function.autosendservermessage.AutoSendServerMessageConfigEnum;
import com.yishian.function.autosendservermessage.AutoSendServerMessageRunnable;
import com.yishian.function.customjoinandleave.CustomJoinAndLeaveConfigEnum;
import com.yishian.function.customjoinandleave.CustomJoinAndLeaveListener;
import com.yishian.function.joinserverwelcome.JoinServerWelcomeConfigEnum;
import com.yishian.function.joinserverwelcome.JoinServerWelcomeListener;
import com.yishian.function.limithighaltitudefluids.LimitHighAltitudeFluidConfigEnum;
import com.yishian.function.limithighaltitudefluids.LimitHighAltitudeFluidListener;
import com.yishian.function.preventhighfrequencyattacks.PreventHighFrequencyAttacksConfigEnum;
import com.yishian.function.preventhighfrequencyattacks.PreventHighFrequencyAttacksListener;
import com.yishian.function.preventhighfrequencyattacks.PreventHighFrequencyAttacksRunnable;
import com.yishian.function.serverlistdisplaymodification.ServerListDisplayModificationConfigEnum;
import com.yishian.function.serverlistdisplaymodification.ServerListDisplayModificationListener;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

/**
 * @author XinQi
 */
public final class Main extends JavaPlugin {
    /**
     * 得到插件管理器
     */
    PluginManager pluginManager = getServer().getPluginManager();
    /**
     * 得到控制台消息
     */
    ConsoleCommandSender consoleSender = getServer().getConsoleSender();
    /**
     * 插件名称
     */
    String messagePrefix;

    /**
     * 这是插件启动时执行的方法
     */
    @Override
    public void onEnable() {
        //检查是否有配置文件
        if (!getDataFolder().exists()) {
            //如果插件目录下没配置文件，保存默认配置文件到插件目录
            saveDefaultConfig();
        } else {
            //直接加载配置文件里的内容
            CommonConfigLoad.loadConfig();
        }

        //保存配置文件后初始化一些跟配置文件相关的变量
        messagePrefix = "&e[" + CommonPluginEnum.PLUGHIN_NAME.getCommand() + "] &7";

        //启动服务器时发送插件消息
        consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "&6欢迎使用UtilityToolbox，插件主页：http://www.utilitytoolbox.cn"));

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
        PluginCommand reloadCommand = getCommand(CommonPluginEnum.PLUGHIN_NAME.getCommand());
        reloadCommand.setPermission(UtilityToolboxEnum.RELOAD_CONFIG_PERMISSION.getCommand());
        reloadCommand.setExecutor(new UtilityToolboxCommand());

        //恢复生命值注册
        PluginCommand healCommand = getCommand(HealEnum.HEAL_COMMAND.getCommand());
        healCommand.setPermission(HealEnum.HEAL_PERMISSION.getCommand());
        healCommand.setExecutor(new HealCommand());

        //开关飞行
        PluginCommand flyCommand = getCommand(FlyEnum.FLY_COMMAND.getCommand());
        flyCommand.setPermission(FlyEnum.FLY_PERMISSION.getCommand());
        flyCommand.setExecutor(new FlyCommand());

        //修改飞行速度
        PluginCommand flySpeedCommand = getCommand(FlySpeedEnum.FLY_SPEED_COMMAND.getCommand());
        flySpeedCommand.setPermission(FlySpeedEnum.FLY_SPEED_PERMISSION.getCommand());
        flySpeedCommand.setExecutor(new FlySpeedCommand());

        //修改移动速度
        PluginCommand walkSpeedCommand = getCommand(WalkSpeedEnum.WALKSPEED_COMMAND.getCommand());
        walkSpeedCommand.setPermission(WalkSpeedEnum.WALKSPEED_PERMISSION.getCommand());
        walkSpeedCommand.setExecutor(new WalkSpeedCommand());

        //申请传送至该玩家位置
        PluginCommand tpaCommand = getCommand(TpaEnum.TPA_COMMAND.getCommand());
        tpaCommand.setPermission(TpaEnum.TPA_PERMISSION.getCommand());
        tpaCommand.setExecutor(new TpaCommand());

        //取消申请传送
        PluginCommand tpaCancelCommand = getCommand(TpaCancelEnum.TPACANCEL_COMMAND.getCommand());
        tpaCancelCommand.setPermission(TpaEnum.TPA_PERMISSION.getCommand());
        tpaCancelCommand.setExecutor(new TpaCancelCommand());

        //同意申请传送
        PluginCommand tpaCceptCommand = getCommand(TpaCceptEnum.TPACCEPT_COMMAND.getCommand());
        tpaCceptCommand.setPermission(TpaEnum.TPA_PERMISSION.getCommand());
        tpaCceptCommand.setExecutor(new TpaCceptCommand());

        //拒绝申请传送
        PluginCommand tpaDenyCommand = getCommand(TpaDenyEnum.TPADENY_COMMAND.getCommand());
        tpaDenyCommand.setPermission(TpaEnum.TPA_PERMISSION.getCommand());
        tpaDenyCommand.setExecutor(new TpaDenyCommand());

        //随机传送
        PluginCommand tprCommand = getCommand(TprEnum.TPR_COMMAND.getCommand());
        tprCommand.setPermission(TprEnum.TPR_PERMISSION.getCommand());
        tprCommand.setExecutor(new TprCommand());

        //设置家
        PluginCommand setHomeCommand = getCommand(SetHomeEnum.SET_HOME_COMMAND.getCommand());
        setHomeCommand.setPermission(HomeEnum.HOME_PERMISSION.getCommand());
        setHomeCommand.setExecutor(new SetHomeCommand());

        //回家
        PluginCommand homeCommand = getCommand(HomeEnum.HOME_COMMAND.getCommand());
        homeCommand.setPermission(HomeEnum.HOME_PERMISSION.getCommand());
        homeCommand.setExecutor(new HomeCommand());

        //展示文本代码
        PluginCommand showTextCodeCommand = getCommand(ShowTextCodeEnum.SHOW_TEXT_CODE_COMMAND.getCommand());
        showTextCodeCommand.setPermission(ShowTextCodeEnum.SHOW_TEXT_CODE_PERMISSION.getCommand());
        showTextCodeCommand.setExecutor(new ShowTextCodeCommand());

        //自杀
        PluginCommand killSelfCommand = getCommand(KillSelfEnum.KILLSELF_COMMAND.getCommand());
        killSelfCommand.setPermission(KillSelfEnum.KILLSELF_PERMISSION.getCommand());
        killSelfCommand.setExecutor(new KillSelfCommand());

        //返回
        PluginCommand backCommand = getCommand(BackEnum.BACK_COMMAND.getCommand());
        backCommand.setPermission(BackEnum.BACK_PERMISSION.getCommand());
        backCommand.setExecutor(new BackCommand());

        //切换是否允许传送
        PluginCommand teleportCommand = getCommand(TeleportEnum.TELEPORT_COMMAND.getCommand());
        teleportCommand.setPermission(TeleportEnum.TELEPORT_PERMISSION.getCommand());
        teleportCommand.setExecutor(new TeleportCommand());

        //复制物品
        PluginCommand copyResCommand = getCommand(CopyResEnum.COPY_RES_COMMAND.getCommand());
        copyResCommand.setPermission(CopyResEnum.COPY_RES_PERMISSION.getCommand());
        copyResCommand.setExecutor(new CopyResCommand());

        //模式切换
        PluginCommand gMCommand = getCommand(GmModeEnum.GM_COMMAND.getCommand());
        gMCommand.setPermission(GmModeEnum.GM_PERMISSION.getCommand());
        gMCommand.setExecutor(new GmCommand());

        //设置临时传送点
        PluginCommand setTpPCommand = getCommand(SetTppEnum.SETTPP_COMMAND.getCommand());
        setTpPCommand.setPermission(TppEnum.TPP_PERMISSION.getCommand());
        setTpPCommand.setExecutor(new SetTppCommand());

        //回临时传送点
        PluginCommand snapTpCommand = getCommand(TppEnum.TPP_COMMAND.getCommand());
        snapTpCommand.setPermission(TppEnum.TPP_PERMISSION.getCommand());
        snapTpCommand.setExecutor(new TppCommand());

        //召集玩家
        PluginCommand musterPlayerCommand = getCommand(MusterPlayerEnum.MUSTER_PLAYER_COMMAND.getCommand());
        musterPlayerCommand.setPermission(MusterPlayerEnum.MUSTER_PLAYER_PERMISSION.getCommand());
        musterPlayerCommand.setExecutor(new MusterPlayerCommand());

        //开关自动死亡回到死亡位置
        PluginCommand autoRespawnBackCommand = getCommand(RebirthInPlaceEnum.REBIRTH_IN_PLACE_COMMAND.getCommand());
        autoRespawnBackCommand.setPermission(RebirthInPlaceEnum.REBIRTH_IN_PLACE_PERMISSION.getCommand());
        autoRespawnBackCommand.setExecutor(new RebirthInPlaceCommand());

        //开关自动重生
        try {
            Class.forName("org.bukkit.Server$Spigot");
            PluginCommand autoRespawnCommand = getCommand(AutoRespawnEnum.AUTORESPAWN_COMMAND.getCommand());
            autoRespawnCommand.setPermission(AutoRespawnEnum.AUTORESPAWN_PERMISSION.getCommand());
            autoRespawnCommand.setExecutor(new AutoRespawnCommand());
        } catch (ClassNotFoundException ignored) {
        }

        //向控制台发送指令
        PluginCommand sendConsoleCommand = getCommand(SendConsoleEnum.SENDCONSOLE_COMMAND.getCommand());
        sendConsoleCommand.setPermission(SendConsoleEnum.SENDCONSOLE_PERMISSION.getCommand());
        sendConsoleCommand.setExecutor(new SendConsoleCommand());
    }

    /**
     * 注册监听器
     */
    public void registerListener() {
        //加入/离开服务器提醒消息
        if ((Boolean) CustomJoinAndLeaveConfigEnum.ENABLE.getMsg()) {
            pluginManager.registerEvents(new CustomJoinAndLeaveListener(), this);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启自定义加入和离开服务器消息"));
        }

        //加入欢迎
        if ((Boolean) JoinServerWelcomeConfigEnum.ENABLE.getMsg()) {
            pluginManager.registerEvents(new JoinServerWelcomeListener(), this);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启加入服务器欢迎"));
        }

        //检测高频红石
        if ((Boolean) AntiHighFrequencyRedStoneConfigEnum.ENABLE.getMsg()) {
            pluginManager.registerEvents(new AntiHighFrequencyRedStoneListener(), this);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启防止高频红石"));
        }

        //检测高空流水
        if ((Boolean) LimitHighAltitudeFluidConfigEnum.ENABLE.getMsg()) {
            pluginManager.registerEvents(new LimitHighAltitudeFluidListener(), this);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启限制高空流体"));
        }

        //离开服务器删除tpa相关信息
        pluginManager.registerEvents(new TpaPlayerLeaveServerListener(), this);

        //玩家Back位置记录
        pluginManager.registerEvents(new BackListener(), this);

        //检测是否允许传送
        pluginManager.registerEvents(new TeleportListener(), this);

        //离开服务器删除临时传送点
        pluginManager.registerEvents(new MusterPlayerListener(), this);

        //设置服务器列表
        if ((Boolean) ServerListDisplayModificationConfigEnum.ENABLE.getMsg()) {
            pluginManager.registerEvents(new ServerListDisplayModificationListener(), this);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启修改服务器列表显示"));
        }

        //限制高频攻击
        if ((Boolean) PreventHighFrequencyAttacksConfigEnum.ENABLE.getMsg()) {
            pluginManager.registerEvents(new PreventHighFrequencyAttacksListener(), this);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启限制高频攻击"));
            CommonUtil.logger.warning("限制高频攻击功能目前还存在缺陷，特殊场景会出现CPS统计异常的情况，建议不要开启此功能");
        }

        //玩家死亡自动回到死亡位置
        pluginManager.registerEvents(new RebirthInPlaceListener(), this);

        //玩家死亡自动重生
        try {
            Class.forName("org.bukkit.Server$Spigot");
            pluginManager.registerEvents(new AutoRespawnListener(), this);
        } catch (ClassNotFoundException ignored) {
            CommonUtil.logger.warning("Bukkit服务器不支持自动重生功能，请使用Spigot服务端及以上服务端");
        }
    }

    /**
     * 注册定时任务
     */
    public void registerTask() {
        //自动发送服务器消息
        if ((Boolean) AutoSendServerMessageConfigEnum.ENABLE.getMsg()) {
            //按照tick时间计算，1tick=0.05s，20tick=1s
            new AutoSendServerMessageRunnable().runTaskTimer(this, 0, (Integer) AutoSendServerMessageConfigEnum.TIME.getMsg() * 20L);
            consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagePrefix + "已开启自动发送服务器消息"));
        }

        //高频红石定时检测
        if ((Boolean) AntiHighFrequencyRedStoneConfigEnum.ENABLE.getMsg()) {
            //按照tick时间计算，1tick=0.05s，20tick=1s
            new AntiHighFrequencyRedStoneRunnable().runTaskTimer(this, 0, (Integer) AntiHighFrequencyRedStoneConfigEnum.TIME.getMsg() * 20L);
        }

        //限制高频攻击定时检测
        if ((Boolean) PreventHighFrequencyAttacksConfigEnum.ENABLE.getMsg()) {
            //按照tick时间计算，1tick=0.05s，20tick=1s
            new PreventHighFrequencyAttacksRunnable().runTaskTimer(this, 0, (Integer) PreventHighFrequencyAttacksConfigEnum.TIME.getMsg() * 20L);
        }
    }

    /**
     * 注册指令配置
     */
    public void registerCommandConfig() {
        //创建各项配置文件
        try {
            //创建home记录文件
            SetHomeConfig.loadHomeConfigFile();
            //创建snap记录文件
            SetTppConfig.loadSnapConfigFile();
            //创建AutoRespawnBack记录文件
            RebirthInPlaceConfig.loadRebirthInPlaceConfigFile();
            //创建AutoRespawn记录文件
            AutoRespawnConfig.loadAutoRespawnConfigFile();
            //创建back记录文件
            BackConfig.loadBackConfigFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
