package com.yishian.command.tpacancel;


/**
 * @author XinQi
 */
public enum TpaCancelEnum {

    /**
     * 恢复生命值
     */
    TPA_CANCEL_COMMAND("tpacancel", "申请传送至他人的指令");

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
