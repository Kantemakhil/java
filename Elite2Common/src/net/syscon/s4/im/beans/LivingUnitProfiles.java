package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class LivingUnitProfiles extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("description")
	private String description;
	@JsonProperty("intLocProfileCode")
	private String intLocProfileCode;
	@JsonProperty("intLocProfileType")
	private String intLocProfileType;
	@JsonProperty("livingUnitId")
	private BigDecimal livingUnitId;
	@JsonProperty("profileId")
	private BigDecimal profileId;

	public LivingUnitProfiles() {
		// LivingUnitProfiles
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId
	 *            the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the intLocProfileCode
	 */
	public String getIntLocProfileCode() {
		return intLocProfileCode;
	}

	/**
	 * @param intLocProfileCode
	 *            the intLocProfileCode to set
	 */
	public void setIntLocProfileCode(final String intLocProfileCode) {
		this.intLocProfileCode = intLocProfileCode;
	}

	/**
	 * @return the intLocProfileType
	 */
	public String getIntLocProfileType() {
		return intLocProfileType;
	}

	/**
	 * @param intLocProfileType
	 *            the intLocProfileType to set
	 */
	public void setIntLocProfileType(final String intLocProfileType) {
		this.intLocProfileType = intLocProfileType;
	}

	/**
	 * @return the livingUnitId
	 */
	public BigDecimal getLivingUnitId() {
		return livingUnitId;
	}

	/**
	 * @param livingUnitId
	 *            the livingUnitId to set
	 */
	public void setLivingUnitId(final BigDecimal livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	/**
	 * @return the profileId
	 */
	public BigDecimal getProfileId() {
		return profileId;
	}

	/**
	 * @param profileId
	 *            the profileId to set
	 */
	public void setProfileId(final BigDecimal profileId) {
		this.profileId = profileId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
