package com.yishian.function.serverlistdisplaymodification;

/**
 * @author XinQi
 */
public enum ServerListDisplayModificationEnum {

    /**
     * 服务器列表显示修改的功能名称
     */
    SERVER_LIST_DISPLAY_MODIFICATION("server-list-display-modification", "服务器列表显示修改的功能名称");

    private final String command;
    private final String msg;

    ServerListDisplayModificationEnum(String command, String msg) {
        this.command = command;
        this.msg = msg;
    }

    public String getCommand() {
        return command;
    }

    public String getMsg() {
        return msg;
    }
}
