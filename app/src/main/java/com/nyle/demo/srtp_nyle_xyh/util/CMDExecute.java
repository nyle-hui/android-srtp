package com.nyle.demo.srtp_nyle_xyh.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dengyonghui on 14/10/27.
 */
public class CMDExecute
{
    public synchronized String run(String [] cmd, String workdirectory) throws IOException
    {
        String result = "";
        try {
            ProcessBuilder builder = new ProcessBuilder(cmd);
            InputStream in = null;
            //设置一个路径
            if (workdirectory != null) {
                builder.directory(new File(workdirectory));
                builder.redirectErrorStream(true);
                Process process = builder.start();
                in = process.getInputStream();
                byte[] re = new byte[1024];
                while (in.read(re) != -1)
                    result = result + new String(re);
            }
            if (in != null) {
                in.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
