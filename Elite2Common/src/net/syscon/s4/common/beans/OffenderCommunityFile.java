package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the OFFENDER_COMMUNITY_FILES database table.
 * 
 */
public class OffenderCommunityFile extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String caseloadType;

	private String closeFileNo;

	private Date createDatetime;

	private String createUserId;

	private Date creationDate;

	private String creationUser;

	private Date fileCreateDate;

	private String fileReference;

	private String fileSubType;

	private String fileType;

	private String holdingAgyLocId;

	private BigDecimal holdingStaffId;

	private Date modifyDatetime;

	private String modifyUserId;

	private String nonOfficerStatus;

	private BigDecimal offenderFileNum;

	private Date resubmissionDate;

	private String sealFlag;

	private String storage;

	private String transCommentText;

	private BigDecimal volumeSeq;

	private Long offenderId;

	private long offenderFileSeq;
	
	public OffenderCommunityFile() {
		// OffenderCommunityFile
	}

	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}

	public long getOffenderFileSeq() {
		return offenderFileSeq;
	}

	public void setOffenderFileSeq(final long offenderFileSeq) {
		this.offenderFileSeq = offenderFileSeq;
	}

	

	public String getCaseloadType() {
		return this.caseloadType;
	}

	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public String getCloseFileNo() {
		return this.closeFileNo;
	}

	public void setCloseFileNo(final String closeFileNo) {
		this.closeFileNo = closeFileNo;
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

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationUser() {
		return this.creationUser;
	}

	public void setCreationUser(final String creationUser) {
		this.creationUser = creationUser;
	}

	public Date getFileCreateDate() {
		return this.fileCreateDate;
	}

	public void setFileCreateDate(final Date fileCreateDate) {
		this.fileCreateDate = fileCreateDate;
	}

	public String getFileReference() {
		return this.fileReference;
	}

	public void setFileReference(final String fileReference) {
		this.fileReference = fileReference;
	}

	public String getFileSubType() {
		return this.fileSubType;
	}

	public void setFileSubType(final String fileSubType) {
		this.fileSubType = fileSubType;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(final String fileType) {
		this.fileType = fileType;
	}

	public String getHoldingAgyLocId() {
		return this.holdingAgyLocId;
	}

	public void setHoldingAgyLocId(final String holdingAgyLocId) {
		this.holdingAgyLocId = holdingAgyLocId;
	}

	public BigDecimal getHoldingStaffId() {
		return this.holdingStaffId;
	}

	public void setHoldingStaffId(final BigDecimal holdingStaffId) {
		this.holdingStaffId = holdingStaffId;
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

	public String getNonOfficerStatus() {
		return this.nonOfficerStatus;
	}

	public void setNonOfficerStatus(final String nonOfficerStatus) {
		this.nonOfficerStatus = nonOfficerStatus;
	}

	public BigDecimal getOffenderFileNum() {
		return this.offenderFileNum;
	}

	public void setOffenderFileNum(final BigDecimal offenderFileNum) {
		this.offenderFileNum = offenderFileNum;
	}

	public Date getResubmissionDate() {
		return this.resubmissionDate;
	}

	public void setResubmissionDate(final Date resubmissionDate) {
		this.resubmissionDate = resubmissionDate;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getStorage() {
		return this.storage;
	}

	public void setStorage(final String storage) {
		this.storage = storage;
	}

	public String getTransCommentText() {
		return this.transCommentText;
	}

	public void setTransCommentText(final String transCommentText) {
		this.transCommentText = transCommentText;
	}

	public BigDecimal getVolumeSeq() {
		return this.volumeSeq;
	}

	public void setVolumeSeq(final BigDecimal volumeSeq) {
		this.volumeSeq = volumeSeq;
	}

}
