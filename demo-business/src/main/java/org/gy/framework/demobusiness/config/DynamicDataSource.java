package org.gy.framework.demobusiness.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        DynamicDataSourceType type = DynamicDataSourceContextHolder.getDataSource();
        if (type == null) {
            return DynamicDataSourceType.MASTER;
        }
        return type;
    }
}
