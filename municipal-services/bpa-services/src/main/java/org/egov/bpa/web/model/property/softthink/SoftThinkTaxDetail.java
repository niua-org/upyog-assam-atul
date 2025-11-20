package org.egov.bpa.web.model.property.softthink;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoftThinkTaxDetail {
    @JsonProperty("HID")
    private String hid;
}