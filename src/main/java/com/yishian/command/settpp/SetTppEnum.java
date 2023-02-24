package com.yishian.command.settpp;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum SetTppEnum {

    /**
     * 设置临时传送点
     */
    SET_TPP_COMMAND("settpp", "设置传送点指令"),
    SET_TPP_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + SET_TPP_COMMAND.getCommand(), "设置传送点权限");

    private final String command;
    private final String msg;

    SetTppEnum(String command, String msg) {
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
