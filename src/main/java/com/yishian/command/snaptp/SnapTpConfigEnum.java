package com.yishian.command.snaptp;

/**
 * @author XinQi
 */
public enum SnapTpConfigEnum {

    /**
     * 提示消息列表
     */
    SNAPTP_APPLY("snaptp-apply", "&a你已传送回临时传送点"),
    SNAPTP_NO_EXIST("snaptp-no-exist", "&c暂无临时传送点，请使用&6/setsnaptp&c指令设置"),
    SNAPTP_CONSOLE_ERROR("snaptp-console-error", "&c控制台无法使用临时传送点相关指令"),
    SNAPTP_COMMAND_ERROR("snaptp-command-error", "&c传送回临时传送点指令错误，正确格式: &6/snaptp");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    SnapTpConfigEnum(String tag, Object msg) {
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
