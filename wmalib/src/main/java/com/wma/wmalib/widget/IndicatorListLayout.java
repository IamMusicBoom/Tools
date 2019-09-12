package com.wma.wmalib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by 王明骜 on 19-9-11 下午1:08.
 */
public class IndicatorListLayout extends LinearLayout {
    private Context mContext;

    public IndicatorListLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public IndicatorListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public IndicatorListLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
    }

    float firstX, firstY;
    int childCount;
    int childHeight;
    int height;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstX = ev.getX();
                firstY = ev.getX();
                childCount = getChildCount();
                height = getMeasuredHeight();
                childHeight = getChildAt(0).getHeight();

                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(firstY - ev.getY()) > 10) {
                    return true;
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(firstY - event.getY()) > 20) {
                    if (showTabListener != null) {
                        showTabListener.show(true, getPos(event.getY()));
                    }
                    return true;
                }

                break;
            case MotionEvent.ACTION_UP:
                if (showTabListener != null) {
                    showTabListener.show(false, getPos(event.getY()));

                }
                break;
        }
        return super.onTouchEvent(event);
    }


    public int getPos(float y) {
        for (int i = 0; i < childCount; i++) {
            int max = i * childHeight + childHeight;
            int min = i * childHeight;
            if (y <= max && y >= min) {
                return i;
            }
        }
        return 0;
    }


    public void addShowTabListener(ShowTabListener showTabListener) {
        this.showTabListener = showTabListener;
    }

    ShowTabListener showTabListener;

    interface ShowTabListener {
        void show(boolean isShow, int pos);
    }
}
