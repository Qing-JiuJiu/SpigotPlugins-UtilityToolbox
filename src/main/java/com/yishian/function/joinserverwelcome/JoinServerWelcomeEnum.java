package com.yishian.function.joinserverwelcome;

/**
 * @author XinQi
 */
public enum JoinServerWelcomeEnum {

    /**
     * 功能名称
     */
    JOIN_SERVER_WELCOME("join-server-welcome", "加入服务器欢迎的功能名称");

    private final String command;
    private final String msg;

    JoinServerWelcomeEnum(String command, String msg) {
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
