package net.syscon.s4.inst.systemsearch;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class VHistoricalBookings
 */
public class VHistoricalBookings extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("rootOffenderId")
	private int rootOffenderId;
	@JsonProperty("offenderBookId")
	private int offenderBookId;
	@JsonProperty("inMovementSeq")
	private int inMovementSeq;
	@JsonProperty("inDate")
	private Date inDate;
	@JsonProperty("inTime")
	private Date inTime;
	@JsonProperty("inMovementType")
	private String inMovementType;
	@JsonProperty("inMovementReasonCode")
	private String inMovementReasonCode;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("agyLocType")
	private String agyLocType;
	@JsonProperty("outDate")
	private Date outDate;
	@JsonProperty("outTime")
	private Date outTime;
	@JsonProperty("outMovementSeq")
	private int outMovementSeq;
	@JsonProperty("outMovementType")
	private String outMovementType;
	@JsonProperty("outMovementReasonCode")
	private String outMovementReasonCode;
	@JsonProperty("bookingNo")
	private String bookingNo;
	@JsonProperty("locatoinDescription")
	private String locatoinDescription;
	@JsonProperty("admitIntakeComments")
	private String admitIntakeComments;
	@JsonProperty("releaseCloseComments")
	private String releaseCloseComments;

	public String getAdmitIntakeComments() {
		return admitIntakeComments;
	}

	public void setAdmitIntakeComments(final String admitIntakeComments) {
		this.admitIntakeComments = admitIntakeComments;
	}

	public String getReleaseCloseComments() {
		return releaseCloseComments;
	}

	public void setReleaseCloseComments(final String releaseCloseComments) {
		this.releaseCloseComments = releaseCloseComments;
	}

	public String getLocatoinDescription() {
		return locatoinDescription;
	}

	public void setLocatoinDescription(final String locatoinDescription) {
		this.locatoinDescription = locatoinDescription;
	}

	private boolean inserted;

	/**
	 * @param rootOffenderId
	 *            rootOffenderId to set
	 */
	public void setRootOffenderId(final int rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	/**
	 * return therootOffenderId
	 */
	public int getRootOffenderId() {
		return this.rootOffenderId;
	}

	/**
	 * @param offenderBookId
	 *            offenderBookId to set
	 */
	public void setOffenderBookId(final int offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * return theoffenderBookId
	 */
	public int getOffenderBookId() {
		return this.offenderBookId;
	}

	/**
	 * @param inMovementSeq
	 *            inMovementSeq to set
	 */
	public void setInMovementSeq(final int inMovementSeq) {
		this.inMovementSeq = inMovementSeq;
	}

	/**
	 * return theinMovementSeq
	 */
	public int getInMovementSeq() {
		return this.inMovementSeq;
	}

	/**
	 * @param inDate
	 *            inDate to set
	 */
	public void setInDate(final Date inDate) {
		this.inDate = inDate;
	}

	/**
	 * return theinDate
	 */
	public Date getInDate() {
		return this.inDate;
	}

	/**
	 * @param inTime
	 *            inTime to set
	 */
	public void setInTime(final Date inTime) {
		this.inTime = inTime;
	}

	/**
	 * return theinTime
	 */
	public Date getInTime() {
		return this.inTime;
	}

	/**
	 * @param inMovementType
	 *            inMovementType to set
	 */
	public void setInMovementType(final String inMovementType) {
		this.inMovementType = inMovementType;
	}

	/**
	 * return theinMovementType
	 */
	public String getInMovementType() {
		return this.inMovementType;
	}

	/**
	 * @param inMovementReasonCode
	 *            inMovementReasonCode to set
	 */
	public void setInMovementReasonCode(final String inMovementReasonCode) {
		this.inMovementReasonCode = inMovementReasonCode;
	}

	/**
	 * return theinMovementReasonCode
	 */
	public String getInMovementReasonCode() {
		return this.inMovementReasonCode;
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
	 * @param outDate
	 *            outDate to set
	 */
	public void setOutDate(final Date outDate) {
		this.outDate = outDate;
	}

	/**
	 * return theoutDate
	 */
	public Date getOutDate() {
		return this.outDate;
	}

	/**
	 * @param outTime
	 *            outTime to set
	 */
	public void setOutTime(final Date outTime) {
		this.outTime = outTime;
	}

	/**
	 * return theoutTime
	 */
	public Date getOutTime() {
		return this.outTime;
	}

	/**
	 * @param outMovementSeq
	 *            outMovementSeq to set
	 */
	public void setOutMovementSeq(final int outMovementSeq) {
		this.outMovementSeq = outMovementSeq;
	}

	/**
	 * return theoutMovementSeq
	 */
	public int getOutMovementSeq() {
		return this.outMovementSeq;
	}

	/**
	 * @param outMovementType
	 *            outMovementType to set
	 */
	public void setOutMovementType(final String outMovementType) {
		this.outMovementType = outMovementType;
	}

	/**
	 * return theoutMovementType
	 */
	public String getOutMovementType() {
		return this.outMovementType;
	}

	/**
	 * @param outMovementReasonCode
	 *            outMovementReasonCode to set
	 */
	public void setOutMovementReasonCode(final String outMovementReasonCode) {
		this.outMovementReasonCode = outMovementReasonCode;
	}

	/**
	 * return theoutMovementReasonCode
	 */
	public String getOutMovementReasonCode() {
		return this.outMovementReasonCode;
	}

	/**
	 * @param bookingNo
	 *            bookingNo to set
	 */
	public void setBookingNo(final String bookingNo) {
		this.bookingNo = bookingNo;
	}

	/**
	 * return thebookingNo
	 */
	public String getBookingNo() {
		return this.bookingNo;
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

	public String getAgyLocType() {
		return agyLocType;
	}

	public void setAgyLocType(final String agyLocType) {
		this.agyLocType = agyLocType;
	}

}