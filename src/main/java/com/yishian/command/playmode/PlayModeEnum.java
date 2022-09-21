package com.yishian.command.playmode;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum PlayModeEnum {

    /**
     * 游戏模式切换
     */
    PLAY_MODE_COMMAND("playmode", "游戏模式切换"),
    PLAY_MODE_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + PLAY_MODE_COMMAND.getCommand(), "模式切换的权限"),
    CREATIVE("creative","创造"),
    SURVIVAL("survival","生存"),
    ADVENTURE("adventure","冒险"),
    SPECTATOR("spectator","旁观");


    private final String command;
    private final String msg;

    PlayModeEnum(String command, String msg) {
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
