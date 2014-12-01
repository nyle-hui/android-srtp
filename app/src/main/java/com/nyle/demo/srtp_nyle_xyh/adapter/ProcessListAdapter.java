package com.nyle.demo.srtp_nyle_xyh.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.media.Image;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyle.demo.srtp_nyle_xyh.R;
import com.nyle.demo.srtp_nyle_xyh.model.BasicProgram;
import com.nyle.demo.srtp_nyle_xyh.util.ApplicationProgramUtil;
import com.nyle.demo.srtp_nyle_xyh.util.ProcessMemoryUtil;
import com.nyle.demo.srtp_nyle_xyh.util.TextUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dengyonghui on 14/10/18.
 */
public class ProcessListAdapter extends BaseAdapter
{
    Context context;
    List<String[]> processList;
    LayoutInflater inflater;
    Map<String[], BasicProgram> basicProgramMap;

    public ProcessListAdapter(Context context)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
        processList = new ArrayList<String[]>();
        basicProgramMap = new HashMap<String[], BasicProgram>();
    }

    public void setProcessList(List<String[]> processList)
    {
        this.processList = processList;
        notifyDataSetChanged();
    }

    public BasicProgram getBasicProcessInfo(int i)
    {
        String[] proccessInfo = processList.get(i);
        BasicProgram basicProgram = basicProgramMap.get(proccessInfo);
        if (basicProgram == null)
        {
            basicProgram = ApplicationProgramUtil.basicProgramUtilSimpleInfo(context, Integer.parseInt(proccessInfo[ProcessMemoryUtil.INDEX_PID]),
                    proccessInfo[ProcessMemoryUtil.INDEX_NAME]);
            basicProgramMap.put(proccessInfo, basicProgram);
        }
        return basicProgram;
    }

    @Override
    public int getCount()
    {
        return processList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return processList.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        String[] proccessInfo = processList.get(i);
        if (proccessInfo == null) return null;

        if (view == null || view.getId() != R.id.item_process)
        {
            view = inflater.inflate(R.layout.item_process, viewGroup, false);
        }

        BasicProgram basicProgram = basicProgramMap.get(proccessInfo);
        if (basicProgram == null)
        {
            basicProgram = ApplicationProgramUtil.basicProgramUtilSimpleInfo(context, Integer.parseInt(proccessInfo[ProcessMemoryUtil.INDEX_PID]),
                    proccessInfo[ProcessMemoryUtil.INDEX_NAME]);
            basicProgramMap.put(proccessInfo, basicProgram);
        }

        ImageView iconShow = (ImageView) view.findViewById(R.id.application_icon);
        TextView nameShow = (TextView) view.findViewById(R.id.type);
        TextView cpuShow = (TextView) view.findViewById(R.id.cpu);
        TextView memShow = (TextView) view.findViewById(R.id.mem);

        iconShow.setImageDrawable(basicProgram.icon);
        TextUtil.textviewContentSet(basicProgram.getProgramName(), nameShow);
        TextUtil.textviewContentSet(basicProgram.memInfoList[ProcessMemoryUtil.INDEX_RSS], memShow);
        TextUtil.textviewContentSet(basicProgram.memInfoList[ProcessMemoryUtil.INDEX_CPU], cpuShow);
        return view;
    }
}
