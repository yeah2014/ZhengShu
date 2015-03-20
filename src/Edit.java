import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by hongyeah on 2015/2/21.
 */
public class Edit extends JFrame {
    public Allzhengshu azs;
    public Edit(Allzhengshu azs){
        this.azs = azs;
        addpanel();
        names.setText(azs.name);
        names.setEditable(false);
        IDs.setText(azs.ID);
        IDs.setEditable(false);
        sexs.setText(azs.sex);
        sexs.setEditable(false);
        phones.setText(azs.phonenumber);
        if(azs.haveServerBook()){
            fuwunumber.setText(azs.serverBook.Number);
            fuwudata.setText(azs.serverBook.fromdate.toString());
            for(int i=0 ; i<10;i++){
                imsave[i].setIcon(azs.serverBook.pictures[i]);
            }
        }
        if(azs.haveJiankang()){
            jiankangfromdata.setText(azs.jiankang.fromdate.toString());
            jiankangtodate.setText(azs.jiankang.todate.toString());
            for(int i=0 ; i<10;i++){
                jiankangimsave[i].setIcon(azs.jiankang.pictures[i]);
            }
        }
        if(azs.havejiashi()){
            jiashinumber.setText(azs.jiashi.number);
            jiashipowers.setText(azs.jiashi.job);
            jiashifromdata.setText(azs.jiashi.fromdate.toString());
            jiashitodate.setText(azs.jiashi.todate.toString());
            for(int i=0;i<10;i++){
                jiashiimsave[i].setIcon(azs.jiashi.pictures[i]);
            }
        }
        if(azs.havelunji()){
            lunjinumber.setText(azs.lunji.number);
            lunjipowers.setText(azs.lunji.job);
            lunjifromdata.setText(azs.lunji.fromdate.toString());
            lunjitodate.setText(azs.lunji.todate.toString());
            for(int i=0 ;i<10;i++){
                lunjiimsave[i].setIcon(azs.lunji.pictures[i]);
            }
        }
        if(azs.haveGND()){
            GNDnumber.setText(azs.gnd.number);
            GNDpowers.setText(azs.gnd.job);
            GNDfromdata.setText(azs.gnd.fromdate.toString());
            GNDtodate.setText(azs.gnd.todate.toString());
            for(int i=0;i<10;i++){
                GNDimsave[i].setIcon(azs.gnd.pictures[i]);
            }
        }
        if(azs.haveneihe()){
            neihenumber.setText(azs.neihe.number);
            neihepowers.setText(azs.neihe.job);
            neihefromdata.setText(azs.neihe.fromdate.toString());
            neihetodate.setText(azs.neihe.todate.toString());
            for(int i=0;i<10;i++){
                neiheimsave[i].setIcon(azs.neihe.pictures[i]);
            }
        }
        if(azs.havePeixunhege()){
            peixunnumber.setText(azs.peixunhege.number);
            peixunfromdata.setText(azs.peixunhege.fromdate.toString());
            for(int i =0;i<azs.peixunhege.include.size();i++){
                zhengname[i].setText(azs.peixunhege.include.get(i).name);
                zhengfromdata[i].setText(azs.peixunhege.include.get(i).fromdate.toString());
                zhengtodata[i].setText(azs.peixunhege.include.get(i).todate.toString());
            }
            for(int i=0;i<10;i++){
                peixunimsave[i].setIcon(azs.peixunhege.pictures[i]);
            }
        }


        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(200,100);
        this.setSize(1200, 600);
        this.setResizable(true);
        this.setVisible(true);
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
    JButton cancel = new JButton("返回");
    JButton delete = new JButton("删除");
    JButton out = new JButton("所有图片导出");
    JTabbedPane add_jtable = new JTabbedPane();
    public void deleperson(){
        for(int i=0;i<Main.Dall.getRowCount();i++){
            if(azs.ID.equalsIgnoreCase(Main.Dall.getValueAt(i,2).toString())){
                Main.Dall.removeRow(i);
                break;
            }
        }
        if(azs.havePeixunhege()){
            for(int i=0;i<Main.Dpeixun.getRowCount();i++){
                if(azs.ID.equalsIgnoreCase(Main.Dpeixun.getValueAt(i,2).toString())){
                    Main.Dpeixun.removeRow(i);
                    break;
                }
            }
        }
        if(azs.haveJiankang()){
            for(int i=0;i<Main.Djiankang.getRowCount();i++){
                if(azs.ID.equalsIgnoreCase(Main.Djiankang.getValueAt(i,2).toString())){
                    Main.Djiankang.removeRow(i);
                    break;
                }
            }
        }
        if(azs.havejiashi()){
            for(int i=0;i<Main.Djiashi.getRowCount();i++){
                if(azs.ID.equalsIgnoreCase(Main.Djiashi.getValueAt(i,2).toString())){
                    Main.Djiashi.removeRow(i);
                    break;
                }
            }
        }
        if(azs.havelunji()){
            for(int i=0;i<Main.Dlunji.getRowCount();i++){
                if(azs.ID.equalsIgnoreCase(Main.Dlunji.getValueAt(i,2).toString())){
                    Main.Dlunji.removeRow(i);
                    break;
                }
            }
        }
        if(azs.haveneihe()){
            for(int i=0;i<Main.Dneihe.getRowCount();i++){
                if(azs.ID.equalsIgnoreCase(Main.Dneihe.getValueAt(i,2).toString())){
                    Main.Dneihe.removeRow(i);
                    break;
                }
            }
        }
        if(azs.haveGND()){
            for(int i=0;i<Main.DGND.getRowCount();i++){
                if(azs.ID.equalsIgnoreCase(Main.DGND.getValueAt(i,2).toString())){
                    Main.DGND.removeRow(i);
                    break;
                }
            }
        }
        if(azs.haveServerBook()){
            for(int i=0;i<Main.Dserver.getRowCount();i++){
                if(azs.ID.equalsIgnoreCase(Main.Dserver.getValueAt(i,2).toString())){
                    Main.Dserver.removeRow(i);
                    break;
                }
            }
        }

    }
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
        add_south.add(cancel);
        add_south.add(delete);
        add_south.add(out);
        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(azs.haveServerBook()){
                    ServerBook sb = azs.serverBook;
                    for(int i=0;i<sb.pictures.length;i++){
                        if(sb.pictures[i]!=null){
                            new CreateImage(sb.pictures[i],"D://证书/"+azs.name+"/服务",i+1);
                            System.out.println("fuwu");
                        }
                    }
                }
                if(azs.havejiashi()){
                    jiashi jiashi = azs.jiashi;
                    for(int i=0;i<jiashi.pictures.length;i++){
                        if(jiashi.pictures[i]!=null){
                            new CreateImage(jiashi.pictures[i],"D://证书/"+azs.name+"/驾驶",i+1);
                            System.out.println("jiashi");
                        }
                    }
                }
                if(azs.havelunji()){
                    lunji lunji = azs.lunji;
                    for(int i=0;i<lunji.pictures.length;i++){
                        if(lunji.pictures[i]!=null){
                            new CreateImage(lunji.pictures[i],"D://证书/"+azs.name+"/轮机",i+1);
                        }
                    }
                }
                if(azs.haveneihe()){
                    neihe neihe = azs.neihe;
                    for(int i=0;i<neihe.pictures.length;i++){
                        if(neihe.pictures[i]!=null){
                            new CreateImage(neihe.pictures[i],"D://证书/"+azs.name+"/内河",i+1);
                        }
                    }
                }
                if(azs.haveGND()){
                    GND gnd = azs.gnd;
                    for(int i=0 ;i<gnd.pictures.length;i++){
                        if(gnd.pictures[i]!=null){
                            new CreateImage(gnd.pictures[i],"D://证书/"+azs.name+"/GND",i+1);
                        }
                    }
                }
                if(azs.haveJiankang()){
                    Jiankang jiankang = azs.jiankang;
                    for(int i=0;i<jiankang.pictures.length;i++){
                        if(jiankang.pictures[i]!=null){
                            new CreateImage(jiankang.pictures[i],"D://证书/"+azs.name+"/健康",i+1);
                        }
                    }
                }
                if(azs.havePeixunhege()){
                    Peixunhege peixunhege = azs.peixunhege;
                    for(int i=0;i<peixunhege.pictures.length;i++){
                        if(peixunhege.pictures[i]!=null){
                            new CreateImage(peixunhege.pictures[i],"D://证书/"+azs.name+"/培训合格证",i+1);
                        }
                    }
                }
                JOptionPane.showMessageDialog(null,"己导出到D:\\\\证书\\中");
            }

        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int k=JOptionPane.showConfirmDialog(null,"确定要删除？");
                if(k!=0){
                    return;
                }
                deleperson();
                JOptionPane.showMessageDialog(null,"正在删除...");
                try {
                    Dao.dropperson(azs.ID);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                dispose();
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (phones.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请填好手机号码");
                    return;
                }

                azs.name = names.getText();
                azs.ID = IDs.getText();
                azs.sex = sexs.getText();
                azs.phonenumber = phones.getText();
                if (!fuwunumber.getText().equals("")) {
                    ServerBook sb = new ServerBook();
                    sb.Number = fuwunumber.getText();
                    String s = fuwudata.getText().replace(".", "-");
                    try {
                        sb.fromdate = Date.valueOf(s);
                    } catch (Exception t) {
                        JOptionPane.showMessageDialog(null,"服务日期填写错误！");
                        return;
                    }
                    for (int i = 0; i < 10; i++) {
                        sb.pictures[i] = (ImageIcon) imsave[i].getIcon();
                    }
                    azs.serverBook = sb;
                    s = null;
                }
                if (!jiankangfromdata.getText().equals("")) {
                    Jiankang jk = new Jiankang();
                    String s = jiankangfromdata.getText().replace(".", "-");
                    try {
                        jk.fromdate = Date.valueOf(s);
                    } catch (Exception t) {
                        JOptionPane.showMessageDialog(null,"健康签发日期填写错误！");
                        return;
                    }
                    s = jiankangtodate.getText().replace(".", "-");
                    try {
                        jk.todate = Date.valueOf(s);
                    } catch (Exception t) {
                        JOptionPane.showMessageDialog(null,"健康截止日期填写错误！");
                        return;
                    }
                    for (int i = 0; i < 10; i++) {
                        jk.pictures[i] = (ImageIcon) jiankangimsave[i].getIcon();
                    }
                    azs.jiankang = jk;
                    s = null;
                }
                if (!jiashinumber.getText().equals("")) {
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
                    s = null;
                }

                if (!lunjinumber.getText().equals("")) {
                    lunji lj = new lunji();
                    lj.number = lunjinumber.getText();
                    lj.job = lunjipowers.getText();
                    String s = lunjifromdata.getText().replace(".", "-");
                    try {
                        lj.fromdate = Date.valueOf(s);
                    } catch (Exception t) {
                        JOptionPane.showMessageDialog(null, "轮机签发日期填写错误！");
                        return;
                    }
                    s = lunjitodate.getText().replace(".", "-");
                    try {
                        lj.todate = Date.valueOf(s);
                    } catch (Exception t) {
                        JOptionPane.showMessageDialog(null, "轮机截止日期填写错误！");
                        return;
                    }
                    for (int i = 0; i < 10; i++) {
                        lj.pictures[i] = (ImageIcon) lunjiimsave[i].getIcon();
                    }
                    azs.lunji = lj;
                    s = null;
                }
                if (!GNDnumber.getText().equals("")) {
                    GND g = new GND();
                    g.number = GNDnumber.getText();
                    g.job = GNDpowers.getText();
                    String s = GNDfromdata.getText().replace(".", "-");
                    try {
                        g.fromdate = Date.valueOf(s);
                    } catch (Exception t) {
                        JOptionPane.showMessageDialog(null, "GND签发日期填写错误！");
                        return;
                    }
                    s = GNDtodate.getText().replace(".", "-");
                    try {
                        g.todate = Date.valueOf(s);
                    } catch (Exception t) {
                        JOptionPane.showMessageDialog(null, "GND截止日期填写错误！");
                        return;
                    }
                    for (int i = 0; i < 10; i++) {
                        g.pictures[i] = (ImageIcon) GNDimsave[i].getIcon();
                    }
                    azs.gnd = g;
                    s = null;
                }
                if (!neihenumber.getText().equals("")) {
                    neihe nh = new neihe();
                    nh.number = neihenumber.getText();
                    nh.job = neihepowers.getText();
                    String s = neihefromdata.getText().replace(".", "-");
                    try {
                        nh.fromdate = Date.valueOf(s);
                    } catch (Exception t) {
                        JOptionPane.showMessageDialog(null, "内河签发日期填写错误！");
                        return;
                    }
                    s = neihetodate.getText().replace(".", "-");
                    try {
                        nh.todate = Date.valueOf(s);
                    } catch (Exception t) {
                        JOptionPane.showMessageDialog(null, "内河截止日期填写错误！");
                        return;
                    }
                    for (int i = 0; i < 10; i++) {
                        nh.pictures[i] = (ImageIcon) neiheimsave[i].getIcon();
                    }
                    azs.neihe = nh;
                    s = null;
                }
                if (!peixunnumber.getText().equals("")) {
                    Peixunhege peixun = new Peixunhege();
                    peixun.number = peixunnumber.getText();
                    String s = peixunfromdata.getText().replace(".", "-");
                    try {
                        peixun.fromdate = Date.valueOf(s);
                    } catch (Exception t) {
                        JOptionPane.showMessageDialog(null, "培训合格证签发日期填写错误！");
                        return;
                    }
                    for (int i = 0; i < 10; i++) {
                        if (!zhengname[i].getText().equals("")) {
                            inPeixunhege ipxh = new inPeixunhege();
                            ipxh.name = zhengname[i].getText();
                            s = zhengfromdata[i].getText().replace(".", "-");
                            try {
                                ipxh.fromdate = Date.valueOf(s);
                            } catch (Exception t) {
                                JOptionPane.showMessageDialog(null, "证书"+(i+1)+"签发日期填写错误！");
                                return;
                            }
                            s = zhengtodata[i].getText().replace(".", "-");
                            try {
                                ipxh.todate = Date.valueOf(s);
                            } catch (Exception t) {
                                JOptionPane.showMessageDialog(null, "证书"+(i+1)+"截止日期填写错误！");
                                return;
                            }
                            peixun.include.add(ipxh);
                        }
                    }
                    for (int i = 0; i < 10; i++) {
                        peixun.pictures[i] = (ImageIcon) peixunimsave[i].getIcon();
                    }
                    azs.peixunhege = peixun;
                    s = null;
                }
                deleperson();
                addperson();
                JOptionPane.showMessageDialog(null, "正在修改...");
                try {
                    Dao.dropperson(azs.ID);
                    Dao.addUserInfo(azs);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        add_all.add(add_south,BorderLayout.SOUTH);
        this.add(add_all);
    }

    public void addperson(){
        Main.add_all_row(azs);
        if(azs.haveServerBook()){
            Main.add_server_row(azs, azs.serverBook);
        }
        if(azs.haveJiankang()){
            Main.add_jiankang_row(azs, azs.jiankang);
        }
        if(azs.havejiashi()){
            Main.add_jiashi_row(azs, azs.jiashi);
        }
        if(azs.havelunji()){
            Main.add_lunji_row(azs, azs.lunji);
        }
        if(azs.haveGND()){
            Main.add_GND_row(azs, azs.gnd);
        }
        if(azs.haveneihe()){
            Main.add_neihe_row(azs, azs.neihe);
        }
        if(azs.havePeixunhege()){
            Main.add_peixun_row(azs, azs.peixunhege);
        }
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
                    ((JButton) e.getSource()).setText("");

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
                    ((JButton) e.getSource()).setText("");

                }
            });
        }
        peixunjsp = new JScrollPane(b_peixun);
        JScrollBar peixunjspbar = peixunjsp.getVerticalScrollBar();
        peixunjspbar.setUnitIncrement(30);
        return peixunjsp;
    }

}
