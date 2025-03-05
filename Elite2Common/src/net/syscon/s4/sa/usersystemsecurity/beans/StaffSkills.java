package net.syscon.s4.sa.usersystemsecurity.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.StaffMembers;

/**
 * The persistent class for the STAFF_SKILLS database table.
 * 
 */
public class StaffSkills extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("rowId")
	private String rowId;
	@JsonProperty("activeFlag")
	private String activeFlag;
	
	@JsonProperty("asOfDate")
	private Date asOfDate;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("programId")
	private BigDecimal programId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("staffSkillId")
	private BigDecimal staffSkillId;
	@JsonProperty("stskComment")
	private String stskComment;

	// bi-directional many-to-one association to StaffMember
	private StaffMembers staffMember;
	@JsonProperty("staffId")
	private long staffId;
	@JsonProperty("skillType")
	private String skillType;
	@JsonProperty("subType")
	private String subType;
	@JsonProperty("progId")
	private String progId;
	/**
	 * @return the asOfDate
	 */
	public Date getAsOfDate() {
		return asOfDate;
	}
	/**
	 * @param asOfDate
	 *            the asOfDate to set
	 */
	public void setAsOfDate(final Date asOfDate) {
		this.asOfDate = asOfDate;
	}
	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}
	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
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
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public StaffSkills() {
	}
	/**
	 * @return the staffId
	 */
	public long getStaffId() {
		return staffId;
	}
	/**
	 * @param skillType
	 *            the staffId to set
	 */
	public void setStaffId(final long staffId) {
		this.staffId = staffId;
	}
	/**
	 * @return the skillType
	 */
	public String getSkillType() {
		return skillType;
	}
	/**
	 * @param skillType
	 *            the skillType to set
	 */
	public void setSkillType(final String skillType) {
		this.skillType = skillType;
	}
	
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	/**
	 * @return the subType
	 */
	public String getSubType() {
		return subType;
	}
	/**
	 * @param subType
	 *            the subType to set
	 */
	public void setSubType(final String subType) {
		this.subType = subType;
	}
	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return this.activeFlag;
	}
	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}
	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}
	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}
	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	/**
	 * @return the programId
	 */
	public BigDecimal getProgramId() {
		return this.programId;
	}
	/**
	 * @param programId
	 *            the programId to set
	 */
	public void setProgramId(final BigDecimal programId) {
		this.programId = programId;
	}
	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}
	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
	/**
	 * @return the staffSkillId
	 */
	public BigDecimal getStaffSkillId() {
		return this.staffSkillId;
	}
	/**
	 * @param staffSkillId
	 *            the staffSkillId to set
	 */
	public void setStaffSkillId(final BigDecimal staffSkillId) {
		this.staffSkillId = staffSkillId;
	}
	/**
	 * @return the stskComment
	 */
	public String getStskComment() {
		return this.stskComment;
	}
	/**
	 * @param stskComment
	 *            the stskComment to set
	 */
	public void setStskComment(final String stskComment) {
		this.stskComment = stskComment;
	}
	/**
	 * @return the staffMember
	 */
	public StaffMembers getStaffMember() {
		return this.staffMember;
	}
	/**
	 * @param staffMember
	 *            the staffMember to set
	 */
	public void setStaffMember(final StaffMembers staffMember) {
		this.staffMember = staffMember;
	}
	public String getProgId() {
		return progId;
	}
	public void setProgId(String progId) {
		this.progId = progId;
	}

}
