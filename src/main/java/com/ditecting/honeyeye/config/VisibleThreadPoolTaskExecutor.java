/**
 * Copy Right Information : ditecting cyber security lab
 *
 * @author : CSheng
 * @date : Created in 2020/3/17 14:48
 * @description : visible thread pool
 * @version : 1.0$
 */
package com.ditecting.honeyeye.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class VisibleThreadPoolTaskExecutor extends ThreadPoolTaskExecutor{
    private static final long serialVersionUID = -7700119989964932102L;
    private static final Logger logger = LoggerFactory.getLogger(VisibleThreadPoolTaskExecutor.class);

    private void showThreadPoolInfo (String prefix){
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();

        if(threadPoolExecutor == null){
            return;
        }

        logger.info("{}, {}, taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]",
                this.getThreadNamePrefix(),
                prefix,
                threadPoolExecutor.getTaskCount(),
                threadPoolExecutor.getCompletedTaskCount(),
                threadPoolExecutor.getActiveCount(),
                threadPoolExecutor.getQueue().size());
    }

    @Override
    public void execute (Runnable task) {
        showThreadPoolInfo("1. do execute");
        super.execute(task);
    }

    @Override
    public void execute (Runnable task, long startTimeout) {
        showThreadPoolInfo("2. do execute");
        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit (Runnable task) {
        showThreadPoolInfo("1. do submit");
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit (Callable<T> task) {
        showThreadPoolInfo("2. do submit");
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable (Runnable task) {
        showThreadPoolInfo("1. do submitListenable");
        return  super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable (Callable<T> task) {
        showThreadPoolInfo("2. do submitListenable");
        return  super.submitListenable(task);
    }
}