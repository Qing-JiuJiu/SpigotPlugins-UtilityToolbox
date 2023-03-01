package com.yishian.command.walkspeed;

/**
 * @author XinQi
 */
public enum WalkSpeedConfigEnum {

    /**
     * 提示消息列表
     */
    WALKSPEED_SELF_RESET("walkspeed-self-reset", "&a你已重置移动速度"),
    WALKSPEED_SELF("walkspeed-self", "&a你已修改移动速度为%walk-speed%"),
    WALKSPEED_SELF_ARGS_ERROR("walkspeed-self-args-error", "&c移动速度参数错误，移动速度只能为0到10，默认速度为2"),
    WALKSPEED_OTHERS("walkspeed-others", "&a你已修改&3%others-player%&a的移动速度为%walk-speed%"),
    WALKSPEED_OTHERS_RESET("walkspeed-others-reset", "&a你已重置&3%others-player%&a的移动速度"),
    WALKSPEED_BY_CONSOLE("walkspeed-by-console", "你已被服务器修改移动速度为%walk-speed%"),
    WALKSPEED_BY_CONSOLE_RESET("walkspeed-by-console-reset", "你已被服务器重置移动速度"),
    WALKSPEED_CONSOLE_ERROR("walkspeed-console-error", "&c控制台执行修改移动速度的指令格式: &6walkspeed [速度] <玩家名称>");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    WalkSpeedConfigEnum(String tag, Object msg) {
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
