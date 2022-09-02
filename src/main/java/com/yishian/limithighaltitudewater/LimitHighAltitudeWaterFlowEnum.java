package com.yishian.limithighaltitudewater;

import com.yishian.common.CommandEnum;

/**
 * @author XinQi
 */
public enum LimitHighAltitudeWaterFlowEnum {

    /**
     * 限制流体信息权限
     */
    LIMIT_FLOW_MESSAGE_PERMISSION(CommandEnum.PLUGHIN_NAME.getCommand() + ".LimitFlowMessage", "限制流体信息");

    private final String command;
    private final String msg;

    LimitHighAltitudeWaterFlowEnum(String command, String msg) {
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
