package com.yishian.command.home;

/**
 * @author XinQi
 */
public enum HomeConfigEnum {

    /**
     * 提示消息列表
     */
    HOME_APPLY("home-apply", "&a你已传送回&3%name%&a家"),
    HOME_NO_EXIST("home-no-exist", "&c暂无&3%name%&c家，请使用&6/sethome [name]&c指令设置家的位置"),
    HOME_CONSOLE_ERROR("home-console-error", "&c控制台无法使用家相关指令"),
    HOME_COMMAND_ERROR("home-command-error", "&c设置家指令格式错误，正确格式: &6/sethome [name]");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    HomeConfigEnum(String tag, Object msg) {
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
