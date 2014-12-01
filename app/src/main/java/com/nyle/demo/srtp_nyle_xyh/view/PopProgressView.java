package com.nyle.demo.srtp_nyle_xyh.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.nyle.demo.srtp_nyle_xyh.R;

/**
 * Created by dd on 13-8-15.
 */
public class PopProgressView
{
    private Context context;
    private View parentView;
    private TextView popTipTextView;
    private PopupWindow popupWindow;

    public PopProgressView(Context context, View parentView)
    {
        this.context = context;
        this.parentView = parentView;

        render();
    }

    public void render()
    {
        View popProgressView = LayoutInflater.from(context).inflate(R.layout.pop_progress, null);
        popTipTextView = (TextView) popProgressView.findViewById(R.id.popTipTextView);
        popupWindow = new PopupWindow(popProgressView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void setTip(String tip)
    {
        if (tip == null)
        {
            tip = "";
        }
        popTipTextView.setText(tip);
    }

    public void show()
    {
        try
        {
            popupWindow.showAtLocation(parentView, Gravity.CENTER, -popupWindow.getWidth() / 2, -popupWindow.getHeight() / 2);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(false);
            popupWindow.update();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void hide()
    {
        try
        {
            popupWindow.dismiss();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
