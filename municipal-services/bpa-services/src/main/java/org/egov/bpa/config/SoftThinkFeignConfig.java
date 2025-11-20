package org.egov.bpa.config;

import feign.codec.ErrorDecoder;
import org.egov.bpa.service.property.PropertyServiceErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoftThinkFeignConfig {

    @Bean("softThinkErrorDecoder")
    public ErrorDecoder softThinkErrorDecoder() {
        return new PropertyServiceErrorDecoder();
    }
}
