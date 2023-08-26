package com.yishian.function.limithighaltitudefluids;

import com.yishian.common.CommonPluginEnum;

/**
 * @author XinQi
 */
public enum LimitHighAltitudeFluidEnum {

    /**
     * 功能名称
     */
    LIMIT_HIGH_ALTITUDE_FLUID("limit-high-altitude-fluid","限制高空流体的功能名称"),
    /**
     * 限制流体信息权限
     */
    LIMIT_FLOW_MESSAGE_PERMISSION(CommonPluginEnum.PLUGHIN_NAME.getCommand() + ".LimitFlowMessage", "限制流体信息");

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
