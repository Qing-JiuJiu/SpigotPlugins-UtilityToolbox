package com.yishian.command.kills;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum KillSEnum {

    /**
     * 自杀
     */
    KILLS_COMMAND("kills", "自杀指令"),
    KillS_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + KILLS_COMMAND.getCommand(), "自杀权限");

    private final String command;
    private final String msg;

    KillSEnum(String command, String msg) {
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
