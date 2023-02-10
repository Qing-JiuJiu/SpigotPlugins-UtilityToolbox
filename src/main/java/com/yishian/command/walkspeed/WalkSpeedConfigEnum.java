package com.yishian.command.walkspeed;

/**
 * @author XinQi
 */
public enum WalkSpeedConfigEnum {

    /**
     * 提示消息列表
     */
    WALK_SPEED_SELF_RESET("walk-speed-self-reset", "&a你已重置移动速度"),
    WALK_SPEED_SELF("walk-speed-self", "&a你已修改移动速度为%walk-speed%"),
    WALK_SPEED_SELF_ARGS_ERROR("walk-speed-self-args-error", "&c移动速度参数错误，移动速度只能为0到10，默认速度为2"),
    WALK_SPEED_OTHERS("walk-speed-others", "&a你已修改&3%others-player%&a的移动速度为%walk-speed%"),
    WALK_SPEED_OTHERS_RESET("walk-speed-others-reset", "&a你已重置&3%others-player%&a的移动速度"),
    WALK_SPEED_OTHERS_RESET_IS_SELF("walk-speed-others-reset-is-self", "你已重置移动速度，修改自己可以不输入玩家名称"),
    WALK_SPEED_OTHERS_IS_SELF("walk-speed-others-is-self", "你已修改移动速度为%walk-speed%，修改自己可以不输入玩家名称"),
    WALK_SPEED_OTHERS_NO_EXIST("walk-speed-others-no-exist", "&c玩家&3%others-player%&c不存在，请检查玩家名字"),
    WALK_SPEED_OTHERS_NO_PERMISSION("walk-speed-others-no-permission", "&c你没有修改他人移动速度指令的权限"),
    WALK_SPEED_BY_OTHERS("walk-speed-by-others", "你已被&3%player%&6修改移动速度为%walk-speed%"),
    WALK_SPEED_BY_OTHERS_RESET("walk-speed-by-others-reset", "你已被&3%player%&6重置移动速度"),
    WALK_SPEED_BY_CONSOLE("walk-speed-by-console", "你已被服务器修改移动速度为%walk-speed%"),
    WALK_SPEED_BY_CONSOLE_RESET("walk-speed-by-console-reset", "你已被服务器重置移动速度"),
    WALK_SPEED_CONSOLE_ERROR("walk-speed-console-error", "&c控制台请执行修改他人移动速度的指令: &6walkspeed [速度] <玩家名称>"),
    WALK_SPEED_COMMAND_ERROR("walk-speed-command-error", "&c修改移动速度指令格式错误，格式: &6/walkspeed [速度] [玩家名称]");

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
