package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ProgramServices extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private long programId;

	private String activeFlag;

	private String breakAllowedFlag;

	private BigDecimal capacity;

	private String code;

	private String commentText;

	private String completionFlag;

	private String contactMethod;

	private Date createDatetime;

	private String createUserId;

	private String description;

	private Date endDate;

	private Date expiryDate;

	private String functionType;

	private BigDecimal listSeq;

	private Date modifyDatetime;

	private String modifyUserId;

	private String moduleFlag;

	private String moduleType;

	private BigDecimal noOfAllowableAbsences;

	private BigDecimal noOfAllowableRestarts;

	private BigDecimal noOfSessions;

	private BigDecimal noOfWeeklySessions;

	private String phaseType;

	private String programCategory;

	private String programClass;

	private String programCode;

	private String programStatus;

	private String sealFlag;

	private BigDecimal sessionLength;

	private Date startDate;

	private String startFlag;
	
	private Integer parentProgramId;
	
	private Long parentOffPrgrefId;
	
	private Long offPrgrefId;
	
	

	public Long getOffPrgrefId() {
		return offPrgrefId;
	}

	public void setOffPrgrefId(Long offPrgrefId) {
		this.offPrgrefId = offPrgrefId;
	}

	@JsonProperty("canDisplay")
	 private Boolean canDisplay = true;

	public ProgramServices() {
		// ProgramServices
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	public Long getParentOffPrgrefId() {
		return parentOffPrgrefId;
	}

	public void setParentOffPrgrefId(Long parentOffPrgrefId) {
		this.parentOffPrgrefId = parentOffPrgrefId;
	}
	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	public long getProgramId() {
		return this.programId;
	}

	public void setProgramId(final long programId) {
		this.programId = programId;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getBreakAllowedFlag() {
		return this.breakAllowedFlag;
	}

	public void setBreakAllowedFlag(final String breakAllowedFlag) {
		this.breakAllowedFlag = breakAllowedFlag;
	}

	public BigDecimal getCapacity() {
		return this.capacity;
	}

	public void setCapacity(final BigDecimal capacity) {
		this.capacity = capacity;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public String getCompletionFlag() {
		return this.completionFlag;
	}

	public void setCompletionFlag(final String completionFlag) {
		this.completionFlag = completionFlag;
	}

	public String getContactMethod() {
		return this.contactMethod;
	}

	public void setContactMethod(final String contactMethod) {
		this.contactMethod = contactMethod;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getFunctionType() {
		return this.functionType;
	}

	public void setFunctionType(final String functionType) {
		this.functionType = functionType;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getModuleFlag() {
		return this.moduleFlag;
	}

	public void setModuleFlag(final String moduleFlag) {
		this.moduleFlag = moduleFlag;
	}

	public String getModuleType() {
		return this.moduleType;
	}

	public void setModuleType(final String moduleType) {
		this.moduleType = moduleType;
	}

	public BigDecimal getNoOfAllowableAbsences() {
		return this.noOfAllowableAbsences;
	}

	public void setNoOfAllowableAbsences(final BigDecimal noOfAllowableAbsences) {
		this.noOfAllowableAbsences = noOfAllowableAbsences;
	}

	public BigDecimal getNoOfAllowableRestarts() {
		return this.noOfAllowableRestarts;
	}

	public void setNoOfAllowableRestarts(final BigDecimal noOfAllowableRestarts) {
		this.noOfAllowableRestarts = noOfAllowableRestarts;
	}

	public BigDecimal getNoOfSessions() {
		return this.noOfSessions;
	}

	public void setNoOfSessions(final BigDecimal noOfSessions) {
		this.noOfSessions = noOfSessions;
	}

	public BigDecimal getNoOfWeeklySessions() {
		return this.noOfWeeklySessions;
	}

	public void setNoOfWeeklySessions(final BigDecimal noOfWeeklySessions) {
		this.noOfWeeklySessions = noOfWeeklySessions;
	}

	public String getPhaseType() {
		return this.phaseType;
	}

	public void setPhaseType(final String phaseType) {
		this.phaseType = phaseType;
	}

	public String getProgramCategory() {
		return this.programCategory;
	}

	public void setProgramCategory(final String programCategory) {
		this.programCategory = programCategory;
	}

	public String getProgramClass() {
		return this.programClass;
	}

	public void setProgramClass(final String programClass) {
		this.programClass = programClass;
	}

	public String getProgramCode() {
		return this.programCode;
	}

	public void setProgramCode(final String programCode) {
		this.programCode = programCode;
	}

	public String getProgramStatus() {
		return this.programStatus;
	}

	public void setProgramStatus(final String programStatus) {
		this.programStatus = programStatus;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getSessionLength() {
		return this.sessionLength;
	}

	public void setSessionLength(final BigDecimal sessionLength) {
		this.sessionLength = sessionLength;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the startFlag
	 */
	public String getStartFlag() {
		return startFlag;
	}

	/**
	 * @param startFlag
	 *            the startFlag to set
	 */
	public void setStartFlag(final String startFlag) {
		this.startFlag = startFlag;
	}

	public Integer getParentProgramId() {
		return parentProgramId;
	}

	public void setParentProgramId(final Integer parentProgramId) {
		this.parentProgramId = parentProgramId;
	}
	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(final Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}
}
