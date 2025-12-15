package org.egov.bpa.web.model.NOC;

import java.util.List;

import javax.validation.Valid;

import lombok.*;
import org.egov.common.contract.request.RequestInfo;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NocRequest {
	
	@JsonProperty("RequestInfo")
	private RequestInfo requestInfo;

	@Valid
	@JsonProperty("Noc")
	private Noc noc;

	@Valid
	@JsonProperty("NocList")
	private List<Noc> nocList;
	  
}
