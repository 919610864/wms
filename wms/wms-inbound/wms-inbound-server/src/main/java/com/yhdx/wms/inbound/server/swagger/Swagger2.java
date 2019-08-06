package com.yhdx.wms.inbound.server.swagger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * API文档生成工具；访问地址：
 * http://localhost:端口/swagger-ui.html
 * basePackage 必须包括**，否则与feign扫描冲突
 * @author jie.yang
 * 用@Configuration注解该类，等价于XML中配置beans；用@Bean标注方法等价于XML中配置bean。
 */
@Configuration
public class Swagger2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Swagger2.class);

    @Value("${enable.swagger}")
    private boolean enableSwagger;

    @Value("${base.package.swagger}")
    private String basePackage;

    private ApiInfo apiTester() {
        return new ApiInfoBuilder().title("WMS  API文档")
                .description("TMS-INBOUND系统 入库流程 API文档")
                .termsOfServiceUrl("")
                .version("1.0").build();
    }

    @Bean
    public Docket createInboundApi() {
        LOGGER.info("Swagger2信息: enable.swagger=" + enableSwagger + "; base.package.swagger=" + basePackage);
        return new Docket(DocumentationType.SWAGGER_2).enable(enableSwagger).groupName("purchase")
                .apiInfo(apiInfoInbound()).select()
                .apis(RequestHandlerSelectors.basePackage(basePackage + ".purchase"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfoInbound() {
        return new ApiInfoBuilder()
                .title("WMS-INBOUND系统 API文档")
                .description("WMS-INBOUND系统 API文档")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
