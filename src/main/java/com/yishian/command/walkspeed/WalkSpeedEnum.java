package com.yishian.command.walkspeed;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum WalkSpeedEnum {

    /**
     * 移动速度
     */
    WALK_SPEED_COMMAND("walkspeed", "移动速度的指令"),
    WALK_SPEED_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + WALK_SPEED_COMMAND.getCommand(), "修改移动速度的权限"),
    WALK_SPEED_OTHERS_PERMISSION(WALK_SPEED_PERMISSION.getCommand() + "." + CommonEnum.OTHERS.getCommand(), "修改他人移动速度的权限");

    private final String command;
    private final String msg;

    WalkSpeedEnum(String command, String msg) {
        this.command = command;
        this.msg = msg;
    }

    public String getCommand() {
        return command;
    }

    public String getMsg() {
        return msg;
    }
}
