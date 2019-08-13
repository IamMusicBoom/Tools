package com.wma.wmalib.base;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
/**
 * Created by 王明骜 on 19-8-12 下午1:48.
 */
public class SpaceItemDecoration  extends RecyclerView.ItemDecoration{
    private int space;
    private boolean includeEdge;
    public SpaceItemDecoration(int spacing, boolean includeEdge) {
        this.space = spacing;
        this.includeEdge = includeEdge;
    }
    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position=parent.getChildAdapterPosition(view);
        if(position!= 0){
            outRect.top = space;
            if(position==parent.getChildCount()&&includeEdge){
                outRect.bottom = space;
            }

        }
    }
}
