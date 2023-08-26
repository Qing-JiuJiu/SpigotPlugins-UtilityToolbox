package com.yishian.common;

/**
 * @author XinQi
 * 插件通用提示消息枚举
 */
public enum CommonMessageEnum {

    /**
     * 插件通用提示消息
     */
    MESSAGE_PREFIX("message-prefix", "&e[UtilityToolbox] &6"),
    CONSOLE_COMMAND_NO_USE("console-command-no-use", "&c控制台无法使用该指令"),
    CONSOLE_USE_OFFICIAL_COMMAND_TIPS("console-use-official-command-tips", "&c控制台请使用官方指令代替该指令"),
    PLAYER_NO_EXIST("player-no-exist","&c玩家&3%others-player%&c不存在，请检查玩家名字");

    private final String tag;
    private String msg;

    CommonMessageEnum(String tag, String msg) {
        this.tag = tag;
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTag() {
        return tag;
    }

    public String getMsg() {
        return msg;
    }
}
