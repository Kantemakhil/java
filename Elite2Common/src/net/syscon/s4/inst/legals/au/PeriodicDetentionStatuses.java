package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class PeriodicDetentionStatuses extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("peroidDetentionStatus")
	private String peroidDetentionStatus;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("updateAllowedFlag")
	private String updateAllowedFlag;
	
	@JsonProperty("numberOfAwol")
	private Long numberOfAwol;
	
	@JsonProperty("activeFlag")
	private String activeFlag;
	
	@JsonProperty("numberOfPenalty")
	private Long numberOfPenalty;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	
	@JsonProperty("expiredDate")
	private Date expiredDate;


	public String getPeroidDetentionStatus() {
		return peroidDetentionStatus;
	}


	public void setPeroidDetentionStatus(final String peroidDetentionStatus) {
		this.peroidDetentionStatus = peroidDetentionStatus;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(final String description) {
		this.description = description;
	}


	public String getUpdateAllowedFlag() {
		return updateAllowedFlag;
	}


	public void setUpdateAllowedFlag(final String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}


	public Long getNumberOfAwol() {
		return numberOfAwol;
	}


	public void setNumberOfAwol(final Long numberOfAwol) {
		this.numberOfAwol = numberOfAwol;
	}


	public String getActiveFlag() {
		return activeFlag;
	}


	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}


	public Long getNumberOfPenalty() {
		return numberOfPenalty;
	}


	public void setNumberOfPenalty(final Long numberOfPenalty) {
		this.numberOfPenalty = numberOfPenalty;
	}


	public String getModifyUserId() {
		return modifyUserId;
	}


	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}


	public Date getExpiredDate() {
		return expiredDate;
	}


	public void setExpiredDate(final Date expiredDate) {
		this.expiredDate = expiredDate;
	}

}
