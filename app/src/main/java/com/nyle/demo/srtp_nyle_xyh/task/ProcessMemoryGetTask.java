package com.nyle.demo.srtp_nyle_xyh.task;

import android.content.Context;

import com.nyle.demo.srtp_nyle_xyh.util.PackageUtil;
import com.nyle.demo.srtp_nyle_xyh.util.ProcessMemoryUtil;
import com.nyle.demo.srtp_nyle_xyh.view.PopProgressView;

/**
 * Created by dengyonghui on 14/11/25.
 */
public class ProcessMemoryGetTask extends BasicTask
{
    public PackageUtil packageUtil;
    public ProcessMemoryUtil processMemoryUtil;

    public ProcessMemoryGetTask(Context context, PopProgressView popProgressView, TaskEventListener listener, PackageUtil packageUtil, ProcessMemoryUtil processMemoryUtil)
    {
        super(context, popProgressView, listener);
        this.packageUtil = packageUtil;
        this.processMemoryUtil = processMemoryUtil;
    }

    @Override
    protected String doInBackground(String... params)
    {
        packageUtil = new PackageUtil(context);
        processMemoryUtil = new ProcessMemoryUtil();
        processMemoryUtil.initPMUtil();

        return null;
    }
}
