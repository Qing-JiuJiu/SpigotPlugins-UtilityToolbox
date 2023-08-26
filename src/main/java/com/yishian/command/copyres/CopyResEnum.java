package com.yishian.command.copyres;

import com.yishian.common.CommonPluginEnum;

/**
 * @author XinQi
 */
public enum CopyResEnum {

    /**
     * 复制物品
     */
    COPY_RES_COMMAND("copyres", "复制物品指令"),
    COPY_RES_PERMISSION(CommonPluginEnum.PLUGHIN_NAME.getCommand() + "." + COPY_RES_COMMAND.getCommand(), "复制物品权限");

    private final String command;
    private final String msg;

    CopyResEnum(String command, String msg) {
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
