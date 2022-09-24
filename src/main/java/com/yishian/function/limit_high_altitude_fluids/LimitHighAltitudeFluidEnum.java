package com.yishian.function.limit_high_altitude_fluids;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum LimitHighAltitudeFluidEnum {

    /**
     * 限制流体信息权限
     */
    LIMIT_FLOW_MESSAGE_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + ".LimitFlowMessage", "限制流体信息");

    private final String command;
    private final String msg;

    LimitHighAltitudeFluidEnum(String command, String msg) {
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
