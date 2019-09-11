package com.wma.wmalib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
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
    private int mIndicatorBg,mIndicatorColor;
    RecyclerView mRecyclerView;
    LinearLayout mIndicatorLl;
    public IndicatorRecyclerView(Context context) {
        super(context);
        init(context,null,0);
    }

    public IndicatorRecyclerView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }


    public IndicatorRecyclerView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.IndicatorRecyclerView);
        mIndicatorBg = ta.getColor(R.styleable.IndicatorRecyclerView_indicator_bg,Color.TRANSPARENT);
        mIndicatorColor = ta.getColor(R.styleable.IndicatorRecyclerView_indicator_color,Color.DKGRAY);
        ta.recycle();
        initData();
    }

    private void initData() {
        mRecyclerView = new RecyclerView(mContext);
        mRecyclerView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));
        addView(mRecyclerView);
        mIndicatorLl = new LinearLayout(mContext);
        if(mIndicatorBg != Color.TRANSPARENT){
            mIndicatorLl.setBackgroundColor(mIndicatorBg);
        }else{
            mIndicatorLl.setBackgroundColor(Color.CYAN);
        }
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp.topMargin = DpUtils.dp2px(mContext,50);
        lp.bottomMargin = DpUtils.dp2px(mContext,50);
        lp.rightMargin = DpUtils.dp2px(mContext,10);
        mIndicatorLl.setLayoutParams(lp);
        mIndicatorLl.setOrientation(LinearLayout.VERTICAL);
        mIndicatorLl.setGravity(Gravity.CENTER);

    }
    public void setIndicators(final Map<String,Integer> map){
        for (final String key : map.keySet()) {
            TextView tv = new TextView(mContext);
            tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0,1));
            tv.setText(key);
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(mIndicatorColor);
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerView.scrollToPosition(map.get(key));
                    LinearLayoutManager mLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(map.get(key), 0);
                }
            });
            mIndicatorLl.addView(tv);
        }
        addView(mIndicatorLl);
    }


    public RecyclerView getRecyclerView(){
        return mRecyclerView;
    }
}
