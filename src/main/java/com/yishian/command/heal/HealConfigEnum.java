package com.yishian.command.heal;

/**
 * @author XinQi
 */
public enum HealConfigEnum{

    /**
     * 提示消息列表
     */
    HEAL_SELF("heal-self", "&a你已恢复状态"),
    HEAL_OTHERS("heal-others", "&a你已恢复&3%others-player%&a的状态"),
    HEAL_BY_CONSOLE("heal-by-console", "你已被服务器恢复状态"),
    HEAL_CONSOLE_ERROR("heal-console-error", "&c控制台执行恢复他人状态的指令格式: &6heal <玩家名称>");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    HealConfigEnum(String tag, String msg) {
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
