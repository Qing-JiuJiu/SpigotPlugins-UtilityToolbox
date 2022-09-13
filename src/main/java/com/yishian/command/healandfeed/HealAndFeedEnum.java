package com.yishian.command.healandfeed;

/**
 * @author XinQi
 */
public enum HealAndFeedEnum {

    /**
     * 恢复生命值和饱食度
     */
    HEAL_AND_FEED_COMMAND("healandfeed", "恢复生命值和饱食度的指令");

    private final String command;
    private final String msg;

    HealAndFeedEnum(String command, String msg) {
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
