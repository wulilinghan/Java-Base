package cls.annotation;

import java.lang.annotation.*;

/**
 * Created By gao_e on 2020/4/7 21:14
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
}
