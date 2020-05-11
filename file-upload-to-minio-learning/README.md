# minIO
## minIO简述

Minio是Apache License v2.0下发布的对象存储服务器。它与Amazon S3云存储服务兼容。它最适合存储非结构化数据，如照片，视频，日志文件，备份和容器/ VM映像。对象的大小可以从几KB到最大5TB
Minio服务器足够轻，可以与应用程序堆栈捆绑在一起，类似于NodeJS，Redis和MySQL。
https://docs.minio.io/

## 二进制安装

### 环境

操作系统：GNU / Linux
[root@wzs ~]# uname -m #计算机类型
x86_64

### server端安装

wget https://dl.minio.io/server/minio/release/linux-amd64/minio
chmod +x minio
./minio server /data
注意启动显示的 appkey secretkey是默认登录账户，密码

### 登录浏览器

浏览器输入： http://ip:9000
例如：http://10.0.102.201:9000 http://172.17.42.1:9000 http://127.0.0.1:9000

AccessKey: PQUIIGGESEPK5MBWO054
SecretKey: I9Zz+ukMi5fmG54D84uzBU6rxQJcadcxrM5vQI+R

## Docker安装

docker pull minio/minio
docker run -p 9000:9000 -e MINIO_ACCESS_KEY=admin -e MINIO_SECRET_KEY=123123123 -v /data:/data minio/minio server /data

### 登录浏览器

浏览器输入： http://ip:9000
账户：admin
密码：123123123

## 操作

### 修改密码
点击右上角

### 上传文件
选中文件夹
点击右下角 + —>点击Upload file—>选中要上传文件

### 创建/删除bucket（文件夹）
**点击右下角加号—>选择create bucket **
添加bucket
删除bucket

### 查看bucket文件信息
点击data，查看与设置该Object的基本信息：
查看共享地址Shareable Link
设置到期时间，最大可保存时间为7天
对话框上方弹出该Object现剩余到期时间

### 忘记密码

#### 二进制安装配置文件位置
/data/.minio.sys/config

#### docker安装配置文件位置
看运行容器时是否指定映射文件 –v
列：-v data:data
/data/.minio.sys/config
没有则默认
/var/lib/docker/vfs/dir/5d244e01a8e8144cd10cb1db0df559dfce6c3dd70a8ca376b1d5bc7138a62865/.minio.sys/config/ config.json

直接修改配置文件中 accessKey secretKey
credential": {
"accessKey": "wzs",
"secretKey": "123123123",
"expiration": "1970-01-01T00:00:00Z",
"status": "enabled"
},

再重新启动便可

## 客户端安装

### 二进制下载（GNU / Linux）
注：要将server****端 minio****服务关闭
https://docs.minio.io/docs/minio-client-complete-guide
GNU / Linux的 64位英特尔 https://dl.minio.io/client/mc/release/linux-amd64/mc
64位PPC https://dl.minio.io/client/mc/release/linux-ppc64le/mc
运行
chmod +x mc
./mc --help

### docker安装
注：要将server端 minio服务关闭
docker pull minio/mc
docker run -it --entrypoint=/bin/sh minio/mc
然后再：
mc config

mc ls play

或者
docker run minio/mc ls play

问题：
若出现 mc: <ERROR> Unable to list folder. The difference between the request time and the server's time is too large.则说明服务器时间不准因设置时间
    