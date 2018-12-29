package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.Random;

/*
该页面主要为定义贪吃蛇和游戏方框页面

 */


public class Snake extends JPanel implements KeyListener,ActionListener{

    // 定义七个图片变量，代表七张图片
    ImageIcon up = new ImageIcon("./pic/up.png"); // 向上的蛇头
    ImageIcon down = new ImageIcon("./pic/down.png"); // 向下的蛇头
    ImageIcon left = new ImageIcon("./pic/left.png"); // 向左的蛇头
    ImageIcon right = new ImageIcon("./pic/right.png"); // 向右的蛇头
    ImageIcon food = new ImageIcon("./pic/food.png");  // 食物
    ImageIcon body = new ImageIcon("./pic/body.png");  // 蛇的身体
    ImageIcon title = new ImageIcon("./pic/title.jpg"); // 游戏界面的主题
    ImageIcon Snakebg = new ImageIcon("./pic/Snakebg.png");

    ImageIcon up2 = new ImageIcon("./pic/up2.png"); // 向上的蛇头
    ImageIcon down2 = new ImageIcon("./pic/down2.png"); // 向下的蛇头
    ImageIcon left2 = new ImageIcon("./pic/left2.png"); // 向左的蛇头
    ImageIcon right2 = new ImageIcon("./pic/right2.png"); // 向右的蛇头
//    ImageIcon food = new ImageIcon("./pic/food.png");  // 食物
    ImageIcon body2 = new ImageIcon("./pic/body2.png");  // 蛇的身体
    private String Name1;
    private String Name2;
    //private static int LevelNumber;

    // 蛇的每一部分
    int[] firstSnakex = new int [750];//定义第一条蛇
    int[] firstSnakey = new int [750];
    int[] secondSnakex = new int [750];//定义第二条蛇
    int[] secondSnakey = new int [750];

    // 随机生成食物
    Random rand = new Random();
    int foodx = rand.nextInt(32)*25; //此处的数值根据自己设计的游戏界面的大小来确定
    int foody = rand.nextInt(22)*25+55;
    int foodx2 = rand.nextInt(32)*25; //此处的数值根据自己设计的游戏界面的大小来确定
    int foody2 = rand.nextInt(22)*25+55;

    // 设置游戏的默认属性
    // 设置游戏的默认属性
    int len = 3;
    int len2 = 3;
    int score = 0;
    int score2 = 0;
  //  int level = 100;
    String direction = "D"; // U上 D下 L左 R右
    String direction2 = "L";

    boolean isStarted = false; // 判断游戏是否开始
    boolean isFailed = false; // 判断游戏是否结束

 //   int ChooseLevel = SnakeWindow().ChooseLevel;
        Tools tools = new Tools();//新建一个工具类
//       // tools.setLevel(2);
//
//        int Level = tools.getLevel();

        int getLevelNumber = tools.getLevel();
        Timer timer = new Timer(getLevelNumber,this); // 每100毫秒调用一次ActionPerforme

//        String User1 = tools.getUser1();
//        String User2 = tools.getUser2();





    //获取玩家昵称


    public Snake(String Name1,String Name2) { // 建造画布的构造函数  1
        this.setFocusable(true);  // 获取焦点
        this.addKeyListener(this); // 监听键盘事件
        this.Name1 = Name1;
        this.Name2 = Name2;

        setup();
        timer.start();
    }
    
