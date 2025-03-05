package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenceIndicator extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenceIndicatorId")
	private Long offenceIndicatorId;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("indicatorCode")
	private String indicatorCode;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offence")
	private Offence offence;
	

	@JsonProperty("offenceCode")
	private String offenceCode;

	@JsonProperty("statuteCode")
	private String statuteCode;
	
	@JsonProperty("offenceId")
	private Long offenceId;

	@JsonProperty("indicatorCodeDescription")
	private String indicatorCodeDescription;
	
	@JsonProperty("listSeq")
	private Integer listSeq;
	/**
	 *
	 * @return
	 */
	public Long getOffenceIndicatorId() {
		return offenceIndicatorId;
	}

	/**
	 *
	 * @param offenceIndicatorId
	 */
	public void setOffenceIndicatorId(final Long offenceIndicatorId) {
		this.offenceIndicatorId = offenceIndicatorId;
	}

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
	public void setCreateDatetime(final Date createDatetime) {
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
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getIndicatorCode() {
		return indicatorCode;
	}

	/**
	 *
	 * @param indicatorCode
	 */
	public void setIndicatorCode(final String indicatorCode) {
		this.indicatorCode = indicatorCode;
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
	public void setModifyDatetime(final Date modifyDatetime) {
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
	public void setModifyUserId(final String modifyUserId) {
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
	public void setSealFlag(final String sealFlag) {
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
	public void setOffence(final Offence offence) {
		this.offence = offence;
	}

	/**
	 * @return the offenceCode
	 */
	public String getOffenceCode() {
		return offenceCode;
	}

	/**
	 * @param offenceCode the offenceCode to set
	 */
	public void setOffenceCode(final String offenceCode) {
		this.offenceCode = offenceCode;
	}

	/**
	 * @return the statuteCode
	 */
	public String getStatuteCode() {
		return statuteCode;
	}

	/**
	 * @param statuteCode the statuteCode to set
	 */
	public void setStatuteCode(final String statuteCode) {
		this.statuteCode = statuteCode;
	}

	public Long getOffenceId() {
		return offenceId;
	}

	public void setOffenceId(Long offenceId) {
		this.offenceId = offenceId;
	}

	public String getIndicatorCodeDescription() {
		return indicatorCodeDescription;
	}

	public void setIndicatorCodeDescription(String indicatorCodeDescription) {
		this.indicatorCodeDescription = indicatorCodeDescription;
	}

	public Integer getListSeq() {
		return listSeq;
	}

	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}

}