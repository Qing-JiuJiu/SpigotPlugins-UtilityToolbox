package com.yishian.command.sendconsole;

/**
 * @author XinQi
 */
public enum SendConsoleConfigEnum {

    /**
     * 提示消息列表
     */
    SENDCONSOLE_APPLY("sendconsole-apply", "&a已让服务器执行&6%command%&a指令，若指令未生效，请核实指令是否正确"),
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
