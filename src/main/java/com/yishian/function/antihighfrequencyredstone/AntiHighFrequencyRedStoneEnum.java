package com.yishian.function.antihighfrequencyredstone;

import com.yishian.common.CommonPluginEnum;

/**
 * @author XinQi
 */
public enum AntiHighFrequencyRedStoneEnum {

    /**
     * 高频红石功能的名称
     */
    ANTI_HIGH_FREQUENCY_RED_STONE("anti-high-frequency-red-stone", "高频红石功能名称"),
    /**
     * 高频红石信息权限
     */
    RED_STONE_MESSAGE_PERMISSION(CommonPluginEnum.PLUGHIN_NAME.getCommand() + ".RedStoneDestroyMessage", "高频红石信息权限");

    private final String command;
    private final String msg;

    AntiHighFrequencyRedStoneEnum(String command, String msg) {
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
