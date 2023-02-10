package com.yishian.function.autosendservermessage;

import java.util.Collections;

/**
 * @author XinQi
 */
public enum AutoSendServerMessageConfigEnum {

    /**
     * 相关配置
     */
    ENABLE("enable", false),
    TIME("time", 600),

    /**
     * 提示消息列表
     */
    SEND_MESSAGES("send-messages", Collections.singletonList("&e[UtilityToolbox] &6欢迎使用本插件"));

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

    AutoSendServerMessageConfigEnum(String tag, Object msg) {
        this.tag = tag;
        this.msg = msg;
    }

}
