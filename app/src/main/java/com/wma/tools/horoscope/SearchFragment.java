package com.wma.tools.horoscope;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wma.tools.R;
import com.wma.tools.databinding.FragmentSearchBinding;
import com.wma.tools.databinding.ItemHistoryBinding;
import com.wma.wmalib.base.fragment.BaseFragment;
import com.wma.wmalib.base.fragment.BaseListFragment;
import com.wma.wmalib.utils.FileUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 王明骜 on 19-9-9 下午3:16.
 */
public class SearchFragment extends BaseListFragment<String, ItemHistoryBinding,FragmentSearchBinding> {
    WeakReference<FragmentSearchBinding> mWeakBinding;
    List<String> mList;
    @Override
    protected void createContentView(ViewGroup container, FragmentSearchBinding binding) {
        mWeakBinding = new WeakReference<>(binding);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void create(Bundle savedInstanceState) {
        mRecyclerView = mWeakBinding.get().recyclerView;
        mWeakBinding.get().imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = mWeakBinding.get().etSearch.getText().toString();
                if(TextUtils.isEmpty(word)){
                    return;
                }
                if(mList.size()==50){
                    mList.remove(mList.size()-1);
                }else if(mList.size()<10){
                    mList.contains(word);
                    mList.remove(word);
                }
                mList.add(0,word);
                mWeakBinding.get().etSearch.setText("");
                FileUtils.write(getContext(),"history",new Gson().toJson(mList));
                getListData();
                ((DictionaryKindActivity) getActivity()).goNext(word,word);
            }
        });
        getListData();
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    protected View getEmptyView() {
        return null;
    }

    @Override
    public void getListData() {
        if (FileUtils.isFileExist(getContext().getFilesDir().getAbsolutePath()+"/"+"history")) {
            String history = FileUtils.read(getContext(), "history");
            mList = new Gson().fromJson(history, new TypeToken<List<String>>() {
            }.getType());
            if(mAdapter != null){
                mAdapter.clearItems();
            }
            handleData(mList);
        }else{
            mList = new ArrayList<>();
        }
    }

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.item_history;
    }

    @Override
    public void bindItemData(ItemHistoryBinding itemHistoryBinding, final String info, int position) {
        itemHistoryBinding.tvHistory.setText(info);
    }
}
