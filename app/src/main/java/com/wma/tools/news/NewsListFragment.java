package com.wma.tools.news;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wma.tools.R;
import com.wma.tools.databinding.FragmentNewsBinding;
import com.wma.tools.databinding.FragmentNewsListBinding;
import com.wma.tools.databinding.ItemNewsBinding;
import com.wma.tools.model.BModel;
import com.wma.tools.model.news.NewsModel;
import com.wma.wmalib.base.adapter.BaseRecyclerViewAdapter;
import com.wma.wmalib.base.fragment.BaseFragment;
import com.wma.wmalib.base.fragment.BaseListFragment;
import com.wma.wmalib.callback.HttpCallBack;
import com.wma.wmalib.glide.GlideUtil;
import com.wma.wmalib.pulltorefresh.library.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王明骜 on 19-8-9 下午2:12.
 */
public class NewsListFragment extends BaseListFragment<NewsModel.ResultBean.DataBean, ItemNewsBinding, FragmentNewsListBinding>  {
    FragmentNewsListBinding mBinding;
    FragmentNewsBinding mParentBinding;
    NewsFragment mParent;

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_news_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, FragmentNewsListBinding binding) {
        mBinding = binding;
        mParent = ((NewsFragment) getParentFragment());
        mParentBinding = ((NewsFragment) getParentFragment()).mBinding;
        mRecyclerView = (PullToRefreshRecyclerView) ((ViewGroup) mBinding.getRoot()).getChildAt(0);
    }

    private void goGetDatas(String s) {
        new NewsModel().getDatas(s, new HttpCallBack<BModel.ResultBean>() {
            @Override
            public void onBegin() {
//                Log.d("WMA-WMA", "onBegin: ");
                showLoading();
            }

            @Override
            public void onComplete() {
//                Log.d("WMA-WMA", "onComplete: ");
                hideLoading();
                if(getAdapter() != null){
                    getAdapter().setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<NewsModel.ResultBean.DataBean>() {
                        @Override
                        public void onItemClick(View covertView, int position, NewsModel.ResultBean.DataBean data) {
                            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                            intent.putExtra("url", data.getUrl());
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onSuccess(BModel.ResultBean resultBean) {
//                Log.d("WMA-WMA", "onSuccess: ");
                List<BModel.ResultBean.DataBean> data = resultBean.getData();
                handleData(data);
            }

            @Override
            public void onFail(String e) {
//                Log.d("WMA-WMA", "onFail: " + e);
                showEmptyView(true);

            }

            @Override
            public void onError(Throwable e) {
//                Log.d("WMA-WMA", "onError: " + e);
                showEmptyView(true);

            }
        });
    }

    @Override
    protected void loadData() {
        getListData();

    }

    @Override
    protected View getEmptyView() {
        return ((ViewGroup) mBinding.getRoot()).getChildAt(1);
    }

    @Override
    public void getListData() {
        String s = mParent.keyType.get(mParent.adapter.getPageTitle(mParentBinding.viewpager.getCurrentItem()));
        Log.d("WMA-WMA", "handleData: " + s + "   " + this);
        goGetDatas(s);
    }

    @Override
    public int getItemLayout(int viewTyoe) {
        return R.layout.item_news;
    }

    @Override
    public void bindItemData(ItemNewsBinding itemNewsBinding, final NewsModel.ResultBean.DataBean info, int position) {
        itemNewsBinding.newsAuthorTv.setText(info.getAuthor_name());
        itemNewsBinding.newsTitleTv.setText(info.getTitle());
        itemNewsBinding.newsDateTv.setText(info.getDate());
        showImage(info.getThumbnail_pic_s(), itemNewsBinding.img1);
        showImage(info.getThumbnail_pic_s02(), itemNewsBinding.img2);
        showImage(info.getThumbnail_pic_s03(), itemNewsBinding.img3);
        itemNewsBinding.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PictureDetailActivity.class);
                int index = 0;
                ArrayList<String> list = new ArrayList<>();
                list.add(info.getThumbnail_pic_s());
                if(!TextUtils.isEmpty(info.getThumbnail_pic_s02())){
                    list.add(info.getThumbnail_pic_s02());
                }
                if(!TextUtils.isEmpty(info.getThumbnail_pic_s03())){
                    list.add(info.getThumbnail_pic_s03());
                }
                intent.putExtra(PictureDetailActivity.EXTRA_IMAGE_INDEX,index);
                intent.putStringArrayListExtra(PictureDetailActivity.EXTRA_IMAGE_URLS,list);
                startActivity(intent);
            }
        });
        itemNewsBinding.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PictureDetailActivity.class);
                int index = 0;
                ArrayList<String> list = new ArrayList<>();
                index = 1;
                list.add(info.getThumbnail_pic_s02());
                if(!TextUtils.isEmpty(info.getThumbnail_pic_s())){
                    list.add(info.getThumbnail_pic_s());
                }
                if(!TextUtils.isEmpty(info.getThumbnail_pic_s03())){
                    list.add(info.getThumbnail_pic_s03());
                }
                intent.putExtra(PictureDetailActivity.EXTRA_IMAGE_INDEX,index);
                intent.putStringArrayListExtra(PictureDetailActivity.EXTRA_IMAGE_URLS,list);
                startActivity(intent);
            }
        });
        itemNewsBinding.img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PictureDetailActivity.class);
                int index = 0;
                ArrayList<String> list = new ArrayList<>();
                index = 2;
                list.add(info.getThumbnail_pic_s03());
                if(!TextUtils.isEmpty(info.getThumbnail_pic_s02())){
                    list.add(info.getThumbnail_pic_s02());
                }
                if(!TextUtils.isEmpty(info.getThumbnail_pic_s03())){
                    list.add(info.getThumbnail_pic_s03());
                }
                intent.putExtra(PictureDetailActivity.EXTRA_IMAGE_INDEX,index);
                intent.putStringArrayListExtra(PictureDetailActivity.EXTRA_IMAGE_URLS,list);
                startActivity(intent);
            }
        });
    }

    private void showImage(String picUrl, ImageView img) {
        if (TextUtils.isEmpty(picUrl)) {
            img.setVisibility(View.GONE);
        } else {
            img.setVisibility(View.VISIBLE);
            picUrl = picUrl.replace("http://", "https://");
            GlideUtil.getInstance().loadImage(getActivity(), img, picUrl, true);
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
