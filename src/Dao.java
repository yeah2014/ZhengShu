
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Dao {
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/zhengshu";
    static String name = "root";
    static String password = "0000";
    static Connection conn = null;

    public Dao() {
    }

    public static Connection dao1() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, name, password);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "连接失败！");
            System.exit(0);
        }
        return conn;
    }

    // ��½ ���Գɹ�
/*    public static boolean login(String account, String password)
            throws SQLException {
        account = "'" + account + "'";
        password = "'" + password + "'";
        String sql = "select *from user_info where u_name=" + account
                + "and u_pass=" + password; // ����ִ�в�ѯ��sql���
        Statement statement = dao1().createStatement(); // ����һ�� Statement
        ResultSet b = statement.executeQuery(sql); // ִ�в�ѯ��sql��䣬���ҷ���һ��ResultSet��ѯ�����
        while (b.next()) // b.next()��ʾָ�벻������һ����ֻҪ��Ϊ�ռ���
        {
            System.out.println("��½�ɹ�");
            return true;
        }
        b.close(); // �ر�ResultSet ����
        statement.close(); // �ر�Statement ����
        System.out.println("��½ʧ��");
        return false;
    }*/

    public static boolean addUserInfo(Allzhengshu u) throws SQLException {
        String name = u.name;
        String ID = u.ID;
        String sql = "insert into zhengshu(name,ID,sourse) values(?,?,?)";
        PreparedStatement a;
        a = dao1().prepareStatement(sql);
        a.setString(1, name);
        a.setString(2, ID);
        a.setObject(3, u);
        int time = a.executeUpdate();
        JOptionPane.showMessageDialog(null,"添加成功！");
        System.out.println(time + "插入完成");
        a.close();
        a = null;name = null;ID = null;sql = null;
        return false;
    }

    public static boolean dropperson(String ID)
            throws SQLException {
        // fromwho = "'" + fromwho + "'";
        ID = "'" + ID + "'";
        String sql = "delete from zhengshu where ID="
                + ID; // ����һ���������ݿ��SQL��䣬���еģ�������Ըı�ı���
        Statement a;
        a = dao1().createStatement();
        a.execute(sql);
        System.out.println("删除成功");
        a.close(); // �ر�a ����
        a = null;
        sql = null;
        return true;

    }

    // ��ѯ�û������Ƿ��Ѿ������˴��˺� ���Գɹ�
