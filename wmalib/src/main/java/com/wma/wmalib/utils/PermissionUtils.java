package com.wma.wmalib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王明骜 on 19-8-19 下午3:08.
 */
public class PermissionUtils {

    public static final int GOTO_SETTING_CODE = 152;

    /**
     * 判断是否有权限
     * @param context
     * @param permissions
     * @return
     */

    public static boolean hasPermissions(Context context, @Size(min = 1) String ...permissions){

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }
        if(context == null){
            throw new IllegalArgumentException("Can't check permissions for null context");

        }
        for (String permission : permissions) {

            if(ContextCompat.checkSelfPermission(context,permission)!= PackageManager.PERMISSION_GRANTED){
                return false;
            }

        }
        return true;

    }


    /**
     * 申请权限
     * @param context
     * @param requestCode
     * @param permissions
     */
    public static void requestPermissions(Activity context, int requestCode, String[] permissions){


        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        String[] permissionsArray = permissionList.toArray(new String[permissionList.size()]);//将List转为数组
        if (permissionList.isEmpty()) {
            //不可能为空
        } else {
            ActivityCompat.requestPermissions(context, permissionsArray, requestCode);
            //返回结果onRequestPermissionsResult
        }
    }




    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, @NonNull PermissionCallbacks callBack) {
        //授予的权限。
        List<String> granted = new ArrayList<>();

        //拒绝的权限
        List<String> denied = new ArrayList<>();


        for (int i = 0; i < permissions.length; i++) {
            String perm = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                granted.add(perm);
            } else {
                denied.add(perm);
            }
        }

        if (null != callBack) {
            if (denied.isEmpty()) {
                callBack.onPermissionsAllGranted(requestCode, granted, denied.isEmpty());
            }

            if (!denied.isEmpty()) {
                callBack.onPermissionsDenied(requestCode, denied);
            }
        }
    }


    /**
     * 用户是否拒绝权限，并检查“不要提醒”。
     *
     * @param activity
     * @param perms
     * @return
     */
    public static boolean somePermissionPermanentlyDenied(Activity activity, @NonNull List<String> perms) {
        for (String deniedPermission : perms) {
            if (permissionPermanentlyDenied(activity, deniedPermission)) {
                return true;
            }
        }

        return false;
    }

    public static boolean permissionPermanentlyDenied(Activity activity, @NonNull String perms) {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, perms)) {
            return true;
        }
        return false;
    }


    public static void showDialogGoToAppSettting(final Activity activity) {
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setMessage("去设置界面开启权限")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 跳转到应用设置界面
                        goToAppSetting(activity);
                    }
                }).setCancelable(false).show();
    }


    /**
     * 跳转到应用设置界面
     */
    public static void goToAppSetting(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, GOTO_SETTING_CODE);
    }

    public static void showPermissionReason(final int requestCode, final Activity activity, final String[] permission, String s) {
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setMessage(s)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermissions(activity, requestCode, permission);
                    }
                })
                .setCancelable(false).show();
    }


    public interface PermissionCallbacks {

        /**
         * @param isAllGranted 是否全部同意
         */
        void onPermissionsAllGranted(int requestCode, List<String> perms, boolean isAllGranted);

        /**
         */
        void onPermissionsDenied(int requestCode, List<String> perms);

    }
}
