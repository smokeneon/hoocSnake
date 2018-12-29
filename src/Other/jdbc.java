package Other;

import java.sql.*;
public class jdbc {
    /**
     * @author Lennox
     * @data 18/12/13
     *  */
    //查询用户密码
    public String FindUser(int id){
        String passd = null;
        String sql = "select * from snakegame where id=?";
        Connection con =getConnection();//获取连接
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(sql);//预处理
            pstmt.setInt(1, id);//给？赋值
            rs = pstmt.executeQuery();//执行SQL语句
            if(rs.next()){//遍历查找到与username匹配的password
                passd=rs.getString("name");//将查询到的password赋值给passd
            }else{
                passd=null;//未查询到为空
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs!=null)rs.close();
                if(pstmt!=null)pstmt.close();
                if(con!=null)con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return passd;//返回passd
    }
    //--------------------我是分隔符----略略略-------------------------------
    //注册新用户
    public void addUser(String username,String passd){
        Connection con = getConnection();
        PreparedStatement pstmt =null;
        String sql = "INSERT INTO User_info(username,password) VALUES(?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, passd);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(pstmt!=null)pstmt.close();
                if(con!=null)con.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //--------------------我是分隔符----略略略---------------------------
    //连接数据库
    public static Connection getConnection(){
        String driver ="com.mysql.jdbc.Driver";//数据库驱动
        String url ="jdbc:mysql://localhost:3306/snakegame";
        String user ="root";
        String password ="root";
        Connection connection =null;
        try {
            Class.forName(driver);//加载数据库驱动
            connection =DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;//返回连接
    }

    public static void main(String[] args) {
        jdbc.getConnection();
        jdbc j=new jdbc();
        String t=j.FindUser(1);
        System.err.print(t);

    }
}
