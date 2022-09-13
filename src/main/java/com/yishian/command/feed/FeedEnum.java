package com.yishian.command.feed;

import com.yishian.common.CommonEnum;

/**
 * @author XinQi
 */
public enum FeedEnum {

    /**
     * 恢复饱食度
     */
    FEED_COMMAND("feed", "恢复饱食度的指令"),
    FEED_PERMISSION(CommonEnum.PLUGHIN_NAME.getCommand() + "." + FEED_COMMAND.getCommand(), "恢复自己饱食度的权限"),
    FEED_OTHERS_PERMISSION(FEED_PERMISSION.getCommand() + "." + CommonEnum.OTHERS.getCommand(), "恢复他人饱食度的权限");

    private final String command;
    private final String msg;

    FeedEnum(String command, String msg) {
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
