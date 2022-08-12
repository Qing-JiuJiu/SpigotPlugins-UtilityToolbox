package com.yishian.customjoinandleave;

import com.yishian.currency.CommandEnum;

/**
 * @author XinQi
 */
public enum CustomJoinAndLeaveCommandEnum {

    //获取自定义加入和离开的名字
    CUSTOMJOINANDLEAVE_NAME("CustomJoinAndLeave", "获取CustomJoinAndLeave插件名字"),

    //获取自定义加入与离开权限
    CUSTOMJOINANDLEAVE_PERMISSION(CommandEnum.PLUGHIN_NAME.getCommand() + "." + CUSTOMJOINANDLEAVE_NAME.command, "获取插件权限名称"),

    //重载配置文件 还未测试
    RELOAD("hello", "重载配置文件成功");


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
