package com.yishian.command.gm;

import com.yishian.common.CommonPluginEnum;

/**
 * @author XinQi
 */
public enum GmModeEnum {

    /**
     * 游戏模式切换
     */
    GM_COMMAND("gm", "游戏模式切换"),
    GM_PERMISSION(CommonPluginEnum.PLUGHIN_NAME.getCommand() + "." + GM_COMMAND.getCommand(), "模式切换的权限"),
    CREATIVE("creative","创造"),
    SURVIVAL("survival","生存"),
    ADVENTURE("adventure","冒险"),
    SPECTATOR("spectator","旁观");

    private final String command;
    private final String msg;

    GmModeEnum(String command, String msg) {
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
