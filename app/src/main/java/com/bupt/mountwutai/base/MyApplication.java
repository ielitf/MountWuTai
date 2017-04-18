package com.bupt.mountwutai.base;

import android.support.multidex.MultiDexApplication;
import android.widget.Toast;

/**
 * Created by joycezhao on 17/4/18.
 */

public class MyApplication extends MultiDexApplication {

    private static MyApplication application;

    public static MyApplication getMyApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
    public void showToast(String msg) {
        Toast.makeText(application, msg + "", Toast.LENGTH_SHORT).show();
    }
}
