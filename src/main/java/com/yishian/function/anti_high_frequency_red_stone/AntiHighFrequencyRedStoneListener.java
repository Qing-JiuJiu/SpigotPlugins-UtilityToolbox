package com.yishian.function.anti_high_frequency_red_stone;

import com.yishian.common.PluginUtils;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

import java.util.List;


/**
 * @author XinQi
 */
public class AntiHighFrequencyRedStoneListener implements Listener {

    @EventHandler
    public void highFrequencyRedStoneListener(BlockRedstoneEvent blockRedstoneEvent) {
        List<String> antiList = PluginUtils.getServerConfig().getConfigurationSection("anti-high-frequency-red-stone").getStringList("anti-red-stone-list");
        //得到那个块
        Block block = blockRedstoneEvent.getBlock();
        //比对命名空间，如果对比成功，将物品登记一次
        if (antiList.contains(block.getType().getKey().toString())) {
            AntiHighFrequencyRedStoneRunnable.detectList.merge(block.getLocation(), 1, Integer::sum);
        }
    }

}
