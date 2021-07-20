package io.netty.msgpack;

import org.apache.commons.codec.binary.Base64;
import org.msgpack.MessagePack;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created By gao_e on 2020/3/8 17:21
 */
public class MsgPackUtil {

//    public static void main(String[] args) {
//        SmartRPCRequest request = new SmartRPCRequest();
//        request.setId(1);
//        request.setB(1);
//        request.setA(1);
//        String s = MsgPackUtil.serializationObject(request);
//        System.out.println(s);
//        SmartRPCRequest smartRPCRequest = MsgPackUtil.deserializationObject(s, SmartRPCRequest.class);
//        System.out.println(smartRPCRequest);
//    }

    public static <T extends Serializable> T deserializationObject(List list, Class<T> clazz) {
        MessagePack msgpack = new MessagePack();
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (int i = 0; i < list.size(); i++) {
                Field field = fields[i];
                field.setAccessible(true);
                field.set(t, list.get(i));
            }
        } catch (Exception e) {
            throw new RuntimeException("Deserialize error");
        }
        return t;
    }

    public static <T extends Serializable> String serializationObject(T obj) {
        MessagePack msgpack = new MessagePack();
        byte[] b = null;
        try {
            b = msgpack.write(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(new Base64().encode(b));
    }

    public static <T extends Serializable> T deserializationObject(String obj,
                                                                   Class<T> clazz) {
        MessagePack msgpack = new MessagePack();
        T t = null;
        byte[] bytes = new Base64().decode(obj.getBytes());
        try {
            t = msgpack.read(bytes, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

}
