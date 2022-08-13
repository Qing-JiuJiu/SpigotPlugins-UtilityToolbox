package com.yishian.auxiliary;

import com.yishian.currency.CommandEnum;

/**
 * @author XinQi
 */
public enum AuxiliaryCommandEnum {

    //治愈指令
    HEAL_COMMAND("heal", "恢复生命的指令"),
    //恢复他人指令
    HEAL_OTHERS_COMMAND("healothers", "恢复他人生命的指令"),
    //治愈自己权限
    HEAL_PERMISSION(CommandEnum.PLUGHIN_NAME.getCommand() + "." + HEAL_COMMAND.getCommand(), "恢复生命的权限"),
    //获取治愈他人权限
    HEAL_OTHERS_PERMISSION(CommandEnum.PLUGHIN_NAME.getCommand() + "." + HEAL_OTHERS_COMMAND.getCommand(), "恢复他人生命的权限"),

    //恢复饱食指令
    FEED_COMMAND("feed", "恢复饱食的指令"),
    //恢复饱食权限
    FEED_PERMISSION(CommandEnum.PLUGHIN_NAME.getCommand() + "." + FEED_COMMAND.getCommand(), "恢复饱食的权限"),
    //恢复他人饱食度指令
    FEED_OTHERS_COMMAND("feedothers", "恢复他人饱食的指令"),
    //恢复他人饱食度权限
    FEED_OTHERS_PERMISSION(CommandEnum.PLUGHIN_NAME.getCommand() + "." + FEED_OTHERS_COMMAND.getCommand(), "恢复他人生命的权限"),

    //恢复全部状态指令
    HEAL_AND_FEED_COMMAND("healandfeed", "恢复生命和饱食的指令");

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
