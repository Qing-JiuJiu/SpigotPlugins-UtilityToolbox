package com.yishian.command.utilitytoolbox;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum UtilityToolboxEnum {

    /**
     * 重载配置文件
     */
    RELOAD_CONFIG_COMMAND("reload", "重载配置文件的指令"),
    /**
     * 重载配置文件的权限
     */
    RELOAD_CONFIG_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + RELOAD_CONFIG_COMMAND.getCommand(), "重载配置文件的权限");

    private final String command;
    private final String msg;

    UtilityToolboxEnum(String command, String msg) {
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
