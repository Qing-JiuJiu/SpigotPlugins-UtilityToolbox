package com.yishian.function.autosendservermessage;

/**
 * @author XinQi
 */
public enum AutoSendServerMessageEnum {

    /**
     * 功能名称
     */
    AUTO_SEND_SERVER_MESSAGE("auto-send-server-messages", "自动发送服务器消息功能名称");

    private final String command;
    private final String msg;

    AutoSendServerMessageEnum(String command, String msg) {
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
