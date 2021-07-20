package math;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author G_Y
 * @Date 2020/6/18 11:42
 * @Description: 排序1G 1亿 数据
 **/
public class DivideAndConquer1GInt {

    public static void main(String[] args) throws IOException {
        initData(100000000);
//        long time1 = System.currentTimeMillis();
//        File file = new File("D:\\int10M.txt");
//        // 拆分文件
//        List<String> strings = splitFile(file, 1 * 1024 * 1024);
//        System.out.println(System.currentTimeMillis() - time1);
//        for (String filePath : strings) {
//
//        }
    }

    // 初始化
    public static void initData(int count) throws FileNotFoundException, UnsupportedEncodingException {
        File file = new File("D:\\1G.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");

        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        long time1 = System.currentTimeMillis();
        for (int i = 1; i <= count; i++) {
            int i1 = random.nextInt(2000000000);
            stringBuilder.append(i1);
            stringBuilder.append("\n");
            if (i % 10000 == 0) {
                String s = stringBuilder.toString();
                try {
                    outputStreamWriter.write(s);
                    outputStreamWriter.flush();
                    stringBuilder = new StringBuilder();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - time1);
    }

    // 文件分割 每个文件数据不超过64M
    public static List<String> splitFile(File file, Integer maxByte) throws IOException {
        List<String> filePaths = new ArrayList<>();
        //读取文件
        BufferedReader br =
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file), "utf-8"
                        )
                );
        String s = null;
        F1:
        for (int i = 1; ; i++) {
            File indexFile = new File("D:\\int10M" + i + ".txt");
            filePaths.add(indexFile.getPath());
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(new FileOutputStream(indexFile), "utf-8");
            StringBuilder sb = new StringBuilder();
            int j = 0;
            while ((s = br.readLine()) != null) {
                if (indexFile.length() > maxByte) {
                    outputStreamWriter.write(s);
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    continue F1;
                }
                //写出到子文件
                sb.append(s + "\n");
                j++;
                if (j > 1000) {
                    outputStreamWriter.write(sb.toString());
                    outputStreamWriter.flush();
                    sb = new StringBuilder();
                    j = 0;
                }
            }
            break;
        }
        br.close();
        //返回子文件路径集合
        return filePaths;
    }

    // 单个文件排序 将单个文件数据读取到内存，排序后，写出到新文件
//    public static String orderByAsc() {
//
//    }

    // 文件合并 同时读取两个文件内容, 根据读取内容的大小，写出到合并文件

}
