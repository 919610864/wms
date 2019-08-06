package com.yhdx.wms.inside.server.startup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(
        scanBasePackages =
                {
                        "com.yhdx.wms.framework",
                        "com.yhdx.wms.inside.server"
                }
)
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.yhdx.wms.inside.server.dao.mapper"})
@EnableTransactionManagement
public class WmsInsideServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsInsideServerApplication.class, args);
    }

}
