package com.yishian.command.autodeathback;

/**
 * @author XinQi
 */
public enum AutoRespawnBackConfigEnum {

    /**
     * 提示消息列表
     */
    AUTORESPAWNBACK_APPLY_OPEN("autorespawnback-apply-open", "&a你已开启重生后自动回到死亡位置"),
    AUTORESPAWNBACK_APPLY_CLOSE("autorespawnback-apply-close", "&a你已关闭重生后自动回到死亡位置"),
    AUTORESPAWNBACK_APPLY("autorespawnback-apply", "你已重生，已自动传送回死亡位置"),
    AUTORESPAWNBACK_CONSOLE_ERROR("autorespawnback-console-error", "&c控制台无法使用此命令");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    AutoRespawnBackConfigEnum(String tag, Object msg) {
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
