package org.egov.bpa.web.model.property.softthink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SoftThinkPropertyValidationResponse {
    private String HoldingNumber;
    private boolean isValid;
    private boolean taxPaid;
    private String message;
    private String status;
    private SoftThinkResultData resultData;
}