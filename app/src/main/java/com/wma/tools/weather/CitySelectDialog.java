package com.wma.tools.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.wma.tools.R;
import com.wma.tools.databinding.ItemProvinceBinding;
import com.wma.tools.model.weather.Model;
import com.wma.tools.model.weather.ProvinceModel;
import com.wma.tools.utils.SPUtils;
import com.wma.wmalib.base.adapter.BaseRecyclerViewAdapter;
import com.wma.wmalib.utils.DpUtils;
import com.wma.wmalib.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 王明骜 on 19-9-2 下午5:25.
 */
public class CitySelectDialog extends DialogFragment {
    RecyclerView mProvinceView, mCityView, mDistView;
    List<ProvinceModel> mProvinces = new ArrayList<>();
    List<ProvinceModel> mCities = new ArrayList<>();
    List<ProvinceModel> mDists = new ArrayList<>();
    int mCurProvincePos = 0;
    int mCurCityPos = 0;
    MyAdapter mProvinceAdapter;
    MyAdapter mCityAdapter;
    MyAdapter mDistAdapter;
    int mCurDistPos = 0;
    Model model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_city_select, container);
        mProvinceView = view.findViewById(R.id.province_view);
        mCityView = view.findViewById(R.id.city_view);
        mDistView = view.findViewById(R.id.dist_view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawableResource(android.R.color.white);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = DpUtils.dp2px(getContext(), 360);
        window.setAttributes(wlp);

    }

    //    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        Bundle arguments = getArguments();
//        String title = arguments.getString("title");
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle(title);
//        builder.setMessage("删除歌曲？");
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(getContext(), "确定", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(getContext(), "取消", Toast.LENGTH_SHORT).show();
//            }
//        });
//        return builder.create();
//    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //省份
        showProvince();

        //城市
        showCity();


        //地区
        showDist();


    }

    private void showDist() {
        mDists.clear();
        String read = FileUtils.read(getContext(), "P_"+mProvinces.get(mCurProvincePos).getName());
        Model model = new Gson().fromJson(read, Model.class);
        List<Model.ResultBean> result = model.getResult();
        for (int i = 0; i < result.size(); i++) {
            Model.ResultBean resultBean = result.get(i);
            String district = resultBean.getDistrict();
            if (SPUtils.getCurCity().contains(resultBean.getCity())) {
                mDists.add(new ProvinceModel(district, false));
            }
        }
        if (mDistAdapter == null) {
            mDistAdapter = new MyAdapter();
        } else {
            mDistAdapter.clearItems();
        }
        mDistAdapter.clearSelectedState();
        mCurDistPos = getCurPosition(SPUtils.getCurDist(), mDists);
        initData(mDistView, mDistAdapter, mCurDistPos, mDists);
        mDistAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<ProvinceModel>() {
            @Override
            public void onItemClick(View covertView, int position, ProvinceModel data) {
                if(mCurDistPos != -1){
                    mDistAdapter.setUnChecked(mCurDistPos);
                }
                mCurDistPos = position;
                mDistAdapter.setChecked(mCurDistPos);
                SPUtils.setCurDist(data.getName());
                dismiss();
                if (onRefreshWeather != null) {
                    onRefreshWeather.onRefresh(data.getName());
                }
            }
        });
    }

    private void showCity() {
        mCities.clear();
        String read = FileUtils.read(getContext(), "P_"+mProvinces.get(mCurProvincePos).getName());
        Model model = new Gson().fromJson(read, Model.class);
        List<Model.ResultBean> result = model.getResult();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < result.size(); i++) {
            set.add(result.get(i).getCity());
        }
        Object[] objects = set.toArray();
        for (int i = 0; i < objects.length; i++) {
            String cityName = (String) objects[i];
            mCities.add(new ProvinceModel(cityName, false));

        }
        if (mCityAdapter == null) {
            mCityAdapter = new MyAdapter();
        } else {
            mCityAdapter.clearItems();
        }
        mCityAdapter.clearSelectedState();
        mCurCityPos = getCurPosition(SPUtils.getCurCity(), mCities);
        initData(mCityView, mCityAdapter, mCurCityPos, mCities);
        mCityAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<ProvinceModel>() {
            @Override
            public void onItemClick(View covertView, int position, ProvinceModel data) {
                if(mCurCityPos != -1){
                    mCityAdapter.setUnChecked(mCurCityPos);
                }
                mCurCityPos = position;
                mCityAdapter.setChecked(mCurCityPos);
                SPUtils.setCurCity(data.getName());
                showDist();
            }
        });
    }

    private void showProvince() {
        mProvinces.clear();
        File filesDir = getContext().getFilesDir();
        String[] list = filesDir.list();
        for (int i = 0; i < list.length; i++) {
            if (list[i].startsWith("P_")) {
                mProvinces.add(new ProvinceModel(list[i].substring(2, list[i].length()), false));
            }
        }
        if (mProvinceAdapter == null) {
            mProvinceAdapter = new MyAdapter();
        } else {
            mProvinceAdapter.clearItems();
        }
        mProvinceAdapter.clearSelectedState();
        mCurProvincePos = getCurPosition(SPUtils.getCurProvince(), mProvinces);
        initData(mProvinceView, mProvinceAdapter, mCurProvincePos, mProvinces);
        mProvinceAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<ProvinceModel>() {
            @Override
            public void onItemClick(View covertView, int position, ProvinceModel data) {
                if(mCurProvincePos != -1){
                    mProvinceAdapter.setUnChecked(mCurProvincePos);
                }
                mCurProvincePos = position;
                mProvinceAdapter.setChecked(mCurProvincePos);
                SPUtils.setCurProvince(data.getName());
                clearData();
                showCity();
            }
        });
    }

    private void clearData() {
        mCities.clear();
        mCityAdapter.clearItems();
        mDists.clear();
        mDistAdapter.clearItems();
    }

    public static CitySelectDialog newInstance(Bundle bundle) {
        CitySelectDialog citySelectDialog = new CitySelectDialog();
        if (bundle != null) {
            citySelectDialog.setArguments(bundle);
        }
        return citySelectDialog;

    }

    class MyAdapter extends BaseRecyclerViewAdapter<ProvinceModel, ItemProvinceBinding> {


        @Override
        protected int getItemLayoutId(int viewType) {
            return R.layout.item_province;
        }

        @Override
        protected void bindData(final ItemProvinceBinding binding, final ProvinceModel item, final int position) {
            binding.tvItem.setText(item.getName());
            binding.tvItem.setChecked(isSelected(position));
        }
    }

    void initData(RecyclerView recyclerView, MyAdapter adapter, int curPos, List<ProvinceModel> list) {
        recyclerView.setAdapter(adapter);
        adapter.addItems(list);
        if(curPos == -1){
            return;
        }
        recyclerView.scrollToPosition(curPos);
        adapter.setChecked(curPos);
    }

    int getCurPosition(String name, List<ProvinceModel> list) {
        if(TextUtils.isEmpty(name)){
            show(getFragmentManager(),"a");
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            if (name.contains(list.get(i).getName())) {
                return i;
            }
        }
        return 0;

    }


    public void setOnRefreshWeather(RefreshWeather onRefreshWeather) {
        this.onRefreshWeather = onRefreshWeather;
    }

    RefreshWeather onRefreshWeather;

    public interface RefreshWeather {
        void onRefresh(String name);
    }
}

