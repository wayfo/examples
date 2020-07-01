# 粘包拆包演示

-  服务器接收数据时，会将客户端发送的数据进行合并或者拆分，再次过程中会造成一条完整的数据被拆分为两部分，导致解析读取异常

# 从服务端的控制台输出可以看出，存在三种类型的输出

1. 一种是正常的字符串输出。
2. 一种是多个字符串“粘”在了一起，我们定义这种 ByteBuf 为粘包。
3. 一种是一个字符串被“拆”开，形成一个破碎的包，我们定义这种 ByteBuf 为半包。
# 透过现象分析原因

应用层面使用了Netty，但是对于操作系统来说，只认TCP协议，尽管我们的应用层是按照 ByteBuf 为单位来发送数据，server按照Bytebuf读取，
但是到了底层操作系统仍然是按照字节流发送数据，因此，数据到了服务端，也是按照字节流的方式读入，
然后到了 Netty 应用层面，重新拼装成 ByteBuf，而这里的 ByteBuf 与客户端按顺序发送的 ByteBuf 可能是不对等的。
因此，我们需要在客户端根据自定义协议来组装我们应用层的数据包，然后在服务端根据我们的应用层的协议来组装数据包，
这个过程通常在服务端称为拆包，而在客户端称为粘包。
拆包和粘包是相对的，一端粘了包，另外一端就需要将粘过的包拆开，
发送端将三个数据包粘成两个 TCP 数据包发送到接收端，接收端就需要根据应用协议将两个数据包重新组装成三个数据包。
# 如何解决

在没有 Netty 的情况下，用户如果自己需要拆包，基本原理就是不断从 TCP 缓冲区中读取数据，每次读取完都需要判断是否是一个完整的数据包，
如果当前读取的数据不足以拼接成一个完整的业务数据包，那就保留该数据，继续从 TCP 缓冲区中读取，直到得到一个完整的数据包。
如果当前读到的数据加上已经读取的数据足够拼接成一个数据包，那就将已经读取的数据拼接上本次读取的数据，构成一个完整的业务数据包传递到业务逻辑，
多余的数据仍然保留，以便和下次读到的数据尝试拼接。

# 而在Netty中，已经造好了许多类型的拆包器，我们直接用就好：
1. 固定长度的拆包器 FixedLengthFrameDecoder
2. 行拆包器 LineBasedFrameDecoder
3. 分隔符拆包器 DelimiterBaseFrameDecoder
4. 基于长度域的拆包器 LengthFieldBasedFrameDecoder 