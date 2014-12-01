package com.nyle.demo.srtp_nyle_xyh.acitivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.nyle.demo.srtp_nyle_xyh.R;
import com.nyle.demo.srtp_nyle_xyh.adapter.ProcessListAdapter;
import com.nyle.demo.srtp_nyle_xyh.adapter.TaskListAdapter;
import com.nyle.demo.srtp_nyle_xyh.global.Const;
import com.nyle.demo.srtp_nyle_xyh.global.Global;
import com.nyle.demo.srtp_nyle_xyh.model.BasicProgram;
import com.nyle.demo.srtp_nyle_xyh.adapter.ServiceListAdapter;
import com.nyle.demo.srtp_nyle_xyh.model.Position;
import com.nyle.demo.srtp_nyle_xyh.task.BasicTask;
import com.nyle.demo.srtp_nyle_xyh.task.ProcessMemoryGetTask;
import com.nyle.demo.srtp_nyle_xyh.util.DensityUtil;
import com.nyle.demo.srtp_nyle_xyh.util.UIUtil;
import com.nyle.demo.srtp_nyle_xyh.view.PopProgressView;

import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;

import java.util.List;

/**
 * Created by dengyonghui on 14/10/27.
 */
public class MainActivity extends Activity implements AdapterView.OnItemClickListener, RadioGroup.OnCheckedChangeListener, BasicTask.TaskEventListener
{
    RadioGroup radioGroup;

    ListView processlistView;
    ProcessListAdapter processProcessListAdapter;

    ListView taskListView;
    List<RunningTaskInfo> taskList;
    TaskListAdapter taskListAdapter;

    ListView serviceListView;
    List<RunningServiceInfo> serviceInfoList;
    ServiceListAdapter serviceListAdapter;

