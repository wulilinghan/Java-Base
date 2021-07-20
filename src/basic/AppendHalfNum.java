package basic;

public class AppendHalfNum {
    public static void main(String[] args) {
        int[] arr = {1,2,1,2,3};
        System.out.println("arr.length = " + arr.length);
        System.out.println(findHalfNum(arr));
    }

    public static Integer findHalfNum(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        Integer num = null;
        num = arr[0];
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == num) {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                num = arr[i];
                count = 0;
            }
        }
//        if (arr.length == 1) {
//            return arr[0];
//        }
        if (count > 0 || (count == 0 && arr.length % 2 == 1)) {
            return num;
        }
        return null;
    }
}
