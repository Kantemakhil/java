package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SentenceKeyDates implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("date")
	private Date date;
	
	@JsonProperty("time")
	private String time;
	
	@JsonProperty("calculationReason")
	private String calculationReason;
	
	@JsonProperty("staffName")
	private String staffName;
	
	@JsonProperty("comment")
	private String comment;
	
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("staffId")
	private Integer staffId;
	
	@JsonProperty("calculationDate")
	private Date calculationDate;

	@JsonProperty("HDCED_Calculated_Date")
	private Date HDCED_Calculated_Date;
	
	@JsonProperty("HDCED_Overrided_Date")
	private Date HDCED_Overrided_Date;
	
	@JsonProperty("HDCAD_Calculated_Date")
	private Date HDCAD_Calculated_Date;
	
	@JsonProperty("HDCAD_Overrided_Date")
	private Date HDCAD_Overrided_Date;
	
	@JsonProperty("ETD_Calculated_Date")
	private Date ETD_Calculated_Date;
	
	@JsonProperty("ETD_Overrided_Date")
	private Date ETD_Overrided_Date;
	
	@JsonProperty("MTD_Calculated_Date")
	private Date MTD_Calculated_Date;
	
	@JsonProperty("MTD_Overrided_Date")
	private Date MTD_Overrided_Date;
	
	@JsonProperty("LTD_Calculated_Date")
	private Date LTD_Calculated_Date;
	
	@JsonProperty("LTD_Overrided_Date")
	private Date LTD_Overrided_Date;
	
	@JsonProperty("ARD_Calculated_Date")
	private Date ARD_Calculated_Date;
	
	@JsonProperty("ARD_Overrided_Date")
	private Date ARD_Overrided_Date;
	
	@JsonProperty("CRD_Calculated_Date")
	private Date CRD_Calculated_Date;
	
	@JsonProperty("CRD_Overrided_Date")
	private Date CRD_Overrided_Date;
	
	@JsonProperty("PED_Calculated_Date")
	private Date PED_Calculated_Date;
	
	@JsonProperty("PED_Overrided_Date")
	private Date PED_Overrided_Date;
	
	@JsonProperty("APD_Calculated_Date")
	private Date APD_Calculated_Date;
	
	@JsonProperty("APD_Overrided_Date")
	private Date APD_Overrided_Date;
	
	@JsonProperty("NPD_Calculated_Date")
	private Date NPD_Calculated_Date;
	
	@JsonProperty("NPD_Overrided_Date")
	private Date NPD_Overrided_Date;
	
	@JsonProperty("LED_Calculated_Date")
	private Date LED_Calculated_Date;
	
	@JsonProperty("LED_Overrided_Date")
	private Date LED_Overrided_Date;
	
	@JsonProperty("SED_Calculated_Date")
	private Date SED_Calculated_Date;
	
	@JsonProperty("SED_Overrided_Date")
	private Date SED_Overrided_Date;
	
	@JsonProperty("PRRD_Calculated_Date")
	private Date PRRD_Calculated_Date;
	
	@JsonProperty("PRRD_Overrided_Date")
	private Date PRRD_Overrided_Date;
	
	@JsonProperty("TARIFF_Calculated_Date")
	private Date TARIFF_Calculated_Date;
	
	@JsonProperty("TARIFF_Overrided_Date")
	private Date TARIFF_Overrided_Date;
	
	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the calculationReason
	 */
	public String getCalculationReason() {
		return calculationReason;
	}

	/**
	 * @param calculationReason the calculationReason to set
	 */
	public void setCalculationReason(String calculationReason) {
		this.calculationReason = calculationReason;
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
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the staffId
	 */
	public Integer getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Date getCalculationDate() {
		return calculationDate;
	}

	public void setCalculationDate(Date calculationDate) {
		this.calculationDate = calculationDate;
	}

	/**
	 * @return the hDCED_Calculated_Date
	 */
	public Date getHDCED_Calculated_Date() {
		return HDCED_Calculated_Date;
	}

	/**
	 * @param hDCED_Calculated_Date the hDCED_Calculated_Date to set
	 */
	public void setHDCED_Calculated_Date(Date hDCED_Calculated_Date) {
		HDCED_Calculated_Date = hDCED_Calculated_Date;
	}

	/**
	 * @return the hDCED_Overrided_Date
	 */
	public Date getHDCED_Overrided_Date() {
		return HDCED_Overrided_Date;
	}

	/**
	 * @param hDCED_Overrided_Date the hDCED_Overrided_Date to set
	 */
	public void setHDCED_Overrided_Date(Date hDCED_Overrided_Date) {
		HDCED_Overrided_Date = hDCED_Overrided_Date;
	}

	/**
	 * @return the hDCAD_Calculated_Date
	 */
	public Date getHDCAD_Calculated_Date() {
		return HDCAD_Calculated_Date;
	}

	/**
	 * @param hDCAD_Calculated_Date the hDCAD_Calculated_Date to set
	 */
	public void setHDCAD_Calculated_Date(Date hDCAD_Calculated_Date) {
		HDCAD_Calculated_Date = hDCAD_Calculated_Date;
	}

	/**
	 * @return the hDCAD_Overrided_Date
	 */
	public Date getHDCAD_Overrided_Date() {
		return HDCAD_Overrided_Date;
	}

	/**
	 * @param hDCAD_Overrided_Date the hDCAD_Overrided_Date to set
	 */
	public void setHDCAD_Overrided_Date(Date hDCAD_Overrided_Date) {
		HDCAD_Overrided_Date = hDCAD_Overrided_Date;
	}

	/**
	 * @return the eTD_Calculated_Date
	 */
	public Date getETD_Calculated_Date() {
		return ETD_Calculated_Date;
	}

	/**
	 * @param eTD_Calculated_Date the eTD_Calculated_Date to set
	 */
	public void setETD_Calculated_Date(Date eTD_Calculated_Date) {
		ETD_Calculated_Date = eTD_Calculated_Date;
	}

	/**
	 * @return the eTD_Overrided_Date
	 */
	public Date getETD_Overrided_Date() {
		return ETD_Overrided_Date;
	}

	/**
	 * @param eTD_Overrided_Date the eTD_Overrided_Date to set
	 */
	public void setETD_Overrided_Date(Date eTD_Overrided_Date) {
		ETD_Overrided_Date = eTD_Overrided_Date;
	}

	/**
	 * @return the mTD_Calculated_Date
	 */
	public Date getMTD_Calculated_Date() {
		return MTD_Calculated_Date;
	}

	/**
	 * @param mTD_Calculated_Date the mTD_Calculated_Date to set
	 */
	public void setMTD_Calculated_Date(Date mTD_Calculated_Date) {
		MTD_Calculated_Date = mTD_Calculated_Date;
	}

	/**
	 * @return the mTD_Overrided_Date
	 */
	public Date getMTD_Overrided_Date() {
		return MTD_Overrided_Date;
	}

	/**
	 * @param mTD_Overrided_Date the mTD_Overrided_Date to set
	 */
	public void setMTD_Overrided_Date(Date mTD_Overrided_Date) {
		MTD_Overrided_Date = mTD_Overrided_Date;
	}

	/**
	 * @return the lTD_Calculated_Date
	 */
	public Date getLTD_Calculated_Date() {
		return LTD_Calculated_Date;
	}

	/**
	 * @param lTD_Calculated_Date the lTD_Calculated_Date to set
	 */
	public void setLTD_Calculated_Date(Date lTD_Calculated_Date) {
		LTD_Calculated_Date = lTD_Calculated_Date;
	}

	/**
	 * @return the lTD_Overrided_Date
	 */
	public Date getLTD_Overrided_Date() {
		return LTD_Overrided_Date;
	}

	/**
	 * @param lTD_Overrided_Date the lTD_Overrided_Date to set
	 */
	public void setLTD_Overrided_Date(Date lTD_Overrided_Date) {
		LTD_Overrided_Date = lTD_Overrided_Date;
	}

	/**
	 * @return the aRD_Calculated_Date
	 */
	public Date getARD_Calculated_Date() {
		return ARD_Calculated_Date;
	}

	/**
	 * @param aRD_Calculated_Date the aRD_Calculated_Date to set
	 */
	public void setARD_Calculated_Date(Date aRD_Calculated_Date) {
		ARD_Calculated_Date = aRD_Calculated_Date;
	}

	/**
	 * @return the aRD_Overrided_Date
	 */
	public Date getARD_Overrided_Date() {
		return ARD_Overrided_Date;
	}

	/**
	 * @param aRD_Overrided_Date the aRD_Overrided_Date to set
	 */
	public void setARD_Overrided_Date(Date aRD_Overrided_Date) {
		ARD_Overrided_Date = aRD_Overrided_Date;
	}

	/**
	 * @return the cRD_Calculated_Date
	 */
	public Date getCRD_Calculated_Date() {
		return CRD_Calculated_Date;
	}

	/**
	 * @param cRD_Calculated_Date the cRD_Calculated_Date to set
	 */
	public void setCRD_Calculated_Date(Date cRD_Calculated_Date) {
		CRD_Calculated_Date = cRD_Calculated_Date;
	}

	/**
	 * @return the cRD_Overrided_Date
	 */
	public Date getCRD_Overrided_Date() {
		return CRD_Overrided_Date;
	}

	/**
	 * @param cRD_Overrided_Date the cRD_Overrided_Date to set
	 */
	public void setCRD_Overrided_Date(Date cRD_Overrided_Date) {
		CRD_Overrided_Date = cRD_Overrided_Date;
	}

	/**
	 * @return the pED_Calculated_Date
	 */
	public Date getPED_Calculated_Date() {
		return PED_Calculated_Date;
	}

	/**
	 * @param pED_Calculated_Date the pED_Calculated_Date to set
	 */
	public void setPED_Calculated_Date(Date pED_Calculated_Date) {
		PED_Calculated_Date = pED_Calculated_Date;
	}

	/**
	 * @return the pED_Overrided_Date
	 */
	public Date getPED_Overrided_Date() {
		return PED_Overrided_Date;
	}

	/**
	 * @param pED_Overrided_Date the pED_Overrided_Date to set
	 */
	public void setPED_Overrided_Date(Date pED_Overrided_Date) {
		PED_Overrided_Date = pED_Overrided_Date;
	}

	/**
	 * @return the aPD_Calculated_Date
	 */
	public Date getAPD_Calculated_Date() {
		return APD_Calculated_Date;
	}

	/**
	 * @param aPD_Calculated_Date the aPD_Calculated_Date to set
	 */
	public void setAPD_Calculated_Date(Date aPD_Calculated_Date) {
		APD_Calculated_Date = aPD_Calculated_Date;
	}

	/**
	 * @return the aPD_Overrided_Date
	 */
	public Date getAPD_Overrided_Date() {
		return APD_Overrided_Date;
	}

	/**
	 * @param aPD_Overrided_Date the aPD_Overrided_Date to set
	 */
	public void setAPD_Overrided_Date(Date aPD_Overrided_Date) {
		APD_Overrided_Date = aPD_Overrided_Date;
	}

	/**
	 * @return the nPD_Calculated_Date
	 */
	public Date getNPD_Calculated_Date() {
		return NPD_Calculated_Date;
	}

	/**
	 * @param nPD_Calculated_Date the nPD_Calculated_Date to set
	 */
	public void setNPD_Calculated_Date(Date nPD_Calculated_Date) {
		NPD_Calculated_Date = nPD_Calculated_Date;
	}

	/**
	 * @return the nPD_Overrided_Date
	 */
	public Date getNPD_Overrided_Date() {
		return NPD_Overrided_Date;
	}

	/**
	 * @param nPD_Overrided_Date the nPD_Overrided_Date to set
	 */
	public void setNPD_Overrided_Date(Date nPD_Overrided_Date) {
		NPD_Overrided_Date = nPD_Overrided_Date;
	}

	/**
	 * @return the lED_Calculated_Date
	 */
	public Date getLED_Calculated_Date() {
		return LED_Calculated_Date;
	}

	/**
	 * @param lED_Calculated_Date the lED_Calculated_Date to set
	 */
	public void setLED_Calculated_Date(Date lED_Calculated_Date) {
		LED_Calculated_Date = lED_Calculated_Date;
	}

	/**
	 * @return the lED_Overrided_Date
	 */
	public Date getLED_Overrided_Date() {
		return LED_Overrided_Date;
	}

	/**
	 * @param lED_Overrided_Date the lED_Overrided_Date to set
	 */
	public void setLED_Overrided_Date(Date lED_Overrided_Date) {
		LED_Overrided_Date = lED_Overrided_Date;
	}

	/**
	 * @return the sED_Calculated_Date
	 */
	public Date getSED_Calculated_Date() {
		return SED_Calculated_Date;
	}

	/**
	 * @param sED_Calculated_Date the sED_Calculated_Date to set
	 */
	public void setSED_Calculated_Date(Date sED_Calculated_Date) {
		SED_Calculated_Date = sED_Calculated_Date;
	}

	/**
	 * @return the sED_Overrided_Date
	 */
	public Date getSED_Overrided_Date() {
		return SED_Overrided_Date;
	}

	/**
	 * @param sED_Overrided_Date the sED_Overrided_Date to set
	 */
	public void setSED_Overrided_Date(Date sED_Overrided_Date) {
		SED_Overrided_Date = sED_Overrided_Date;
	}

	/**
	 * @return the pRRD_Calculated_Date
	 */
	public Date getPRRD_Calculated_Date() {
		return PRRD_Calculated_Date;
	}

	/**
	 * @param pRRD_Calculated_Date the pRRD_Calculated_Date to set
	 */
	public void setPRRD_Calculated_Date(Date pRRD_Calculated_Date) {
		PRRD_Calculated_Date = pRRD_Calculated_Date;
	}

	/**
	 * @return the pRRD_Overrided_Date
	 */
	public Date getPRRD_Overrided_Date() {
		return PRRD_Overrided_Date;
	}

	/**
	 * @param pRRD_Overrided_Date the pRRD_Overrided_Date to set
	 */
	public void setPRRD_Overrided_Date(Date pRRD_Overrided_Date) {
		PRRD_Overrided_Date = pRRD_Overrided_Date;
	}

	/**
	 * @return the tARIFF_Calculated_Date
	 */
	public Date getTARIFF_Calculated_Date() {
		return TARIFF_Calculated_Date;
	}

	/**
	 * @param tARIFF_Calculated_Date the tARIFF_Calculated_Date to set
	 */
	public void setTARIFF_Calculated_Date(Date tARIFF_Calculated_Date) {
		TARIFF_Calculated_Date = tARIFF_Calculated_Date;
	}

	/**
	 * @return the tARIFF_Overrided_Date
	 */
	public Date getTARIFF_Overrided_Date() {
		return TARIFF_Overrided_Date;
	}

	/**
	 * @param tARIFF_Overrided_Date the tARIFF_Overrided_Date to set
	 */
	public void setTARIFF_Overrided_Date(Date tARIFF_Overrided_Date) {
		TARIFF_Overrided_Date = tARIFF_Overrided_Date;
	}
	
	
}
