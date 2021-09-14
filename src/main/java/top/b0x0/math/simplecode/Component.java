package top.b0x0.math.simplecode;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: java-base->Componet
 * @description:
 * @author: ManJiis
 * @since: 2019-08-28 15:15
 **/
public class Component {
    public static final Map<String, Handler> map = new HashMap<>(16);

    static {
        map.put("+", Handler.ADD);
        map.put("-", Handler.SUBTRACTION);
        map.put("*", Handler.MULT);
        map.put("/", Handler.DIVISION);
    }
}
