package com.yishian.command.walkspeed;

import com.yishian.common.CommonPluginEnum;

/**
 * @author XinQi
 */
public enum WalkSpeedEnum {

    /**
     * 移动速度
     */
    WALKSPEED_COMMAND("walkspeed", "移动速度的指令"),
    WALKSPEED_PERMISSION(CommonPluginEnum.PLUGHIN_NAME.getCommand() + "." + WALKSPEED_COMMAND.getCommand(), "修改移动速度的权限"),
    WALKSPEED_OTHERS_PERMISSION(WALKSPEED_PERMISSION.getCommand() + "." + CommonPluginEnum.OTHERS.getCommand(), "修改他人移动速度的权限");

    private final String command;
    private final String msg;

    WalkSpeedEnum(String command, String msg) {
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
