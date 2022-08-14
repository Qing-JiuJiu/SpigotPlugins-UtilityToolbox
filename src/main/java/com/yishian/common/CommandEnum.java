package com.yishian.common;

/**
 * @author XinQi
 */
public enum CommandEnum {

    //获取插件名称
    PLUGHIN_NAME("Enhancements", "获取插件名称"),

    //重载配置文件的指令和权限，重载配置文件需要打插件名称+空格+reload
    RELOAD_CONFIG_COMMAND("reload", "重载配置文件"),
    RELOAD_CONFIG_PERMISSION(PLUGHIN_NAME.getCommand() + "." + RELOAD_CONFIG_COMMAND.getCommand(), "重载配置文件权限");

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
