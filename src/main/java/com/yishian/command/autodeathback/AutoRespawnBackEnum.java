package com.yishian.command.autodeathback;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum AutoRespawnBackEnum {

    /**
     * 自动重生后回到死亡位置
     */
    AUTO_RESPAWN_BACK_COMMAND("autorespawnback", "自动重生后回到死亡位置指令"),
    AUTO_RESPAWN_BACK_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + AUTO_RESPAWN_BACK_COMMAND.getCommand(), "自动重生后回到死亡位置权限");

    private final String command;
    private final String msg;

    AutoRespawnBackEnum(String command, String msg) {
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
