package com.yishian.command.playmode;

/**
 * @author XinQi
 */
public enum PlayModeConfigEnum {

    /**
     * 提示消息列表
     */
    PLAYMODE_APPLY("playmode-apply", "&a已让服务器执行切换模式指令"),
    PLAYMODE_CONSOLE_ERROR("playmode-console-error", "&c控制台请使用官方指令：&6gamemode <模式> <玩家>"),
    PLAYMODE_COMMAND_ERROR("playmode-command-error", "&c切换模式指令格式错误，正确格式: &6/playmode <游戏模式>"),
    PLAYMODE_NO_PERMISSION_ERROR("playmode-no-permission-error", "&c你没有切换到模式&3%gamemode%&c的权限");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    PlayModeConfigEnum(String tag, String msg) {
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
