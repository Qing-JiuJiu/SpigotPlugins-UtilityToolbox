package com.yishian.command.killself;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum KillSelfEnum {

    /**
     * 自杀
     */
    KILL_SELF_COMMAND("killself", "自杀指令"),
    KILL_SELF_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + KILL_SELF_COMMAND.getCommand(), "自杀权限");

    private final String command;
    private final String msg;

    KillSelfEnum(String command, String msg) {
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
