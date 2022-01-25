package com.chinadci.neo4j.config;

import com.chinadci.neo4j.utils.DatabaseRouting;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据库配置
 */
@Configuration
public class DatabaseConfiguration {

    /**
     * 主库配置
     * @return 数据源配置
     */
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 从库配置
     * @return 数据源配置
     */
    @Bean
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSourceProperties secondaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Resource
    private DataSourceProperties masterDataSourceProperties;

    @Resource
    private DataSourceProperties secondaryDataSourceProperties;

    @Bean
    public DataSource dbRouting() throws IOException {
        DataSource master = masterDataSourceProperties.initializeDataSourceBuilder().build();
        DataSource secondary = secondaryDataSourceProperties.initializeDataSourceBuilder().build();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("master", master);
        dataSourceMap.put("secondary", secondary);

        DatabaseRouting routing = new DatabaseRouting();
        routing.setDefaultTargetDataSource(master);
        routing.setTargetDataSources(dataSourceMap);
        return routing;
    }

}