package com.wma.tools.news;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.wma.tools.R;
import com.wma.tools.databinding.FragmentPictureDetialBinding;
import com.wma.wmalib.base.fragment.BaseFragment;
import com.wma.wmalib.zoom.PhotoView;
import com.wma.wmalib.zoom.PhotoViewAttacher;

/**
 * Created by 王明骜 on 19-8-13 上午8:38.
 */
public class PictureDetailFragment extends BaseFragment<FragmentPictureDetialBinding> {
    FragmentPictureDetialBinding mBinding;
    private String mImageUrl;
    private PhotoView mImageView;
    private ProgressBar progressBar;
    private PhotoViewAttacher mAttacher;

    @Override
    protected void createContentView(ViewGroup container, FragmentPictureDetialBinding binding) {
        mBinding = binding;
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_picture_detial;
    }

    @Override
    public void create(Bundle savedInstanceState) {

        mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
        mImageUrl = mImageUrl.replace("http://","https://");
        progressBar = mBinding.loading;
        mImageView = mBinding.image;
        mAttacher = new PhotoViewAttacher(mImageView);

        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View arg0, float arg1, float arg2) {
                getActivity().finish();
            }
        });

        mAttacher.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                if (null == bm) {
//                    ToastUtil.showToast("图片信息不存在...");
//                    return true;
//                }
//                new AlertDialog.Builder(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
//                        .setMessage("保存到本地")
//                        .setNegativeButton("取消", null)
//                        .setPositiveButton("保存", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bm, "悦享租" + System.currentTimeMillis(), "悦享租" + System.currentTimeMillis());
//                            }
//                        }).show();

                return true;
            }
        });
        progressBar.setVisibility(View.VISIBLE);
        Glide.with(getActivity()).load(mImageUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                progressBar.setVisibility(View.GONE);
                mImageView.setImageBitmap(resource);
                mAttacher.update();

            }
        });
    }

    public static PictureDetailFragment newInstance(String imageUrl) {
        final PictureDetailFragment f = new PictureDetailFragment();

        final Bundle args = new Bundle();
        args.putString("url", imageUrl);
        f.setArguments(args);

        return f;
    }
}
