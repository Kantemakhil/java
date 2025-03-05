package net.syscon.s4.im.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class TempLivingUnitProfiles extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	private Object createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Object modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("profileCode")
	private String profileCode;

	@JsonProperty("profileType")
	private String profileType;

	/**
	 * Creates new OffenderAlertsCommitBean class Object
	 */
	public TempLivingUnitProfiles() {
		// TempLivingUnitProfiles
	}

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Object createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Object modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getProfileCode() {
		return this.profileCode;
	}

	public void setProfileCode(final String profileCode) {
		this.profileCode = profileCode;
	}

	public String getProfileType() {
		return this.profileType;
	}

	public void setProfileType(final String profileType) {
		this.profileType = profileType;
	}

}