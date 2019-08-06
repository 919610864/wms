package com.yhdx.wms.inbound.server.startup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.yhdx.wms.framework",
        "com.yhdx.wms.inbound.server"})
@EnableDiscoveryClient
@EnableFeignClients(
        {
                "com.yhdx.wms.inside.feign.api"
        }
)
@MapperScan(basePackages = {"com.yhdx.wms.inbound.server.dao.mapper"})
@EnableTransactionManagement
@EnableSwagger2
public class PurChaSingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurChaSingApplication.class, args);
    }

}
