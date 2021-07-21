package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author: GY.
 * @Description: TODO()
 * @since:Created in 2019/11/21 0021.
 * @Modified By:
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //1. 获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        //2. 切换非阻塞模式
        sChannel.configureBlocking(false);

        //3. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //4. 发送数据给服务端
        Scanner scan = new Scanner(System.in);
        System.out.println("请选择:1下载图片 2下载视频");
        while (scan.hasNext()) {
            String str = scan.next();
            Date date = new Date();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String s = sd.format(date);
            buf.put((s + "\n" + str).getBytes());
            buf.flip();
            sChannel.write(buf);
            buf.clear();
            System.out.println("请继续选择:");
        }

        //5. 关闭通道
        sChannel.close();
    }
}
