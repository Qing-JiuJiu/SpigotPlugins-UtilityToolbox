package com.yishian.command.back;

/**
 * @author XinQi
 */
public enum BackConfigEnum {

    /**
     * 提示消息列表
     */
    BACK_APPLY("back-apply", "&a你已回到被传送前的位置"),
    BACK_DIED_TIPS("back-died-tips", "你在下一次传送前可使用&c/back&6指令回到死亡位置"),
    BACK_DIED_NO_TP_TIPS("back-died-no-tp-tips", "服务器&c/back&6指令已记录你的死亡位置"),
    BACK_NO_LOCATION("back-no-location", "&c你暂时没有返回的位置"),
    BACK_CONSOLE_ERROR("back-console-error", "&c控制台无法使用返回指令"),
    BACK_COMMAND_ERROR("back-command-error", "&c返回指令格式错误，正确格式: &6/back");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    BackConfigEnum(String tag, Object msg) {
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
