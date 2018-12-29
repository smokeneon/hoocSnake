package Snake;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class update {

         update(String User1,String User2,int Score1,int Score2) throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("update---------------------------------------------------");
            //获取数据库的连接
            String url = "jdbc:mysql://localhost:3306/snakegame";
            Connection connection = DriverManager.getConnection(url, "root", "root");
          //  Statement statement = connection.createStatement();
            Tools tools = new Tools();
//            String User1 = tools.getUser1();
//            String User2 = tools.getUser2();
//            int Score1 = tools.getScore1();
//            int Score2 = tools.getScore2();

             Date d = new Date();//将时间戳添加至数据库
             String time = null;
             DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             time = sdf.format(d);
             System.out.println(time);

             String sql = "insert into player VALUES(null,?,?,?)";
            String sql2 = "insert into player VALUES(null,?,?,?)";
            System.out.println("Update"+User1+"  "+User2+"  "+Score1+"  "+Score2);
            PreparedStatement statement1 = connection.prepareStatement(sql);
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement1.setString(1,User1);//第1个问好替换为User
            statement1.setInt(2, Score1);
             statement1.setString(3, time);
            statement2.setString(1,User2);
            statement2.setInt(2,Score2);
             statement2.setString(3,time);
//            int resule = statement1.executeUpdate(sql);
//            int resule2 = statement2.executeUpdate(sql2);
////            int resule = statement.executeUpdate(sql);
////            int resule2 = statement.executeUpdate(sql2);
            int rs = statement1.executeUpdate();
            int rs2= statement2.executeUpdate();


            System.out.println("update---------------------------------------------------"+rs+rs2);

            statement1.close();
            statement2.close();
            connection.close();
        }

}
