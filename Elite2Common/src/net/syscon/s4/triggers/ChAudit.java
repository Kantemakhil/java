package net.syscon.s4.triggers;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class ChAudit extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String actionType;
	private String agyLocId;
	private String offenderIdDisplay;
	private String bookingNo;
	private String offenceCode;
	private Double initialCounts;
	private String offenceType;
	private Long offenderChargeId;
	private String chargeStatus;
	private String description;
	private String statuteCode;
	private Date offenceDate;
	private Long offenderBookId;
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

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	public String getOffenceCode() {
		return offenceCode;
	}

	public void setOffenceCode(String offenceCode) {
		this.offenceCode = offenceCode;
	}

	public Double getInitialCounts() {
		return initialCounts;
	}

	public void setInitialCounts(Double initialCounts) {
		this.initialCounts = initialCounts;
	}

	public String getOffenceType() {
		return offenceType;
	}

	public void setOffenceType(String offenceType) {
		this.offenceType = offenceType;
	}

	public Long getOffenderChargeId() {
		return offenderChargeId;
	}

	public void setOffenderChargeId(Long offenderChargeId) {
		this.offenderChargeId = offenderChargeId;
	}

	public String getChargeStatus() {
		return chargeStatus;
	}

	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatuteCode() {
		return statuteCode;
	}

	public void setStatuteCode(String statuteCode) {
		this.statuteCode = statuteCode;
	}

	public Date getOffenceDate() {
		return offenceDate;
	}

	public void setOffenceDate(Date offenceDate) {
		this.offenceDate = offenceDate;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
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
