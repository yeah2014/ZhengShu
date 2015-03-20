import javax.swing.*;
import java.io.Serializable;

/**
 * Created by hongyeah on 2015/2/4.
 */
public class Allzhengshu extends Person implements Serializable{
    public ServerBook serverBook = null;
    public jiashi jiashi = null;
    public lunji lunji = null;
    public GND gnd = null;
    public neihe neihe = null;
    public Peixunhege peixunhege = null;
    public Jiankang jiankang = null;

    public boolean haveServerBook(){
        if(serverBook==null) return false;
        else return true;
    }

    public boolean havejiashi(){
        if(jiashi == null){
            return false;
        }
        return true;
    }

    public boolean havelunji(){
        if(lunji==null) return false;
        return true;
    }

    public boolean haveGND(){
        if(gnd == null) return false;
        return true;
    }

    public boolean haveneihe(){
        if(neihe==null) return false;
        return true;
    }


    public boolean havePeixunhege(){
        if(peixunhege==null) return false;
        else return true;
    }

    public boolean haveJiankang(){
        if(jiankang==null) return false;
        else return true;
    }

    public String toString(){
        return new String("all"+super.toString());
    }
}
