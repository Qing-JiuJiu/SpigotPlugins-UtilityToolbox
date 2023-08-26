package com.yishian.command.flyspeed;

import com.yishian.common.CommonPluginEnum;

/**
 * @author XinQi
 */
public enum FlySpeedEnum {

    /**
     * 飞行速度
     */
    FLY_SPEED_COMMAND("flyspeed", "飞行速度的指令"),
    FLY_SPEED_PERMISSION(CommonPluginEnum.PLUGHIN_NAME.getCommand() + "." + FLY_SPEED_COMMAND.getCommand(), "修改飞行速度的权限"),
    FLY_SPEED_OTHERS_PERMISSION(FLY_SPEED_PERMISSION.getCommand() + "." + CommonPluginEnum.OTHERS.getCommand(), "需改他人飞行速度的权限");

    private final String command;
    private final String msg;

    FlySpeedEnum(String command, String msg) {
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
