package com.yishian.command.sethome;

import java.util.Collections;

/**
 * @author XinQi
 */
public enum SetHomeConfigEnum {
    /**
     * 允许列表
     */
    ALLOW_WORLD("allow-world", Collections.singletonList("*")),

    /**
     * 提示消息列表
     */
    SETHOME_APPLY("sethome-apply", "&a已将此位置设为&3%name%&a家"),
    SETHOME_WORLD_ERROR("sethome-world-error", "&c世界&3%world%&c禁止设置家"),
    SETHOME_COMMAND_ERROR("sethome-command-error", "&c回家指令格式错误，正确格式: &6/sethome [name]");

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

    SetHomeConfigEnum(String tag, Object msg) {
        this.tag = tag;
        this.msg = msg;
    }

}
