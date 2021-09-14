package top.b0x0.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author ManJiis
 * @since 2021-08-16
 */
public class TestJdbcConnection {

    public static final String URL = "top.b0x0.jdbc:mysql://localhost:3306/test";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    public static void main(String[] args) throws Exception {
        // 1.注册驱动程序
//        Class.forName("com.mysql.top.b0x0.jdbc.Driver");
        // 新版驱动
//        Class.forName("com.mysql.cj.top.b0x0.jdbc.Driver");

        // 2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        // 3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        String execSql = "SELECT id,username FROM sys_user ";
        ResultSet rs = stmt.executeQuery(execSql);
        // 如果有数据，rs.next()返回true
        while (rs.next()) {
            // mybatis 通过JavaBean的get set 方法赋予返回值
            System.out.println(rs.getString("username") + " 员工ID：" + rs.getString("id"));
        }
    }
}