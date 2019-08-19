package com.wma.wmalib.loading;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;

import com.wma.wmalib.R;
import com.wma.wmalib.utils.ThreadUtils;

/**
 * Created by 王明骜 on 19-8-9 下午1:36.
 */
public class LoadingHandler {

    private LoadingDialog mLoadingDialog;
    private Context mContext;
    private boolean mHideLoading;

    public LoadingHandler(Context context) {

        mContext = context;
    }


    public void showLoading() {
        showLoading(mContext.getString(R.string.loading));
    }


    public void showLoading(final String message) {

        if (mLoadingDialog != null)
            return;
        mHideLoading = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!mHideLoading) {
                    mLoadingDialog =
                            new LoadingDialog(mContext, message);
                    mLoadingDialog.setMessage(message);
                    mLoadingDialog.show();
                    mLoadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            mHideLoading = true;
                            mLoadingDialog = null;

                        }


                    });
                }

            }
        }, 100);

    }

    public void updateLoading(String message) {

        if (mLoadingDialog != null)
            mLoadingDialog.setMessage(message);
    }

    public void hideLoading() {
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mHideLoading = true;
                if (mLoadingDialog != null) {
                    if(mLoadingDialog.isShowing())
                        mLoadingDialog.cancel();
                    mLoadingDialog = null;
                }

            }
        });


    }
}
