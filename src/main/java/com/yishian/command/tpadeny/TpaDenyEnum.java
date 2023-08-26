package com.yishian.command.tpadeny;

/**
 * @author XinQi
 */
public enum TpaDenyEnum {

    /**
     * 拒绝传送请求
     */
    TPADENY_COMMAND("tpadeny", "拒绝传送请求指令");

    private final String command;
    private final String msg;

    TpaDenyEnum(String command, String msg) {
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
