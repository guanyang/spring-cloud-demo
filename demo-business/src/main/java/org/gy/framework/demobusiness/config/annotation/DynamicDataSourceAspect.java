package org.gy.framework.demobusiness.config.annotation;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.gy.framework.demobusiness.config.DynamicDataSourceContextHolder;
import org.gy.framework.demobusiness.config.DynamicDataSourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.reflect.Method;

@Aspect
@Component
public class DynamicDataSourceAspect {

    protected static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    private static final ThreadLocal<Boolean> aspectActive = new NamedThreadLocal<>("AspectActive");

    @Around("@annotation(org.gy.framework.demobusiness.config.annotation.DataSource)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        boolean synchronizationActive = false;
        try {
            //如果包含事务上下文，这里就不作处理，交由DynamicDataSourceTransactionManager处理
            // 事务优先级高
            synchronizationActive = TransactionSynchronizationManager.isSynchronizationActive();
            if (!synchronizationActive) {
                //获取数据源类型
                MethodSignature signature = (MethodSignature) point.getSignature();// 方法签名
                Method method = signature.getMethod();// 获取方法
                String methodName = signature.getName();// 拦截的方法名称

                DataSource annotation = method.getAnnotation(DataSource.class);
                DynamicDataSourceType type = annotation.value();

                logger.info("DynamicDataSourceAspect设置方法[{}] use [{}] Strategy.", methodName, type.name());

                setAspectActive(true);
                DynamicDataSourceContextHolder.putDataSource(type);

            }

            return point.proceed();
        } finally {
            if (!synchronizationActive) {
                clearAspectActive();
                DynamicDataSourceContextHolder.clearDataSource();
            }
        }
    }

    public static void setAspectActive(boolean active) {
        aspectActive.set(active ? Boolean.TRUE : null);
    }

    public static boolean isAspectActive() {
        return aspectActive.get() != null;
    }

    public static void clearAspectActive() {
        aspectActive.remove();
    }

}
