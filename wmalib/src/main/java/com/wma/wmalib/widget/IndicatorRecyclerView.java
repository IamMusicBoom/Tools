package com.wma.wmalib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wma.wmalib.R;
import com.wma.wmalib.utils.DpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 王明骜 on 19-9-10 下午2:01.
 */
public class IndicatorRecyclerView extends RelativeLayout {
    private Context mContext;
    private int mIndicatorBg, mIndicatorColor;
    RecyclerView mRecyclerView;
    IndicatorListLayout mIndicatorLl;

    public IndicatorRecyclerView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public IndicatorRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }


    public IndicatorRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.IndicatorRecyclerView);
        mIndicatorBg = ta.getColor(R.styleable.IndicatorRecyclerView_indicator_bg, Color.TRANSPARENT);
        mIndicatorColor = ta.getColor(R.styleable.IndicatorRecyclerView_indicator_color, Color.DKGRAY);
        ta.recycle();
        initData();
    }
    TextView tabTv;
    private void initData() {
        mRecyclerView = new RecyclerView(mContext);
        mRecyclerView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        addView(mRecyclerView);
        mIndicatorLl = new IndicatorListLayout(mContext);
        if (mIndicatorBg != Color.TRANSPARENT) {
            mIndicatorLl.setBackgroundColor(mIndicatorBg);
        } else {
            mIndicatorLl.setBackgroundColor(Color.CYAN);
        }
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp.topMargin = DpUtils.dp2px(mContext, 50);
        lp.bottomMargin = DpUtils.dp2px(mContext, 50);
        lp.rightMargin = DpUtils.dp2px(mContext, 10);
        mIndicatorLl.setLayoutParams(lp);
        mIndicatorLl.setOrientation(LinearLayout.VERTICAL);
        mIndicatorLl.setGravity(Gravity.CENTER);
        mIndicatorLl.addShowTabListener(new IndicatorListLayout.ShowTabListener() {
            @Override
            public void show(boolean isShow, int pos) {
                if(isShow){
                    if(tabTv.getVisibility() == View.GONE){
                        tabTv.setVisibility(VISIBLE);
                    }
                    tabTv.setText(((TextView) mIndicatorLl.getChildAt(pos)).getText().toString());
                    Integer integer = mIndicatorMap.get(tabTv.getText().toString());
                    mRecyclerView.scrollToPosition(integer);
                    LinearLayoutManager mLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(integer, 0);
                }else{
                    if(tabTv.getVisibility() == View.VISIBLE){
                        tabTv.setVisibility(GONE);
                    }
                }
            }
        });
        tabTv = new TextView(mContext);
        LayoutParams lpTab = new LayoutParams(DpUtils.dp2px(mContext, 50), DpUtils.dp2px(mContext, 50));
        lpTab.addRule(RelativeLayout.CENTER_IN_PARENT);
        tabTv.setLayoutParams(lpTab);
        tabTv.setGravity(Gravity.CENTER);
        tabTv.getPaint().setFakeBoldText(true);
        tabTv.setBackgroundColor(ContextCompat.getColor(mContext,R.color.half_trans));
        tabTv.setTextColor(Color.WHITE);
        tabTv.setVisibility(GONE);
        addView(tabTv);
    }
    Map<String, Integer> mIndicatorMap;
    public void setIndicators(final Map<String, Integer> maps) {
        mIndicatorMap = maps;
        for (final String key : mIndicatorMap.keySet()) {
            TextView tv = new TextView(mContext);
            tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
            tv.setText(key);
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(mIndicatorColor);
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerView.scrollToPosition(mIndicatorMap.get(key));
                    LinearLayoutManager mLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(mIndicatorMap.get(key), 0);
                }
            });
            mIndicatorLl.addView(tv);
        }
        addView(mIndicatorLl);
    }


    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }


}
