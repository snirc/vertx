package snir.code.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {


    public static String getCurrentDate(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        return sdf.format(date);
    }

    public static String getLastDateTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Date date=new Date();
        return sdf.format(date);
    }
    public static String getLastDateTimeWithoutMills(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        return sdf.format(date);
    }
    public static String getLastTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss:SSS");
        Date date=new Date();
        return sdf.format(date);
    }

    public static Date parseDateTime(String dateString){
        //this is the format we receive from the client, the matcher knows how to deal with it
        SimpleDateFormat sdf=new SimpleDateFormat ("yyyyMMdd HH:mm");

        //this is the format in which dates are saved in the database
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date task_time=null;
        String task_time_fix_string=null;
        Date task_time_fix=null;
        try {
            //first we parse the date with the format we received it in
            task_time=(Date)(sdf.parse(dateString));

            //after that we create a string in the new format, the one we want to be saved in the database
            task_time_fix_string = sdf2.format(task_time);

            //we then take the string and parse it to a date object so we can return it
            task_time_fix = sdf2.parse(task_time_fix_string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return task_time_fix;
    }

    public static Date parseDate(String dateString){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date task_time=null;
        try {
            task_time=(Date)(sdf.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return task_time;
    }

    public static String getTime(String dateString){
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
        Date date=null;
        try {
            date=(Date)(sdf.parse(dateString));

            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            return String.valueOf(date.getTime()/1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseDateTime(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        try {
            return sdf.format(date);
        } catch (Exception e) {
            return "0000-00-00 00:00:00:000";
        }
    }

    public static String parseDateTime(long milliSeconds){
        Date date=new Date(milliSeconds);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        try {
            return sdf.format(date);
        } catch (Exception e) {
            return "0000-00-00 00:00:00:000";
        }
    }

    public static String parseDateTimeFromSeconds(long seconds){
        Date date=new Date(seconds*1000);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        try {
            return sdf.format(date);
        } catch (Exception e) {
            return "0000-00-00 00:00:00:000";
        }
    }

    public static Date getDatePlusYear() {
        Calendar calendar=Calendar.getInstance();
//        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);
        calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+1);
        return calendar.getTime();
    }

    public static Date getEndofDay() {
        Calendar calendar=Calendar.getInstance();
//        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);
        calendar.set(Calendar.HOUR,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,00);
        calendar.set(Calendar.MILLISECOND,000);
        return calendar.getTime();
    }

    public static String getExpirationEndofDate(){
        Date today=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        return sdf.format(today)+" 23:59";
    }

    public static void main(String[] args) {
        System.out.println(DateUtils.parseDateTime(System.currentTimeMillis()));
        System.out.println(System.currentTimeMillis());
        System.out.println(DateUtils.parseDateTimeFromSeconds(1521482400));
        System.out.println(DateUtils.parseDateTimeFromSeconds(1521486000));
        System.out.println(DateUtils.parseDateTimeFromSeconds(1521489600));
    }

}
