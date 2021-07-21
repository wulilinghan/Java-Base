package basic;

/**
 * @author ManJiis
 * @since 2020/11/9 21:16
 * @Description: // TODO
 **/
import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AesDataHabitUtil {
    private static final String encodeRules = "aeDaHab0604";

    public AesDataHabitUtil() {
    }

    public static String decodeStr(String content) {
        try {
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            keygen.init(128, new SecureRandom("aeDaHab0604".getBytes()));
            SecretKey original_key = keygen.generateKey();
            byte[] raw = original_key.getEncoded();
            SecretKey key = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            // base64 解码
            byte[] byte_content = Base64.decodeBase64(content);
            // 解密
            byte[] byte_decode = cipher.doFinal(byte_content);
            // 转字符串
            String AES_decode = new String(byte_decode, "utf-8");
            return AES_decode;
        } catch (NoSuchAlgorithmException var9) {
            var9.printStackTrace();
        } catch (NoSuchPaddingException var10) {
            var10.printStackTrace();
        } catch (InvalidKeyException var11) {
            var11.printStackTrace();
        } catch (IOException var12) {
            var12.printStackTrace();
        } catch (IllegalBlockSizeException var13) {
            var13.printStackTrace();
        } catch (BadPaddingException var14) {
            var14.printStackTrace();
        }

        return null;
    }


    public static String encodeStr(String content) {
        try {
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            SecretKey original_key = keygen.generateKey();
            byte[] raw = original_key.getEncoded();
            SecretKey key = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);

            // AES 加密
            byte[] byte_decode = cipher.doFinal(content.getBytes());
            // base64 编码
            byte[] byte_content = Base64.encodeBase64(byte_decode);
            String AES_decode = new String(byte_content, "utf-8");
            return AES_decode;
        } catch (NoSuchAlgorithmException var9) {
            var9.printStackTrace();
        } catch (NoSuchPaddingException var10) {
            var10.printStackTrace();
        } catch (InvalidKeyException var11) {
            var11.printStackTrace();
        } catch (IOException var12) {
            var12.printStackTrace();
        } catch (IllegalBlockSizeException var13) {
            var13.printStackTrace();
        } catch (BadPaddingException var14) {
            var14.printStackTrace();
        }

        return null;
    }


    public static void main(String[] args) {
        String s = "admin";
        String s1 = encodeStr(s);

        String s2 = decodeStr(s1);
        System.out.println(s2);


    }

}

