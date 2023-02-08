package com.yishian.common;


/**
 * @author XinQ
 */
public enum CommonEnum {

    /**
     * 通用枚举
     */
    PLUGHIN_NAME("UtilityToolbox", "本插件的名称"),
    FUNCTION_IS_ENABLE("enable","插件是否启用布尔值"),
    OTHERS("others", "是否允许对他人执行权限的后缀"),
    ALL("*", "所有的通配符"),
    IS_BROADCAST_MESSAGE("is_broadcast_message" , "用于配置文件获取是否广播消息"),
    MESSAGE("message","用于获取配置文件消息"),
    TIME("time","用于获取定时器时间的英语"),

    /**
     * 重载配置文件
     */
    RELOAD_CONFIG_COMMAND("reload", "重载配置文件的指令"),
    RELOAD_CONFIG_PERMISSION(PLUGHIN_NAME.getCommand() + "." + RELOAD_CONFIG_COMMAND.getCommand(), "重载配置文件的权限"),

    /**
     * 消息前缀
     */
    MESSAGE_PREFIX(CommonUtils.ServerConfig.getConfigurationSection("plugin-message").getString("message-prefix"),"消息前缀");

    private final String command;
    private final String msg;

    CommonEnum(String command, String msg) {
        this.command = command;
        this.msg = msg;
    }

    public String getCommand() {
        return command;
    }

    public String getMsg() {
        return msg;
    }
}