    public void paint(Graphics g) { // Graphics 画笔
        this.setBackground(Color.CYAN); // 设置画布背景颜色
        title.paintIcon(this, g, 0, 0);// 放置主题图片
        Snakebg.paintIcon(this, g, 0, 54);// 放置游戏背景
       // g.fillRect(0, 64, 850, 647); // 用画笔设置游戏方框
        // 画1蛇头（注意判断蛇头的方向）
        if (direction.equals("R"))
            right.paintIcon(this, g, firstSnakex[0], firstSnakey[0]);
        else if (direction.equals("L"))
            left.paintIcon(this, g, firstSnakex[0], firstSnakey[0]);
        else if (direction.equals("U"))
            up.paintIcon(this, g, firstSnakex[0], firstSnakey[0]);
        else if (direction.equals("D"))
            down.paintIcon(this, g, firstSnakex[0], firstSnakey[0]);

        if (direction2.equals("R"))
            right2.paintIcon(this, g, secondSnakex[0], secondSnakey[0]);
        else if (direction2.equals("L"))
            left2.paintIcon(this, g, secondSnakex[0], secondSnakey[0]);
        else if (direction2.equals("U"))
            up2.paintIcon(this, g, secondSnakex[0], secondSnakey[0]);
        else if (direction2.equals("D"))
            down2.paintIcon(this, g, secondSnakex[0],secondSnakey[0]);
        // 画蛇的身体
        for(int i = 1; i < len; i ++)
            body.paintIcon(this, g, firstSnakex[i],  firstSnakey[i]);
        for(int i = 1; i < len2; i ++)
            body2.paintIcon(this, g, secondSnakex[i], secondSnakey[i]);

        // 判断如果游戏没开始显示。。。
        if (!isStarted){
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑",Font.BOLD, 30));
            g.drawString("按下空格键  开始 / 暂停", 230, 300);
        }

        // 判断如果游戏结束显示。。。
        if (isFailed){
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑",Font.BOLD, 30));
            g.drawString("游戏结束！ 按下空格键继续", 230, 300);
        }

        // 显示食物
        food.paintIcon(this, g, foodx, foody);
        food.paintIcon(this,g,foodx2,foody2);
        //定位
//        food.paintIcon(this, g, 0, 0);
//        food.paintIcon(this, g, 0, 600);
//        food.paintIcon(this, g, 800, 55);
//        food.paintIcon(this, g, 800, 0);
//        food.paintIcon(this, g, 800, 600);
//        food.paintIcon(this, g, 0, 55);
//        food.paintIcon(this, g, 800, 600);


      //  JTextField jTextField1 = new Trans().getjTextField();
       // JTextField jTextField2 = new Trans().getjTextField2();
       // System.out.println(jTextField1.getText() + ":" + jTextField2.getText());
        // 设置分数和蛇的长度
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑",Font.PLAIN,15));
        g.drawString( Name1+"分数 : "+score, 550, 40);
        g.drawString( Name2+"分数 : "+score2, 445, 40);
        String LevelString = null;
        if(getLevelNumber == 50){
            LevelString = "较难";
        }else if(getLevelNumber == 100){
            LevelString = "一般";
        }else if(getLevelNumber == 150) {
            LevelString = "简单";
        }else{
            LevelString ="默认";
        }
        g.drawString( "难度 : "+LevelString, 680, 40);
      
    }

