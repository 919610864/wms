package com.yhdx.wms.inbound.server.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataSourceConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);


    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Value("${mybatis.config-location}")
    private String configLocation;


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource dataSource() {
        return new DruidDataSource();
    }

    @Primary
    @Bean
    public DataSourceProxy dataSourceProxy(DruidDataSource druidDataSource){
        return new DataSourceProxy(druidDataSource);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSourceProxy druidDataSource) {
        logger.info("初始化事务管理器:PlatformTransactionManager");
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(druidDataSource);
        return transactionManager;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSourceProxy dataSourceProxy) {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setDataSource(dataSourceProxy);
        sqlSessionFactory.setTransactionFactory(new SpringManagedTransactionFactory());
        try {
            sqlSessionFactory.setConfigLocation(resolver.getResource(configLocation));
            sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
            return sqlSessionFactory.getObject();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
