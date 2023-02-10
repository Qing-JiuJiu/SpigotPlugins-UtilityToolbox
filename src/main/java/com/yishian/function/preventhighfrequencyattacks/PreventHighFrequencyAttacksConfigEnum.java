package com.yishian.function.preventhighfrequencyattacks;

/**
 * @author XinQi
 */
public enum PreventHighFrequencyAttacksConfigEnum {

    /**
     * 相关配置
     */
    ENABLE("enable", false),
    IS_BROADCAST_MESSAGE("is-broadcast-message", true),
    IS_KILL("is-kill", false),
    LIMIT("limit",50),
    TIME("time", 3),
    /**
     * 提示消息列表
     */
    PLAYER_KICK_MESSAGE("player-kick-message", "&c你因疑似使用连点器被服务器踢出，若有疑问请联系服主，CPS:%cps%"),
    BROADCAST_KICK_MESSAGE("broadcast-kick-message", "&c玩家&3%player%&c疑似使用连点器，已踢出服务器，CPS:%cps%"),
    BROADCAST_NO_KICK_MESSAGE("broadcast-no-kick-message", "&c玩家&3%player%&c疑似使用连点器，CPS:%cps%");

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

    PreventHighFrequencyAttacksConfigEnum(String tag, Object msg) {
        this.tag = tag;
        this.msg = msg;
    }

}
