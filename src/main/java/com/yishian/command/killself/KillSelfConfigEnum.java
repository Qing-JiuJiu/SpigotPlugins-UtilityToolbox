package com.yishian.command.killself;

/**
 * @author XinQi
 */
public enum KillSelfConfigEnum {

    /**
     * 提示消息列表
     */
    KILLSELF_APPLY("killself-apply", "&a已让服务器执行相关指令");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    KillSelfConfigEnum(String tag, String msg) {
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
