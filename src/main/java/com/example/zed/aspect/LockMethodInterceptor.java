package com.example.zed.aspect;

import com.example.zed.annotation.CacheLock;
import com.example.zed.lock.RedisUtil;
import com.example.zed.lock.SurvivalClamProcessor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @Author: zed
 * @Date: 2019/5/22 19:47
 * @Description: 分布式锁aop
 */
@Aspect
@Configuration
public class LockMethodInterceptor {


    @Around("execution(public * *(..)) && @annotation(com.example.zed.annotation.CacheLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new RuntimeException("lock key don't null...");
        }
        final String lockKey = lock.prefix();
        String value = UUID.randomUUID().toString();
        try {
            // 假设上锁成功，但是设置过期时间失效，以后拿到的都是 false
            final boolean success = RedisUtil.getLock(lockKey, value, lock.expire());
            if (!success) {
                throw new RuntimeException("重复提交");
            }
            // 延长锁时间的守护线程
            SurvivalClamProcessor survivalClamProcessor = new SurvivalClamProcessor(lockKey, value, lock.expire());
            Thread survivalThread = new Thread(survivalClamProcessor);
            survivalThread.setDaemon(true);
            survivalThread.start();
            Object returnObject;
            try {
                returnObject =  pjp.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("系统异常");
            }
            survivalClamProcessor.stop();
            survivalThread.interrupt();
            return returnObject;
        } finally {
            // TODO 如果演示的话需要注释该代码;实际应该放开
            RedisUtil.releaseLock(lockKey, value);
        }
    }
}
