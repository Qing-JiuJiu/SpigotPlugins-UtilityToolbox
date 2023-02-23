package com.yishian.command.teleport;

/**
 * @author XinQi
 */
public enum TeleportConfigEnum {

    /**
     * 提示消息列表
     */
    TELEPORT_ALLOW("teleport-allow", "&a服务器已恢复使用指令传送"),
    TELEPORT_ALLOW_REASON("teleport-allow-reason", "&a服务器已恢复使用指令传送，原因: &6%reason%"),
    TELEPORT_DENY("teleport-deny", "&c服务器已临时禁止指令传送"),
    TELEPORT_DENY_APPLY("teleport-deny-apply", "&c服务器已临时禁止指令传送，传送失败"),
    TELEPORT_DENY_REASON("teleport-deny-reason", "&c服务器已临时禁止指令传送，原因: &6%reason%"),
    TELEPORT_COMMAND_ERROR("teleport-command-error", "&c服务器是否允许指令传送格式错误，正确格式: &6/teleport [reason]");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    TeleportConfigEnum(String tag, Object msg) {
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
