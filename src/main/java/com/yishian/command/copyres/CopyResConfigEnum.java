package com.yishian.command.copyres;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author XinQi
 */
public enum CopyResConfigEnum {

    /**
     * 排除列表
     */
    EXCLUDE_LIST("exclude-list", new ArrayList<>()),
    /**
     * 黑名单
     */
    BLACK_LIST("black-list", Arrays.asList("minecraft:enchanted_golden_apple",
            "minecraft:golden_apple",
            "minecraft:golden_carrot",
            "minecraft:gold_ingot",
            "minecraft:gold_nugget",
            "minecraft:gold_block",
            "minecraft:tnt",
            "minecraft:gunpowder",
            "minecraft:raw_gold_block",
            "minecraft:raw_gold"
    )),
    /**
     * 白名单
     */
    WILDCARD_LIST("wildcard-list", new ArrayList<>()),

    /**
     * 提示消息列表
     */
    COPYRES_APPLY("copyres-apply", "&a你已复制&3%res%&a物品"),
    COPYRES_DENY("copyres-deny", "&c物品&3%res%&c禁止复制");

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

    CopyResConfigEnum(String tag, Object msg) {
        this.tag = tag;
        this.msg = msg;
    }

}
