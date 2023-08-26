package com.yishian.command.sendconsole;

import com.yishian.common.CommonPluginEnum;

/**
 * @author XinQi
 */
public enum SendConsoleEnum {

    /**
     * 发送控制台指令
     */
    SENDCONSOLE_COMMAND("sendconsole", "发送控制台指令"),
    SENDCONSOLE_PERMISSION(CommonPluginEnum.PLUGHIN_NAME.getCommand() + "." + SENDCONSOLE_COMMAND.getCommand(), "发送控制台指令的权限");

    private final String command;
    private final String msg;

    SendConsoleEnum(String command, String msg) {
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
