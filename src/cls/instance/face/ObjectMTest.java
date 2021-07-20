package cls.instance.face;

public class ObjectMTest {
    public static void main(String[] args) {
        ABean a = new ABean(100);
        BBean b = new BBean(100);
        ABean a2 = new ABean(100);
        System.out.println(a.equals(b));
        System.out.println(a == a2);
        System.out.println(a.equals(a2));
    }
}
class ABean {
    int a;

    public ABean(int a) {
        super();
        this.a = a;
    }

    public ABean() {
        super();
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    public int hashCode() {
        return a;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == this.hashCode();
    }
}
class BBean {
    public BBean(int b) {
        super();
        this.b = b;
    }

    public BBean() {
        super();
    }

    int b;

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public int hashCode() {
        return b;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == this.hashCode();
    }
}
