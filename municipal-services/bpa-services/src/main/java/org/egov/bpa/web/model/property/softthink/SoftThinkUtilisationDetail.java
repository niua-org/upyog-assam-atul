package org.egov.bpa.web.model.property.softthink;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoftThinkUtilisationDetail {
    @JsonProperty("HID")
    private String hid;
    
    @JsonProperty("PurposeType")
    private String purposeType;
    
    @JsonProperty("Floor")
    private String floor;
    
    @JsonProperty("FloorType")
    private String floorType;
    
    @JsonProperty("Portion")
    private String portion;
    
    @JsonProperty("Rent")
    private String rent;
    
    @JsonProperty("Remarks")
    private String remarks;
}