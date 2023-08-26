package com.yishian.command.rebirthinplace;

import com.yishian.common.CommonPluginEnum;

/**
 * @author XinQi
 */
public enum RebirthInPlaceEnum {

    /**
     * 自动重生后回到死亡位置
     */
    REBIRTH_IN_PLACE_COMMAND("rebirthinplace", "自动重生后回到死亡位置指令"),
    REBIRTH_IN_PLACE_PERMISSION(CommonPluginEnum.PLUGHIN_NAME.getCommand() + "." + REBIRTH_IN_PLACE_COMMAND.getCommand(), "自动重生后回到死亡位置权限");

    private final String command;
    private final Object msg;

    RebirthInPlaceEnum(String command, Object msg) {
        this.command = command;
        this.msg = msg;
    }

    public String getCommand() {
        return command;
    }

    public Object getMsg() {
        return msg;
    }
}
