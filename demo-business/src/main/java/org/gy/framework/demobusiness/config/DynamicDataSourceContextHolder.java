package org.gy.framework.demobusiness.config;

public final class DynamicDataSourceContextHolder {

    private static final ThreadLocal<DynamicDataSourceType> holder = new ThreadLocal<>();

    private DynamicDataSourceContextHolder() {
    }

    public static void putDataSource(DynamicDataSourceType dataSource) {
        holder.set(dataSource);
    }

    public static DynamicDataSourceType getDataSource() {
        return holder.get();
    }

    public static void clearDataSource() {
        holder.remove();
    }

}
