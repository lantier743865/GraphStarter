package com.top.wuxiaolong.graphstarter.task;

import android.os.SystemClock;

import com.top.wuxiaolong.launcher.executor.Schedulers;
import com.top.wuxiaolong.launcher.task.ILaunchTask;

import java.util.ArrayList;
import java.util.List;


/**
 * B 任务 依赖于 A
 */
public class BTask extends BaseTask {
    @Override
    public void call() {
        before();
        SystemClock.sleep(340);
        after();
    }

    @Override
    public List<Class<? extends ILaunchTask>> dependsOn() {
        List<Class<? extends ILaunchTask>> list = new ArrayList<>();
        list.add(ATask.class);
        return list;
    }
    @Override
    public Schedulers runOn() {
        return Schedulers.COMPUTATION;
    }

}
