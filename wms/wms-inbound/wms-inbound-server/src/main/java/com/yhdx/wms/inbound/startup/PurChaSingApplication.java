package com.yhdx.wms.inbound.startup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.yhdx.wms.framework",
        "com.yhdx.wms.inbound"})
@EnableDiscoveryClient
@EnableFeignClients(
        {
                "com.yhdx.wms.inside.feign.api"
        }
)
@MapperScan(basePackages = {"com.yhdx.wms.inbound.dao.mapper"})
@EnableTransactionManagement
public class PurChaSingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurChaSingApplication.class, args);
    }

}
