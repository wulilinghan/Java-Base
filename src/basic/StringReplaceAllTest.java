package basic;

public class StringReplaceAllTest {

    public static void main(String[] args) {
        String s = "123abc000";
        String abc = s.replaceAll("abc", "***");
        System.out.println(abc);
    }

}
