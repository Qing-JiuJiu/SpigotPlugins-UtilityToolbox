package com.yishian.command.teleport;

import com.yishian.common.CommonPluginEnum;

/**
 * @author XinQi
 */
public enum TeleportEnum {

    /**
     * 服务器是否允许传送切换
     */
    TELEPORT_COMMAND("teleport", "服务器是否允许传送切换指令"),
    TELEPORT_PERMISSION(CommonPluginEnum.PLUGHIN_NAME.getCommand() + "." + TELEPORT_COMMAND.getCommand(), "服务器是否允许传送切换权限");

    private final String command;
    private final String msg;

    TeleportEnum(String command, String msg) {
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
