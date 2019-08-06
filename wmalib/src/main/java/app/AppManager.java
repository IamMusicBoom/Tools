package app;
import java.util.HashMap;
import java.util.Stack;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 王明骜 on 19-8-5 下午4:08.
 */
public class AppManager {
    public static Stack<AppCompatActivity> activityStack;
    private static AppManager instance;
    private static HashMap<String, Integer> standMemory = new HashMap<String, Integer>();

    public void addMemory(Object o) {
        standMemory.put(o.getClass().getName() + o.hashCode(), 1);
    }

    public void finalize(Object o) {
        standMemory.remove(o.getClass().getName() + o.hashCode());
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(AppCompatActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<AppCompatActivity>();
        }
        activityStack.add(activity);
    }

    public void pushActivity(AppCompatActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<AppCompatActivity>();
        }
        if(!activityStack.contains(activity)){
            activityStack.add(0,activity);
        }
    }

    public void popActivity(AppCompatActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<AppCompatActivity>();
        }
        activityStack.remove(activity);
    }
    /**
     * 获取当前Activity (栈顶的Activity,最后一个入栈的Activity)
     */
    public AppCompatActivity currentActivity() {
        if (activityStack == null) {
            return null;
        }
        AppCompatActivity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 获得当前 Activity 的名字
     * @return
     */
    public String currentActivityName() {
       AppCompatActivity activity = currentActivity();
        return activity.getClass().getSimpleName();
    }

    /**
     * 判断当前 Activity的名字是否与所传名字相同
     * @param name
     * @return
     */
    public boolean equalsActivity(String name) {
        return currentActivityName().equals(name);

    }

    /**
     * 判断堆栈中是否有某个activity
     * @param cls
     * @return
     */
    public boolean containsActivity(Class<?> cls) {
        for (AppCompatActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        AppCompatActivity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(AppCompatActivity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (AppCompatActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        if (activityStack == null) {
            return;
        }
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            /*
             * Intent intent = new Intent(context, MainActivity.class);
             * PendingIntent restartIntent = PendingIntent.getActivity( context,
             * 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK); //退出程序 AlarmManager
             * mgr =
             * (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
             * mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
             * restartIntent); // 1秒钟后重启应用
             */
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);

        } catch (Exception e) {

        }
    }
}
