# BaiduMap-TrafficAssistant
&emsp;&emsp;&nbsp;该项目是基于百度地图SDK开发的一款交通助手App，目前已经上线豌豆荚、魅族应用市场、搜狗手机助手等多个安卓应用市场。目前我决定开源该项目，为更多的安卓应用开发者或者基于百度地图SDK开发人员提供服务和便利。当然App中还有不少bug和可扩展的功能模块，也希望各位开发者为该项目贡献自己的code力量。大家可以通过点击右上角的fork到自己的Github仓库，也可以点击star实时关注项目进度。 
## 1.项目简介
&emsp;&emsp;&nbsp;该版本的百度地图SDK为v3.2.0. 使用百度地图SDK实现的功能有：普通地图显示、卫星地图显示、交通流量图显示、城市热力图显示、定位功能、模式切换（普通模式、跟随模式、罗盘模式）、公交地铁查询、离线地图下载和导航功能。其他的附加功能有浏览网页功能和通讯录功能。</br>
&emsp;&emsp;&nbsp;其中我还留出多个接口可供大家开发其他模块：登录注册功能、智能助手和娱乐功能。如果你有好的idea，也可以在App里面实现。希望在更多开发者的共同努力下，把这个应用开发成一个优秀的产品。系统主界面如下：
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/主界面.jpg)<br/><br/>
## 2.开发指南
&emsp;&emsp;&nbsp;如果你下载或者clone代码，其实并不能在你的真机或者模拟器上显示出百度地图，还需要进行简单的百度地图SDK开发的配置。配置方式如下：
### （1）创建应用
&emsp;&emsp;&nbsp;你需要先去百度地图SDK平台官网[http://developer.baidu.com/map/index.php?title=%E9%A6%96%E9%A1%B5](http://developer.baidu.com/map/index.php?title=%E9%A6%96%E9%A1%B5)申请一个key值，进入右上角的“API控制台”，然后创建一个应用即可。
### （2）获取key
&emsp;&emsp;&nbsp;我们需要创建的是一个Android应用。如图所示：
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/创建应用.png)<br/><br/>
### （3）安全码
&emsp;&emsp;&nbsp;在上一个步骤中，需要填入一个安全码，这个非常关键！安全码 = 数字签名+；+包名。该App的包名为：com.android.traffic.  那如何获取数字签名呢？可以去Eclipse中的Preferences-->Android-->Build下获取，其中SHA1 fingerprint就是我们要的数字签名。如图：
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/安全码.png)<br/><br/>
### （4）获取AK复制到项目
&emsp;&emsp;&nbsp;在完成上述步骤后，就可以获取一个访问应用（AK），然后把该AK复制到项目AndroidMenifest.xml文件下的API_KEY位置即可。
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/AK.png)<br/><br/>
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/AK02.png)<br/><br/>
### （5）运行项目
&emsp;&emsp;&nbsp;到此为止，你已经可以在真机或者模拟器上运行这个App了。但是请注意，模拟器运行时定位功能将不可用，个人建议最好在真机上运行调试。
## 3.开发建议
&emsp;&emsp;&nbsp;通过查看主界面可以知道，登录注册模块、智能助手模块和娱乐模块并没有实现，大家如果有其他比较好的实现方式，也可以进行体现。希望这个开源项目可以在大家的努力下越来越好,期待你的code。
## 4.运行效果
### （1）定位功能
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/定位.jpg)<br/><br/>
### （2）卫星地图
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/卫星地图.jpg)<br/><br/>
### （3）交通流量图
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/交通流量图.jpg)<br/><br/>
### （4）城市热力图
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/热力图.jpg)<br/><br/>
### （5）罗盘模式
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/罗盘模式.jpg)<br/><br/>
### （6）公交地铁查询
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/公交查询01.jpg)<br/><br/>
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/公交查询02.jpg)<br/><br/>
### （7）离线地图下载
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/离线地图.jpg)<br/><br/>
## 5.技术博客
我的个人技术博客：[http://blog.csdn.net/chenyufeng1991](http://blog.csdn.net/chenyufeng1991) 。欢迎大家访问！


# Introduce In English
&emsp;&emsp;&nbsp;This Android App is based on BaiduMap SDK called TrafficAssistant.At present,the app is sold on Peasecod,Meizu App Market,Sougou Assistant.But now,I decide to opensource this project which for many Android App developers and BaiduMap developers.At the same time,the project has bugs and lack of some function modules,which I hope developers could contribute your nice code to  this app.You could click fork button in the upper right corner to acquire project to you Github repo,or click star button to focus on project progress.
## 1.Project Introduce
&emsp;&emsp;&nbsp;The version of BaiduMap SDK is v3.2.0. Now the functions of app are showing general map,satellite map,traffic flow map,thermodynamic map,location,mode switch(common mode,following mode,compass mode),bus and subway inquiry,downloading offline map and navagation.Other addtional function are browsing website and contacts.</br>
&emsp;&emsp;&nbsp;This app give you some interfaces to develop other modules like login and register function,intelligence helper and entertainment.If you have good ideas,could realize them in the app.I hope more and more Android developers work togerther,and develop an excellent product.The main interface of app is following:
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/主界面.jpg)<br/><br/>
## 2.Develop Guideline
&emsp;&emsp;&nbsp;If you download or clone the code,the app can't run on your smart  phone or simulator.In fact,you need some simple configurations about BaiduMap SDK.The configuration methods are following:
### (1)Create Application
&emsp;&emsp;&nbsp;First you need go to BaiduMap official website[http://developer.baidu.com/map/index.php?title=%E9%A6%96%E9%A1%B5](http://developer.baidu.com/map/index.php?title=%E9%A6%96%E9%A1%B5)to apply a key where enter the "API Console"upper right corner. 
### (2)Obtain Key
&emsp;&emsp;&nbsp;we need create an Android app.As shown in figure:
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/创建应用.png)<br/><br/>
### (3)Security Code
&emsp;&emsp;&nbsp;In the last step,need a security code.This is very important! Security Code = SHA1 fingerprint + package name. This app package name is "com.android.traffic".How could we obtain SHA1 fingerprint? We can go to Eclipse-->Preferences-->Android-->Build ,now you could see SHA1:
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/安全码.png)<br/><br/>
### (4)Copy AK to Project
&emsp;&emsp;&nbsp;After completing the above steps,you could obtain a AK(Application Key),then copy AK to AndroidMenifest.xml of peoject,where the position in API_KEY.
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/AK.png)<br/><br/>
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/AK02.png)<br/><br/>
### (5)Run Project
&emsp;&emsp;&nbsp;So far,you could run the app on your smart mobile phone or simulators.But be careful,the location function may invalid on simulator. Personnally,run app on real machine at best.
## 3.Development Suggestion
&emsp;&emsp;&nbsp;By viewing main interface,login-register module,intelligence helper and entertainment didn't implement.If you have wonderful idea,just coding and pull request.I hope this opensource project will better in everyone's efforts.I expect you code.
## 4.Operation Effect
### (1)Location
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/定位.jpg)<br/><br/>
### (2)Satellite Map
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/卫星地图.jpg)<br/><br/>
### (3)Traffic Flow Map
 ![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/交通流量图.jpg)<br/><br/>
### (4)Thermodynamic Map
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/热力图.jpg)<br/><br/>
### (5)Compass Mode
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/罗盘模式.jpg)<br/><br/>
### (6)Bus And Subway Inquiry
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/公交查询01.jpg)<br/><br/>
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/公交查询02.jpg)<br/><br/>
### (7)Download Offline Map
![Alt text](https://github.com/chenyufeng1991/BaiduMap-TrafficAssistant/raw/master/Screenshots/离线地图.jpg)<br/><br/>
## 5.Technology Blog
&emsp;&emsp;&nbsp;My personal technology blog:[http://blog.csdn.net/chenyufeng1991](http://blog.csdn.net/chenyufeng1991) .Welcome to visit.