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
    HEAL_OTHERS_NO_EXIST("heal-others-no-exist", "&c玩家&3%others-player%&c不存在，请检查玩家名字"),
    HEAL_OTHERS_IS_SELF("heal-others-is-self", "你已恢复状态，你可以使用不带参数的指令来恢复自己"),
    HEAL_OTHERS_NO_PERMISSION("heal-others-no-permission", "&c你没有执行恢复他人状态指令的权限"),
    HEAL_BY_OTHERS("heal-by-others", "你已被&3%player%&6恢复状态"),
    HEAL_BY_CONSOLE("heal-by-console", "你已被服务器恢复状态"),
    HEAL_CONSOLE_ERROR("heal-console-error", "&c控制台请执行恢复他人状态的指令: &6heal <玩家名称>"),
    HEAL_COMMAND_ERROR("heal-command-error", "&c恢复状态指令格式错误，正确格式: &6/heal [玩家名称]");

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
