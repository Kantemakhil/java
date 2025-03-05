package net.syscon.s4.inst.movements.housingchanges.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the COURT_MOVEMENT_TMP database table.
 * 
 */
public class CourtMovementTmp implements Serializable {
	private static final long serialVersionUID = 1L;

	private String agyLocId;

	private Date createDatetime;

	private String createUserId;

	private BigDecimal livingUnitId1;

	private BigDecimal livingUnitId2;

	private BigDecimal livingUnitId3;

	private BigDecimal livingUnitId4;

	private Date modifyDatetime;

	private String modifyUserId;

	private Date movementDate;

	private String movementReason;

	private Date movementTime;

	private BigDecimal offenderBookId;

	private String sealFlag;

	private String toAgyLocId;

	private BigDecimal toLivingUnitId1;

	private BigDecimal toLivingUnitId2;

	private BigDecimal toLivingUnitId3;

	private BigDecimal toLivingUnitId4;
	
	private String livingUnitCode1;

	private String livingUnitCode2;

	private String livingUnitCode3;

	private String livingUnitCode4;
	
	private String livingUnitCode5;
	
	private String description;
	
	private String code;

	public CourtMovementTmp() {
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public BigDecimal getLivingUnitId1() {
		return this.livingUnitId1;
	}

	public void setLivingUnitId1(BigDecimal livingUnitId1) {
		this.livingUnitId1 = livingUnitId1;
	}

	public BigDecimal getLivingUnitId2() {
		return this.livingUnitId2;
	}

	public void setLivingUnitId2(BigDecimal livingUnitId2) {
		this.livingUnitId2 = livingUnitId2;
	}

	public BigDecimal getLivingUnitId3() {
		return this.livingUnitId3;
	}

	public void setLivingUnitId3(BigDecimal livingUnitId3) {
		this.livingUnitId3 = livingUnitId3;
	}

	public BigDecimal getLivingUnitId4() {
		return this.livingUnitId4;
	}

	public void setLivingUnitId4(BigDecimal livingUnitId4) {
		this.livingUnitId4 = livingUnitId4;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getMovementDate() {
		return this.movementDate;
	}

	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}

	public String getMovementReason() {
		return this.movementReason;
	}

	public void setMovementReason(String movementReason) {
		this.movementReason = movementReason;
	}

	public Date getMovementTime() {
		return this.movementTime;
	}

	public void setMovementTime(Date movementTime) {
		this.movementTime = movementTime;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getToAgyLocId() {
		return this.toAgyLocId;
	}

	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	public BigDecimal getToLivingUnitId1() {
		return this.toLivingUnitId1;
	}

	public void setToLivingUnitId1(BigDecimal toLivingUnitId1) {
		this.toLivingUnitId1 = toLivingUnitId1;
	}

	public BigDecimal getToLivingUnitId2() {
		return this.toLivingUnitId2;
	}

	public void setToLivingUnitId2(BigDecimal toLivingUnitId2) {
		this.toLivingUnitId2 = toLivingUnitId2;
	}

	public BigDecimal getToLivingUnitId3() {
		return this.toLivingUnitId3;
	}

	public void setToLivingUnitId3(BigDecimal toLivingUnitId3) {
		this.toLivingUnitId3 = toLivingUnitId3;
	}

	public BigDecimal getToLivingUnitId4() {
		return this.toLivingUnitId4;
	}

	public void setToLivingUnitId4(BigDecimal toLivingUnitId4) {
		this.toLivingUnitId4 = toLivingUnitId4;
	}

	/**
	 * @return the livingUnitCode1
	 */
	public String getLivingUnitCode1() {
		return livingUnitCode1;
	}

	/**
	 * @param livingUnitCode1 the livingUnitCode1 to set
	 */
	public void setLivingUnitCode1(String livingUnitCode1) {
		this.livingUnitCode1 = livingUnitCode1;
	}

	/**
	 * @return the livingUnitCode2
	 */
	public String getLivingUnitCode2() {
		return livingUnitCode2;
	}

	/**
	 * @param livingUnitCode2 the livingUnitCode2 to set
	 */
	public void setLivingUnitCode2(String livingUnitCode2) {
		this.livingUnitCode2 = livingUnitCode2;
	}

	/**
	 * @return the livingUnitCode3
	 */
	public String getLivingUnitCode3() {
		return livingUnitCode3;
	}

	/**
	 * @param livingUnitCode3 the livingUnitCode3 to set
	 */
	public void setLivingUnitCode3(String livingUnitCode3) {
		this.livingUnitCode3 = livingUnitCode3;
	}

	/**
	 * @return the livingUnitCode4
	 */
	public String getLivingUnitCode4() {
		return livingUnitCode4;
	}

	/**
	 * @param livingUnitCode4 the livingUnitCode4 to set
	 */
	public void setLivingUnitCode4(String livingUnitCode4) {
		this.livingUnitCode4 = livingUnitCode4;
	}

	/**
	 * @return the livingUnitCode5
	 */
	public String getLivingUnitCode5() {
		return livingUnitCode5;
	}

	/**
	 * @param livingUnitCode5 the livingUnitCode5 to set
	 */
	public void setLivingUnitCode5(String livingUnitCode5) {
		this.livingUnitCode5 = livingUnitCode5;
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
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
