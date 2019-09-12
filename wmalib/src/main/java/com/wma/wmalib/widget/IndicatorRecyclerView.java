package com.wma.wmalib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
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
    private boolean isClick = false;

    private String mCurGroupName = "";

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
        }
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isClick){
                    isClick = false;
                    return;
                }
                View childAt = recyclerView.getChildAt(0);
                int position = recyclerView.getChildAdapterPosition(childAt);
                String groupName = getGroupName(position);
                if (!mCurGroupName.equals(groupName)) {
                    TextView unCheckTv = getCurCheckedTv(mCurGroupName);
                    setTextCheck(unCheckTv,false);
                    mCurGroupName = groupName;
                    TextView checkTv = getCurCheckedTv(mCurGroupName);
                    setTextCheck(checkTv,true);
                    Log.d("WMA-WMA", "onScrolled: mCurGroupName = " + mCurGroupName);
                }

            }


        });
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mIndicatorLl.setLayoutParams(lp);
        mIndicatorLl.setOrientation(LinearLayout.VERTICAL);
        mIndicatorLl.setGravity(Gravity.CENTER);
        mIndicatorLl.setPadding(20, 20, 20, 20);
        mIndicatorLl.addShowTabListener(new IndicatorListLayout.ShowTabListener() {
            @Override
            public void show(boolean isShow, int pos) {
                if (isShow) {
                    if (tabTv.getVisibility() == View.GONE) {
                        tabTv.setVisibility(VISIBLE);
                    }
                    tabTv.setText(((TextView) mIndicatorLl.getChildAt(pos)).getText().toString());
                    Integer integer = mIndicatorMap.get(tabTv.getText().toString());
                    mRecyclerView.scrollToPosition(integer);
                    LinearLayoutManager mLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(integer, 0);
                    for (int i = 0; i < mIndicatorLl.getChildCount(); i++) {
                        TextView tv = (TextView) mIndicatorLl.getChildAt(i);
                        if (i == pos) {
                            mCurGroupName = tv.getText().toString();
                            setTextCheck(tv, true);
                        } else {
                            setTextCheck(tv, false);
                        }
                        Log.d("WMA-WMA", "show: mCurGroupName = " + mCurGroupName);
                    }
                } else {
                    if (tabTv.getVisibility() == View.VISIBLE) {
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
        tabTv.setBackgroundColor(ContextCompat.getColor(mContext, R.color.half_trans));
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
            if (mIndicatorMap.get(key) == 0) {
                mCurGroupName = key;
                setTextCheck(tv, true);
            } else {
                setTextCheck(tv, false);
            }
            tv.setGravity(Gravity.CENTER);
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerView.scrollToPosition(mIndicatorMap.get(key));
                    LinearLayoutManager mLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(mIndicatorMap.get(key), 0);
                    isClick = true;
                    String groupName = ((TextView) v).getText().toString();
                    if (!mCurGroupName.equals(groupName)) {
                        TextView unCheckTv = getCurCheckedTv(mCurGroupName);
                        setTextCheck(unCheckTv,false);
                        mCurGroupName = groupName;
                        TextView checkTv = getCurCheckedTv(mCurGroupName);
                        setTextCheck(checkTv,true);
                    }
                    Log.d("WMA-WMA", "onClick: mCurGroupName = " + mCurGroupName);
                }
            });
            mIndicatorLl.addView(tv);
        }
        CardView cardView = new CardView(mContext);
        cardView.setRadius(DpUtils.dp2px(mContext, 5));
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp.topMargin = DpUtils.dp2px(mContext, 50);
        lp.bottomMargin = DpUtils.dp2px(mContext, 50);
        lp.rightMargin = DpUtils.dp2px(mContext, 10);
        cardView.setLayoutParams(lp);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cardView.setElevation(DpUtils.dp2px(mContext, 10));
        }
        cardView.addView(mIndicatorLl);
        addView(cardView);
    }


    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }


    public void setTextCheck(TextView tv, boolean isChecked) {
        if (tv == null) {
            return;
        }
        if (isChecked) {
            tv.setBackgroundResource(R.drawable.bg_indicator_list);
            tv.setTextColor(Color.WHITE);
        } else {
            tv.setBackgroundResource(R.drawable.bg_un_check_indicator_list);
            tv.setTextColor(mIndicatorColor);
        }

    }


    private String getGroupName(int pos) {
        if (onGroupController != null) {
            String groupName = onGroupController.getGroupName(pos);
            return groupName;
        }
        return "";
    }

    public void setOnGroupController(GroupController onGroupController) {
        this.onGroupController = onGroupController;
    }

    GroupController onGroupController;

    public interface GroupController {
        String getGroupName(int pos);
    }

    public TextView getCurCheckedTv(String groupName){
        for (int i = 0; i < mIndicatorLl.getChildCount(); i++) {
            TextView childAt = (TextView) mIndicatorLl.getChildAt(i);
            if(groupName.equals(childAt.getText().toString())){
                return childAt;
            }
        }
        return null;
    }

}
