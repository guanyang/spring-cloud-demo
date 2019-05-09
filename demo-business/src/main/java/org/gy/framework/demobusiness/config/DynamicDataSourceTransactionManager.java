package org.gy.framework.demobusiness.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

public class DynamicDataSourceTransactionManager extends DataSourceTransactionManager {

    private static final long serialVersionUID = 4015408611384383554L;

    protected static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceTransactionManager.class);

    //只读事务到读库，读写事务到写库
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        //设置数据源
        boolean readOnly = definition.isReadOnly();
        DynamicDataSourceType type = DynamicDataSourceType.MASTER;
        if (readOnly) {
            type = DynamicDataSourceType.SLAVE;
        }

        logger.info("DynamicDataSourceTransactionManager设置方法[{}] use [{}] Strategy.", definition.getName(), type.name());
        DynamicDataSourceContextHolder.putDataSource(type);

        super.doBegin(transaction, definition);
    }

    //清理本地线程的数据源
    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        super.doCleanupAfterCompletion(transaction);
        DynamicDataSourceContextHolder.clearDataSource();
    }
}
