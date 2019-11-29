package com.top.wuxiaolong.launcher.executor;

public interface TaskExecutor {
    void execute(Runnable runnable);

    void shutdown();
}
