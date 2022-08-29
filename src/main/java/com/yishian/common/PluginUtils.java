package com.yishian.common;

import com.yishian.Main;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;


/**
 * @author XinQi
 */
public class PluginUtils {

    /**
     * 得到服务器配置文件
     *
     * @return 返回配置文件
     */
    public static FileConfiguration getServerConfig() {
        return Main.getProvidingPlugin(Main.class).getConfig();
    }

    /**
     * 参数数量最大为0-1时玩家通用提示
     * @param args 指令参数
     * @return 提示
     */
    public static List<String> arg1CommandPlayerTip(String[] args) {
        ArrayList<String> tips = new ArrayList<>();
        //判断参数是否为空，是的话就给出全部提示
        if (StringUtils.isEmpty(args[0])) {
            Bukkit.getOnlinePlayers().forEach(player -> tips.add(player.getName()));
            //判断参数数量是否为1，证明输入了内容给出根据输入的参数前缀给出对应的提示
        } else if (args.length == 1) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                String playerName = player.getName();
                if (playerName.startsWith(args[0])) {
                    tips.add(playerName);
                }
            });
        }
        return tips;
    }

    /**
     * 参数数量最大为1-2时玩家通用提示
     * @param args 指令参数
     * @return 提示
     */
    public static List<String> arg2CommandPlayerTips(String[] args) {
        ArrayList<String> tips = new ArrayList<>();
        if (args.length == 2 && StringUtils.isEmpty(args[1])) {
            Bukkit.getOnlinePlayers().forEach(player -> tips.add(player.getName()));
            //判断参数数量是否为1，证明输入了内容给出根据输入的参数前缀给出对应的提示
        } else if (args.length == 2) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                String playerName = player.getName();
                if (playerName.startsWith(args[1])) {
                    tips.add(playerName);
                }
            });
        }
        return tips;
    }

    /**
     *
     * @param oneX 第一个位置的x坐标
     * @param oneY 第一个位置的y坐标
     * @param oneZ 第一个位置的z坐标
     * @param twoX 第二个位置的x坐标
     * @param twoY 第二个位置的y坐标
     * @param twoZ 第二个位置的z坐标
     * @return 返回计算后的距离
     * 该方案被移除，location提供了方法来计算距离
     */
    public static Double calculateDistance(Double oneX,Double oneY,Double oneZ,Double twoX,Double twoY,Double twoZ) {
        return (Math.pow((oneX-twoX),2)+Math.pow((oneY-twoY),2)+Math.pow((oneZ-twoZ),2))/2;
    }
}
