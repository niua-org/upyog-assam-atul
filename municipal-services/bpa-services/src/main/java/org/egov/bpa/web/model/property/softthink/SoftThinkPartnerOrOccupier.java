package org.egov.bpa.web.model.property.softthink;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoftThinkPartnerOrOccupier {
    @JsonProperty("IsPrincipal")
    private Boolean isPrincipal;
    
    @JsonProperty("PartnerOrOccupierName")
    private String partnerOrOccupierName;
    
    @JsonProperty("PartnerOrOccupierAge")
    private String partnerOrOccupierAge;
    
    @JsonProperty("PartnerOrOccupierGender")
    private String partnerOrOccupierGender;

    @JsonProperty("GuardianName")
    private String guardianName;

    @JsonProperty("Relation")
    private String relation;

    @JsonProperty("Designation")
    private String designation;

    @JsonProperty("TypeName")
    private String typeName;

    @JsonProperty("Remarks")
    private String remarks;
}