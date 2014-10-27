package com.nyle.demo.srtp_nyle_xyh;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RadioGroup;

import Util.PackageUtil;


public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener
{

    int status = -1;
    ListView listView;

    RadioGroup radioGroup;
    PackageUtil packageUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(this);
        listView = (ListView)findViewById(R.id.listview);

        packageUtil = new PackageUtil(this);

        for (ApplicationInfo applicationInfo : packageUtil.allAppList)
        {
            Log.i("nyle", "processName" + applicationInfo.processName + ";name" + applicationInfo.loadLabel(getPackageManager()).toString());
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id)
    {
        if (id == R.id.menu_1)
        {

        }
        else if(id == R.id.menu_2)
        {

        }
        else if (id == R.id.menu_3)
        {

        }
    }
}
