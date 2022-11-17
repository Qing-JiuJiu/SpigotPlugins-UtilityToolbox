package com.yishian.command.autorespawn;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum AutoRespawnEnum {

    /**
     * 自动重生
     */
    AUTO_RESPAWN_COMMAND("autorespawn", "自动重生指令"),
    AUTO_RESPAWN_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + AUTO_RESPAWN_COMMAND.getCommand(), "自动重生指令权限");

    private final String command;
    private final String msg;

    AutoRespawnEnum(String command, String msg) {
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
