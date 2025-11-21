package org.egov.bpa.web.model.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyValidationResponse {
    private String property;
    private boolean isValid;
    private boolean taxPaid;
    private String message;
    private String status;
    private PropertyDetails details;
}