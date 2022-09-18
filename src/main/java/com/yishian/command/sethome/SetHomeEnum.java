package com.yishian.command.sethome;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum SetHomeEnum {

    /**
     * 设置家
     */
    SET_HOME_COMMAND("sethome", "设置家指令"),
    SET_HOME_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + SET_HOME_COMMAND.getCommand(), "设置家权限");

    private final String command;
    private final String msg;

    SetHomeEnum(String command, String msg) {
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
