package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ArrestRelatedOffences extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty ("arrestOffenceId")
	private Long arrestOffenceId;
	
	@JsonProperty ("arrestId")
	private Long arrestId;
	
	
	@JsonProperty ("offenceDate")
	private Date offenceDate;
	
	@JsonProperty ("offenceCode")
	private String offenceCode;
	
	@JsonProperty ("statuteCode")
	private String statuteCode;
	
	
	@JsonProperty ("caseInfoNumber")
	private String caseInfoNumber;
	
	@JsonProperty ("offenceType")
	private String offenceType;
	
	@JsonProperty ("offenceCounts")
	private Long offenceCounts;
	
	@JsonProperty ("createUserId")
	private String createUserId;
	
	@JsonProperty ("createDate")
	private Date createDate;
	
	@JsonProperty ("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty ("modifyUserId")
	private String modifyUserId;

	/**
	 * @return the arrestOffenceId
	 */
	public Long getArrestOffenceId() {
		return arrestOffenceId;
	}

	/**
	 * @param arrestOffenceId the arrestOffenceId to set
	 */
	public void setArrestOffenceId(final Long arrestOffenceId) {
		this.arrestOffenceId = arrestOffenceId;
	}

	/**
	 * @return the arrestId
	 */
	public Long getArrestId() {
		return arrestId;
	}

	/**
	 * @param arrestId the arrestId to set
	 */
	public void setArrestId(final Long arrestId) {
		this.arrestId = arrestId;
	}

	/**
	 * @return the offenceDate
	 */
	public Date getOffenceDate() {
		return offenceDate;
	}

	/**
	 * @param offenceDate the offenceDate to set
	 */
	public void setOffenceDate(final Date offenceDate) {
		this.offenceDate = offenceDate;
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

	/**
	 * @return the caseInfoNumber
	 */
	public String getCaseInfoNumber() {
		return caseInfoNumber;
	}

	/**
	 * @param caseInfoNumber the caseInfoNumber to set
	 */
	public void setCaseInfoNumber(final String caseInfoNumber) {
		this.caseInfoNumber = caseInfoNumber;
	}

	/**
	 * @return the offenceType
	 */
	public String getOffenceType() {
		return offenceType;
	}

	/**
	 * @param offenceType the offenceType to set
	 */
	public void setOffenceType(final String offenceType) {
		this.offenceType = offenceType;
	}

	/**
	 * @return the offenceCounts
	 */
	public Long getOffenceCounts() {
		return offenceCounts;
	}

	/**
	 * @param offenceCounts the offenceCounts to set
	 */
	public void setOffenceCounts(final Long offenceCounts) {
		this.offenceCounts = offenceCounts;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	

}
