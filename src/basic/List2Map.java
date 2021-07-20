package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author G_Y
 * @Date 2020/8/6 10:53
 * @Description: // List 转 map操作
 **/
public class List2Map {
    public static void main(String[] args) {
//        List<User2> users = new ArrayList<>();
//        users.add(new User2(1, "张三"));
//        users.add(new User2(2, "李四"));
//
//        Map<Integer, User2> userMap = users.stream().
//                collect(Collectors.toMap(User2::getUserId, user2 -> user2));
//        System.out.println(userMap);
        // {
        // 1:{"userId":1,"userName":"张三"},
        // 2:{"userId":2,"userName":"李四"}
        // }



        // List 转 List
        List<User2> users2 = new ArrayList<>();
        users2.add(new User2(1, "张三"));
        users2.add(new User2(2, "李四"));


        List<User2DTO> collect =
                users2.stream().map(Converter::from).collect(Collectors.toList());

        collect =
                users2.stream().map(User2::from).collect(Collectors.toList());

        System.out.println(collect);

    }

}
class Converter{
    public static User2DTO from(User2 user2){
        if(user2==null)
            return  null;
        return new User2DTO(user2.getUserId(),user2.getName());
    }
}
class User2DTO{
    private Integer userId;
    private String userName;

    public User2DTO(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

class User2 {
    public User2(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }
    public User2DTO from(){
        return new User2DTO(this.getUserId(),this.getName());
    }

    private Integer userId;
    private String name;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}