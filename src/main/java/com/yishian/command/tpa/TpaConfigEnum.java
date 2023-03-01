package com.yishian.command.tpa;

/**
 * @author XinQi
 */
public enum TpaConfigEnum {

    /**
     * 提示消息列表
     */
    TPA_OTHERS_NO_EXIST("tpa-others-no-exist", "&c玩家&3%others-player%&c不存在，请检查玩家名字"),
    TPA_OTHERS_IDENTICAL("tpa-others-identical", "&c请不要发送重复传送请求"),
    TPA_AUTO_TPACANCEL("tpa-auto-tpacancel", "已自动取消上一个传送请求"),
    TPA_AUTO_TPACANCEL_OTHERS("tpa-auto-tpacancel-others", "玩家&3%player%&6已取消传送请求"),
    TPA_APPLY("tpa-apply", "&a传送请求已发送给玩家&3%others-player%"),
    TPA_APPLY_OTHERS("tpa-apply-others", "玩家&3%player%&6请求传送到你这里"),
    TPA_APPLY_TPACANCEL_TIPS("tpa-apply-tpacancel-tips", "取消请求输入: &c/tpacancel"),
    TPA_APPLY_ACCEPT_TIPS("tpa-apply-accept-tips", "同意请求输入: &a/tpaccept"),
    TPA_APPLY_DENY_TIPS("tpa-apply-deny-tips", "拒绝请求输入: &c/tpadeny"),
    TPA_COMMAND_ERROR("tpa-command-error", "&c传送指令格式错误，正确格式: &6/tpa <其他玩家名称>"),
    TPA_OTHERS_LEAVE_SERVER("tpa-others-leave-server", "玩家&3%others-player%&6已离开服务器，已自动取消其传送请求");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    TpaConfigEnum(String tag, Object msg) {
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
