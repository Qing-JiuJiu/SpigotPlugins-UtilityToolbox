package com.yishian.function.preventhighfrequencyattacks;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum PreventHighFrequencyAttacksEnum {

    /**
     * 防止高频攻击的功能名称
     */
    PREVENT_HIGH_FREQUENCY_ATTACKS("prevent-high-frequency-attacks", "防止高频攻击的功能名称"),

    /**
     * 玩家高频攻击被提出服务器信息权限
     */
    CPS_MESSAGE_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + ".PlayerFrequencyAttacksMessage", "玩家高频攻击被提出服务器信息");

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
