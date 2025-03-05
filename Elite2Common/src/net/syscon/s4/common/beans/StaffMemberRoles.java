package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class StaffMemberRoles extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("staffId")
	@NotNull
	private Integer staffId;

	@JsonProperty("roleId")
	@NotNull
	private Integer roleId;

	@JsonProperty("createDateTime")
	@NotNull
	private Timestamp createDateTime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("modifyDateTime")
	private Timestamp modifyDateTime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("roleCode")
	@NotNull
	@Size(max = 30)
	private String roleCode;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("description")
	private String description;
	
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("middleName")
	private String middleName;
	
	@JsonProperty("rowId")
	private String rowId;
	
	@JsonProperty("liReturn")
	private int liReturn;
	
	@JsonProperty("totalRecords")
	private int totalRecords;
	
	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("agyLocID")
	private String agyLocID;
	
	@JsonProperty("position")
	@NotNull
	@Size(max = 25)
	private String position;
	
	@JsonProperty("role")
	@Size(max = 12)
	private String role;
	
	@JsonProperty("fromDate")
	private Date fromDate;
	
	@JsonProperty("calAgyLocId")
	private String calAgyLocId;
	
	public String getAgyLocID() {
		return agyLocID;
	}

	public void setAgyLocID(String agyLocID) {
		this.agyLocID = agyLocID;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * Creates new StaffMemberRoles class Object
	 */
	public StaffMemberRoles() {
		// StaffMemberRoles
	}

	/**
	 * @return the nbtDescription
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param staffId
	 *            staffId to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param staffId
	 *            staffId to set
	 */
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	/**
	 * return the staffId
	 */
	public Integer getStaffId() {
		return this.staffId;
	}

	/**
	 * @param roleId
	 *            roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * return the roleId
	 */
	public Integer getRoleId() {
		return this.roleId;
	}

	/**
	 * @param createDatetime
	 *            createDatetime to set
	 */
	public void setCreateDateTime(Timestamp createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * return the createDatetime
	 */
	public Timestamp getCreateDateTime() {
		return this.createDateTime;
	}

	/**
	 * @param createUserId
	 *            createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return the createUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param modifyDatetime
	 *            modifyDatetime to set
	 */
	public void setModifyDateTime(Timestamp modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	/**
	 * return the modifyDatetime
	 */
	public Timestamp getModifyDateTime() {
		return this.modifyDateTime;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return the modifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @param roleCode
	 *            roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * return the roleCode
	 */
	public String getRoleCode() {
		return this.roleCode;
	}

	/**
	 * @param sealFlag
	 *            sealFlag to set
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return the sealFlag
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
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public int getLiReturn() {
		return liReturn;
	}

	public void setLiReturn(int liReturn) {
		this.liReturn = liReturn;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getCalAgyLocId() {
		return calAgyLocId;
	}

	public void setCalAgyLocId(final String calAgyLocId) {
		this.calAgyLocId = calAgyLocId;
	}

	
}