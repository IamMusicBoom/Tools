package com.wma.tools.weather;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;

import com.wma.tools.R;
import com.wma.tools.databinding.FragmentWeatherBinding;
import com.wma.tools.model.IAllApi;
import com.wma.tools.model.weather.WeatherAdapter;
import com.wma.tools.model.weather.WeatherModel;
import com.wma.tools.utils.Common;
import com.wma.tools.utils.SPUtils;
import com.wma.wmalib.base.fragment.BaseFragment;
import com.wma.wmalib.callback.HttpCallBack;
import com.wma.wmalib.common.LogUtils;
import com.wma.wmalib.common.WCommon;
import com.wma.wmalib.http.HttpUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王明骜 on 19-8-7 上午11:02.
 */
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {
    private final String TAG = this.getClass().getSimpleName();
    List<String> mDetailList = new ArrayList<>();
    List<WeatherModel.ResultBean.FutureBean> mFeatures = null;
    WeakReference<FragmentWeatherBinding> mWeakBinding;

    private WeatherAdapter mAdapter;

    @Override
    protected void createContentView(ViewGroup container, FragmentWeatherBinding binding) {
        mWeakBinding = new WeakReference<>(binding);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_weather;
    }

    @Override
    public void create(Bundle savedInstanceState) {
        mWeakBinding.get().swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }

    private void getData() {
        HttpUtils.httpUtils.init(IAllApi.WEATHER_HOST);
        String curDist = SPUtils.getCurDist();
        new WeatherModel().getDatas(curDist, new HttpCallBack<WeatherModel>() {
            @Override
            public void onBegin() {
                mWeakBinding.get().swipeView.setRefreshing(true);
            }

            @Override
            public void onComplete() {
                mWeakBinding.get().swipeView.setRefreshing(false);
            }

            @Override
            public void onSuccess(WeatherModel weatherModel) {
                WeatherModel.ResultBean result = weatherModel.getResult();
                String city = Common.CITY + ":" + (TextUtils.isEmpty(result.getCity()) ? "null" : result.getCity());
                mDetailList.add(city);
                WeatherModel.ResultBean.RealtimeBean realtime = result.getRealtime();

                String aqi = Common.AIQ + ":" +  (TextUtils.isEmpty(realtime.getAqi()) ? "null" : realtime.getAqi());
                mDetailList.add(aqi);

                String direct = Common.DIRECT + ":" +  (TextUtils.isEmpty(realtime.getDirect()) ? "null" : realtime.getDirect());
                mDetailList.add(direct);

                String humidity = Common.HUMIDITY + ":" +  (TextUtils.isEmpty(realtime.getHumidity()) ? "null" : realtime.getHumidity());
                mDetailList.add(humidity);

                String info = Common.INFO + ":" +  (TextUtils.isEmpty(realtime.getInfo()) ? "null" : realtime.getInfo());
                mDetailList.add(info);

                String power = Common.POWER + ":" + (TextUtils.isEmpty(realtime.getPower()) ? "null" : realtime.getPower());
                mDetailList.add(power);

                String temperature = Common.TEMPERATURE + ":" + (TextUtils.isEmpty(realtime.getTemperature()) ? "null" : realtime.getTemperature());
                mDetailList.add(temperature);

                String wid = Common.WID + ":" +  (TextUtils.isEmpty(realtime.getWid()) ? "null" : realtime.getWid());
                mDetailList.add(wid);
                mFeatures = result.getFuture();
                initData(realtime);
            }

            @Override
            public void onFail(String e) {
                mWeakBinding.get().swipeView.setRefreshing(false);
                LogUtils.d(TAG, e);
            }

            @Override
            public void onError(Throwable e) {
                mWeakBinding.get().swipeView.setRefreshing(false);
                LogUtils.d(TAG, e.toString());
            }
        });
    }

    private void initData(WeatherModel.ResultBean.RealtimeBean realtime) {
        String wid = realtime.getWid();
        if(!TextUtils.isEmpty(wid)){
            Integer integer = Integer.valueOf(wid);
            if(integer == 0){
                mWeakBinding.get().swipeView.setBackgroundResource(R.mipmap.sunny);
            }else if(integer == 1 || integer == 2){
                mWeakBinding.get().swipeView.setBackgroundResource(R.mipmap.cloudy);
            }else if(integer >=3 && integer<=12){
                mWeakBinding.get().swipeView.setBackgroundResource(R.mipmap.rain);
            }else if(integer >=13 && integer<=17){
                mWeakBinding.get().swipeView.setBackgroundResource(R.mipmap.snow);
            }else if(integer == 18){
                mWeakBinding.get().swipeView.setBackgroundResource(R.mipmap.fog);
            }else if(integer == 19){
                mWeakBinding.get().swipeView.setBackgroundResource(R.mipmap.rain);
            }else if(integer == 20){
                mWeakBinding.get().swipeView.setBackgroundResource(R.mipmap.sand);
            }else if(integer >=21 && integer<=25){
                mWeakBinding.get().swipeView.setBackgroundResource(R.mipmap.rain);
            }else if(integer >=26 && integer<=28){
                mWeakBinding.get().swipeView.setBackgroundResource(R.mipmap.snow);
            }else if(integer >=29 && integer<=31){
                mWeakBinding.get().swipeView.setBackgroundResource(R.mipmap.sand);
            }else if (integer == 53){
                mWeakBinding.get().swipeView.setBackgroundResource(R.mipmap.smog);
            }else{
                mWeakBinding.get().swipeView.setBackgroundResource(R.mipmap.welcome);
            }
        }

        if (mAdapter == null) {
            mAdapter = new WeatherAdapter(mDetailList, mFeatures);
            mWeakBinding.get().recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addList(mDetailList, mFeatures);
        }

    }

    @Override
    protected void lazyLoad() {
        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mAdapter!= null){
            mAdapter.clear();
            mAdapter = null;
        }
    }
}
