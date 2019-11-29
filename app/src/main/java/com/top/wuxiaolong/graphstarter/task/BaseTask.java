package com.top.wuxiaolong.graphstarter.task;


import android.util.Log;

import com.top.wuxiaolong.graphstarter.App;
import com.top.wuxiaolong.launcher.executor.Schedulers;
import com.top.wuxiaolong.launcher.task.LaunchTask;


public abstract class BaseTask extends LaunchTask {
    private static final String TAG = "BaseTask";
    public void before() {
        Log.e(TAG, "---->>begain: "+ this.getClass().getSimpleName() + ",start:"+(System.currentTimeMillis() - App.start) +",thread: "+ Thread.currentThread().getName() );
        Log.e(TAG, "---->>before: "+getDependsOnString() );
    }

    public void after() {
        Log.e(TAG, "---->>finish: "+ this.getClass().getSimpleName() +",end:"+(System.currentTimeMillis() - App.start) +",thread: "+ Thread.currentThread().getName() );
    }



    @Override
    public Schedulers runOn() {
        return Schedulers.MAIN;
    }
}
