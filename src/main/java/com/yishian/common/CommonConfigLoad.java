package com.yishian.common;

import com.yishian.Main;
import com.yishian.command.autodeathback.AutoRespawnBackConfigEnum;
import com.yishian.command.autodeathback.AutoRespawnBackEnum;
import com.yishian.command.autorespawn.AutoRespawnConfigEnum;
import com.yishian.command.autorespawn.AutoRespawnEnum;
import com.yishian.command.back.BackConfigEnum;
import com.yishian.command.back.BackEnum;
import com.yishian.command.copyres.CopyResConfigEnum;
import com.yishian.command.copyres.CopyResEnum;
import com.yishian.command.feed.FeedConfigEnum;
import com.yishian.command.feed.FeedEnum;
import com.yishian.command.fly.FlyConfigEnum;
import com.yishian.command.fly.FlyEnum;
import com.yishian.command.flyspeed.FlySpeedConfigEnum;
import com.yishian.command.flyspeed.FlySpeedEnum;
import com.yishian.command.heal.HealConfigEnum;
import com.yishian.command.heal.HealEnum;
import com.yishian.command.healandfeed.HealAndFeedConfigEnum;
import com.yishian.command.healandfeed.HealAndFeedEnum;
import com.yishian.command.home.HomeConfigEnum;
import com.yishian.command.home.HomeEnum;
import com.yishian.command.killself.KillSelfConfigEnum;
import com.yishian.command.killself.KillSelfEnum;
import com.yishian.command.musterplayer.MusterPlayerConfigEnum;
import com.yishian.command.musterplayer.MusterPlayerEnum;
import com.yishian.command.playmode.PlayModeConfigEnum;
import com.yishian.command.playmode.PlayModeEnum;
import com.yishian.command.sendconsole.SendConsoleConfigEnum;
import com.yishian.command.sendconsole.SendConsoleEnum;
import com.yishian.command.sethome.SetHomeConfigEnum;
import com.yishian.command.sethome.SetHomeEnum;
import com.yishian.command.settpp.SetTppConfigEnum;
import com.yishian.command.settpp.SetTppEnum;
import com.yishian.command.showtextcolor.ShowTextCodeConfigEnum;
import com.yishian.command.showtextcolor.ShowTextCodeEnum;
import com.yishian.command.tpp.TppConfigEnum;
import com.yishian.command.tpp.TppEnum;
import com.yishian.command.teleport.TeleportConfigEnum;
import com.yishian.command.teleport.TeleportEnum;
import com.yishian.command.tpa.TpaConfigEnum;
import com.yishian.command.tpa.TpaEnum;
import com.yishian.command.tpacancel.TpaCancelConfigEnum;
import com.yishian.command.tpacancel.TpaCancelEnum;
import com.yishian.command.tpaccept.TpaCceptConfigEnum;
import com.yishian.command.tpaccept.TpaCceptEnum;
import com.yishian.command.tpadeny.TpaDenyConfigEnum;
import com.yishian.command.tpadeny.TpaDenyEnum;
import com.yishian.command.tpr.TprConfigEnum;
import com.yishian.command.tpr.TprEnum;
import com.yishian.command.utilitytoolbox.UtilityToolboxConfigEnum;
import com.yishian.command.utilitytoolbox.UtilityToolboxEnum;
import com.yishian.command.walkspeed.WalkSpeedConfigEnum;
import com.yishian.command.walkspeed.WalkSpeedEnum;
import com.yishian.function.antihighfrequencyredstone.AntiHighFrequencyRedStoneConfigEnum;
import com.yishian.function.antihighfrequencyredstone.AntiHighFrequencyRedStoneEnum;
import com.yishian.function.autosendservermessage.AutoSendServerMessageConfigEnum;
import com.yishian.function.autosendservermessage.AutoSendServerMessageEnum;
import com.yishian.function.customjoinandleave.CustomJoinAndLeaveConfigEnum;
import com.yishian.function.customjoinandleave.CustomJoinAndLeaveEnum;
import com.yishian.function.joinserverwelcome.JoinServerWelcomeConfigEnum;
import com.yishian.function.joinserverwelcome.JoinServerWelcomeEnum;
import com.yishian.function.limithighaltitudefluids.LimitHighAltitudeFluidConfigEnum;
import com.yishian.function.limithighaltitudefluids.LimitHighAltitudeFluidEnum;
import com.yishian.function.preventhighfrequencyattacks.PreventHighFrequencyAttacksConfigEnum;
import com.yishian.function.preventhighfrequencyattacks.PreventHighFrequencyAttacksEnum;
import com.yishian.function.serverlistdisplaymodification.ServerListDisplayModificationConfigEnum;
import com.yishian.function.serverlistdisplaymodification.ServerListDisplayModificationEnum;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XinQi
 * 该类配置所有指令/功能的配置文件内容检查
 */
public class CommonConfigLoad {

    /**
     * 服务器配置文件
     */
    public static FileConfiguration ServerConfig = Main.getProvidingPlugin(Main.class).getConfig();

    /**
     * 未定义的标签，用于输出警告
     */
    static List<String> undefinedTagList = new ArrayList<>();

