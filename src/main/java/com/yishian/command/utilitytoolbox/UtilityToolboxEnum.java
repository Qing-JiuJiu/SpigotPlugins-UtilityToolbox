package com.yishian.command.utilitytoolbox;

import com.yishian.common.CommonPluginEnum;

/**
 * @author XinQi
 */
public enum UtilityToolboxEnum {

    /**
     * 插件主指令
     */
    UTILITYTOOLBOX_COMMAND("utilitytoolbox", "插件主指令"),
    /**
     * 重载配置文件
     */
    RELOAD_CONFIG_COMMAND("reload", "重载配置文件的指令"),
    /**
     * 重载配置文件的权限
     */
    RELOAD_CONFIG_PERMISSION(CommonPluginEnum.PLUGHIN_NAME.getCommand() + "." + RELOAD_CONFIG_COMMAND.getCommand(), "重载配置文件的权限");

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
