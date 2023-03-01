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
    MESSAGE("message","用于获取配置文件消息");

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
