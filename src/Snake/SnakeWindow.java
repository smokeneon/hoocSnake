package Snake;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/*
该文件描述贪吃蛇窗口页面，包括菜单栏，关于和排名界面
 */
public class SnakeWindow extends JDialog{
		//int LevelNumber;
	//贪吃蛇页面
	public SnakeWindow(String UserName1,String UserName2) {
			//this.LevelNumber= LevelNumeber;
		 JFrame frame = new JFrame();
	        frame.setBounds(10,10,840,690);
	        frame.setResizable(false);

	        JMenuBar jMenuBar = new JMenuBar();
	        JMenu level = new JMenu("功能");
	        JMenu rank = new JMenu("排名");
	        JMenu about = new JMenu("关于");

			JMenuItem rankItem = new JMenuItem("显示排名");
			JMenuItem aboutItem = new JMenuItem("关于信息");
			JMenuItem introduction= new JMenuItem("游戏介绍");
			JMenuItem exitLogin = new JMenuItem("注销登陆");
	        JMenuItem quit = new JMenuItem("退出");

	        jMenuBar.add(level);
	        jMenuBar.add(rank);
	        jMenuBar.add(about);
	        rank.add(rankItem);
	        about.add(introduction);
	        about.add(aboutItem);
			level.add(exitLogin);
	        level.addSeparator();
			Tools tools = new Tools();


	        //实现关于弹出窗口的监听
			exitLogin.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					new showSnakeLogin.loginSnake();
				}
			});
			aboutItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new aboutWindow();
				}
			});
			introduction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new introductionWindow();
			}
			});


			rankItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						new rankWindow();
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			});

	        //实现菜单栏退出功能
	        level.add(quit);
	        quit.addActionListener(new ActionListener() {

			  @Override
			  public void actionPerformed(ActionEvent e) {
			  System.exit(0);

					}

	         	});

	        frame.setJMenuBar(jMenuBar);
//	      sheet = new JMenuItem("");

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        String u1 = UserName1;
	        String u2 = UserName2;

	        Snake panel = new Snake(u1,u2);
	        frame.add(panel);
	        frame.setVisible(true);

	}
	//关于页面
	public class aboutWindow extends JFrame{
		 public aboutWindow() {
			 this.setSize(400,180);
			 this.setLocation(230,200);
			 this.setTitle("关于");
			 this.setResizable(false);
			 this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			 
			 JPanel jPanel = new JPanel();
			 JLabel jLabel1 = new JLabel("                                   ");
			 JLabel jLabel4 = new JLabel("                                   ");
			 JLabel jLabel = new JLabel("hooc开源项目，可供学习，禁止用于商业用途");
			 JLabel jLabel2 = new JLabel("官方网站：www.hooc.top");
			 JLabel jLabel3 = new JLabel("作者微信：Air_mars)");
			 jPanel.add(jLabel1);
			 jPanel.add(jLabel4);
			 jPanel.add(jLabel);
			 jPanel.add(jLabel2);
			 jPanel.add(jLabel3);
			 
			 this.add(jPanel);
			 this.setVisible(true);
		 }
		 
		

	}
	//游戏介绍页面

	public class introductionWindow extends JFrame{
		public introductionWindow() {
			this.setSize(400,200);
			this.setLocation(230,200);
			this.setTitle("hooc双人贪吃蛇游戏介绍");
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JPanel jPanel = new JPanel();
			JLabel jLabel1 = new JLabel("                                              ");
			JLabel jLabel2 = new JLabel("          红蛇控制按钮 A左 S下 D右 W上           ");
			jLabel2.setForeground(Color.pink);
			JLabel jLabel3 = new JLabel("          蓝蛇控制按钮 小键盘上下左右             ");
			jLabel3.setForeground(Color.BLUE);
			JLabel jLabel4 = new JLabel("          判定死亡条件：两蛇相撞                  ");
			JLabel jLabel5 = new JLabel("          以下不作为死亡条件                      ");
			JLabel jLabel6 = new JLabel("          1.超出边界 2.撞到自身身体 3.逆向行走      ");
			jLabel6.setForeground(Color.orange);
			jPanel.add(jLabel1);
			jPanel.add(jLabel2);
			jPanel.add(jLabel3);
			jPanel.add(jLabel4);
			jPanel.add(jLabel5);
			jPanel.add(jLabel6);

			this.add(jPanel);
			this.setVisible(true);
		}



	}
	//排名页面
	public class rankWindow extends JFrame{
		private int id;
		private String name;
		private int score;
		 public rankWindow() throws SQLException, ClassNotFoundException {
			//数据库连接查询部分
			 Class.forName("com.mysql.cj.jdbc.Driver");

			 //获取数据库的连接
			 String url = "jdbc:mysql://localhost:3306/snakegame";
			 Connection connection = DriverManager.getConnection(url,"root","root");

			 //3.获取操作数据库的对象
			 Statement statement = connection.createStatement();

			 String sql = "select * from player order by score desc;";
			 ResultSet resultSet = statement.executeQuery(sql);
			 Tools tools = new Tools();
			 //遍历结果集
			 DefaultTableModel model = new DefaultTableModel(){
			 	public boolean isCellEditable(int row,int column){
			 		return false;
				}
			 };//创建一个表格模型

			 model.setColumnIdentifiers(new Object[]{"昵称","分数","时间戳"});
			 while (resultSet.next()){

				 String name =  resultSet.getString("name");
				 int score = resultSet.getInt("score");
				 String time = resultSet.getString("time");
				 System.out.println("玩家"+"id"+":"+name+"分数"+score);
				 //把以上数据添加到表格模型的每一行中
				 model.addRow(new Object[]{name,score,time});
			 }





//			 this.setSize(200,500);
			 this.setLocation(50,100);
			 this.setTitle("排名");
			 this.setResizable(false);
			 this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			 JPanel jPanel = new JPanel();

			 JTable table = new JTable(model);
			 table.setRowHeight(30);
			 table.setPreferredScrollableViewportSize(new Dimension(350,350));//如果数据太多自动添加滚动条
			 table.setBackground(new Color(54,66,74));
			 table.setForeground(Color.white);

			 //设置表格内容居中显示
			 DefaultTableCellRenderer r = new DefaultTableCellRenderer();
			 r.setHorizontalAlignment(JLabel.CENTER);
			 table.setDefaultRenderer(Object.class,r);

			 JScrollPane scrollPane = new JScrollPane(table);
			 this.getContentPane().add(scrollPane,BorderLayout.CENTER);
			 this.pack();//自适应大小，不然会很小
			 connection.close();
			 this.setVisible(true);
		 }
	}

	}
	

