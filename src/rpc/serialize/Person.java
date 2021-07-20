package rpc.serialize;

import java.io.Serializable;

/**
 * @author GY
 * @date 2018年10月6日
 * @说明:Hessian序列化跟Java序列化一样，序列化对象同样需要实现Serializable接口
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String name;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Person [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append("]");
        return builder.toString();
    }
    public Person(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    public Person() {
        super();
    }

}
