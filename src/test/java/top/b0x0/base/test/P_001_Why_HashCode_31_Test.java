package top.b0x0.base.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.b0x0.data_structure.map.HashUtils;

import javax.swing.*;
import java.util.Set;

/**
 * @author ManJiis
 * @since 2021-09-17
 * @since JDK1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class P_001_Why_HashCode_31_Test {

    private Set<String> words;

    @Before
    public void before() {
        "aa".hashCode();

        String uri = "D:\\work-space\\ower\\Java-Base\\src\\main\\java\\top\\b0x0\\data_structure\\map\\103976个英语单词库.txt";
        words = HashUtils.readTxt(uri);
    }

    @Test
    public void test() {

    }
}
