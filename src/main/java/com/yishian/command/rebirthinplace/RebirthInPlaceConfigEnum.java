package com.yishian.command.rebirthinplace;

/**
 * @author XinQi
 */
public enum RebirthInPlaceConfigEnum {

    /**
     * 提示消息列表
     */
    REBIRTHINPLACE_APPLY_OPEN("rebirthinplace-apply-open", "&a你已开启原地重生"),
    REBIRTHINPLACE_APPLY_CLOSE("rebirthinplace-apply-close", "&a你已关闭原地重生"),
    REBIRTHINPLACE_APPLY("rebirthinplace-apply", "你已原地重生");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    RebirthInPlaceConfigEnum(String tag, Object msg) {
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
