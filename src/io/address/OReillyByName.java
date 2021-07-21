package io.address;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class OReillyByName {

    /**
     * @author GY
     * @since 2018年10月6日
     * @功能:建立与本地DNS服务器的一个连接，来查找名字和数字地址
     * @说明:
     */
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.taobao.com");
            System.out.println(address.getHostAddress());
            System.out.println(address);

            String str = "124.193.235.253";
            String[] ipStr = str.split("\\.");
            byte[] ipBuf = new byte[4];
            for (int i = 0; i < 4; i++) {
                ipBuf[i] = (byte) (Integer.parseInt(ipStr[i]) & 0xff);
            }

            InetAddress ia = InetAddress.getByAddress(ipBuf);
            System.out.println(ia);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

}
