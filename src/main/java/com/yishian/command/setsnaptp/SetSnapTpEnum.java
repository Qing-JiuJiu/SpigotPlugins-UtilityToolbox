package com.yishian.command.setsnaptp;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum SetSnapTpEnum {

    /**
     * 设置临时传送点
     */
    SET_SNAP_TP_COMMAND("setsnaptp", "设置临时传送位置指令"),
    SET_SNAP_TP_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + SET_SNAP_TP_COMMAND.getCommand(), "设置临时传送位置传送");

    private final String command;
    private final String msg;

    SetSnapTpEnum(String command, String msg) {
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
