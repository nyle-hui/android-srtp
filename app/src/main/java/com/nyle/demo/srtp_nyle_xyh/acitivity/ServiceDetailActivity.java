package com.nyle.demo.srtp_nyle_xyh.acitivity;

import android.app.Activity;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nyle.demo.srtp_nyle_xyh.R;
import com.nyle.demo.srtp_nyle_xyh.global.Global;
import com.nyle.demo.srtp_nyle_xyh.util.TextUtil;
import com.nyle.demo.srtp_nyle_xyh.util.TimeUtil;

/**
 * Created by dengyonghui on 14/11/27.
 */
public class ServiceDetailActivity extends Activity
{
    TextView serviceNameShow;
    TextView contentShow;
    RunningServiceInfo runningServiceInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);

        initView();
        runningTaskInfoGet();
        runningTaskInfoRender();
    }

    private void runningTaskInfoRender()
    {
        TextUtil.textviewContentSet(getActivityName(runningServiceInfo.service), serviceNameShow);
        TextUtil.textviewContentSet(getContentShow(runningServiceInfo), contentShow);
    }

    private String getContentShow(RunningServiceInfo runningTaskInfo)
    {
        String result = "";
        result += "service :  " + runningServiceInfo.service + "\n\n";
        result += "所属进程 :  " + runningServiceInfo.process + "\n\n";
        result += "客户端连接次数 :  " + runningTaskInfo.clientCount + "\n\n";
        result += "首次连接时间 :  " + TimeUtil.dateGet(runningTaskInfo.activeSince) + "\n\n";
        result += "最后活动时间 :  " + TimeUtil.dateGet(runningTaskInfo.lastActivityTime) + "\n\n";
        result += "Crash 次数 :  " + runningTaskInfo.crashCount + "\n\n";
        result += "用户ID :  " + runningTaskInfo.uid + "\n\n";
        return result;
    }

    private void runningTaskInfoGet()
    {
        runningServiceInfo = Global.runningServiceInfo;
    }

    private void initView()
    {
        serviceNameShow = (TextView) findViewById(R.id.service_name_show);
        contentShow = (TextView) findViewById(R.id.service_content_show);
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
