#QuickStar

QuickStar是一个可快速开发web应用的项目，基于Spring Boot微服务框架, 配置简单方便，
可将Web应用打包成jar包，方便部署，测试.<br>
数据库采用MySQL, 持久层框架采用MyBatis,可用mybatis-generator自动生成对应的类和xml文件，
包括了常用的增删改查，如果有比较复杂的需求可增加一些SQL语句<br>
采用Redis作为缓存，常用的一些方法写在相应的工具类中<br>
集成了Netty， 实现非阻塞通讯，可用于一些业务的非阻塞通讯，例如与智能硬件设备的数据通讯<br>
