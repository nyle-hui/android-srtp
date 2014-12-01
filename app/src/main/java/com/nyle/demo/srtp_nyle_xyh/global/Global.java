package com.nyle.demo.srtp_nyle_xyh.global;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;

import com.nyle.demo.srtp_nyle_xyh.util.PackageUtil;
import com.nyle.demo.srtp_nyle_xyh.util.ProcessMemoryUtil;

/**
 * Created by dengyonghui on 14/11/25.
 */
public class Global
{
    static public PackageUtil packageUtil;
    static public ProcessMemoryUtil processMemoryUtil;
    static public PackageManager pm;

    public static ActivityManager.RunningTaskInfo runningTaskInfo;
    public static ActivityManager.RunningServiceInfo runningServiceInfo;

    public static PackageManager getPackageManager(Context context)
    {
        if (pm == null)
        {
            pm = context.getApplicationContext().getPackageManager();
        }
        return pm;
    }
}
