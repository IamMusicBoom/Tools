package com.wma.wmalib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wma.wmalib.R;
import com.wma.wmalib.common.LogUtils;

/**
 * Created by 王明骜 on 19-8-7 上午10:27.
 */
public class WViewPager extends ViewPager {
    private boolean isVertical = false;
    private boolean canScroll = true;
    public WViewPager(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public WViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.WViewPager);
        isVertical = ta.getBoolean(R.styleable.WViewPager_isVertical,false);

        canScroll = ta.getBoolean(R.styleable.WViewPager_canScroll,true);
//        LogUtils.d("WMA-WMA","isVertical = " + isVertical + "  canScroll = " + canScroll);
        ta.recycle();
        if(isVertical){
            setPageTransformer(true, new VerticalPageTransformer());
            setOverScrollMode(OVER_SCROLL_NEVER);
        }
    }

    private class VerticalPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(@NonNull View view, float position) {
            if (position < -1) {
                view.setAlpha(0);
            } else if (position <= 1) {
                view.setAlpha(1);
                view.setTranslationX(view.getWidth() * -position);
                float yPosition = position * view.getHeight();
                view.setTranslationY(yPosition);
            } else {
                view.setAlpha(0);
            }
        }
    }

    private MotionEvent swapXY(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();
        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;
        ev.setLocation(newX, newY);
        return ev;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){
        if(canScroll){
            if(isVertical){
                boolean intercepted = super.onInterceptTouchEvent(swapXY(ev));
                swapXY(ev);
                return intercepted;
            }else{
                return super.onInterceptTouchEvent(ev);
            }
        }else{
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(canScroll){
            if(isVertical){
                return super.onTouchEvent(swapXY(ev));
            }else{
                return super.onTouchEvent(ev);
            }
        }else{
            return false;
        }
    }
}
