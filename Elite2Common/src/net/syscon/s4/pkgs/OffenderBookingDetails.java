package net.syscon.s4.pkgs;

import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderBookingDetails extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long offenderBookId;
	private String cellSharingAlertFlag;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	private String specialNeedsText;
	private String availabilityText;
	private String sealFlag;

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getCellSharingAlertFlag() {
		return cellSharingAlertFlag;
	}

	public void setCellSharingAlertFlag(String cellSharingAlertFlag) {
		this.cellSharingAlertFlag = cellSharingAlertFlag;
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

	public String getSpecialNeedsText() {
		return specialNeedsText;
	}

	public void setSpecialNeedsText(String specialNeedsText) {
		this.specialNeedsText = specialNeedsText;
	}

	public String getAvailabilityText() {
		return availabilityText;
	}

	public void setAvailabilityText(String availabilityText) {
		this.availabilityText = availabilityText;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
