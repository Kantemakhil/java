package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class StaffWorkAssignmentsV1 extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String agyLocId;

	private BigDecimal bookId;

	private String calAgyLocId;

	private BigDecimal chargeSeq;

	private String component;

	private String description;

	private String firstName;

	private Date fromDate;

	private String lastName;

	private BigDecimal line;

	private BigDecimal offassId;

	private BigDecimal offenderBookId;

	private BigDecimal offenderBookIdRequest;

	private BigDecimal offenderId;

	private String offenderIdDisplay;

	private String offenderName;

	private String orderCode;

	private Date orderReqExpiry;

	private String orderType;

	private String position;

	private BigDecimal requestSeq;

	private String role;

	private BigDecimal sacStaffId;

	private BigDecimal sentenceSeq;

	private BigDecimal staffId;

	private String staffName;

	private String status;

	private Date supExpRptDue;

	private Date viewOrder;

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the bookId
	 */
	public BigDecimal getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(final BigDecimal bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the calAgyLocId
	 */
	public String getCalAgyLocId() {
		return calAgyLocId;
	}

	/**
	 * @param calAgyLocId the calAgyLocId to set
	 */
	public void setCalAgyLocId(final String calAgyLocId) {
		this.calAgyLocId = calAgyLocId;
	}

	/**
	 * @return the chargeSeq
	 */
	public BigDecimal getChargeSeq() {
		return chargeSeq;
	}

	/**
	 * @param chargeSeq the chargeSeq to set
	 */
	public void setChargeSeq(final BigDecimal chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	/**
	 * @return the component
	 */
	public String getComponent() {
		return component;
	}

	/**
	 * @param component the component to set
	 */
	public void setComponent(final String component) {
		this.component = component;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(final Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the line
	 */
	public BigDecimal getLine() {
		return line;
	}

	/**
	 * @param line the line to set
	 */
	public void setLine(final BigDecimal line) {
		this.line = line;
	}

	/**
	 * @return the offassId
	 */
	public BigDecimal getOffassId() {
		return offassId;
	}

	/**
	 * @param offassId the offassId to set
	 */
	public void setOffassId(final BigDecimal offassId) {
		this.offassId = offassId;
	}

	/**
	 * @return the offenderBookId
	 */
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the offenderBookIdRequest
	 */
	public BigDecimal getOffenderBookIdRequest() {
		return offenderBookIdRequest;
	}

	/**
	 * @param offenderBookIdRequest the offenderBookIdRequest to set
	 */
	public void setOffenderBookIdRequest(final BigDecimal offenderBookIdRequest) {
		this.offenderBookIdRequest = offenderBookIdRequest;
	}

	/**
	 * @return the offenderId
	 */
	public BigDecimal getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId the offenderId to set
	 */
	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
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
	 * @return the offenderName
	 */
	public String getOffenderName() {
		return offenderName;
	}

	/**
	 * @param offenderName the offenderName to set
	 */
	public void setOffenderName(final String offenderName) {
		this.offenderName = offenderName;
	}

	/**
	 * @return the orderCode
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * @param orderCode the orderCode to set
	 */
	public void setOrderCode(final String orderCode) {
		this.orderCode = orderCode;
	}

	/**
	 * @return the orderReqExpiry
	 */
	public Date getOrderReqExpiry() {
		return orderReqExpiry;
	}

	/**
	 * @param orderReqExpiry the orderReqExpiry to set
	 */
	public void setOrderReqExpiry(final Date orderReqExpiry) {
		this.orderReqExpiry = orderReqExpiry;
	}

	/**
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(final String orderType) {
		this.orderType = orderType;
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
	 * @return the requestSeq
	 */
	public BigDecimal getRequestSeq() {
		return requestSeq;
	}

	/**
	 * @param requestSeq the requestSeq to set
	 */
	public void setRequestSeq(final BigDecimal requestSeq) {
		this.requestSeq = requestSeq;
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
	 * @return the sacStaffId
	 */
	public BigDecimal getSacStaffId() {
		return sacStaffId;
	}

	/**
	 * @param sacStaffId the sacStaffId to set
	 */
	public void setSacStaffId(final BigDecimal sacStaffId) {
		this.sacStaffId = sacStaffId;
	}

	/**
	 * @return the sentenceSeq
	 */
	public BigDecimal getSentenceSeq() {
		return sentenceSeq;
	}

	/**
	 * @param sentenceSeq the sentenceSeq to set
	 */
	public void setSentenceSeq(final BigDecimal sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	/**
	 * @return the staffId
	 */
	public BigDecimal getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(final BigDecimal staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * @param staffName the staffName to set
	 */
	public void setStaffName(final String staffName) {
		this.staffName = staffName;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * @return the supExpRptDue
	 */
	public Date getSupExpRptDue() {
		return supExpRptDue;
	}

	/**
	 * @param supExpRptDue the supExpRptDue to set
	 */
	public void setSupExpRptDue(final Date supExpRptDue) {
		this.supExpRptDue = supExpRptDue;
	}

	/**
	 * @return the viewOrder
	 */
	public Date getViewOrder() {
		return viewOrder;
	}

	/**
	 * @param viewOrder the viewOrder to set
	 */
	public void setViewOrder(final Date viewOrder) {
		this.viewOrder = viewOrder;
	}

}
