import javax.swing.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hongyeah on 2015/2/4.
 */
public class ServerBook implements Serializable{
    public String Number = null;
    public Date fromdate = null;
    public ImageIcon[] pictures = new ImageIcon[10];

    public ServerBook(){
    }

    public ServerBook(String Number, Date date){
        this.Number = Number;
        this.fromdate = date;
    }

    public Date getDate(){
        return this.fromdate;
    }

    public void setDate(Date date){
        this.fromdate = date;
    }
    public String toString(){
        return new String("服务簿"+" "+super.toString());
    }

}
