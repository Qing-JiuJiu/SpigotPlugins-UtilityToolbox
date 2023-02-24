package com.yishian.command.settpp;

import java.util.Collections;

/**
 * @author XinQi
 */
public enum SetTppConfigEnum {
    /**
     * 排除列表
     */
    ALLOW_WORLD("allow-world", Collections.singletonList("*")),

    /**
     * 提示消息列表
     */
    SETTPP_APPLY("settpp-apply", "&a已将此位置设为&3%tp-name%&a传送点"),
    SETTPP_WORLD_ERROR("settpp-world-error", "&c世界&3%world%&c禁止设置传送点"),
    SETTPP_CONSOLE_ERROR("settpp-console-error", "&c控制台无法使用传送点相关指令"),
    SETTPP_COMMAND_ERROR("settpp-command-error", "&c设置传送点指令格式错误，正确格式: &6/settpp [tpname]");

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

    SetTppConfigEnum(String tag, Object msg) {
        this.tag = tag;
        this.msg = msg;
    }

}
