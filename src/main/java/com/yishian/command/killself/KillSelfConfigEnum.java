package com.yishian.command.killself;

/**
 * @author XinQi
 */
public enum KillSelfConfigEnum {

    /**
     * 提示消息列表
     */
    KILLSELF_APPLY("killself-apply", "&a已让服务器执行自杀指令"),
    KILLSELF_CONSOLE_ERROR("killself-console-error", "&c控制台请使用官方指令：kill [目标]"),
    KILLSELF_COMMAND_ERROR("killself-command-error", "&c自杀指令格式错误，正确格式: &6/killself");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    KillSelfConfigEnum(String tag, String msg) {
        this.tag = tag;
        this.msg = msg;
    }

    public String getTag() {
        return tag;
    }

    public Object getMsg() {
        return msg;
    }
}
