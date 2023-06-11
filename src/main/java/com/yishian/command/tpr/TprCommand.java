package com.yishian.command.tpr;

import com.yishian.command.teleport.TeleportCommand;
import com.yishian.common.CommonEnum;
import com.yishian.common.CommonUtils;
import com.yishian.common.PluginMessageConfigEnum;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author XinQi
 */
public class TprCommand implements CommandExecutor {

    //新建一个随机数
    Random rand = new Random();

    //用于存储基岩方块
    List<Block> blockList = new ArrayList<>();

    //危险方块列表
    List<?> dangerousBlockList = CommonUtils.objectToList(TprConfigEnum.DANGEROUS_BLOCK.getMsg());

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //判断执行指令的是用户还是控制台
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + PluginMessageConfigEnum.CONSOLE_COMMAND_NO_USE.getMsg()));
            return true;
        }

        //获取玩家位置变量，并发送给玩家等待传送
        Player player = (Player) sender;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + TprConfigEnum.TPR_SEARCHING.getMsg()));
        Location playerLocation = player.getLocation();

        //判断是否是允许随机传送
        World world = playerLocation.getWorld();
        String worldName = world.getName();

        //获得允许随机传送的世界列表
        List<?> allowHomeWorldList = CommonUtils.objectToList(TprConfigEnum.ALLOW_WORLD.getMsg());
        if (!allowHomeWorldList.contains(worldName) && !allowHomeWorldList.contains(CommonEnum.ALL.getCommand())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + TprConfigEnum.TPR_WORLD_ERROR.getMsg()).replaceAll("%world%", worldName));
            return true;
        }

        //因随机传送获取安全位置开销很大，使用异步任务进行

            //得到配置文件里设置的数值
            int tprx = Integer.parseInt(TprConfigEnum.RANDOM_X.getMsg().toString());
            int tprz = Integer.parseInt(TprConfigEnum.RANDOM_Z.getMsg().toString());

            //传送位置
            Location newLocation;

            //判断是否以用户为中心进行随机传送
            if ((Boolean) TprConfigEnum.RESPAWN_CENTER.getMsg()) {
                //获取世界的重生点
                Location spawnLocation = world.getSpawnLocation();

                //重置底值，防止出现边缘撞到例如仙人掌之类的物品
                spawnLocation.setX(spawnLocation.getBlockX());
                spawnLocation.setY(spawnLocation.getBlockY());
                spawnLocation.setZ(spawnLocation.getBlockZ());

                //获取传送位置
                newLocation = newLocation(spawnLocation, world, tprx, tprz);
            } else {
                //重置底值，防止出现边缘撞到例如仙人掌之类的物品
                playerLocation.setX(playerLocation.getBlockX());
                playerLocation.setY(playerLocation.getBlockY());
                playerLocation.setZ(playerLocation.getBlockZ());

                //获取新的位置
                newLocation = newLocation(playerLocation, world, tprx, tprz);
            }

                //传送玩家
                player.teleport(newLocation);
                if (TeleportCommand.allowTp) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', PluginMessageConfigEnum.MESSAGE_PREFIX.getMsg() + TprConfigEnum.TPR_APPLY.getMsg()).replaceAll("%x%", String.valueOf(newLocation.getBlockX())).replaceAll("%y%", String.valueOf(newLocation.getBlockY())).replaceAll("%z%", String.valueOf(newLocation.getBlockZ())));
                }




        return true;
    }


    /**
     * 根据当前位置产生一个新的安全位置
     */
    private Location newLocation(Location location, World world, Integer tprx, Integer tprz) {
        //获得随机值
        int x = rand.nextInt(tprx * 2 + 1) - tprx; // 生成对应的对应的正负tprx区间的数值
        int z = rand.nextInt(tprz * 2 + 1) - tprz; // 生成对应的对应的正负tprz区间的数值

        //添加随机值
        Location newLocation = location.clone().add(x, 0, z);

        //判断该位置是否安全
        Block highestBlockAt = world.getHighestBlockAt(newLocation);

        //判断是否是下届，不是下届且方块不是危险方块则返回该位置 +1
        if (!"world_nether".equals(world.getName())) {
            if (dangerousBlockList.contains(highestBlockAt.getType().getKey().toString())) {
                //重新产生新的位置，如果该位置有危险的话
                return newLocation(location, world, tprx, tprz);
            }
            newLocation.setY(highestBlockAt.getY() + 1);
            return newLocation;
        }

        return removeBedrock(world, newLocation, highestBlockAt, tprx, tprz);
    }

    /**
     * 循环去除基岩
     */
    public Location removeBedrock(World world, Location location, Block highestBlockAt,Integer tprx, Integer tprz) {
        //判断是否是基岩
        if ("minecraft:bedrock".equals(highestBlockAt.getType().getKey().toString())) {
            blockList.add(highestBlockAt);
           highestBlockAt.setType(org.bukkit.Material.AIR);

            return removeBedrock(world, location, world.getHighestBlockAt(location),tprx,tprz);
        }

        //重新将基岩填上
            for (Block block : blockList) {
                block.setType(org.bukkit.Material.BEDROCK);
            }

        blockList.clear();

        //判断该位置是否有危险
        if (dangerousBlockList.contains(highestBlockAt.getType().getKey().toString())) {
            //重新产生新的位置，如果该位置有危险的话
            return newLocation(location, world, tprx, tprz);
        }

        return location;
    }
}

