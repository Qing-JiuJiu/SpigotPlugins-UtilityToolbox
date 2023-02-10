package com.yishian.command.setsnaptp;

import java.util.Collections;

/**
 * @author XinQi
 */
public enum SetSnapTpConfigEnum {
    /**
     * 排除列表
     */
    ALLOW_WORLD("allow-world", Collections.singletonList("*")),

    /**
     * 提示消息列表
     */
    SETSNAPTP_APPLY("setsnaptp-apply", "&a已将此位置设为临时传送点"),
    SETSNAPTP_WORLD_ERROR("setsnaptp-world-error", "&c世界&3%world%&c禁止设置临时传送点"),
    SETSNAPTP_CONSOLE_ERROR("setsnaptp-console-error", "&c控制台无法使用临时传送点相关指令"),
    SETSNAPTP_COMMAND_ERROR("setsnaptp-command-error", "&c设置临时传送点指令格式错误，正确格式: &6/setsnaptp");

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

    SetSnapTpConfigEnum(String tag, Object msg) {
        this.tag = tag;
        this.msg = msg;
    }

}
