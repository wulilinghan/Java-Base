package rpc.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author GY
 * @date 2018年10月6日
 * @说明:对象序列化、反序列化测试
 */
public class SerializeTest {

    public static void main(String[] args)
            throws IOException, ClassNotFoundException {

        // 字节数组输出流
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // 对象输出流
        ObjectOutputStream oos = new ObjectOutputStream(os);
        // 可序列化对象
        Person zhangsan = new Person(1, "ZhangSan");
        // 写入对象
        oos.writeObject(zhangsan);
        // 写入字节流
        byte[] zhangsanByte = os.toByteArray();
        oos.close();
        os.close();

        // 字节数组输入流
        ByteArrayInputStream is = new ByteArrayInputStream(zhangsanByte);
        // 执行反序列化，从流中读取对象
        ObjectInputStream ois = new ObjectInputStream(is);
        // 读出对象
        Person person = (Person) ois.readObject();
        System.out.println(person);
        ois.close();
        is.close();

    }

}
