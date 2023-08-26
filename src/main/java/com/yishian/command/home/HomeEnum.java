package com.yishian.command.home;

import com.yishian.common.CommonPluginEnum;

/**
 * @author XinQi
 */
public enum HomeEnum {

    /**
     * 回家
     */
    HOME_COMMAND("home", "回家指令"),
    HOME_PERMISSION(CommonPluginEnum.PLUGHIN_NAME.getCommand() + "." + HOME_COMMAND.getCommand(), "家权限");

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
