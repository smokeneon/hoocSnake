package Other;
import Snake.Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class insertDB {

    public static void main(String[] args) {
        Connection conn = getConnection("root", "root");    // 获取数据库连接
        insert(conn);    //方法名调用数据库连接
        releaseConnection(conn);// 释放数据库连接
    }
    //插入数据
    public static void insert(Connection conn) {
        try {
            Tools tools = new Tools();
            String name1 = tools.getUser1();
            String name2 = tools.getUser2();
            int score1 = tools.getScore1();
            int score2 = tools.getScore2();
            System.out.println("insert name1 name2 score1 score2"+ name1 + score1 + name2 + score2);

            String sql = "insert into player values (NULL, name1, score1)"; // 插入数据的sql语句
            String sql2 = "insert into player values (NULL, name2, score2)";
            Statement stmt1 =conn.createStatement();    // 创建用于执行静态sql语句的Statement对象
            int count = stmt1.executeUpdate(sql);  // 执行插入操作的sql语句，并返回插入数据的个数
            int count2 = stmt1.executeUpdate(sql2);
            int addcount = count + count2;
            System.out.println("向player中插入了 " + addcount + " 条数据"); //输出插入操作的处理结果
            conn.close();   //关闭数据库连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //数据库连接
    public static Connection getConnection(String user, String pass) {
        Connection conn = null;//声明连接对象
        String driver = "com.mysql.cj.jdbc.Driver";// 驱动程序类名
        String url = "jdbc:mysql://localhost:3306/snakegame?" // 数据库URL
                + "useUnicode=true&characterEncoding=UTF8";// 防止乱码
        try {
            Class.forName(driver);// 注册(加载)驱动程序
            conn = DriverManager.getConnection(url, user, pass);// 获取数据库连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    //释放数据库连接
    public static void releaseConnection(Connection conn) {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}