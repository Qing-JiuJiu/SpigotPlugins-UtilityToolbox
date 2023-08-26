package com.yishian.command.showtextcolor;

import com.yishian.common.CommonPluginEnum;

/**
 * @author XinQi
 */
public enum ShowTextCodeEnum {

    /**
     * 展示文本代
     */
    SHOW_TEXT_CODE_COMMAND("showtextcode", "展示文本代码指令"),

    SHOW_TEXT_CODE_PERMISSION(CommonPluginEnum.PLUGHIN_NAME.getCommand() + "." + SHOW_TEXT_CODE_COMMAND.getCommand(), "展示颜色代码权限");

    private final String command;
    private final String msg;

    ShowTextCodeEnum(String command, String msg) {
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
