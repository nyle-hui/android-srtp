package com.nyle.demo.srtp_nyle_xyh.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.nyle.demo.srtp_nyle_xyh.R;
import com.nyle.demo.srtp_nyle_xyh.global.Const;
import com.nyle.demo.srtp_nyle_xyh.global.Global;
import com.nyle.demo.srtp_nyle_xyh.model.BasicProgram;
import com.nyle.demo.srtp_nyle_xyh.model.DetailProgram;

import java.util.Calendar;

/**`
 * Created by dengyonghui on 14/11/27.
 */
public class ApplicationProgramUtil
{
    public static BasicProgram basicProgramUtilSimpleInfo(Context context, int procId, String procNameString) {

        BasicProgram program = new BasicProgram();
        program.setProcessName(procNameString);
        program.procId = procId;

        // 根据进程名，获取应用程序的ApplicationInfo对象
        ApplicationInfo tempAppInfo = Global.packageUtil.getApplicationInfo(procNameString);

        PackageManager packageManager = Global.getPackageManager(context);

        if (tempAppInfo != null) {
            // 为进程加载图标和程序名称
            program.setIcon(tempAppInfo.loadIcon(packageManager));
            program.setProgramName(tempAppInfo.loadLabel(packageManager).toString());
        }
        else {
            // 如果获取失败，则使用默认的图标和程序名
            program.setIcon(context.getResources().getDrawable(R.drawable.icon_avater));
            program.setProgramName(procNameString);
            Log.i(Const.TAG_SYSTEMASSIST, procNameString);
        }

        String[] memInfo = Global.processMemoryUtil.getMemInfoByPid(procId);
        Log.v("nyle", "Time --- > " + Calendar.getInstance().getTimeInMillis());
        program.memStringList(memInfo);

        return program;
    }

    public static DetailProgram buildProgramComplexInfo(Context context, String procNameString) {

        DetailProgram complexProgramUtil = new DetailProgram();
        // 根据进程名，获取应用程序的ApplicationInfo对象
        ApplicationInfo tempAppInfo = Global.packageUtil.getApplicationInfo(procNameString);
        if (tempAppInfo == null) {

            Log.i("nyle", "tempAppInfo null" + "  proceName : " + procNameString);
            return null;
        }

        PackageInfo tempPkgInfo = null;
        try {
            tempPkgInfo = Global.getPackageManager(context).getPackageInfo(
                    tempAppInfo.packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_ACTIVITIES
                            | PackageManager.GET_SERVICES | PackageManager.GET_PERMISSIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (tempPkgInfo == null) {
            return null;
        }

        complexProgramUtil.setProcessName(procNameString);
        complexProgramUtil.setCompanyName(context.getString(R.string.no_use));
        complexProgramUtil.setVersionName(tempPkgInfo.versionName);
        complexProgramUtil.setVersionCode(tempPkgInfo.versionCode);
        complexProgramUtil.setDataDir(tempAppInfo.dataDir);
        complexProgramUtil.setSourceDir(tempAppInfo.sourceDir);

     // 以下注释部分的信息暂时获取不到
//        complexProgramUtil.setFirstInstallTime(tempPkgInfo.firstInstallTime);
//        complexProgramUtil.setLastUpdateTime(tempPkgInfo.lastUpdateTime);
//        complexProgramUtil.setCodeSize(packageStats.codeSize);
//        complexProgramUtil.setDataSize(packageStats.dataSize);
//        complexProgramUtil.setCacheSize(packageStats.cacheSize);
//        complexProgramUtil.setExternalDataSize(0);
//        complexProgramUtil.setExternalCacheSize(0);
//        complexProgramUtil.setExternalMediaSize(0);
//        complexProgramUtil.setExternalObbSize(0);

        // 获取以下三个信息，需要为PackageManager进行授权(packageManager.getPackageInfo()方法)
        complexProgramUtil.setUserPermissions(tempPkgInfo.requestedPermissions);
        complexProgramUtil.packageName = tempPkgInfo.packageName;
        complexProgramUtil.setServices(tempPkgInfo.services);
        complexProgramUtil.setActivities(tempPkgInfo.activities);

        return complexProgramUtil;
    }
}
