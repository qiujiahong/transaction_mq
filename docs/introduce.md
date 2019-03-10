# 简介


![image.png](https://upload-images.jianshu.io/upload_images/11852957-f590860a53181cc3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



事务是一种可靠、一致的方式访问和操作数据库中的程序单元。

事务的原则：
* A原子性，全部执行，或者全部不执行；
* C一致性，状态改变的结果是完整并且一致的。
* I 隔离性，一个事务在操作过程中没有提交之前其他进程看不到没提交的数据；
* D 持久性， 数据提交以后，这个结果才会保存


