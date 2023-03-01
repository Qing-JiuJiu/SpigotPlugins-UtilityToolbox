package com.yishian.command.settpp;

/**
 * @author XinQi
 */
public enum SetTppEnum {

    /**
     * 设置临时传送点
     */
    SET_TPP_COMMAND("settpp", "设置传送点指令");
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
