package com.nyle.demo.srtp_nyle_xyh.model;

import android.content.pm.ActivityInfo;
import android.content.pm.ServiceInfo;
import android.text.format.DateFormat;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dengyonghui on 14/11/27.
 */

public class DetailProgram implements Serializable
{

    private static final long serialVersionUID = 1L;
    /*
     * 定义应用程序的扩展信息部分
     */
    private int pid;
    private String processName;                        // 程序运行的进程名

    private String companyName;                        // 公司名称
    private int versionCode;                        // 版本代号
    private String versionName;                        // 版本名称

    private String dataDir;                            // 程序的数据目录
    private String sourceDir;                        // 程序包的源目录

    private String firstInstallTime;                // 第一次安装的时间
    private String lastUpdateTime;                    // 最近的更新时间

    private String userPermissions;                    // 应用程序的权限
    private String activities;                        // 应用程序包含的Activities
    private String services;                        // 应用程序包含的服务

    // android.content.pm.PackageState类的包信息
    // 此处只是安装包的信息
    public String packageName;
    private String codeSize;
    private long dataSize;
    private long cacheSize;
    private long externalDataSize;
    private long externalCacheSize;
    private long externalMediaSize;
    private long externalObbSize;

    public DetailProgram() {
        pid = 0;
        processName = "";
        companyName = "";
        versionCode = 0;
        versionName = "";
        dataDir = "";
        sourceDir = "";
        firstInstallTime = "";
        lastUpdateTime = "";
        userPermissions = "";
        activities = "";
        services = "";

        initPackageSize();
    }

    private void initPackageSize() {
        codeSize = "0.00";
        dataSize = 0;
        cacheSize = 0;
        externalCacheSize = 0;
        externalDataSize = 0;
        externalMediaSize = 0;
        externalObbSize = 0;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyString) {
        this.companyName = companyString;
    }

    public String getFirstInstallTime() {
        if (firstInstallTime == null || firstInstallTime.length() <= 0) {
            firstInstallTime = "null";
        }
        return firstInstallTime;
    }

    public void setFirstInstallTime(long firstInstallTime) {
        this.firstInstallTime = DateFormat.format(
                "yyyy-MM-dd", firstInstallTime).toString();
    }

    public String getLastUpdateTime() {
        if (lastUpdateTime == null || lastUpdateTime.length() <= 0) {
            lastUpdateTime = "null";
        }
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = DateFormat.format(
                "yyyy-MM-dd", lastUpdateTime).toString();
    }

    public String getActivities() {
        if (activities == null || activities.length() <= 0) {
            activities = "null";
        }
        return activities;
    }

    public void setActivities(ActivityInfo[] activities) {
        this.activities = Array2String(activities);
    }

    public String getUserPermissions() {
        if (userPermissions == null || userPermissions.length() <= 0) {
            userPermissions = "null";
        }
        return userPermissions;
    }

    public void setUserPermissions(String[] userPermissions) {
        this.userPermissions = Array2String(userPermissions);
    }

    public String getUserPermissionsFormat()
    {
        if (userPermissions == null || userPermissions.length() <= 0) {
            userPermissions = "没有申请相关权限";
        }

        String result = "";

        Map<String, String> permissionMap = new HashMap<String, String>();
        permissionMap.put("android.permission.WRITE_EXTERNAL_STORAGE", "写存储卡");
        permissionMap.put("android.permission.READ_EXTERNAL_STORAGE", "读存储卡");
        permissionMap.put("android.permission.MOUNT_UNMOUNT_FILESYSTEMS", "装载和卸载文件系统");
        permissionMap.put("android.permission.CHANGE_WIFI_STATE", "改变wifi状态");
        permissionMap.put("android.permission.ACCESS_WIFI_STATE", "访问wifi状态");
        permissionMap.put("android.permission.INTERNET", "网络连接");
        permissionMap.put("android.permission.ACCESS_NETWORK_STATE", "访问网络状态");
        permissionMap.put("android.permission.CHANGE_NETWORK_STATE", "改变网络状态");
        permissionMap.put("android.permission.ACCESS_FINE_LOCATION", "访问精准的GPS位置");
        permissionMap.put("android.permission.ACCESS_COARSE_LOCATION", "基于网络的粗略的位置");
        permissionMap.put("android.permission.ACCESS_MOCK_LOCATION", "获取模拟定位信息");
        permissionMap.put("android.permission.RECEIVE_BOOT_COMPLETED", "开机启动");
        permissionMap.put("android.permission.CALL_PHONE", "拨打电话");
        permissionMap.put("android.permission.CALL_PRIVILEGED", "允许程序拨打电话");
        permissionMap.put("android.permission.BROADCAST_SMS", "广播短信");
        permissionMap.put("android.permission.READ_SMS", "读短信");
        permissionMap.put("android.permission.SEND_SMS", "发短信");
        permissionMap.put("android.permission.RECEIVE_SMS", "收短信");
        permissionMap.put("android.permission.WRITE_SMS", "写短信");
        permissionMap.put("android.permission.READ_CONTACTS", "读联系人");
        permissionMap.put("android.permission.WRITE_CONTACTS", "编辑联系人");
        permissionMap.put("android.permission.READ_CALL_LOG", "读通话记录");
        permissionMap.put("android.permission.WRITE_CALL_LOG", "修改通话记录");
        permissionMap.put("android.permission.INSTALL_SHORTCUT", "安装快捷方式");
        permissionMap.put("android.permission.UNINSTALL_SHORTCUT", "卸载快捷方式");
        permissionMap.put("android.permission.RECORD_AUDIO", "录音");
        permissionMap.put("android.permission.MODIFY_AUDIO_SETTINGS", "修改声音设置");
        permissionMap.put("android.permission.VIBRATE", "振动");
        permissionMap.put("android.permission.MOUNT_UNMOUNT_FILESYSTEMS", "装载和卸载文件系统");
        permissionMap.put("android.permission.READ_LOGS", "查阅敏感日志数据");
        permissionMap.put("android.permission.BROADCAST_STICKY", "发送持久广播");
        permissionMap.put("android.permission.WRITE_SETTINGS", "修改全局系统设置");
        permissionMap.put("android.permission.WAKE_LOCK", "唤醒");
        permissionMap.put("android.permission.RESTART_PACKAGES", "重启程序");
        permissionMap.put("android.permission.KILL_BACKGROUND_PROCESSES", "关闭程序");
        permissionMap.put("android.permission.PLUGIN", "浏览器插件开发");
        permissionMap.put("android.permission.DISABLE_KEYGUARD", "禁用键盘锁");
        permissionMap.put("android.permission.WRITE_HISTORY_BOOKMARKS", "写入浏览器收藏夹");
        permissionMap.put("android.permission.READ_HISTORY_BOOKMARKS", "浏览器历史记录读取");
        permissionMap.put("android.permission.CAMERA", "照相机功能");
        permissionMap.put("android.permission.READ_PHONE_STATE", "读取手机状态");

        for (String a : permissionMap.keySet())
        {
            result += !userPermissions.contains(a) ? "" : permissionMap.get(a) + "\n";
        }

        return result;
    }

