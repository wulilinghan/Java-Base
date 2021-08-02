package juc.thread.threadSafe;

import org.apache.commons.lang.builder.ToStringBuilder;

public class IntAndUserTheadTest {

    public static void main(String[] args) {
        User user = new User(1,null,"小明");
        System.out.println("init user = "+user.getName());
        new Thread(new Runnable() {
            @Override
            public void run() {
                user.setFriend(new User(2,"张三"));
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                User friend = user.getFriend();
                System.out.println("线程2-----Pre："+friend.getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                user.setId(200);
                friend = new User(3,"李四");
                while (true){
                    System.out.println("线程2："+user);
                    System.out.println("线程2："+friend.getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}

class User {
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;
    private User friend;

    public User(int id, User friend, String name) {
        this.id = id;
        this.friend = friend;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("friend", friend)
                .toString();
    }
}
