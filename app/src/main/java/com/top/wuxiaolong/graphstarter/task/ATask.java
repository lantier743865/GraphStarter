package com.top.wuxiaolong.graphstarter.task;

import android.os.SystemClock;

import com.top.wuxiaolong.launcher.executor.Schedulers;


public class ATask extends BaseTask {
    private static final String TAG = "ATask";
    @Override
    public void call() {
        before();
        SystemClock.sleep(200);
        after();
    }
    @Override
    public Schedulers runOn() {
        return Schedulers.MAIN;
    }

}
