package com.yishian.command.musterplayer;


import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum MusterPlayerEnum {

    /**
     * 召集玩家
     */
    MUSTER_PLAYER_COMMAND("musterplayer", "召集玩家"),
    MUSTER_PLAYER_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + MUSTER_PLAYER_COMMAND.getCommand(), "召集玩家的权限");

    private final String command;
    private final String msg;

    MusterPlayerEnum(String command, String msg) {
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
