package com.yishian.command.tpaccept;

/**
 * @author XinQi
 */
public enum TpaCceptConfigEnum {

    /**
     * 提示消息列表
     */
    TPACCEPT_APPLY("tpaccept-apply", "&a你已接受玩家&3%others-player%&a的传送请求"),
    TPACCEPT_APPLY_IS_SELF("tpaccept-apply-is-self", "&c请不要接受自己的传送请求"),
    TPACCEPT_APPLY_OTHERS("tpaccept-apply-others", "你向玩家&3%player%&6申请的传送请求已被接受"),
    TPACCEPT_CONSOLE_ERROR("tpaccept-console-error", "&c控制台无法使用申请传送相关指令"),
    TPACCEPT_OTHERS_NO_EXIST("tpaccept-others-no-exist", "&c玩家&3%others-player%&c不存在，请检查玩家名字"),
    TPACCEPT_COMMAND_ERROR("tpaccept-command-error", "&c同意请求指令格式错误，正确格式: &6/tpaccept [其他玩家名称]"),
    TPACCEPT_NO_TPA_ERROR("tpaccept-no-tpa-error", "&c你没有待处理的传送请求"),
    TPACCEPT_NO_OTHERS_PLAYER_TPA_ERROR("tpaccept-no-others-player-tpa-error", "&c你没有玩家&3%others-player%&c的传送请求");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    TpaCceptConfigEnum(String tag, Object msg) {
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
