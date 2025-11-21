package org.egov.bpa.web.model.property.softthink;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoftThinkPropertyResultData {
    @JsonProperty("OldHoldingNumber")
    private String oldHoldingNumber;
    
    @JsonProperty("NewHoldingNumber")
    private String newHoldingNumber;
    
    @JsonProperty("UIN_Number")
    private String uinNumber;
    
    @JsonProperty("CitizenName")
    private String citizenName;
    
    @JsonProperty("MobileNo")
    private String mobileNo;
    
    @JsonProperty("WardNumber")
    private String wardNumber;
    
    @JsonProperty("CityName")
    private String cityName;
    
    @JsonProperty("RoadName")
    private String roadName;
    
    @JsonProperty("PostOffice")
    private String postOffice;
    
    @JsonProperty("Address")
    private String address;
    
    @JsonProperty("PinNumber")
    private String pinNumber;
    
    @JsonProperty("DagNumber")
    private String dagNumber;
    
    @JsonProperty("PattaNumber")
    private String pattaNumber;
    
    @JsonProperty("MouzaNumber")
    private String mouzaNumber;
    
    @JsonProperty("Zone")
    private String zone;
    
    @JsonProperty("LandQuantumValue")
    private String landQuantumValue;
    
    @JsonProperty("DeedNumber")
    private String deedNumber;
    
    @JsonProperty("DeedDate")
    private String deedDate;
    
    @JsonProperty("IsPropertyTaxUpToDate")
    private Boolean isPropertyTaxUpToDate;
    
    @JsonProperty("listCarpetAreaDetails")
    private List<SoftThinkCarpetAreaDetail> listCarpetAreaDetails;
    
    @JsonProperty("listPartnerOrOccupier")
    private List<SoftThinkPartnerOrOccupier> listPartnerOrOccupier;
    
    @JsonProperty("listHoldingSummary")
    private List<SoftThinkHoldingSummary> listHoldingSummary;
    
    @JsonProperty("listUtilisationDetails")
    private List<SoftThinkUtilisationDetail> listUtilisationDetails;
    
    @JsonProperty("listTaxDetails")
    private List<SoftThinkTaxDetail> listTaxDetails;
}