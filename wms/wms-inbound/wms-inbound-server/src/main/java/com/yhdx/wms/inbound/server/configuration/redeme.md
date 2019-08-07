###Spring本地事务执行异常(全局事务)

####本地事务
> 当前使用 JdbcTransactionFactory 进行初始化sqlsession
```java
@Bean
public SqlSessionFactory sqlSessionFactory(DataSourceProxy dataSourceProxy) {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.settaSource(dataSourceProxy);
    bean.setTransactionFactory(new JdbcTransactionFactory());
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    try {
        bean.setConfigLocation(resolver.getResource(configLocation));
        bean.setMapperLocations(resolver.getResources(mapperLocations));

        return bean.getObject();
    } catch (Exception e) {
        throw new IllegalStateException(e);
    }
}

@Bean
public DataSourceTransactionManager transactionManager(DruidDataSource druidDataSource) {
    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
    transactionManager.setDataSource(druidDataSource);

    return transactionManager;
}
```
**bean.setTransactionFactory(new JdbcTransactionFactory());**
> 查看SqlSessionFactoryBean 
```java
if (this.transactionFactory == null) {
      this.transactionFactory = new SpringManagedTransactionFactory();
    }
```
> sqlsession初始化的时候，初始化当前的tx，需要跟spring结合也就是SpringManagedTransactionFactory

###修改代码如下
```java
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
```

####全局事务
> 可自行测试
```java
@Aspect
@Configuration
public class TransactionAdviceConfig {

    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.***.service..*.*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {

        DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        DefaultTransactionAttribute txAttr_REQUIRED_READONLY = new DefaultTransactionAttribute();
        txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttr_REQUIRED_READONLY.setReadOnly(true);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();

        source.addTransactionalMethod("save*", txAttr_REQUIRED);
        source.addTransactionalMethod("delete*", txAttr_REQUIRED);
        source.addTransactionalMethod("update*", txAttr_REQUIRED);
        source.addTransactionalMethod("exec*", txAttr_REQUIRED);
        source.addTransactionalMethod("set*", txAttr_REQUIRED);
        source.addTransactionalMethod("get*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("query*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("find*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("list*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("count*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("is*", txAttr_REQUIRED_READONLY);

        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
```

####本地事务 结论
**sqlSessionFactory.setTransactionFactory(new SpringManagedTransactionFactory());**
* 当前只有一个事务管理器时可不指定相应的fatocy,mybatis默认会使用SpringManagedTransactionFactory进行初始化tx


####参考资料
* [Spring事务 JDBC Transaction JTATransaction CMTTransaction](https://blog.csdn.net/gxftry1st/article/details/73105489)
* [mybatis-spring事务处理机制分析](https://my.oschina.net/fifadxj/blog/785621)
* [spring事务源码分析结合matis源码](https://www.cnblogs.com/lcxdever/p/4579240.html)
* [Tansactional详解](https://blog.csdn.net/jiangyu1013/article/details/84397366)
* [Spring中Transactional事务回滚](https://blog.csdn.net/u013142781/article/details/50421904)
