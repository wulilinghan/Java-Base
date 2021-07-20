package designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class BeautifulGirl {
    //我的亲人
    private List<Concerner> concerners = new ArrayList<>();
    //我是否生病了
    private boolean isFallIll = false;
    //添加我的亲人
    public void addRelatives(Concerner concerner) {
        this.concerners.add(concerner);
    }
    public boolean isFallIll() {
        return isFallIll;
    }
    //生病了
    public void setFallIll(Boolean isFallIll) {
        this.isFallIll = isFallIll;
        if(isFallIll)
            this.notifyAllRelatives();            
    }
    //告知我的家人
    private void notifyAllRelatives() {
        for (Concerner observer : concerners) {
            observer.care();
        }
    }
}
