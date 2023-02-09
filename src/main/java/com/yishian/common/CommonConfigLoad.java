package com.yishian.common;

import com.yishian.Main;
import com.yishian.command.autodeathback.AutoRespawnBackConfigEnum;
import com.yishian.command.autodeathback.AutoRespawnBackEnum;
import com.yishian.command.heal.HealConfigEnum;
import com.yishian.command.heal.HealEnum;
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
        healConfigLoad();
        autoRespawnBackConfigConfigLoad();

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
            healConfigNodeEnum.setMsg(healConfigMessage.getString(healConfigTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!healConfigMessage.contains(healConfigTag, true)) {
                undefinedTagList.add(healConfigTag);
            }
        }

    }

    /**
     * autoRespawnBackConfig指令的配置加载
     */
    public static void autoRespawnBackConfigConfigLoad() {
        //autoRespawnBack消息内容
        ConfigurationSection autoRespawnBackMessage = ServerConfig.getConfigurationSection(AutoRespawnBackEnum.AUTO_RESPAWN_BACK_COMMAND.getCommand()).getConfigurationSection(CommonEnum.MESSAGE.getCommand());
        //得到所有枚举的值
        AutoRespawnBackConfigEnum[] autoRespawnBackConfigEnums = AutoRespawnBackConfigEnum.values();
        //循环判断每个节点是否存在，存在就替换枚举里的内容，不存在就添加到未定义的标签列表并恢复原内容
        for (AutoRespawnBackConfigEnum autoRespawnBackConfigNodeEnum : autoRespawnBackConfigEnums) {
            //得到配置文件标签
            String autoRespawnBackConfigNodeEnumTag = autoRespawnBackConfigNodeEnum.getTag();
            //直接设置消息内容，如果存在该节点就会设置节点内容，如果不存在就会使用源配置文件里的内容
            autoRespawnBackConfigNodeEnum.setMsg(autoRespawnBackMessage.getString(autoRespawnBackConfigNodeEnumTag));
            //判断是否拥有该节点，没有就添加进列表用于输出警告
            if (!autoRespawnBackMessage.contains(autoRespawnBackConfigNodeEnumTag, true)) {
                undefinedTagList.add(autoRespawnBackConfigNodeEnumTag);
            }
        }
    }

}
