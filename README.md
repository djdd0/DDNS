# ddns
在TP-LINK+阿里云解析DNS环境下使用的动态DNS小软件

# 使用方法

将项目导入idea，修改properties.txt中的参数，运行主类：djdd.ddns.DdnsClient。

部署时可导出为jar包或使用[Release](https://github.com/djdd0/ddns/releases)，将ddns.jar与properties.txt放在同一目录下，使用`java -jar ddns.jar`命令运行。

# 参数获取方法

`getIPMethod` 参数可以为`localIPv4`/`localIPv6`/`TPlinkIPv4`。

当获取到的IP地址为IPv4时，`recordType`需为`A`，当获取到的IP地址为IPv6时，`recordType`需为`AAAA`。

`RR`：主机记录

当通过TP-LINK获得IP地址时 Password获取方法：（参考链接第一步）
https://www.coderbbb.com/articles/70

阿里云AccessKey获取方法：
https://help.aliyun.com/zh/ram/user-guide/create-an-accesskey-pair
