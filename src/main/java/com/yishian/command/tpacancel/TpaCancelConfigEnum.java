package com.yishian.command.tpacancel;

/**
 * @author XinQi
 */
public enum TpaCancelConfigEnum {

    /**
     * 提示消息列表
     */
    TPACANCEL_APPLY("tpacancel-apply", "&a已取消传送请求"),
    TPACANCEL_OTHERS("tpacancel-others", "玩家&3%player%&6已取消传送请求"),
    TPACANCEL_NO_TPA_ERROR("tpacancel-no-tpa-error", "&c你没有待处理的传送请求");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    TpaCancelConfigEnum(String tag, Object msg) {
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
