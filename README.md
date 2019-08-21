# Dragon

### 运维小公举服务
##### 1.提供一些日常开发运维过程中需要的一些小工具，释放双手。
##### 2.学习练手的一些Demo

---
### 项目的整体结构说明
* dragon
    * main //小工具使用的包
        * controller
            * AlgorithmController //尝试一些算法使用的main方法
            * DemoController //尝试一些jdk中的方法或者不用调用service的方法
            * UtilController //提供一个main方法调用service中提供的具体运维工具的方法
        * dao //数据层
            * model //数据模型
                * xmlutil //测试xmlUtil时使用的数据模型
        * service //提供具体的运维时候的类，一个运维内容一个类
        * util //运维工具类所需要的可以封装为工具的方法
    * redis //提供操作redis的一些方法
        * conf //redis集群配置，读取配置文件中的配置。并配置操作redis的方法等信息
        * controller //提供可供网页访问的方法，返回需要的信息
        * entity //redis配置文件需要的实体，把配置文件转换成实体
    * study //学习过程中练手的包，good good study,day day up!
        * design //设计模式的探索
            * singleton //单例模式
            * template //模板模式
        * arraylist //自己实现一个arrayList方法
    * swagger
        * conf //swagger需要的配置文件
---
### 项目中可能用到的一些资料
* [Apache Maven repository](https://mvnrepository.com/)

