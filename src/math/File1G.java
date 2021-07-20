package math;

import java.io.*;
import java.util.*;

/**
 * GY
 */
public class File1G {
    public static void main(String[] args) throws IOException {
        String initFilePath = initFile(100000000);
        File file = new File(initFilePath);
        long time1 = System.currentTimeMillis();
        // 分割的子文件集合
        List<String> filePath = splipFile(file, 128 * 1024 * 1024);
        System.out.println("over:" + (System.currentTimeMillis() - time1));
        // 排序后的新子文件集合
        List<String> sortPath = new LinkedList<>();
//        List<String> filePath = new ArrayList<>();
//        filePath.add("F:\\file1G1.txt");
        for (String string : filePath) {
            // 单个文件进行排序
            String s = sortFile(new File(string));
            sortPath.add(s);
        }
//        sortPath.add("F:\\test1.txt");
//        sortPath.add("F:\\test2.txt");
//        sortPath.add("F:\\test3.txt");
        //合并文件
        int c = 0;
        while (true) {
            if (sortPath.size() == 1) {
                break;
            }
            String s = sortPath.get(0);
            String s1 = sortPath.get(1);
            c++;
            String s2 = joinFile(new File(s), new File(s1), c);
            sortPath.add(s2);
            sortPath.remove(0);
            sortPath.remove(0);
        }
        System.out.println(c);
        System.out.println(sortPath.get(0));
    }

    // 初始化 测试文件
    public static String initFile(int count) throws IOException {
        File file = new File("F:\\file1G.txt");
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw =
                new OutputStreamWriter(fos, "utf-8");
        StringBuilder sb = new StringBuilder(1000);
        Random random = new Random();
        for (int i = 1; i <= count; i++) {
            int num = random.nextInt(2000000000);
            sb.append(num);
            sb.append("\n");
            if (i % 1000 == 0) {
                osw.write(sb.toString());
                osw.flush();
                sb = new StringBuilder(1000);
            }
        }
        osw.close();
        return file.getPath();
    }

    // 拆分文件 size 为 子文件 ,size = 子文件字节上限，file = 大文件；返回 子文件 路径 集合
    public static List<String> splipFile(File file, int size) throws IOException {
        long length = file.length();
        System.out.println(length);
        String filePath = file.getPath();
        System.out.println(filePath);
        if (length <= size) {
            return Arrays.asList(filePath);
        }
        List<String> fileNames = new ArrayList<>();
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, "utf-8");
        BufferedReader br = new BufferedReader(isr);

        F1:
        for (int i = 1; ; i++) {
            File subFile = new File("F:\\file1G" + i + ".txt");
            fileNames.add(subFile.getPath());
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(new FileOutputStream(subFile), "utf-8");
            String s = "";
            StringBuilder sb = new StringBuilder();
            // 一行一行读取
            int count = 1;
            while ((s = br.readLine()) != null) {
                // 读取到StringBuilder
                sb.append(s + "\n");
                if (count > 5000) {
                    outputStreamWriter.write(sb.toString());
                    outputStreamWriter.flush();
                    if (subFile.length() > size) {
                        continue F1;
                    }
                    sb = new StringBuilder();
                    count = 1;
                    continue;
                }
                count++;
            }
            outputStreamWriter.write(sb.toString());
            outputStreamWriter.flush();
            outputStreamWriter.close();
            break;
        }
        br.close();
        return fileNames;
    }

    // 对单个文件进行排序 返回排序之后的新文件名称
    public static String sortFile(File file) throws IOException {
        long t = System.currentTimeMillis();
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
        List<Integer> list = new ArrayList<>(13000000);
        String s = null;
        while ((s = bufferedReader.readLine()) != null) {
            list.add(Integer.valueOf(s));
        }
        System.out.println(System.currentTimeMillis() - t);
        t = System.currentTimeMillis();
        bufferedReader.close();
        // 排序
        Integer[] ints = new Integer[list.size()];
        Integer[] integers = list.toArray(ints);
        Arrays.sort(integers);
        System.out.println(System.currentTimeMillis() - t);
        t = System.currentTimeMillis();
        String newPath = file.getPath().replaceAll("\\.", "new.");
        File newFile = new File(newPath);
        OutputStreamWriter outputStreamWriter =
                new OutputStreamWriter(new FileOutputStream(newFile), "utf-8");
        StringBuilder sb = new StringBuilder(5000);
        for (int i = 0; i < integers.length; i++) {
            Integer integer = integers[i];
            sb.append(integer + "\n");
            if ((i + 1) % 5000 == 0) {
                String s1 = sb.toString();
                outputStreamWriter.write(s1);
                outputStreamWriter.flush();
                sb = new StringBuilder(5000);
                continue;
            }
        }
        outputStreamWriter.write(sb.toString());
        outputStreamWriter.flush();
        outputStreamWriter.close();
        System.out.println(System.currentTimeMillis() - t);
        return newPath;
    }

    // 对两个文件进行排序合并,返回合并后的新文件地址
    public static String joinFile(File file1, File file2, int c) throws IOException {
        // TODO 两两合并 测试，待优化!
        File file = new File("F:\\file1G_merge"+c+".txt");
        // 写出
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file),"utf-8");
        // 读入
        BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(file1), "utf-8"));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2), "utf-8"));
        boolean f1HasNum = true;
        boolean f2HasNum = true;
        String s1 = br1.readLine();
        String s2 = br2.readLine();
        while (true) {
            if(s1 == null && s2 == null) {
                br1.close();
                br2.close();
                break;
            }
            if(s1 == null) {
                f1HasNum = false;
                osw.write(s2+"\n");
                s2 = br2.readLine();
                continue;
            }
            if(s2 == null) {
                f2HasNum = false;
                osw.write(s1+"\n");
                s1 = br1.readLine();
                continue;
            }
            if(Integer.valueOf(s1) <= Integer.valueOf(s2)) {
                osw.write(s1+"\n");
                s1 = br1.readLine();
                continue;
            }
            if(Integer.valueOf(s1) >= Integer.valueOf(s2)) {
                osw.write(s2+"\n");
                s2 = br2.readLine();
                continue;
            }
        }
        osw.close();
        return file.getPath();
    }
}
