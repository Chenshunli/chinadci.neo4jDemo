package com.chinadci.neo4j.utils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态多数据源路由
 */
public class DatabaseRouting extends AbstractRoutingDataSource {

    /**
     * 线程变量（缓存当前数据源名称）
     */
    private static final ThreadLocal<String> LOCAL = new ThreadLocal<>();

    /**
     * 设置当前线程使用数据源名
     * @param dataSourceName 数据源名
     */
    public static void setCurrentThreadDataSource(String dataSourceName) {
        LOCAL.set(dataSourceName);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return LOCAL.get();
    }

}