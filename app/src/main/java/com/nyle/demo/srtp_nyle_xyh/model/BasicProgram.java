package com.nyle.demo.srtp_nyle_xyh.model;

import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.nyle.demo.srtp_nyle_xyh.util.ProcessMemoryUtil;

import java.io.Serializable;

/**
 * Created by dengyonghui on 14/11/27.
 */
public class BasicProgram implements Serializable
{
    /*
     * 定义应用程序的简要信息部分
     */
    public transient Drawable icon;                            // 程序图标
    public String programName;                        // 程序名称
    public String processName;
    public int procId;
    public String[] memInfoList;

    public BasicProgram() {
        icon = null;
        programName = "";
        processName = "";
        memInfoList = null;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public void memStringList(String[] memInfo)
    {
        memInfoList = memInfo;
    }

    public int getMemory()
    {
        String tmp = memInfoList[ProcessMemoryUtil.INDEX_RSS];
        String tmpStr = "";
        if(tmp.length() > 0)
        {
            for(int i = 0;i < tmp.length(); i++)
            {
                String tmp2 = "" + tmp.charAt(i);
                if((tmp2).matches("[0-9.]")){
                    tmpStr+=tmp2;
                }
            }
        }
        tmp = tmpStr;

        return Integer.parseInt(tmp);
    }
}
