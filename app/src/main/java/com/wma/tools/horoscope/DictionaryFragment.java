package com.wma.tools.horoscope;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;

import com.wma.tools.R;
import com.wma.tools.databinding.FragmentDictionaryBinding;
import com.wma.tools.news.NewsFragment;
import com.wma.wmalib.base.fragment.BaseFragment;

/**
 * Created by 王明骜 on 19-9-4 下午5:13.
 */
public class DictionaryFragment extends BaseFragment<FragmentDictionaryBinding> {
    FragmentManager fgManager;

    int mCurPos = 0;

    @Override
    protected void createContentView(ViewGroup container, FragmentDictionaryBinding binding) {

    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_dictionary;
    }

    @Override
    public void create(Bundle savedInstanceState) {
        fgManager = getChildFragmentManager();
        FragmentTransaction transaction = fgManager.beginTransaction();
        transaction.add(R.id.container,new PinYinFragment());
        transaction.commit();
    }
    
    public void goNext(){
        FragmentTransaction transaction = fgManager.beginTransaction();
        transaction.replace(R.id.container,new PinYinFragment());
        transaction.commit();
        Log.d("WMA-WMA", "goNext: ");
    }
    public void back(){
        FragmentTransaction transaction = fgManager.beginTransaction();
        transaction.replace(R.id.container,new PinYinFragment());
        transaction.commit();
        Log.d("WMA-WMA", "back: ");
    }
}
