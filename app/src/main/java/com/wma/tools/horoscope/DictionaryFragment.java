package com.wma.tools.horoscope;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wma.tools.R;
import com.wma.tools.databinding.FragmentDictionaryBinding;
import com.wma.tools.news.NewsFragment;
import com.wma.wmalib.base.fragment.BaseFragment;

/**
 * Created by 王明骜 on 19-9-4 下午5:13.
 */
public class DictionaryFragment extends BaseFragment<FragmentDictionaryBinding> {

    @Override
    protected void createContentView(ViewGroup container, FragmentDictionaryBinding binding) {
        binding.tv0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),DictionaryKindActivity.class);
                intent.putExtra("position", 0);
                intent.putExtra("title", ((TextView) v).getText().toString());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(),v,"title").toBundle());
                }else{
                    startActivity(intent);
                }
            }
        });
        binding.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),DictionaryKindActivity.class);
                intent.putExtra("position", 1);
                intent.putExtra("title", ((TextView) v).getText().toString());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(),v,"title").toBundle());
                }else{
                    startActivity(intent);
                }
            }
        });
        binding.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),DictionaryKindActivity.class);
                intent.putExtra("position", 2);
                intent.putExtra("title", ((TextView) v).getText().toString());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(),v,"title").toBundle());
                }else{
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_dictionary;
    }

    @Override
    public void create(Bundle savedInstanceState) {

    }
}
