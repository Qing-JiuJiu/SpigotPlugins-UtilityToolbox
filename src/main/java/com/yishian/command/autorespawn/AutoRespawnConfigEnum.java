package com.yishian.command.autorespawn;

/**
 * @author XinQi
 */
public enum AutoRespawnConfigEnum {

    /**
     * 提示消息列表
     */
    AUTORESPAWN_APPLY_OPEN("autorespawn-apply-open", "&a你已开启自动重生"),
    AUTORESPAWN_APPLY_CLOSE("autorespawn-apply-close", "&a你已关闭自动重生"),
    AUTORESPAWN_APPLY("autorespawn-apply", "已自动跳过死亡结算界面");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    AutoRespawnConfigEnum(String tag, Object msg) {
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
