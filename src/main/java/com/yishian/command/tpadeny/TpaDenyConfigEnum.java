package com.yishian.command.tpadeny;

/**
 * @author XinQi
 */
public enum TpaDenyConfigEnum {

    /**
     * 提示消息列表
     */
    TPADENY_APPLY("tpadeny-apply", "&a你已拒绝玩家&3%others-player%&a的传送请求"),
    TPADENY_APPLY_IS_SELF("tpadeny-apply-is-self", "&c请不要拒绝自己的传送请求"),
    TPADENY_APPLY_OTHERS("tpadeny-apply-others", "你向玩家&3%player%&6申请的传送请求已被拒绝"),
    TPADENY_OTHERS_NO_EXIST("tpadeny-others-no-exist", "&c玩家&3%others-player%&c不存在，请检查玩家名字"),
    TPADENY_NO_TPA_ERROR("tpadeny-no-tpa-error", "&c你没有待处理的传送请求"),
    TPADENY_NO_OTHERS_PLAYER_TPA_ERROR("tpadeny-no-others-player-tpa-error", "&c你没有玩家&3%others-player%&c的传送请求");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    TpaDenyConfigEnum(String tag, Object msg) {
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
