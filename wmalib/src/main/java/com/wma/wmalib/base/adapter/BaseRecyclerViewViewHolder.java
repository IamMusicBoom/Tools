package com.wma.wmalib.base.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 王明骜 on 19-8-12 下午1:16.
 */
public class BaseRecyclerViewViewHolder<E extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public BaseRecyclerViewViewHolder(@NonNull View itemView) {
        super(itemView);
    }


   public E getBinding(){
        return DataBindingUtil.getBinding(itemView);
    }

}
