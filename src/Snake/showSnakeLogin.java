package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
该页面主要为登陆页面
 */
public class showSnakeLogin extends JDialog {

    public showSnakeLogin(){
    }
    
   //登陆页面
  static class loginSnake extends JFrame{


        public loginSnake(){
            this.setSize(400,520);
//            this.setLocation(500,100);
            this.setTitle("hooc双人贪吃蛇登陆");
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel jPanel = new JPanel();
            JPanel jPanel1 = new JPanel(){
                public void paint(Graphics g) { // Graphics 画笔
                    ImageIcon bg = new ImageIcon("./pic/hoocSnakelogin.png");
                    ImageIcon head = new ImageIcon("./pic/head.png");

                    bg.paintIcon(this, g, 0, 0);// 放置主题图片

                }
            };
            jPanel.setLayout(null);
            jPanel1.setBounds(0,0,400,400);
            this.add(jPanel1);


            JLabel jLabel = new JLabel("玩家一昵称:");
            jLabel.setBounds(20,410,110,20);


            jLabel.setForeground(Color.white);
            // jLabel.setBounds(10,20,70,50);
            // jLabel.setFont(new Font("微软雅黑"));

            JLabel jLabel2 = new JLabel("玩家二昵称");
            jLabel2.setBounds(20,440,110,20);
            jLabel2.setForeground(Color.white);
            // jLabel2.setBounds(10,80,70,30);

            //JTextField jTextField = new Trans().getjTextField();
            Tools tools = new Tools();
            JTextField jTextField = new JTextField(10);
            jTextField.setBounds(110,410,100,20);

            // JTextField jTextField2 = new Trans().getjTextField2();
            JTextField jTextField2 = new JTextField(10);
            jTextField2.setBounds(110,440,100,20);

            JLabel jLabel3 = new JLabel("难度");
            jLabel3.setFont(new Font("微软雅黑",Font.BOLD,12));
            jLabel3.setBounds(230,400,30,50);
            jLabel3.setForeground(Color.WHITE);

            JComboBox jComboBox = new JComboBox<String>();
            jComboBox.addItem("简单");
            jComboBox.addItem("一般");
            jComboBox.addItem("较难");
            jComboBox.setBounds(225,440,35,15);
            jComboBox.setFont(new Font("微软雅黑",Font.BOLD,10));

            JButton jButton = new JButton("登  陆");
            jButton.setFont(new Font("微软雅黑",Font.BOLD,12));
            jButton.setBounds(280,410,80,50);



            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   String  UserName1 = jTextField.getText();
                   System.out.println("jTextField.getText ="+jTextField.getText());
                   String  UserName2 = jTextField2.getText();
                   tools.setUser1(UserName1);
                   tools.setUser2(UserName2);


                   String LevelString = (String) jComboBox.getSelectedItem();//获取下拉列表的字符串
                   System.out.println("ComboBox"+LevelString);
                    int Level = 100;

                    if(LevelString == "简单"){
                      //Level = 150;
                      tools.setLevel(150);

                    }else
                    if(LevelString == "一般"){
                      //  Level = 100;
                        tools.setLevel(100);
                    }else
                    if(LevelString == "较难"){
                      //  Level =50;
                        tools.setLevel(50);
                    }
                    System.out.println("1111111ShowSnakePanel.Level"+Level);


                    if(jTextField.getText().trim().equals("")||jTextField2.getText().trim().equals("")){
                        new inputName();

                    }else{
                        new SnakeWindow(UserName1,UserName2);//新建贪吃蛇窗口
                        dispose();//关闭该窗口
                    }




                }
            });

            jPanel.setBounds(0,400,400,100);
            jPanel.add(jLabel);
            jPanel.add(jTextField);
            jPanel.add(jLabel2);
            jPanel.add(jTextField2);
            jPanel.add(jButton);
            jPanel.add(jLabel3);
            jPanel.add(jComboBox);
            this.add(jPanel);
            jPanel.setVisible(true);
            this.setVisible(true);
            jPanel.setBackground(Color.black);

        }

        }
    //未输入昵称警告窗口
    public static class inputName extends JFrame{
        public inputName() {
            this.setSize(400,180);
            this.setLocation(2,250);
            this.setTitle("警告");
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel jPanel = new JPanel();
            JLabel jLabel1 = new JLabel("                                                                      ");
            JLabel jLabel4 = new JLabel("                                     警 告！                           ");
            JLabel jLabel = new JLabel("                                 玩家昵称不能为空                        ");
            JLabel jLabel2 = new JLabel("                                  感谢亲的配合                           ");
            JLabel jLabel3 = new JLabel("                                    ");
            jPanel.add(jLabel1);
            jPanel.add(jLabel4);
            jPanel.add(jLabel);
            jPanel.add(jLabel2);
            jPanel.add(jLabel3);

            this.add(jPanel);
            this.setVisible(true);
        }



    }

    //主函数
  public static void main(String[] args) {
      new loginSnake();//创建登陆页面

  }
}
