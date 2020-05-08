# RMI

## Java RMI定义

Java RMI：Java远程方法调用，即Java RMI（Java Remote Method Invocation）
是Java编程语言里，一种用于实现远程过程调用的应用程序编程接口。它使客户机上运行的
程序可以调用远程服务器上的对象。远程方法调用特性使Java编程人员能够在网络环境中分
布操作。RMI全部的宗旨就是尽可能简化远程接口对象的使用。

## Java RMI工作原理

RMI能让一个Java程序去调用网络中另一台计算机的Java对象的方法，那么调用的效果就像是在本机上调用一样。通俗的讲：A机器上面有一个class，通过远程调用，B机器调用这个class 中的方法。
RMI，远程方法调用（Remote Method Invocation）是Enterprise JavaBeans的支柱，是建立分布式Java应用程序的方便途径。RMI是非常容易使用的，但是它非常的强大。
RMI的基础是接口，RMI构架基于一个重要的原理：定义接口和定义接口的具体实现是分开的。

## Java RMI的局限

RMI目前使用Java远程消息交换协议JRMP（Java Remote Messaging Protocol）进行
通信。JRMP是专为Java的远程对象制定的协议，由于JRMP是专为Java对象制定的，因此，
RMI对于用非Java语言开发的应用系统的支持不足。不能与用非Java语言书写的对象进行通
信（意思是只支持客户端和服务器端都是Java程序的代码的远程调用）。

## 