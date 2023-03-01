package com.yishian.command.fly;

/**
 * @author XinQi
 */
public enum FlyConfigEnum {

    /**
     * 提示消息列表
     */
    FLY_SELF_OPEN("fly-self-open", "&a你已开启飞行"),
    FLY_SELF_CLOSE("fly-self-close", "&a你已关闭飞行"),
    FLY_OTHERS_OPEN("fly-others-open", "&a你已开启&3%others-player%&a的飞行"),
    FLY_OTHERS_CLOSE("fly-others-close", "&a你已关闭&3%others-player%&a的飞行"),
    FLY_BY_CONSOLE_OPEN("fly-by-console-open", "你已被服务器开启飞行"),
    FLY_BY_CONSOLE_CLOSE("fly-by-console-close", "你已被服务器关闭飞行"),
    FLY_CONSOLE_ERROR("fly-console-error", "&c控制台执行开关飞行的指令格式: &6fly <玩家名称>");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    FlyConfigEnum(String tag, String msg) {
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
