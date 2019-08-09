package com.wma.tools.news;

import android.os.Bundle;

import com.wma.tools.R;
import com.wma.tools.databinding.FragmentNewsBinding;
import com.wma.tools.model.BModel;
import com.wma.tools.model.news.NewsModel;
import com.wma.wmalib.base.BaseFragment;
import com.wma.wmalib.callback.HttpCallBack;

import java.util.List;

/**
 * Created by 王明骜 on 19-8-6 下午3:48.
 */
public class NewsFragment extends BaseFragment<FragmentNewsBinding> {

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, final FragmentNewsBinding binding) {
        NewsModel model = new NewsModel();
        model.getDatas("top", new HttpCallBack<NewsModel.ResultBean>() {
            @Override
            public void onSuccess(NewsModel.ResultBean resultBean) {
                List<BModel.ResultBean.DataBean> data = resultBean.getData();
                String title = data.get(0).getTitle();
                binding.tv.setText(title);
            }

            @Override
            public void onFail(String e) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
