package com.yishian.command.heal;

import com.yishian.common.CommonPluginEnum;

/**
 * @author XinQi
 */
public enum HealEnum {

    /**
     * 恢复状态
     */
    HEAL_COMMAND("heal", "恢复状态的指令"),
    HEAL_PERMISSION(CommonPluginEnum.PLUGHIN_NAME.getCommand() + "." + HEAL_COMMAND.getCommand(), "恢复状态的权限"),
    HEAL_OTHERS_PERMISSION(HEAL_PERMISSION.getCommand() + "." + CommonPluginEnum.OTHERS.getCommand(), "恢复他人状态的权限");

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
