//package com.whj.usercenter.config;
//
//import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.AsyncConfigurer;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.Executor;
//
//@Configuration
//public class TaskExecutorConfig implements AsyncConfigurer {
//
//    @Value("${threadPool.keepAliveSeconds}")
//    private int keepAliveSeconds;
//
//    @Value("${threadPool.corePoolSize}")
//    private int corePoolSize;
//
//    @Value("${threadPool.maxPoolSize}")
//    private int maxPoolSize;
//
//    @Value("${threadPool.queueCapacity}")
//    private int queueCapacity;
//
//    @Override
//    public Executor getAsyncExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(corePoolSize);
//        executor.setMaxPoolSize(maxPoolSize);
//        executor.setQueueCapacity(queueCapacity);
//        executor.setKeepAliveSeconds(keepAliveSeconds);
//        executor.setThreadNamePrefix("User-AsyncPool-");
//        executor.initialize();
//        return executor;
//    }
//
//    @Override
//    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//        return null;
//    }
//
//}
