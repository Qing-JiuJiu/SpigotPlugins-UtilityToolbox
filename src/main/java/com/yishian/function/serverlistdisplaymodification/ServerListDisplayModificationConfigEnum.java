package com.yishian.function.serverlistdisplaymodification;

/**
 * @author XinQi
 */
public enum ServerListDisplayModificationConfigEnum {

    /**
     * 相关配置
     */
    ENABLE("enable", false),
    MAX_PLAYER("max-player", 20),
    ICON("icon", "icon.png"),

    /**
     * 提示消息列表
     */
    FIRST_LINE("first-line", "&e[UtilityToolbox] &6欢迎使用本插件"),
    SECOND_LINE("second-line", "&e[UtilityToolbox] &6欢迎使用本插件");

    private final String tag;
    private Object msg;

    public String getTag() {
        return tag;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    ServerListDisplayModificationConfigEnum(String tag, Object msg) {
        this.tag = tag;
        this.msg = msg;
    }

}
