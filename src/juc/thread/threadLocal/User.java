package juc.thread.threadLocal;

/**
 * @author TANG
 * @since 2021-08-02
 * @since JDK1.8
 */
public class User {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
