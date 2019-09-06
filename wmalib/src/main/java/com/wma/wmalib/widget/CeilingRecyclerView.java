package com.wma.wmalib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by 王明骜 on 19-9-4 下午4:18.
 */
public class CeilingRecyclerView extends ViewGroup {
    Context mContext;
    public CeilingRecyclerView(Context context) {
        super(context);
        init(context,null,0);
    }

    public CeilingRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public CeilingRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
