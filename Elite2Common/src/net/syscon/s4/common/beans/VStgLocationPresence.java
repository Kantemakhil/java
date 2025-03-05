package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the V_STG_LOCATION_PRESENCE database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VStgLocationPresence extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("locationCount")
	private BigDecimal locationCount;
	@JsonProperty("locationDescription")
	private String locationDescription;
	@JsonProperty("stgId")
	private BigDecimal stgId;

	public VStgLocationPresence() {
//		VStgLocationPresence
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public BigDecimal getLocationCount() {
		return this.locationCount;
	}

	public void setLocationCount(final BigDecimal locationCount) {
		this.locationCount = locationCount;
	}

	public String getLocationDescription() {
		return this.locationDescription;
	}

	public void setLocationDescription(final String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public BigDecimal getStgId() {
		return this.stgId;
	}

	public void setStgId(final BigDecimal stgId) {
		this.stgId = stgId;
	}

}
