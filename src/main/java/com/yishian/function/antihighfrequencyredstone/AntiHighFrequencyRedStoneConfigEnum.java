package com.yishian.function.antihighfrequencyredstone;

import java.util.Arrays;

/**
 * @author XinQi
 */
public enum AntiHighFrequencyRedStoneConfigEnum {

    /**
     * 相关配置
     */
    ENABLE("enable", false),
    IS_BROADCAST_MESSAGE("is-broadcast-message", true),
    LIMIT("limit", 50),
    TIME("time", 5),
    ANTI_RED_STONE_LIST("anti-red-stone-list", Arrays.asList("minecraft:redstone_wire","minecraft:lever")),

    /**
     * 提示消息列表
     */
    DESTROY_MESSAGE("destroy-message", "玩家&3%player%&6附近存在高频红石，坐标&cx:%x% y:%y% z:%z%&6，已摧毁");

    private final String tag;
    private Object msg;

    public String getTag() {
        return tag;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    AntiHighFrequencyRedStoneConfigEnum(String tag, Object msg) {
        this.tag = tag;
        this.msg = msg;
    }

}
