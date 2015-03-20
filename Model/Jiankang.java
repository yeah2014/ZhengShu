import javax.swing.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hongyeah on 2015/2/4.
 */
public class Jiankang implements Serializable{
    public Date fromdate = null;
    public Date todate = null;
    public ImageIcon[] pictures = new ImageIcon[10];

    public Jiankang(){

    }
    public Jiankang(Date fromdate, Date todate){
        this.fromdate = fromdate;
        this.todate = todate;
    }


    public String toString(){

        return new String(super.toString()+"健康证");
    }
}
