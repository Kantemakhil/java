package net.syscon.s4.cm.primaryofficeragentassignment.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ExtOwnershipTransfer extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("extTransferId")
	private Long extTransferId;

	@JsonProperty("agyLocIdFrom")
	private String agyLocIdFrom;

	@JsonProperty("agyIocIdTo")
	private String agyIocIdTo;

	@JsonProperty("transferDate")
	private Date transferDate;

	@JsonProperty("assStaffId")
	private Long assStaffId;

	@JsonProperty("transferFlag")
	private String transferFlag;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("ptrId")
	private Long ptrId;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sacStaffId")
	private Long sacStaffId;

	@JsonProperty("offenderLastName")
	private String offenderLastName;

	@JsonProperty("offenderFirstName")
	private String offenderFirstName;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("staffLastName")
	private String staffLastName;

	@JsonProperty("staffFirstName")
	private String staffFirstName;
	
	
	@JsonProperty("offenderId")
	private Long offenderId;
	
	@JsonProperty("nonOfficerStatus")
	private String nonOfficerStatus;
	
	@JsonProperty("offenderFileSeq")
	private Long offenderFileSeq;
	
	
	
	
	
	
	

	@JsonProperty("dspLastName")
	private String dspLastName;
	
	@JsonProperty("dspFirstName")
	private String dspFirstName;
	
	@JsonProperty("pOffIdDisp")
	private String pOffIdDisp;
	
	@JsonProperty("dspFirstNameTwo")
	private String dspFirstNameTwo;
	
	@JsonProperty("dspLastNameTwo")
	private String dspLastNameTwo;
	
	@JsonProperty("caseLoadId")
	private String caseLoadId;
	
	@JsonProperty("eventSeq")
	private Long eventSeq;
	
	@JsonProperty("agyLocId")
	private Long agyLocId;
	
	@JsonProperty("chkSelect")
	private String chkSelect;
	
	@JsonProperty("staffId")
	private Long staffId;
	
	@JsonProperty("position")
	private String position;
	
	@JsonProperty("role")
	private String role;
	
	@JsonProperty("nbtTeamId")
	private Integer nbtTeamId;
	
	@JsonProperty("pOffenderBookId")
	private Long pOffenderBookId;
	
	@JsonProperty("agyLocIdTo")
	private String agyLocIdTo;
	
	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("workedStaffMembers")
	private List<Integer> workedStaffMembers;
	

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the extTransferId
	 */
	public Long getExtTransferId() {
		return extTransferId;
	}

	/**
	 * @param extTransferId the extTransferId to set
	 */
	public void setExtTransferId(final Long extTransferId) {
		this.extTransferId = extTransferId;
	}

	/**
	 * @return the agyLocIdFrom
	 */
	public String getAgyLocIdFrom() {
		return agyLocIdFrom;
	}

	/**
	 * @param agyLocIdFrom the agyLocIdFrom to set
	 */
	public void setAgyLocIdFrom(final String agyLocIdFrom) {
		this.agyLocIdFrom = agyLocIdFrom;
	}

	/**
	 * @return the agyIocIdTo
	 */
	public String getAgyIocIdTo() {
		return agyIocIdTo;
	}

	/**
	 * @param agyIocIdTo the agyIocIdTo to set
	 */
	public void setAgyIocIdTo(final String agyIocIdTo) {
		this.agyIocIdTo = agyIocIdTo;
	}

	/**
	 * @return the transferDate
	 */
	public Date getTransferDate() {
		return transferDate;
	}

	/**
	 * @param transferDate the transferDate to set
	 */
	public void setTransferDate(final Date transferDate) {
		this.transferDate = transferDate;
	}

	/**
	 * @return the assStaffId
	 */
	public Long getAssStaffId() {
		return assStaffId;
	}

	/**
	 * @param assStaffId the assStaffId to set
	 */
	public void setAssStaffId(final Long assStaffId) {
		this.assStaffId = assStaffId;
	}

	/**
	 * @return the transferFlag
	 */
	public String getTransferFlag() {
		return transferFlag;
	}

	/**
	 * @param transferFlag the transferFlag to set
	 */
	public void setTransferFlag(final String transferFlag) {
		this.transferFlag = transferFlag;
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
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the ptrId
	 */
	public Long getPtrId() {
		return ptrId;
	}

	/**
	 * @param ptrId the ptrId to set
	 */
	public void setPtrId(final Long ptrId) {
		this.ptrId = ptrId;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the sacStaffId
	 */
	public Long getSacStaffId() {
		return sacStaffId;
	}

	/**
	 * @param sacStaffId the sacStaffId to set
	 */
	public void setSacStaffId(final Long sacStaffId) {
		this.sacStaffId = sacStaffId;
	}

	/**
	 * @return the offenderLastName
	 */
	public String getOffenderLastName() {
		return offenderLastName;
	}

	/**
	 * @param offenderLastName the offenderLastName to set
	 */
	public void setOffenderLastName(final String offenderLastName) {
		this.offenderLastName = offenderLastName;
	}

	/**
	 * @return the offenderFirstName
	 */
	public String getOffenderFirstName() {
		return offenderFirstName;
	}

	/**
	 * @param offenderFirstName the offenderFirstName to set
	 */
	public void setOffenderFirstName(final String offenderFirstName) {
		this.offenderFirstName = offenderFirstName;
	}

	/**
	 * @return the offenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 * @param offenderIdDisplay the offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * @return the staffLastName
	 */
	public String getStaffLastName() {
		return staffLastName;
	}

	/**
	 * @param staffLastName the staffLastName to set
	 */
	public void setStaffLastName(final String staffLastName) {
		this.staffLastName = staffLastName;
	}

	/**
	 * @return the staffFirstName
	 */
	public String getStaffFirstName() {
		return staffFirstName;
	}

	/**
	 * @param staffFirstName the staffFirstName to set
	 */
	public void setStaffFirstName(final String staffFirstName) {
		this.staffFirstName = staffFirstName;
	}

	/**
	 * @return the offenderId
	 */
	public Long getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId the offenderId to set
	 */
	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the nonOfficerStatus
	 */
	public String getNonOfficerStatus() {
		return nonOfficerStatus;
	}

	/**
	 * @param nonOfficerStatus the nonOfficerStatus to set
	 */
	public void setNonOfficerStatus(final String nonOfficerStatus) {
		this.nonOfficerStatus = nonOfficerStatus;
	}

	/**
	 * @return the offenderFileSeq
	 */
	public Long getOffenderFileSeq() {
		return offenderFileSeq;
	}

	/**
	 * @param offenderFileSeq the offenderFileSeq to set
	 */
	public void setOffenderFileSeq(final Long offenderFileSeq) {
		this.offenderFileSeq = offenderFileSeq;
	}

	/**
	 * @return the dspLastName
	 */
	public String getDspLastName() {
		return dspLastName;
	}

	/**
	 * @param dspLastName the dspLastName to set
	 */
	public void setDspLastName(final String dspLastName) {
		this.dspLastName = dspLastName;
	}

	/**
	 * @return the dspFirstName
	 */
	public String getDspFirstName() {
		return dspFirstName;
	}

	/**
	 * @param dspFirstName the dspFirstName to set
	 */
	public void setDspFirstName(final String dspFirstName) {
		this.dspFirstName = dspFirstName;
	}

	/**
	 * @return the pOffIdDisp
	 */
	public String getpOffIdDisp() {
		return pOffIdDisp;
	}

	/**
	 * @param pOffIdDisp the pOffIdDisp to set
	 */
	public void setpOffIdDisp(final String pOffIdDisp) {
		this.pOffIdDisp = pOffIdDisp;
	}

	/**
	 * @return the dspFirstNameTwo
	 */
	public String getDspFirstNameTwo() {
		return dspFirstNameTwo;
	}

	/**
	 * @param dspFirstNameTwo the dspFirstNameTwo to set
	 */
	public void setDspFirstNameTwo(final String dspFirstNameTwo) {
		this.dspFirstNameTwo = dspFirstNameTwo;
	}

	/**
	 * @return the dspLastNameTwo
	 */
	public String getDspLastNameTwo() {
		return dspLastNameTwo;
	}

	/**
	 * @param dspLastNameTwo the dspLastNameTwo to set
	 */
	public void setDspLastNameTwo(final String dspLastNameTwo) {
		this.dspLastNameTwo = dspLastNameTwo;
	}

	/**
	 * @return the caseLoadId
	 */
	public String getCaseLoadId() {
		return caseLoadId;
	}

	/**
	 * @param caseLoadId the caseLoadId to set
	 */
	public void setCaseLoadId(final String caseLoadId) {
		this.caseLoadId = caseLoadId;
	}

	/**
	 * @return the eventSeq
	 */
	public Long getEventSeq() {
		return eventSeq;
	}

	/**
	 * @param eventSeq the eventSeq to set
	 */
	public void setEventSeq(final Long eventSeq) {
		this.eventSeq = eventSeq;
	}

	/**
	 * @return the agyLocId
	 */
	public Long getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId the agyLocId to set
	 */
	public void setAgyLocId(final Long agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the chkSelect
	 */
	public String getChkSelect() {
		return chkSelect;
	}

	/**
	 * @param chkSelect the chkSelect to set
	 */
	public void setChkSelect(final String chkSelect) {
		this.chkSelect = chkSelect;
	}

	/**
	 * @return the staffId
	 */
	public Long getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(final Long staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(final String position) {
		this.position = position;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(final String role) {
		this.role = role;
	}

	/**
	 * @return the nbtTeamId
	 */
	public Integer getNbtTeamId() {
		return nbtTeamId;
	}

	/**
	 * @param nbtTeamId the nbtTeamId to set
	 */
	public void setNbtTeamId(final Integer nbtTeamId) {
		this.nbtTeamId = nbtTeamId;
	}

	/**
	 * @return the pOffenderBookId
	 */
	public Long getpOffenderBookId() {
		return pOffenderBookId;
	}

	/**
	 * @param pOffenderBookId the pOffenderBookId to set
	 */
	public void setpOffenderBookId(final Long pOffenderBookId) {
		this.pOffenderBookId = pOffenderBookId;
	}

	/**
	 * @return the agyLocIdTo
	 */
	public String getAgyLocIdTo() {
		return agyLocIdTo;
	}

	/**
	 * @param agyLocIdTo the agyLocIdTo to set
	 */
	public void setAgyLocIdTo(final String agyLocIdTo) {
		this.agyLocIdTo = agyLocIdTo;
	}
	/**
	 * @return the rootOffenderId
	 */
	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 * @param rootOffenderId the rootOffenderId to set
	 */
	public void setRootOffenderId(final BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public List<Integer> getWorkedStaffMembers() {
		return workedStaffMembers;
	}

	public void setWorkedStaffMembers(List<Integer> workedStaffMembers) {
		this.workedStaffMembers = workedStaffMembers;
	}


}
