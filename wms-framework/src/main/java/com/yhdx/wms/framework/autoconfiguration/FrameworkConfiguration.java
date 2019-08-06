package com.yhdx.wms.framework.autoconfiguration;

import com.yhdx.wms.framework.aop.DuplicateKeyCheckAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FrameworkConfiguration {

    @Bean
    public DuplicateKeyCheckAspect duplicateKeyCheckAspect() {
        return new DuplicateKeyCheckAspect();
    }

}
