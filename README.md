# UtilityToolbox

中文名：实用工具箱 —— 提供常用指令和服务器功能 [1.7-1.19x]

英文名：UtilityToolbox - Add Common Commands And Server Functions [1.7-1.19x]

适用版本：1.7，1.8，1.9，1.10，1.11，1.12，1.13，1.14，1.15，1.16，1.17，1.18，1.19

最新版本：3.1.0

官网：[http://www.utilitytoolbox.cn/](http://www.utilitytoolbox.cn/)

下载链接：[https://www.spigotmc.org/resources/utilitytoolbox-add-common-commands-and-server-functions-1-7-1-19x.104791/](https://www.spigotmc.org/resources/utilitytoolbox-add-common-commands-and-server-functions-1-7-1-19x.104791/)

开发文档：[https://hub.spigotmc.org/javadocs/spigot/](https://hub.spigotmc.org/javadocs/spigot/)

```
#1.0.0
正式发布插件
拥有指令：utilitytoolbox reload、heal、feed、healandfeed、fly、flyspeed、walkspeed
拥有功能：自定义加入/离开服务器消息、定时发送服务器消息、加入服务器欢迎
#1.0.1
在自定义加入/离开服务器消息、加入服务器欢迎基础上扩展功能：第一次加入服务器的消息内容功能、第一次加入服务器的欢迎内容功能
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
#2.1.3
添加高频红石摧毁时判断该位置是否还是红石，否则在特殊场景下会导致摧毁其他物品
#2.2.0
添加连点器检测功能
#2.2.1
修复自动重生后back指令无法回到之前位置的异常
#2.2.2
删除遗留在限制高频攻击里的一个打印输出用于测试的代码
#2.3.0
添加autorespawnback指令，用于玩家重生后可自动回到重生前位置
修复无死亡掉落功能玩家死亡后会出现玩家库存保留却依然还会掉落物品的问题
优化配置文件内容
#2.3.2
修复对他人执行命令 op 无权限的问题
在配置文件中添加了对所有功能消息文字颜色，用于快速判断内部情况
将通过配置文件开启自动重生功能取消，转为个人使用命令开启功能
添加autorespawn指令，用于开启自己的自动重生
#2.4.0
添加sendconsole指令，用于在客户端发送指令给控制台
添加了部分功能日志输出
修复了高频红石可能出现的玩家名字错误的问题
删除无死亡掉落功能
#2.4.1
修复在未开启返回权限时使用自动重生返回会导致无法获取重生前位置的异常
删除下线后临时传送点删除监听事件
#2.4.2
将setsnaptp临时传送点指令的位置数据改使用yaml文件保存，防止内存溢出
将sethome、tpa权限修改为默认所有玩家拥有
优化配置文件内容
#3.0.0
重构配置文件的读取方式，提高性能，并防止因更新插件/误修改配置文件而导致的报错
修复一些在重构过程中发现的问题
优化配置文件内容
注意：该版本由于重构了配置文件读取方式，大量修改了配置文件相关代码，在3.0.0版本之前的用户更新到该版本或该版本以上请尽量删除原本的配置文件，重启服务器以生成最新的配置文件
#3.0.1
将部分指令内容改成使用yaml文件记录，防止潜在的内存溢出问题
在部分功能/指令重要节点上添加日志
#3.0.2
修复高频红石一个判断问题，该问题会导致无法显示距离高频红石最近的玩家
修改了配置文件细节问题
#3.0.3
删除限制高频红石、限制高空流水里默认未找到玩家时自带的颜色代码格式
根据测试修改配置文件默认参数
#3.1.0
新增tpr随机传送指令
删除feed、healandfeed指令，该指令功能合并到heal指令
将autorespawnback指令修改为rebirthinplace
将setsnaptp、snaptp指令修改为settpp、tpp
将killself指令修改为kills
将playmode指令修改为gm
将sethome、home、settpp、tpp指令添加[name]参数，允许设置多个家、传送点
将sethome、settpp指令权限设置分别设置为utilitytoolbox.home、utilitytoolbox.tpp
删除flyspeed、walkspeed、heal指令的.other权限，只允许控制台对他人执行相关指令
修改默认权限，玩家默认拥有权限：utilitytoolbox.home、utilitytoolbox.tpp、utilityToolbox.tpa、utilitytoolbox.tpr、utilitytoolbox.back、utilityToolbox.killself、utilityToolbox.rebirthinplace、utilityToolbox.autorespawn
优化配置文件内容，减少重复的消息内容
注意：该版本修改了大量的内容，会跟之前版本的配置文件有大量冲突，在此之前的版本更新到此版本请删除旧版本配置文件以自动保存最新的配置文件
```
