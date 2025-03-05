package net.syscon.s4.inst.automatedcounts.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Class SalesMaintenances
 */

import net.syscon.s4.common.beans.BaseModel;

public class SalesMaintenances extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("stockLocId")
	private String stockLocId;
	@JsonProperty("passQuantityFieldFlag")
	private String passQuantityFieldFlag;
	@JsonProperty("groupLikeItemFlag")
	private String groupLikeItemFlag;
	@JsonProperty("soundAlertFlag")
	private String soundAlertFlag;
	@JsonProperty("userAcknowledgeFlag")
	private String userAcknowledgeFlag;
	@JsonProperty("exceptionRecordedFlag")
	private String exceptionRecordedFlag;
	@JsonProperty("autoSaleReceiptFlag")
	private String autoSaleReceiptFlag;
	@JsonProperty("saleReceiptName")
	private String saleReceiptName;
	@JsonProperty("saleReceiptCopy")
	private Integer saleReceiptCopy;
	@JsonProperty("displayReceiptCommentFlag")
	private String displayReceiptCommentFlag;
	@JsonProperty("printReceiptBalanceFlag")
	private String printReceiptBalanceFlag;
	@JsonProperty("autoReturnReceiptFlag")
	private String autoReturnReceiptFlag;
	@JsonProperty("returnReceiptName")
	private String returnReceiptName;
	@JsonProperty("returnReceiptCopy")
	private Integer returnReceiptCopy;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	private boolean inserted;
	
	public SalesMaintenances() {
		// SalesMaintenances
	}

	/**
	 * @param caseloadId
	 *            caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * return thecaseloadId
	 */
	public String getCaseloadId() {
		return this.caseloadId;
	}

	/**
	 * @param stockLocId
	 *            stockLocId to set
	 */
	public void setStockLocId(final String stockLocId) {
		this.stockLocId = stockLocId;
	}

	/**
	 * return thestockLocId
	 */
	public String getStockLocId() {
		return this.stockLocId;
	}

	/**
	 * @param passQuantityFieldFlag
	 *            passQuantityFieldFlag to set
	 */
	public void setPassQuantityFieldFlag(final String passQuantityFieldFlag) {
		this.passQuantityFieldFlag = passQuantityFieldFlag;
	}

	/**
	 * return thepassQuantityFieldFlag
	 */
	public String getPassQuantityFieldFlag() {
		return this.passQuantityFieldFlag;
	}

	/**
	 * @param groupLikeItemFlag
	 *            groupLikeItemFlag to set
	 */
	public void setGroupLikeItemFlag(final String groupLikeItemFlag) {
		this.groupLikeItemFlag = groupLikeItemFlag;
	}

	/**
	 * return thegroupLikeItemFlag
	 */
	public String getGroupLikeItemFlag() {
		return this.groupLikeItemFlag;
	}

	/**
	 * @param soundAlertFlag
	 *            soundAlertFlag to set
	 */
	public void setSoundAlertFlag(final String soundAlertFlag) {
		this.soundAlertFlag = soundAlertFlag;
	}

	/**
	 * return thesoundAlertFlag
	 */
	public String getSoundAlertFlag() {
		return this.soundAlertFlag;
	}

	/**
	 * @param userAcknowledgeFlag
	 *            userAcknowledgeFlag to set
	 */
	public void setUserAcknowledgeFlag(final String userAcknowledgeFlag) {
		this.userAcknowledgeFlag = userAcknowledgeFlag;
	}

	/**
	 * return theuserAcknowledgeFlag
	 */
	public String getUserAcknowledgeFlag() {
		return this.userAcknowledgeFlag;
	}

	/**
	 * @param exceptionRecordedFlag
	 *            exceptionRecordedFlag to set
	 */
	public void setExceptionRecordedFlag(final String exceptionRecordedFlag) {
		this.exceptionRecordedFlag = exceptionRecordedFlag;
	}

	/**
	 * return theexceptionRecordedFlag
	 */
	public String getExceptionRecordedFlag() {
		return this.exceptionRecordedFlag;
	}

	/**
	 * @param autoSaleReceiptFlag
	 *            autoSaleReceiptFlag to set
	 */
	public void setAutoSaleReceiptFlag(final String autoSaleReceiptFlag) {
		this.autoSaleReceiptFlag = autoSaleReceiptFlag;
	}

	/**
	 * return theautoSaleReceiptFlag
	 */
	public String getAutoSaleReceiptFlag() {
		return this.autoSaleReceiptFlag;
	}

	/**
	 * @param saleReceiptName
	 *            saleReceiptName to set
	 */
	public void setSaleReceiptName(final String saleReceiptName) {
		this.saleReceiptName = saleReceiptName;
	}

	/**
	 * return thesaleReceiptName
	 */
	public String getSaleReceiptName() {
		return this.saleReceiptName;
	}

	/**
	 * @param saleReceiptCopy
	 *            saleReceiptCopy to set
	 */
	public void setSaleReceiptCopy(Integer saleReceiptCopy) {
		this.saleReceiptCopy = saleReceiptCopy;
	}

	/**
	 * return thesaleReceiptCopy
	 */
	public Integer getSaleReceiptCopy() {
		return this.saleReceiptCopy;
	}

	/**
	 * @param displayReceiptCommentFlag
	 *            displayReceiptCommentFlag to set
	 */
	public void setDisplayReceiptCommentFlag(final String displayReceiptCommentFlag) {
		this.displayReceiptCommentFlag = displayReceiptCommentFlag;
	}

	/**
	 * return thedisplayReceiptCommentFlag
	 */
	public String getDisplayReceiptCommentFlag() {
		return this.displayReceiptCommentFlag;
	}

	/**
	 * @param printReceiptBalanceFlag
	 *            printReceiptBalanceFlag to set
	 */
	public void setPrintReceiptBalanceFlag(final String printReceiptBalanceFlag) {
		this.printReceiptBalanceFlag = printReceiptBalanceFlag;
	}

	/**
	 * return theprintReceiptBalanceFlag
	 */
	public String getPrintReceiptBalanceFlag() {
		return this.printReceiptBalanceFlag;
	}

	/**
	 * @param autoReturnReceiptFlag
	 *            autoReturnReceiptFlag to set
	 */
	public void setAutoReturnReceiptFlag(final String autoReturnReceiptFlag) {
		this.autoReturnReceiptFlag = autoReturnReceiptFlag;
	}

	/**
	 * return theautoReturnReceiptFlag
	 */
	public String getAutoReturnReceiptFlag() {
		return this.autoReturnReceiptFlag;
	}

	/**
	 * @param returnReceiptName
	 *            returnReceiptName to set
	 */
	public void setReturnReceiptName(final String returnReceiptName) {
		this.returnReceiptName = returnReceiptName;
	}

	/**
	 * return thereturnReceiptName
	 */
	public String getReturnReceiptName() {
		return this.returnReceiptName;
	}

	/**
	 * @param returnReceiptCopy
	 *            returnReceiptCopy to set
	 */
	public void setReturnReceiptCopy(final Integer returnReceiptCopy) {
		this.returnReceiptCopy = returnReceiptCopy;
	}

	/**
	 * return thereturnReceiptCopy
	 */
	public Integer getReturnReceiptCopy() {
		return this.returnReceiptCopy;
	}

	/**
	 * @param createDatetime
	 *            createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * return thecreateDatetime
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * @param createUserId
	 *            createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return thecreateUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param modifyDatetime
	 *            modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * return themodifyDatetime
	 */
	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return themodifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @param sealFlag
	 *            sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return thesealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
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

}