package com.yishian.command.flyspeed;

/**
 * @author XinQi
 */
public enum FlySpeedConfigEnum {

    /**
     * 提示消息列表
     */
    FLYSPEED_SELF_RESET("flyspeed-self-reset", "&a你已重置飞行速度"),
    FLYSPEED_SELF("flyspeed-self", "&a你已修改飞行速度为%fly-speed%"),
    FLYSPEED_SELF_ARGS_ERROR("flyspeed-self-args-error", "&c飞行速度参数错误，飞行速度只能为0-10，默认速度为1"),
    FLYSPEED_OTHERS("flyspeed-others", "&a你已修改&3%others-player%&a的飞行速度为%fly-speed%"),
    FLYSPEED_OTHERS_RESET("flyspeed-others-reset", "&a你已重置&3%others-player%&a的飞行速度"),
    FLYSPEED_BY_CONSOLE("flyspeed-by-console", "你已被服务器修改飞行速度为%fly-speed%"),
    FLYSPEED_BY_CONSOLE_RESET("flyspeed-by-console-reset", "你已被服务器重置飞行速度"),
    FLYSPEED_CONSOLE_ERROR("flyspeed-console-error", "&c控制台执行修改飞行速度的指令格式: &6flyspeed [速度] <玩家名称>");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    FlySpeedConfigEnum(String tag, Object msg) {
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
