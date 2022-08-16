package com.yishian.auxiliary;

import com.yishian.common.CommandEnum;

/**
 * @author XinQi
 */
public enum AuxiliaryCommandEnum {

    /**
     * 恢复生命值
     */
    HEAL_COMMAND("heal", "恢复生命值的指令"),
    HEAL_PERMISSION(CommandEnum.PLUGHIN_NAME.getCommand() + "." + HEAL_COMMAND.getCommand(), "恢复自己生命值的权限"),
    HEAL_OTHERS_PERMISSION(HEAL_PERMISSION.getCommand() + "." + CommandEnum.OTHERS.getCommand(), "恢复他人生命值的权限"),

    /**
     * 恢复饱食度
     */
    FEED_COMMAND("feed", "恢复饱食度的指令"),
    FEED_PERMISSION(CommandEnum.PLUGHIN_NAME.getCommand() + "." + FEED_COMMAND.getCommand(), "恢复自己饱食度的权限"),
    FEED_OTHERS_PERMISSION(FEED_PERMISSION.getCommand() + "." + CommandEnum.OTHERS.getCommand(), "恢复他人饱食度的权限"),

    /**
     * 恢复生命值和饱食度
     */
    HEAL_AND_FEED_COMMAND("healandfeed", "恢复生命值和饱食度的指令"),

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

    AuxiliaryCommandEnum(String command, String msg) {
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
