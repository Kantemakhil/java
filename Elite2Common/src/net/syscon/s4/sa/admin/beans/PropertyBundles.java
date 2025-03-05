package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class PropertyBundles extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String groupId;
	
	private String groupName;
	
	private Integer propertySeq;
	
	private String caseloadId;
	
	private String activeFlag;

	private Date createDatetime;

	private String createUserId;

	private Date expiryDate;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;
	
	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getPropertySeq() {
		return propertySeq;
	}

	public void setPropertySeq(Integer propertySeq) {
		this.propertySeq = propertySeq;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}


	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	
}
