package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderAllocationsSentences extends BaseModel implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("caseLoadId")
	private String caseLoadId;
	
	@JsonProperty("matter")
	private String matter;
	
	@JsonProperty("commenceType")
	private String commenceType;
	
	@JsonProperty("formInfoJson")
	private String formInfoJson;
	
	@JsonProperty("no")
	private Integer no;
	
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	
	@JsonProperty("teamCode")
	private String teamCode;

	@JsonProperty("staffId")
	private BigDecimal staffId;

	@JsonProperty("teamId")
	private BigDecimal teamId;

	@JsonProperty("teamMemberId")
	private BigDecimal teamMemberId;

	@JsonProperty("agyLocId")
	private String agyLocId;
	
	
	@JsonProperty("conTransferId")
	private BigDecimal conTransferId;
	
	@JsonProperty("offenderSentConditionId")
	private BigDecimal offenderSentConditionId;
	
	@JsonProperty("formInfoJsonBlob")
	private byte[] formInfoJsonBlob;
	
	@JsonProperty("intakeAgyLocId")
	private String intakeAgyLocId;
	
	@JsonProperty("moduleName")
	private String moduleName;
	
	@JsonProperty("condtionExists")
	private Boolean condtionExists;
	
	@JsonProperty("prvsAllocOfficer")
	private BigDecimal prvsAllocOfficer;
	
	@JsonProperty("prvsAllocToOfficer")
	private BigDecimal prvsAllocToOfficer;
	
	@JsonProperty("prvsAllocAgyLocId")
	private String prvsAllocAgyLocId;
	
	@JsonProperty("prvsAllocToAgyLocId")
	private String prvsAllocToAgyLocId;
	
	@JsonProperty("prvsAllocTeamId")
	private BigDecimal prvsAllocTeamId;
	
	@JsonProperty("prvsAllocToTeamId")
	private BigDecimal prvsAllocToTeamId;
	
	@JsonProperty("prvsAllocTeamMemberId")
	private BigDecimal prvsAllocTeamMemberId;
	
	@JsonProperty("prvsAllocToTeamMemberId")
	private BigDecimal prvsAllocToTeamMemberId;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("toTeamId")
	private BigDecimal toTeamId;
	
	@JsonProperty("conditionsExistsInBoth")
	private boolean conditionsExistsInBoth = false;
	
	
	@JsonProperty("toAgyLocId")
	private String toAgyLocId;
	
	@JsonProperty("sentenceSeq")
	private Integer sentenceSeq;
	
	@JsonProperty("condiStatus")
	private String condiStatus;
	
	@JsonProperty("toStaffId")
	private BigDecimal toStaffId;
	
	@JsonProperty("toTeamMemberId")
	private BigDecimal toTeamMemberId;
	
	@JsonProperty("officersName")
	private String officersName;
	
	@JsonProperty("orderType")
	private String orderType;
	
	@JsonProperty("rcvdFromLoc")
	private String rcvdFromLoc;
	
	@JsonProperty("rcvdFromTeam")
	private BigDecimal rcvdFromTeam;
	
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

	public String getCaseLoadId() {
		return caseLoadId;
	}

	public void setCaseLoadId(String caseLoadId) {
		this.caseLoadId = caseLoadId;
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

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
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

	public BigDecimal getConTransferId() {
		return conTransferId;
	}

	public void setConTransferId(BigDecimal conTransferId) {
		this.conTransferId = conTransferId;
	}

	public BigDecimal getOffenderSentConditionId() {
		return offenderSentConditionId;
	}

	public void setOffenderSentConditionId(BigDecimal offenderSentConditionId) {
		this.offenderSentConditionId = offenderSentConditionId;
	}

	public byte[] getFormInfoJsonBlob() {
		return formInfoJsonBlob;
	}

	public void setFormInfoJsonBlob(byte[] formInfoJsonBlob) {
		this.formInfoJsonBlob = formInfoJsonBlob;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getIntakeAgyLocId() {
		return intakeAgyLocId;
	}

	public void setIntakeAgyLocId(String intakeAgyLocId) {
		this.intakeAgyLocId = intakeAgyLocId;
	}

	public Boolean getCondtionExists() {
		return condtionExists;
	}

	public void setCondtionExists(Boolean condtionExists) {
		this.condtionExists = condtionExists;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public BigDecimal getPrvsAllocOfficer() {
		return prvsAllocOfficer;
	}

	public void setPrvsAllocOfficer(BigDecimal prvsAllocOfficer) {
		this.prvsAllocOfficer = prvsAllocOfficer;
	}

	public BigDecimal getPrvsAllocToOfficer() {
		return prvsAllocToOfficer;
	}

	public void setPrvsAllocToOfficer(BigDecimal prvsAllocToOfficer) {
		this.prvsAllocToOfficer = prvsAllocToOfficer;
	}

	public String getPrvsAllocAgyLocId() {
		return prvsAllocAgyLocId;
	}

	public void setPrvsAllocAgyLocId(String prvsAllocAgyLocId) {
		this.prvsAllocAgyLocId = prvsAllocAgyLocId;
	}

	public String getPrvsAllocToAgyLocId() {
		return prvsAllocToAgyLocId;
	}

	public void setPrvsAllocToAgyLocId(String prvsAllocToAgyLocId) {
		this.prvsAllocToAgyLocId = prvsAllocToAgyLocId;
	}

	public BigDecimal getPrvsAllocTeamId() {
		return prvsAllocTeamId;
	}

	public void setPrvsAllocTeamId(BigDecimal prvsAllocTeamId) {
		this.prvsAllocTeamId = prvsAllocTeamId;
	}

	public BigDecimal getPrvsAllocToTeamId() {
		return prvsAllocToTeamId;
	}

	public void setPrvsAllocToTeamId(BigDecimal prvsAllocToTeamId) {
		this.prvsAllocToTeamId = prvsAllocToTeamId;
	}

	public BigDecimal getPrvsAllocTeamMemberId() {
		return prvsAllocTeamMemberId;
	}

	public void setPrvsAllocTeamMemberId(BigDecimal prvsAllocTeamMemberId) {
		this.prvsAllocTeamMemberId = prvsAllocTeamMemberId;
	}

	public BigDecimal getPrvsAllocToTeamMemberId() {
		return prvsAllocToTeamMemberId;
	}

	public void setPrvsAllocToTeamMemberId(BigDecimal prvsAllocToTeamMemberId) {
		this.prvsAllocToTeamMemberId = prvsAllocToTeamMemberId;
	}

	public boolean isConditionsExistsInBoth() {
		return conditionsExistsInBoth;
	}

	public void setConditionsExistsInBoth(boolean conditionsExistsInBoth) {
		this.conditionsExistsInBoth = conditionsExistsInBoth;
	}

	public Integer getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(Integer sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public String getCondiStatus() {
		return condiStatus;
	}

	public void setCondiStatus(String condiStatus) {
		this.condiStatus = condiStatus;
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

	public String getOfficersName() {
		return officersName;
	}

	public void setOfficersName(String officersName) {
		this.officersName = officersName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
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
