package com.yishian.command.tpp;

/**
 * @author XinQi
 */
public enum TppConfigEnum {

    /**
     * 提示消息列表
     */
    TPP_APPLY("tpp-apply", "&a你已传送到&3%tp-name%&a传送点"),
    TPP_NO_EXIST("tpp-no-exist", "&c传送点&3%tp-name%&c不存在，请检查传送点名称是否正确"),
    TPP_COMMAND_ERROR("tpp-command-error", "&c回到传送点指令错误，正确格式: &6/tpp [name]");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    TppConfigEnum(String tag, Object msg) {
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
