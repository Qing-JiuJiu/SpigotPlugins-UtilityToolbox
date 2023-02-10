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
    FLY_OTHERS_NO_EXIST("fly-others-no-exist", "&c玩家&3%others-player%&c不存在，请检查玩家名字"),
    FLY_OTHERS_IS_SELF_OPEN("fly-others-is-self-open", "你已开启飞行，可以使用不带参数的指令来开关飞行"),
    FLY_OTHERS_IS_SELF_CLOSE("fly-others-is-self-close", "你已关闭飞行，可以使用不带参数的指令来开关飞行"),
    FLY_OTHERS_NO_PERMISSION("fly-others-no-permission", "&c你没有执行开关他人飞行指令的权限"),
    FLY_BY_OTHERS_OPEN("fly-by-others-open", "你已被&3%player%&6开启飞行"),
    FLY_BY_OTHERS_CLOSE("fly-by-others-close", "你已被&3%player%&6关闭飞行"),
    FLY_BY_CONSOLE_OPEN("fly-by-console-open", "你已被服务器开启飞行"),
    FLY_BY_CONSOLE_CLOSE("fly-by-console-close", "你已被服务器关闭飞行"),
    FLY_CONSOLE_ERROR("fly-console-error", "&c控制台请执行开关他人飞行的指令: &6fly <玩家名称>"),
    FLY_COMMAND_ERROR("fly-command-error", "&c开关飞行指令格式错误，正确格式: &6/fly [玩家名称]");

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
