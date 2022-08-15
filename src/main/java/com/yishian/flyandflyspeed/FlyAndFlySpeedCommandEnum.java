package com.yishian.flyandflyspeed;

import com.yishian.common.CommandEnum;

/**
 * @author XinQi
 */
public enum FlyAndFlySpeedCommandEnum {

    /**
     * 飞行
     */
    FLY_COMMAND("fly", "开关飞行的指令"),
    FLY_PERMISSION(CommandEnum.PLUGHIN_NAME.getCommand() + "." + FLY_COMMAND.getCommand(), "开关飞行的权限"),
    FLY_OTHERS_PERMISSION(FLY_PERMISSION.getCommand() + " . " + CommandEnum.OTHERS.getCommand(), "开关他人飞行的权限"),

    /**
     * 飞行速度
     */
    FLY_SPEED_COMMAND("flyspeed", "飞行速度的指令"),
    FLY_SPEED_PERMISSION(CommandEnum.PLUGHIN_NAME.getCommand() + "." + FLY_SPEED_COMMAND.getCommand(), "开关飞行的权限"),
    FLY_SPEED_OTHERS_PERMISSION(FLY_SPEED_PERMISSION.getCommand() + "." + CommandEnum.OTHERS.getCommand(), "开关他人飞行的权限");

    private final String command;
    private final String msg;

    FlyAndFlySpeedCommandEnum(String command, String msg) {
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
