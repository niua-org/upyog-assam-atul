package org.egov.bpa.web.model.property.softthink;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoftThinkCarpetAreaDetail {
    @JsonProperty("HID")
    private String hid;
    
    @JsonProperty("PurposeType")
    private String purposeType;
    
    @JsonProperty("Floor")
    private String floor;
    
    @JsonProperty("Particulars")
    private String particulars;
    
    @JsonProperty("CarpetArea")
    private Integer carpetArea;
    
    @JsonProperty("Remarks")
    private String remarks;
}