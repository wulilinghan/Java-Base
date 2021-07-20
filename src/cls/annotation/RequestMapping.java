package cls.annotation;

import java.lang.annotation.*;

/**
 * Created By gao_e on 2020/4/7 21:15
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String[] value() default {};
}
