package com.yishian.command.snaptp;

/**
 * @author XinQi
 */
public enum SnapTpEnum {

    /**
     * 临时传送点传送
     */
    SNAP_TP_COMMAND("snaptp", "传送回临时传送点");

    private final String command;
    private final String msg;

    SnapTpEnum(String command, String msg) {
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
