package com.yishian.command.tpa;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum TpaEnum {

    /**
     * 申请传送至他人
     */
    TPA_COMMAND("tpa", "申请传送至他人的指令"),
    TPA_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + TPA_COMMAND.getCommand(), "申请传送至他人的权限");

    private final String command;
    private final String msg;

    TpaEnum(String command, String msg) {
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
