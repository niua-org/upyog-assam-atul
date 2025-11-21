package org.egov.bpa.service.property.softthink;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.egov.bpa.exception.PropertyServiceException;
import org.egov.bpa.web.model.property.PropertyDetails;
import org.egov.bpa.web.model.property.PropertyValidationResponse;
import org.egov.bpa.web.model.property.softthink.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SoftThinkPropertyValidationService {

    private final SoftThinkRestClient softThinkRestClient;

    public PropertyValidationResponse validatePropertyWithTaxStatus(String holdingNumber) {
        try {
            SoftThinkPropertyRequest request = SoftThinkPropertyRequest.builder()
                    .HoldingNumber(holdingNumber)
                    .build();

            SoftThinkPropertyResponse response = softThinkRestClient.fetchHoldingDetails(request);

            boolean isValid = isPropertyValid(response);
            boolean taxPaid = isTaxPaid(response);

            return PropertyValidationResponse.builder()
                    .property(holdingNumber)
                    .isValid(isValid)
                    .taxPaid(taxPaid)
                    .message(isValid ? "Property validation successful" : "Property validation failed")
                    .status(response.getStatus())
                    .details(mapToPropertyDetails(response))
                    .build();

        } catch (Exception e) {
            log.error("Error validating property: {}", e.getMessage());
            throw new PropertyServiceException("Failed to validate property: " + e.getMessage());
        }
    }

    private boolean isPropertyValid(SoftThinkPropertyResponse response) {
        return response != null
                && "success".equalsIgnoreCase(response.getStatus())
                && response.getResultData() != null
                && (response.getResultData().getNewHoldingNumber() != null || response.getResultData().getOldHoldingNumber() != null);
    }

    private boolean isTaxPaid(SoftThinkPropertyResponse response) {
        if (response == null || response.getResultData() == null) {
            return false;
        }
        return Boolean.TRUE.equals(response.getResultData().getIsPropertyTaxUpToDate());
    }

    private PropertyDetails mapToPropertyDetails(SoftThinkPropertyResponse response) {
        if (response == null || response.getResultData() == null) {
            return null;
        }

        SoftThinkPropertyResultData resultData = response.getResultData();
        return PropertyDetails.builder()
                .ownerName(resultData.getCitizenName())
                .guardianName(resultData.getListPartnerOrOccupier().get(0).getGuardianName())
                .address(resultData.getAddress())
                .phone(resultData.getMobileNo())
                .ulb(resultData.getCityName())
                .ward(resultData.getWardNumber())
                .buildingUse(resultData.getListHoldingSummary().get(0).getUsedHolding())
                .propertyVendor("SOFTTHINK")
                .build();
    }


}