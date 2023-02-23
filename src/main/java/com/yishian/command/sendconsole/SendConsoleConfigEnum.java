package com.yishian.command.sendconsole;

/**
 * @author XinQi
 */
public enum SendConsoleConfigEnum {

    /**
     * 提示消息列表
     */
    SENDCONSOLE_APPLY_SUCCESS("sendconsole-apply-success", "&a服务器执行&6%command%&a指令成功"),
    SENDCONSOLE_APPLY_FAIL("sendconsole-apply-fail", "&c服务器执行&6%command%&c指令失败，请检查指令是否正确"),
    SENDCONSOLE_COMMAND_ERROR("sendconsole-command-error", "&c发送控制台指令格式错误，正确格式: &6/sendconsole <arg...>");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    SendConsoleConfigEnum(String tag, String msg) {
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
