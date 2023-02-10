package com.yishian.command.utilitytoolbox;

/**
 * @author XinQi
 */
public enum UtilityToolboxConfigEnum {

    /**
     * 提示消息列表
     */
    UTILITYTOOLBOX_COMMAND_ERROR("utilitytoolbox-command-error", "&c子指令无效或无子指令，正确格式: &6/UtilityToolbox <子指令>"),
    UTILITYTOOLBOX_APPLY_RELOAD("utilitytoolbox-apply-reload", "&a配置文件重载成功");

    private final String tag;
    private Object msg;

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    UtilityToolboxConfigEnum(String tag, Object msg) {
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
