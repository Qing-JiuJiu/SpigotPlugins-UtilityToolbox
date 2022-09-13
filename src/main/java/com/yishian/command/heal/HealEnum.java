package com.yishian.command.heal;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum HealEnum {

    /**
     * 恢复生命值
     */
    HEAL_COMMAND("heal", "恢复生命值的指令"),
    HEAL_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + HEAL_COMMAND.getCommand(), "恢复自己生命值的权限"),
    HEAL_OTHERS_PERMISSION(HEAL_PERMISSION.getCommand() + "." + CommonEnum.OTHERS.getCommand(), "恢复他人生命值的权限");

    private final String command;
    private final String msg;

    HealEnum(String command, String msg) {
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
