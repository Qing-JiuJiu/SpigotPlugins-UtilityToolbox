package com.yishian.currency;

public enum CommandEnum {
    //重载配置文件
    PLUGHIN_NAME("Enhancements", "获取插件名称");

    private final String command;
    private final String msg;

    CommandEnum(String command, String msg) {
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
