package com.yishian.common;


/**
 * @author XinQi
 */
public enum CommandEnum {

    /**
     * 通用枚举
     */
    PLUGHIN_NAME("UtilityToolbox", "本插件的名称"),
    FUNCTION_IS_ENABLE("enable","插件启用布尔值"),
    OTHERS("others", "他人的英语"),
    ALL("*", "所有的通配符"),
    IS_BROADCAST_MESSAGE("is_broadcast_message" , "是否广播消息"),

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

    CommandEnum(String command, String msg) {
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
