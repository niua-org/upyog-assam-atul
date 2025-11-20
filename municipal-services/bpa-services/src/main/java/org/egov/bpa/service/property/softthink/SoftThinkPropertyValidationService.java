package org.egov.bpa.service.property.softthink;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.egov.bpa.exception.PropertyServiceException;
import org.egov.bpa.web.model.property.softthink.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SoftThinkPropertyValidationService {

    private final SoftThinkRestClient softThinkRestClient;

    public SoftThinkPropertyValidationResponse validatePropertyWithTaxStatus(String holdingNumber) {
        try {
            SoftThinkPropertyRequest request = SoftThinkPropertyRequest.builder()
                    .HoldingNumber(holdingNumber)
                    .build();

            SoftThinkPropertyResponse response = softThinkRestClient.fetchHoldingDetails(request);

            boolean isValid = isPropertyValid(response);
            boolean taxPaid = isTaxPaid(response);

            return SoftThinkPropertyValidationResponse.builder()
                    .HoldingNumber(holdingNumber)
                    .isValid(isValid)
                    .taxPaid(taxPaid)
                    .message(response.getMessage())
                    .status(response.getStatus())
                    .resultData(response.getResultData())
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


}