/*    public static boolean queryAccount(String account) throws SQLException {
        account = "'" + account + "'";
        String sql = "select *from user_info where u_name=" + account;
        Statement statement = dao1().createStatement(); // ����һ�� Statement
        ResultSet b = statement.executeQuery(sql); // ִ�в�ѯ��sql��䣬���ҷ���һ��ResultSet��ѯ�����
        while (b.next()) // b.next()��ʾָ�벻������һ����ֻҪ��Ϊ�ռ���
        {
            return true;
        }
        b.close(); // �ر�ResultSet ����
        statement.close(); // �ر�Statement ����
        return false;
    }*/


    public static ResultSet findforname(String names) throws SQLException {
        String name = names;
        name = "'" + name + "'";
        String sql = "select *from zhengshu where name=" + name;
        Statement statement = dao1().createStatement(); // ����һ�� Statement
        ResultSet b = statement.executeQuery(sql); // ִ�в�ѯ��sql��䣬���ҷ���һ��ResultSet��ѯ�����

        return b;
    }

    public static Allzhengshu findforID(String IDS) throws SQLException {
        String ID = IDS;
        ID = "'" + ID + "'";
        String sql = "select *from zhengshu where ID=" + ID;
        Statement statement = dao1().createStatement();
        ResultSet b = statement.executeQuery(sql);
        try {
            while (b.next())
            {
                ObjectInputStream input = new ObjectInputStream(b.getBinaryStream(3));
                return (Allzhengshu) input.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            b.close();
            statement.close();
            b = null;
            statement = null;
            ID = null;
            sql = null;
        }
        return null;
    }

    public static int getcount() throws SQLException {
        String sql = "select count(*) from zhengshu";
        Statement statement = dao1().createStatement();
        ResultSet b = statement.executeQuery(sql);
        int count = 0;
        b.last();
            count = b.getInt(1);

        return count;
    }
    public static ResultSet Get(int begin,int nub) throws SQLException {
        String sql = "select *from zhengshu limit "+begin+","+nub;
        Statement statement = dao1().createStatement();
        ResultSet bb = statement.executeQuery(sql);
        sql = null;
        return bb;
    }

public static void main(String[] agrs) throws SQLException {



}

}
    /*public static boolean isperson(String account) throws SQLException {
        String account1 = "'" + account + "'";
        String sql = "select *from user_info where u_name=" + account1;
        Statement statement = dao1().createStatement(); // ����һ�� Statement
        ResultSet b = statement.executeQuery(sql); // ִ�в�ѯ��sql��䣬���ҷ���һ��ResultSet��ѯ�����
        if (b.next() == false)
            return false;
        else
            return true;
    }

    // ��ѯ�û������Ƿ��Ѿ������˴��˺� ���Գɹ�

    public static TrayIcon.MessageType personal(String account) throws SQLException {
        TrayIcon.MessageType message = new TrayIcon.MessageType();
        String account1 = "'" + account + "'";
        String sql = "select *from user_info where u_name=" + account1;
        Statement statement = dao1().createStatement(); // ����һ�� Statement
        ResultSet b = statement.executeQuery(sql); // ִ�в�ѯ��sql��䣬���ҷ���һ��ResultSet��ѯ�����
        while (b.next()) {
            message.Users.setId(b.getString(2));
            message.Users.setSex(b.getString(6));
            message.Users.setName(b.getString(8));
            message.Users.setSign(b.getString(7));
            message.Users.setHeadicon(b.getInt(9));
        }
        b.close();
        statement.close();
        return message;
    }
    //ĳ�û������к���
    public static ArrayList<String> whoesfriends(String account)
            throws SQLException {
        ArrayList<String> al = new ArrayList<String>();
        String sql2 = "select *from " + account + "friendslist";
        Statement statement2 = dao1().createStatement(); // ����һ�� Statement
        ResultSet b2 = statement2.executeQuery(sql2);
        while (b2.next()) {
            al.add(b2.getString(1));
        }
        b2.close();
        statement2.close();
        return al;
    }
    //��¼���Ҫ�ĸ�����Ϣ�����ڹ�������б�
    public static MessageType personInformation(String account)
            throws SQLException {
        MessageType message = new MessageType();
        String account1 = "'" + account + "'";
        String sql = "select *from user_info where u_name=" + account1;
        Statement statement = dao1().createStatement(); // ����һ�� Statement
        ResultSet b = statement.executeQuery(sql); // ִ�в�ѯ��sql��䣬���ҷ���һ��ResultSet��ѯ�����
        while (b.next()) // b.next()��ʾָ�벻������һ����ֻҪ��Ϊ�ռ���
        {
            message.Userdata.setId(b.getString(2));
            message.Userdata.setName(b.getString(8));
            message.Userdata.setSex(b.getString(6));
            message.Userdata.setSign(b.getString(7));
            message.Userdata.setHeadicon(b.getInt(9));
        }
        String sql2 = "select *from " + account + "friendslist";
        Statement statement2 = dao1().createStatement(); // ����һ�� Statement
        ResultSet b2 = statement2.executeQuery(sql2);
        String sql3;
        Statement statement3;// ����һ�� Statement
        ResultSet b3;// ִ�в�ѯ��sql��䣬���ҷ���һ��ResultSet��ѯ�����
        ArrayList<Friends> friends = new ArrayList<Friends>();
        while (b2.next()) {
            sql3 = "select *from user_info where u_name=" + b2.getString(1);
            statement3 = dao1().createStatement();
            b3 = statement3.executeQuery(sql3);
            while (b3.next()) // b.next()��ʾָ�벻������һ����ֻҪ��Ϊ�ռ���
            {
                Friends f = new Friends();
                f.setFlag(b2.getInt(2));
                f.setId(b3.getString(2));
                f.setName(b3.getString(8));
                f.setFriendsign(b3.getString(7));
                f.setHeadicon(b3.getInt(9));
                friends.add(f);
            }
            message.Userdata.setFriend(friends);
        }
        b.close(); // �ر�ResultSet ����
        statement.close(); // �ر�Statement ����
        return message;
    }

    // ���������б� ���Գɹ�
    public static boolean createFriendsList(String u_name) {
        String sql = "create table " + u_name + "friendslist("
                + "f_id varchar(20) , f_group int )";
        try {

            Statement stmt = dao1().createStatement();
            if (!stmt.execute(sql)) {
                System.out.println("�����б����ɹ�");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    // ���彫���ݲ������ݿ�ķ��� ���Գɹ�
    public static void ChaRu(String fromwho, String towho, String content,
                             String time, String img) throws Exception {

        String sql = "insert into W_content(f_who,t_who,chat_content,data,img) values(?,?,?,?,?)"; // ����һ���������ݿ��SQL��䣬���еģ�������Ըı�ı���
        PreparedStatement a = dao1().prepareStatement(sql); // ��coon������һ��PreparedStatement����
        a.setString(1, fromwho); // ��sender������ֵ������һ����
        a.setString(2, towho); // ��getter������ֵ�����ڶ�����
        a.setString(3, content); // ��connent������ֵ������������
        a.setString(4, time); // ��Time������ֵ�������ĸ���
        a.setString(5, img);
        a.executeUpdate(); // ִ�в������ݿ��SQl���
        System.out.println("���ݿ����ɹ�");
        if (a != null) {
            a.close(); // �ر�a ����
        }

    }

    // ������Ҽ�¼ ���Գɹ�
    public static RowSet chazhao(String fromwho, String towho) throws Exception {
        fromwho = "'" + fromwho + "'";
        towho = "'" + towho + "'";

        String sql = "select *from w_content where (f_who=" + fromwho
                + "and t_who=" + towho + ")or(f_who=" + towho + "and t_who="
                + fromwho + ")"; // ����ִ�в�ѯ��sql���
        Statement statement = dao1().createStatement(); // ����һ�� Statement
        ResultSet bb = statement.executeQuery(sql); // ִ�в�ѯ��sql��䣬���ҷ���һ��ResultSet��ѯ�����
        RowSet b = populate(bb);
        // while (b.next()) // b.next()��ʾָ�벻������һ����ֻҪ��Ϊ�ռ���
        // {
        // System.out.println(b.getString(2) + "\t" //
        // b.getString(1)��ʾһ����ѯ����ĵ�1���ֶε�ֵ
        // + b.getString(3) + "\t" // b.getString(2)��ʾһ����ѯ����ĵ�2���ֶε�ֵ
        // + b.getString(4) + "\t" // b.getString(3)��ʾһ����ѯ����ĵ�3���ֶε�ֵ
        // + b.getString(5)); // b.getString(4)��ʾһ����ѯ����ĵ�4���ֶε�ֵ
        // }

        return b;
    }

    //��ResultSetת��Ϊ�����л���RowSet
    public static RowSet populate(ResultSet rs) throws SQLException {
        CachedRowSetImpl crs = new CachedRowSetImpl();
        crs.populate(rs);
        return crs;
    }

    // ��Ӻ��� ���Գɹ�
    public static boolean addfriends(String fromwho, String f_name, int group)
            throws SQLException {
        // fromwho = "'" + fromwho + "'";
        if (!Dao.isperson(f_name))
            return false;
        String sql = "insert into " + fromwho
                + "friendslist (f_id,f_group) values(?,?)"; // ����һ���������ݿ��SQL��䣬���еģ�������Ըı�ı���
        PreparedStatement a;
        a = dao1().prepareStatement(sql);
        // ��coon������һ��PreparedStatement����
        a.setString(1, f_name); // ��u_name������ֵ������
        a.setInt(2, group);
        if (a.executeUpdate() != 0) // ִ�в������ݿ��SQl���
        {
            System.out.println("����ɹ�");
            return true;
        }
        a.close();// �ر�a ����
        return false;

    }

    // ɾ������ ���Գɹ�


    public static void main(String args[]) throws SQLException {

//		// Forget a = new Forget();
//		// a.setAnswer("answer");
//		try {
//			// Dao.chazhao("1111", "1");
//			Dao.addfriends("123", "12", 1);
//			// Dao.dropfriends("kjfkejkfek", "456123");
//			// Dao.personInformation("1");
//			// System.out.println(Dao.isperson("1"));
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// System.out.println(findAnser( a, "fef"));
//		// if( login("fef", "few") )
//		// System.out.println("0");
//		// try {
//		// Dao.ChaRu("1111", "1", "u_answer", "u_question", "u_sign");
//		// } catch (Exception e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// }
//		// Dao.createFriendsList("123");
//		// if(Dao.addUserInfo("1111", "1", "u_answer", "u_question",
//		// "u_sign"," x","y"))
//		// System.out.println("fjoe");
//		// //Dao.login("u_name", "u_password");
//		// //Dao.queryU_table("1", "1");
//		// createFriendsList(1);
    }

}
*/