package com.wma.tools.horoscope;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wma.tools.R;
import com.wma.wmalib.utils.DpUtils;

/**
 * Created by 王明骜 on 19-9-6 下午2:53.
 */
public class CeilingItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;

    private Rect mDiverRect;
    private Paint mPaint;

    private int mDividerHeight;

    private int mGroupDividerHeight;
    private int mItemDiverHeight;

    private int mCellingColor;
    private int mGroupColor;

    public CeilingItemDecoration(Context context, GroupController onGroupController) {
        this.mContext = context;
        initData();
        mDiverRect = new Rect();
        this.onGroupController = onGroupController;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mGroupColor);


    }

    private void initData() {
        mItemDiverHeight = 1;
        mGroupDividerHeight = 150;
        mGroupColor = ContextCompat.getColor(mContext, R.color.colorPrimaryDark);
        mCellingColor = Color.BLACK;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (position == 0 || isGroupFirst(position)) {
            mDividerHeight = mGroupDividerHeight;
        } else {
            mDividerHeight = mItemDiverHeight;
        }
        outRect.top = mDividerHeight;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        for (int i = 0; i < parent.getChildCount(); i++) {
            View childAt = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(childAt);
            if (isGroupFirst(position)) {
                mDividerHeight = mGroupDividerHeight;
            } else {
                mDividerHeight = mItemDiverHeight;
            }
            int left = parent.getPaddingLeft();
            int right = childAt.getWidth() - parent.getPaddingRight();
            int bottom = childAt.getTop();
            int top = bottom - mDividerHeight;
            mDiverRect.set(left, top, right, bottom);
            mPaint.setColor(mGroupColor);
            c.drawRect(mDiverRect, mPaint);
            if (isGroupFirst(position)) {
                float baseLine = (top + bottom) / 2f - (mPaint.descent() + mPaint.ascent()) / 2f;
                mPaint.setColor(mCellingColor);
                mPaint.setTextSize(DpUtils.sp2px(mContext, 20));
                c.drawText(getGroupName(position), left + DpUtils.dp2px(mContext, 10), baseLine, mPaint);
            }
        }

    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        View childAt = parent.getChildAt(0);
        if (childAt == null) {
            return;
        }
        int position = parent.getChildAdapterPosition(childAt);
        int left = parent.getPaddingLeft();

        int right = childAt.getWidth() - parent.getPaddingRight();
        if (childAt.getBottom() <= mGroupDividerHeight && isGroupFirst(position + 1)) {
            mPaint.setColor(mGroupColor);
            c.drawRect(left, 0, right, childAt.getBottom(), mPaint);
            float baseLine = childAt.getBottom() / 2f - (mPaint.descent() + mPaint.ascent()) / 2f;
            mPaint.setColor(mCellingColor);
            c.drawText(getGroupName(position), left + DpUtils.dp2px(mContext, 10), baseLine, mPaint);
        } else {
            mPaint.setColor(mGroupColor);
            c.drawRect(left, 0, right, mGroupDividerHeight, mPaint);
            float baseLine = mGroupDividerHeight / 2f - (mPaint.descent() + mPaint.ascent()) / 2f;
            mPaint.setColor(mCellingColor);
            mPaint.setTextSize(DpUtils.sp2px(mContext, 20));
//            c.drawText(getGroupName(position), left + DpUtils.dp2px(mContext, 10), baseLine, mPaint);
            c.drawText(getGroupName(position), left + DpUtils.dp2px(mContext, 10), baseLine, mPaint);
        }

    }

    private boolean isGroupFirst(int pos) {
        if (pos == 0) {
            return true;
        } else {
            String preGroupName = getGroupName(pos - 1);
            String curGroupName = getGroupName(pos);
            return !preGroupName.equals(curGroupName);
        }
    }

    private String getGroupName(int pos) {
        if (onGroupController != null) {
            String groupName = onGroupController.getGroupName(pos);
            return groupName;
        }
        return "";
    }


    GroupController onGroupController;

    public interface GroupController {
        String getGroupName(int pos);
    }
}
