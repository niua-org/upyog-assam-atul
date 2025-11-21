package org.egov.bpa.web.model.property.softthink;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoftThinkHoldingSummary {
    @JsonProperty("HoldingId")
    private String holdingId;
    
    @JsonProperty("HoldingType")
    private String holdingType;
    
    @JsonProperty("ConstructionType")
    private String constructionType;
    
    @JsonProperty("UseOfHolding")
    private String usedHolding;
    
    @JsonProperty("Storied")
    private String storied;
    
    @JsonProperty("IsParking")
    private String isParking;
    
    @JsonProperty("PlinthArea")
    private String plinthArea;
    
    @JsonProperty("AnnualRentValue")
    private String annualRentValue;
}