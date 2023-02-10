package com.yishian.function.joinserverwelcome;

import java.util.Collections;

/**
 * @author XinQi
 */
public enum JoinServerWelcomeConfigEnum {

    /**
     * 相关配置
     */
    ENABLE("enable", true),
    FIRST_JOIN_SERVER_WELCOME_ENABLE("first-join-server-welcome-enable", false),

    /**
     * 提示消息列表
     */
    JOIN_SERVER_WELCOME_MESSAGE("join-server-welcome-message", Collections.singletonList("&e[UtilityToolbox] &6欢迎使用本插件")),
    FIRST_JOIN_SERVER_WELCOME_MESSAGE("first-join-server-welcome-message", Collections.singletonList("&e[UtilityToolbox] &6欢迎使用本插件"));

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

    JoinServerWelcomeConfigEnum(String tag, Object msg) {
        this.tag = tag;
        this.msg = msg;
    }

}
