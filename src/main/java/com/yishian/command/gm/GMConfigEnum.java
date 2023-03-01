package com.yishian.command.gm;

/**
 * @author XinQi
 */
public enum GMConfigEnum {

    /**
     * 提示消息列表
     */
    GM_APPLY("gm-apply", "&a已让服务器执行切换模式指令"),
    GM_COMMAND_ERROR("gm-command-error", "&c切换模式指令格式错误，正确格式: &6/gm <游戏模式>"),
    GM_NO_PERMISSION_ERROR("gm-no-permission-error", "&c你没有切换到模式&3%gamemode%&c的权限");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    GMConfigEnum(String tag, String msg) {
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
