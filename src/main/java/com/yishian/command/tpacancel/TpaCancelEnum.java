package com.yishian.command.tpacancel;


/**
 * @author XinQi
 */
public enum TpaCancelEnum {

    /**
     * 取消传送请求
     */
    TPACANCEL_COMMAND("tpacancel", "取消传送请求指令");

    private final String command;
    private final String msg;

    TpaCancelEnum(String command, String msg) {
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
