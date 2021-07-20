package proxystatic.example;

/**
 * Created By gao_e on 2020/3/16 14:55
 */
public class Client {
    public static void main(String[] args) {
        Subject subject = SubjectStaticFactory.getInstance();
        subject.doSomething();
    }
}
