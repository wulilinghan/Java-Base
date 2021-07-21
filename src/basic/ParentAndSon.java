package basic;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: java-base->ParentAndSon
 * @description:
 * @author: ManJiis
 * @since: 2019-09-18 15:30
 **/
public class ParentAndSon {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Parent parent = new Son();
        parent.say();
        System.out.println(parent.getClass());
        System.out.println(parent.getClass() == Son.class);//万物皆对象 true
        System.out.println(parent.name);
    }
}

class Parent {
    String name = "parent";
    public void say() {
        System.out.println("Parent");
//        System.out.println(this.getClass());
//        this.f2();
    }
    public void f2() {
        System.out.println("Parent f2");
    }
}

class Son extends Parent {
    String name = "son";
    @Override
    public void say() {
        System.out.println("Son");
    }
//    @Override
//    public void f2() {
//        System.out.println("Son f2");
//    }
}