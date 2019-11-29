package com.top.wuxiaolong.launcher.executor;

import android.os.Handler;
import android.os.Looper;

public class MainExecutor implements TaskExecutor {
    private static final Handler mMainHandler = new Handler(Looper.getMainLooper());

    public static MainExecutor getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final MainExecutor INSTANCE = new MainExecutor();
    }

    private MainExecutor() {
    }

    @Override
    public void execute(Runnable runnable) {
        mMainHandler.post(runnable);
    }

    @Override
    public void shutdown() {

    }
}
