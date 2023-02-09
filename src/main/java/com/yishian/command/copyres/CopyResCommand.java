package com.yishian.command.copyres;

import com.yishian.common.CommonConfigLoad;
import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import java.util.List;

/**
 * @author XinQi
 */
public class CopyResCommand implements CommandExecutor {

    /**
     * 获取配置文件里该指令的配置文件
     */
    static ConfigurationSection copyResConfigurationSection = CommonConfigLoad.ServerConfig.getConfigurationSection(CopyResEnum.COPY_RES_COMMAND.getCommand());
    /**
     * 获取配置文件里该指令的消息提示
     */
    static ConfigurationSection copyResMessage = copyResConfigurationSection.getConfigurationSection(CommonEnum.MESSAGE.getCommand());
    /**
     * 黑名单
     */
    static List<String> itemBlackList = copyResConfigurationSection.getStringList("black-list");
    /**
     * 白名单
     */
    static List<String> itemWildcardList = copyResConfigurationSection.getStringList("wildcard-list");
    /**
     * 排除列表
     */
    static List<String> itemExcludeList = copyResConfigurationSection.getStringList("exclude-list");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断指令是否带参数
        if (args.length != 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + copyResMessage.getString("copyres-command-error")));
            return true;
        }

        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + copyResMessage.getString("copyres-console-error")));
            return true;
        }

        //得到玩家手上的物品
        Player player = (Player) sender;
        PlayerInventory playerInventory = player.getInventory();
        ItemStack itemInMainHand = playerInventory.getItemInMainHand();

        //获得物品名称
        String itemName = itemInMainHand.getType().getKey().toString();

        //判断是否在排除列表
        if (!itemExcludeList.contains(itemName)) {
            //判断黑名单
            if (itemBlackList.contains(itemName)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + copyResMessage.getString("copyres-deny").replaceAll("%res%", itemName)));
                return true;
            }

            //判断是否在通配符名单
            if (!CommonUtils.collectionIsEmpty(itemWildcardList)) {
                for (String itemWildcard : itemWildcardList) {
                    if (itemName.startsWith(itemWildcard)) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + copyResMessage.getString("copyres-deny").replaceAll("%res%", itemName)));
                        return true;
                    }
                }
            }
        }

        //修改物品数据让玩家获得最大物品并发送消息
        ItemStack cloneItemStack = itemInMainHand.clone();
        cloneItemStack.setAmount(cloneItemStack.getMaxStackSize());
        playerInventory.addItem(cloneItemStack);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommonEnum.MESSAGE_PREFIX.getCommand() + copyResMessage.getString("copyres-apply").replaceAll("%res%", itemName)));

        //控制台输出信息
        CommonUtils.sendConsoleMessage("玩家" + player.getName() + "复制了物品：" + itemName);

        //执行成功
        return true;
    }
}
