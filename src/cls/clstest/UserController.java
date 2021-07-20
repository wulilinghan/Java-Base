package cls.clstest;

import cls.annotation.Autowired;
import cls.annotation.Controller;
import cls.annotation.RequestMapping;

/**
 * Created By gao_e on 2020/4/7 21:17
 */
@Controller(name = "userController", values = {"aaa","bbb"})
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String f() {
        String s = userService.f3();
        return s;
    }

}
