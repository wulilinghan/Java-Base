package basic;

import java.util.Arrays;

/**
 * @program: java-base->ZhiAndYinYong
 * @description:
 * @author: G_Y
 * @since: 2019-09-18 16:23
 **/
public class ZhiAndYinYong {
//    public static void main(String[] args) {
//        int i = 1;
//        String str = new String("hello");
////        String str = "hello";
//        int[] arr = {1, 2, 3};
//        Arrb arrb = new Arrb();
//        change(i, str, arr, arrb.a);
//        System.out.println("i=" + i);                       //1
//        System.out.println("str=" + str);                   //hello
//        System.out.println("arr=" + Arrays.toString(arr));  //2,2,3
//        System.out.println(arrb.a);                         //1
//        change1(i, str, arr, arrb);
//        System.out.println(arrb.a);                         //2
//    }
//
//    public static void change(int i, String arr, int[] a, int ar) {
//        i += 1;
//        arr += " world";
//        a[0] += 1;
//        ar += 1;
//    }
//
//    public static void change1(int i, String arr, int[] a, Arrb ar) {
//        i += 1;
//        arr += "world";
//        a[0] += 1;
//        ar.a += 1;
//    }


    public static void main(String[] args) {

        // 1  -- 传递的是对象
//        UserObj userObj = new UserObj();
//        userObj.setId(1);
//        f1(userObj);
//        System.out.println(userObj);  // 2

        // 2  -- 传递的是1
//        int i = 1;
//        f2(i);
//        System.out.println(i); // 1

        // 3 -- 传递的是1
//        Integer integer = new Integer(1);
//        f3(1);
//        System.out.println(integer); // 1

        // 4 -- 传递的是"1"
//        String str = new String("1");
//        f4(str);
//        System.out.println(str); // 1
    }

    public static void f1(UserObj user) {
        user.setId(2);
    }

    public static void f2(int i) {
        i = 2;
    }

    public static void f3(Integer i) {
        i = 2;
    }

    public static void f4(String str) {
        str = "2";
    }

}

class UserObj {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserObj{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }

}





