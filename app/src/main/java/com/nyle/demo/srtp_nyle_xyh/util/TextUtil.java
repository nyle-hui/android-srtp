package com.nyle.demo.srtp_nyle_xyh.util;

import android.widget.TextView;

/**
 * Created by dengyonghui on 14/11/7.
 */
public class TextUtil
{
    /**
     *
     * make a textview to show content if content is not null
     * @param content : content will to show
     * @param textView : textview
     * @return time(long)
     */
    public static boolean textviewContentSet(String content, TextView textView)
    {
        if (textView == null ) return false;
        if (content == null || content.length() <= 0)
        {
            return false;
        }
        else
        {
            textView.setText(content);
            return true;
        }
    }


    /**
     *
     * deal if the string is null
     * @param string : if the string test
     * @return boolean
     */
    public static boolean ifNull(String string)
    {
        if (string  == null || string.length() <= 0)
        {
            return true;
        }
        else return false;
    }

    /**
     *
     * deal if the spinner is choose
     * @param string : the content of spinner now
     * @param init : the content of spinner at begin
     * @return boolean
     */
    public static boolean ifSpinnerNull(String string, String init)
    {
        if (string  == null || string.length() <= 0 || string.equals(init))
        {
            return true;
        }
        else return false;
    }
}
