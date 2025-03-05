package net.syscon.s4.inst.legals.legalorders;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderPbDecisions extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("createDateTime")
	private Date createDateTime;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("latestPbRelease")
	private Date latestPbRelease;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("nbtHearingDate")
	private Date nbtHearingDate;

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getLatestPbRelease() {
		return latestPbRelease;
	}

	public void setLatestPbRelease(Date latestPbRelease) {
		this.latestPbRelease = latestPbRelease;
	}

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Date getNbtHearingDate() {
		return nbtHearingDate;
	}

	public void setNbtHearingDate(Date nbtHearingDate) {
		this.nbtHearingDate = nbtHearingDate;
	}

}