    /**
     * 加载所有配置文件，加载完成后输出所有配置文件中未定义的标签
     */
    public static void loadConfig() {
        //重新读取配置文件
        ServerConfig = CommonUtils.javaPlugin.getConfig();

        //读取所有配置文件
        //指令相关
        healConfigLoad();
        autoRespawnBackConfigLoad();
        autoRespawnConfigLoad();
        teleportConfigLoad();
        copyResConfigLoad();
        backConfigLoad();
        feedConfigLoad();
        flyConfigLoad();
        healAndFeedConfigLoad();
        flySpeedConfigLoad();
        walkSpeedConfigLoad();
        homeConfigLoad();
        setHomeConfigLoad();
        killSelfConfigLoad();
        musterPlayerConfigLoad();
        playModeConfigLoad();
        sendConsoleConfigLoad();
        setSnapTpConfigLoad();
        showTextCodeConfigLoad();
        snapTpConfigLoad();
        tpaConfigLoad();
        tpaCancelConfigLoad();
        tpaCceptConfigLoad();
        tpaDenyConfigLoad();
        utilityToolboxConfigLoad();
        tprConfigLoad();

        //功能相关
        antiHighFrequencyRedStoneConfigLoad();
        autoSendServerMessageConfigLoad();
        customJoinAndLeaveConfigLoad();
        joinServerWelcomeConfigLoad();
        limitHighAltitudeFluidConfigLoad();
        preventHighFrequencyAttacksConfigLoad();
        serverListDisplayModificationConfigLoad();

        //输出所有未定义的标签
        if (undefinedTagList.size() > 0) {
            undefinedTagList.forEach(tag -> CommonUtils.javaPlugin.getLogger().warning("配置文件缺少标签或标签值：" + tag));
            CommonUtils.javaPlugin.getLogger().warning("配置文件里缺少标签或标签值，缺少原因是插件版本更新或配置文件误修改导致，缺少的内容将使用默认值。如果需要自定义该内容，请在配置文件中对应位置添加对应内容，内容位置及模板请参考帖子里最新的config.yml。如果从未修改过配置文件，可直接删除插件配置文件重启服务器自动生成最新配置文件或忽略该警告");
            //清空列表
            undefinedTagList.clear();
        }
    }

