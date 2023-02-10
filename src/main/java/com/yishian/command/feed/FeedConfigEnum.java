package com.yishian.command.feed;

/**
 * @author XinQi
 */
public enum FeedConfigEnum {

    /**
     * 提示消息列表
     */
    FEED_SELF("feed-self", "&a你已恢复饱食度"),
    FEED_OTHERS("feed-others", "&a你已恢复&3%others-player%&a的饱食度"),
    FEED_OTHERS_NO_EXIST("feed-others-no-exist", "&c玩家&3%others-player%&c不存在，请检查玩家名字"),
    FEED_OTHERS_IS_SELF("feed-others-is-self", "你已恢复饱食度，你可以使用不带参数的指令来恢复自己"),
    FEED_OTHERS_NO_PERMISSION("feed-others-no-permission", "&c你没有执行恢复他人饱食度指令的权限"),
    FEED_BY_OTHERS("feed-by-others", "你已被&3%player%&6恢复饱食度"),
    FEED_BY_CONSOLE("feed-by-console", "你已被服务器恢复饱食度"),
    FEED_CONSOLE_ERROR("feed-console-error", "&c控制台请执行恢复他人饱食度的指令: &6feed <玩家名称>"),
    FEED_COMMAND_ERROR("feed-command-error", "&c恢复饱食度指令格式错误，正确格式: &6/feed [玩家名称]");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    FeedConfigEnum(String tag, String msg) {
        this.tag = tag;
        this.msg = msg;
    }

    public String getTag() {
        return tag;
    }

    public Object getMsg() {
        return msg;
    }
}
