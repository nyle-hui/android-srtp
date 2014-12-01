package com.nyle.demo.srtp_nyle_xyh.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nyle.demo.srtp_nyle_xyh.R;
import com.nyle.demo.srtp_nyle_xyh.util.MathUtil;
import com.nyle.demo.srtp_nyle_xyh.util.TextUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dengyonghui on 14/11/27.
 */
public class MemoryShowView extends RelativeLayout
{
    int memory = 20000; // memory
    int totalMemory = 2048 * 1024; // all memory in the mechine 2G
    int num = 0;
    Timer timer;

    MemShowBackgroundView memShowBackgoundView;
    TextView memPecentShow;
    TextView memNumShow;

    public MemoryShowView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.memory_show, this);
        memShowBackgoundView = (MemShowBackgroundView)findViewById(R.id.back_ground);
        memPecentShow = (TextView) findViewById(R.id.mem_percent_show);
        memNumShow = (TextView) findViewById(R.id.mem_num_show);
    }

    final Handler handler = new Handler()
    {
        public void handleMessage(Message msg) {
                switch (msg.what)
                {
                    case 1:
                         if ( num / 1000f > memory / (float) totalMemory)
                         {
                             memShowBackgoundView.setPercentage(memory / (float) totalMemory);
                             timer.cancel();
                         }
                         else
                         {
                             num++;
                             memShowBackgoundView.setPercentage(num / 1000f);
                         }
                         break;
                 }
                super.handleMessage(msg);
            }
    };

    public void setMemory(int memory)
    {
        this.memory = memory;
        num = 0;
        TimerTask task = new TimerTask(){
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
                }
            };
        timer = new Timer(true);
        timer.schedule(task, 500, 20);

        TextUtil.textviewContentSet(MathUtil.getPercentString(memory / (float) totalMemory), memPecentShow);
        int tmp = (int)(memory / 1024f) * 100;
        TextUtil.textviewContentSet( tmp/100f + "M", memNumShow);
    }
}
