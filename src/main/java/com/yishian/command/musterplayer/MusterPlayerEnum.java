package com.yishian.command.musterplayer;


import com.yishian.common.CommonPluginEnum;

/**
 * @author XinQi
 */
public enum MusterPlayerEnum {

    /**
     * 召集玩家
     */
    MUSTER_PLAYER_COMMAND("musterplayer", "召集玩家"),
    MUSTER_PLAYER_PERMISSION(CommonPluginEnum.PLUGHIN_NAME.getCommand() + "." + MUSTER_PLAYER_COMMAND.getCommand(), "召集玩家的权限"),

    /**
     * 指令参数
     */
    START("start", "发起召集"),
    CANCEL("cancel", "取消召集"),
    END("end", "结束召集"),
    CCEPT("ccept", "同意召集"),
    DENY("deny", "拒绝召集"),
    AGAIN("again", "重新发起服务器上一次召集"),
    LIST("list", "查看召集列表");

    private final String command;
    private final String msg;

    MusterPlayerEnum(String command, String msg) {
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
