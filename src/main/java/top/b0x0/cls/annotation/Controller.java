package top.b0x0.cls.annotation;

import java.lang.annotation.*;

/**
 * @author: ThinkPad.
 * @Description: TODO()
 * @since:Created in 2019/8/25.
 * @Modified By:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String name();
    String[] values();
}
