# 成都市主城区生鲜冷链服务平台



基于 Vue3 + Element Plus + iClient for Leaflet 的生鲜冷链服务平台。



## 前言

该服务平台基于VUE3+ElementPlus+iClientt for leaflet

技术栈为：Vue2/Vue3+ElementUI/ElementPlus+Echarts+Axios+Node+Mysql+SpringBoot+Redis+Uniapp

采用iFrame将多个项目集成到一个系统界面进行展示



系统界面采用GitHub上的后台管理系统框架-项目地址：[lin-xin/vue-manage-system at V4.2.0 (github.com)](https://github.com/lin-xin/vue-manage-system/tree/V4.2.0)

## 项目截图

### 登录

![](./systemScreen/登录.png)

### 首页

![](./systemScreen/首页-1.png)

![](./systemScreen/首页-2.png)



地图平台

![](./systemScreen/地图平台.png)



自定义SQL查询

![](./systemScreen/自定义SQL查询.png)



拉框查询

![](./systemScreen/拉框查询.png)



地址匹配 - 模糊匹配 -限制返回数为3

![](./systemScreen/地址匹配.png)



搜索栏查询

![](./systemScreen/queryHeader-1.png)

![](./systemScreen/queryHeader-2.png)



地址匹配-高级选项

![](./systemScreen/地址匹配高级选项.png)

地址匹配 - 返回数无限制

![](./systemScreen/地址匹配-无限制.png)



服务区分析 - 全部

![](./systemScreen/服务区分析-全部.png)

服务区分析 - 地区

![](./systemScreen/服务区分析-地区.png)



服务区分析 - 查询

![](./systemScreen/服务区分析-搜索.png)

服务区分析 - 单点

![](./systemScreen/服务区分析-单点.png)



缓冲区分析-参数配置

![](./systemScreen/缓冲区分析参数配置.png)

缓冲区分析-圆形-左右等距

![](./systemScreen/缓冲区分析-1.png)

缓冲区分析-方形-左右不等距

![](./systemScreen/缓冲区分析-2.png)



生鲜配送 - 切换底图 - 天地图

![](./systemScreen/生鲜配送.png)





单点配送高级选项

![](./systemScreen/单点配送-高级选项.png)

单点配送

![](./systemScreen/单点配送-1.png)

单点配送-最佳路径

![](./systemScreen/单点配送-2.png)



多方配送

![](./systemScreen/多方配送.png)



聚合配送

![](./systemScreen/聚合配送.png)

聚合配送 - 高级选项 -上传文件

![](./systemScreen/聚合配送-高级选项-上传文件.png)

聚合配送 - 根据上传的xls进行配送

![](./systemScreen/聚合配送-根据xls进行配送.png)



生鲜查询

![](./systemScreen/生鲜查询.png)

生鲜查询高级选项

![](./systemScreen/生鲜查询高级选项.png)



优惠生鲜

![](./systemScreen/优惠生鲜.png)

优惠生鲜-动效设置

![](./systemScreen/优惠生鲜-动效设置.png)

优惠生鲜-动效展示

![](./systemScreen/优惠生鲜-动效展示-1.png)

![](./systemScreen/优惠生鲜-动效展示-2.png)

![](./systemScreen/优惠生鲜-动效展示-3.png)



热力图 - 点击流量和商品进行动态展示

![](./systemScreen/热力图.png)





强度图

![](./systemScreen/强度图-1.png)

![](./systemScreen/强调图-2.png)

![](./systemScreen/强度图-3.png)

![](./systemScreen/强度图-4.png)



数据3D可视化

![](./systemScreen/3D可视化.png)



生鲜展示

![](./systemScreen/生鲜展示.png)





超市管理

![](./systemScreen/超市管理.png)



超市大屏-信息面板

![](./systemScreen/超市大屏-1.png)

超市大屏 - 模型展示

![](./systemScreen/超市大屏-2.png)

超市大屏 - 多角度展示

![](./systemScreen/超市大屏-多角度展示.png)



小程序管理

用户管理

![](./systemScreen/小程序管理-用户管理.png)

订单管理

![](./systemScreen/小程序管理-订单管理.png)

商品管理

![](./systemScreen/小程序管理-商品管理.png)

商品管理-搜索

![](./systemScreen/小程序管理-商品管理-搜索.png)

商品管理 - 编辑商品

![](./systemScreen/小程序管理-商品管理-编辑.png)

商品管理 - 删除

![](./systemScreen/小程序管理-商品管理-删除.png)

商品管理-添加商品

![](./systemScreen/小程序管理-商品管理-添加商品.png)

![](./systemScreen/小程序管理-商品管理-添加商品-成功.png)



数据管理

查询

![](./systemScreen/数据管理-查询.png)

分页

![](./systemScreen/数据管理-分页.png)

## 功能

- [x] 登录

- [x] 系统首页-信息总览

- [x] 地图显示

- [x] 对接在线天地图

- [x] 叠加成都市行政区划图

- [x] 拉框查询-弹窗展示鲜品价目信息

- [x] 搜索框

- [x] 地址匹配查询

- [x] 超市ID查询

- [x] 自定义SQL查询

- [x] 距离查询

- [x] 生鲜配送

- [x] 服务区分析

- [x] 缓冲区分析

- [x] 单方配送

- [x] 多方配送

- [x] 聚合配送

- [x] 点聚合

- [x] Echarts叠加显示生鲜价格

- [x] 热力图+蜂巢图叠加展示数据

- [x] 热力图时序展示流量、价格变化

- [x] node读取xls、json

- [x] node连接mysql

- [x] 数据3D可视化

- [x] 生鲜图片及描述展示

- [x] 订单管理

- [x] 超市管理

- [x] 超市大屏-信息面板-BIM模型

- [x] 数据管理-查询与分页

- [x] 强度线

- [x] 小程序交互

- [x] 数据库共享

- [x] 用户管理

- [x] 订单管理 - 购物车提交

- [x] 商品管理 - 添加修改商品

  
  
  

## 安装步骤

```
git clone https://gitee.com/xiao-Zhanggg/cheng-du-fresh-manage    // 把模板下载到本地
cd cheng-du-fresh-manage    // 进入模板目录
npm install         // 安装项目依赖，等待安装完成之后，安装失败可用 cnpm 或 yarn

// 开启服务器，浏览器访问 http://localhost:8080
npm run serve

// 用终端打开位于Src -> serve使用命令：node ./inex.js 打开服务区

// 用VScode打开 目录下的systemTwo，打开index.html 右键 Open With LiveServer

// 将systemTwo的打开端口复制到Src -> systemTwoPort.json 如果一样则不需要
```

## License

[MIT](https://github.com/lin-xin/vue-manage-system/blob/master/LICENSE)
