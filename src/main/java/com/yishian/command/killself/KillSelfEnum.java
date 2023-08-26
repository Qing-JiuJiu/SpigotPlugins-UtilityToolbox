package com.yishian.command.killself;

import com.yishian.common.CommonPluginEnum;

/**
 * @author XinQi
 */
public enum KillSelfEnum {

    /**
     * 自杀
     */
    KILLSELF_COMMAND("killself", "自杀指令"),
    KILLSELF_PERMISSION(CommonPluginEnum.PLUGHIN_NAME.getCommand() + "." + KILLSELF_COMMAND.getCommand(), "自杀权限");

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
