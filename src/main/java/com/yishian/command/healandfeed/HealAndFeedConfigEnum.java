package com.yishian.command.healandfeed;

/**
 * @author XinQi
 */
public enum HealAndFeedConfigEnum {

    /**
     * 提示消息列表
     */
    HEAL_AND_FEED_SELF("heal-and-feed-self", "&a你已恢复生命值和饱食度"),
    HEAL_AND_FEED_OTHERS("heal-and-feed-others", "&a你已恢复&3%others-player%&a的生命值和饱食度"),
    HEAL_AND_FEED_OTHERS_NO_EXIST("heal-and-feed-others-no-exist", "&c玩家&3%others-player%&c不存在，请检查玩家名字"),
    HEAL_AND_FEED_OTHERS_IS_SELF("heal-and-feed-others-is-self", "你已恢复生命值和饱食度，可以使用不带参数的指令来恢复自己"),
    HEAL_AND_FEED_OTHERS_NO_PERMISSION("heal-and-feed-others-no-permission", "&c你没有执行恢复他人生命值和饱食度指令的权限"),
    HEAL_AND_FEED_BY_OTHERS("heal-and-feed-by-others", "你已被&3%player%&6恢复生命值和饱食度"),
    HEAL_AND_FEED_BY_CONSOLE("heal-and-feed-by-console", "你已被服务器恢复生命值和饱食度"),
    HEAL_AND_FEED_CONSOLE_ERROR("heal-and-feed-console-error", "&c控制台请执行恢复他人生命值和饱食度的指令: &6healandfeed <玩家名称>"),
    HEAL_AND_FEED_COMMAND_ERROR("heal-and-feed-command-error", "&c恢复生命值和饱食度指令格式错误，正确格式: &6/healandfeed [玩家名称]");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    HealAndFeedConfigEnum(String tag, String msg) {
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
