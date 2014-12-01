package com.nyle.demo.srtp_nyle_xyh.util;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dengyonghui on 14/10/27.
 */
public class ProcessMemoryUtil
{
    public static final int INDEX_FIRST = -1;
    public static final int INDEX_PID = INDEX_FIRST + 1;
    public static final int INDEX_CPU = INDEX_FIRST + 3;
    public static final int INDEX_STAT = INDEX_FIRST + 3;
    public static final int INDEX_THR = INDEX_FIRST + 4;
    public static final int INDEX_VSS = INDEX_FIRST + 5;
    public static final int INDEX_RSS = INDEX_FIRST + 7;
    public static final int INDEX_PCY = INDEX_FIRST + 7;
    public static final int INDEX_UID = INDEX_FIRST + 8;
    public static final int INDEX_NAME = INDEX_FIRST + 10;
    public static final int Length_ProcStat = 9;

//    "CPU:" + item[INDEX_CPU]
//    "  Mem:" + item[INDEX_RSS]

    private List<String[]> PMUList = null;

    public ProcessMemoryUtil() {

    }

    public List<String[]> getPMUList()
    {
        return PMUList;
    }

    private String getProcessRunningInfo() {
        Log.i("fetch_process_info", "start. . . . ");
        String result = null;
        CMDExecute cmdexe = new CMDExecute();
        try {
            String[] args = {"/system/bin/top", "-n", "1" ,"-s", "rss"};
            result = cmdexe.run(args, "/system/bin/");
            Log.i("nyle", "\n" + result);
        } catch (IOException ex) {
            Log.i("fetch_process_info", "ex=" + ex.toString());
        }
        return result;
    }

    private int parseProcessRunningInfo(String infoString) {
        String tempString = "";
        boolean bIsProcInfo = false;

        String[] rows = null;
        String[] columns = null;
        rows = infoString.split("[\n]+");        // 使用正则表达式分割字符串

        for (int i = 0; i < rows.length; i++) {
            tempString = rows[i];
            if (!tempString.contains("PID") && !tempString.contains("User"))
            {
                if (bIsProcInfo == true)
                {
                    tempString = tempString.trim();
                    columns = tempString.split("[ ]+");
                    if (columns.length == 10)
                        PMUList.add(columns);
                }
            }
            else
            {
                bIsProcInfo = true;
            }
        }

        return PMUList.size();
    }

    // 初始化所有进程的CPU和内存列表，用于检索每个进程的信息
    public void initPMUtil() {
        PMUList = new ArrayList<String[]>();
        String resultString = getProcessRunningInfo();
        parseProcessRunningInfo(resultString);
    }

    // 根据进程名的内存信息 （获取CPU和内存信息）
    public String[] getMemInfoByName(String procName) {
        String[] result = null;

        String tempString = "";
        for (Iterator<String[]> iterator = PMUList.iterator(); iterator.hasNext();) {
            String[] item = (String[]) iterator.next();
            tempString = item[INDEX_NAME];
            if (tempString != null && tempString.equals(procName)) {
                result = item;
                break;
            }
        }
        return result;
    }

    // 根据进程ID获取内存信息 （获取CPU和内存信息）
    public String[] getMemInfoByPid(int pid) {
        String[] result = null;

        String tempPidString = "";
        int tempPid = 0;
        int count = PMUList.size();
        for (int i = 0; i < count; i++) {
            String[] item = PMUList.get(i);
            tempPidString = item[INDEX_PID];
            if (tempPidString == null) {
                continue;
            }
            tempPid = Integer.parseInt(tempPidString);
            if (tempPid == pid) {
                result = item;
                break;
            }
        }
        return result;
    }
}
