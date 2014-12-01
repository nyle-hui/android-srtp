package com.nyle.demo.srtp_nyle_xyh.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.nyle.demo.srtp_nyle_xyh.R;
import com.nyle.demo.srtp_nyle_xyh.util.DensityUtil;

/**
 * Created by dengyonghui on 14/11/27.
 */
public class MemShowBackgroundView extends View
{
    int radius;
    float percentage = 0;

    public MemShowBackgroundView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        radius = DensityUtil.dip2px(context, 240 / 2);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(getResources().getColor(R.color.mem_view_background));
        canvas.drawCircle(radius, radius, radius, p);

        p.setColor(getResources().getColor(R.color.mem_view_part));
        RectF oval2 = new RectF(0, 0, radius * 2, radius * 2);
        canvas.drawArc(oval2, 270, percentage * 360, true, p);

        p.setColor(getResources().getColor(R.color.mem_view_top));
        canvas.drawCircle(radius , radius, radius * 0.667f, p);
    }

    public void setPercentage(float percentage)
    {
        this.percentage = percentage;
        this.postInvalidate();
    }
}
