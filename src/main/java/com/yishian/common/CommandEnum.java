package com.yishian.common;

/**
 * @author XinQi
 */
public enum CommandEnum {

    /**
     * 通用枚举
     */
    PLUGHIN_NAME("enhancements", "本插件的名称"),
    OTHERS("others", "他人的英语"),

    /**
     * 重载配置文件
     */
    RELOAD_CONFIG_COMMAND("reload", "重载配置文件的指令"),
    RELOAD_CONFIG_PERMISSION(PLUGHIN_NAME.getCommand() + "." + RELOAD_CONFIG_COMMAND.getCommand(), "重载配置文件的权限");

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
