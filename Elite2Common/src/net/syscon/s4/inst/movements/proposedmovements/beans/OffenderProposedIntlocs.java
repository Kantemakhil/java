package net.syscon.s4.inst.movements.proposedmovements.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderProposedIntlocs extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	
	@JsonProperty("locationSeq")
	private Long locationSeq;
	
	@JsonProperty("fromLivingUnitId")
	private Long fromLivingUnitId;
	
	@JsonProperty("toLivingUnitId")
	private Long toLivingUnitId;
	
	@JsonProperty("movementType")
	private String movementType;
	
	@JsonProperty("movementReason")
	private String movementReason;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("modifiedDatetime")
	private Date modifiedDatetime;
	
	@JsonProperty("modifiyUserId")
	private String modifiyUserId;
	
	
	@JsonProperty("initiatedDate")
	private Date initiatedDate;
	
	@JsonProperty("approvalDate")
	private Date approvalDate;
	
	@JsonProperty("levelOneId")
	private Long levelOneId;
	
	@JsonProperty("levelTwoId")
	private Long levelTwoId;
	
	@JsonProperty("levelThreeId")
	private Long levelThreeId;
	
	@JsonProperty("levelFourId")
	private Long levelFourId;
	
	
	@JsonProperty("livingUnitId")
	private BigDecimal livingUnitId;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("vCount")
	private Long vCount;
	
	@JsonProperty("lvReturnCheckSecurity")
	private String lvReturnCheckSecurity;
	
	@JsonProperty("lvReturnCheckNonAsso")
	private String lvReturnCheckNonAsso;
	
	@JsonProperty("offenderId")
	private Long offenderId;
	
	
	@JsonProperty("fromLivUnitidDesc")
	private String fromLivUnitidDesc;
	
	@JsonProperty("toLivUnitidDesc")
	private String toLivUnitidDesc;
	
	@JsonProperty("commentRole")
	private String commentRole;
	

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Long getLocationSeq() {
		return locationSeq;
	}

	public void setLocationSeq(Long locationSeq) {
		this.locationSeq = locationSeq;
	}

	public Long getFromLivingUnitId() {
		return fromLivingUnitId;
	}

	public void setFromLivingUnitId(Long fromLivingUnitId) {
		this.fromLivingUnitId = fromLivingUnitId;
	}

	public Long getToLivingUnitId() {
		return toLivingUnitId;
	}

	public void setToLivingUnitId(Long toLivingUnitId) {
		this.toLivingUnitId = toLivingUnitId;
	}

	public String getMovementType() {
		return movementType;
	}

	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	public String getMovementReason() {
		return movementReason;
	}

	public void setMovementReason(String movementReason) {
		this.movementReason = movementReason;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Date getModifiedDatetime() {
		return modifiedDatetime;
	}

	public void setModifiedDatetime(Date modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	public String getModifiyUserId() {
		return modifiyUserId;
	}

	public void setModifiyUserId(String modifiyUserId) {
		this.modifiyUserId = modifiyUserId;
	}


	public Date getInitiatedDate() {
		return initiatedDate;
	}

	public void setInitiatedDate(Date initiatedDate) {
		this.initiatedDate = initiatedDate;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

//	}

	public BigDecimal getLivingUnitId() {
		return livingUnitId;
	}

	public void setLivingUnitId(BigDecimal livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}
	public Long getvCount() {
		return vCount;
	}

	public void setvCount(Long vCount) {
		this.vCount = vCount;
	}

	public String getLvReturnCheckSecurity() {
		return lvReturnCheckSecurity;
	}

	public void setLvReturnCheckSecurity(String lvReturnCheckSecurity) {
		this.lvReturnCheckSecurity = lvReturnCheckSecurity;
	}

	public String getLvReturnCheckNonAsso() {
		return lvReturnCheckNonAsso;
	}

	public void setLvReturnCheckNonAsso(String lvReturnCheckNonAsso) {
		this.lvReturnCheckNonAsso = lvReturnCheckNonAsso;
	}

	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Long offenderId) {
		this.offenderId = offenderId;
	}

	public Long getLevelOneId() {
		return levelOneId;
	}

	public void setLevelOneId(Long levelOneId) {
		this.levelOneId = levelOneId;
	}

	public Long getLevelTwoId() {
		return levelTwoId;
	}

	public void setLevelTwoId(Long levelTwoId) {
		this.levelTwoId = levelTwoId;
	}

	public Long getLevelThreeId() {
		return levelThreeId;
	}

	public void setLevelThreeId(Long levelThreeId) {
		this.levelThreeId = levelThreeId;
	}

	public Long getLevelFourId() {
		return levelFourId;
	}

	public void setLevelFourId(Long levelFourId) {
		this.levelFourId = levelFourId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFromLivUnitidDesc() {
		return fromLivUnitidDesc;
	}

	public void setFromLivUnitidDesc(String fromLivUnitidDesc) {
		this.fromLivUnitidDesc = fromLivUnitidDesc;
	}

	public String getToLivUnitidDesc() {
		return toLivUnitidDesc;
	}

	public void setToLivUnitidDesc(String toLivUnitidDesc) {
		this.toLivUnitidDesc = toLivUnitidDesc;
	}

	public String getCommentRole() {
		return commentRole;
	}

	public void setCommentRole(String commentRole) {
		this.commentRole = commentRole;
	}
	
}
