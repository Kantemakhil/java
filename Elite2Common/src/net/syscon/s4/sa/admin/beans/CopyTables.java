package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the COPY_TABLES database table.
 * 
 */
public class CopyTables extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private String colName;

	private Date createDatetime;

	private String createUserId;

	private Date expiryDate;

	private BigDecimal listSeq;

	private Date modifyDatetime;

	private String modifyUserId;

	private String parentTable;

	private String sealFlag;

	private String seqName;

	private String updateAllowedFlag;

	private String tableOperationCode;

	private String movementType;

	private String movementReasonCode;

	private String tableName;
    private Integer returnValue;
	public String getTableOperationCode() {
		return tableOperationCode;
	}

	public void setTableOperationCode(final String tableOperationCode) {
		this.tableOperationCode = tableOperationCode;
	}

	public String getMovementType() {
		return movementType;
	}

	public void setMovementType(final String movementType) {
		this.movementType = movementType;
	}

	public String getMovementReasonCode() {
		return movementReasonCode;
	}

	public void setMovementReasonCode(final String movementReasonCode) {
		this.movementReasonCode = movementReasonCode;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(final String tableName) {
		this.tableName = tableName;
	}

	public CopyTables() {
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getColName() {
		return this.colName;
	}

	public void setColName(final String colName) {
		this.colName = colName;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getParentTable() {
		return this.parentTable;
	}

	public void setParentTable(final String parentTable) {
		this.parentTable = parentTable;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getSeqName() {
		return this.seqName;
	}

	public void setSeqName(final String seqName) {
		this.seqName = seqName;
	}

	public String getUpdateAllowedFlag() {
		return this.updateAllowedFlag;
	}

	public void setUpdateAllowedFlag(final String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}

	public Integer getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(final Integer returnValue) {
		this.returnValue = returnValue;
	}

}
