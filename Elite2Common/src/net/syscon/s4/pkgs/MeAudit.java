package net.syscon.s4.pkgs;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class MeAudit extends BaseModel  implements Serializable{
	
	private String actionType;
	private String agyLocId;
	private String oldOffenderIdDisplay;
	private String newOffenderIdDisplay;
	private String newBookingNo;
	private Date modifiedDate;
	private String sealFlag;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getAgyLocId() {
		return agyLocId;
	}
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}
	public String getOldOffenderIdDisplay() {
		return oldOffenderIdDisplay;
	}
	public void setOldOffenderIdDisplay(String oldOffenderIdDisplay) {
		this.oldOffenderIdDisplay = oldOffenderIdDisplay;
	}
	public String getNewOffenderIdDisplay() {
		return newOffenderIdDisplay;
	}
	public void setNewOffenderIdDisplay(String newOffenderIdDisplay) {
		this.newOffenderIdDisplay = newOffenderIdDisplay;
	}
	public String getNewBookingNo() {
		return newBookingNo;
	}
	public void setNewBookingNo(String newBookingNo) {
		this.newBookingNo = newBookingNo;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
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
