package org.egov.bpa.web.model.property.softthink;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SoftThinkPropertyResponse {
    private String status;
    private String message;
    @JsonProperty("ResultData")
    private SoftThinkPropertyResultData resultData;
}