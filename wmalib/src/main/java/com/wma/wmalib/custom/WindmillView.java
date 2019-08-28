package com.wma.wmalib.custom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.wma.wmalib.utils.DpUtils;

import app.BaseAppContext;

/**
 * Created by 王明骜 on 19-8-21 下午3:39.
 */
public class WindmillView extends View {
    private final int DEFAULT_COLOR = Color.BLUE;
    private Paint mPaint;
    private Path mPath;

    private int mHeight = DpUtils.dp2px(BaseAppContext.getInstance(),100);
    private int mWidth = DpUtils.dp2px(BaseAppContext.getInstance(),100);;
    public WindmillView(Context context) {
        super(context);
        init();
    }

    public WindmillView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init();
    }



    public WindmillView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPath = new Path();
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        calculationSize(widthMode,mWidth);
    }

    private void calculationSize(int mode, int size) {

        switch (mode){
            case MeasureSpec.EXACTLY:
                break;
        }

    }
}
