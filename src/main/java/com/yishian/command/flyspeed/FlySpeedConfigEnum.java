package com.yishian.command.flyspeed;

/**
 * @author XinQi
 */
public enum FlySpeedConfigEnum {

    /**
     * 提示消息列表
     */
    FLY_SPEED_SELF_RESET("fly-speed-self-reset", "&a你已重置飞行速度"),
    FLY_SPEED_SELF("fly-speed-self", "&a你已修改飞行速度为%fly-speed%"),
    FLY_SPEED_SELF_ARGS_ERROR("fly-speed-self-args-error", "&c飞行速度参数错误，飞行速度只能为0-10，默认速度为1"),
    FLY_SPEED_OTHERS("fly-speed-others", "&a你已修改&3%others-player%&a的飞行速度为%fly-speed%"),
    FLY_SPEED_OTHERS_RESET("fly-speed-others-reset", "&a你已重置&3%others-player%&a的飞行速度"),
    FLY_SPEED_OTHERS_RESET_IS_SELF("fly-speed-others-reset-is-self", "你已重置飞行速度，修改自己可以不输入玩家名称"),
    FLY_SPEED_OTHERS_IS_SELF("fly-speed-others-is-self", "你已修改飞行速度为%fly-speed%，修改自己可以不输入玩家名称"),
    FLY_SPEED_OTHERS_NO_EXIST("fly-speed-others-no-exist", "&c玩家&3%others-player%&c不存在，请检查玩家名字"),
    FLY_SPEED_OTHERS_NO_PERMISSION("fly-speed-others-no-permission", "&c你没有修改他人飞行速度指令的权限"),
    FLY_SPEED_BY_OTHERS("fly-speed-by-others", "你已被&3%player%&6修改飞行速度为%fly-speed%"),
    FLY_SPEED_BY_OTHERS_RESET("fly-speed-by-others-reset", "你已被&3%player%&6重置飞行速度"),
    FLY_SPEED_BY_CONSOLE("fly-speed-by-console", "你已被服务器修改飞行速度为%fly-speed%"),
    FLY_SPEED_BY_CONSOLE_RESET("fly-speed-by-console-reset", "你已被服务器重置飞行速度"),
    FLY_SPEED_CONSOLE_ERROR("fly-speed-console-error", "&c控制台请执行修改他人飞行速度的指令: &6flyspeed [速度] <玩家名称>"),
    FLY_SPEED_COMMAND_ERROR("fly-speed-command-error", "&c修改飞行速度指令格式错误，格式: &6/flyspeed [速度] [玩家名称]");

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
