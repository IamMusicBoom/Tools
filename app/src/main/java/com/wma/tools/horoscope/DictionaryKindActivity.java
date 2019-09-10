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

import java.util.ArrayList;
import java.util.List;

public class DictionaryKindActivity extends BaseActivity<ActivityDictionaryKindBinding> {
    private int mCurPos;
    public static final int WORD = 0;
    public static final int BU_SHOU = 1;
    public static final int PIN_YIN = 2;


    int index = 0;
    private FragmentManager fm;
    private List<String> mTitles = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState, ActivityDictionaryKindBinding binding) {
        final Intent intent = getIntent();
        mCurPos = intent.getIntExtra("position", 0);
        mTitles.add(intent.getStringExtra("title"));
        setTitle(mTitles.get(index), null);
        setLeftText("返回", R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index > 0){
                    beBack();
                }else if(index == 0){
                    finish();
                }
            }
        });
        fm = getSupportFragmentManager();
        switch (mCurPos) {
            case WORD:
                SearchFragment searchFragment = SearchFragment.newInstance();
                FragmentTransaction searchTransaction = fm.beginTransaction();
                searchTransaction.replace(R.id.container,searchFragment);
                searchTransaction.commit();
                break;
            case BU_SHOU:
                ListFragment buShouListFragment = ListFragment.newInstance(mCurPos);
                FragmentTransaction buShou = fm.beginTransaction();
                buShou.add(R.id.container,buShouListFragment);
                buShou.commit();
                break;
            case PIN_YIN:
                ListFragment pinyinListFragment = ListFragment.newInstance(mCurPos);
                FragmentTransaction pinyin = fm.beginTransaction();
                pinyin.add(R.id.container,pinyinListFragment);
                pinyin.commit();
                break;
        }
    }

    public void goNext(String title,String value){
        index++;
        mTitles.add(title);
        switch (mCurPos){
            case BU_SHOU:
            case PIN_YIN:
                if(index == 1){
                    ListDetailFragment listFragment = ListDetailFragment.newInstance(value,mCurPos);
                    FragmentTransaction listTransaction = fm.beginTransaction();
                    listTransaction.replace(R.id.container,listFragment);
                    listTransaction.commit();
                    setTitle(mTitles.get(index),null);
                    break;
                }else if(index == 2){
                    DetailFragment detailFragment = DetailFragment.newInstance(DetailFragment.ID_TYPE,value);
                    FragmentTransaction detailTransaction = fm.beginTransaction();
                    detailTransaction.replace(R.id.container,detailFragment);
                    detailTransaction.commit();
                    setTitle(mTitles.get(index),null);
                    break;
                }
            case WORD:
                if(index == 1){
                    DetailFragment detailFragment = DetailFragment.newInstance(DetailFragment.WORD_TYPE,value);
                    FragmentTransaction detailTransaction = fm.beginTransaction();
                    detailTransaction.replace(R.id.container,detailFragment);
                    detailTransaction.commit();
                    setTitle(mTitles.get(index),null);
                    break;
                }
        }


    }


    public void beBack(){
        mTitles.remove(index);
        index--;
        switch (mCurPos){
            case BU_SHOU:
                if(index == 1){
                    ListDetailFragment listFragment = ListDetailFragment.newInstance(mTitles.get(index),mCurPos);
                    FragmentTransaction listTransaction = fm.beginTransaction();
                    listTransaction.replace(R.id.container,listFragment);
                    listTransaction.commit();
                    setTitle(mTitles.get(index),null);
                }else if(index == 0){
                    ListFragment buShouListFragment = ListFragment.newInstance(mCurPos);
                    FragmentTransaction buShou = fm.beginTransaction();
                    buShou.replace(R.id.container,buShouListFragment);
                    buShou.commit();
                    setTitle(mTitles.get(index),null);
                }
                break;
            case PIN_YIN:
                if(index == 1){
                    ListDetailFragment listFragment = ListDetailFragment.newInstance(mTitles.get(index),mCurPos);
                    FragmentTransaction listTransaction = fm.beginTransaction();
                    listTransaction.replace(R.id.container,listFragment);
                    listTransaction.commit();
                    setTitle(mTitles.get(index),null);
                }else if(index == 0){
                    ListFragment pinyinListFragment = ListFragment.newInstance(mCurPos);
                    FragmentTransaction pinyin = fm.beginTransaction();
                    pinyin.replace(R.id.container,pinyinListFragment);
                    pinyin.commit();
                    setTitle(mTitles.get(index),null);
                }
                break;
            case WORD:
                if(index == 0){
                    SearchFragment searchFragment = SearchFragment.newInstance();
                    FragmentTransaction searchTransaction = fm.beginTransaction();
                    searchTransaction.replace(R.id.container,searchFragment);
                    searchTransaction.commit();
                    setTitle(mTitles.get(index),null);
                }
                break;

        }
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_dictionary_kind;
    }
}
