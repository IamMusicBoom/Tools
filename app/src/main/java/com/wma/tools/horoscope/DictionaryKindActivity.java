package com.wma.tools.horoscope;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.wma.tools.R;
import com.wma.tools.databinding.ActivityDictionaryKindBinding;
import com.wma.wmalib.base.activity.BaseActivity;

public class DictionaryKindActivity extends BaseActivity<ActivityDictionaryKindBinding> {
    private int mCurPos;

    private FragmentManager fm;

    @Override
    public void onCreate(Bundle savedInstanceState, ActivityDictionaryKindBinding binding) {
        Intent intent = getIntent();
        mCurPos = intent.getIntExtra("position", 0);
        setTitle(intent.getStringExtra("title"), null);
        setLeftText("返回", R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fm = getSupportFragmentManager();
        switch (mCurPos) {
            case 0:
                ListFragment wordFragment = ListFragment.newInstance(mCurPos);
                FragmentTransaction word = fm.beginTransaction();
                word.add(R.id.container,wordFragment);
                word.commit();
                break;
            case 1:
                ListFragment buShouFragment = ListFragment.newInstance(mCurPos);
                FragmentTransaction buShou = fm.beginTransaction();
                buShou.add(R.id.container,buShouFragment);
                buShou.commit();
                break;
            case 2:
                ListFragment pinyinFragment = ListFragment.newInstance(mCurPos);
                FragmentTransaction pinyin = fm.beginTransaction();
                pinyin.add(R.id.container,pinyinFragment);
                pinyin.commit();
                break;
        }
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_dictionary_kind;
    }
}
