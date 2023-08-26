package com.yishian.common;


/**
 * @author XinQ
 */
public enum CommonPluginEnum {

    /**
     * 通用枚举
     */
    PLUGHIN_NAME("UtilityToolbox", "本插件的名称"),
    FUNCTION_IS_ENABLE("enable","插件是否启用布尔值"),
    OTHERS("others", "是否允许对他人执行权限的后缀"),
    ALL("*", "所有的通配符"),
    POINT(".", "点"),
    MESSAGE("message","用于获取配置文件消息"),
    NUMBER_TWO("2","数字2");

    private final String command;
    private final String msg;

    CommonPluginEnum(String command, String msg) {
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
