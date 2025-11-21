package org.egov.bpa.service.property.sumato;


import org.egov.bpa.config.SumatoFeignConfig;
import org.egov.bpa.web.model.property.sumato.SumatoPropertyRequest;
import org.egov.bpa.web.model.property.sumato.SumatoPropertyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "property-service",
        url = "${sumato.property.service.url}",
        configuration = SumatoFeignConfig.class
)
public interface SumatoPropertyServiceClient {

    @PostMapping("/obps/holding/fetch")
    SumatoPropertyResponse fetchPropertyDetails(@RequestBody SumatoPropertyRequest request);
}

