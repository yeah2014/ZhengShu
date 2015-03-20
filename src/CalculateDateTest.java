import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalculateDateTest {

    public static void main(String[] args) {

        DateCalculate dateCalculate = DateCalculate.calculate("2014-03-17", "2013-02-13");
        System.out.println("月差为: " + dateCalculate.getDayss());
        System.out.println("天差为: " + dateCalculate.dayss);
        System.out.println();


    }

}

/**
 * @author lxbccsu
 *日期比较差值不包括起始日期,包括最后日期
 */
class DateCalculate{

    public  long dayss;
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static DateCalculate calculate(String startdate, String endDate){
        try {
            return calculate(dateFormat.parse(startdate),dateFormat.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算差值,注意 endDate > startDate
     * @param startDate
     * @param endDate
     * @return
     */
    public static DateCalculate calculate(Date startDate, Date endDate){
        DateCalculate dataCalculate = new DateCalculate();
        Calendar firstDay = Calendar.getInstance();
        Calendar lastDay = Calendar.getInstance();
        firstDay.setTime(startDate);
        lastDay.setTime(endDate);

        //算出天数总差值
        long allDays = ((lastDay.getTimeInMillis()) - (firstDay.getTimeInMillis()))/(1000*24*60*60);
        dataCalculate.setdayss(allDays);

        return dataCalculate;

    }


    public void setdayss(long dayss){
        this.dayss = dayss;
    }
    public long getDayss(){
        return dayss;
    }
}
