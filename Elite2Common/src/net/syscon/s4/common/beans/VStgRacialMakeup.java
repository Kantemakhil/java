package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the V_STG_RACIAL_MAKEUP database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VStgRacialMakeup extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("raceCount")
	private BigDecimal raceCount;
	@JsonProperty("raceDescription")
	private String raceDescription;
	@JsonProperty("stgId")
	private BigDecimal stgId;

	public VStgRacialMakeup() {
		// VStgRacialMakeup
	}

	public BigDecimal getRaceCount() {
		return this.raceCount;
	}

	public void setRaceCount(final BigDecimal raceCount) {
		this.raceCount = raceCount;
	}

	public String getRaceDescription() {
		return this.raceDescription;
	}

	public void setRaceDescription(final String raceDescription) {
		this.raceDescription = raceDescription;
	}

	public BigDecimal getStgId() {
		return this.stgId;
	}

	public void setStgId(final BigDecimal stgId) {
		this.stgId = stgId;
	}

}
