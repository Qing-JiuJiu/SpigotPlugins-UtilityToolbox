package com.yishian.command.home;

/**
 * @author XinQi
 */
public enum HomeEnum {

    /**
     * 回家
     */
    HOME_COMMAND("home", "回家指令");

    private final String command;
    private final String msg;

    HomeEnum(String command, String msg) {
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
