package com.nyle.demo.srtp_nyle_xyh.acitivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nyle.demo.srtp_nyle_xyh.R;
import com.nyle.demo.srtp_nyle_xyh.global.Const;
import com.nyle.demo.srtp_nyle_xyh.model.BasicProgram;
import com.nyle.demo.srtp_nyle_xyh.model.DetailProgram;
import com.nyle.demo.srtp_nyle_xyh.util.ApplicationProgramUtil;
import com.nyle.demo.srtp_nyle_xyh.util.TextUtil;
import com.nyle.demo.srtp_nyle_xyh.util.UIUtil;
import com.nyle.demo.srtp_nyle_xyh.view.MemoryShowView;

import java.lang.reflect.Method;

/**
 * Created by dengyonghui on 14/11/27.
 */
public class ProcessDetailActivity extends Activity
{
    MemoryShowView memoryShowView;
    BasicProgram basicProgram;
    TextView nameShow;
    DetailProgram detailProgram;
    TextView processNameShow;
    TextView versionInfoShow;
    TextView installDirShow;
    TextView authorityShow;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_detail);

        initView();
        getBasicProgram();
        basicProgramRender();

        getDetailProgram();
        detailProgramRender();
    }

    private void detailProgramRender()
    {
        if (detailProgram == null)
        {
            return;
        }

        if (detailProgram.getProcessName() == null) processNameShow.setText("暂无");
        else TextUtil.textviewContentSet(detailProgram.getProcessName(), processNameShow);

        if (detailProgram.getVersionName() == null) processNameShow.setText("暂无");
        else TextUtil.textviewContentSet("版本: " + detailProgram.getVersionName(), versionInfoShow);

        TextUtil.textviewContentSet(detailProgram.getDataDir(), installDirShow);
        TextUtil.textviewContentSet(detailProgram.getUserPermissionsFormat(), authorityShow);
    }

    private void getDetailProgram()
    {
        detailProgram = ApplicationProgramUtil.buildProgramComplexInfo(this, basicProgram.processName);
    }

    private void basicProgramRender()
    {
        memoryShowView.setMemory(basicProgram.getMemory());
        TextUtil.textviewContentSet(basicProgram.getProgramName(), nameShow);
    }

    //get the processInfo
    private void getBasicProgram()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        basicProgram = (BasicProgram) bundle.getSerializable("basicProgram");
    }

    private void initView()
    {
        memoryShowView = (MemoryShowView) findViewById(R.id.memory_show);
        nameShow = (TextView) findViewById(R.id.process_name_show);

        processNameShow = (TextView) findViewById(R.id.name_show);
        versionInfoShow = (TextView) findViewById(R.id.version_info);
        installDirShow = (TextView) findViewById(R.id.install_dir);
        authorityShow = (TextView) findViewById(R.id.authority);
    }

    public void clickToTurnBack(View view)
    {
        finish();
    }

    //结束进程
    public void clickToCancle(View view)
    {
        if (detailProgram == null) return;
        Log.i("nyle", "package name :" + detailProgram.packageName);
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        manager.killBackgroundProcesses(detailProgram.packageName);
        UIUtil.toast(this, "关闭成功");
        setResult(Const.CANCLE_PROCESS);
        this.finish();
    }
}
