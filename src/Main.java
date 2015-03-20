import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by hongyeah on 2015/2/4.
 */
public class Main extends JFrame{
    public static void main(String[] agrs){
        Dao.dao1();
        System.out.println("连接成功！");
        new Main();
    }

    public Main(){

        try {
            InitJtable();
            System.out.println("初始化table成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(300, 50);
        this.setSize(1200, 600);
        this.setResizable(true);
        this.setVisible(true);
    }

    public JTable All,Server,Jiashi,Lunji,GND,Neihe,Peixun,Jiankang;
    public static DefaultTableModel Dall,Dserver,Djiashi,Dlunji,DGND,Dneihe,Dpeixun,Djiankang;
    public JScrollPane jsp_all,jsp_server,jsp_jiashi,jsp_lunji,jsp_GND,jsp_neihe,jsp_peixun,jsp_jiankang;
    public JTabbedPane jTabbedPane;
    public ResultSet every;
    public void InitJtable() throws SQLException, IOException, ClassNotFoundException {
        jTabbedPane = new JTabbedPane();
        String[] Sall = {"编号","姓名","身份证","性别","电话"};
        Dall = new DefaultTableModel(Sall,0);
        All = new JTable(Dall);
        jsp_all= new JScrollPane(All);
        JScrollBar bar_all = jsp_all.getVerticalScrollBar();
        bar_all.setUnitIncrement(30);
        jTabbedPane.add("所有人员名单",jsp_all);

        String[] Sserver = {"编号","姓名","身份证","编号","签发日期"};
        Dserver = new DefaultTableModel(Sserver,0);
        Server = new JTable(Dserver);
        jsp_server = new JScrollPane(Server);
        JScrollBar bar_server = jsp_server.getVerticalScrollBar();
        bar_server.setUnitIncrement(30);
        jTabbedPane.add("服务簿",jsp_server);

        String[] Sjiashi = {"编号","姓名","身份证","证书编号","等级与职务","签发日期","截止日期"};
        Djiashi = new DefaultTableModel(Sjiashi,0);
        Jiashi = new JTable(Djiashi);
        jsp_jiashi = new JScrollPane(Jiashi);
        JScrollBar bar_jiashi = jsp_jiashi.getVerticalScrollBar();
        bar_jiashi.setUnitIncrement(30);
        jTabbedPane.add("驾驶",jsp_jiashi);

        String[] Slunji ={"编号","姓名","身份证","证书编号","等级与职务","签发日期","截止日期"};
        Dlunji = new DefaultTableModel(Slunji,0);
        Lunji = new JTable(Dlunji);
        jsp_lunji = new JScrollPane(Lunji);
        JScrollBar bar_lunji = jsp_lunji.getVerticalScrollBar();
        bar_lunji.setUnitIncrement(30);
        jTabbedPane.add("轮机",jsp_lunji);

        String[] SGND = {"编号","姓名","身份证","证书编号","等级与职务","签发日期","截止日期"};
        DGND = new DefaultTableModel(SGND,0);
        GND = new JTable(DGND);
        jsp_GND = new JScrollPane(GND);
        JScrollBar bar_GND = jsp_GND.getVerticalScrollBar();
        bar_GND.setUnitIncrement(30);
        jTabbedPane.add("GND",jsp_GND);

        String[] Sneihe = {"编号","姓名","身份证","证书编号","等级与职务","签发日期","截止日期"};
        Dneihe = new DefaultTableModel(Sneihe,0);
        Neihe = new JTable(Dneihe);
        jsp_neihe = new JScrollPane(Neihe);
        JScrollBar bar_neihe = jsp_neihe.getVerticalScrollBar();
        bar_neihe.setUnitIncrement(30);
        jTabbedPane.add("内河",jsp_neihe);

        String[] Speixun = {"编号","姓名","身份证","证书编号","签发日期"};
        Dpeixun = new DefaultTableModel(Speixun,0);
        Peixun = new JTable(Dpeixun);
        jsp_peixun = new JScrollPane(Peixun);
        JScrollBar bar_peixun = jsp_peixun.getVerticalScrollBar();
        bar_peixun.setUnitIncrement(30);
        jTabbedPane.add("船员培训合格证",jsp_peixun);

        String[] Sjiankang = {"编号","姓名","身份证","签发日期","有效期至"};
        Djiankang   = new DefaultTableModel(Sjiankang,0);
        Jiankang = new JTable(Djiankang);
        jsp_jiankang = new JScrollPane(Jiankang);
        JScrollBar bar_jiankang = jsp_jiankang.getVerticalScrollBar();
        bar_jiankang.setUnitIncrement(30);
        jTabbedPane.add("健康证",jsp_jiankang);

        findpanel();
        System.out.println("findpanel 成功");
        noticepanel();
        System.out.println("notic panel 成功");
        addpanel();
        System.out.println("add panel 成功");
        initperson();
        System.out.println("initperson 成功");
        this.add(jTabbedPane);
    }
    JButton find;
    JLabel find_name,find_ID;
    JTextField find_names,find_IDs;
    JPanel find_back;
    public void findpanel(){
        find_name = new JLabel("姓名");
        find_ID = new JLabel("身份证号码");
        find_names = new JTextField(20);
        find_IDs = new JTextField(20);
        find = new JButton("查找");
        find_back = new JPanel(new FlowLayout());
        find_back.add(find_name);
        find_back.add(find_names);
        find_back.add(find_ID);
        find_back.add(find_IDs);
        find_back.add(find);
        jTabbedPane.add("查找", find_back);

        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (find_names.getText().equals("") && find_IDs.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "输入为空，请重新输入");
                    return;
                }
                Allzhengshu findit = null;
                ResultSet rs = null;
                if (find_IDs.getText().equals("")) {
                    try {
                        rs = Dao.findforname(find_names.getText());
                    } catch (SQLException e2) {
                        e2.printStackTrace();
                    }

                    int j=0;
                    ObjectInputStream input = null;
                    try {
                        while(rs.next()){
                            input = new ObjectInputStream(rs.getBinaryStream(3));
                            j++;
                            findit = (Allzhengshu) input.readObject();
                            new Edit(findit);
                        }
                        if(j==0) {
                            findit = null;
                            find_names.setText(null);
                            find_IDs.setText(null);
                            JOptionPane.showMessageDialog(null, "找不到该用户，请核对后再输入");
                            return;
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }finally {
                        try {
                            if(input!=null)
                            input.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            rs.close();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        input = null;
                        rs = null;
                    }


                } else {
                    try {
                        findit = Dao.findforID(find_IDs.getText());
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    if (findit != null)
                        new Edit(findit);
                    else {
                        JOptionPane.showMessageDialog(null, "找不到该用户，请核对后再输入");
                    }
                }
                    findit = null;
                    find_names.setText(null);
                    find_IDs.setText(null);

            }
        });

    }
    public ArrayList<String> search_ID;
    public void initperson() throws SQLException, IOException, ClassNotFoundException {
        int datarows = 0;
        try {
            datarows=Dao.getcount();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        ResultSet rs = null;
        ObjectInputStream input =null;
        Allzhengshu azs =null;
        search_ID = new ArrayList<String>();
        for(int i = 0;i<datarows;i++) {
            rs = Dao.Get(i, 1);
            while (rs.next()) {
                input = new ObjectInputStream(rs.getBinaryStream(3));
                azs = (Allzhengshu) input.readObject();
                add_all_row(azs);
                search_ID.add(azs.ID);
                if (azs.haveServerBook()) {
                    add_server_row(azs, azs.serverBook);
                }
                if (azs.haveJiankang()) {
                    add_jiankang_row(azs, azs.jiankang);
                }
                if (azs.havejiashi()) {
                    add_jiashi_row(azs, azs.jiashi);
                }
                if (azs.havelunji()) {
                    add_lunji_row(azs, azs.lunji);
                }
                if (azs.haveGND()) {
                    add_GND_row(azs, azs.gnd);
                }
                if (azs.haveneihe()) {
                    add_neihe_row(azs, azs.neihe);
                }
                if (azs.havePeixunhege()) {
                    add_peixun_row(azs, azs.peixunhege);
                }
                input.close();
                input = null;
                azs = null;
            }
            rs.close();
        }

    }
    JPanel add_all = new JPanel();
    JPanel add_south = new JPanel();
    JPanel add = new JPanel();
    JPanel add_north = new JPanel();
    JLabel name = new JLabel("姓名:");
    JLabel ID = new JLabel("身份证号码:");
    JLabel sex = new JLabel("性别:");
    JLabel phone = new JLabel("电话:");
    JTextField names = new JTextField(20);
    JTextField IDs = new JTextField(20);
    JTextField sexs = new JTextField(20);
    JTextField phones = new JTextField(20);
    JPanel fuwu = new JPanel();
    JPanel b_jiashi = new JPanel();
    JPanel b_lunji = new JPanel();
    JPanel b_GND = new JPanel();
    JPanel b_neihe = new JPanel();
    JPanel b_peixun = new JPanel();
    JPanel b_jiankang = new JPanel();
    JButton save = new JButton("保存");
    JTabbedPane add_jtable = new JTabbedPane();
    public void addpanel(){
        add_all.setLayout(new BorderLayout());
        add_north.setLayout(new FlowLayout());
        add.setLayout(new FlowLayout());
        add.add(name);
        add.add(names);
        add.add(ID);
        add.add(IDs);
        add.add(sex);
        add.add(sexs);
        add.add(phone);
        add.add(phones);
        b_jiashi.add(jiashinumber);
        b_lunji.add(lunjinumber);
        add_jtable.add("服务",fuwuEdit());
        add_jtable.add("驾驶",jiashiEdit());
        add_jtable.add("轮机",lunjiEdit());
        add_jtable.add("GND",GNDEdit());
        add_jtable.add("内河",neiheEdit());
        add_jtable.add("培训合格证",peixunhegeEdit());
        add_jtable.add("健康证",jiankangEdit());
        add_all.add(add,BorderLayout.NORTH);
        add_all.add(add_jtable,BorderLayout.CENTER);
        add_south.add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(names.getText().equals("")||IDs.getText().equals("")||sexs.getText().equals("")||phones.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"请填好正确信息");
                    return;
                }
                if(IDs.getText().length() != 18) {
                    JOptionPane.showMessageDialog(null,"身份证号码不规范！");
                    return;
                }

                for(int i =0;i<search_ID.size();i++)
                 {
                     if (IDs.getText().equalsIgnoreCase(search_ID.get(i))) {
                         JOptionPane.showMessageDialog(null, "已经存在此人,请核对好资料！");
                         return;
                     }
                 }

                Allzhengshu azs = new Allzhengshu();
                azs.name = names.getText();
                azs.ID = IDs.getText();
                azs.sex = sexs.getText();
                azs.phonenumber = phones.getText();
                search_ID.add(azs.ID);
                if(!fuwunumber.getText().equals("")) {
                    ServerBook sb = new ServerBook();
                    sb.Number = fuwunumber.getText();
                    String s = fuwudata.getText().replace(".","-");
                    try {
                        sb.fromdate = Date.valueOf(s);
                    }catch(Exception t){
                        JOptionPane.showMessageDialog(null,"服务日期填写错误！");
                        return;
                    }
                    for(int i=0;i<10;i++){
                        sb.pictures[i] = (ImageIcon)imsave[i].getIcon();
                    }
                    azs.serverBook = sb;
                    add_server_row(azs, azs.serverBook);
                }
                if(!jiankangfromdata.getText().equals("")) {
                    Jiankang jk = new Jiankang();
                    String s = jiankangfromdata.getText().replace(".","-");
                    try {
                        jk.fromdate = Date.valueOf(s);
                    }catch(Exception t){
                        JOptionPane.showMessageDialog(null,"健康签发日期填写错误！");
                        return;
                    }
                    s = jiankangtodate.getText().replace(".","-");
                    try {
                        jk.todate = Date.valueOf(s);
                    }catch(Exception t){
                        JOptionPane.showMessageDialog(null,"健康截止日期填写错误！");
                        return;
                    }
                    for(int i=0;i<10;i++){
                        jk.pictures[i] = (ImageIcon)jiankangimsave[i].getIcon();
                    }
                    azs.jiankang = jk;
                    add_jiankang_row(azs, azs.jiankang);
                }
                if(!jiashinumber.getText().equals("")) {
                    jiashi js = new jiashi();
                    js.number = jiashinumber.getText();
                    js.job = jiashipowers.getText();
                    String s = jiashifromdata.getText().replace(".", "-");
                    try {
                        js.fromdate = Date.valueOf(s);
                    } catch (Exception t) {
                        JOptionPane.showMessageDialog(null, "驾驶签发日期填写错误！");
                        return;
                    }
                    s = jiashitodate.getText().replace(".", "-");
                    try {
                        js.todate = Date.valueOf(s);
                    } catch (Exception t) {
                        JOptionPane.showMessageDialog(null, "驾驶截止日期填写错误！");
                        return;
                    }
                    for (int i = 0; i < 10; i++) {
                        js.pictures[i] = (ImageIcon) jiashiimsave[i].getIcon();
                    }
                    azs.jiashi = js;
                    add_jiashi_row(azs, azs.jiashi);
                }

                if(!lunjinumber.getText().equals("")) {
                    lunji lj = new lunji();
                    lj.number = lunjinumber.getText();
                    lj.job = lunjipowers.getText();
                    String s = lunjifromdata.getText().replace(".","-");
                    try {
                        lj.fromdate = Date.valueOf(s);
                    }catch(Exception t){
                        JOptionPane.showMessageDialog(null,"轮机签发日期填写错误！");
                        return;
                    }
                    s = lunjitodate.getText().replace(".","-");
                    try {
                        lj.todate = Date.valueOf(s);
                    }catch(Exception t){
                        JOptionPane.showMessageDialog(null,"轮机截止日期填写错误！");
                        return;
                    }
                    for(int i=0;i<10;i++){
                        lj.pictures[i] = (ImageIcon)lunjiimsave[i].getIcon();
                    }
                    azs.lunji = lj;
                    add_lunji_row(azs, azs.lunji);
                }
                if(!GNDnumber.getText().equals("")) {
                    GND g = new GND();
                    g.number = GNDnumber.getText();
                    g.job = GNDpowers.getText();
                    String s = GNDfromdata.getText().replace(".","-");
                    try {
                        g.fromdate = Date.valueOf(s);
                    }catch(Exception t){
                        JOptionPane.showMessageDialog(null,"GND签发日期填写错误！");
                        return;
                    }
                    s = GNDtodate.getText().replace(".","-");
                    try {
                        g.todate = Date.valueOf(s);
                    }catch(Exception t){
                        JOptionPane.showMessageDialog(null,"GND日期填写错误！");
                        return;
                    }
                    for(int i=0;i<10;i++){
                        g.pictures[i] = (ImageIcon)GNDimsave[i].getIcon();
                    }
                    azs.gnd = g;
                    add_GND_row(azs, azs.gnd);
                }
                if(!neihenumber.getText().equals("")) {
                    neihe nh = new neihe();
                    nh.number = neihenumber.getText();
                    nh.job = neihepowers.getText();
                    String s = neihefromdata.getText().replace(".","-");
                    try {
                        nh.fromdate = Date.valueOf(s);
                    }catch(Exception t){
                        JOptionPane.showMessageDialog(null,"内河签发日期填写错误！");
                        return;
                    }
                    s = neihetodate.getText().replace(".","-");
                    try {
                        nh.todate = Date.valueOf(s);
                    }catch(Exception t){
                        JOptionPane.showMessageDialog(null,"内河截止日期填写错误！");
                        return;
                    }
                    for(int i=0;i<10;i++){
                        nh.pictures[i] = (ImageIcon)neiheimsave[i].getIcon();
                    }
                    azs.neihe = nh;
                    add_neihe_row(azs, azs.neihe);
                }
                if(!peixunnumber.getText().equals("")){
                    Peixunhege peixun = new Peixunhege();
                    peixun.number = peixunnumber.getText();
                    String s = peixunfromdata.getText().replace(".","-");
                    try {
                        peixun.fromdate = Date.valueOf(s);
                    }catch(Exception t){
                        JOptionPane.showMessageDialog(null,"培训合格证签发日期填写错误！");
                        return;
                    }
                    for(int i=0;i<10;i++){
                        if(!zhengname[i].getText().equals("")){
                            inPeixunhege ipxh = new inPeixunhege();
                            ipxh.name = zhengname[i].getText();
                            s = zhengfromdata[i].getText().replace(".","-");
                            try {
                                ipxh.fromdate = Date.valueOf(s);
                            }catch(Exception t){
                                JOptionPane.showMessageDialog(null,"证书"+(i+1)+"签发日期填写错误！");
                                return;
                            }
                            s = zhengtodata[i].getText().replace(".","-");
                            try {
                                ipxh.todate = Date.valueOf(s);
                            }catch(Exception t){
                                JOptionPane.showMessageDialog(null,"证书"+(i+1)+"截止日期填写错误！");
                                return;
                            }
                            peixun.include.add(ipxh);
                        }
                    }
                    for(int i = 0; i<10;i++){
                        peixun.pictures[i] = (ImageIcon)peixunimsave[i].getIcon();
                    }
                    azs.peixunhege=peixun;
                    add_peixun_row(azs, azs.peixunhege);
                }
                peixunnumber.setText(null);
                peixunfromdata.setText(null);
                jiashinumber.setText(null);
                jiashifromdata.setText(null);
                jiashipowers.setText(null);
                jiashitodate.setText(null);
                lunjinumber.setText(null);
                lunjifromdata.setText(null);
                lunjipowers.setText(null);
                lunjitodate.setText(null);
                GNDnumber.setText(null);
                GNDfromdata.setText(null);
                GNDpowers.setText(null);
                GNDtodate.setText(null);
                neihenumber.setText(null);
                neihefromdata.setText(null);
                neihepowers.setText(null);
                neihetodate.setText(null);
                fuwunumber.setText(null);
                fuwudata.setText(null);
                jiankangfromdata.setText(null);
                jiankangtodate.setText(null);
                names.setText(null);
                IDs.setText(null);
                sexs.setText(null);
                phones.setText(null);
                for(int i=0;i<10;i++){
                    zhengname[i].setText(null);
                    zhengfromdata[i].setText(null);
                    zhengtodata[i].setText(null);
                    imsave[i].setIcon(null);
                    jiashiimsave[i].setIcon(null);
                    lunjiimsave[i].setIcon(null);
                    GNDimsave[i].setIcon(null);
                    neiheimsave[i].setIcon(null);
                    jiankangimsave[i].setIcon(null);
                    peixunimsave[i].setIcon(null);
                }
                add_all_row(azs);
                try {
                    Dao.addUserInfo(azs);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        add_all.add(add_south,BorderLayout.SOUTH);
        jTabbedPane.add("添加",add_all);

    }

    JPanel notice,six,five,four,three,two,one;
    JComboBox jComboBox;
    JScrollPane jScrollPanesix;
    JScrollPane jScrollPanefive;
    JScrollPane jScrollPanefour;
    JScrollPane jScrollPanethree;
    JScrollPane jScrollPanetwo;
    JScrollPane jScrollPaneone;
    CardLayout c= new CardLayout();
    JPanel Card;
    public void noticepanel() throws SQLException, IOException, ClassNotFoundException {
        notice = new JPanel();
        Card = new JPanel(c);
        notice.setLayout(new BorderLayout());
        jComboBox = new JComboBox();
        notice.add(jComboBox, BorderLayout.NORTH);
        jComboBox.addItem("6个月");
        jComboBox.addItem("5个月");
        jComboBox.addItem("4个月");
        jComboBox.addItem("3个月");
        jComboBox.addItem("2个月");
        jComboBox.addItem("1个月");
        jComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    if (e.getItem().equals("1个月")) {
                        c.show(Card, "one");
                    } else if (e.getItem().equals("2个月")) {
                        c.show(Card, "two");
                    } else if (e.getItem().equals("3个月")) {
                        c.show(Card, "three");
                    } else if (e.getItem().equals("4个月")) {
                        c.show(Card, "four");
                    } else if (e.getItem().equals("5个月")) {
                        c.show(Card, "five");
                    } else if (e.getItem().equals("6个月")) {
                        c.show(Card, "six");
                    }
            }
        });
        six = new JPanel();
        five = new JPanel();
        four = new JPanel();
        three = new JPanel();
        two = new JPanel();
        one = new JPanel();
        six.setLayout(new GridLayout(0, 1));
        five.setLayout(new GridLayout(0, 1));
        four.setLayout(new GridLayout(0, 1));
        three.setLayout(new GridLayout(0, 1));
        two.setLayout(new GridLayout(0, 1));
        one.setLayout(new GridLayout(0, 1));
        DateCalculate dateCalculate;
        java.util.Date now = new java.util.Date();
        int datarows = 0;
        try {
            datarows = Dao.getcount();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        ResultSet rs = null;
        ObjectInputStream input = null;
        Allzhengshu allzhengshu = null;
        for (int i = 0; i < datarows; i++) {

            rs = Dao.Get(i, 1);

            while (rs.next()) {
                input = new ObjectInputStream(rs.getBinaryStream(3));
                allzhengshu = (Allzhengshu) input.readObject();

                if (allzhengshu.havejiashi()) {
                    dateCalculate = DateCalculate.calculate(now, allzhengshu.jiashi.todate);
                    if (dateCalculate != null) {
                        if (dateCalculate.getDayss() <= 30) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                            two.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                            one.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 60) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                            two.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 90) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 120) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 150) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 180) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 驾驶证截止日期为 " + allzhengshu.jiashi.todate.toString()));
                        }
                    }
                }
                if (allzhengshu.havelunji()) {
                    dateCalculate = DateCalculate.calculate(now, allzhengshu.lunji.todate);
                    if (dateCalculate != null) {
                        if (dateCalculate.getDayss() <= 30) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                            two.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                            one.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 60) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                            two.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 90) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 120) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 150) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 180) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 轮机证截止日期为 " + allzhengshu.lunji.todate.toString()));
                        }
                    }
                }
                if (allzhengshu.haveGND()) {
                    dateCalculate = DateCalculate.calculate(now, allzhengshu.gnd.todate);
                    if (dateCalculate != null) {
                        if (dateCalculate.getDayss() <= 30) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND截止日期为 " + allzhengshu.gnd.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND证截止日期为 " + allzhengshu.gnd.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND证截止日期为 " + allzhengshu.gnd.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + "GND证截止日期为 " + allzhengshu.gnd.todate.toString()));
                            two.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND证截止日期为 " + allzhengshu.gnd.todate.toString()));
                            one.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND证截止日期为 " + allzhengshu.gnd.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 60) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND截止日期为 " + allzhengshu.gnd.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND证截止日期为 " + allzhengshu.gnd.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND证截止日期为 " + allzhengshu.gnd.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + "GND证截止日期为 " + allzhengshu.gnd.todate.toString()));
                            two.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND证截止日期为 " + allzhengshu.gnd.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 90) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND截止日期为 " + allzhengshu.gnd.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND证截止日期为 " + allzhengshu.gnd.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND证截止日期为 " + allzhengshu.gnd.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + "GND证截止日期为 " + allzhengshu.gnd.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 120) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND截止日期为 " + allzhengshu.gnd.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND证截止日期为 " + allzhengshu.gnd.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND证截止日期为 " + allzhengshu.gnd.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 150) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND截止日期为 " + allzhengshu.gnd.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND证截止日期为 " + allzhengshu.gnd.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 180) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " GND截止日期为 " + allzhengshu.gnd.todate.toString()));
                        }
                    }
                }
                if (allzhengshu.haveneihe()) {
                    dateCalculate = DateCalculate.calculate(now, allzhengshu.neihe.todate);
                    if (dateCalculate != null) {
                        if (dateCalculate.getDayss() <= 30) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + "内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                            two.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                            one.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 60) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + "内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                            two.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 90) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + "内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 120) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 150) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 180) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 内河证截止日期为 " + allzhengshu.neihe.todate.toString()));
                        }
                    }
                }
                if (allzhengshu.haveJiankang()) {
                    dateCalculate = DateCalculate.calculate(now, allzhengshu.jiankang.todate);
                    if (dateCalculate != null) {
                        if (dateCalculate.getDayss() <= 30) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + "健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                            two.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                            one.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 60) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + "健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                            two.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 90) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + "健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 120) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 150) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 180) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " 健康证截止日期为 " + allzhengshu.jiankang.todate.toString()));
                        }
                    }
                }
                if (allzhengshu.havePeixunhege()) {
                    for (int j = 0; j < allzhengshu.peixunhege.include.size(); j++) {
                        inPeixunhege include = allzhengshu.peixunhege.include.get(j);
                        dateCalculate = DateCalculate.calculate(now, allzhengshu.peixunhege.include.get(j).todate);
                        if (dateCalculate.getDayss() <= 30) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                            two.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                            one.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 60) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                            two.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 90) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                            three.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 120) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                            four.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 150) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                            five.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                        } else if (dateCalculate.getDayss() <= 180) {
                            six.add(new JLabel(allzhengshu.name + " " + allzhengshu.ID + " " + include.name + "证截止日期为 " + include.todate.toString()));
                        }
                    }
                }
                input.close();
                input = null;
                allzhengshu = null;
            }

            rs.close();
            rs = null;
        }
            jScrollPanesix = new JScrollPane(six);
            jScrollPanefive = new JScrollPane(five);
            jScrollPanefour = new JScrollPane(four);
            jScrollPanethree = new JScrollPane(three);
            jScrollPanetwo = new JScrollPane(two);
            jScrollPaneone = new JScrollPane(one);
            JScrollBar bar_six = jScrollPanesix.getVerticalScrollBar();
            bar_six.setUnitIncrement(30);
            JScrollBar bar_five = jScrollPanefive.getVerticalScrollBar();
            bar_five.setUnitIncrement(30);
            JScrollBar bar_four = jScrollPanefour.getVerticalScrollBar();
            bar_four.setUnitIncrement(30);
            JScrollBar bar_three = jScrollPanethree.getVerticalScrollBar();
            bar_three.setUnitIncrement(30);
            JScrollBar bar_two = jScrollPanetwo.getVerticalScrollBar();
            bar_two.setUnitIncrement(30);
            JScrollBar bar_one = jScrollPaneone.getVerticalScrollBar();
            bar_one.setUnitIncrement(30);
            notice.add(Card, BorderLayout.CENTER);

            Card.add(jScrollPaneone, "one");
            Card.add(jScrollPanetwo, "two");
            Card.add(jScrollPanethree, "three");
            Card.add(jScrollPanefour, "four");
            Card.add(jScrollPanefive, "five");
            Card.add(jScrollPanesix, "six");
            c.show(Card, "six");
            jTabbedPane.add("公告", notice);
        }

    JLabel fuwunumbers = new JLabel("编号");
    JTextField fuwunumber = new JTextField(20);
    JLabel fuwudatas = new JLabel("签发日期");
    JTextField fuwudata = new JTextField(20);
    JButton[] imsave = new JButton[10];
    JScrollPane fuwujsp;
    public JScrollPane fuwuEdit(){
        fuwu.setLayout(new BoxLayout(fuwu,BoxLayout.Y_AXIS));
        fuwu.add(fuwunumbers);
        fuwu.add(fuwunumber);
        fuwu.add(fuwudatas);
        fuwu.add(fuwudata);
        for(int i =0;i<10 ;i++){
            imsave[i]=new JButton("选择图片"+(i+1));
            fuwu.add(imsave[i]);
            imsave[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "JPG & GIF Images", "jpg", "gif");
                    chooser.setFileFilter(filter);
                    chooser.showDialog(new JLabel(), "Choose");
                    ((JButton)e.getSource()).setIcon(new ImageIcon(chooser.getSelectedFile().getAbsolutePath()));

                }
            });
        }

        fuwujsp = new JScrollPane(fuwu);
        JScrollBar bar_fuwujsp = fuwujsp.getVerticalScrollBar();
        bar_fuwujsp.setUnitIncrement(30);
        return fuwujsp;
    }
    JLabel jiashinumbers = new JLabel("证书编号");
    JTextField jiashinumber = new JTextField(20);
    JLabel jiashipower = new JLabel("等级与职务");
    JTextField jiashipowers = new JTextField(20);
    JLabel jiashifromdatas = new JLabel("签发日期");
    JTextField jiashifromdata = new JTextField(20);
    JLabel jiashitodates = new JLabel("截止日期");
    JTextField jiashitodate = new JTextField(20);
    JButton[] jiashiimsave = new JButton[10];
    JScrollPane jiashijsp;
    public JScrollPane lunjiEdit(){
        b_lunji.setLayout(new BoxLayout(b_lunji,BoxLayout.Y_AXIS));
        b_lunji.add(lunjinumbers);
        b_lunji.add(lunjinumber);
        b_lunji.add(lunjipower);
        b_lunji.add(lunjipowers);
        b_lunji.add(lunjifromdatas);
        b_lunji.add(lunjifromdata);
        b_lunji.add(lunjitodates);
        b_lunji.add(lunjitodate);
        for(int i=0;i<10;i++){
            lunjiimsave[i] = new JButton("选择图片"+(i+1));
            b_lunji.add(lunjiimsave[i]);
            lunjiimsave[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "JPG & GIF Images", "jpg", "gif");
                    chooser.setFileFilter(filter);
                    chooser.showDialog(new JLabel(), "Choose");
                    ((JButton)e.getSource()).setIcon(new ImageIcon(chooser.getSelectedFile().getAbsolutePath()));

                }
            });
        }
        lunjijsp = new JScrollPane(b_lunji);
        JScrollBar bar_lunjijsp = lunjijsp.getVerticalScrollBar();
        bar_lunjijsp.setUnitIncrement(30);
        return lunjijsp;
    }
    JLabel lunjinumbers = new JLabel("证书编号");
    JTextField lunjinumber = new JTextField(20);
    JLabel lunjipower = new JLabel("等级与职务");
    JTextField lunjipowers = new JTextField(20);
    JLabel lunjifromdatas = new JLabel("签发日期");
    JTextField lunjifromdata = new JTextField(20);
    JLabel lunjitodates = new JLabel("截止日期");
    JTextField lunjitodate = new JTextField(20);
    JButton[] lunjiimsave = new JButton[10];
    JScrollPane lunjijsp;
    public JScrollPane jiashiEdit(){
        b_jiashi.setLayout(new BoxLayout(b_jiashi,BoxLayout.Y_AXIS));
        b_jiashi.add(jiashinumbers);
        b_jiashi.add(jiashinumber);
        b_jiashi.add(jiashipower);
        b_jiashi.add(jiashipowers);
        b_jiashi.add(jiashifromdatas);
        b_jiashi.add(jiashifromdata);
        b_jiashi.add(jiashitodates);
        b_jiashi.add(jiashitodate);
        for(int i=0;i<10;i++){
            jiashiimsave[i] = new JButton("选择图片"+(i+1));
            b_jiashi.add(jiashiimsave[i]);
            jiashiimsave[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "JPG & GIF Images", "jpg", "gif");
                    chooser.setFileFilter(filter);
                    chooser.showDialog(new JLabel(), "Choose");
                    ((JButton)e.getSource()).setIcon(new ImageIcon(chooser.getSelectedFile().getAbsolutePath()));

                }
            });
        }
        jiashijsp = new JScrollPane(b_jiashi);
        JScrollBar bar_jiashijsp = jiashijsp.getVerticalScrollBar();
        bar_jiashijsp.setUnitIncrement(30);
        return jiashijsp;
    }
    JLabel GNDnumbers = new JLabel("证书编号");
    JTextField GNDnumber = new JTextField(20);
    JLabel GNDpower = new JLabel("等级与职务");
    JTextField GNDpowers = new JTextField(20);
    JLabel GNDfromdatas = new JLabel("签发日期");
    JTextField GNDfromdata = new JTextField(20);
    JLabel GNDtodates = new JLabel("截止日期");
    JTextField GNDtodate = new JTextField(20);
    JButton[] GNDimsave = new JButton[10];
    JScrollPane GNDjsp;
    public JScrollPane GNDEdit(){
        b_GND.setLayout(new BoxLayout(b_GND,BoxLayout.Y_AXIS));
        b_GND.add(GNDnumbers);
        b_GND.add(GNDnumber);
        b_GND.add(GNDpower);
        b_GND.add(GNDpowers);
        b_GND.add(GNDfromdatas);
        b_GND.add(GNDfromdata);
        b_GND.add(GNDtodates);
        b_GND.add(GNDtodate);
        for(int i=0;i<10;i++){
            GNDimsave[i] = new JButton("选择图片"+(i+1));
            b_GND.add(GNDimsave[i]);
            GNDimsave[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "JPG & GIF Images", "jpg", "gif");
                    chooser.setFileFilter(filter);
                    chooser.showDialog(new JLabel(), "Choose");
                    ((JButton)e.getSource()).setIcon(new ImageIcon(chooser.getSelectedFile().getAbsolutePath()));

                }
            });
        }
        GNDjsp = new JScrollPane(b_GND);
        JScrollBar bar_GNDjsp = GNDjsp.getVerticalScrollBar();
        bar_GNDjsp.setUnitIncrement(30);
        return GNDjsp;
    }
    JLabel jiankangfromdatas = new JLabel("签发日期");
    JTextField jiankangfromdata = new JTextField(20);
    JLabel jiankangtodates = new JLabel("截止日期");
    JTextField jiankangtodate = new JTextField(20);
    JButton[] jiankangimsave = new JButton[10];
    JScrollPane jiankangjsp;
    public JScrollPane jiankangEdit(){
        b_jiankang.setLayout(new BoxLayout(b_jiankang,BoxLayout.Y_AXIS));
        b_jiankang.add(jiankangfromdatas);
        b_jiankang.add(jiankangfromdata);
        b_jiankang.add(jiankangtodates);
        b_jiankang.add(jiankangtodate);
        for(int i=0;i<10;i++){
            jiankangimsave[i] = new JButton("选择图片"+(i+1));
            b_jiankang.add(jiankangimsave[i]);
            jiankangimsave[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "JPG & GIF Images", "jpg", "gif");
                    chooser.setFileFilter(filter);
                    chooser.showDialog(new JLabel(), "Choose");
                    ((JButton)e.getSource()).setIcon(new ImageIcon(chooser.getSelectedFile().getAbsolutePath()));

                }
            });
        }
        jiankangjsp = new JScrollPane(b_jiankang);
        JScrollBar bar_jiankangjsp = jiankangjsp.getVerticalScrollBar();
        bar_jiankangjsp.setUnitIncrement(30);
        return jiankangjsp;
    }
    JLabel neihenumbers = new JLabel("证书编号");
    JTextField neihenumber = new JTextField(20);
    JLabel neihepower = new JLabel("等级与职务");
    JTextField neihepowers = new JTextField(20);
    JLabel neihefromdatas = new JLabel("签发日期");
    JTextField neihefromdata = new JTextField(20);
    JLabel neihetodates = new JLabel("截止日期");
    JTextField neihetodate = new JTextField(20);
    JButton[] neiheimsave = new JButton[10];
    JScrollPane neihejsp;
    public JScrollPane neiheEdit(){
        b_neihe.setLayout(new BoxLayout(b_neihe,BoxLayout.Y_AXIS));
        b_neihe.add(neihenumbers);
        b_neihe.add(neihenumber);
        b_neihe.add(neihepower);
        b_neihe.add(neihepowers);
        b_neihe.add(neihefromdatas);
        b_neihe.add(neihefromdata);
        b_neihe.add(neihetodates);
        b_neihe.add(neihetodate);
        for(int i=0;i<10;i++){
            neiheimsave[i] = new JButton("选择图片"+(i+1));
            b_neihe.add(neiheimsave[i]);
            neiheimsave[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "JPG & GIF Images", "jpg", "gif");
                    chooser.setFileFilter(filter);
                    chooser.showDialog(new JLabel(), "Choose");
                    ((JButton) e.getSource()).setIcon(new ImageIcon(chooser.getSelectedFile().getAbsolutePath()));

                }
            });
        }
        neihejsp = new JScrollPane(b_neihe);
        JScrollBar bar_neihejsp = neihejsp.getVerticalScrollBar();
        bar_neihejsp.setUnitIncrement(30);
        return neihejsp;
    }
    JLabel peixunnumbers = new JLabel("证书编号");
    JTextField peixunnumber = new JTextField(20);
    JLabel peixunfromdatas = new JLabel("签发日期");
    JTextField peixunfromdata = new JTextField(20);
    JLabel[] zhengnames = new JLabel[10];
    JTextField[] zhengname = new JTextField[10];
    JLabel[] zhengfromdatas = new JLabel[10];
    JTextField[] zhengfromdata = new JTextField[10];
    JLabel[] zhengtodatas = new JLabel[10];
    JTextField[] zhengtodata = new JTextField[10];
    JButton[] peixunimsave = new JButton[10];
    JScrollPane peixunjsp;
    JScrollBar peixunjspbar;
    public JScrollPane peixunhegeEdit(){
        b_peixun.setLayout(new BoxLayout(b_peixun,BoxLayout.Y_AXIS));
        b_peixun.add(peixunnumbers);
        b_peixun.add(peixunnumber);
        b_peixun.add(peixunfromdatas);
        b_peixun.add(peixunfromdata);
        for(int i=0; i<10;i++){
            zhengnames[i] = new JLabel("证书"+(i+1));
            b_peixun.add(zhengnames[i]);
            zhengname[i] = new JTextField(20);
            b_peixun.add(zhengname[i]);
            zhengfromdatas[i] = new JLabel("证书"+(i+1)+"的签发日期");
            zhengfromdata[i] = new JTextField(20);
            b_peixun.add(zhengfromdatas[i]);
            b_peixun.add(zhengfromdata[i]);
            zhengtodatas[i] = new JLabel("证书"+(i+1)+"的截止日期");
            zhengtodata[i] = new JTextField(20);
            b_peixun.add(zhengtodatas[i]);
            b_peixun.add(zhengtodata[i]);
        }
        for(int i=0;i<10;i++){
            peixunimsave[i] = new JButton("选择图片"+(i+1));
            b_peixun.add(peixunimsave[i]);
            peixunimsave[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "JPG & GIF Images", "jpg", "gif");
                    chooser.setFileFilter(filter);
                    chooser.showDialog(new JLabel(), "Choose");
                    ((JButton) e.getSource()).setIcon(new ImageIcon(chooser.getSelectedFile().getAbsolutePath()));

                }
            });
        }
        peixunjsp = new JScrollPane(b_peixun);
        peixunjspbar = peixunjsp.getVerticalScrollBar();
        peixunjspbar.setUnitIncrement(30);
        return peixunjsp;
    }
    public static void add_all_row(Allzhengshu azs){
        for(int i=0;i<Dall.getRowCount();i++){
            Dall.setValueAt(i+1,i,0);
        }
        String[] rrow = {String.valueOf(Dall.getRowCount()+1),azs.name,azs.ID,azs.sex,azs.phonenumber};
        Dall.addRow(rrow);
    }
    public static void add_server_row(Allzhengshu azs , ServerBook sb){
        for(int i=0;i<Dserver.getRowCount();i++){
            Dserver.setValueAt(i+1,i,0);
        }
        String[] rrow = {String.valueOf(Dserver.getRowCount()+1),azs.name,azs.ID,sb.Number,sb.fromdate.toString()};
        Dserver.addRow(rrow);
    }
    public static void add_jiashi_row(Allzhengshu azs , jiashi js){
        for(int i=0;i<Djiashi.getRowCount();i++){
            Djiashi.setValueAt(i+1,i,0);
        }
        String[] rrow = {String.valueOf(Djiashi.getRowCount()+1),azs.name,azs.ID,js.number,js.job,js.fromdate.toString(),js.todate.toString()};
        Djiashi.addRow(rrow);
    }
    public static void add_GND_row(Allzhengshu azs , GND js){
        for(int i=0;i<DGND.getRowCount();i++){
            DGND.setValueAt(i+1,i,0);
        }
        String[] rrow = {String.valueOf(DGND.getRowCount()+1),azs.name,azs.ID,js.number,js.job,js.fromdate.toString(),js.todate.toString()};
        DGND.addRow(rrow);
    }
    public static void add_neihe_row(Allzhengshu azs , neihe js){
        for(int i=0;i<Dneihe.getRowCount();i++){
            Dneihe.setValueAt(i+1,i,0);
        }
        String[] rrow = {String.valueOf(Dneihe.getRowCount()+1),azs.name,azs.ID,js.number,js.job,js.fromdate.toString(),js.todate.toString()};
        Dneihe.addRow(rrow);
    }
    public static void add_lunji_row(Allzhengshu azs , lunji js){
        for(int i=0;i<Dlunji.getRowCount();i++){
            Dlunji.setValueAt(i + 1, i, 0);
        }
        String[] rrow = {String.valueOf(Dlunji.getRowCount()+1),azs.name,azs.ID,js.number,js.job,js.fromdate.toString(),js.todate.toString()};
        Dlunji.addRow(rrow);
    }
    public static void add_jiankang_row(Allzhengshu azs , Jiankang js){
        for(int i=0;i<Djiankang.getRowCount();i++){
            Djiankang.setValueAt(i+1,i,0);
        }
        String[] rrow = {String.valueOf(Djiankang.getRowCount()+1),azs.name,azs.ID,js.fromdate.toString(),js.todate.toString()};
        Djiankang.addRow(rrow);
    }
    public static void add_peixun_row(Allzhengshu azs , Peixunhege js){
        for(int i=0;i<Dpeixun.getRowCount();i++){
            Dpeixun.setValueAt((i+1),i,0);
        }
        String[] rrow = {String.valueOf(Dpeixun.getRowCount()+1),azs.name,azs.ID,js.number,js.fromdate.toString()};
        Dpeixun.addRow(rrow);
    }



}


