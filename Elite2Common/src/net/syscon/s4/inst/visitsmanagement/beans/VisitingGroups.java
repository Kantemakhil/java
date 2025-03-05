package net.syscon.s4.inst.visitsmanagement.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class VisitingGroups
 */
public class VisitingGroups {

	@JsonProperty("groupId")
	private Integer groupId;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("visitDate")
	private Date visitDate;
	@JsonProperty("startTime")
	private Date startTime;
	@JsonProperty("endTime")
	private Date endTime;
	@JsonProperty("groupName")
	private String groupName;
	@JsonProperty("noVisitors")
	private Integer noVisitors;
	@JsonProperty("groupPurpose")
	private String groupPurpose;
	@JsonProperty("approvedById")
	private Integer approvedById;
	@JsonProperty("escortedById")
	private Integer escortedById;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	private boolean inserted;
	@JsonProperty("staffIdTemp")
	private String staffIdTemp;
	@JsonProperty("approvedbyIdTemp")
	private String approvedbyIdTemp;
	@JsonProperty("escortedByIdTemp") 
	private String escortedByIdTemp;
	@JsonProperty("groupPurposeTemp")
	private String groupPurposeTemp;

	
	public  VisitingGroups (){
		
		// VisitingGroups
	}
	/**
	 * @return the groupPurposeTemp
	 */
	public String getGroupPurposeTemp() {
		return groupPurposeTemp;
	}
	/**
	 * @param groupPurposeTemp the groupPurposeTemp to set
	 */
	public void setGroupPurposeTemp(String groupPurposeTemp) {
		this.groupPurposeTemp = groupPurposeTemp;
	}
	/**
	 * @return the approvedbyIdTemp
	 */
	public String getApprovedbyIdTemp() {
		return approvedbyIdTemp;
	}
	/**
	 * @param approvedbyIdTemp the approvedbyIdTemp to set
	 */
	public void setApprovedbyIdTemp(String approvedbyIdTemp) {
		this.approvedbyIdTemp = approvedbyIdTemp;
	}
	/**
	 * @return the escortedByIdTemp
	 */
	public String getEscortedByIdTemp() {
		return escortedByIdTemp;
	}
	/**
	 * @param escortedByIdTemp the escortedByIdTemp to set
	 */
	public void setEscortedByIdTemp(String escortedByIdTemp) {
		this.escortedByIdTemp = escortedByIdTemp;
	}
	/**
	 * @return the staffIdTemp
	 */
	public String getStaffIdTemp() {
		return staffIdTemp;
	}
	/**
	 * @param staffIdTemp the staffIdTemp to set
	 */
	public void setStaffIdTemp(final String staffIdTemp) {
		this.staffIdTemp = staffIdTemp;
	}
	/**
	 * @param groupId
	 *            groupId to set
	 */
	public void setGroupId(final Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * return thegroupId
	 */
	public Integer getGroupId() {
		return this.groupId;
	}

	/**
	 * @param agyLocId
	 *            agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * return theagyLocId
	 */
	public String getAgyLocId() {
		return this.agyLocId;
	}

	/**
	 * @param visitDate
	 *            visitDate to set
	 */
	public void setVisitDate(final Date visitDate) {
		this.visitDate = visitDate;
	}

	/**
	 * return thevisitDate
	 */
	public Date getVisitDate() {
		return this.visitDate;
	}

	/**
	 * @param startTime
	 *            startTime to set
	 */
	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * return thestartTime
	 */
	public Date getStartTime() {
		return this.startTime;
	}

	/**
	 * @param endTime
	 *            endTime to set
	 */
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * return theendTime
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * @param groupName
	 *            groupName to set
	 */
	public void setGroupName(final String groupName) {
		this.groupName = groupName;
	}

	/**
	 * return thegroupName
	 */
	public String getGroupName() {
		return this.groupName;
	}

	/**
	 * @param noVisitors
	 *            noVisitors to set
	 */
	public void setNoVisitors(final Integer noVisitors) {
		this.noVisitors = noVisitors;
	}

	/**
	 * return thenoVisitors
	 */
	public Integer getNoVisitors() {
		return this.noVisitors;
	}

	/**
	 * @param groupPurpose
	 *            groupPurpose to set
	 */
	public void setGroupPurpose(final String groupPurpose) {
		this.groupPurpose = groupPurpose;
	}

	/**
	 * return thegroupPurpose
	 */
	public String getGroupPurpose() {
		return this.groupPurpose;
	}

	/**
	 * @param approvedById
	 *            approvedById to set
	 */
	public void setApprovedById(final Integer approvedById) {
		this.approvedById = approvedById;
	}

	/**
	 * return theapprovedById
	 */
	public Integer getApprovedById() {
		return this.approvedById;
	}

	/**
	 * @param escortedById
	 *            escortedById to set
	 */
	public void setEscortedById(final Integer escortedById) {
		this.escortedById = escortedById;
	}

	/**
	 * return theescortedById
	 */
	public Integer getEscortedById() {
		return this.escortedById;
	}

	/**
	 * @param commentText
	 *            commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * return thecommentText
	 */
	public String getCommentText() {
		return this.commentText;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return themodifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @param modifyDatetime
	 *            modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * return themodifyDatetime
	 */
	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	/**
	 * @param createDatetime
	 *            createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * return thecreateDatetime
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * @param createUserId
	 *            createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return thecreateUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param sealFlag
	 *            sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return thesealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

}