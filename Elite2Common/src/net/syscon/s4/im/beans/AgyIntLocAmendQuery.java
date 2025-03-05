package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class AgyIntLocAmendQuery extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("pAgyLocId")
	private String pAgyLocId;

	@JsonProperty("pLevel1Code")
	private String pLevel1Code;

	@JsonProperty("pLevel2Code")
	private String pLevel2Code;

	@JsonProperty("pLevel4Code")
	private String pLevel4Code;

	@JsonProperty("pLevel3Code")
	private String pLevel3Code;

	@JsonProperty("pAmendDateFrom")
	private Date pAmendDateFrom;

	@JsonProperty("pAmendDateTo")
	private Date pAmendDateTo;

	@JsonProperty("description")
	private String description;

	@JsonProperty("columnName")
	private String columnName;

	@JsonProperty("oldValue")
	private String oldValue;

	@JsonProperty("newValue")
	private String newValue;

	@JsonProperty("deactivateReasonCode")
	private String deactivateReasonCode;

	@JsonProperty("actionCode")
	private String actionCode;

	@JsonProperty("amendDate")
	private Date amendDate;

	@JsonProperty("amendUserId")
	private String amendUserId;

	@JsonProperty("amendUserDate")
	private String amendUserDate;

	@JsonProperty("livingUnitCode")
	private String livingUnitCode;

	/**
	 * @return the livingUnitCode
	 */
	public String getLivingUnitCode() {
		return livingUnitCode;
	}

	/**
	 * @param livingUnitCode
	 *            the livingUnitCode to set
	 */
	public void setLivingUnitCode(final String livingUnitCode) {
		this.livingUnitCode = livingUnitCode;
	}

	/**
	 * @return the amendUserDate
	 */
	public String getAmendUserDate() {
		return amendUserDate;
	}

	/**
	 * @param amendUserDate
	 *            the amendUserDate to set
	 */
	public void setAmendUserDate(final String amendUserDate) {
		this.amendUserDate = amendUserDate;
	}

	public String getpAgyLocId() {
		return pAgyLocId;
	}

	public void setpAgyLocId(final String pAgyLocId) {
		this.pAgyLocId = pAgyLocId;
	}

	public String getpLevel1Code() {
		return pLevel1Code;
	}

	public void setpLevel1Code(final String pLevel1Code) {
		this.pLevel1Code = pLevel1Code;
	}

	public String getpLevel2Code() {
		return pLevel2Code;
	}

	public void setpLevel2Code(final String pLevel2Code) {
		this.pLevel2Code = pLevel2Code;
	}

	public String getpLevel4Code() {
		return pLevel4Code;
	}

	public void setpLevel4Code(final String pLevel4Code) {
		this.pLevel4Code = pLevel4Code;
	}

	public String getpLevel3Code() {
		return pLevel3Code;
	}

	public void setpLevel3Code(final String pLevel3Code) {
		this.pLevel3Code = pLevel3Code;
	}

	public Date getpAmendDateFrom() {
		return pAmendDateFrom;
	}

	public void setpAmendDateFrom(final Date pAmendDateFrom) {
		this.pAmendDateFrom = pAmendDateFrom;
	}

	public Date getpAmendDateTo() {
		return pAmendDateTo;
	}

	public void setpAmendDateTo(final Date pAmendDateTo) {
		this.pAmendDateTo = pAmendDateTo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(final String columnName) {
		this.columnName = columnName;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(final String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(final String newValue) {
		this.newValue = newValue;
	}

	public String getDeactivateReasonCode() {
		return deactivateReasonCode;
	}

	public void setDeactivateReasonCode(final String deactivateReasonCode) {
		this.deactivateReasonCode = deactivateReasonCode;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(final String actionCode) {
		this.actionCode = actionCode;
	}

	public Date getAmendDate() {
		return amendDate;
	}

	public void setAmendDate(final Date amendDate) {
		this.amendDate = amendDate;
	}

	public String getAmendUserId() {
		return amendUserId;
	}

	public void setAmendUserId(final String amendUserId) {
		this.amendUserId = amendUserId;
	}

}
