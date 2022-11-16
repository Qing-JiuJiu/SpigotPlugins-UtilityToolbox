package com.yishian.function.preventhighfrequencyattacks;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/*TODO
   该方案有一定的问题：
   1，PlayerDropItemEvent事件只有看空气时才会触发PlayerInteractEvent事件，如果PlayerDropItemEvent看地上触发，在下一次PlayerInteractEvent事件会因为isDropItem判定导致丢失一次登记
   2，对着空中右键丢药水时会触发一次左键登记
   暂定可能能解决的方案：
   1，PlayerDropItemEvent触发时判断玩家准星准星是否是空气
   2，添加使用物品事件监听，添加识别字段
*/

/**
 * @author XinQi
 */
public class PreventHighFrequencyAttacksListener implements Listener {

    /**
     * 是否是丢弃物品
     */
    Boolean isDropItem = false;

    /**
     * 用户点击一次就登记一次
     */
    @EventHandler
    public void playerClick(PlayerInteractEvent playerInteractEvent) {
        //获得玩家动作类型
        Action playerInteractEventAction = playerInteractEvent.getAction();
        //判断是左键且不是红石触发的
        if (playerInteractEventAction == Action.LEFT_CLICK_AIR || playerInteractEventAction == Action.LEFT_CLICK_BLOCK) {
            //判断是否是丢弃物品触发的
            if (isDropItem) {
                isDropItem = false;
            } else {
                //登记一次
                PreventHighFrequencyAttacksRunnable.detectList.merge(playerInteractEvent.getPlayer().getUniqueId(), 1, Integer::sum);
            }
        }
    }

    /**
     * 实体收到伤害事件
     */
    @EventHandler
    public void playerAttack(EntityDamageByEntityEvent entityDamageByEntityEvent) {
        //获得造成伤害的实体
        Entity entity = entityDamageByEntityEvent.getDamager();
        assert entity instanceof Player;
        //ENTITY_ATTACK 玩家攻击就登记一次
        if (entityDamageByEntityEvent.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            PreventHighFrequencyAttacksRunnable.detectList.merge(entity.getUniqueId(), 1, Integer::sum);
        }
    }

    /**
     * 丢物品时给予变量为true，方便触发点击事件时判定是丢弃物品造成的
     */
    @EventHandler
    public void playerDropItem(PlayerDropItemEvent playerDropItemEvent) {
        isDropItem = true;
    }

}
