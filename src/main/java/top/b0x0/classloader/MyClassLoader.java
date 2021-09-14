package top.b0x0.classloader;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;

/**
 * MyClassLoader.java是自定义类加载器，用于解密被加密后的class文件。
 *
 * @author ManJiis
 * @since 2021-08-02
 */
public class MyClassLoader extends ClassLoader {
    private Key key = null;
    private String tranInfo = null;

    private String classPath;

    public MyClassLoader() {

    }

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    public MyClassLoader(Key key, String tranInfo) {
        this.key = key;
        this.tranInfo = tranInfo;
    }

    @Override
    protected Class findClass(String name) throws ClassNotFoundException {
        // 获取class解密后的字节码
        byte[] classBytes = null;
        try {
            classBytes = loadClassBytes(name);
        } catch (Exception e) {
            throw new ClassNotFoundException(name);
        }

        // 使用字节码，实例类对象
        String className = name.substring(name.lastIndexOf("/") + 1, name.lastIndexOf("."));

        // defineClass方法可以把二进制流字节组成的文件转换为一个java.lang.Class
        Class cl = defineClass(className, classBytes, 0, classBytes.length);
        if (cl == null) {
            throw new ClassNotFoundException(name);
        }
        return cl;
    }

    /**
     * 读取自定义加密的class文件
     *
     * @param name /
     * @return /
     * @throws IOException /
     */
    private byte[] loadClassBytes(String name) throws IOException {
        // 读入文件
        FileInputStream ins = null;
        ByteArrayOutputStream baos = null;
        CipherInputStream cis = null;
        byte[] result = null;
        try {
            ins = new FileInputStream(name);
            Cipher cip = Cipher.getInstance(this.tranInfo);
            cip.init(Cipher.DECRYPT_MODE, this.key);
            // 使用密码解密class文件
            cis = new CipherInputStream(ins, cip);
            baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = cis.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            result = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {                    // 释放流
            baos.close();
            cis.close();
            ins.close();
        }
        return result;
    }
}

