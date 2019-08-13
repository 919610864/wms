package com.mglj.wms.inbound.server.configuration;

import com.mglj.base.aop.AuditAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(AopConfiguration.class);

    @Bean
    public AuditAspect auditAspect() {
        logger.info("初始化 AUDIT ASPECT {}" ,new AuditAspect());
        return new AuditAspect();
    }
}