    boolean ifMenuShow = false;
    ViewGroup mSideBar;
    ImageView covering;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        processListViewSet();
    }

    private void processListViewSet()
    {
        for(String[] s : Global.processMemoryUtil.getPMUList())
        {
            String result = "";
            for (String s1 : s)
            {
                result += s1 + " ";
            }
            Log.i("nyle", result);
        }
        processProcessListAdapter = new ProcessListAdapter(this);
        processProcessListAdapter.setProcessList(Global.processMemoryUtil.getPMUList());
        processlistView.setAdapter(processProcessListAdapter);
    }

    private void initView()
    {
        mSideBar = (ViewGroup) findViewById(R.id.side_bar);
        covering = (ImageView) findViewById(R.id.cover);

        processlistView = (ListView) findViewById(R.id.list_view);
        processlistView.setOnItemClickListener(this);

        taskListView = (ListView) findViewById(R.id.task_list_show);
        taskListView.setOnItemClickListener(this);

        serviceListView = (ListView) findViewById(R.id.service_list_show);
        serviceListView.setOnItemClickListener(this);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.check(R.id.menu_process);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        if (adapterView == processlistView)
        {
            BasicProgram basicProgram = processProcessListAdapter.getBasicProcessInfo(i);
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("basicProgram", basicProgram);
            intent.putExtras(bundle);
            intent.setClass(MainActivity.this, ProcessDetailActivity.class);
            startActivityForResult(intent, Const.TO_PROCESS_DETAIL_ACTIVITY);
        }
        else if (adapterView == taskListView)
        {
            Global.runningTaskInfo = (RunningTaskInfo) taskListAdapter.getItem(i);
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, TaskDetailActivity.class);
            startActivity(intent);
        }
        else if (adapterView == serviceListView)
        {
            Global.runningServiceInfo = (RunningServiceInfo) serviceListAdapter.getItem(i);
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, ServiceDetailActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id)
    {
        allListHide();
        switch (id)
        {
            case R.id.menu_process:
            {
                processlistView.setVisibility(View.VISIBLE);
            }break;
            case R.id.menu_task:
            {
                if (taskList == null) taskListSet();
                taskListView.setVisibility(View.VISIBLE);
            }break;
            case R.id.menu_service:
            {
                if (serviceInfoList == null) serviceListSet();
                serviceListView.setVisibility(View.VISIBLE);
            }break;
            default:
        }
    }

    private void serviceListSet()
    {
        serviceInfoList = serviceListGet();
        serviceListAdapter = new ServiceListAdapter(this);
        serviceListAdapter.setServiceInfoList(serviceInfoList);
        serviceListView.setAdapter(serviceListAdapter);

    }

    private List<RunningServiceInfo> serviceListGet()
    {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        return activityManager.getRunningServices(100);
    }

    private void taskListSet()
    {
        taskList = taskListGet();
        taskListAdapter = new TaskListAdapter(this);
        taskListAdapter.setTaskList(taskList);
        taskListView.setAdapter(taskListAdapter);
    }

    private List<ActivityManager.RunningTaskInfo> taskListGet()
    {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        return activityManager.getRunningTasks(100); // max num of running tasks
    }

    //hide three list view
    private void allListHide()
    {
        taskListView.setVisibility(View.GONE);
        serviceListView.setVisibility(View.GONE);
        processlistView.setVisibility(View.GONE);
    }

    //click the menu
    public void clickMenu(View view)
    {
        if (ifMenuShow)
        {
            hideMenu();
            covering.setVisibility(View.GONE);
            mSideBar.setVisibility(View.GONE);
        }
        else
        {
            mSideBar.setVisibility(View.VISIBLE);
            showMenu();
            covering.setVisibility(View.VISIBLE);
        }
    }

    private void hideMenu()
    {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                0, -DensityUtil.dip2px(getBaseContext(), 140f), 0, 0
        );
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(200);
        animationSet.addAnimation(translateAnimation);
        animationSet.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                mSideBar.clearAnimation();
                UIUtil.updateViewPosition(mSideBar, -DensityUtil.dip2px(getBaseContext(), 139f));
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
        mSideBar.startAnimation(animationSet);
        ifMenuShow = false;
    }

    private void showMenu()
    {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                0, DensityUtil.dip2px(getBaseContext(), 140f), 0, 0
        );
        translateAnimation.setDuration(200);
        animationSet.addAnimation(translateAnimation);

        animationSet.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                mSideBar.clearAnimation();
                UIUtil.updateViewPosition(mSideBar, 0);
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });

        mSideBar.startAnimation(animationSet);
        ifMenuShow = true;
    }

    //click "分享"
    public void clickToShare(View view)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
        intent.putExtra(Intent.EXTRA_TEXT, "Share app 浙大云课堂, 学习真的很方便!!");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, getTitle()));
    }

    //click “关于我们”
    public void clickToAboutUs(View view)
    {
//        Intent intent = new Intent();
//        intent.setClass(MainActivity.this, AboutUsActivity.class);
//        startActivity(intent);
    }

    public void clickTCancleAll(View view)
    {

    }

    //click refresh
    public void clickToRefresh(View view)
    {
       refreshProcess();
    }

    private void refreshProcess()
    {
        ProcessMemoryGetTask processMemoryGetTask = new ProcessMemoryGetTask(this, new PopProgressView(this, mSideBar) ,this, Global.packageUtil, Global.processMemoryUtil);
        processMemoryGetTask.execute();
    }

    @Override
    public void onPostExecuteListener(BasicTask task)
    {
        if (task instanceof ProcessMemoryGetTask)
        {
            ProcessMemoryGetTask processMemoryGetTask = (ProcessMemoryGetTask) task;

            Global.packageUtil = processMemoryGetTask.packageUtil;
            Global.processMemoryUtil = processMemoryGetTask.processMemoryUtil;
            processListViewSet();
            serviceInfoList = null;
            taskList = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == Const.TO_PROCESS_DETAIL_ACTIVITY)
        {
            //return from process detail and close a process
            if (resultCode == Const.CANCLE_PROCESS)
            {
                refreshProcess();
            }
        }
    }

    public void clickToHideMenu(View view)
    {
        hideMenu();
        covering.setVisibility(View.GONE);
        mSideBar.setVisibility(View.GONE);
    }
}

//            Log.i("nyle2", "pid: " + runningAppProcessInfo.pid + ";prcocessName" + runningAppProcessInfo.processName); //后面的 是比较好记的名字
