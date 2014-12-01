package com.nyle.demo.srtp_nyle_xyh.util;

/**
 * Created by dengyonghui on 14/11/27.
 */
public class MathUtil
{
    //不保留小数
    public static String getPercentString(float f)
    {
        int temp = (int)(f * 100);
        return  temp + "%";
    }

    //保留1位小数
    public static String getPercentStringOneRight(float f)
    {
        int temp = (int)(f * 1000);
        double result = (double)temp / 10;
        return  result + "%";
    }
}
