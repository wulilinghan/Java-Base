package top.b0x0.juc.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 如果使用原子性对象引用，在多线程情况下进行对象的更新可以确保一致性
 *
 * @author ManJiis
 * @since 2019-08-21 13:42
 **/
public class AtomicReferenceLearn {
    static Person person;
    static AtomicReference<Person> atomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        person = new Person("Tom", 18);
        atomicReference = new AtomicReference<Person>(person);
        System.out.println("Atomic Person is " + atomicReference.get().toString());
        // 在多线程情况下进行对象的更新可以确保一致性
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "[name: " + this.name + ", age: " + this.age + "]";
    }
}
