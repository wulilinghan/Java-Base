package cls.annotation;

import java.lang.annotation.*;

/**
 * @Author: ThinkPad.
 * @Description: TODO()
 * @Date:Created in 2019/8/25.
 * @Modified By:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String name();
    String[] values();
}
