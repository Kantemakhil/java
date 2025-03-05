package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VProgramPhases extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("breakAllowedFlag")
	private String breakAllowedFlag;
	@JsonProperty("capacity")
	private BigDecimal capacity;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("description")
	private String description;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
	@JsonProperty("moduleFlag")
	private String moduleFlag;
	@JsonProperty("moduleType")
	private String moduleType;
	@JsonProperty("moduleTypeDesc")
	private String moduleTypeDesc;
	@JsonProperty("noOfSessions")
	private BigDecimal noOfSessions;
	@JsonProperty("phaseType")
	private String phaseType;
	@JsonProperty("phaseTypeDesc")
	private String phaseTypeDesc;
	@JsonProperty("programId")
	private BigDecimal programId;
	@JsonProperty("programPhaseId")
	private BigDecimal programPhaseId;
	@JsonProperty("sessionLength")
	private BigDecimal sessionLength;
	@JsonProperty("nbtSessionLength")
	private String nbtSessionLength;
	@JsonProperty("description1")
	private String description1;
	

	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("seqOne")
	private BigDecimal seqOne;
	
	@JsonProperty("nbtDescription")
	private String nbtDescription;
	
	@JsonProperty("userName")
	private String userName;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public VProgramPhases() {
		// VProgramPhases
	}

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the breakAllowedFlag
	 */
	public String getBreakAllowedFlag() {
		return breakAllowedFlag;
	}

	/**
	 * @param breakAllowedFlag
	 *            the breakAllowedFlag to set
	 */
	public void setBreakAllowedFlag(final String breakAllowedFlag) {
		this.breakAllowedFlag = breakAllowedFlag;
	}

	/**
	 * @return the capacity
	 */
	public BigDecimal getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity
	 *            the capacity to set
	 */
	public void setCapacity(final BigDecimal capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText
	 *            the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
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
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the listSeq
	 */
	public BigDecimal getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the moduleFlag
	 */
	public String getModuleFlag() {
		return moduleFlag;
	}

	/**
	 * @param moduleFlag
	 *            the moduleFlag to set
	 */
	public void setModuleFlag(final String moduleFlag) {
		this.moduleFlag = moduleFlag;
	}

	/**
	 * @return the moduleType
	 */
	public String getModuleType() {
		return moduleType;
	}

	/**
	 * @param moduleType
	 *            the moduleType to set
	 */
	public void setModuleType(final String moduleType) {
		this.moduleType = moduleType;
	}

	/**
	 * @return the moduleTypeDesc
	 */
	public String getModuleTypeDesc() {
		return moduleTypeDesc;
	}

	/**
	 * @param moduleTypeDesc
	 *            the moduleTypeDesc to set
	 */
	public void setModuleTypeDesc(final String moduleTypeDesc) {
		this.moduleTypeDesc = moduleTypeDesc;
	}

	/**
	 * @return the noOfSessions
	 */
	public BigDecimal getNoOfSessions() {
		return noOfSessions;
	}

	/**
	 * @param noOfSessions
	 *            the noOfSessions to set
	 */
	public void setNoOfSessions(final BigDecimal noOfSessions) {
		this.noOfSessions = noOfSessions;
	}

	/**
	 * @return the phaseType
	 */
	public String getPhaseType() {
		return phaseType;
	}

	/**
	 * @param phaseType
	 *            the phaseType to set
	 */
	public void setPhaseType(final String phaseType) {
		this.phaseType = phaseType;
	}

	/**
	 * @return the phaseTypeDesc
	 */
	public String getPhaseTypeDesc() {
		return phaseTypeDesc;
	}

	/**
	 * @param phaseTypeDesc
	 *            the phaseTypeDesc to set
	 */
	public void setPhaseTypeDesc(final String phaseTypeDesc) {
		this.phaseTypeDesc = phaseTypeDesc;
	}

	/**
	 * @return the programId
	 */
	public BigDecimal getProgramId() {
		return programId;
	}

	/**
	 * @param programId
	 *            the programId to set
	 */
	public void setProgramId(final BigDecimal programId) {
		this.programId = programId;
	}

	/**
	 * @return the programPhaseId
	 */
	public BigDecimal getProgramPhaseId() {
		return programPhaseId;
	}

	/**
	 * @param programPhaseId
	 *            the programPhaseId to set
	 */
	public void setProgramPhaseId(final BigDecimal programPhaseId) {
		this.programPhaseId = programPhaseId;
	}

	/**
	 * @return the sessionLength
	 */
	public BigDecimal getSessionLength() {
		return sessionLength;
	}

	/**
	 * @param sessionLength
	 *            the sessionLength to set
	 */
	public void setSessionLength(final BigDecimal sessionLength) {
		this.sessionLength = sessionLength;
	}

	/**
	 * @return the nbtSessionLength
	 */
	public String getNbtSessionLength() {
		return nbtSessionLength;
	}

	/**
	 * @param nbtSessionLength
	 *            the nbtSessionLength to set
	 */
	public void setNbtSessionLength(final String nbtSessionLength) {
		this.nbtSessionLength = nbtSessionLength;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the seqOne
	 */
	public BigDecimal getSeqOne() {
		return seqOne;
	}

	/**
	 * @param seqOne the seqOne to set
	 */
	public void setSeqOne(BigDecimal seqOne) {
		this.seqOne = seqOne;
	}

	/**
	 * @return the description1
	 */
	public String getDescription1() {
		return description1;
	}

	/**
	 * @param description1 the description1 to set
	 */
	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	/**
	 * @return the nbtDescription
	 */
	public String getNbtDescription() {
		return nbtDescription;
	}

	/**
	 * @param nbtDescription the nbtDescription to set
	 */
	public void setNbtDescription(String nbtDescription) {
		this.nbtDescription = nbtDescription;
	}

	
}
