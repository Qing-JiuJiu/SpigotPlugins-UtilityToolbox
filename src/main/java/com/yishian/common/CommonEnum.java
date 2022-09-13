package com.yishian.common;


/**
 * @author XinQi
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
    PLUGIN_MESSAGE("plugin-message" , "用于获取插件主消息"),
    MESSAGE_PREFIX("message-prefix" , "用于获取配置文件消息前缀"),
    MESSAGE("message","用于获取配置文件消息"),
    TIME("time","用于获取定时器时间的英语"),

    /**
     * 重载配置文件
     */
    RELOAD_CONFIG_COMMAND("reload", "重载配置文件的指令"),
    RELOAD_CONFIG_PERMISSION(PLUGHIN_NAME.getCommand() + "." + RELOAD_CONFIG_COMMAND.getCommand(), "重载配置文件的权限");

    /**
     * 功能名称
     */

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
