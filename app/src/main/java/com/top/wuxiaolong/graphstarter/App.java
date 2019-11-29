package com.top.wuxiaolong.graphstarter;

import android.app.Application;
import android.util.Log;

import com.top.wuxiaolong.graphstarter.task.ATask;
import com.top.wuxiaolong.graphstarter.task.BTask;
import com.top.wuxiaolong.graphstarter.task.CTask;
import com.top.wuxiaolong.graphstarter.task.DTask;
import com.top.wuxiaolong.graphstarter.task.ETask;
import com.top.wuxiaolong.graphstarter.task.FTask;
import com.top.wuxiaolong.graphstarter.task.GTask;
import com.top.wuxiaolong.graphstarter.task.MTask;
import com.top.wuxiaolong.launcher.AppLauncher;
import com.top.wuxiaolong.launcher.listener.IdleHandler;

public class App extends Application {
    private static final String TAG = "App";
    public static long start;

    @Override
    public void onCreate() {
        super.onCreate();
        start = System.currentTimeMillis();
        AppLauncher launcher = new AppLauncher.Builder()
                .addTask(new MTask())
                .addTask(new ETask())
                .addTask(new CTask())
                .addTask(new BTask())
                .addTask(new FTask())
                .addTask(new GTask())
                .addTask(new ATask())
                .addTask(new DTask())
                .idleHandler(new IdleHandler() {
                    @Override
                    public void onIdle() {
                        Log.e(TAG, "---->>onIdle");
                    }
                }).start();
        long end = System.currentTimeMillis();
        Log.e(TAG, "---->>cost: " + (end - start) );
    }
}
