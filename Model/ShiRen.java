import javax.swing.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hongyeah on 2015/2/4.
 */
public class ShiRen implements Serializable{
    public String number = null;
    public String job = null;
    public Date fromdate = null;
    public Date todate = null;
    public ImageIcon[] pictures = new ImageIcon[10];

    public String toString(){
        return new String("适任证书"+" "+super.toString());
    }
}


class jiashi extends ShiRen{
    public jiashi(){
    }
    public jiashi(String number,String job,Date fromdate,Date todate){
        this.number = number;
        this.job = job;
        this.fromdate = fromdate;
        this.todate = todate;
    }

    public String toString(){
        return new String("驾驶"+" "+super.toString());
    }
}

class lunji extends ShiRen{
    public lunji(){

    }
    public lunji(String number,String job,Date fromdate,Date todate){
        this.number = number;
        this.job = job;
        this.fromdate = fromdate;
        this.todate = todate;
    }
    public String toString(){
        return new String("轮机"+" "+super.toString());
    }
}
class GND extends ShiRen{
    public GND(){

    }
    public GND(String number,String job,Date fromdate,Date todate){
        this.number = number;
        this.job = job;
        this.fromdate = fromdate;
        this.todate = todate;
    }
}
class neihe extends ShiRen{
    public neihe(){

    }

    public neihe(String number,String job,Date fromdate,Date todate){
        this.number = number;
        this.job = job;
        this.fromdate = fromdate;
        this.todate = todate;
    }
    public String toString(){
        return new String("内河"+" "+super.toString());
    }
}
