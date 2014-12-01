package com.nyle.demo.srtp_nyle_xyh.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.nyle.demo.srtp_nyle_xyh.global.Global;

import java.util.List;

/**
 * Created by dengyonghui on 14/10/20.
 */
public class PackageUtil
{
    // ApplicationInfo 类，保存了普通应用程序的信息，主要是指Manifest.xml中application标签中的信息
    public List<ApplicationInfo> allAppList;

    public PackageUtil(Context context) {
        // 通过包管理器，检索所有的应用程序（包括卸载）与数据目录
        PackageManager pm = Global.getPackageManager(context);
        allAppList = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        pm.getInstalledPackages(0);
    }

    /**
     * 通过一个程序名返回该程序的一个ApplicationInfo对象
     * param name 程序名
     * @return ApplicationInfo
     */
    public ApplicationInfo getApplicationInfo(String appName) {
        if (appName == null) {
            return null;
        }

        for (ApplicationInfo appinfo : allAppList) {
            if (appName.equals(appinfo.processName)) {
                return appinfo;
            }
        }
        return null;
    }
}
