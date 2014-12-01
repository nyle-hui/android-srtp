package com.nyle.demo.srtp_nyle_xyh.acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.nyle.demo.srtp_nyle_xyh.R;
import com.nyle.demo.srtp_nyle_xyh.global.Const;
import com.nyle.demo.srtp_nyle_xyh.global.Global;
import com.nyle.demo.srtp_nyle_xyh.task.BasicTask;
import com.nyle.demo.srtp_nyle_xyh.task.ProcessMemoryGetTask;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dengyonghui on 14/11/25.
 */
public class WelcomeActivity extends Activity implements BasicTask.TaskEventListener
{

    final static int maxNum = 70;
    TextView welcomeShow;
    int num = 0;
    Timer timer;

    int changeToMainNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeShow = (TextView) findViewById(R.id.welcome);

        processMemoryGet();

        TimerTask task = new TimerTask(){
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };
        timer = new Timer(true);
        timer.schedule(task, 500, 20);
    }

    private void processMemoryGet()
    {
        ProcessMemoryGetTask processMemoryGetTask = new ProcessMemoryGetTask(this, null, this, Global.packageUtil, Global.processMemoryUtil);
        processMemoryGetTask.execute();
    }

    @Override
    public void onPostExecuteListener(BasicTask task)
    {
        if (task instanceof ProcessMemoryGetTask)
        {
            ProcessMemoryGetTask processMemoryGetTask = (ProcessMemoryGetTask) task;
            if (processMemoryGetTask.packageUtil != null && processMemoryGetTask.processMemoryUtil != null)
            {
                Global.packageUtil = processMemoryGetTask.packageUtil;
                Global.processMemoryUtil = processMemoryGetTask.processMemoryUtil;
                mainActivityShow();
            }
        }
    }

    //change to main activity
    private void mainActivityShow()
    {
        changeToMainNum ++;
        if (changeToMainNum == 2)
        {
            Intent intent = new Intent();
            intent.setClass(WelcomeActivity.this, MainActivity.class);
            startActivityForResult(intent, Const.FROM_ACTIVITY_WELCOMEACTIVITY);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == Const.FROM_ACTIVITY_WELCOMEACTIVITY)
        {
            finish();
        }
    }

    final Handler handler = new Handler()
    {
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 1:
                   if (num == maxNum)
                   {
                       num++;
                       timer.cancel();
                       welcomeShow.setTextColor(0xff000000);
                       mainActivityShow();
                   }
                   else
                   {
                       num++;
                       int color = 0xff * num / maxNum * 0x1000000;
                       welcomeShow.setTextColor(color);
                   }
                   break;
            }
            super.handleMessage(msg);
        }
    };

}
