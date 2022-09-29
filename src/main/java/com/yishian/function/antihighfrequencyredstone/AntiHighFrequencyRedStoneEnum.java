package com.yishian.function.antihighfrequencyredstone;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum AntiHighFrequencyRedStoneEnum {

    /**
     * 高频红石信息权限
     */
    RED_STONE_MESSAGE_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + ".RedStoneDestroyMessage", "高频红石信息");

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