    public String getServices() {
        if (services == null || services.length() <= 0) {
            services = "null";
        }
        return services;
    }

    public void setServices(ServiceInfo[] services) {
        this.services = Array2String(services);
    }

    public String getProcessName() {
        if (processName == null || processName.length() <= 0) {
            processName = "null";
        }
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getDataDir() {
        if (dataDir == null || dataDir.length() <= 0) {
            dataDir = "null";
        }
        return dataDir;
    }

    public void setDataDir(String dataDir) {
        this.dataDir = dataDir;
    }

    public String getSourceDir() {
        if (sourceDir == null || sourceDir.length() <= 0) {
            sourceDir = "null";
        }
        return sourceDir;
    }

    public void setSourceDir(String sourceDir) {
        this.sourceDir = sourceDir;
    }

    /*
     * 三个重载方法，参数不同，调用不同的方法，用于将object数组转化成要求的字符串
     */
    // 用户权限信息
    public String Array2String(String[] array) {

        String resultString = "";
        if (array != null && array.length > 0) {
            resultString = "";
            for (int i = 0; i < array.length; i++) {
                resultString += array[i];
                if (i < (array.length - 1)) {
                    resultString += "\n";
                }
            }
        }
        return resultString;
    }

    // 服务信息
    public String Array2String(ServiceInfo[] array) {
        String resultString = "";
        if (array != null && array.length > 0) {
            resultString = "";
            for (int i = 0; i < array.length; i++) {
                if (array[i].name == null) {
                    continue;
                }
                resultString += array[i].name.toString();
                if (i < (array.length - 1)) {
                    resultString += "\n";
                }
            }
        }
        return resultString;
    }

    // 活动信息
    public String Array2String(ActivityInfo[] array) {
        String resultString = "";
        if (array != null && array.length > 0) {
            resultString = "";
            for (int i = 0; i < array.length; i++) {
                if (array[i].name == null) {
                    continue;
                }
                resultString += array[i].name.toString();
                if (i < (array.length - 1)) {
                    resultString += "\n";
                }
            }
        }
        return resultString;
    }

    public String getCodeSize() {
        return codeSize;
    }

    public void setCodeSize(long codeSize) {
        DecimalFormat df = new DecimalFormat("###.00");
        this.codeSize = df.format((double)(codeSize/1024.0));
    }

    public long getDataSize() {
        return dataSize;
    }

    public void setDataSize(long dataSize) {
        this.dataSize = dataSize;
    }

    public long getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(long cacheSize) {
        this.cacheSize = cacheSize;
    }

    public long getExternalDataSize() {
        return externalDataSize;
    }

    public void setExternalDataSize(long externalDataSize) {
        this.externalDataSize = externalDataSize;
    }

    public long getExternalCacheSize() {
        return externalCacheSize;
    }

    public void setExternalCacheSize(long externalCacheSize) {
        this.externalCacheSize = externalCacheSize;
    }

    public long getExternalMediaSize() {
        return externalMediaSize;
    }

    public void setExternalMediaSize(long externalMediaSize) {
        this.externalMediaSize = externalMediaSize;
    }

    public long getExternalObbSize() {
        return externalObbSize;
    }

    public void setExternalObbSize(long externalObbSize) {
        this.externalObbSize = externalObbSize;
    }

    public String getPackageSize() {
        String resultString = "";
        resultString = "Code Size: " + codeSize + "KB\n"
                + "Data Size: " + dataSize + "KB\n"
                + "Cache Size: " + cacheSize + "KB\n"
                + "External Data Size: " + externalDataSize + "KB\n"
                + "External Cache Size: " + externalCacheSize + "KB\n"
                + "External Media Size: " + externalMediaSize + "KB\n"
                + "External Obb Size: " + externalObbSize + "KB";
        return resultString;
    }
}
