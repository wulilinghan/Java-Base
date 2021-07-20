package effective.object;

public class UseStaticMethodCreateObject {

    public static void main(String[] args) {
        System.out.println(valueOf(false));
    }

    public static Boolean valueOf(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }

}
