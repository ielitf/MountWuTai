package com.bupt.mountwutai.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * 跳转的工具类
 * Created by Wyf on 2017/5/14.
 */

public class ActivityUtils {
    /**
     * 跳转页面
     *
     * @param tarActivity 目标页面
     */
    public static void intent2Activity(Context context, Class<? extends Activity> tarActivity, Bundle bundle) {
        Intent intent = new Intent(context, tarActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转页面
     *
     * @param tarActivity 目标页面
     */
    public static void intent2Activity(Context context, Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(context, tarActivity);
        context.startActivity(intent);
    }
}