    public void setup() { // 游戏初始化
        System.out.println("Snake Score1="+score+"  Snake Score2="+score2);
        System.out.println("Snake this.Name1 this.Name2"+this.Name1 + this.Name2);

        System.out.println("setup== true玩家一：" +tools.getUser1()+"得分:"+tools.getScore1());
        System.out.println("setup== true玩家二：" +tools.getUser2()+"得分:"+tools.getScore2());

        isStarted = false;
        isFailed = false;
        len = 3;
        len2 = 3;
        score = 0;
        score2 = 0;
       // int Level = tools.getLevel();

        firstSnakex[0] = 600;firstSnakex[1] = 625; firstSnakex[2] = 650;
        firstSnakey[0] = 105;firstSnakey[1] = 105; firstSnakey[2] = 105;
        secondSnakex[0] =100;secondSnakex[1]=125;secondSnakex[2]=150;
        secondSnakey[0] =555;secondSnakey[1]=555;secondSnakey[2]=555;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        //实现键盘响应
        int KeyCode = e.getKeyCode();
        if (KeyCode == KeyEvent.VK_SPACE){ // 敲击空格现实/消除提示信息
            if (isFailed){
               System.out.println("Keypressed Score1 "+score +"Score2 "+score2+"Name1 "+this.Name1+"Name2 "+this.Name2);
                try {
                    new update(this.Name1,this.Name2,this.score,this.score2);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                System.out.println("isFailed == true玩家一：isFailed" +tools.getUser1()+"得分:"+tools.getScore1());
                System.out.println("isFailed == true玩家二：isFailed" +tools.getUser2()+"得分:"+tools.getScore2());


//                isStarted = false;  // 可以将这两行放入setup中
//                isFailed = false;
                setup();
            }else
                isStarted = !isStarted;
        }
        else if (KeyCode ==  KeyEvent.VK_W)
            direction2 = "U";
        else if (KeyCode ==  KeyEvent.VK_S)
            direction2 = "D";
        else if (KeyCode ==  KeyEvent.VK_D )
            direction2 = "R";
        else if (KeyCode ==  KeyEvent.VK_A)
            direction2 = "L";
        //舍弃direction等于反方向功能
        else if (KeyCode ==  KeyEvent.VK_UP )
            direction = "U";
        else if (KeyCode ==  KeyEvent.VK_DOWN )
            direction = "D";
        else if (KeyCode ==  KeyEvent.VK_RIGHT)
            direction = "R";
        else if (KeyCode ==  KeyEvent.VK_LEFT)
            direction = "L";


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 1. 再定义一个闹钟
        timer.start();
     //   System.out.println("ActionPerformed LevelNumber"+LevelNumber);
      //  System.out.println("ActionPerformed a"+getLevelNumber);
        // 2. 移动数据
        if (isStarted && !isFailed){
            // 移动身体
            for (int i = len; i>0; i--){
                firstSnakex[i] = firstSnakex[i-1];
                firstSnakey[i] =  firstSnakey[i-1];
            }
            for (int i = len2; i>0; i--){
               secondSnakex[i] = secondSnakex[i-1];
                secondSnakey[i] =  secondSnakey[i-1];
            }
            // 移动头
            if (direction.equals("R")){
                firstSnakex[0] =  firstSnakex[0] + 25;
                if( firstSnakex[0] > 800)  firstSnakex[0] = 0;
            }else if (direction.equals("L")){
                firstSnakex[0] =  firstSnakex[0] - 25;
                if( firstSnakex[0] < 0)  firstSnakex[0] = 800;
            }else if (direction.equals("U")){
                firstSnakey[0] =  firstSnakey[0] - 25;
                if ( firstSnakey[0] < 55)  firstSnakey[0] = 605;
            }else if (direction.equals("D")){
                firstSnakey[0] =  firstSnakey[0] + 25;
                if ( firstSnakey[0] > 605)  firstSnakey[0] = 55;
            }
            if (direction2.equals("R")){
                secondSnakex[0] =  secondSnakex[0] + 25;
                if(secondSnakex[0] > 800)  secondSnakex[0] = 0;
            }else if (direction2.equals("L")){
               secondSnakex[0] =  secondSnakex[0] - 25;
                if( secondSnakex[0] < 0)  secondSnakex[0] = 800;
            }else if (direction2.equals("U")){
                secondSnakey[0] =  secondSnakey[0] - 25;
                if ( secondSnakey[0] < 55)  secondSnakey[0] = 605;
            }else if (direction2.equals("D")){
               secondSnakey[0] =  secondSnakey[0] + 25;
                if ( secondSnakey[0] > 605)  secondSnakey[0] = 55;
            }

            if ( firstSnakex[0] == foodx &&  firstSnakey[0] == foody ){  // 吃食物
                len ++;
                score ++;
                foodx = rand.nextInt(32)*25;
                foody = rand.nextInt(22)*25+55;

            }
            if ( firstSnakex[0] == foodx2 &&  firstSnakey[0] == foody2 ){  // 吃食物
                len ++;
                score ++;

                foodx2 = rand.nextInt(32)*25;
                foody2 = rand.nextInt(22)*25+55;
            }

            if ( secondSnakex[0] == foodx &&  secondSnakey[0] == foody){  // 吃食物
                len2 ++;
                score2 ++;
                foodx = rand.nextInt(32)*25;
                foody = rand.nextInt(22)*25+55;

            }
            if ( secondSnakex[0] == foodx2 &&  secondSnakey[0] == foody2){  // 吃食物
                len2 ++;
                score2 ++;
                foodx2 = rand.nextInt(32)*25;
                foody2 = rand.nextInt(22)*25+55;
            }

            for (int i = 1; i < len; i ++){  // 如果蛇头碰到自己的身体游戏结束
//                if ( firstSnakex[0] ==  firstSnakex[i] &&  firstSnakey[0] ==  firstSnakey[i]){
//                    isFailed = true;
//                }
//                if ( secondSnakex[0] ==  secondSnakex[i] &&  secondSnakey[0] ==  secondSnakey[i]){
//                    isFailed = true;
//                }
                if ( secondSnakex[0] ==  firstSnakex[i] &&  secondSnakey[0] ==  firstSnakey[i]){
                    isFailed = true;
                }
                if(secondSnakex[0] == firstSnakex[0] && secondSnakey[0] ==firstSnakey[0]){
                    isFailed = true;
                }
                if ( firstSnakex[0] ==  secondSnakex[i] &&  firstSnakey[0] ==  secondSnakey[i]){
                    isFailed = true;
                }
            }

            for (int i = 1; i < len2; i ++){  // 如果蛇头碰到自己的身体游戏结束
//                if ( firstSnakex[0] ==  firstSnakex[i] &&  firstSnakey[0] ==  firstSnakey[i]){
//                    isFailed = true;
//                }
//                if ( secondSnakex[0] ==  secondSnakex[i] &&  secondSnakey[0] ==  secondSnakey[i]){
//                    isFailed = true;
//                }
                if ( secondSnakex[0] ==  firstSnakex[i] &&  secondSnakey[0] ==  firstSnakey[i]){
                    isFailed = true;
                }
                if(secondSnakex[0] == firstSnakex[0] && secondSnakey[0] ==firstSnakey[0]){
                    isFailed = true;
                }
                if ( firstSnakex[0] ==  secondSnakex[i] &&  firstSnakey[0] ==  secondSnakey[i]){
                    isFailed = true;
                }
            }

        }


        // 3. repaint（）
        repaint();

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}