package classloader;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

/**
 * Mycipher.java用于加密EasyTest.class。
 *
 * @author ManJiis
 * @since 2021-08-02
 */
public class MyCipher {
    private Key key = null;
    private String tranInfo = null;

    public MyCipher(Key key, String tranInfo) {
        this.key = key;
        this.tranInfo = tranInfo;
    }

    public void cipher(InputStream ins, OutputStream ous) throws IOException {
        // 创建加密输出流
        Cipher cip = null;
        CipherOutputStream cos = null;
        try {

            cip = Cipher.getInstance(this.tranInfo);
            cip.init(Cipher.ENCRYPT_MODE, this.key);
            cos = new CipherOutputStream(ous, cip);
            // 写出到输入流
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = ins.read(buf)) != -1) {
                cos.write(buf, 0, len);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放输入和输出流
            cos.close();
            ins.close();
            ous.close();
        }
    }
}