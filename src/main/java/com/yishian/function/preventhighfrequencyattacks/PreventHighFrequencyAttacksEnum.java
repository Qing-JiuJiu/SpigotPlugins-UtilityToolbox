package com.yishian.function.preventhighfrequencyattacks;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum PreventHighFrequencyAttacksEnum {

    /**
     * 玩家高频攻击被提出服务器信息权限
     */
    CPS_MESSAGE_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + ".PlayerFrequencyAttacksKickMessage", "玩家高频攻击被提出服务器信息");

    private final String command;
    private final String msg;

    PreventHighFrequencyAttacksEnum(String command, String msg) {
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
