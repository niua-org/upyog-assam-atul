package org.egov.bpa.service.property;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.egov.bpa.config.SoftThinkFeignConfig;
import org.egov.bpa.service.property.softthink.SoftThinkPropertyValidationService;
import org.egov.bpa.service.property.sumato.SumatoPropertyValidationService;
import org.egov.bpa.web.model.property.PropertyValidationResponse;
import org.egov.bpa.web.model.property.sumato.PropertyRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PropertyValidationService {

    private final SoftThinkPropertyValidationService softThinkPropertyValidationService;
    private final SumatoPropertyValidationService sumatoPropertyValidationService;
    private final SoftThinkFeignConfig softThinkConfig;

    public PropertyValidationResponse validateProperty(PropertyRequest propertyRequest) {
        String propertyNumber = propertyRequest.getPropertyNumber();
        String tenantId = propertyRequest.getTenantId().split("\\.")[1];
        log.info("Received property validation request for property number: {} in tenant: {}", propertyNumber, tenantId);

        if (softThinkConfig.softthinkPropertyCities.contains(tenantId)) {
            log.info("Property validation request for property number: {} in tenant: {} is handled by SoftThink", propertyNumber, tenantId);
            return softThinkPropertyValidationService.validatePropertyWithTaxStatus(propertyNumber);
        } else {
            return sumatoPropertyValidationService.validatePropertyWithTaxStatus(propertyNumber);
        }
    }
}