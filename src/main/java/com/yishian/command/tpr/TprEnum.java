package com.yishian.command.tpr;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum TprEnum {

    /**
     * 随机传送
     */
    TPR_COMMAND("tpr", "随机传送指令"),
    TPR_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + TPR_COMMAND.getCommand(), "随机传送权限");

    private final String command;
    private final String msg;

    TprEnum(String command, String msg) {
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
