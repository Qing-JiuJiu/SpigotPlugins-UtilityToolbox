package com.yishian.command.musterplayer;

/**
 * @author XinQi
 */
public enum MusterPlayerConfigEnum {
    /*
     * 传送等待时间
     */
    TIME("time", 3),

    /**
     * 提示消息列表
     */
    MUSTERPLAYER_APPLY_START("musterplayer-apply-start", "&a已广播召集所有玩家到此位置，请等待所有玩家处理请求"),
    MUSTERPLAYER_APPLY_START_REPEAT("musterplayer-apply-start-repeat", "&c玩家&3%player%&c已发起召集请求，请等待该召集结束后再发起召集"),
    MUSTERPLAYER_APPLY_START_ERROR("musterplayer-apply-start-error", "&c无其他在线玩家，请不要发起召集玩家请求"),
    MUSTERPLAYER_APPLY_START_OTHERS("musterplayer-apply-start-others", "玩家&3%player%&6正在召集所有玩家，请及时处理请求"),
    MUSTERPLAYER_APPLY_START_OTHERS_REASON("musterplayer-apply-start-others-reason", "玩家&3%player%&6正在召集所有玩家，原因: &c%reason%&c，请及时处理请求"),
    MUSTERPLAYER_APPLY_AGAIN("musterplayer-apply-again", "&a已发起上一次召集请求，请等待所有玩家处理请求"),
    MUSTERPLAYER_APPLY_AGAIN_ERROR("musterplayer-apply-again-error", "&c没有上一次召集请求，请使用&6/musterplayer start&c指令发起新的召集"),
    MUSTERPLAYER_APPLY_CANCEL_CONSLOE("musterplayer-apply-cancel-consloe", "控制台已取消此次召集请求"),
    MUSTERPLAYER_APPLY_CANCEL_PLAYER("musterplayer-apply-cancel-player", "&a你已取消此次召集请求"),
    MUSTERPLAYER_APPLY_CANCEL_PLAYER_OTHERS("musterplayer-apply-cancel-player-others", "玩家&c%player%&6已取消此次召集请求"),
    MUSTERPLAYER_APPLY_END("musterplayer-apply-end", "玩家&3%player%&6已提前结束召集，同意召集的玩家已传送至召集位置"),
    MUSTERPLAYER_APPLY_END_TIME("musterplayer-apply-end-time", "玩家&3%player%&6提前结束召集，同意召集的玩家将在&c%time%&6秒后立即传送"),
    MUSTERPLAYER_APPLY_END_TP("musterplayer-apply-end-tp", "你已传送至召集位置"),
    MUSTERPLAYER_END("musterplayer-end", "已完成召集，同意召集的玩家已传送至召集位置"),
    MUSTERPLAYER_END_TIME("musterplayer-end-time", "已完成召集，同意召集玩家将在&c%time%秒&6后立即传送"),
    MUSTERPLAYER_APPLY_CCEPT("musterplayer-apply-ccept", "玩家&3%player%&6已接受召集"),
    MUSTERPLAYER_APPLY_DENY("musterplayer-apply-deny", "玩家&c%player%&6已拒绝召集"),
    MUSTERPLAYER_APPLY_LIST_ALLOW("musterplayer-apply-list-allow", "已同意召集的玩家: &a%player-list%"),
    MUSTERPLAYER_APPLY_LIST_DENY("musterplayer-apply-list-deny", "已拒绝召集的玩家: &c%player-list%"),
    MUSTERPLAYER_APPLY_LIST_DEAL_WITH("musterplayer-apply-list-deal-with", "还未处理召集的玩家: &7%player-list%"),
    MUSTERPLAYER_CANCEL_TIPS("musterplayer-cancel-tips", "取消召集输入: &c/musterplayer cancel"),
    MUSTERPLAYER_END_TIPS("musterplayer-end-tips", "结束召集输入: &c/musterplayer end"),
    MUSTERPLAYER_ACCEPT_TIPS("musterplayer-accept-tips", "同意召集输入: &a/musterplayer ccept"),
    MUSTERPLAYER_DENY_TIPS("musterplayer-deny-tips", "拒绝召集输入: &c/musterplayer deny"),
    MUSTERPLAYER_OTHERS_TIPS("musterplayer-others-tips", "所有玩家处理召集请求后或发起者提前结束召集，才会进行传送"),
    MUSTERPLAYER_APPLY_ERROR("musterplayer-apply-error", "&c没有需要处理的召集请求"),
    MUSTERPLAYER_PLAYER_LEAVE_CONVENOR("musterplayer-player-leave-convenor", "召集者&3%player%&6已离开服务器，已取消此次召集"),
    MUSTERPLAYER_PLAYER_LEAVE_CALLEE("musterplayer-player-leave-callee", "被召集者&3%player%&6已离开服务器，已在被召集列表中移除该玩家"),
    MUSTERPLAYER_CONSOLE_ERROR("musterplayer-console-error", "&c控制台仅能使用&6musterplayer cancel&c指令用于取消玩家召集"),
    MUSTERPLAYER_COMMAND_ERROR("musterplayer-command-error", "&c召集指令错误，正确格式: &6/musterplayer <参数>");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    MusterPlayerConfigEnum(String tag, Object msg) {
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
