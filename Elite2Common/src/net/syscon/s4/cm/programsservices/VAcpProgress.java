package net.syscon.s4.cm.programsservices;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the V_ACP_PROGRESS database table.
 * 
 */
public class VAcpProgress implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal offPrgrefId;

	private BigDecimal offenderPrgObligationId;

	private String profileCommentText;

	private Date profileCompletionDate;

	private String profileNeededFlag;

	private String programClass;

	private String programDescription;

	private BigDecimal programId;

	private BigDecimal programListSeq;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public VAcpProgress() {
		// VAcpProgress
	}

	public BigDecimal getOffPrgrefId() {
		return this.offPrgrefId;
	}

	public void setOffPrgrefId(BigDecimal offPrgrefId) {
		this.offPrgrefId = offPrgrefId;
	}

	public BigDecimal getOffenderPrgObligationId() {
		return this.offenderPrgObligationId;
	}

	public void setOffenderPrgObligationId(BigDecimal offenderPrgObligationId) {
		this.offenderPrgObligationId = offenderPrgObligationId;
	}

	public String getProfileCommentText() {
		return this.profileCommentText;
	}

	public void setProfileCommentText(String profileCommentText) {
		this.profileCommentText = profileCommentText;
	}

	public Date getProfileCompletionDate() {
		return this.profileCompletionDate;
	}

	public void setProfileCompletionDate(Date profileCompletionDate) {
		this.profileCompletionDate = profileCompletionDate;
	}

	public String getProfileNeededFlag() {
		return this.profileNeededFlag;
	}

	public void setProfileNeededFlag(String profileNeededFlag) {
		this.profileNeededFlag = profileNeededFlag;
	}

	public String getProgramClass() {
		return this.programClass;
	}

	public void setProgramClass(String programClass) {
		this.programClass = programClass;
	}

	public String getProgramDescription() {
		return this.programDescription;
	}

	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}

	public BigDecimal getProgramId() {
		return this.programId;
	}

	public void setProgramId(BigDecimal programId) {
		this.programId = programId;
	}

	public BigDecimal getProgramListSeq() {
		return this.programListSeq;
	}

	public void setProgramListSeq(BigDecimal programListSeq) {
		this.programListSeq = programListSeq;
	}

}
