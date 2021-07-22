package thread.ThreadLocal.code39;

/**
 * @author ManJiis
 * @since 2020/9/14 18:45
 * @Description: // TODO
 **/
public class Client {

    public static void main(String[] args) {

        UserMapper userMapper = new UserMapper();

//        PageHelper.startPage(1, 10);
//        userMapper.selectByCondition("gaoyue");

        for (int i = 1; i <= 10; i++) {
            int i1 = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Thread thread = Thread.currentThread();
                    System.out.println(thread + "------" + i1);
                    PageHelper.startPage(i1, 10);
                    userMapper.selectByCondition("gaoyue");
                }
            }).start();
        }

    }

}

class UserMapper {
    public void selectByCondition(String username) {
        Thread thread = Thread.currentThread();
        System.out.println(thread);
        // 模拟 ，比如根据api 动态生成的 原sql如下
        String sql = "select * from user where username='" + username + "'";
        System.out.println("原sql：" + sql + "---线程" + thread);
        // sql执行拦截器
        sql = SqlInterceptor.optSqlBeforeExecut(sql);
        // 执行sql
        System.out.println("执行sql：" + sql + "---线程" + thread);
    }
}

// sql执行拦截器
class SqlInterceptor {
    public static String optSqlBeforeExecut(String sql) {
        int pageNum = 0, pageSize = 0;
        Page page = PageHelper.get();
        pageNum = page.getPageNum();
        pageSize = page.getPageSize();
        sql = sql + " Limit " + ((pageNum - 1) * pageSize) + ", " + pageSize;
        return sql;
    }
}
