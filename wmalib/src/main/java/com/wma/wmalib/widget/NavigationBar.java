package com.wma.wmalib.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wma.wmalib.R;
import com.wma.wmalib.databinding.NavigationBarBinding;

/**
 * Created by 王明骜 on 19-8-5 下午4:49.
 */
public class NavigationBar {
    private Context mContext;


    NavigationBarBinding binding;

    public NavigationBar(Context mContext) {
        this.mContext = mContext;
        initTitleView();
    }

    private void initTitleView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.navigation_bar, null, false);
    }

    public NavigationBarBinding getNavBinding() {
        return binding;
    }

    public void setTitle(String title, View.OnClickListener listener) {
        if (TextUtils.isEmpty(title)) {
            binding.midTv.setText("");
        } else {
            binding.midTv.setText(title);
        }
        if (listener != null) {
            binding.midTv.setClickable(true);
            binding.midTv.setOnClickListener(listener);
        } else {
            binding.midTv.setClickable(false);
        }
    }

    private void setRightText(String msg) {
        if (TextUtils.isEmpty(msg)) {
            binding.rightTv.setText("");
        } else {
            binding.rightTv.setText(msg);
        }
    }

    private void setRightImg(int resId) {
        if (resId <= 0) {
            return;
        } else {
            Drawable drawable = ContextCompat.getDrawable(mContext, resId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            binding.rightTv.setCompoundDrawables(null, null, drawable, null);
        }
    }

    public void registerRightText(String msg, int imgId, View.OnClickListener listener) {
        setRightText(msg);
        setRightImg(imgId);
        if (listener != null) {
            binding.rightTv.setClickable(true);
            binding.rightTv.setOnClickListener(listener);
        } else {
            binding.rightTv.setClickable(false);
        }
    }


    private void setLeftText(String msg) {
        if (TextUtils.isEmpty(msg)) {
            binding.leftTv.setText(msg);
        } else {
            binding.leftTv.setText(msg);
        }

    }

    private void setLeftImg(int resId) {
        if (resId <= 0) {
            return;
        } else {
            Drawable drawable = ContextCompat.getDrawable(mContext, resId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            binding.leftTv.setCompoundDrawables(drawable, null, null, null);
        }
    }

    public void registerLeftText(String msg, int imgId, View.OnClickListener listener) {
        if (!TextUtils.isEmpty(msg)) {
            setLeftText(msg);
        } else {
            setLeftText("");
        }
        if (imgId > 0) {
            setLeftImg(imgId);
        }
        if (listener != null) {
            binding.leftTv.setClickable(true);
            binding.leftTv.setOnClickListener(listener);
        } else {
            binding.leftTv.setClickable(false);
        }
    }


    public void setNavBarVisible() {
        if (binding.rootCl.getVisibility() != View.VISIBLE) {
            binding.rootCl.setVisibility(View.VISIBLE);

        }
    }

    public void setNavBarGone() {
        if (binding.rootCl.getVisibility() != View.GONE) {
            binding.rootCl.setVisibility(View.GONE);
        }
    }

    public void setNavBarInvisible() {
        if (binding.rootCl.getVisibility() != View.INVISIBLE) {
            binding.rootCl.setVisibility(View.INVISIBLE);
        }
    }
}
