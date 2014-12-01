package com.nyle.demo.srtp_nyle_xyh.acitivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nyle.demo.srtp_nyle_xyh.R;
import com.nyle.demo.srtp_nyle_xyh.global.Global;
import com.nyle.demo.srtp_nyle_xyh.util.TextUtil;

/**
 * Created by dengyonghui on 14/11/27.
 */
public class TaskDetailActivity extends Activity
{
    TextView taskNameShow;
    TextView contentShow;
    RunningTaskInfo runningTaskInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        initView();
        runningTaskInfoGet();
        runningTaskInfoRender();
    }

    private void runningTaskInfoRender()
    {
        TextUtil.textviewContentSet(getActivityName(runningTaskInfo.topActivity), taskNameShow);
        TextUtil.textviewContentSet(getContentShow(runningTaskInfo), contentShow);
    }

    private String getContentShow(RunningTaskInfo runningTaskInfo)
    {
        String result = "";
        result += "Base Activity :  " + getActivityName(runningTaskInfo.topActivity) + "\n\n";
        result += "Task id :  " + runningTaskInfo.id + "\n\n";
        result += "Num Activites :  " + runningTaskInfo.numActivities + "\n\n";
        result += "Num Running :  " + runningTaskInfo.numRunning + "\n\n";
        return result;
    }

    private void runningTaskInfoGet()
    {
        runningTaskInfo = Global.runningTaskInfo;
    }

    private void initView()
    {
        taskNameShow = (TextView) findViewById(R.id.task_name_show);
        contentShow = (TextView) findViewById(R.id.task_content_show);
    }

    public void clickToTurnBack(View view) { finish();}

    private String getActivityName(ComponentName topActivity)
    {
        boolean ifWrite = false;
        String result = "" + topActivity;
        String tmpStr = "";
        if(result.length() > 0)
        {
            for(int i = 0;i < result.length(); i++)
            {
                String tmp2 = "" + result.charAt(i);
                if((tmp2).matches("[/]"))
                {
                    ifWrite = true;
                    continue;
                }
                else if (tmp2.matches("[}]"))
                {
                    ifWrite = false;
                    break;
                }

                if (ifWrite)
                {
                    tmpStr+=tmp2;
                }
            }
        }
        String[] sourceStrArray = tmpStr.split("\\.");
        if (sourceStrArray.length == 0)
            return tmpStr;
        result = sourceStrArray[sourceStrArray.length - 1];
        return  result;
    }
}
