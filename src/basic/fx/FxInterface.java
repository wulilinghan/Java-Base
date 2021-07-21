package basic.fx;

/**
 * @author ManJiis
 * @since 2020/6/10 11:14
 * @Description: // TODO
 **/
public interface FxInterface<T> {

    <T> T get();

    void set(T t);

}
