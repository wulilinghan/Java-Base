package basic.fx;

/**
 * @Author G_Y
 * @Date 2020/6/10 11:14
 * @Description: // TODO
 **/
public interface FxInterface<T> {

    <T> T get();

    void set(T t);

}
