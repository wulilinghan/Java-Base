package designpattern.single;

import designpattern.single.s1.User;

/**
 * Created By gao_e on 2020/5/5 16:47
 */
public enum EService {
    E_SERVICE_SINGLE("single");
    private String name;
    private EService(String name){
        this.name = name;
    }
}
