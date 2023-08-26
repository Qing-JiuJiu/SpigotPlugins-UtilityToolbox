package com.yishian.command.autorespawn;

import com.yishian.common.CommonPluginEnum;

/**
 * @author XinQi
 * 自动重生指令枚举
 */
public enum AutoRespawnEnum {

    /**
     * 自动重生
     */
    AUTORESPAWN_COMMAND("autorespawn", "自动重生指令"),
    AUTORESPAWN_PERMISSION(CommonPluginEnum.PLUGHIN_NAME.getCommand() + "." + AUTORESPAWN_COMMAND.getCommand(), "自动重生指令权限");

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
