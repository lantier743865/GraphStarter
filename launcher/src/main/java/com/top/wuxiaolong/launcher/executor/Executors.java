package com.top.wuxiaolong.launcher.executor;

public class Executors {
    public static TaskExecutor get(Schedulers schedulers) {
        TaskExecutor executor;
        switch (schedulers) {
            case IO:
                executor = IOExecutor.getInstance();
                break;
            case MAIN:
                executor = MainExecutor.getInstance();
                break;
            case COMPUTATION:
            default:
                executor = ComputeExecutor.getInstance();
        }
        return executor;
    }
}
