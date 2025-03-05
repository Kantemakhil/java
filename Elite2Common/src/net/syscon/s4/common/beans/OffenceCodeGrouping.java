package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenceCodeGrouping extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offence")
	private Offence offence;

	@JsonProperty("offenceGroup")
	private String offenceGroup;

	@JsonProperty("offenceCode")
	private String offenceCode;

	@JsonProperty("statuteCode")
	private String statuteCode;

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public Offence getOffence() {
		return offence;
	}

	/**
	 *
	 * @param offence
	 */
	public void setOffence(Offence offence) {
		this.offence = offence;
	}

	/**
	 *
	 * @return
	 */
	public String getOffenceGroup() {
		return offenceGroup;
	}

	/**
	 *
	 * @param offenceGroup
	 */
	public void setOffenceGroup(String offenceGroup) {
		this.offenceGroup = offenceGroup;
	}

	/**
	 *
	 * @return
	 */
	public String getOffenceCode() {
		return offenceCode;
	}

	/**
	 *
	 * @param offenceCode
	 */
	public void setOffenceCode(String offenceCode) {
		this.offenceCode = offenceCode;
	}

	/**
	 *
	 * @return
	 */
	public String getStatuteCode() {
		return statuteCode;
	}

	/**
	 *
	 * @param statuteCode
	 */
	public void setStatuteCode(String statuteCode) {
		this.statuteCode = statuteCode;
	}

}