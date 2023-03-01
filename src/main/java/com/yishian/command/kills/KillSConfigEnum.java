package com.yishian.command.kills;

/**
 * @author XinQi
 */
public enum KillSConfigEnum {

    /**
     * 提示消息列表
     */
    KILLS_APPLY("kills-apply", "&a已让服务器执行自杀指令"),
    KILLS_COMMAND_ERROR("kills-command-error", "&c自杀指令格式错误，正确格式: &6/kills");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    KillSConfigEnum(String tag, String msg) {
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
