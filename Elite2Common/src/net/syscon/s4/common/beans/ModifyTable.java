package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ModifyTable extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("updateAllowedFlag")
	private String updateAllowedFlag;

	@JsonProperty("movementReason")
	private MovementReason movementReason;

	@JsonProperty("tableOperationCode")
	private String tableOperationCode;

	@JsonProperty("movementType")
	private String movementType;

	@JsonProperty("movementReasonCode")
	private String movementReasonCode;

	@JsonProperty("tableName")
	private String tableName;

	/**
	 *
	 * @return
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 *
	 * @param activeFlag
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 *
	 * @param expiryDate
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getListSeq() {
		return listSeq;
	}

	/**
	 *
	 * @param listSeq
	 */
	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getUpdateAllowedFlag() {
		return updateAllowedFlag;
	}

	/**
	 *
	 * @param updateAllowedFlag
	 */
	public void setUpdateAllowedFlag(String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}

	/**
	 *
	 * @return
	 */
	public MovementReason getMovementReason() {
		return movementReason;
	}

	/**
	 *
	 * @param movementReason
	 */
	public void setMovementReason(MovementReason movementReason) {
		this.movementReason = movementReason;
	}

	/**
	 *
	 * @return
	 */
	public String getTableOperationCode() {
		return tableOperationCode;
	}

	/**
	 *
	 * @param tableOperationCode
	 */
	public void setTableOperationCode(String tableOperationCode) {
		this.tableOperationCode = tableOperationCode;
	}

	/**
	 *
	 * @return
	 */
	public String getMovementType() {
		return movementType;
	}

	/**
	 *
	 * @param movementType
	 */
	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	/**
	 *
	 * @return
	 */
	public String getMovementReasonCode() {
		return movementReasonCode;
	}

	/**
	 *
	 * @param movementReasonCode
	 */
	public void setMovementReasonCode(String movementReasonCode) {
		this.movementReasonCode = movementReasonCode;
	}

	/**
	 *
	 * @return
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 *
	 * @param tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}