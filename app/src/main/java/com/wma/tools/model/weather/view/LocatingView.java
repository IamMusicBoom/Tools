package com.wma.tools.model.weather.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.wma.tools.R;
import com.wma.wmalib.utils.DpUtils;

/**
 * Created by 王明骜 on 19-9-5 上午10:51.
 */
public class LocatingView extends View {
    private Context mContext;

    private int mFailColor;
    private int mSuccessClolor;

    private Bitmap mLocateBm;

    private int mCircleBgColor;
    private int mProgressColor;
    private int mRadius;


    private Paint mPaint;
    private int mStockWidth;

    private int mHeight, mWidth;

    private int mStartAngle;
    private int mSweepAngle;

    private Rect srcRect;
    private Rect dstRect;
    private RectF oval;


    public static final int LOCATING = 0;//定位中
    public static final int LOCATE_FAIL = 1;//定位失败
    public static final int LOCATE_SUCCESS = 2;//定位成功
    public static final int UNLOCATE = 3;//定位成功
    private static int mCurState = UNLOCATE;


    public LocatingView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public LocatingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public LocatingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        initData();
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.LocatingView);
        mFailColor = ta.getColor(R.styleable.LocatingView_Fail_Color, ContextCompat.getColor(mContext, R.color.fail_red));
        mSuccessClolor = ta.getColor(R.styleable.LocatingView_Success_Color, ContextCompat.getColor(mContext, R.color.success_green));
        mCircleBgColor = ta.getColor(R.styleable.LocatingView_Circle_Bg_Color, Color.DKGRAY);
        mProgressColor = ta.getColor(R.styleable.LocatingView_Progress_Color, ContextCompat.getColor(mContext, R.color.colorPrimary));

    }

    private void initData() {
        initPaintForLocating(true);

        mLocateBm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_locate);

        srcRect = new Rect(0,0, mLocateBm.getWidth(), mLocateBm.getHeight());
        mWidth = mLocateBm.getWidth();
        mHeight = mLocateBm.getHeight();
        mRadius = mWidth/2 - 2*mStockWidth;
        dstRect = srcRect;
        oval=new RectF(mStockWidth,mStockWidth,mWidth-mStockWidth,mWidth-mStockWidth);
        mStartAngle = 0;
        mSweepAngle = 120;
    }

    private void initPaintForLocating(boolean isBg) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mStockWidth = DpUtils.dp2px(mContext,3);
        mPaint.setStrokeWidth(mStockWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        if(isBg){
            mPaint.setColor(mCircleBgColor);
        }else{
            if(mCurState == LOCATE_SUCCESS){
                mPaint.setColor(ContextCompat.getColor(mContext,R.color.success_green));
            }else if(mCurState == LOCATE_FAIL){
                mPaint.setColor(ContextCompat.getColor(mContext,R.color.fail_red));
            }else {
                mPaint.setColor(mProgressColor);
            }
            mPaint.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.SOLID));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                mWidth = Math.min(mWidth, widthSize);
                break;
            case MeasureSpec.EXACTLY:
                mWidth = widthSize;
                break;
        }
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                mHeight = Math.min(mHeight, heightSize);
                break;
            case MeasureSpec.EXACTLY:
                mHeight = heightSize;
                break;
        }

        mWidth = mHeight = Math.min(mWidth,mHeight);
        mRadius = mWidth/2 - mStockWidth;
        oval.set(mStockWidth,mStockWidth,mWidth-mStockWidth,mWidth-mStockWidth);
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mLocateBm != null && mCurState != LOCATING){
            mPaint.reset();
            canvas.drawBitmap(mLocateBm,srcRect, dstRect,mPaint);
        }else{
            initPaintForLocating(true);
            canvas.drawArc(oval,0,360,false,mPaint);
            initPaintForLocating(false);
            canvas.drawArc(oval,mStartAngle,mSweepAngle,false,mPaint);
        }
    }

    public void setCurState(int state){
        mCurState = state;
        mLocateBm = null;
        switch (mCurState){
            case UNLOCATE:
                mLocateBm = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.ic_locate);
                break;
            case LOCATE_FAIL:
              startFailAnim();
                break;
            case LOCATE_SUCCESS:
                startSuccessAnim();
                break;
            case LOCATING:
                startLocatingAnim();
                return;
        }
        postInvalidate();
    }

    private void startFailAnim() {
        if(mAnim != null && mAnim.isRunning()){
            mAnim.cancel();
        }
        mSweepAngle = 0;
        mAnim = ValueAnimator.ofInt(mStartAngle,360);
        mAnim.setDuration(1000);
        mAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                mSweepAngle = animatedValue;
                postInvalidate();
            }
        });
        mAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mLocateBm = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.ic_locate_red);
            }
        });
        mAnim.setInterpolator(new DecelerateInterpolator());
        mAnim.start();

    }

    private void startSuccessAnim() {
        if(mAnim != null && mAnim.isRunning()){
            mAnim.cancel();
        }
        mSweepAngle = 0;
        mAnim = ValueAnimator.ofInt(mStartAngle,360);
        mAnim.setDuration(1000);
        mAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                mSweepAngle = animatedValue;
                postInvalidate();
            }
        });
        mAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mLocateBm = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.ic_locate_green);
            }
        });
        mAnim.setInterpolator(new DecelerateInterpolator());
        mAnim.start();

    }

    ValueAnimator mAnim;
    private void startLocatingAnim() {
        if(mAnim != null && mAnim.isRunning()){
            mAnim.cancel();
        }
        mStartAngle = 0;
        mSweepAngle = 120;
        mAnim = ValueAnimator.ofInt(0,360);
        mAnim.setDuration(500);
        mAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                mStartAngle = animatedValue;
                postInvalidate();
            }
        });
        mAnim.setInterpolator(new LinearInterpolator());
        mAnim.setRepeatCount(-1);
        mAnim.start();
    }

}
