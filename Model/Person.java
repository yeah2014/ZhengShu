import javax.swing.*;
import java.io.Serializable;
import java.util.Vector;

/**
 * Created by hongyeah on 2015/2/4.
 */
public class Person implements Serializable{
    public String name = null;
    public String sex = null;
    public String ID = null;
    public String phonenumber = null;
    public ImageIcon IDcard = null;

    public String toString(){
        return new String(name+" "+sex+" "+ID+" "+phonenumber);
    }

    public void setsourse(String name,String ID){
        this.name = name;
        this.ID = ID;
    }

}
