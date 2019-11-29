package com.top.wuxiaolong.launcher.task;

import com.top.wuxiaolong.launcher.IAppLauncher;
import com.top.wuxiaolong.launcher.executor.Schedulers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public abstract class LaunchTask implements ILaunchTask {

    private static final int STATE_CREATE = 0;
    private static final int STATE_WAITING = 1;
    private static final int STATE_RUNNING = 2;
    private static final int STATE_FINISHED = 3;
    private final Set<ILaunchTask> mChildTask;
    private CountDownLatch mDependsOnLatch;
    private IAppLauncher mContextLauncher;
    private int mState = STATE_CREATE;

    protected LaunchTask() {
        this.mChildTask = new HashSet<>();
    }

    @Override
    public void run() {
        markState(STATE_WAITING);
        waitToSatisfy();
        markState(STATE_RUNNING);
        call();
        markState(STATE_FINISHED);
        notifyChildren();
        notifyLauncher();
    }
    private void markState(int state) {
        mState = state;
    }
    protected abstract void call();

    private void waitToSatisfy() {
        if (mDependsOnLatch != null) {
            try {
                mDependsOnLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void notifyChildren() {
        if (!mChildTask.isEmpty()) {
            for (ILaunchTask task : mChildTask) {
                task.satisfy();
            }
        }
    }

    private void notifyLauncher() {
        if (mContextLauncher != null) {
            List<String> breakPoints = finishBeforeBreakPoints();
            if (breakPoints != null && !breakPoints.isEmpty()) {
                for (String breakPoint : breakPoints) {
                    mContextLauncher.satisfyBreakPoint(breakPoint);
                }
            }
            mContextLauncher.onceTaskFinish();
        }
    }

    @Override
    public List<Class<? extends ILaunchTask>> dependsOn() {
        return null;
    }

    @Override
    public Schedulers runOn() {
        return Schedulers.COMPUTATION;
    }

    @Override
    public void satisfy() {
        if (mDependsOnLatch != null) {
            mDependsOnLatch.countDown();
        }
    }

    protected String getDependsOnString() {
        if (dependsOn() == null || dependsOn().isEmpty()) {
            return "";
        }
        StringBuilder output = new StringBuilder();
        for (Class clazz : dependsOn()) {
            output.append(clazz.getSimpleName());
            output.append(" | ");
        }
        return output.toString();
    }

    protected String getThreadName() {
        return Thread.currentThread().getName();
    }

    @Override
    public void addChildTask(ILaunchTask task) {
        mChildTask.add(task);
    }

    @Override
    public List<String> finishBeforeBreakPoints() {
        return null;
    }

    @Override
    public void attachContext(IAppLauncher launcher) {
        mContextLauncher = launcher;
    }

    @Override
    public boolean isFinished() {
        return mState == STATE_FINISHED;
    }

    @Override
    public void updateDependsCount(int count) {
        mDependsOnLatch = new CountDownLatch(count);
    }
}
