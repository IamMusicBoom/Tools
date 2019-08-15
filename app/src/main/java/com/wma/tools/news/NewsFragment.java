package com.wma.tools.news;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wma.tools.R;
import com.wma.tools.databinding.FragmentNewsBinding;
import com.wma.tools.model.IAllApi;
import com.wma.wmalib.base.fragment.BaseTabFragment;
import com.wma.wmalib.http.HttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王明骜 on 19-8-6 下午3:48.
 */
public class NewsFragment extends BaseTabFragment<FragmentNewsBinding> {
    public FragmentNewsBinding mBinding;
//    top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
    String titles[] = {"头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};
    public Map<String,String> keyType = new HashMap<>();
    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void onCreate(Bundle savedInstanceState,  FragmentNewsBinding binding) {
        HttpUtils.httpUtils.init(IAllApi.NEWS_HOST);
        mBinding = binding;
       initViews();
        keyType.put("头条","top");
        keyType.put("社会","shehui");
        keyType.put("国内","guonei");
        keyType.put("国际","guoji");
        keyType.put("娱乐","yule");
        keyType.put("体育","tiyu");
        keyType.put("军事","junshi");
        keyType.put("科技","keji");
        keyType.put("财经","caijing");
        keyType.put("时尚","shishang");
    }

    @Override
    public List<Fragment> getFragments() {
        List<Fragment> list = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            list.add(new NewsListFragment());
        }
        return list;
    }

    @Override
    public ViewPager getViewPager() {
        return mBinding.viewpager;
    }

    @Override
    public TabLayout getTabLayout() {
        return mBinding.tabLayout;
    }

    @Override
    public List<String> getTitles() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            list.add(titles[i]);
        }
        return list;
    }

    @Override
    public List<Drawable> getIcons() {
        return null;
    }
}
