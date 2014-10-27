package com.nyle.demo.srtp_nyle_xyh;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.RadioGroup;

import Adapter.ListAdapter;
import Util.PackageUtil;
import Util.ProcessMemoryUtil;

/**
 * Created by dengyonghui on 14/10/27.
 */
public class ListActivity extends Activity
{
    RadioGroup radioGroup;

    ListView listView;
    ListAdapter listAdapter;

    PackageUtil packageUtil;
    ProcessMemoryUtil processMemoryUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.check(R.id.menu_process);

        listView = (ListView) findViewById(R.id.list_view);

        packageUtil = new PackageUtil(this);
        processMemoryUtil = new ProcessMemoryUtil();
        processMemoryUtil.initPMUtil();

        for (ApplicationInfo applicationInfo : packageUtil.allAppList)
        {
            Log.i("nyle", "processName" + applicationInfo.processName + ";name" + applicationInfo.loadLabel(getPackageManager()).toString());
        }


        listAdapter = new ListAdapter(this);
        listAdapter.setProcessList(packageUtil.allAppList);
        listView.setAdapter(listAdapter);
    }
}
