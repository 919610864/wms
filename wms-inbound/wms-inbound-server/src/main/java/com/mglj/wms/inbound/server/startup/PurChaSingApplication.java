package com.mglj.wms.inbound.server.startup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.mglj.wms.framework","com.mglj.wms.inbound.server","com.mglj.base.audit"})
@EnableDiscoveryClient
@EnableFeignClients({"com.mglj.wms.inside.feign.api"})
@MapperScan(basePackages = {"com.mglj.wms.inbound.server.dao.mapper","com.mglj.base.dao.api"})
@EnableTransactionManagement
@EnableSwagger2
@ComponentScan("com.mglj")
public class PurChaSingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurChaSingApplication.class, args);
    }

}
