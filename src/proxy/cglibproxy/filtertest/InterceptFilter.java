package proxy.cglibproxy.filtertest;

import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;
/**
 * Created By gao_e on 2020/3/21 11:23
 */
public class InterceptFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        if (method.isAnnotationPresent(CatchAble.class))
            return 1;
        if (method.isAnnotationPresent(Transactional.class))
            return 2;
        return 0;
    }
}
