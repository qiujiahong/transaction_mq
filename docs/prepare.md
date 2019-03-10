# 环境准备

本教程使用到的软件包括：  
* mysql
* idea
* docker 
* maven 
* spring boot 2.1.2.RELEASE

## docker 运行mysql


```bash
docker run --name mysql1 -e MYSQL_ROOT_PASSWORD=123456 \
        -e MYSQL_DATABASE=db1 -e MYSQL_ROOT_HOST=% \
        -e MYSQL_USER=nick -e MYSQL_PASSWORD=123456  -d -p 3307:3306 mysql/mysql-server:5.7


docker run --name mysql1 -e MYSQL_ROOT_PASSWORD=123456 \
        -e MYSQL_DATABASE=db1 -e MYSQL_ROOT_HOST=% \
        -e MYSQL_USER=nick -e MYSQL_PASSWORD=123456  -d -p 3307:3306 \
        -v $PWD/mysql_data:/var/lib/mysql mysql/mysql-server:5.7

```