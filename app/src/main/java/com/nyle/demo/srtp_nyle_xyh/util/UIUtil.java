package com.nyle.demo.srtp_nyle_xyh.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nyle.demo.srtp_nyle_xyh.model.Position;


/**
 * Created by dd on 13-8-15.
 */
public class UIUtil
{
    public static void toast(Context context, String content)
    {
        Toast toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void alert(Context context, String content)
    {
        try
        {
            AlertDialog dialog = new AlertDialog.Builder(context).create();
            dialog.setMessage(content);
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    dialogInterface.dismiss();
                }
            });

            dialog.show();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void updateViewPosition(ViewGroup userView, int x)
    {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) userView.getLayoutParams();
        layoutParams.leftMargin = x;
        userView.setLayoutParams(layoutParams);
    }

}
