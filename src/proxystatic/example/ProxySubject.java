package proxystatic.example;

/**
 * Created By gao_e on 2020/3/16 14:53
 */
public class ProxySubject implements Subject {
    public ProxySubject(Subject realSubject) {
        this.realSubject = realSubject;
    }
    private Subject realSubject;
    @Override
    public void doSomething() {
        this.doOtherThing();
        realSubject.doSomething();
        this.doOtherThing();
    }
    private void doOtherThing() {
        System.out.println("doOtherThing");
    }
}