    /**
     * heal指令的配置加载
     */
    public static void healConfigLoad() {
        //heal消息内容
        ConfigurationSection healConfigMessage = ServerConfig.getConfigurationSection(HealEnum.HEAL_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        HealConfigEnum[] healConfigEnums = HealConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (HealConfigEnum healConfigNodeEnum : healConfigEnums) {
            //得到配置文件标签
            String healConfigTag = healConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            healConfigNodeEnum.setMsg(healConfigMessage.get(healConfigTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!healConfigMessage.contains(healConfigTag, true)) {
                undefinedTagList.add(healConfigTag);
            }
        }

    }

    /**
     * autorespawnback指令的配置加载
     */
    public static void autoRespawnBackConfigLoad() {
        //autoRespawnBack消息内容
        ConfigurationSection autoRespawnBackMessage = ServerConfig.getConfigurationSection(AutoRespawnBackEnum.AUTO_RESPAWN_BACK_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        AutoRespawnBackConfigEnum[] autoRespawnBackConfigEnums = AutoRespawnBackConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (AutoRespawnBackConfigEnum autoRespawnBackConfigNodeEnum : autoRespawnBackConfigEnums) {
            //得到配置文件标签
            String autoRespawnBackConfigNodeEnumTag = autoRespawnBackConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            autoRespawnBackConfigNodeEnum.setMsg(autoRespawnBackMessage.get(autoRespawnBackConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!autoRespawnBackMessage.contains(autoRespawnBackConfigNodeEnumTag, true)) {
                undefinedTagList.add(autoRespawnBackConfigNodeEnumTag);
            }
        }
    }

    /**
     * autorespawn指令的配置加载
     */
    public static void autoRespawnConfigLoad() {
        //autoRespawn消息内容
        ConfigurationSection autoRespawnMessage = ServerConfig.getConfigurationSection(AutoRespawnEnum.AUTO_RESPAWN_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        AutoRespawnConfigEnum[] autoRespawnConfigEnums = AutoRespawnConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (AutoRespawnConfigEnum autoRespawnConfigNodeEnum : autoRespawnConfigEnums) {
            //得到配置文件标签
            String autoRespawnConfigNodeEnumTag = autoRespawnConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            autoRespawnConfigNodeEnum.setMsg(autoRespawnMessage.get(autoRespawnConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!autoRespawnMessage.contains(autoRespawnConfigNodeEnumTag, true)) {
                undefinedTagList.add(autoRespawnConfigNodeEnumTag);
            }
        }
    }

    /**
     * teleport指令的配置加载
     */
    public static void teleportConfigLoad() {
        //teleport消息内容
        ConfigurationSection teleportMessage = ServerConfig.getConfigurationSection(TeleportEnum.TELEPORT_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        TeleportConfigEnum[] teleportConfigEnums = TeleportConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (TeleportConfigEnum teleportConfigNodeEnum : teleportConfigEnums) {
            //得到配置文件标签
            String teleportConfigNodeEnumTag = teleportConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            teleportConfigNodeEnum.setMsg(teleportMessage.get(teleportConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!teleportMessage.contains(teleportConfigNodeEnumTag, true)) {
                undefinedTagList.add(teleportConfigNodeEnumTag);
            }
        }
    }

    /**
     * copyres指令的配置加载
     */
    public static void copyResConfigLoad() {
        //获得copyres指令的配置根目录
        ConfigurationSection copyresConfigurationSection = ServerConfig.getConfigurationSection(CopyResEnum.COPY_RES_COMMAND.getCommand());
        //copyres消息内容
        ConfigurationSection copyresMessage = copyresConfigurationSection.getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        CopyResConfigEnum[] copyresConfigEnums = CopyResConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (CopyResConfigEnum copyresConfigNodeEnum : copyresConfigEnums) {
            //得到配置文件标签
            String copyresConfigNodeEnumTag = copyresConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            if (copyresMessage.get(copyresConfigNodeEnumTag) != null) {
                copyresConfigNodeEnum.setMsg(copyresMessage.get(copyresConfigNodeEnumTag));
            } else {
                copyresConfigNodeEnum.setMsg(copyresConfigurationSection.get(copyresConfigNodeEnumTag));
            }
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!copyresMessage.contains(copyresConfigNodeEnumTag, true) && !copyresConfigurationSection.contains(copyresConfigNodeEnumTag, true)) {
                undefinedTagList.add(copyresConfigNodeEnumTag);
            }
        }
    }

    /**
     * back指令的配置加载
     */
    public static void backConfigLoad() {
        //back消息内容
        ConfigurationSection backMessage = ServerConfig.getConfigurationSection(BackEnum.BACK_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        BackConfigEnum[] backConfigEnums = BackConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (BackConfigEnum backConfigNodeEnum : backConfigEnums) {
            //得到配置文件标签
            String backConfigNodeEnumTag = backConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            backConfigNodeEnum.setMsg(backMessage.get(backConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!backMessage.contains(backConfigNodeEnumTag, true)) {
                undefinedTagList.add(backConfigNodeEnumTag);
            }
        }
    }

    /**
     * feed指令的配置加载
     */
    public static void feedConfigLoad() {
        //feed消息内容
        ConfigurationSection feedMessage = ServerConfig.getConfigurationSection(FeedEnum.FEED_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        FeedConfigEnum[] feedConfigEnums = FeedConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (FeedConfigEnum feedConfigNodeEnum : feedConfigEnums) {
            //得到配置文件标签
            String feedConfigNodeEnumTag = feedConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            feedConfigNodeEnum.setMsg(feedMessage.get(feedConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!feedMessage.contains(feedConfigNodeEnumTag, true)) {
                undefinedTagList.add(feedConfigNodeEnumTag);
            }
        }
    }

    /**
     * fly指令的配置加载
     */
    public static void flyConfigLoad() {
        //fly消息内容
        ConfigurationSection flyMessage = ServerConfig.getConfigurationSection(FlyEnum.FLY_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        FlyConfigEnum[] flyConfigEnums = FlyConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (FlyConfigEnum flyConfigNodeEnum : flyConfigEnums) {
            //得到配置文件标签
            String flyConfigNodeEnumTag = flyConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            flyConfigNodeEnum.setMsg(flyMessage.get(flyConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!flyMessage.contains(flyConfigNodeEnumTag, true)) {
                undefinedTagList.add(flyConfigNodeEnumTag);
            }
        }
    }

    /**
     * healandfeed指令的配置加载
     */
    public static void healAndFeedConfigLoad() {
        //healandfeed消息内容
        ConfigurationSection healAndFeedMessage = ServerConfig.getConfigurationSection(HealAndFeedEnum.HEAL_AND_FEED_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        HealAndFeedConfigEnum[] healAndFeedConfigEnums = HealAndFeedConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (HealAndFeedConfigEnum healAndFeedConfigNodeEnum : healAndFeedConfigEnums) {
            //得到配置文件标签
            String healAndFeedConfigNodeEnumTag = healAndFeedConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            healAndFeedConfigNodeEnum.setMsg(healAndFeedMessage.get(healAndFeedConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!healAndFeedMessage.contains(healAndFeedConfigNodeEnumTag, true)) {
                undefinedTagList.add(healAndFeedConfigNodeEnumTag);
            }
        }
    }

    /**
     * flyspeed指令的配置加载
     */
    public static void flySpeedConfigLoad() {
        //flyspeed消息内容
        ConfigurationSection flySpeedMessage = ServerConfig.getConfigurationSection(FlySpeedEnum.FLY_SPEED_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        FlySpeedConfigEnum[] flySpeedConfigEnums = FlySpeedConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (FlySpeedConfigEnum flySpeedConfigNodeEnum : flySpeedConfigEnums) {
            //得到配置文件标签
            String flySpeedConfigNodeEnumTag = flySpeedConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            flySpeedConfigNodeEnum.setMsg(flySpeedMessage.get(flySpeedConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!flySpeedMessage.contains(flySpeedConfigNodeEnumTag, true)) {
                undefinedTagList.add(flySpeedConfigNodeEnumTag);
            }
        }
    }

    /**
     * walkspeed指令的配置加载
     */
    public static void walkSpeedConfigLoad() {
        //walkspeed消息内容
        ConfigurationSection walkSpeedMessage = ServerConfig.getConfigurationSection(WalkSpeedEnum.WALK_SPEED_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        WalkSpeedConfigEnum[] walkSpeedConfigEnums = WalkSpeedConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (WalkSpeedConfigEnum walkSpeedConfigNodeEnum : walkSpeedConfigEnums) {
            //得到配置文件标签
            String walkSpeedConfigNodeEnumTag = walkSpeedConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            walkSpeedConfigNodeEnum.setMsg(walkSpeedMessage.get(walkSpeedConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!walkSpeedMessage.contains(walkSpeedConfigNodeEnumTag, true)) {
                undefinedTagList.add(walkSpeedConfigNodeEnumTag);
            }
        }
    }

    /**
     * home指令的配置加载
     */
    public static void homeConfigLoad() {
        //home消息内容
        ConfigurationSection homeMessage = ServerConfig.getConfigurationSection(HomeEnum.HOME_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        HomeConfigEnum[] homeConfigEnums = HomeConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (HomeConfigEnum homeConfigNodeEnum : homeConfigEnums) {
            //得到配置文件标签
            String homeConfigNodeEnumTag = homeConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            homeConfigNodeEnum.setMsg(homeMessage.get(homeConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!homeMessage.contains(homeConfigNodeEnumTag, true)) {
                undefinedTagList.add(homeConfigNodeEnumTag);
            }
        }
    }

    /**
     * sethome指令的配置加载
     */
    public static void setHomeConfigLoad() {
        //获得sethome指令的配置根目录
        ConfigurationSection setHomeConfigurationSection = ServerConfig.getConfigurationSection(SetHomeEnum.SET_HOME_COMMAND.getCommand());
        //sethome消息内容
        ConfigurationSection setHomeMessage = setHomeConfigurationSection.getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        SetHomeConfigEnum[] setHomeConfigEnums = SetHomeConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (SetHomeConfigEnum setHomeConfigNodeEnum : setHomeConfigEnums) {
            //得到配置文件标签
            String setHomeConfigNodeEnumTag = setHomeConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            if (setHomeMessage.get(setHomeConfigNodeEnumTag) != null) {
                setHomeConfigNodeEnum.setMsg(setHomeMessage.get(setHomeConfigNodeEnumTag));
            } else {
                setHomeConfigNodeEnum.setMsg(setHomeConfigurationSection.get(setHomeConfigNodeEnumTag));
            }
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!setHomeMessage.contains(setHomeConfigNodeEnumTag, true) && !setHomeConfigurationSection.contains(setHomeConfigNodeEnumTag, true)) {
                undefinedTagList.add(setHomeConfigNodeEnumTag);
            }
        }
    }

    /**
     * killself指令的配置加载
     */
    public static void killSelfConfigLoad() {
        //killself消息内容
        ConfigurationSection killSelfMessage = ServerConfig.getConfigurationSection(KillSelfEnum.KILL_SELF_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        KillSelfConfigEnum[] killSelfConfigEnums = KillSelfConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (KillSelfConfigEnum killSelfConfigNodeEnum : killSelfConfigEnums) {
            //得到配置文件标签
            String killSelfConfigNodeEnumTag = killSelfConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            killSelfConfigNodeEnum.setMsg(killSelfMessage.get(killSelfConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!killSelfMessage.contains(killSelfConfigNodeEnumTag, true)) {
                undefinedTagList.add(killSelfConfigNodeEnumTag);
            }
        }
    }

    /**
     * musterplayer指令的配置加载
     */
    public static void musterPlayerConfigLoad() {
        //获得copyres指令的配置根目录
        ConfigurationSection musterPlayerConfigurationSection = ServerConfig.getConfigurationSection(MusterPlayerEnum.MUSTER_PLAYER_COMMAND.getCommand());
        //musterplayer消息内容
        ConfigurationSection musterPlayerMessage = musterPlayerConfigurationSection.getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        MusterPlayerConfigEnum[] musterPlayerConfigEnums = MusterPlayerConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (MusterPlayerConfigEnum musterPlayerConfigNodeEnum : musterPlayerConfigEnums) {
            //得到配置文件标签
            String musterPlayerConfigNodeEnumTag = musterPlayerConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            if (musterPlayerMessage.get(musterPlayerConfigNodeEnumTag) != null) {
                musterPlayerConfigNodeEnum.setMsg(musterPlayerMessage.get(musterPlayerConfigNodeEnumTag));
            } else {
                musterPlayerConfigNodeEnum.setMsg(musterPlayerConfigurationSection.get(musterPlayerConfigNodeEnumTag));
            }
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!musterPlayerMessage.contains(musterPlayerConfigNodeEnumTag, true) && !musterPlayerConfigurationSection.contains(musterPlayerConfigNodeEnumTag, true)) {
                undefinedTagList.add(musterPlayerConfigNodeEnumTag);
            }
        }
    }

    /**
     * playmode指令的配置加载
     */
    public static void playModeConfigLoad() {
        //playmode消息内容
        ConfigurationSection playModeMessage = ServerConfig.getConfigurationSection(PlayModeEnum.PLAY_MODE_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        PlayModeConfigEnum[] playModeConfigEnums = PlayModeConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (PlayModeConfigEnum playModeConfigNodeEnum : playModeConfigEnums) {
            //得到配置文件标签
            String playModeConfigNodeEnumTag = playModeConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            playModeConfigNodeEnum.setMsg(playModeMessage.get(playModeConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!playModeMessage.contains(playModeConfigNodeEnumTag, true)) {
                undefinedTagList.add(playModeConfigNodeEnumTag);
            }
        }
    }

    /**
     * sendconsole指令的配置加载
     */
    public static void sendConsoleConfigLoad() {
        //sendconsole消息内容
        ConfigurationSection sendConsoleMessage = ServerConfig.getConfigurationSection(SendConsoleEnum.SEND_CONSOLE_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        SendConsoleConfigEnum[] sendConsoleConfigEnums = SendConsoleConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (SendConsoleConfigEnum sendConsoleConfigNodeEnum : sendConsoleConfigEnums) {
            //得到配置文件标签
            String sendConsoleConfigNodeEnumTag = sendConsoleConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            sendConsoleConfigNodeEnum.setMsg(sendConsoleMessage.get(sendConsoleConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!sendConsoleMessage.contains(sendConsoleConfigNodeEnumTag, true)) {
                undefinedTagList.add(sendConsoleConfigNodeEnumTag);
            }
        }
    }

    /**
     * setsnaptp指令的配置加载，已经改成settpp
     */
    public static void setSnapTpConfigLoad() {
        ConfigurationSection setSnapTpConfigurationSection = ServerConfig.getConfigurationSection(SetTppEnum.SET_TPP_COMMAND.getCommand());
        //setsnaptp消息内容
        ConfigurationSection setSnapTpMessage = setSnapTpConfigurationSection.getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        SetTppConfigEnum[] setTppConfigEnums = SetTppConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (SetTppConfigEnum setSnapTpConfigNodeEnum : setTppConfigEnums) {
            //得到配置文件标签
            String setSnapTpConfigNodeEnumTag = setSnapTpConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            if (setSnapTpMessage.get(setSnapTpConfigNodeEnumTag) != null) {
                setSnapTpConfigNodeEnum.setMsg(setSnapTpMessage.get(setSnapTpConfigNodeEnumTag));
            } else {
                setSnapTpConfigNodeEnum.setMsg(setSnapTpConfigurationSection.get(setSnapTpConfigNodeEnumTag));
            }
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!setSnapTpMessage.contains(setSnapTpConfigNodeEnumTag, true) && !setSnapTpConfigurationSection.contains(setSnapTpConfigNodeEnumTag, true)) {
                undefinedTagList.add(setSnapTpConfigNodeEnumTag);
            }
        }
    }

    /**
     * showtextcode指令的配置加载
     */
    public static void showTextCodeConfigLoad() {
        //showtextcode消息内容
        ConfigurationSection showTextCodeMessage = ServerConfig.getConfigurationSection(ShowTextCodeEnum.SHOW_TEXT_CODE_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        ShowTextCodeConfigEnum[] showTextCodeConfigEnums = ShowTextCodeConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (ShowTextCodeConfigEnum showTextCodeConfigNodeEnum : showTextCodeConfigEnums) {
            //得到配置文件标签
            String showTextCodeConfigNodeEnumTag = showTextCodeConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            showTextCodeConfigNodeEnum.setMsg(showTextCodeMessage.get(showTextCodeConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!showTextCodeMessage.contains(showTextCodeConfigNodeEnumTag, true)) {
                undefinedTagList.add(showTextCodeConfigNodeEnumTag);
            }
        }
    }

    /**
     * snaptp指令的配置加载
     */
    public static void snapTpConfigLoad() {
        //snaptp消息内容
        ConfigurationSection snapTpMessage = ServerConfig.getConfigurationSection(TppEnum.TPP_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        TppConfigEnum[] tppConfigEnums = TppConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (TppConfigEnum snapTpConfigNodeEnum : tppConfigEnums) {
            //得到配置文件标签
            String snapTpConfigNodeEnumTag = snapTpConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            snapTpConfigNodeEnum.setMsg(snapTpMessage.get(snapTpConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!snapTpMessage.contains(snapTpConfigNodeEnumTag, true)) {
                undefinedTagList.add(snapTpConfigNodeEnumTag);
            }
        }
    }

    /**
     * tpa指令的配置加载
     */
    public static void tpaConfigLoad() {
        //tpa消息内容
        ConfigurationSection tpaMessage = ServerConfig.getConfigurationSection(TpaEnum.TPA_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        TpaConfigEnum[] tpaConfigEnums = TpaConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (TpaConfigEnum tpaConfigNodeEnum : tpaConfigEnums) {
            //得到配置文件标签
            String tpaConfigNodeEnumTag = tpaConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            tpaConfigNodeEnum.setMsg(tpaMessage.get(tpaConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!tpaMessage.contains(tpaConfigNodeEnumTag, true)) {
                undefinedTagList.add(tpaConfigNodeEnumTag);
            }
        }
    }

    /**
     * tpacancel指令的配置加载
     */
    public static void tpaCancelConfigLoad() {
        //tpacancel消息内容
        ConfigurationSection tpaCancelMessage = ServerConfig.getConfigurationSection(TpaCancelEnum.TPA_CANCEL_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        TpaCancelConfigEnum[] tpaCancelConfigEnums = TpaCancelConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (TpaCancelConfigEnum tpaCancelConfigNodeEnum : tpaCancelConfigEnums) {
            //得到配置文件标签
            String tpaCancelConfigNodeEnumTag = tpaCancelConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            tpaCancelConfigNodeEnum.setMsg(tpaCancelMessage.get(tpaCancelConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!tpaCancelMessage.contains(tpaCancelConfigNodeEnumTag, true)) {
                undefinedTagList.add(tpaCancelConfigNodeEnumTag);
            }
        }
    }

    /**
     * tpaccept指令的配置加载
     */
    public static void tpaCceptConfigLoad() {
        //tpaccept消息内容
        ConfigurationSection tpaCceptMessage = ServerConfig.getConfigurationSection(TpaCceptEnum.TPA_CCEPT_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        TpaCceptConfigEnum[] tpaCceptConfigEnums = TpaCceptConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (TpaCceptConfigEnum tpaCceptConfigNodeEnum : tpaCceptConfigEnums) {
            //得到配置文件标签
            String tpaCceptConfigNodeEnumTag = tpaCceptConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            tpaCceptConfigNodeEnum.setMsg(tpaCceptMessage.get(tpaCceptConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!tpaCceptMessage.contains(tpaCceptConfigNodeEnumTag, true)) {
                undefinedTagList.add(tpaCceptConfigNodeEnumTag);
            }
        }
    }

    /**
     * tpadeny指令的配置加载
     */
    public static void tpaDenyConfigLoad() {
        //tpadeny消息内容
        ConfigurationSection tpaDenyMessage = ServerConfig.getConfigurationSection(TpaDenyEnum.TPA_DENY_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        TpaDenyConfigEnum[] tpaDenyConfigEnums = TpaDenyConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (TpaDenyConfigEnum tpaDenyConfigNodeEnum : tpaDenyConfigEnums) {
            //得到配置文件标签
            String tpaDenyConfigNodeEnumTag = tpaDenyConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            tpaDenyConfigNodeEnum.setMsg(tpaDenyMessage.get(tpaDenyConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!tpaDenyMessage.contains(tpaDenyConfigNodeEnumTag, true)) {
                undefinedTagList.add(tpaDenyConfigNodeEnumTag);
            }
        }
    }

    /**
     * utilitytoolbox指令的配置加载
     */
    public static void utilityToolboxConfigLoad() {
        //utilitytoolbox消息内容
        ConfigurationSection utilityToolboxMessage = ServerConfig.getConfigurationSection(UtilityToolboxEnum.UTILITYTOOLBOX_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        UtilityToolboxConfigEnum[] utilityToolboxConfigEnums = UtilityToolboxConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (UtilityToolboxConfigEnum utilityToolboxConfigNodeEnum : utilityToolboxConfigEnums) {
            //得到配置文件标签
            String utilityToolboxConfigNodeEnumTag = utilityToolboxConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            utilityToolboxConfigNodeEnum.setMsg(utilityToolboxMessage.get(utilityToolboxConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!utilityToolboxMessage.contains(utilityToolboxConfigNodeEnumTag, true)) {
                undefinedTagList.add(utilityToolboxConfigNodeEnumTag);
            }
        }
    }

    /**
     * tpr指令的配置加载
     */
    public static void tprConfigLoad(){
        //获得tpr指令的配置根目录
        ConfigurationSection tprConfig = ServerConfig.getConfigurationSection(TprEnum.TPR_COMMAND.getCommand());
        //获得tpr指令的消息内容
        ConfigurationSection tprMessage = tprConfig.getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        TprConfigEnum[] tprConfigEnums = TprConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (TprConfigEnum tprConfigNodeEnum : tprConfigEnums) {
            //得到配置文件标签
            String tprConfigNodeEnumTag = tprConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            if (tprMessage.get(tprConfigNodeEnumTag) != null) {
                tprConfigNodeEnum.setMsg(tprMessage.get(tprConfigNodeEnumTag));
            } else {
                tprConfigNodeEnum.setMsg(tprConfig.get(tprConfigNodeEnumTag));
            }
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!tprMessage.contains(tprConfigNodeEnumTag, true) && !tprConfig.contains(tprConfigNodeEnumTag, true)) {
                undefinedTagList.add(tprConfigNodeEnumTag);
            }
        }
    }

    /**
     * AntiHighFrequencyRedStone功能的配置加载
     */
    public static void antiHighFrequencyRedStoneConfigLoad() {
        //AntiHighFrequencyRedStone功能的配置内容
        ConfigurationSection antiHighFrequencyRedStoneConfig = ServerConfig.getConfigurationSection(AntiHighFrequencyRedStoneEnum.ANTI_HIGH_FREQUENCY_RED_STONE.getCommand());
        //AntiHighFrequencyRedStone的消息内容
        ConfigurationSection antiHighFrequencyRedStoneMessage = antiHighFrequencyRedStoneConfig.getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        AntiHighFrequencyRedStoneConfigEnum[] antiHighFrequencyRedStoneConfigEnums = AntiHighFrequencyRedStoneConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (AntiHighFrequencyRedStoneConfigEnum antiHighFrequencyRedStoneConfigNodeEnum : antiHighFrequencyRedStoneConfigEnums) {
            //得到配置文件标签
            String antiHighFrequencyRedStoneConfigNodeEnumTag = antiHighFrequencyRedStoneConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            if (antiHighFrequencyRedStoneMessage.get(antiHighFrequencyRedStoneConfigNodeEnumTag) != null) {
                antiHighFrequencyRedStoneConfigNodeEnum.setMsg(antiHighFrequencyRedStoneMessage.get(antiHighFrequencyRedStoneConfigNodeEnumTag));
            } else {
                antiHighFrequencyRedStoneConfigNodeEnum.setMsg(antiHighFrequencyRedStoneConfig.get(antiHighFrequencyRedStoneConfigNodeEnumTag));
            }
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!antiHighFrequencyRedStoneMessage.contains(antiHighFrequencyRedStoneConfigNodeEnumTag, true) && !antiHighFrequencyRedStoneConfig.contains(antiHighFrequencyRedStoneConfigNodeEnumTag, true)) {
                undefinedTagList.add(antiHighFrequencyRedStoneConfigNodeEnumTag);
            }
        }
    }

    /**
     * AutoSendServerMessage功能的配置加载
     */
    public static void autoSendServerMessageConfigLoad() {
        //AutoSendServerMessage功能的配置内容
        ConfigurationSection autoSendServerMessageConfig = ServerConfig.getConfigurationSection(AutoSendServerMessageEnum.AUTO_SEND_SERVER_MESSAGE.getCommand());
        //AutoSendServerMessage的消息内容
        ConfigurationSection autoSendServerMessageMessage = autoSendServerMessageConfig.getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        AutoSendServerMessageConfigEnum[] autoSendServerMessageConfigEnums = AutoSendServerMessageConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (AutoSendServerMessageConfigEnum autoSendServerMessageConfigNodeEnum : autoSendServerMessageConfigEnums) {
            //得到配置文件标签
            String autoSendServerMessageConfigNodeEnumTag = autoSendServerMessageConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            if (autoSendServerMessageMessage.get(autoSendServerMessageConfigNodeEnumTag) != null) {
                autoSendServerMessageConfigNodeEnum.setMsg(autoSendServerMessageMessage.get(autoSendServerMessageConfigNodeEnumTag));
            } else {
                autoSendServerMessageConfigNodeEnum.setMsg(autoSendServerMessageConfig.get(autoSendServerMessageConfigNodeEnumTag));
            }
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!autoSendServerMessageMessage.contains(autoSendServerMessageConfigNodeEnumTag, true) && !autoSendServerMessageConfig.contains(autoSendServerMessageConfigNodeEnumTag, true)) {
                undefinedTagList.add(autoSendServerMessageConfigNodeEnumTag);
            }
        }
    }

    /**
     * CustomJoinAndLeave功能的配置加载
     */
    public static void customJoinAndLeaveConfigLoad() {
        //CustomJoinAndLeave功能的配置内容
        ConfigurationSection customJoinAndLeaveConfig = ServerConfig.getConfigurationSection(CustomJoinAndLeaveEnum.CUSTOM_JOIN_AND_LEAVE.getCommand());
        //CustomJoinAndLeave的消息内容
        ConfigurationSection customJoinAndLeaveMessage = customJoinAndLeaveConfig.getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        CustomJoinAndLeaveConfigEnum[] customJoinAndLeaveConfigEnums = CustomJoinAndLeaveConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (CustomJoinAndLeaveConfigEnum customJoinAndLeaveConfigNodeEnum : customJoinAndLeaveConfigEnums) {
            //得到配置文件标签
            String customJoinAndLeaveConfigNodeEnumTag = customJoinAndLeaveConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            if (customJoinAndLeaveMessage.get(customJoinAndLeaveConfigNodeEnumTag) != null) {
                customJoinAndLeaveConfigNodeEnum.setMsg(customJoinAndLeaveMessage.get(customJoinAndLeaveConfigNodeEnumTag));
            } else {
                customJoinAndLeaveConfigNodeEnum.setMsg(customJoinAndLeaveConfig.get(customJoinAndLeaveConfigNodeEnumTag));
            }
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!customJoinAndLeaveMessage.contains(customJoinAndLeaveConfigNodeEnumTag, true) && !customJoinAndLeaveConfig.contains(customJoinAndLeaveConfigNodeEnumTag, true)) {
                undefinedTagList.add(customJoinAndLeaveConfigNodeEnumTag);
            }
        }
    }

    /**
     * JoinServerWelcome功能的配置加载
     */
    public static void joinServerWelcomeConfigLoad() {
        //JoinServerWelcome功能的配置内容
        ConfigurationSection joinServerWelcomeConfig = ServerConfig.getConfigurationSection(JoinServerWelcomeEnum.JOIN_SERVER_WELCOME.getCommand());
        //JoinServerWelcome的消息内容
        ConfigurationSection joinServerWelcomeMessage = joinServerWelcomeConfig.getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        JoinServerWelcomeConfigEnum[] joinServerWelcomeConfigEnums = JoinServerWelcomeConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (JoinServerWelcomeConfigEnum joinServerWelcomeConfigNodeEnum : joinServerWelcomeConfigEnums) {
            //得到配置文件标签
            String joinServerWelcomeConfigNodeEnumTag = joinServerWelcomeConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            if (joinServerWelcomeMessage.get(joinServerWelcomeConfigNodeEnumTag) != null) {
                joinServerWelcomeConfigNodeEnum.setMsg(joinServerWelcomeMessage.get(joinServerWelcomeConfigNodeEnumTag));
            } else {
                joinServerWelcomeConfigNodeEnum.setMsg(joinServerWelcomeConfig.get(joinServerWelcomeConfigNodeEnumTag));
            }
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!joinServerWelcomeMessage.contains(joinServerWelcomeConfigNodeEnumTag, true) && !joinServerWelcomeConfig.contains(joinServerWelcomeConfigNodeEnumTag, true)) {
                undefinedTagList.add(joinServerWelcomeConfigNodeEnumTag);
            }
        }
    }

    /**
     * LimitHighAltitudeFluid功能的配置加载
     */
    public static void limitHighAltitudeFluidConfigLoad() {
        //LimitHighAltitudeFluid功能的配置内容
        ConfigurationSection limitHighAltitudeFluidConfig = ServerConfig.getConfigurationSection(LimitHighAltitudeFluidEnum.LIMIT_HIGH_ALTITUDE_FLUID.getCommand());
        //LimitHighAltitudeFluid的消息内容
        ConfigurationSection limitHighAltitudeFluidMessage = limitHighAltitudeFluidConfig.getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        LimitHighAltitudeFluidConfigEnum[] limitHighAltitudeFluidConfigEnums = LimitHighAltitudeFluidConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (LimitHighAltitudeFluidConfigEnum limitHighAltitudeFluidConfigNodeEnum : limitHighAltitudeFluidConfigEnums) {
            //得到配置文件标签
            String limitHighAltitudeFluidConfigNodeEnumTag = limitHighAltitudeFluidConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            if (limitHighAltitudeFluidMessage.get(limitHighAltitudeFluidConfigNodeEnumTag) != null) {
                limitHighAltitudeFluidConfigNodeEnum.setMsg(limitHighAltitudeFluidMessage.get(limitHighAltitudeFluidConfigNodeEnumTag));
            } else {
                limitHighAltitudeFluidConfigNodeEnum.setMsg(limitHighAltitudeFluidConfig.get(limitHighAltitudeFluidConfigNodeEnumTag));
            }
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!limitHighAltitudeFluidMessage.contains(limitHighAltitudeFluidConfigNodeEnumTag, true) && !limitHighAltitudeFluidConfig.contains(limitHighAltitudeFluidConfigNodeEnumTag, true)) {
                undefinedTagList.add(limitHighAltitudeFluidConfigNodeEnumTag);
            }
        }
    }

    /**
     * PreventHighFrequencyAttacks功能的配置加载
     */
    public static void preventHighFrequencyAttacksConfigLoad() {
        //PreventHighFrequencyAttacks功能的配置内容
        ConfigurationSection preventHighFrequencyAttacksConfig = ServerConfig.getConfigurationSection(PreventHighFrequencyAttacksEnum.PREVENT_HIGH_FREQUENCY_ATTACKS.getCommand());
        //PreventHighFrequencyAttacks的消息内容
        ConfigurationSection preventHighFrequencyAttacksMessage = preventHighFrequencyAttacksConfig.getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        PreventHighFrequencyAttacksConfigEnum[] preventHighFrequencyAttacksConfigEnums = PreventHighFrequencyAttacksConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (PreventHighFrequencyAttacksConfigEnum preventHighFrequencyAttacksConfigNodeEnum : preventHighFrequencyAttacksConfigEnums) {
            //得到配置文件标签
            String preventHighFrequencyAttacksConfigNodeEnumTag = preventHighFrequencyAttacksConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            if (preventHighFrequencyAttacksMessage.get(preventHighFrequencyAttacksConfigNodeEnumTag) != null) {
                preventHighFrequencyAttacksConfigNodeEnum.setMsg(preventHighFrequencyAttacksMessage.get(preventHighFrequencyAttacksConfigNodeEnumTag));
            } else {
                preventHighFrequencyAttacksConfigNodeEnum.setMsg(preventHighFrequencyAttacksConfig.get(preventHighFrequencyAttacksConfigNodeEnumTag));
            }
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!preventHighFrequencyAttacksMessage.contains(preventHighFrequencyAttacksConfigNodeEnumTag, true) && !preventHighFrequencyAttacksConfig.contains(preventHighFrequencyAttacksConfigNodeEnumTag, true)) {
                undefinedTagList.add(preventHighFrequencyAttacksConfigNodeEnumTag);
            }
        }
    }

    /**
     * ServerListDisplayModification功能的配置加载
     */
    public static void serverListDisplayModificationConfigLoad() {
        //ServerListDisplayModification功能的配置内容
        ConfigurationSection serverListDisplayModificationConfig = ServerConfig.getConfigurationSection(ServerListDisplayModificationEnum.SERVER_LIST_DISPLAY_MODIFICATION.getCommand());
        //ServerListDisplayModification的消息内容
        ConfigurationSection serverListDisplayModificationMessage = serverListDisplayModificationConfig.getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        ServerListDisplayModificationConfigEnum[] serverListDisplayModificationConfigEnums = ServerListDisplayModificationConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (ServerListDisplayModificationConfigEnum serverListDisplayModificationConfigNodeEnum : serverListDisplayModificationConfigEnums) {
            //得到配置文件标签
            String serverListDisplayModificationConfigNodeEnumTag = serverListDisplayModificationConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            if (serverListDisplayModificationMessage.get(serverListDisplayModificationConfigNodeEnumTag) != null) {
                serverListDisplayModificationConfigNodeEnum.setMsg(serverListDisplayModificationMessage.get(serverListDisplayModificationConfigNodeEnumTag));
            } else {
                serverListDisplayModificationConfigNodeEnum.setMsg(serverListDisplayModificationConfig.get(serverListDisplayModificationConfigNodeEnumTag));
            }
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!serverListDisplayModificationMessage.contains(serverListDisplayModificationConfigNodeEnumTag, true) && !serverListDisplayModificationConfig.contains(serverListDisplayModificationConfigNodeEnumTag, true)) {
                undefinedTagList.add(serverListDisplayModificationConfigNodeEnumTag);
            }
        }
    }

}
