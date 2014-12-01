package com.nyle.demo.srtp_nyle_xyh.adapter;

import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyle.demo.srtp_nyle_xyh.R;
import com.nyle.demo.srtp_nyle_xyh.util.TextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengyonghui on 14/10/18.
 */
public class ServiceListAdapter extends BaseAdapter
{
    Context context;
    List<RunningServiceInfo> serviceInfoList;
    LayoutInflater inflater;

    public ServiceListAdapter(Context context)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
        serviceInfoList = new ArrayList<RunningServiceInfo>();
    }

    public void setServiceInfoList(List<RunningServiceInfo> serviceInfoList)
    {
        this.serviceInfoList = serviceInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return serviceInfoList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return serviceInfoList.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        RunningServiceInfo serviceInfo = serviceInfoList.get(i);
        if (serviceInfo == null) return null;

        if (view == null || view.getId() != R.id.item_service)
        {
            view = inflater.inflate(R.layout.item_service, viewGroup, false);
        }

        TextView nameShow = (TextView) view.findViewById(R.id.service_name_show);

        TextUtil.textviewContentSet(simple(serviceInfo.service), nameShow);
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
