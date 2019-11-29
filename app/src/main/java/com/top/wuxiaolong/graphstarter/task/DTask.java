package com.top.wuxiaolong.graphstarter.task;

import android.os.SystemClock;

import com.top.wuxiaolong.launcher.executor.Schedulers;


public class DTask extends BaseTask {
    @Override
    public void call() {
        before();
        SystemClock.sleep(460);
        after();
    }
    @Override
    public Schedulers runOn() {
        return Schedulers.COMPUTATION;
    }
}
