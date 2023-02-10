package com.yishian.command.copyres;

import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

/**
 * @author XinQi
 */
public class CopyResCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否带参数
        if (args.length != 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + CopyResConfigEnum.COPYRES_COMMAND_ERROR.getMsg()));
            return true;
        }

        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + CopyResConfigEnum.COPYRES_CONSOLE_ERROR.getMsg()));
            return true;
        }

        //得到玩家手上的物品
        Player player = (Player) sender;
        PlayerInventory playerInventory = player.getInventory();
        ItemStack itemInMainHand = playerInventory.getItemInMainHand();

        //获得物品名称
        String itemName = itemInMainHand.getType().getKey().toString();
        //得到排除列表
        List<?> itemExcludeList = CommonUtils.objectToList(CopyResConfigEnum.EXCLUDE_LIST.getMsg());
        //得到黑名单
        List<?> itemBlackList = CommonUtils.objectToList(CopyResConfigEnum.BLACK_LIST.getMsg());

        //判断是否在排除列表
        if (!itemExcludeList.contains(itemName)) {
            //判断黑名单
            if (itemBlackList.contains(itemName)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + CopyResConfigEnum.COPYRES_DENY.getMsg().toString().replaceAll("%res%", itemName)));
                return true;
            }

            //得到白名单
            List<?> itemWildcardList = CommonUtils.objectToList(CopyResConfigEnum.WILDCARD_LIST.getMsg());

            //判断是否在通配符名单
            if (!CommonUtils.collectionIsEmpty(itemWildcardList)) {
                for (Object itemWildcard : itemWildcardList) {
                    if (itemName.startsWith(itemWildcard.toString())) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + CopyResConfigEnum.COPYRES_DENY.getMsg().toString().replaceAll("%res%", itemName)));
                        return true;
                    }
                }
            }
        }

        //修改物品数据让玩家获得最大物品并发送消息
        ItemStack cloneItemStack = itemInMainHand.clone();
        cloneItemStack.setAmount(cloneItemStack.getMaxStackSize());
        playerInventory.addItem(cloneItemStack);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + CopyResConfigEnum.COPYRES_APPLY.getMsg().toString().replaceAll("%res%", itemName)));

        //控制台输出信息
        CommonUtils.sendConsoleMessage("玩家" + player.getName() + "复制了物品：" + itemName);

        //执行成功
        return true;
    }
}
