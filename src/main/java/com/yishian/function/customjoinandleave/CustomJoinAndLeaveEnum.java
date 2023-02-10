package com.yishian.function.customjoinandleave;

/**
 * @author XinQi
 */
public enum CustomJoinAndLeaveEnum {

    /**
     * 功能名称
     */
    CUSTOM_JOIN_AND_LEAVE("join-and-leave-server-message", "自定义加入服务器和离开消息的功能名称");

    private final String command;
    private final String msg;

    CustomJoinAndLeaveEnum(String command, String msg) {
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
