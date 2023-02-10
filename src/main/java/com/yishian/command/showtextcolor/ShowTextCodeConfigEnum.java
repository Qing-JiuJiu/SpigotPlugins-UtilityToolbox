package com.yishian.command.showtextcolor;

import java.util.Arrays;

/**
 * @author XinQi
 */
public enum ShowTextCodeConfigEnum {
    /**
     * 提示消息列表
     */
    SHOWTEXTCODE_COMMAND_ERROR("showtextcode-command-error", "&a已将此位置设为家"),
    SHOW_LIST("show-list", Arrays.asList("&c文本代码列表------------------------------",
            "&11 深紫 &7/ &99（淡紫）",
            "&22 墨绿 &7/ &aa（碧绿）",
            "&33 深蓝 &7/ &bb（天蓝）",
            "&44 深红 &7/ &cc（大红）",
            "&55 粉红 &7/ &dd（亮粉红）",
            "&66 橙色 &7/ &ee（金色）",
            "&77 浅灰 &7/ &ff（白色）",
            "&88 深灰 &7/ &00（黑色）",
            "k是乱码(有非英文字符就会无效，不展示)",
            "&ll是粗体字",
            "&oo是斜体字",
            "&rr是恢复到原始效果",
            "&mm是删除线",
            "&nn是下划线",
            "&c展示列表结束------------------------------"
            ));

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

    ShowTextCodeConfigEnum(String tag, Object msg) {
        this.tag = tag;
        this.msg = msg;
    }

}
