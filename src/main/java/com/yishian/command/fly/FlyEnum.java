package com.yishian.command.fly;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum FlyEnum {

    /**
     * 飞行
     */
    FLY_COMMAND("fly", "开关飞行的指令"),
    FLY_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + FLY_COMMAND.getCommand(), "开关飞行的权限"),
    FLY_OTHERS_PERMISSION(FLY_PERMISSION.getCommand() + "." + CommonEnum.OTHERS.getCommand(), "开关他人飞行的权限");

    private final String command;
    private final String msg;

    FlyEnum(String command, String msg) {
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
