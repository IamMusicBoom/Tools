package com.wma.tools.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wma.tools.ToolApplication;
import com.wma.tools.model.weather.Model;
import com.wma.wmalib.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王明骜 on 19-9-4 下午2:44.
 */
public class Utils {
   static Context mContext =  ToolApplication.getInstance();
    public static String formatProvince(String name){
        File filesDir = mContext.getFilesDir();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < filesDir.list().length;i++) {
            String s = filesDir.list()[i];
            if(s.startsWith("P_")){
                String provinceName = s.substring(2,s.length());
                list.add(provinceName);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if(name.contains(list.get(i))){
                return list.get(i);
            }
        }
        return "";

    }

    public static String formatCity(String name){
        String read = FileUtils.read(mContext, "P_"+SPUtils.getCurProvince());
        Model model = new Gson().fromJson(read, Model.class);
        List<Model.ResultBean> result = model.getResult();
        for (int i = 0; i < result.size(); i++) {
            Model.ResultBean resultBean = result.get(i);
            String city = resultBean.getCity();
            if(name.contains(city)){
                return city;
            }
        }
        return "";
    }

    public static String formatDist(String name){
        if(name.contains(SPUtils.getCurProvince())){
            name = name.replace(SPUtils.getCurProvince(),"");
        }
        if(name.contains(SPUtils.getCurCity())){
            name = name.replace(SPUtils.getCurCity(),"");
        }
        String read = FileUtils.read(mContext, "P_"+SPUtils.getCurProvince());
        Model model = new Gson().fromJson(read, Model.class);
        List<Model.ResultBean> result = model.getResult();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            Model.ResultBean resultBean = result.get(i);
            String city = resultBean.getCity();
            if(SPUtils.getCurCity().equals(city)){
                list.add(resultBean.getDistrict());
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if(name.contains(list.get(i))){
                return list.get(i);
            }
        }
        return SPUtils.getCurCity();
    }
}
