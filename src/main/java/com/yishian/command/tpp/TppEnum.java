package com.yishian.command.tpp;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum TppEnum {

    /**
     * 临时传送点传送
     */
    TPP_COMMAND("tpp", "回到传送点"),
    TPP_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + TPP_COMMAND.getCommand(), "传送点权限");


    private final String command;
    private final String msg;

    TppEnum(String command, String msg) {
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
