/**
 * Copy Right Information : ditecting cyber security lab
 *
 * @author : CSheng
 * @date : Created in 2020/3/17 14:48
 * @description : converter thread pool
 * @version : 1.0$
 */
package com.ditecting.honeyeye.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


@Configuration
@EnableAsync
public class ExecutorConfig {
    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);

    @Bean
    public Executor asyncConvertExecutor(){
        logger.info("start asyncConvertExecutor");
        ThreadPoolTaskExecutor executor = new VisibleThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("async-convert-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//invoker threads will process their excessive tasks by themselves
        executor.initialize();
        return executor;
    }

    @Bean
    public Executor asyncTsharkExecutor(){
        logger.info("start asyncTsharkExecutor");
        ThreadPoolTaskExecutor executor = new VisibleThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("async-tshark-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//invoker threads will process their excessive tasks by themselves
        executor.initialize();
        return executor;
    }
}