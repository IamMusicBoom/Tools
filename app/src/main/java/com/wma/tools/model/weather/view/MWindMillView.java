package com.wma.tools.model.weather.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.wma.tools.R;
import com.wma.wmalib.utils.DpUtils;

/**
 * Created by 王明骜 on 19-8-29 上午9:21.
 */
public class MWindMillView extends View {
    private Context mContext;

    private Paint mPaint;

    /**
     * 风车柱子高度
     */
    private int mPillarHeight;
    /**
     * 风车柱子宽度
     */
    private int mPillarWidth;

    /**
     * 风车单个叶片长度
     */
    private int mFanLength;

    /**
     * 风车单个叶片宽度
     */
    private int mFanWidth;
    /**
     * 扇叶个数
     */
    private int mFanCount;

    /**
     * 旋转角度
     */
    private int mDegrees;

    /**
     * 控件高度
     */
    private int mHeight;

    /**
     * 控件宽度
     */
    private int mWidth;

    /**
     * 风力等级
     */
    private int mWindLevel;


    /**
     * 扇叶颜色
     */
    private int mFanColor;

    /**
     * 柱子颜色
     */
    private int mPillarColor;

    public MWindMillView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public MWindMillView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MWindMillView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        //获取自定义属性 =============== 开始
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.MWindMillView);
        mFanColor = ta.getColor(R.styleable.MWindMillView_Fan_Color, Color.WHITE);
        mPillarColor = ta.getColor(R.styleable.MWindMillView_Pillar_Color, Color.WHITE);
        ta.recycle();
        //获取自定义属性 =============== 结束


        initData();

        //初始化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mFanColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);

    }

    private void initData() {
        mFanLength = DpUtils.dp2px(mContext, 40);
        mPillarHeight = DpUtils.dp2px(mContext, 100);
        mPillarWidth = DpUtils.dp2px(mContext, 5);
        mFanWidth = DpUtils.dp2px(mContext, 5);
        mFanCount = 3;
        mDegrees = 360 / mFanCount;
        setWindLevel(1);
        mHeight = mFanLength + mPillarHeight;
        mWidth = mFanLength * 2;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                mWidth = widthSize;
                mFanLength = mWidth / 2;
                break;
        }
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                mHeight = heightSize;
                mPillarHeight = 3 / 5 * mHeight;
                mFanLength = 2 / 5 * mHeight;
                break;
        }

        if (mWidth > mHeight) {
            initData();
        }
        setMeasuredDimension(mWidth, mHeight);
    }


    private int mCurDegrees;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCurDegrees == 360) {
            mCurDegrees = 0;
        }
        mCurDegrees += 5;
        canvas.translate(mWidth / 2, mHeight / 2);
        RectF pillarRectF = new RectF(0 - mPillarWidth / 2, 10, mPillarWidth / 2, mPillarHeight);
        mPaint.setColor(mPillarColor);
        canvas.drawRoundRect(pillarRectF, 50, 50, mPaint);
        mPaint.setColor(mFanColor);
        RectF fanRectF = new RectF(0 - mFanWidth / 2, 0, mFanWidth / 2, mFanLength);
        for (int i = 0; i < mFanCount; i++) {
            canvas.save();
            canvas.rotate(mDegrees * i + mCurDegrees);
            canvas.drawOval(fanRectF, mPaint);
            canvas.restore();
        }
        postInvalidateDelayed(mWindLevel);
    }

    public void setWindLevel(int level) {
        mWindLevel = 100 / level;
    }
}
