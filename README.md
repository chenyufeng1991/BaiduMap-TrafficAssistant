# BaiduMap-TrafficAssistant
&emsp;&emsp;&nbsp;该项目是基于百度地图SDK开发的一款交通助手App，目前已经上线豌豆荚、魅族应用市场、搜狗手机助手等多个安卓应用市场。目前我决定开源该项目，为更多的安卓应用开发者或者基于百度地图SDK开发人员提供服务和便利。当然App中还有不少bug和可扩展的功能模块，也希望各位开发者为该项目贡献自己的code力量。
##1.项目简介
&emsp;&emsp;&nbsp;该版本的百度地图SDK为v3.2.0. 使用百度地图SDK实现的功能有：普通地图显示、卫星地图显示、交通流量图显示、城市热力图显示、定位功能、模式切换（普通模式、跟随模式、罗盘模式）、公交地铁查询、离线地图下载和导航功能。其他的附加功能有浏览网页功能和通讯录功能。</br>
&emsp;&emsp;&nbsp;其中我还留出多个接口可供大家开发其他模块：登录注册功能、智能助手和娱乐功能。如果你有好的idea，也可以在App里面实现。希望在更多开发者的共同努力下，把这个应用开发成一个优秀的产品。系统主界面如下：
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/主界面.jpg)<br/><br/>
##2.开发指南
&emsp;&emsp;&nbsp;如果你下载或者clone代码，其实并不能在你的真机或者模拟器上显示出百度地图，还需要进行简单的百度地图SDK开发的配置。配置方式如下：
###（1）创建应用
&emsp;&emsp;&nbsp;你需要先去百度地图SDK平台官网[http://developer.baidu.com/map/index.php?title=%E9%A6%96%E9%A1%B5](http://developer.baidu.com/map/index.php?title=%E9%A6%96%E9%A1%B5)申请一个key值，进入右上角的“API控制台”，然后创建一个应用即可。
###（2）获取key
&emsp;&emsp;&nbsp;我们需要创建的是一个Android应用。如图所示：
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/创建应用.png)<br/><br/>
###（3）安全码
&emsp;&emsp;&nbsp;在上一个步骤中，需要填入一个安全码，这个非常关键！安全码 = 数字签名+；+包名。该App的包名为：com.android.traffic.  那如何获取数字签名呢？可以去Eclipse中的Preferences-->Android-->Build下获取，其中SHA1 fingerprint就是我们要的数字签名。如图：
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/安全码.png)<br/><br/>
###（4）获取AK复制到项目
&emsp;&emsp;&nbsp;在完成上述步骤后，就可以获取一个访问应用（AK），然后把该AK复制到项目AndroidMenifest.xml文件下的API_KEY位置即可。
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/AK.png)<br/><br/>
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/AK02.png)<br/><br/>
###（5）运行项目
&emsp;&emsp;&nbsp;到此为止，你已经可以在真机或者模拟器上运行这个App了。但是请注意，模拟器运行时定位功能将不可用，个人建议最好在真机上运行调试。
##3.开发建议
&emsp;&emsp;&nbsp;通过查看主界面可以知道，登录注册模块、智能助手模块和娱乐模块并没有实现，大家如果有其他比较好的实现方式，也可以进行体现。希望这个开源项目可以在大家的努力下越来越好,期待你的code。
##4.运行效果
###（1）定位功能
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/定位.jpg)<br/><br/>
###（2）卫星地图
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/卫星地图.jpg)<br/><br/>
###（3）交通流量图
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/交通流量图.jpg)<br/><br/>
###（4）城市热力图
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/热力图.jpg)<br/><br/>
###（5）罗盘模式
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/罗盘模式.jpg)<br/><br/>
###（6）公交地铁查询
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/公交查询01.jpg)<br/><br/>
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/公交查询02.jpg)<br/><br/>
###（7）离线地图下载
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/离线地图.jpg)<br/><br/>
##5.技术博客
我的个人技术博客：[http://blog.csdn.net/chenyufeng1991](http://blog.csdn.net/chenyufeng1991) 。欢迎大家访问！
