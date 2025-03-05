package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderCondTransfer extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("conTransferId")
	private BigDecimal conTransferId;
	
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;

	@JsonProperty("offenderSentConditionId")
	private BigDecimal offenderSentConditionId;

	@JsonProperty("staffId")
	private BigDecimal staffId;

	@JsonProperty("teamId")
	private BigDecimal teamId;

	@JsonProperty("teamMemberId")
	private BigDecimal teamMemberId;

	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("toTeamId")
	private BigDecimal toTeamId;

	@JsonProperty("toAgyLocId")
	private String toAgyLocId;

	@JsonProperty("condiStatus")
	private String condiStatus;

	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("createDateTime")
	private Date createDateTime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
	
	@JsonProperty("toStaffId")
	private BigDecimal toStaffId;
	
	@JsonProperty("toTeamMemberId")
	private BigDecimal toTeamMemberId;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("matter")
	private String matter;
	
	@JsonProperty("commenceType")
	private String commenceType;
	
	@JsonProperty("formInfoJson")
	private String formInfoJson;
	
	@JsonProperty("no")
	private Integer no;
	
	@JsonProperty("sentenceSeq")
	private Integer sentenceSeq;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("longCommentText")
	private String longCommentText;
	
	@JsonProperty("parentCondTransferId")
	private BigDecimal parentCondTransferId;
	
	@JsonProperty("receivedFrom")
	private String receivedFrom;
	
	@JsonProperty("formInfoJsonBlob")
	private byte[] formInfoJsonBlob;
	
	@JsonProperty("officersName")
	private String officersName;
	
	@JsonProperty("moduleId")
	private String moduleId;
	
	@JsonProperty("rcvdFromLoc")
	private String rcvdFromLoc;
	
	@JsonProperty("rcvdFromTeam")
	private BigDecimal rcvdFromTeam;
 
	public BigDecimal getConTransferId() {
		return conTransferId;
	}

	public void setConTransferId(BigDecimal conTransferId) {
		this.conTransferId = conTransferId;
	}

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getOffenderSentConditionId() {
		return offenderSentConditionId;
	}

	public void setOffenderSentConditionId(BigDecimal offenderSentConditionId) {
		this.offenderSentConditionId = offenderSentConditionId;
	}

	public BigDecimal getStaffId() {
		return staffId;
	}

	public void setStaffId(BigDecimal staffId) {
		this.staffId = staffId;
	}

	public BigDecimal getTeamId() {
		return teamId;
	}

	public void setTeamId(BigDecimal teamId) {
		this.teamId = teamId;
	}

	public BigDecimal getTeamMemberId() {
		return teamMemberId;
	}

	public void setTeamMemberId(BigDecimal teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getCondiStatus() {
		return condiStatus;
	}

	public void setCondiStatus(String condiStatus) {
		this.condiStatus = condiStatus;
	}

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

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getListSeq() {
		return listSeq;
	}

	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public BigDecimal getToTeamId() {
		return toTeamId;
	}

	public void setToTeamId(BigDecimal toTeamId) {
		this.toTeamId = toTeamId;
	}

	public String getToAgyLocId() {
		return toAgyLocId;
	}

	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	public BigDecimal getToStaffId() {
		return toStaffId;
	}

	public void setToStaffId(BigDecimal toStaffId) {
		this.toStaffId = toStaffId;
	}

	public BigDecimal getToTeamMemberId() {
		return toTeamMemberId;
	}

	public void setToTeamMemberId(BigDecimal toTeamMemberId) {
		this.toTeamMemberId = toTeamMemberId;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	public String getCommenceType() {
		return commenceType;
	}

	public void setCommenceType(String commenceType) {
		this.commenceType = commenceType;
	}

	public String getFormInfoJson() {
		return formInfoJson;
	}

	public void setFormInfoJson(String formInfoJson) {
		this.formInfoJson = formInfoJson;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public Integer getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(Integer sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLongCommentText() {
		return longCommentText;
	}

	public void setLongCommentText(String longCommentText) {
		this.longCommentText = longCommentText;
	}

	public BigDecimal getParentCondTransferId() {
		return parentCondTransferId;
	}

	public void setParentCondTransferId(BigDecimal parentCondTransferId) {
		this.parentCondTransferId = parentCondTransferId;
	}

	public String getReceivedFrom() {
		return receivedFrom;
	}

	public void setReceivedFrom(String receivedFrom) {
		this.receivedFrom = receivedFrom;
	}

	public byte[] getFormInfoJsonBlob() {
		return formInfoJsonBlob;
	}

	public void setFormInfoJsonBlob(byte[] formInfoJsonBlob) {
		this.formInfoJsonBlob = formInfoJsonBlob;
	}

	public String getOfficersName() {
		return officersName;
	}

	public void setOfficersName(String officersName) {
		this.officersName = officersName;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getRcvdFromLoc() {
		return rcvdFromLoc;
	}

	public void setRcvdFromLoc(String rcvdFromLoc) {
		this.rcvdFromLoc = rcvdFromLoc;
	}

	public BigDecimal getRcvdFromTeam() {
		return rcvdFromTeam;
	}

	public void setRcvdFromTeam(BigDecimal rcvdFromTeam) {
		this.rcvdFromTeam = rcvdFromTeam;
	}
	
	
}
