package Other;

import Snake.Tools;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class quary {
    private int id;
    private String name;
    private int score;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        //获取数据库的连接
        String url = "jdbc:mysql://localhost:3306/snakegame";
        Connection connection = DriverManager.getConnection(url, "root", "root");

        //3.获取操作数据库的对象
        Statement statement = connection.createStatement();

        String sql = "select * from player;";
        ResultSet resultSet = statement.executeQuery(sql);
        Tools tools = new Tools();
        //遍历结果集
        DefaultTableModel model = new DefaultTableModel();//创建一个表格模型
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int score = resultSet.getInt("score");
            System.out.println("玩家" + "id" + ":" + name + "分数" + score);
            //把以上数据添加到表格模型的每一行中



        }

        connection.close();

    }
}

