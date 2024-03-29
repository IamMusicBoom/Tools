/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     16-12-19 上午11:56
 * ********************************************************
 */

package com.wma.wmalib.base.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


import static com.wma.wmalib.memory.LruCacheUtils.clear;
import static java.util.Collections.addAll;

/**
 * Created by 王明骜 on 19-8-12 上午11:20.
 */
public abstract class BaseRecyclerViewAdapter<T, E extends ViewDataBinding> extends RecyclerView.Adapter<BaseRecyclerViewViewHolder<E>> {
    protected Context mContext;
    private ArrayList<T> listData = new ArrayList<>();
    private OnItemClickListener<T> itemClickListener;
    private OnItemLongClickListener<T> itemLongClickListener;
    private long minClickIntervaltime = 100; //ITEM点击的最小间隔
    private long mLastClickTime;//上次点击时间
    private SparseBooleanArray selectedItems;

    public BaseRecyclerViewAdapter(Context context) {
        this.mContext = context;
        this.listData = new ArrayList<>();
        this.selectedItems = new SparseBooleanArray();

    }

    public BaseRecyclerViewAdapter(Context context, ArrayList<T> datas) {
        if (datas == null) datas = new ArrayList<>();
        this.mContext = context;
        this.listData = datas;
        this.selectedItems = new SparseBooleanArray();
    }

    public BaseRecyclerViewAdapter() {
        this.selectedItems = new SparseBooleanArray();
    }

    public boolean isSelected(int position) {
        return getSelectedItems().contains(position);
    }

    public void clearSelectedState() {
        List<Integer> selection = getSelectedItems();
        selectedItems.clear();
        for (Integer i : selection) {
            notifyItemChanged(i);
        }
    }

    public ArrayList<Integer> getSelectedItems() {
        ArrayList<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); ++i) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

    public void setChecked(int position) {
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }
        notifyItemChanged(position);
    }

    public void setUnChecked(int position) {
        if(selectedItems.get(position)){
            selectedItems.put(position, false);
            selectedItems.delete(position);
            notifyItemChanged(position);
        }
    }


    /**
     * 自己定义的GridLayoutManager的item需要跨的行数
     *
     * @return GridLayoutManager 0为占满，其他为占的具体行数,最多为占满
     */
    public int getGridItemSpanCount(int position, int viewType) {
        return 1;
    }

    /**
     * 客户自己定义的StaggeredLayoutManager的item是否需要占满
     *
     * @return true为占满, false为1行
     */
    public boolean getIsStaggeredItemFullSpan(int position, int viewType) {
        return false;
    }

    /**
     * 设置Item点击的最小间隔
     *
     * @param minClickIntervaltime millionSeconds
     */
    public void setItemMinClickIntervalTime(long minClickIntervaltime) {
        this.minClickIntervaltime = minClickIntervaltime;
    }

//    /**
//     * 设置显示数据,替代getView，在此函数中进行赋值操作
//     * <p>
//     * ex:
//     * TextView tvNumb = getView(view, R.id.tv);
//     * tvNumb.setText(String.valueOf(position + 1));
//     */
//    public abstract void setUpData(RecyclerView.ViewHolder holder, int position, int viewType, T data);

    public void setOnItemClickListener(OnItemClickListener<T> li) {
        itemClickListener = li;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> li) {
        itemLongClickListener = li;
    }

    /**
     * 插入一条数据
     */
    public void addItem(T item) {
        listData.add(0, item);
        notifyItemInserted(0);
    }

    /**
     * 插入一条数据
     */
    public void addItem(T item, int position) {
        position = Math.min(position, listData.size());
        listData.add(position, item);
        notifyItemInserted(position);
    }

    /**
     * 在列表尾添加一串数据
     */
    public void addItems(List<T> items) {
        int start = listData.size();
        listData.addAll(items);
        notifyItemRangeChanged(start, items.size());
    }


    public void updateItem(int position) {
        if (position > listData.size() - 1) {
            return;
        }
        notifyItemChanged(position);
    }

    /**
     * 清除所有数据
     */
    public void clearItems() {
        listData.clear();
        notifyDataSetChanged();
    }


    public void replaceItem(int position, T item) {
        if (item != null) {
            this.listData.set(getIndex(position), item);
            notifyItemChanged(position);
        }
    }

    public final void removeItem(T item) {
        if (this.listData.contains(item)) {
            int position = listData.indexOf(item);
            this.listData.remove(item);
            notifyItemRemoved(position);
        }
    }

    public final void removeItem(int position) {
        if (this.getItemCount() > position) {
            this.listData.remove(getIndex(position));
            notifyItemRemoved(position);
        }
    }

    public final T getItem(int position) {
        int p = getIndex(position);
        if (p < 0 || p >= listData.size())
            return null;
        return listData.get(getIndex(position));
    }

    public int getIndex(int position) {
        return position;
    }

    public final void resetItem(List<T> items) {
        if (items != null) {
            clear();
            addAll(items);
        }
    }


    /**
     * 将数据替换为传入的数据集
     */
    public void setItems(List<T> items) {
        listData.clear();
        if (items != null) {
            listData.addAll(items);
        }
        notifyDataSetChanged();
    }


    /**
     * 获取数据集
     */
    public ArrayList<T> getData() {
        return listData;
    }

    @NonNull
    @Override
    public BaseRecyclerViewViewHolder<E> onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater;
        inflater = LayoutInflater.from(viewGroup.getContext());
        View view = DataBindingUtil.inflate(inflater, getItemLayoutId(viewType), viewGroup, false).getRoot();
        return new BaseRecyclerViewViewHolder<>(view);
    }

    protected abstract int getItemLayoutId(int viewType);

    @Override
    public void onBindViewHolder(@NonNull final BaseRecyclerViewViewHolder<E> viewHolder, final int position) {
        final T data = listData.get(position);
        if (itemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long curTime = System.currentTimeMillis();
                    if (curTime - mLastClickTime > minClickIntervaltime) {
                        mLastClickTime = curTime;
                        itemClickListener.onItemClick(viewHolder.itemView, position, data);
                    }
                }
            });
        }

        if (itemLongClickListener != null) {
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    long curTime = System.currentTimeMillis();
                    if (curTime - mLastClickTime > minClickIntervaltime) {
                        mLastClickTime = curTime;
                        return itemLongClickListener.onItemLongClick(viewHolder.itemView, position, data);
                    }
                    return false;
                }
            });
        }
        bindData(viewHolder.getBinding(), getItem(position), position);

//        setUpData(viewHolder, position, getItemViewType(position), data);
    }

    protected abstract void bindData(E binding, T item, int position);

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public interface OnItemClickListener<T> {
        void onItemClick(View covertView, int position, T data);
    }

    public interface OnItemLongClickListener<T> {
        boolean onItemLongClick(View covertView, int position, T data);
    }


}