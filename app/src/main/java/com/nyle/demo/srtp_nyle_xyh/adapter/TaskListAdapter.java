package com.nyle.demo.srtp_nyle_xyh.adapter;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
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
import java.util.List;

/**
 * Created by dengyonghui on 14/10/18.
 */
public class TaskListAdapter extends BaseAdapter
{
    Context context;
    List<ActivityManager.RunningTaskInfo> taskInfoList;
    LayoutInflater inflater;

    public TaskListAdapter(Context context)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
        taskInfoList = new ArrayList<ActivityManager.RunningTaskInfo>();
    }

    public void setTaskList(List<ActivityManager.RunningTaskInfo> taskInfoList)
    {
        this.taskInfoList = taskInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return taskInfoList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return taskInfoList.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ActivityManager.RunningTaskInfo taskInfo = taskInfoList.get(i);
        if (taskInfo == null) return null;

        if (view == null || view.getId() != R.id.item_task)
        {
            view = inflater.inflate(R.layout.item_task, viewGroup, false);
        }

        ImageView iconShow = (ImageView) view.findViewById(R.id.task_icon);
        TextView nameShow = (TextView) view.findViewById(R.id.task_name_show);
        TextView idShow = (TextView) view.findViewById(R.id.task_id_show);

        iconShow.setBackgroundResource(R.drawable.task_sign);
        TextUtil.textviewContentSet("" + taskInfo.id, idShow);
        TextUtil.textviewContentSet(simple(taskInfo.baseActivity), nameShow);
        return view;
    }

    private String simple(ComponentName baseActivity)
    {
        //baseActivity must be ComponentInfo{...}, we select the part from the string

        boolean ifWrite = false;
        String result = "" + baseActivity;
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

        result = tmpStr;
        return  result;
    }
}
