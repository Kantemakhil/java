package net.syscon.s4.inst.movements.beans;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the V_OFFENDER_BOOKINGS database table.
 * 
 */
public class VOffenderBookings implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private String agencyImlDesc;

	private BigDecimal agencyImlId;

	private String agencyImlLevel1Code;

	private String agencyImlLevel2Code;

	private String agencyImlLevel3Code;

	private String agyLocId;

	private Object birthDate;

	private Object bookingBeginDate;

	private Object bookingEndDate;

	private String bookingNo;

	private String bookingStatus;

	private String firstName;

	private String inOutStatus;

	private String lastName;

	private String livingUnitDesc;

	private BigDecimal livingUnitId;

	private String livingUnitLevel1Code;

	private String livingUnitLevel2Code;

	private String livingUnitLevel3Code;

	private String livingUnitLevel4Code;

	private String middleName;

	private BigDecimal offenderBookId;

	private BigDecimal offenderId;

	private String offenderIdDisplay;

	private String suffix;

	public VOffenderBookings() {
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getAgencyImlDesc() {
		return this.agencyImlDesc;
	}

	public void setAgencyImlDesc(String agencyImlDesc) {
		this.agencyImlDesc = agencyImlDesc;
	}

	public BigDecimal getAgencyImlId() {
		return this.agencyImlId;
	}

	public void setAgencyImlId(BigDecimal agencyImlId) {
		this.agencyImlId = agencyImlId;
	}

	public String getAgencyImlLevel1Code() {
		return this.agencyImlLevel1Code;
	}

	public void setAgencyImlLevel1Code(String agencyImlLevel1Code) {
		this.agencyImlLevel1Code = agencyImlLevel1Code;
	}

	public String getAgencyImlLevel2Code() {
		return this.agencyImlLevel2Code;
	}

	public void setAgencyImlLevel2Code(String agencyImlLevel2Code) {
		this.agencyImlLevel2Code = agencyImlLevel2Code;
	}

	public String getAgencyImlLevel3Code() {
		return this.agencyImlLevel3Code;
	}

	public void setAgencyImlLevel3Code(String agencyImlLevel3Code) {
		this.agencyImlLevel3Code = agencyImlLevel3Code;
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public Object getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Object birthDate) {
		this.birthDate = birthDate;
	}

	public Object getBookingBeginDate() {
		return this.bookingBeginDate;
	}

	public void setBookingBeginDate(Object bookingBeginDate) {
		this.bookingBeginDate = bookingBeginDate;
	}

	public Object getBookingEndDate() {
		return this.bookingEndDate;
	}

	public void setBookingEndDate(Object bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}

	public String getBookingNo() {
		return this.bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	public String getBookingStatus() {
		return this.bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getInOutStatus() {
		return this.inOutStatus;
	}

	public void setInOutStatus(String inOutStatus) {
		this.inOutStatus = inOutStatus;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLivingUnitDesc() {
		return this.livingUnitDesc;
	}

	public void setLivingUnitDesc(String livingUnitDesc) {
		this.livingUnitDesc = livingUnitDesc;
	}

	public BigDecimal getLivingUnitId() {
		return this.livingUnitId;
	}

	public void setLivingUnitId(BigDecimal livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	public String getLivingUnitLevel1Code() {
		return this.livingUnitLevel1Code;
	}

	public void setLivingUnitLevel1Code(String livingUnitLevel1Code) {
		this.livingUnitLevel1Code = livingUnitLevel1Code;
	}

	public String getLivingUnitLevel2Code() {
		return this.livingUnitLevel2Code;
	}

	public void setLivingUnitLevel2Code(String livingUnitLevel2Code) {
		this.livingUnitLevel2Code = livingUnitLevel2Code;
	}

	public String getLivingUnitLevel3Code() {
		return this.livingUnitLevel3Code;
	}

	public void setLivingUnitLevel3Code(String livingUnitLevel3Code) {
		this.livingUnitLevel3Code = livingUnitLevel3Code;
	}

	public String getLivingUnitLevel4Code() {
		return this.livingUnitLevel4Code;
	}

	public void setLivingUnitLevel4Code(String livingUnitLevel4Code) {
		this.livingUnitLevel4Code = livingUnitLevel4Code;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getOffenderId() {
		return this.offenderId;
	}

	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public String getOffenderIdDisplay() {
		return this.offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
