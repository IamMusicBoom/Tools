package com.wma.tools.model.weather.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import com.wma.tools.R;
import com.wma.wmalib.utils.DpUtils;

/**
 * Created by 王明骜 on 19-8-30 下午3:47.
 */
public class CircleLevelView extends LinearLayout {
    private Context mContext;

    private Paint mPaint;

    private int mLevelColor;//前景色
    private int mBgColor;//背景色

    private int mRadius;//圆弧半径
    private int mStroke;//笔的宽度

    private int mHeight, mWidth;

    RectF mRectF;//圆弧所在的矩形

    private float mCurSweepAngle;//当前扫过的角度
    private long mDuration;//动画执行的时间
    private int mMaxAngle;//最大角度
    private int mStartAngle;//开始角度


    public CircleLevelView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CircleLevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CircleLevelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        initData();
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.CircleLevelView);
        mLevelColor = ta.getColor(R.styleable.CircleLevelView_Level_Color, ContextCompat.getColor(mContext, R.color.colorAccent));
        mBgColor = ta.getColor(R.styleable.CircleLevelView_Bg_Color, Color.WHITE);
        ta.recycle();
        setWillNotDraw(false);


    }

    /**
     * 初始化数据
     */
    private void initData() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStroke = DpUtils.dp2px(mContext, 5);
        mPaint.setStrokeWidth(mStroke);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);

        mRadius = DpUtils.dp2px(mContext, 100);

        mWidth = mHeight = mRadius * 2;

        mMaxAngle = 240;
        mStartAngle = 150;
        mDuration = 1000;
        mCurSweepAngle = 0;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                mWidth = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                if(widthSize < mWidth){
                    mWidth = widthSize;
                }
                break;
        }

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                mHeight = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                if(heightSize < mHeight){
                    mHeight = heightSize;
                }
                break;
        }
        mWidth = mHeight = Math.min(mWidth,mHeight);
        mRadius = mWidth/2 -2*mStroke;
        setMeasuredDimension(mWidth,mHeight);
        mRectF = new RectF(0+mStroke,0+mStroke,mWidth-mStroke,mHeight-mStroke);

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initBgPaint();
        canvas.drawArc(mRectF,mStartAngle,mMaxAngle,false,mPaint);
        initLevelPaint();
        canvas.drawArc(mRectF,mStartAngle,mCurSweepAngle,false,mPaint);

    }

    private void initLevelPaint() {
        mPaint.setColor(mLevelColor);
        mPaint.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.SOLID));

    }

    private void initBgPaint(){
        mPaint.setColor(mBgColor);
        mPaint.setMaskFilter(null);
    }

    public void setLevel(float level){
        float dist = level/100* mMaxAngle;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(mCurSweepAngle,dist);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setDuration(mDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                mCurSweepAngle = animatedValue;
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

}
