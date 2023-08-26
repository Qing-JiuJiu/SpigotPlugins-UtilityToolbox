package com.yishian.command.tpaccept;

/**
 * @author XinQi
 */
public enum TpaCceptEnum {

    /**
     * 同意传送请求
     */
    TPACCEPT_COMMAND("tpaccept", "同意传送请求指令");

    private final String command;
    private final String msg;

    TpaCceptEnum(String command, String msg) {
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
