package org.gy.framework.demobusiness.config.interceptor;


import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.gy.framework.demobusiness.config.DynamicDataSourceContextHolder;
import org.gy.framework.demobusiness.config.DynamicDataSourceType;
import org.gy.framework.demobusiness.config.annotation.DynamicDataSourceAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;


@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class DynamicPlugin implements Interceptor {

    protected static final Logger                             logger   = LoggerFactory.getLogger(DynamicPlugin.class);
    private static final   String                             REGEX    = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";
    private static final   Map<String, DynamicDataSourceType> cacheMap = new ConcurrentHashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        boolean synchronizationActive = false;
        boolean aopActive = false;
        try {
            //如果包含事务上下文，这里就不作处理，交由DynamicDataSourceTransactionManager处理
            synchronizationActive = TransactionSynchronizationManager.isSynchronizationActive();
            // 如果aop切面激活，则不处理，交由DynamicDataSourceAspect处理
            aopActive = DynamicDataSourceAspect.isAspectActive();
            if (!synchronizationActive && !aopActive) {
                wrapDataSourceType(invocation);
            }
            return invocation.proceed();
        } finally {
            if (!synchronizationActive && !aopActive) {
                DynamicDataSourceContextHolder.clearDataSource();
            }
        }
    }

    private void wrapDataSourceType(Invocation invocation) {
        Object[] objects = invocation.getArgs();
        MappedStatement ms = (MappedStatement) objects[0];
        DynamicDataSourceType sourceType = null;
        if ((sourceType = cacheMap.get(ms.getId())) == null) {
            //读方法
            if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                //!selectKey 为自增id查询主键(SELECT LAST_INSERT_ID() )方法，使用主库
                if (ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                    sourceType = DynamicDataSourceType.MASTER;
                } else {
                    BoundSql boundSql = ms.getSqlSource().getBoundSql(objects[1]);
                    String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
                    if (sql.matches(REGEX)) {
                        sourceType = DynamicDataSourceType.MASTER;
                    } else {
                        sourceType = DynamicDataSourceType.SLAVE;
                    }
                }
            } else {
                sourceType = DynamicDataSourceType.MASTER;
            }
            cacheMap.put(ms.getId(), sourceType);
        }
        logger.info("DynamicPlugin设置方法[{}] use [{}] Strategy, SqlCommandType [{}]..", ms.getId(), sourceType.name(), ms.getSqlCommandType().name());
        DynamicDataSourceContextHolder.putDataSource(sourceType);
    }

    @Override
    public Object plugin(Object target) {
//        if (target instanceof Executor) {
//            return Plugin.wrap(target, this);
//        } else {
//            return target;
//        }
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
