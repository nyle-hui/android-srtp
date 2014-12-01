package com.nyle.demo.srtp_nyle_xyh.task;

import android.content.Context;
import android.os.AsyncTask;

import com.nyle.demo.srtp_nyle_xyh.view.PopProgressView;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by dd on 13-8-15.
 */
public abstract class BasicTask extends AsyncTask<String, Void, String>
{

    public Context context;
    private PopProgressView popProgressView;
    protected String popTip;
    public int tag = 0;
    public String data;
    protected Map<String, String> postData;
    private TaskEventListener listener;

    public BasicTask()
    {
        this(null);
    }

    public BasicTask(Context context)
    {
        this(context, null, null);
    }

    public BasicTask(Context context, PopProgressView popProgressView)
    {
        this(context, popProgressView, null);
    }

    public BasicTask(Context context, TaskEventListener listener)
    {
        this(context, null, listener);
    }

    public BasicTask(Context context, PopProgressView popProgressView, TaskEventListener listener)
    {
        super();
        this.context = context;
        this.popProgressView = popProgressView;
        this.listener = listener;
    }

    public void put(String key, String value)
    {
        if (key == null || value == null) return;

        if (postData == null)
        {
            postData = new HashMap<String, String>();
        }

        postData.put(key, value);
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();

        if (popProgressView != null)
        {
            popProgressView.setTip(popTip);
            popProgressView.show();
        }
    }

    @Override
    protected abstract String doInBackground(String... params);

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);

        if (popProgressView != null)
        {
            popProgressView.hide();
        }

        if (listener != null)
        {
            listener.onPostExecuteListener(this);
        }

    }


    public interface TaskEventListener
    {
        public abstract void onPostExecuteListener(BasicTask task);
    }
}
