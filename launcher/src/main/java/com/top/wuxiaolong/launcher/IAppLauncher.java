package com.top.wuxiaolong.launcher;

public interface IAppLauncher {
    void breakPoint(String type);

    void breakPoint(String type, int timeout);

    void satisfyBreakPoint(String type);

    void onceTaskFinish();

    void start();

    void shutdown();
}
