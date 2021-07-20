package proxystatic.example;

/**
 * Created By gao_e on 2020/3/16 14:53
 */
public class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println("do real something");
    }
}
