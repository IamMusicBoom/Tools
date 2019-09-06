package com.wma.tools.model.weather;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wma.tools.R;
import com.wma.tools.ToolApplication;
import com.wma.tools.WelComeActivity;
import com.wma.tools.model.weather.view.CircleLevelView;
import com.wma.tools.model.weather.view.LocatingView;
import com.wma.tools.model.weather.view.MWindMillView;
import com.wma.tools.utils.Common;
import com.wma.tools.utils.SPUtils;

import java.util.List;

/**
 * Created by 王明骜 on 19-8-30 下午3:03.
 */
public class WeatherAdapter extends RecyclerView.Adapter {

    List<String> mDetailList;
    List<WeatherModel.ResultBean.FutureBean> mFeatures;

    private final int TEMPERATURE_TYPE = 1;

    private final int HUMIDITY_TYPE = 2;

    private final int WIND_TYPE = 3;

    private final int FEATURE_TYPE = 4;

    public WeatherAdapter(List<String> mDetailList, List<WeatherModel.ResultBean.FutureBean> mFutures) {
        this.mDetailList = mDetailList;
        this.mFeatures = mFutures;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        switch (itemType) {
            case TEMPERATURE_TYPE:
                View temperatureView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_temperature, viewGroup, false);
                return new WeatherTempHolder(temperatureView);
            case HUMIDITY_TYPE:
                View humidityView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_humidity, viewGroup, false);
                return new WeatherHumidityHolder(humidityView);
            case WIND_TYPE:
                View windView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_wind, viewGroup, false);
                return new WeatherWindHolder(windView);
            case FEATURE_TYPE:
                View featureView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_feature, viewGroup, false);
                return new WeatherFutureHolder(featureView);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TEMPERATURE_TYPE:
                for (int i = 0; i < mDetailList.size(); i++) {
                    String s = mDetailList.get(i);
                    String[] split = s.split(":");
                    if (split[0].equalsIgnoreCase(Common.CITY)) {
                        ((WeatherTempHolder) viewHolder).m1TvCity.setText(split[1]);
                    } else if (split[0].equalsIgnoreCase(Common.TEMPERATURE)) {
                        ((WeatherTempHolder) viewHolder).m1TvTemp.setText(split[1]);
                    } else if (split[0].equalsIgnoreCase(Common.INFO)) {
                        ((WeatherTempHolder) viewHolder).m1TvWid.setText(split[1]);
                    } else if (split[0].equalsIgnoreCase(Common.WID)) {
                        if (!TextUtils.isEmpty(split[1])) {
                            ((WeatherTempHolder) viewHolder).m1ImgWid.setImageResource(Common.WID_IMG_MAP.get(split[1]));
                        }
                    }
                }
                break;
            case HUMIDITY_TYPE:
                for (int i = 0; i < mDetailList.size(); i++) {
                    String s = mDetailList.get(i);
                    String[] split = s.split(":");
                    if (split[0].equalsIgnoreCase(Common.HUMIDITY)) {
                        String humidity = split[1];
                        if (TextUtils.isEmpty(humidity)) {
                            humidity = "99";
                        }
                        ((WeatherHumidityHolder) viewHolder).m2CirView.setLevel(Integer.valueOf(humidity));
                        ((WeatherHumidityHolder) viewHolder).m2TvHumidity.setText(humidity);
                    }
                }
                break;
            case WIND_TYPE:
                for (int i = 0; i < mDetailList.size(); i++) {
                    String s = mDetailList.get(i);
                    String[] split = s.split(":");
                    if (split[0].equalsIgnoreCase(Common.POWER)) {
                        String power = split[1];
                        if (!TextUtils.isEmpty(power)) {
                            String substring = power.substring(0, power.length() - 1);
                            ((WeatherWindHolder) viewHolder).m3WindView.setWindLevel(Integer.valueOf(substring));
                            ((WeatherWindHolder) viewHolder).m3TvPower.setText(power);
                        }
                    } else if (split[0].equalsIgnoreCase(Common.DIRECT)) {
                        ((WeatherWindHolder) viewHolder).m3TvWind.setText(split[1]);
                    }
                }
                break;
            case FEATURE_TYPE:
                WeatherModel.ResultBean.FutureBean futureBean = mFeatures.get(position - 3);
                String date = futureBean.getDate().substring(5, futureBean.getDate().length());
                ((WeatherFutureHolder) viewHolder).m4TvDate.setText(date);
                ((WeatherFutureHolder) viewHolder).m4TvTemp.setText(futureBean.getTemperature());
                ((WeatherFutureHolder) viewHolder).m4TvWid.setText(futureBean.getWeather());
                ((WeatherFutureHolder) viewHolder).m4TvDirect.setText(futureBean.getDirect());

                if (futureBean.getWid() != null) {
                    WeatherModel.ResultBean.FutureBean.WidBean wid = futureBean.getWid();
                    String day = wid.getDay();
                    String night = wid.getNight();
                    if (day != null) {
                        ((WeatherFutureHolder) viewHolder).m4ImgDay.setVisibility(View.VISIBLE);
                        ((WeatherFutureHolder) viewHolder).m4ImgDay.setImageResource(Common.WID_IMG_MAP.get(day));
                    } else {
                        ((WeatherFutureHolder) viewHolder).m4ImgDay.setVisibility(View.INVISIBLE);
                    }

                    if (night != null) {
                        ((WeatherFutureHolder) viewHolder).m4ImgNight.setVisibility(View.VISIBLE);
                        ((WeatherFutureHolder) viewHolder).m4ImgNight.setImageResource(Common.WID_IMG_MAP.get(night));
                    } else {
                        ((WeatherFutureHolder) viewHolder).m4ImgNight.setVisibility(View.INVISIBLE);
                    }
                    if (day != null && night != null && day.equals(night)) {
                        ((WeatherFutureHolder) viewHolder).m4ImgNight.setVisibility(View.INVISIBLE);
                        ((WeatherFutureHolder) viewHolder).m4ImgDay.setVisibility(View.INVISIBLE);
                        ((WeatherFutureHolder) viewHolder).m4ImgArrow.setVisibility(View.VISIBLE);
                        ((WeatherFutureHolder) viewHolder).m4ImgArrow.setImageResource(Common.WID_IMG_MAP.get(day));
                    } else {
                        ((WeatherFutureHolder) viewHolder).m4ImgNight.setVisibility(View.VISIBLE);
                        ((WeatherFutureHolder) viewHolder).m4ImgDay.setVisibility(View.VISIBLE);
                        ((WeatherFutureHolder) viewHolder).m4ImgArrow.setVisibility(View.VISIBLE);
                        ((WeatherFutureHolder) viewHolder).m4ImgArrow.setImageResource(R.drawable.ic_right_arrow);
                    }
                }
                break;

        }
    }

    @Override
    public int getItemCount() {
        return 3 + mFeatures.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TEMPERATURE_TYPE;
        } else if (position == 1) {
            return HUMIDITY_TYPE;
        } else if (position == 2) {
            return WIND_TYPE;
        } else {
            return FEATURE_TYPE;
        }
    }

    public void clear() {
        mFeatures.clear();
        mDetailList.clear();
        notifyDataSetChanged();
    }

    public void addList(List<String> mDetailList, List<WeatherModel.ResultBean.FutureBean> mFeatures) {
        this.mDetailList.addAll(mDetailList);
        this.mFeatures.addAll(mFeatures);
        notifyDataSetChanged();
    }

    class WeatherTempHolder extends RecyclerView.ViewHolder {
        TextView m1TvCity, m1TvTemp, m1TvWid;
        ImageView m1ImgWid;
        LocatingView m1ImgLocate;


        public WeatherTempHolder(@NonNull View itemView) {
            super(itemView);
            m1TvCity = itemView.findViewById(R.id.tv_city);
            m1TvCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onCitySelectListener != null) {
                        onCitySelectListener.select();
                    }
                }
            });
            m1TvTemp = itemView.findViewById(R.id.tv_temp);
            m1TvWid = itemView.findViewById(R.id.tv_wid);
            m1ImgWid = itemView.findViewById(R.id.img_wid);
            m1ImgLocate = itemView.findViewById(R.id.img_locate);
            Log.d("WMA-WMA", "WeatherTempHolder: SPUtils.getLocateState() = " + SPUtils.getLocateState());
            m1ImgLocate.setCurState(SPUtils.getLocateState());
            m1ImgLocate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    m1ImgLocate.setCurState(LocatingView.LOCATING);
                   ToolApplication.getInstance().startService(new Intent(ToolApplication.getInstance(),LocateService.class));
                }
            });
        }
    }

    class WeatherHumidityHolder extends RecyclerView.ViewHolder {
        CircleLevelView m2CirView;
        TextView m2TvHumidity;

        public WeatherHumidityHolder(@NonNull View itemView) {
            super(itemView);
            m2TvHumidity = itemView.findViewById(R.id.tv_humidity);
            m2CirView = itemView.findViewById(R.id.cir_view);
        }

    }

    class WeatherWindHolder extends RecyclerView.ViewHolder {
        MWindMillView m3WindView;
        TextView m3TvWind, m3TvPower;

        public WeatherWindHolder(@NonNull View itemView) {
            super(itemView);
            m3TvWind = itemView.findViewById(R.id.tv_wind);
            m3TvPower = itemView.findViewById(R.id.tv_power);
            m3WindView = itemView.findViewById(R.id.wind_view);
        }

    }

    class WeatherFutureHolder extends RecyclerView.ViewHolder {
        TextView m4TvDate, m4TvWid, m4TvTemp, m4TvDirect;
        ImageView m4ImgDay, m4ImgArrow, m4ImgNight;

        public WeatherFutureHolder(@NonNull View itemView) {
            super(itemView);
            m4TvDate = itemView.findViewById(R.id.tv_date);
            m4TvWid = itemView.findViewById(R.id.tv_future_wid);
            m4TvTemp = itemView.findViewById(R.id.tv_future_temp);
            m4TvDirect = itemView.findViewById(R.id.tv_future_direct);
            m4ImgArrow = itemView.findViewById(R.id.img_arrow);
            m4ImgNight = itemView.findViewById(R.id.img_light);
            m4ImgDay = itemView.findViewById(R.id.img_day);
        }
    }


    public void setOnCitySelectListener(CitySelectListener onCitySelectListener) {
        this.onCitySelectListener = onCitySelectListener;
    }

    CitySelectListener onCitySelectListener;

    public interface CitySelectListener {
        void select();
    }

}
