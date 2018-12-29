package Snake;
/*
这是一个工具类
 */
public class Tools {
    private static int level ;
    private String User1 = null;
    private String User2 = null;
    private static int score1 = 0;
    private static int score2 = 0;
    public static int getScore2() {
        return score2;
    }

    public static void setScore2(int score2) {
        Tools.score2 = score2;
    }


    public static int getScore1() {
        return score1;
    }

    public static void setScore1(int score) {
        Tools.score1 = score;
    }


    public int getLevel() {


        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUser1() {
        return User1;
    }

    public void setUser1(String user1) {
        this.User1 = user1;
    }

    public String getUser2() {
        return User2;
    }

    public void setUser2(String user2) {
        this.User2 = user2;
    }
}
