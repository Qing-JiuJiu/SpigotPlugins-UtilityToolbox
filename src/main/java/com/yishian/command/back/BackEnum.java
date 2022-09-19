package com.yishian.command.back;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum BackEnum {

    /**
     * 返回指令
     */
    BACK_COMMAND("back", "返回指令"),
    BACK_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + BACK_COMMAND.getCommand(), "返回权限");

    private final String command;
    private final String msg;

    BackEnum(String command, String msg) {
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
