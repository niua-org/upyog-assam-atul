package org.egov.bpa.service.property.softthink;

import org.egov.bpa.config.SoftThinkFeignConfig;
import org.egov.bpa.web.model.property.softthink.SoftThinkPropertyRequest;
import org.egov.bpa.web.model.property.softthink.SoftThinkPropertyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "softthink-property-service", url = "${softthink.property.service.url}",
        configuration = SoftThinkFeignConfig.class)
public interface SoftThinkPropertyServiceClient {
    
    @PostMapping(value = "/SCMBAPI/API/Holding/FetchHoldingDetails", 
                 consumes = "application/json", 
                 produces = "application/json")
    SoftThinkPropertyResponse fetchHoldingDetails(
            @RequestHeader("Authorization") String authToken,
            @RequestBody SoftThinkPropertyRequest request
    );
}