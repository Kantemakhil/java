package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;



public class CourseActivityParties extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private long crsActyPartyId;

	private String contactText;

	private Date createDatetime;

	private String createUserId;

	private String firstName;

	private String lastName;

	private Date modifyDatetime;

	private String modifyUserId;

	private String partyRole;

	private String partyRoleText;

	private BigDecimal personId;

	private String registrationText;

	private String sealFlag;

	private BigDecimal staffId;
	private long crsActyId;
	private String nbtLastName;
	private String nbtFirstName;
	private long providerId;
	@JsonProperty("rowId")
	private String rowId;
	private int returnValue;
	private int serverCode;
	

	//bi-directional many-to-one association to CourseActivity
	//private CourseActivity courseActivity;

	public int getServerCode() {
		return serverCode;
	}

	public void setServerCode(int serverCode) {
		this.serverCode = serverCode;
	}

	public int getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(int returnValue) {
		this.returnValue = returnValue;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public long getProviderId() {
		return providerId;
	}

	public void setProviderId(long providerId) {
		this.providerId = providerId;
	}

	public String getNbtLastName() {
		return nbtLastName;
	}

	public void setNbtLastName(String nbtLastName) {
		this.nbtLastName = nbtLastName;
	}

	public String getNbtFirstName() {
		return nbtFirstName;
	}

	public void setNbtFirstName(String nbtFirstName) {
		this.nbtFirstName = nbtFirstName;
	}

	public long getCrsActyId() {
		return crsActyId;
	}

	public void setCrsActyId(long crsActyId) {
		this.crsActyId = crsActyId;
	}

	public CourseActivityParties() {
	}

	public long getCrsActyPartyId() {
		return this.crsActyPartyId;
	}

	public void setCrsActyPartyId(long crsActyPartyId) {
		this.crsActyPartyId = crsActyPartyId;
	}

	public String getContactText() {
		return this.contactText;
	}

	public void setContactText(String contactText) {
		this.contactText = contactText;
	}

	

	public String getCreateUserId() {
		return this.createUserId;
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

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getPartyRole() {
		return this.partyRole;
	}

	public void setPartyRole(String partyRole) {
		this.partyRole = partyRole;
	}

	public String getPartyRoleText() {
		return this.partyRoleText;
	}

	public void setPartyRoleText(String partyRoleText) {
		this.partyRoleText = partyRoleText;
	}

	public BigDecimal getPersonId() {
		return this.personId;
	}

	public void setPersonId(java.math.BigDecimal personId) {
		this.personId = personId;
	}

	public String getRegistrationText() {
		return this.registrationText;
	}

	public void setRegistrationText(String registrationText) {
		this.registrationText = registrationText;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getStaffId() {
		return this.staffId;
	}

	public void setStaffId(java.math.BigDecimal staffId) {
		this.staffId = staffId;
	}

	/*public CourseActivity getCourseActivity() {
		return this.courseActivity;
	}

	public void setCourseActivity(CourseActivity courseActivity) {
		this.courseActivity = courseActivity;
	}*/

}
