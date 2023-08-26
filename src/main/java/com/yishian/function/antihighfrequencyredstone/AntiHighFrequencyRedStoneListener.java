package com.yishian.function.antihighfrequencyredstone;

import com.yishian.common.CommonUtil;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

/**
 * @author XinQi
 */
public class AntiHighFrequencyRedStoneListener implements Listener {

    /**
     * 登记红石出现次数
     */
    @EventHandler
    public void highFrequencyRedStoneListener(BlockRedstoneEvent blockRedstoneEvent) {
        //得到那个块
        Block block = blockRedstoneEvent.getBlock();
        //比对命名空间，如果对比成功，将物品登记一次
        if (CommonUtil.objectToList(AntiHighFrequencyRedStoneConfigEnum.ANTI_RED_STONE_LIST.getMsg()).contains(block.getType().getKey().toString())) {
            AntiHighFrequencyRedStoneRunnable.detectList.merge(block.getLocation(), 1, Integer::sum);
        }
    }

}
