package com.nyle.demo.srtp_nyle_xyh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dengyonghui on 14/11/7.
 */
public class TimeUtil
{
    /**
     *
     * change a time from string to long
     * @param timeString : time string (yyyy/MM/dd)
     * @return time(long)
     */
    public static long timeGet(String timeString)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        try{
            date = sdf.parse(timeString);
        } catch(ParseException e) {
        }
        return  date.getTime();
    }

    /**
     *
     * get a date (only year , month, day) from time(long)
     * @param time : time
     * @return date
     */
    public static String dateGet(long time)
    {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = new Date(time);
        return format.format(d1);
    }

}
