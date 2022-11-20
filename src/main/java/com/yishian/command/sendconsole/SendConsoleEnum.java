package com.yishian.command.sendconsole;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum SendConsoleEnum {

    /**
     * 发送控制台指令
     */
    SEND_CONSOLE_COMMAND("sendconsole", "发送控制台指令"),
    SEND_CONSOLE_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + SEND_CONSOLE_COMMAND.getCommand(), "发送控制台指令的权限");

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
