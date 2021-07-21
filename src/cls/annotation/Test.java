package cls.annotation;

import java.lang.annotation.Annotation;

/**
 * @program: java-base->Test
 * @description:
 * @author: G_Y
 * @since: 2019-08-25 18:52
 **/
public class Test {

    public static void main(String[] args) {
        // 判断类对象上是否有某个注解修饰
        boolean annotationPresent = TestController.class.isAnnotationPresent(Controller.class);
        System.out.println(annotationPresent);
    }
}
