import javax.swing.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

/**
 * Created by hongyeah on 2015/2/4.
 */
public class Peixunhege  implements Serializable{
    public String number = null;
    public Date fromdate = null;
    public Vector<inPeixunhege> include = new Vector<inPeixunhege>();
    public ImageIcon[] pictures = new ImageIcon[10];
    public Peixunhege(){

    }

    public Peixunhege(String number, Date fromdate){
        this.number = number;
        this.fromdate = fromdate;
    }

    public void addzhengshu(inPeixunhege e){
        include.add(e);
    }

    public Vector<inPeixunhege> getzhengshu(){
        return include;
    }

    public int getside(){
        return include.size();
    }

    public String toString(){
        return new String("船员培训合格证"+super.toString()+" "+getside()+"个证");
    }
}

class inPeixunhege extends Person implements Serializable{
    public String name = null;
    public Date fromdate = null;
    public Date todate = null;

    public inPeixunhege(){

    }

    public inPeixunhege(String name,Date fromdate,Date todate){
        this.name = name;
        this.fromdate = fromdate;
        this.todate = todate;
    }

    public String toString(){
        return new String(name+" "+super.toString());
    }
}