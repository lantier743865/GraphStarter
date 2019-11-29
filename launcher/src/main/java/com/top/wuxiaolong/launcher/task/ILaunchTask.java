package com.top.wuxiaolong.launcher.task;

import com.top.wuxiaolong.launcher.IAppLauncher;
import com.top.wuxiaolong.launcher.executor.Schedulers;

import java.util.List;

public interface ILaunchTask extends Runnable{
    List<Class<? extends ILaunchTask>> dependsOn();

    Schedulers runOn();

    void satisfy();

    void addChildTask(ILaunchTask task);

    List<String> finishBeforeBreakPoints();

    void attachContext(IAppLauncher launcher);

    boolean isFinished();

    void updateDependsCount(int count);
}
