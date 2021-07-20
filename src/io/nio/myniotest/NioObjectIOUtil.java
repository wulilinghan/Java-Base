package io.nio.myniotest;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * @Author: GY.
 * @Description: TODO()
 * @Date:Created in 2019/11/21 0021.
 * @Modified By:
 */
public class NioObjectIOUtil {
    public static Object toObject(byte[] buff) {
        ByteArrayInputStream bis = new ByteArrayInputStream(buff);
        ObjectInputStream ois = null;
        try {
            //包装字节数组流
            ois = new ObjectInputStream(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object object = null;
        try {
            //读入对象
            object = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null)
                    bis.close();
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    public static ByteBuffer toByteBuffer(Object object) {
        ByteArrayOutputStream bio = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        ByteBuffer outBuf = null;
        try {
            //包装字节流
            oos = new ObjectOutputStream(bio);
            //将对象写入字节流
            oos.writeObject(object);
            //得到对象字节
            byte[] ba = bio.toByteArray();
            //得到对象字节的长度
            int len = ba.length;
            //字节缓冲区的大小为INT + 对象字节的长度
            outBuf = ByteBuffer.allocate((Integer.SIZE >> 3) + len);
            //压入数据：数据长度
            outBuf.putInt(len);
            //压入数据：对象数据
            outBuf.put(ba);
            //归位
            outBuf.flip();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bio != null)
                    bio.close();
                if (oos != null)
                    oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outBuf;
    }
}
