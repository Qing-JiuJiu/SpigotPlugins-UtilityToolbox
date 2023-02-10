package com.yishian.function.limithighaltitudefluids;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author XinQi
 */
public enum LimitHighAltitudeFluidConfigEnum {

    /**
     * 相关配置
     */
    ENABLE("enable", false),
    IS_BROADCAST_MESSAGE("is-broadcast-message", true),
    LIMIT_HIGH("limit-high", 80),
    LIMIT_FLUID_LIST("limit-fluid-list",Arrays.asList("minecraft:water","minecraft:lava")),
    LIMIT_WORLD_LIST("limit-world-list", Collections.singletonList("world")),
    /**
     * 提示消息列表
     */
    DESTROY_MESSAGE("destroy-message", "玩家&3%player%&6附近存在高空流体，坐标&cx:%x% y:%y% z:%z%&6，已限制");

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

    LimitHighAltitudeFluidConfigEnum(String tag, Object msg) {
        this.tag = tag;
        this.msg = msg;
    }

}
