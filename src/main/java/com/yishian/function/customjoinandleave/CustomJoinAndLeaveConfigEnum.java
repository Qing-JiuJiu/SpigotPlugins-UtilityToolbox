package com.yishian.function.customjoinandleave;

/**
 * @author XinQi
 */
public enum CustomJoinAndLeaveConfigEnum {

    /**
     * 相关配置
     */
    ENABLE("enable", true),
    FIRST_JOIN_SERVER_MESSAGE_ENABLE("first-join-server-message-enable", false),

    /**
     * 提示消息列表
     */
    PLAYER_JOIN_SERVER_MESSAGE("player-join-server-message", "&a%player% join server"),
    PLAYER_LEAVE_SERVER_MESSAGE("player-leave-server-message", "&c%player% leave server"),
    PLAYER_FIRST_JOIN_SERVER_MESSAGE("player-first-join-server-message", "&a%player% first join server");

    private final String tag;
    private Object msg;

    public String getTag() {
        return tag;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    CustomJoinAndLeaveConfigEnum(String tag, Object msg) {
        this.tag = tag;
        this.msg = msg;
    }

}
