package net.syscon.s4.im.beans;


import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderDetails extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenders")
	private List<OffenderDetail> offenders;

	public List<OffenderDetail> getOffenders() {
		return offenders;
	}

	public void setOffenders(List<OffenderDetail> offenders) {
		this.offenders = offenders;
	}
}
