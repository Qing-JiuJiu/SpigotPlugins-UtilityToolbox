package com.yishian.customjoinandleave;

import com.yishian.currency.CommandEnum;

/**
 * @author XinQi
 */
public enum CustomJoinAndLeaveCommandEnum {

    //获取自定义加入和离开的名字
    CUSTOMJOINANDLEAVE_NAME("CustomJoinAndLeave", "获取CustomJoinAndLeave插件名字");

    private final String command;
    private final String msg;

    CustomJoinAndLeaveCommandEnum(String command, String msg) {
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
