# UtilityToolbox

中文名：实用工具箱 —— 提供常用指令和服务器功能 [1.7-1.19x]

英文名：UtilityToolbox - Add Common Commands And Server Functions [1.7-1.19x]

适用版本：1.7，1.8，1.9，1.10，1.11，1.12，1.13，1.14，1.15，1.16，1.17，1.18，1.19

最新版本：2.1.1

下载链接：[https://www.spigotmc.org/resources/utilitytoolbox-add-common-commands-and-server-functions-1-7-1-19x.104791/](https://www.spigotmc.org/resources/utilitytoolbox-add-common-commands-and-server-functions-1-7-1-19x.104791/)

开发文档：[https://hub.spigotmc.org/javadocs/spigot/](https://hub.spigotmc.org/javadocs/spigot/)

## 制作初衷

制作此插件的初衷是因为在长期游玩的过程中，使用服务器插件的体验并不是很好，经常会出现：

1. 版本不兼容
2. 插件的功能比较强大，不需要那么复杂的功能，否则导致配置使用起来及其繁琐
3. 无法满足我的一些使用习惯或需求

例子：大部分的传送插件都无法一次性处理所有传送请求，大部分都是玩家a申请传送后，玩家b再申请会将玩家a的传送请求覆盖，导致总是要一个一个申请、一个一个同意的繁琐事情发生

所以：我将常用的指令/功能由自己从零开发并根据本人实际游玩体验来做一定的定制化处理，例如上述讲到的tpa传送，我的设计就是玩家之间的tpa不会覆盖上一个玩家的tpa传送请求，并且tpaccept默认是可以接受所有传送请求，如果只想接受某个玩家的请求就可以通过使用tpaccept [玩家]来指定接受某一个玩家的传送请求，从而方便有效的解决上述遇到的问题

## 更新日志

```bash
#1.0.0
正式发布插件
拥有指令：utilitytoolbox reload、heal、feed、healandfeed、fly、flyspeed、walkspeed
拥有功能：自定义加入/离开服务器消息、定时发送服务器消息、加入服务器欢迎
#1.0.1
添加第一次加入服务器的消息内容功能、第一次加入服务器的欢迎内容功能
#1.1.0
新增高频红石检测功能
#1.2.0
新增高空流体限制功能
#1.2.3
新增tpa申请传送、tpacancel取消传送指令
#1.3.0
完善tpa传送系列相关指令，添加了tpaccept同意传送指令、tpadeny拒绝传送指令
#1.3.1
修复高空流体限制功能、高频红石检测功能不显示玩家名称的问题
#1.4.0
新增sethome设置家、home回家相关指令
#1.5.0
新增showtextcode展示文本特殊字符指令、killself指令
新增无死亡掉落功能、死亡自动重生功能
#1.6.0
新增back返回上一个传送点、teleport切换服务器是否允许传送指令
#1.7.0
新增copyres复制物品指令
#1.8.0
新增playmode模式切换指令
#1.9.0
新增setsnaptp设置临时传送点、snaptp传送到临时传送点指令
#2.0.0
新增musterplayer召集玩家指令，共7个子参数
#2.1.0
新增修改服务器列表内容修改功能
#2.1.1
优化配置文件内容，添加了更多注释
#2.1.2
优化代码，将旧代码逻辑修改至新代码逻辑，增加代码可读性
```

## 配置文件

```yaml
#这是一个配置文件，所有主功能的开启和关闭都必须重启服务器/重新加载插件
plugin-message:
  message-prefix: '&e[UtilityToolbox] &7'
  reload-message: '配置文件重载成功'
  command-args-error: '子指令无效或无子指令，正确格式: &c/UtilityToolbox <子指令>'

#自定义加入和离开服务器消息
join-and-leave-server-message:
  enable: true
  first-join-server-message-enable: false
  message:
    player-join-server-message: '&a%player% join server'
    player-leave-server-message: '&c%player% leave server'
    player-first-join-server-message: '&a%player% first join server'

#加入服务器欢迎
join-server-welcome:
  enable: true
  first-join-server-welcome-enable: false
  message:
    join-server-welcome-message:
      - '&e[UtilityToolbox] &7欢迎使用本插件'
    first-join-server-welcome-message:
      - '&e[UtilityToolbox] &7欢迎第一次使用本插件'

#自动发送服务器消息，类似公告
auto-send-server-messages:
  enable: false
  #默认秒
  time: 600
  message:
    send-messages:
      - '&e[UtilityToolbox] &7欢迎使用本插件'

#高频红石检测
anti-high-frequency-red-stone:
  enable: false
  #是否所有玩家都能看到红石被摧毁消息，否则只有UtilityToolbox.AntiRedStoneMessage权限的用户才能看
  is-broadcast-message: true
  #红石在检测时间内的限制次数
  limit: 50
  #检测时间，默认秒
  time: 5
  #检测的红石列表
  anti-red-stone-list:
    #默认红石、拉杆，可自行添加
    - 'minecraft:redstone_wire'
    - 'minecraft:lever'
  message:
    destroy-message: "玩家&3%player%&7附近存在高频红石，坐标&3x:%x% y:%y% z:%z%&7，已摧毁"

#限制高空流体
limit-high-altitude-fluid:
  enable: false
  #是否所有玩家都能看到流体被限制的消息，否则只有UtilityToolbox.LimitFlowMessage权限的用户才能看
  is-broadcast-message: true
  #限制高度
  limit-high: 80
  limit-fluid-list:
    #默认水和岩浆，该名字为流动液体的名字
    - 'minecraft:water'
    - 'minecraft:lava'
  #限制的世界
  limit-world-list:
    #*为限制所有世界
    - '*'
  message:
    destroy-message: "玩家&3%player%&7附近存在高空流体，坐标&3x:%x% y:%y% z:%z%&7，已限制"

#玩家自动重生
auto-respawn:
  enable: false

#玩家无死亡掉落
no-death-drop:
  enable: false

#服务器列表显示
server-list-display-modification:
  enable: false
  #最大玩家数
  max-player: 20
  #图标名称，放置在插件配置文件根目录下，规格必须为64*64，如果该图标不存在则无影响
  icon: icon.png
  message:
    first-line: '&e[UtilityToolbox] &7欢迎使用本插件'
    second-line: '&e[UtilityToolbox] &7欢迎使用本插件'

#恢复生命值
heal:
  message:
    heal-self: '你已恢复生命值'
    heal-others: '你已恢复&3%others-player%&7的生命值'
    heal-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    heal-others-is-self: '你已恢复生命值，可以使用不带参数的指令来恢复自己'
    heal-others-no-permission: '你没有执行恢复他人生命值指令的权限'
    heal-by-others: '你已被&3%player%&7恢复生命值'
    heal-by-console: '你已被服务器恢复生命值'
    heal-console-error: '控制台请执行恢复他人生命值的指令: &cheal <玩家名称>'
    heal-command-error: '恢复生命值指令格式错误，正确格式: &c/heal [玩家名称]'

#恢复饱食度
feed:
  message:
    feed-self: '你已恢复饱食度'
    feed-others: '你已恢复&3%others-player%&7的饱食度'
    feed-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    feed-others-is-self: '你已恢复饱食度，可以使用不带参数的指令来恢复自己'
    feed-others-no-permission: '你没有执行恢复他人饱食度指令的权限'
    feed-by-others: '你已被&3%player%&7恢复饱食度'
    feed-by-console: '你已被服务器恢复饱食度'
    feed-console-error: '控制台请执行恢复他人饱食度的指令: &cfeed <玩家名称>'
    feed-command-error: '恢复饱食度指令格式错误，正确格式: &c/feed [玩家名称]'

#恢复生命值和饱食度
#当拥有恢复生命值和饱食度指令时该指令可使用
healandfeed:
  message:
    heal-and-feed-self: '你已恢复生命值和饱食度'
    heal-and-feed-others: '你已恢复&3%others-player%&7的生命值和饱食度'
    heal-and-feed-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    heal-and-feed-others-is-self: '你已恢复生命值和饱食度，可以使用不带参数的指令来恢复自己'
    heal-and-feed-others-no-permission: '你没有执行恢复他人生命值和饱食度指令的权限'
    heal-and-feed-by-others: '你已被&3%player%&7恢复生命值和饱食度'
    heal-and-feed-by-console: '你已被服务器恢复生命值和饱食度'
    heal-and-feed-console-error: '控制台请执行恢复他人生命值和饱食度的指令: &chealandfeed <玩家名称>'
    heal-and-feed-command-error: '恢复生命值和饱食度指令格式错误，正确格式: &c/healandfeed [玩家名称]'

#飞行
fly:
  message:
    fly-self-open: '你已开启飞行'
    fly-self-close: '你已关闭飞行'
    fly-others-open: '你已开启&3%others-player%&7的飞行'
    fly-others-close: '你已关闭&3%others-player%&7的飞行'
    fly-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    fly-others-is-self-open: '你已开启飞行，可以使用不带参数的指令来开关飞行'
    fly-others-is-self-close: '你已关闭飞行，可以使用不带参数的指令来开关飞行'
    fly-others-no-permission: '你没有执行开关他人飞行指令的权限'
    fly-by-others-open: '你已被&3%player%&7开启飞行'
    fly-by-others-close: '你已被&3%player%&7关闭飞行'
    fly-by-console-open: '你已被服务器开启飞行'
    fly-by-console-close: '你已被服务器关闭飞行'
    fly-console-error: '控制台请执行开关他人飞行的指令: &cfly <玩家名称>'
    fly-command-error: '开关飞行指令格式错误，正确格式: &c/fly [玩家名称]'

#飞行速度
flyspeed:
  message:
    fly-speed-self-reset: '你已重置飞行速度'
    fly-speed-self: '你已修改飞行速度为%fly-speed%'
    fly-speed-self-args-error: '飞行速度参数错误，飞行速度只能为0-10，默认速度为1'
    fly-speed-others: '你已修改&3%others-player%&7的飞行速度为%fly-speed%'
    fly-speed-others-reset: '你已重置&3%others-player%&7的飞行速度'
    fly-speed-others-reset-is-self: '你已重置飞行速度，修改自己可以不输入玩家名称'
    fly-speed-others-is-self: '你已修改飞行速度为%fly-speed%，修改自己可以不输入玩家名称'
    fly-speed-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    fly-speed-others-no-permission: '你没有修改他人飞行速度指令的权限'
    fly-speed-by-others-reset: '你已被&3%player%&7重置飞行速度'
    fly-speed-by-console-reset: '你已被服务器重置飞行速度'
    fly-speed-by-others: '你已被&3%player%&7修改飞行速度为%fly-speed%'
    fly-speed-by-console: '你已被服务器修改飞行速度为%fly-speed%'
    fly-speed-console-error: '控制台请执行修改他人飞行速度的指令: &cflyspeed [速度] <玩家名称>'
    fly-speed-command-error: '修改飞行速度指令格式错误，格式: &c/flyspeed [速度] [玩家名称]'

#行走速度
walkspeed:
  message:
    walk-speed-self-reset: '你已重置移动速度'
    walk-speed-self: '你已修改移动速度为%walk-speed%'
    walk-speed-self-args-error: '移动速度参数错误，移动速度只能为0到10，默认速度为2'
    walk-speed-others: '你已修改&3%others-player%&7的移动速度为%walk-speed%'
    walk-speed-others-reset: '你已重置&3%others-player%&7的移动速度'
    walk-speed-others-reset-is-self: '你已重置移动速度，修改自己可以不输入玩家名称'
    walk-speed-others-is-self: '你已修改移动速度为%walk-speed%，修改自己可以不输入玩家名称'
    walk-speed-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    walk-speed-others-no-permission: '你没有修改他人移动速度指令的权限'
    walk-speed-by-others: '你已被&3%player%&7修改移动速度为%walk-speed%'
    walk-speed-by-others-reset: '你已被&3%player%&7重置移动速度'
    walk-speed-by-console: '你已被服务器修改移动速度为%walk-speed%'
    walk-speed-by-console-reset: '你已被服务器重置移动速度'
    walk-speed-console-error: '控制台请执行修改他人移动速度的指令: &cwalkspeed [速度] <玩家名称>'
    walk-speed-command-error: '修改移动速度指令格式错误，格式: &c/walkspeed [速度] [玩家名称]'

#申请传送
tpa:
  message:
    tpa-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    tpa-others-identical: '请不要发送重复申请'
    tpa-auto-tpacancel: '已自动取消上一个传送请求'
    tpa-auto-tpacancel-others: '玩家&3%player%&7已取消传送请求'
    tpa-apply: '传送请求已发送给玩家&3%others-player%'
    tpa-apply-others: '玩家&3%player%&7请求传送到你这里'
    tpa-apply-tpacancel-tips: '取消请求输入: &c/tpacancel'
    tpa-apply-accept-tips: '同意请求输入: &c/tpaccept'
    tpa-apply-deny-tips: '拒绝请求输入: &c/tpadeny'
    tpa-console-error: '控制台无法使用传送相关指令'
    tpa-command-error: '传送指令格式错误，正确格式: &c/tpa <其他玩家名称>'
    tpa-others-leave-server: '玩家&3%others-player%&7已离开服务器，已自动取消其传送请求'

#取消传送
tpacancel:
  message:
    tpacancel-apply: '已取消传送请求'
    tpacancel-others: '玩家&3%player%&7已取消传送请求'
    tpacancel-no-tpa-error: '你没有待处理的传送请求'
    tpacancel-console-error: '控制台无法使用传送相关指令'

#同意传送
tpaccept:
  message:
    tpaccept-apply: '你已接受玩家&3%others-player%&7的传送请求'
    tpaccept-apply-is-self: '请不要接受自己的传送请求'
    tpaccept-apply-others: '你向玩家&3%player%&7申请的传送请求已被接受'
    tpaccept-console-error: '控制台无法使用传送相关指令'
    tpaccept-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    tpaccept-command-error: '同意请求指令格式错误，正确格式: &c/tpaccept [其他玩家名称]'
    tpaccept-no-tpa-error: '你没有待处理的传送请求'
    tpaccept-no-others-player-tpa-error: '你没有玩家&3%others-player%&7的传送请求'

#拒绝传送
tpadeny:
  message:
    tpadeny-apply: '你已拒绝玩家&3%others-player%&7的传送请求'
    tpadeny-apply-is-self: '请不要拒绝自己的传送请求'
    tpadeny-apply-others: '你向玩家&3%player%&7申请的传送请求已被拒绝'
    tpadeny-console-error: '控制台无法使用传送相关指令'
    tpadeny-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    tpadeny-command-error: '拒绝请求指令格式错误，正确格式: &c/tpadeny [其他玩家名称]'
    tpadeny-no-tpa-error: '你没有待处理的传送请求'
    tpadeny-no-others-player-tpa-error: '你没有玩家&3%others-player%&7的传送请求'

#设置家
sethome:
  allow-world:
    - 'world'
  message:
    sethome-apply: '已将此位置设为家'
    sethome-world-error: '世界&3%world%&7禁止设置家'
    sethome-console-error: '控制台无法使用家相关指令'
    sethome-command-error: '设置家指令格式错误，正确格式: &c/sethome'

#回家
home:
  message:
    home-apply: '你已传送回家'
    home-no-exist: '暂无家，请使用&c/sethome&7指令设置家的位置'
    home-console-error: '控制台无法使用家相关指令'
    home-command-error: '设置家指令格式错误，正确格式: &c/sethome'

#展示文本代码
showtextcode:
  message:
    showtextcode-command-error: '展示文本代码指令格式错误，正确格式: &c/showtextcolor'
    show-list:
      - '&c文本代码列表------------------------------'
      - '&11 深紫 &7/ &99（淡紫）'
      - '&22 墨绿 &7/ &aa（碧绿）'
      - '&33 深蓝 &7/ &bb（天蓝）'
      - '&44 深红 &7/ &cc（大红）'
      - '&55 粉红 &7/ &dd（亮粉红）'
      - '&66 橙色 &7/ &ee（金色）'
      - '&77 浅灰 &7/ &ff（白色）'
      - '&88 深灰 &7/ &00（黑色）'
      - 'k是乱码(有非英文字符就会无效，不展示)'
      - '&ll是粗体字'
      - '&oo是斜体字'
      - '&rr是恢复到原始效果'
      - '&mm是删除线'
      - '&nn是下划线'
      - '&c展示列表结束------------------------------'

#等同于自己使用/kill指令
killself:
  message:
    killself-apply: '已让服务器执行自杀指令'
    killself-console-error: '控制台请使用官方指令：kill [目标]'
    killself-command-error: '自杀指令格式错误，正确格式: &c/killself'

#传送到上一个传送/死亡的位置
back:
  message:
    back-apply: '你已回到被传送前的位置'
    back-died-tips: '服务器已允许使用&c/back&7指令回到死亡位置'
    back-died-no-tp-tips: '服务器&c/back&7指令已记录你的死亡位置'
    back-no-location: '你暂时没有返回的位置'
    back-console-error: '控制台无法使用返回指令'
    back-command-error: '返回指令格式错误，正确格式: &c/back'

#切换服务器是否允许指令传送
teleport:
  message:
    teleport-allow: '&a服务器已恢复使用指令传送'
    teleport-allow-reason: '&a服务器已恢复使用指令传送，原因: &6%reason%'
    teleport-deny: '&c服务器已临时禁止指令传送'
    teleport-deny-apply: '&c服务器已临时禁止指令传送，传送失败'
    teleport-deny-reason: '&c服务器已临时禁止指令传送，原因: &6%reason%'
    teleport-command-error: '服务器是否允许指令传送格式错误，正确格式: &c/teleport [原因]'

#复制物品
copyres:
  #排除列表(用于排除通配符内的物品)写法，例如钻石甲：minecraft:diamond_chestplate
  exclude-list:
    - ''
  #黑名单，写法：钻石甲：minecraft:diamond_chestplate
  black-list:
  #默认禁用:附魔金苹果、金苹果、金萝卜、黄金、金粒、金块、tnt、火药
    - 'minecraft:enchanted_golden_apple'
    - 'minecraft:golden_apple'
    - 'minecraft:golden_carrot'
    - 'minecraft:gold_ingot'
    - 'minecraft:gold_nugget'
    - 'minecraft:gold_block'
    - 'minecraft:tnt'
    - 'minecraft:gunpowder'
  #通配符列表写法(只写前缀)，例如minecraft:开头的所有物品，minecraft:
  wildcard-list:
    - ''
  message:
    copyres-apply: '你已复制&3%res%&7物品'
    copyres-deny: '物品&3%res%&7禁止复制'
    copyres-console-error: '控制台请使用官方指令：give <玩家> <物品[参数]> [数量]'
    copyres-command-error: '复制物品指令格式错误，正确格式: &c/copyres'

#模式切换
playmode:
  message:
    playmode-apply: '已让服务器执行切换模式指令'
    playmode-console-error: '控制台请使用官方指令：gamemode <模式> <玩家>'
    playmode-command-error: '切换模式指令格式错误，正确格式: &c/playmode <游戏模式>'
    playmode-no-permission-error: '你没有切换到模式&3%gamemode%&7的权限'

#设置临时传送点
setsnaptp:
  #允许设置的世界
  allow-world:
    - '*'
  message:
    setsnaptp-apply: '已将此位置设为临时传送点，下线后删除'
    setsnaptp-world-error: '世界&3%world%&7禁止设置临时传送点'
    setsnaptp-console-error: '控制台无法使用临时传送点相关指令'
    setsnaptp-command-error: '设置临时传送点指令格式错误，正确格式: &c/setsnaptp'

#传送到临时传送点
snaptp:
  message:
    snaptp-apply: '你已传送回临时传送点'
    snaptp-no-exist: '暂无临时传送点，请使用&c/setsnaptp&7指令设置'
    snaptp-console-error: '控制台无法使用临时传送点相关指令'
    snaptp-command-error: '传送回临时传送点指令错误，正确格式: &c/snaptp'

#召集玩家
musterplayer:
  #召集完成后，传送的等待时间，默认秒
  time: 3
  message:
    musterplayer-apply-start: '已广播召集所有玩家到此位置，请等待所有玩家处理请求'
    musterplayer-apply-start-repeat: '玩家&3%player%&7已发起召集请求，请等待该召集结束后再发起召集'
    musterplayer-apply-start-error: '无其他在线玩家，请不要发起召集玩家请求'
    musterplayer-apply-start-others: '玩家&3%player%&7正在召集所有玩家，请及时处理请求'
    musterplayer-apply-start-others-reason: '玩家&3%player%&7正在召集所有玩家，原因: &6%reason%&7，请及时处理请求'
    musterplayer-apply-again: '已发起上一次召集请求，请等待所有玩家处理请求'
    musterplayer-apply-again-error: '没有上一次召集请求，请使用&c/musterplayer start&7指令发起新的召集'
    musterplayer-apply-cancel-consloe: '控制台已取消此次召集请求'
    musterplayer-apply-cancel-player: '你已取消此次召集请求'
    musterplayer-apply-cancel-player-others: '玩家&c%player%&7已取消此次召集请求'
    musterplayer-apply-end: '玩家%player%已提前结束召集，同意召集的玩家已传送至召集位置'
    musterplayer-apply-end-time: '玩家&3%player%&7提前结束召集，同意召集的玩家将在&c%time%&7秒后立即传送'
    musterplayer-apply-end-tp: '你已传送至召集位置'
    musterplayer-end: '已完成召集，同意召集的玩家已传送至召集位置'
    musterplayer-end-time: '已完成召集，同意召集玩家将在&c%time%秒&7后立即传送'
    musterplayer-apply-ccept: '玩家&3%player%&7已接受召集'
    musterplayer-apply-deny: '玩家&c%player%&7已拒绝召集'
    musterplayer-apply-list-allow: '已同意召集的玩家: &a%player-list%'
    musterplayer-apply-list-deny: '已拒绝召集的玩家: &c%player-list%'
    musterplayer-apply-list-deal-with: '还未处理召集的玩家: &6%player-list%'
    musterplayer-cancel-tips: '取消召集输入: &c/musterplayer cancel'
    musterplayer-end-tips: '结束召集输入: &c/musterplayer end'
    musterplayer-accept-tips: '同意召集输入: &c/musterplayer ccept'
    musterplayer-deny-tips: '拒绝召集输入: &c/musterplayer deny'
    musterplayer-others-tips: '所有玩家处理召集请求后或发起者提前结束召集，才会进行传送'
    musterplayer-apply-error: '没有需要处理的召集请求'
    musterplayer-player-leave-convenor: '召集者&3%player%&7已离开服务器，已取消此次召集'
    musterplayer-player-leave-callee: '被召集者&3%player%&7已离开服务器，已在被召集列表中移除该玩家'
    musterplayer-console-error: '控制台仅能使用&cmusterplayer cancel&7指令取消玩家召集'
    musterplayer-command-error: '召集指令错误，正确格式: &c/musterplayer <参数>'
```

## 指令列表

> 表格中的'\\'是为了取消Markdown语法，请忽略

| 指令                                 | 作用              |
|------------------------------------|-----------------|
| /utilitytoolbox reload             | 重载插件配置文件        |
| /heal [player]                     | 恢复自己/他人生命值      |
| /feed [player]                     | 恢复自己/他人饱食度      |
| /healandfeed [player]              | 恢复自己/他人生命值和饱食度  |
| /fly [player]                      | 切换自己/他人飞行状态     |
| /flyspeed <player/speed> [player]  | 修改自己/他人飞行速度     |
| /walkspeed <player/speed> [player] | 修改自己/他人移动速度     |
| /tpa <\player\>                    | 申请传送至该玩家的位置     |
| /tpacancel                         | 取消传送申请          |
| /tpaccept [player]                 | 同意所有/指定玩家玩家传送申请 |
| /tpadeny [player]                  | 拒绝所有/指定玩家玩家传送申请 |
| /sethome                           | 设置自己的家的位置       |
| /home                              | 回自己家            |
| /killself                          | 等同于自己输入/kill    |
| /showtextcode                      | 展示文本颜色代码        |
| /back                              | 传送到上一个传送/死亡位置   |
| /teleport                          | 切换服务器是否允许使用传送指令 |
| /copyres                           | 复制手上的物品         |
| /playmode <\mode>                  | 切换游戏模式          |
| /setsnaptp                         | 设置临时传送点         |
| /snaptp                            | 传送到临时传送点        |
| /musterplayer <\arg>               | 召集所有玩家          |

## 功能列表

| 功能                            | 作用            |
|-------------------------------|---------------|
| CustomJoinAndLeaveServer      | 自定义加入/离开服务器消息 |
| AutoSendServerMessages        | 定时发送服务器消息     |
| JoinServerWelcome             | 加入服务器欢迎       |
| AntiHighFrequencyRedStone     | 高频红石检测        |
| LimitHighAltitudeFluid        | 高空流体限制        |
| NoDeathDrop                   | 无死亡掉落         |
| AutoRespawn                   | 自动死亡重生        |
| ServerListDisplayModification | 服务器列表内容修改     |

## 权限列表

> 表格中的'\\'是为了取消Markdown语法，请忽略

| 权限                                    | 对应指令/功能                                                                        |
|---------------------------------------|--------------------------------------------------------------------------------|
| utilitytoolbox                        | /utilitytoolbox <\arg>                                                         |
|                                       |                                                                                |
| utilitytoolbox.heal                   | /heal                                                                          |
| utilitytoolbox.heal.others            | /heal <\player>                                                                |
|                                       |                                                                                |
| utilitytoolbox.feed                   | /feed                                                                          |
| utilitytoolbox.feed.others            | /heal <\player>                                                                |
|                                       |                                                                                |
| utilitytoolbox.fly                    | /fly                                                                           |
| utilitytoolbox.fly.others             | /fly <\player>                                                                 |
|                                       |                                                                                |
| utilitytoolbox.flyspeed               | /flyspeed [speed]                                                              |
| utilitytoolbox.flyspeed.others        | /flyspeed <player/speed> [player]                                              |
|                                       |                                                                                |
| utilitytoolbox.walkspeed              | /walkspeed [speed]                                                             |
| utilitytoolbox.walkspeed.others       | /walkspeed <player/speed> [player]                                             |
|                                       |                                                                                |
| utilitytoolbox.tpa                    | /tpa <\player\><br />/tpacancel<br />/tpaccept [player]<br />/tpadeny [player] |
|                                       |                                                                                |
| utilitytoolbox.sethome                | /sethome<br />/home                                                            |
|                                       |                                                                                |
| utilitytoolbox.killself               | /killself                                                                      |
|                                       |                                                                                |
| utilitytoolbox.showtextcode           | /showtextcode                                                                  |
|                                       |                                                                                |
| utilitytoolbox.back                   | /back                                                                          |
|                                       |                                                                                |
| utilitytoolbox.teleport               | /teleport                                                                      |
|                                       |                                                                                |
| utilitytoolbox.copyres                | /copyres                                                                       |
|                                       |                                                                                |
| utilitytoolbox.playmode               | /playmode <\mode>                                                              |
| utilitytoolbox.playmode.creative      | /playmode <\creative>                                                          |
| utilitytoolbox.playmode.survival      | /playmode <\survival>                                                          |
| utilitytoolbox.playmode.adventure     | /playmode <\adventure>                                                         |
| utilitytoolbox.playmode.spectator     | /playmode <\spectator>                                                         |
|                                       |                                                                                |
| utilitytoolbox.setsnaptp              | /setsnaptp<br />/snaptp                                                        |
|                                       |                                                                                |
| utilitytoolbox.musterplayer           | /musterplayer <\arg>                                                           |
|                                       |                                                                                |
| utilitytoolbox.RedStoneDestroyMessage | 收到高频红石被摧毁的消息                                                                   |
| utilitytoolbox.LimitFlowMessage       | 收到高空流体被限制的消息                                                                   |

## 指令详细介绍

> 表格中的'\'是为了取消Markdown语法，请忽略

### /utilitytoolbox

| 使用                     | 对应权限           | 作用                           |
|------------------------|----------------|------------------------------|
| /utilitytoolbox reload | utilitytoolbox | 重载插件配置文件，用于修改配置文件后，无需重启服务器更新 |

配置文件内容：

```yaml
plugin-message:
  message-prefix: '&e[UtilityToolbox] &7'
  reload-message: '配置文件重载成功'
  command-args-error: '子指令无效或无子指令，正确格式: &c/UtilityToolbox <子指令>'
```

### /heal

| 使用              | 对应权限                       | 作用             |
|-----------------|----------------------------|----------------|
| /heal           | utilitytoolbox.heal        | 恢复自己的生命值至其上限   |
| /heal <\player> | utilitytoolbox.heal.others | 恢复指定玩家的生命值至其上限 |

配置文件内容：

```yaml
heal:
  message:
    heal-self: '你已恢复生命值'
    heal-others: '你已恢复&3%others-player%&7的生命值'
    heal-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    heal-others-is-self: '你已恢复生命值，可以使用不带参数的指令来恢复自己'
    heal-others-no-permission: '你没有执行恢复他人生命值指令的权限'
    heal-by-others: '你已被&3%player%&7恢复生命值'
    heal-by-console: '你已被服务器恢复生命值'
    heal-console-error: '控制台请执行恢复他人生命值的指令: &cheal <玩家名称>'
    heal-command-error: '恢复生命值指令格式错误，正确格式: &c/heal [玩家名称]'
```

<br/>

### /feed

| 使用              | 对应权限                       | 作用             |
|-----------------|----------------------------|----------------|
| /feed           | utilitytoolbox.feed        | 恢复自己的饱食度至其上限   |
| /feed <\player> | utilitytoolbox.feed.others | 恢复指定玩家的饱食度至其上限 |

配置文件内容：

```yaml
feed:
  message:
    feed-self: '你已恢复饱食度'
    feed-others: '你已恢复&3%others-player%&7的饱食度'
    feed-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    feed-others-is-self: '你已恢复饱食度，可以使用不带参数的指令来恢复自己'
    feed-others-no-permission: '你没有执行恢复他人饱食度指令的权限'
    feed-by-others: '你已被&3%player%&7恢复饱食度'
    feed-by-console: '你已被服务器恢复饱食度'
    feed-console-error: '控制台请执行恢复他人饱食度的指令: &cfeed <玩家名称>'
    feed-command-error: '恢复饱食度指令格式错误，正确格式: &c/feed [玩家名称]'
```

<br/>

### /healandfeed

| 使用                     | 对应权限                                                       | 作用                 |
|------------------------|------------------------------------------------------------|--------------------|
| /healandfeed           | utilitytoolbox.heal<br />utilitytoolbox.feed               | 恢复自己的生命值和饱食度至其上限   |
| /healandfeed <\player> | utilitytoolbox.heal.others<br />utilitytoolbox.feed.others | 恢复指定玩家的生命值和饱食度至其上限 |

配置文件内容：

```yaml
healandfeed:
  message:
    heal-and-feed-self: '你已恢复生命值和饱食度'
    heal-and-feed-others: '你已恢复&3%others-player%&7的生命值和饱食度'
    heal-and-feed-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    heal-and-feed-others-is-self: '你已恢复生命值和饱食度，可以使用不带参数的指令来恢复自己'
    heal-and-feed-others-no-permission: '你没有执行恢复他人生命值和饱食度指令的权限'
    heal-and-feed-by-others: '你已被&3%player%&7恢复生命值和饱食度'
    heal-and-feed-by-console: '你已被服务器恢复生命值和饱食度'
    heal-and-feed-console-error: '控制台请执行恢复他人生命值和饱食度的指令: &chealandfeed <玩家名称>'
    heal-and-feed-command-error: '恢复生命值和饱食度指令格式错误，正确格式: &c/healandfeed [玩家名称]'
```

<br/>

### /fly

| 使用             | 对应权限                      | 作用          |
|----------------|---------------------------|-------------|
| /fly           | utilitytoolbox.fly        | 切换自己的飞行状态   |
| /fly <\player> | utilitytoolbox.fly.others | 切换指定玩家的飞行状态 |

配置文件内容：

```yaml
fly:
  message:
    fly-self-open: '你已开启飞行'
    fly-self-close: '你已关闭飞行'
    fly-others-open: '你已开启&3%others-player%&7的飞行'
    fly-others-close: '你已关闭&3%others-player%&7的飞行'
    fly-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    fly-others-is-self-open: '你已开启飞行，可以使用不带参数的指令来开关飞行'
    fly-others-is-self-close: '你已关闭飞行，可以使用不带参数的指令来开关飞行'
    fly-others-no-permission: '你没有执行开关他人飞行指令的权限'
    fly-by-others-open: '你已被&3%player%&7开启飞行'
    fly-by-others-close: '你已被&3%player%&7关闭飞行'
    fly-by-console-open: '你已被服务器开启飞行'
    fly-by-console-close: '你已被服务器关闭飞行'
    fly-console-error: '控制台请执行开关他人飞行的指令: &cfly <玩家名称>'
    fly-command-error: '开关飞行指令格式错误，正确格式: &c/fly [玩家名称]'
```

<br/>

### /flyspeed

| 使用                           | 对应权限                           | 作用                  |
|------------------------------|--------------------------------|---------------------|
| /flyspeed                    | utilitytoolbox.flyspeed        | 重置自己的飞行速度，默认速度为2    |
| /flyspeed <\speed>           | utilitytoolbox.flyspeed        | 修改自己的飞行速度，最大速度为10   |
| /flyspeed <\player>          | utilitytoolbox.flyspeed.others | 重置指定玩家的飞行速度，默认速度为2  |
| /flyspeed <\player> <\speed> | utilitytoolbox.flyspeed.others | 修改指定玩家的飞行速度，最大速度为10 |

配置文件内容：

```yaml
flyspeed:
  message:
    fly-speed-self-reset: '你已重置飞行速度'
    fly-speed-self: '你已修改飞行速度为%fly-speed%'
    fly-speed-self-args-error: '飞行速度参数错误，飞行速度只能为0-10，默认速度为1'
    fly-speed-others: '你已修改&3%others-player%&7的飞行速度为%fly-speed%'
    fly-speed-others-reset: '你已重置&3%others-player%&7的飞行速度'
    fly-speed-others-reset-is-self: '你已重置飞行速度，修改自己可以不输入玩家名称'
    fly-speed-others-is-self: '你已修改飞行速度为%fly-speed%，修改自己可以不输入玩家名称'
    fly-speed-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    fly-speed-others-no-permission: '你没有修改他人飞行速度指令的权限'
    fly-speed-by-others-reset: '你已被&3%player%&7重置飞行速度'
    fly-speed-by-console-reset: '你已被服务器重置飞行速度'
    fly-speed-by-others: '你已被&3%player%&7修改飞行速度为%fly-speed%'
    fly-speed-by-console: '你已被服务器修改飞行速度为%fly-speed%'
    fly-speed-console-error: '控制台请执行修改他人飞行速度的指令: &cflyspeed [速度] <玩家名称>'
    fly-speed-command-error: '修改飞行速度指令格式错误，格式: &c/flyspeed [速度] [玩家名称]'
```

<br/>

### /walkspeed

| 使用                            | 对应权限                            | 作用                  |
|-------------------------------|---------------------------------|---------------------|
| /walkspeed                    | utilitytoolbox.walkspeed        | 重置自己的移动速度，默认速度为2    |
| /walkspeed <\speed>           | utilitytoolbox.walkspeed        | 修改自己的移动速度，最大速度为10   |
| /walkspeed <\player>          | utilitytoolbox.walkspeed.others | 重置指定玩家的移动速度，默认速度为2  |
| /walkspeed <\player> <\speed> | utilitytoolbox.walkspeed.others | 修改指定玩家的移动速度，最大速度为10 |

配置文件内容：

```yaml
walkspeed:
  message:
    walk-speed-self-reset: '你已重置移动速度'
    walk-speed-self: '你已修改移动速度为%walk-speed%'
    walk-speed-self-args-error: '移动速度参数错误，移动速度只能为0到10，默认速度为2'
    walk-speed-others: '你已修改&3%others-player%&7的移动速度为%walk-speed%'
    walk-speed-others-reset: '你已重置&3%others-player%&7的移动速度'
    walk-speed-others-reset-is-self: '你已重置移动速度，修改自己可以不输入玩家名称'
    walk-speed-others-is-self: '你已修改移动速度为%walk-speed%，修改自己可以不输入玩家名称'
    walk-speed-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    walk-speed-others-no-permission: '你没有修改他人移动速度指令的权限'
    walk-speed-by-others: '你已被&3%player%&7修改移动速度为%walk-speed%'
    walk-speed-by-others-reset: '你已被&3%player%&7重置移动速度'
    walk-speed-by-console: '你已被服务器修改移动速度为%walk-speed%'
    walk-speed-by-console-reset: '你已被服务器重置移动速度'
    walk-speed-console-error: '控制台请执行修改他人移动速度的指令: &cwalkspeed [速度] <玩家名称>'
    walk-speed-command-error: '修改移动速度指令格式错误，格式: &c/walkspeed [速度] [玩家名称]'
```

<br/>

### /tpa

> 申请者下线会自动取消其传送申请

| 使用                   | 对应权限               | 作用           |
|----------------------|--------------------|--------------|
| /tpa &lt;\player&gt; | utilitytoolbox.tpa | 申请传送至指定玩家的位置 |

配置文件内容：

```yaml
tpa:
  message:
    tpa-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    tpa-others-identical: '请不要发送重复申请'
    tpa-auto-tpacancel: '已自动取消上一个传送请求'
    tpa-auto-tpacancel-others: '玩家&3%player%&7已取消传送请求'
    tpa-apply: '传送请求已发送给玩家&3%others-player%'
    tpa-apply-others: '玩家&3%player%&7请求传送到你这里'
    tpa-apply-tpacancel-tips: '取消请求输入: &c/tpacancel'
    tpa-apply-accept-tips: '同意请求输入: &c/tpaccept'
    tpa-apply-deny-tips: '拒绝请求输入: &c/tpadeny'
    tpa-console-error: '控制台无法使用传送相关指令'
    tpa-command-error: '传送指令格式错误，正确格式: &c/tpa <其他玩家名称>'
    tpa-others-leave-server: '玩家&3%others-player%&7已离开服务器，已自动取消其传送请求'
```

<br/>

### /tpacancel

> 当自己使用 /tpa 发送了一个申请传送请求时可使用该指令取消自己的传送请求

| 使用         | 对应权限               | 作用        |
|------------|--------------------|-----------|
| /tpacancel | utilitytoolbox.tpa | 取消自己的申请传送 |

配置文件内容：

```yaml
tpacancel:
  message:
    tpacancel-apply: '已取消传送请求'
    tpacancel-others: '玩家&3%player%&7已取消传送请求'
    tpacancel-no-tpa-error: '你没有待处理的传送请求'
    tpacancel-console-error: '控制台无法使用传送相关指令'
```

<br/>

### /tpaccept

> 当有玩家使用 /tpa 申请传送至自己位置的请求时可使用

| 使用                  | 对应权限               | 作用                 |
|---------------------|--------------------|--------------------|
| /tpaccept           | utilitytoolbox.tpa | 同意所有玩家申请传送至自己位置的请求 |
| /tpaccept <\player> | utilitytoolbox.tpa | 同意指定玩家申请传送至自己位置的请求 |

配置文件内容：

```yaml
tpaccept:
  message:
    tpaccept-apply: '你已接受玩家&3%others-player%&7的传送请求'
    tpaccept-apply-is-self: '请不要接受自己的传送请求'
    tpaccept-apply-others: '你向玩家&3%player%&7申请的传送请求已被接受'
    tpaccept-console-error: '控制台无法使用传送相关指令'
    tpaccept-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    tpaccept-command-error: '同意请求指令格式错误，正确格式: &c/tpaccept [其他玩家名称]'
    tpaccept-no-tpa-error: '你没有待处理的传送请求'
    tpaccept-no-others-player-tpa-error: '你没有玩家&3%others-player%&7的传送请求'
```

<br/>

### /tpadeny

> 当有玩家使用 /tpa 申请传送至自己位置的请求时可使用

| 使用                 | 对应权限               | 作用                 |
|--------------------|--------------------|--------------------|
| /tpadeny           | utilitytoolbox.tpa | 拒绝所有玩家申请传送至自己位置的请求 |
| /tpadeny <\player> | utilitytoolbox.tpa | 拒绝指定玩家申请传送至自己位置的请求 |

配置文件内容：

```yaml
tpadeny:
  message:
    tpadeny-apply: '你已拒绝玩家&3%others-player%&7的传送请求'
    tpadeny-apply-is-self: '请不要拒绝自己的传送请求'
    tpadeny-apply-others: '你向玩家&3%player%&7申请的传送请求已被拒绝'
    tpadeny-console-error: '控制台无法使用传送相关指令'
    tpadeny-others-no-exist: '玩家&3%others-player%&7不存在，请检查玩家名字'
    tpadeny-command-error: '拒绝请求指令格式错误，正确格式: &c/tpadeny [其他玩家名称]'
    tpadeny-no-tpa-error: '你没有待处理的传送请求'
    tpadeny-no-others-player-tpa-error: '你没有玩家&3%others-player%&7的传送请求'
```

<br/>

### /sethome

> 可通过配置文件设置哪些世界允许设置家

| 使用       | 对应权限                   | 作用             |
|----------|------------------------|----------------|
| /sethome | utilitytoolbox.sethome | 将当前位置设置成自己家的位置 |

配置文件内容：

```yaml
sethome:
  allow-world:
    - 'world'
  message:
    sethome-apply: '已将此位置设为家'
    sethome-world-error: '世界&3%world%&7禁止设置家'
    sethome-console-error: '控制台无法使用家相关指令'
    sethome-command-error: '设置家指令格式错误，正确格式: &c/sethome'
```

<br/>

### /home

> 当自己使用指令 /sethome 设置某个位置为自己家的位置时可使用

| 使用    | 对应权限                   | 作用       |
|-------|------------------------|----------|
| /home | utilitytoolbox.sethome | 回到自己家的位置 |

配置文件内容：

```yaml
home:
  message:
    home-apply: '你已传送回家'
    home-no-exist: '暂无家，请使用&c/sethome&7指令设置家的位置'
    home-console-error: '控制台无法使用家相关指令'
    home-command-error: '设置家指令格式错误，正确格式: &c/sethome'
```

<br/>

### /killself

> 主要是因为官方指令过于强大，可使用 /kill [player/range] 强大的参数

| 使用        | 对应权限                    | 作用           |
|-----------|-------------------------|--------------|
| /killself | utilitytoolbox.killself | 等同于官方指令/kill |

配置文件内容：

```yaml
killself:
  message:
    killself-apply: '已让服务器执行自杀指令'
    killself-console-error: '控制台请使用官方指令：kill [目标]'
    killself-command-error: '自杀指令格式错误，正确格式: &c/killself'
```

<br/>

### /showtextcode

> 主要方便查看文字的颜色、特殊代码等，修改配置文件时可参考

| 使用            | 对应权限                        | 作用       |
|---------------|-----------------------------|----------|
| /showtextcode | utilitytoolbox.showtextcode | 展示文本特殊代码 |

配置文件内容：

```yaml
showtextcode:
  message:
    showtextcode-command-error: '展示文本代码指令格式错误，正确格式: &c/showtextcolor'
    show-list:
      - '&c文本代码列表------------------------------'
      - '&11 深紫 &7/ &99（淡紫）'
      - '&22 墨绿 &7/ &aa（碧绿）'
      - '&33 深蓝 &7/ &bb（天蓝）'
      - '&44 深红 &7/ &cc（大红）'
      - '&55 粉红 &7/ &dd（亮粉红）'
      - '&66 橙色 &7/ &ee（金色）'
      - '&77 浅灰 &7/ &ff（白色）'
      - '&88 深灰 &7/ &00（黑色）'
      - 'k是乱码(有非英文字符就会无效，不展示)'
      - '&ll是粗体字'
      - '&oo是斜体字'
      - '&rr是恢复到原始效果'
      - '&mm是删除线'
      - '&nn是下划线'
      - '&c展示列表结束------------------------------'
```

<br/>

### /back

| 使用    | 对应权限                | 作用            |
|-------|---------------------|---------------|
| /back | utilitytoolbox.back | 传送回上一个传送/死亡位置 |

配置文件内容：

```yaml
back:
  message:
    back-apply: '你已回到被传送前的位置'
    back-died-tips: '服务器已允许使用&c/back&7指令回到死亡位置'
    back-died-no-tp-tips: '服务器&c/back&7指令已记录你的死亡位置'
    back-no-location: '你暂时没有返回的位置'
    back-console-error: '控制台无法使用返回指令'
    back-command-error: '返回指令格式错误，正确格式: &c/back'
```

<br/>

### /teleport

> 可用于特殊战斗时使用

| 使用                  | 对应权限                    | 作用                     |
|---------------------|-------------------------|------------------------|
| /teleport           | utilitytoolbox.teleport | 切换服务器内是否允许使用指令传送       |
| /teleport <\reason> | utilitytoolbox.teleport | 切换服务器内是否允许使用指令传送，并声明原因 |

配置文件内容：

```yaml
teleport:
  message:
    teleport-allow: '&a服务器已恢复使用指令传送'
    teleport-allow-reason: '&a服务器已恢复使用指令传送，原因: &6%reason%'
    teleport-deny: '&c服务器已临时禁止指令传送'
    teleport-deny-apply: '&c服务器已临时禁止指令传送，传送失败'
    teleport-deny-reason: '&c服务器已临时禁止指令传送，原因: &6%reason%'
    teleport-command-error: '服务器是否允许指令传送格式错误，正确格式: &c/teleport [原因]'
```

<br/>

### /copyres

> 通过配置文件可设置哪些物品允许/禁止复制

| 使用       | 对应权限                   | 作用       |
|----------|------------------------|----------|
| /copyres | utilitytoolbox.copyres | 复制主手上的物品 |

配置文件内容：

```yaml
copyres:
  exclude-list:
    - ''
  black-list:
    - 'minecraft:enchanted_golden_apple'
    - 'minecraft:golden_apple'
    - 'minecraft:golden_carrot'
    - 'minecraft:gold_ingot'
    - 'minecraft:gold_nugget'
    - 'minecraft:gold_block'
    - 'minecraft:tnt'
    - 'minecraft:gunpowder'
  wildcard-list:
    - ''
  message:
    copyres-apply: '你已复制&3%res%&7物品'
    copyres-deny: '物品&3%res%&7禁止复制'
    copyres-console-error: '控制台请使用官方指令：give <玩家> <物品[参数]> [数量]'
    copyres-command-error: '复制物品指令格式错误，正确格式: &c/copyres'
```

<br/>

### /playmode

> 主要是因为官方指令过于强大，可使用 /gamemode <\mode> [player] 强大的参数

| 使用                     | 对应权限                              | 作用                             |
|------------------------|-----------------------------------|--------------------------------|
| /playmode <\mode>      | utilitytoolbox.playmode           | 切换自己的游戏模式，如果没有其他子权限，默认允许切换所有模式 |
| /playmode <\creative>  | utilitytoolbox.playmode.creative  | 将自己切换成创造模式                     |
| /playmode <\survival>  | utilitytoolbox.playmode.survival  | 将自己切换成生存模式                     |
| /playmode <\adventure> | utilitytoolbox.playmode.adventure | 将自己切换成冒险模式                     |
| /playmode <\spectator> | utilitytoolbox.playmode.spectator | 将自己切换成观察者模式                    |

配置文件内容：

```yaml
playmode:
  message:
    playmode-apply: '已让服务器执行切换模式指令'
    playmode-console-error: '控制台请使用官方指令：gamemode <模式> <玩家>'
    playmode-command-error: '切换模式指令格式错误，正确格式: &c/playmode <游戏模式>'
    playmode-no-permission-error: '你没有切换到模式&3%gamemode%&7的权限'
```

<br/>

### /setsnaptp

> 该传送点下线后就会消失
>
> 可通过配置文件设置哪些世界允许设置临时传送点

| 使用         | 对应权限                     | 作用      |
|------------|--------------------------|---------|
| /setsnaptp | utilitytoolbox.setsnaptp | 设置临时传送点 |

配置文件内容：

```yaml
setsnaptp:
  allow-world:
    - '*'
  message:
    setsnaptp-apply: '已将此位置设为临时传送点，下线后删除'
    setsnaptp-world-error: '世界&3%world%&7禁止设置临时传送点'
    setsnaptp-console-error: '控制台无法使用临时传送点相关指令'
    setsnaptp-command-error: '设置临时传送点指令格式错误，正确格式: &c/setsnaptp'
```

<br/>

### /snaptp

> 需使用 /setsnaptp 设置临时传送点后使用

| 使用      | 对应权限                     | 作用       |
|---------|--------------------------|----------|
| /snaptp | utilitytoolbox.setsnaptp | 传送回临时传送点 |

配置文件内容：

```yaml
snaptp:
  message:
    snaptp-apply: '你已传送回临时传送点'
    snaptp-no-exist: '暂无临时传送点，请使用&c/setsnaptp&7指令设置'
    snaptp-console-error: '控制台无法使用临时传送点相关指令'
    snaptp-command-error: '传送回临时传送点指令错误，正确格式: &c/snaptp'
```

<br/>

### /musterplayer

> 当仅有一个玩家在线时无法使用该指令
>
> 当召集者下线，会自动取消其召集申请
>
> 当被召集者下线，会自动取消其召集请求
>
> 当所有被召集者处理完召集请求后或召集者提前结束召集才会进行传送
>
> 可通过配置文件设置召集后多少秒后才进行传送

| 使用                            | 对应权限                        | 作用                      |
|-------------------------------|-----------------------------|-------------------------|
| /musterplayer start           | utilitytoolbox.musterplayer | 申请召集所有玩家传送至当前位置         |
| /musterplayer start <\reason> | utilitytoolbox.musterplayer | 申请召集所有玩家传送至当前位置，并声明召集原因 |
| /musterplayer cancel          | utilitytoolbox.musterplayer | 取消召集申请                  |
| /musterplayer end             | utilitytoolbox.musterplayer | 提前结束召集申请                |
| /musterplayer ccept           | utilitytoolbox.musterplayer | 同意召集请求                  |
| /musterplayer deny            | utilitytoolbox.musterplayer | 拒绝召集请求                  |
| /musterplayer list            | utilitytoolbox.musterplayer | 显示本次召集玩家的处理状态           |
| /musterplayer again           | utilitytoolbox.musterplayer | 发送上一次的申请召集请求            |

配置文件内容：

```yaml
musterplayer:
  time: 3
  message:
    musterplayer-apply-start: '已广播召集所有玩家到此位置，请等待所有玩家处理请求'
    musterplayer-apply-start-repeat: '玩家&3%player%&7已发起召集请求，请等待该召集结束后再发起召集'
    musterplayer-apply-start-error: '无其他在线玩家，请不要发起召集玩家请求'
    musterplayer-apply-start-others: '玩家&3%player%&7正在召集所有玩家，请及时处理请求'
    musterplayer-apply-start-others-reason: '玩家&3%player%&7正在召集所有玩家，原因: &6%reason%&7，请及时处理请求'
    musterplayer-apply-again: '已发起上一次召集请求，请等待所有玩家处理请求'
    musterplayer-apply-again-error: '没有上一次召集请求，请使用&c/musterplayer start&7指令发起新的召集'
    musterplayer-apply-cancel-consloe: '控制台已取消此次召集请求'
    musterplayer-player-leave-convenor: '召集者&3%player%&7已离开服务器，已取消此次召集'
    musterplayer-player-leave-callee: '被召集者&3%player%&7已离开服务器，已在被召集列表中移除该玩家'
    musterplayer-apply-cancel-player: '你已取消此次召集请求'
    musterplayer-apply-cancel-player-others: '玩家&c%player%&7已取消此次召集请求'
    musterplayer-apply-end: '玩家%player%已提前结束召集，同意召集的玩家已传送至召集位置'
    musterplayer-apply-end-time: '玩家&3%player%&7提前结束召集，同意召集的玩家将在&c%time%&7秒后立即传送'
    musterplayer-apply-end-tp: '你已传送至召集位置'
    musterplayer-end: '已完成召集，同意召集的玩家已传送至召集位置'
    musterplayer-end-time: '已完成召集，同意召集玩家将在&c%time%秒&7后立即传送'
    musterplayer-apply-ccept: '玩家&3%player%&7已接受召集'
    musterplayer-apply-deny: '玩家&c%player%&7已拒绝召集'
    musterplayer-apply-list-allow: '已同意召集的玩家: &a%player-list%'
    musterplayer-apply-list-deny: '已拒绝召集的玩家: &c%player-list%'
    musterplayer-apply-list-deal-with: '还未处理召集的玩家: &6%player-list%'
    musterplayer-cancel-tips: '取消召集输入: &c/musterplayer cancel'
    musterplayer-end-tips: '结束召集输入: &c/musterplayer end'
    musterplayer-accept-tips: '同意召集输入: &c/musterplayer ccept'
    musterplayer-deny-tips: '拒绝召集输入: &c/musterplayer deny'
    musterplayer-others-tips: '所有玩家处理召集请求后或发起者提前结束召集，才会进行传送'
    musterplayer-apply-error: '没有需要处理的召集请求'
    musterplayer-console-error: '控制台仅能使用&cmusterplayer cancel&7指令取消玩家召集'
    musterplayer-command-error: '召集指令错误，正确格式: &c/musterplayer <参数>'
```

## 功能详细介绍

### 自定义加入/离开服务器消息

> 用于自定义修改玩家进入/离开服务器时服务器显示的内容

配置文件内容：

```yaml
join-and-leave-server-message:
  enable: true
  first-join-server-message-enable: false
  message:
    player-join-server-message: '&a%player% join server'
    player-leave-server-message: '&c%player% leave server'
    player-first-join-server-message: '&a%player% first join server'
```



### 定时发送服务器消息

> 每隔一段时间广播消息，可用于发送公告提醒、服务器规范提醒等

配置文件内容：

```yaml
auto-send-server-messages:
  enable: false
  time: 600
  message:
    send-messages:
      - '&e[UtilityToolbox] &7欢迎使用本插件'
```



###  加入服务器欢迎

> 当玩家加入服务器时发送的欢迎语句，仅加入者能看到此消息
>
> 可通过配置文件开启第一次进入欢迎的内容

配置文件内容：

```yaml
join-server-welcome:
  enable: true
  first-join-server-welcome-enable: false
  message:
    join-server-welcome-message:
      - '&e[UtilityToolbox] &7欢迎使用本插件'
    first-join-server-welcome-message:
      - '&e[UtilityToolbox] &7欢迎第一次使用本插件'
```



### 高频红石检测

> 红石触发、停止算两次计算
>
> 可通过配置文件设置是否全部人能看到高频红石触发的消息、否则只有拥有UtilityToolbox.AntiRedStoneMessage权限的用户才能看到
>
> 可通过配置文件设置检测时间、限制次数、检测的红石列表

配置文件内容：

```yaml
anti-high-frequency-red-stone:
  enable: false
  is-broadcast-message: true
  limit: 50
  time: 5
  anti-red-stone-list:
    - 'minecraft:redstone_wire'
    - 'minecraft:lever'
  message:
    destroy-message: "玩家&3%player%&7附近存在高频红石，坐标&3x:%x% y:%y% z:%z%&7，已摧毁"
```



### 高空流体限制

>因一个流体会向多个方向扩散，所以可能会看到多次触发的消息
>
>可通过配置文件设置是否全部人能看到高频红石触发的消息、否则只有拥有UtilityToolbox.LimitFlowMessage权限的用户才能看到
>
>可通过配置文件设置限制的高度、限制的流体、检测的世界列表

配置文件内容：

```yaml
limit-high-altitude-fluid:
  enable: false
  is-broadcast-message: true
  limit-high: 80
  limit-fluid-list:
    - 'minecraft:water'
    - 'minecraft:lava'
  limit-world-list:
    - '*'
  message:
    destroy-message: "玩家&3%player%&7附近存在高空流体，坐标&3x:%x% y:%y% z:%z%&7，已限制"
```



### 无死亡掉落

> 用于减少开服后对控制台的操作

配置文件内容：

```yaml
no-death-drop:
  enable: false
```



### 自动死亡重生

> 用于减少死亡后结算界面的等待时间

配置文件内容：

```yaml
#玩家自动重生
auto-respawn:
  enable: false
```



### 服务器列表内容修改

> 该设置在多人游戏列表展示
>
> 图片必须是64*64，否则可能会出现异常报错

配置文件内容：

```yaml
server-list-display-modification:
  enable: false
  max-player: 20
  icon: icon.png
  message:
    first-line: '&e[UtilityToolbox] &7欢迎使用本插件'
    second-line: '&e[UtilityToolbox] &7欢迎使用本插件'
```

## 未来更新计划

> 非常未来

+ 重新编写配置文件的读取方式，减少重复的读取
+ 添加登录功能
+ 添加客户端类型检测功能
+ 添加离线服务器皮肤恢复功能
