package com.yishian.command.tpr;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author XinQi
 */
public enum TprConfigEnum {
    /**
     * 允许列表
     */
    ALLOW_WORLD("allow-world", Collections.singletonList("*")),

    /**
     * 禁止随机到的列表，方便后期维护
     */
    DANGEROUS_BLOCK("dangerous-block", Arrays.asList("minecraft:water","minecraft:lava","minecraft:cactus","minecraft:magma_block","minecraft:spawner","minecraft:fire","minecraft:soul_fire")),

    /**
     * 随机传送范围
     */
    RANDOM_X("random-x", 10000),
    RANDOM_Z("random-z", 10000),

    /**
     * 是否以重生点为中心进行随机传送
     */
    RESPAWN_CENTER("respawn-center", true),

    /**
     * 提示消息列表
     */
    TPR_SEARCHING("tpr-searching","&6正在定位安全的传送位置，请不要连续使用随机传送"),
    TPR_APPLY("tpr-apply", "&a你已随机传送，当前坐标 &cx:%x% y:%y% z:%z%"),
    TPR_WORLD_ERROR("tpr-world-error", "&c世界&3%world%&c禁止随机传送"),
    TPR_CONSOLE_ERROR("tpr-console-error", "&c控制台无法使用随机传送相关指令");


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

    TprConfigEnum(String tag, Object msg) {
        this.tag = tag;
        this.msg = msg;
    }

}
