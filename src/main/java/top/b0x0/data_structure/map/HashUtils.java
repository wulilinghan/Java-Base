package top.b0x0.data_structure.map;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ManJiis
 * @since 2021-09-17
 * @since JDK1.8
 */
public class HashUtils {

    /**
     * <pre>>
     *  public int hashCode() {
     *       int h = hash;
     *    if (h == 0 && value.length > 0) {
     *          char val[] = value;
     *       for (int i = 0; i < value.length; i++) {
     *             h = 31 * h + val[i];
     *       }
     *           hash = h;
     *      }
     *       return h;
     *   }
     * </pre>
     *
     * @param str        字符串
     * @param multiplier /
     * @return int
     */
    public static int hashCode(String str, Integer multiplier) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = multiplier * hash + str.charAt(i);
        }
        return hash;
    }

    /**
     * 读取本地文件，单词表
     *
     * @param uri 单词表.txt文件
     * @return 单词集合(去重)
     */
    public static Set<String> readTxt(String uri) {
        Set<String> set = new HashSet<>();
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(uri), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] ss = line.split("\t");
                set.add(ss[1]);
            }
            br.close();
            isr.close();
        } catch (Exception ignore) {
            return null;
        }
        return set;
    }
}
