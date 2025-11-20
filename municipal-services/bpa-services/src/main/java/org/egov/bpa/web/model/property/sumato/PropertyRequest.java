package org.egov.bpa.web.model.property.sumato;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.egov.common.contract.request.RequestInfo;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyRequest {

    private RequestInfo requestInfo;

    private String propertyNumber;

    private String tenantId;
}
