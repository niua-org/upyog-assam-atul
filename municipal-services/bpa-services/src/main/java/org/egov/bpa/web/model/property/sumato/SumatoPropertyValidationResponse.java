package org.egov.bpa.web.model.property.sumato;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SumatoPropertyValidationResponse {
    private String property;
    private Boolean isValid;
    private Boolean taxPaid;
    private String message;
    private SumatoPropertyDetails details;
}




