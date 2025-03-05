package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the ITEM_TRANSACTION_STATII database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemTransactionStatii implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max=32)
	private String createUserId;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("agencyIncidentId")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	@Size(max=32)
	private String modifyUserId;

	@JsonProperty("sealFlag")
	@Size(max=1)
	private String sealFlag;
	
	@JsonProperty("itemStatusFrom")
	@NotNull
	@Size(max=12)
	private String itemStatusFrom;

	@JsonProperty("itemStatusTo")
	@NotNull
	@Size(max=12)
	private String itemStatusTo;

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the listSeq
	 */
	public BigDecimal getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq the listSeq to set
	 */
	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the itemStatusFrom
	 */
	public String getItemStatusFrom() {
		return itemStatusFrom;
	}

	/**
	 * @param itemStatusFrom the itemStatusFrom to set
	 */
	public void setItemStatusFrom(String itemStatusFrom) {
		this.itemStatusFrom = itemStatusFrom;
	}

	/**
	 * @return the itemStatusTo
	 */
	public String getItemStatusTo() {
		return itemStatusTo;
	}

	/**
	 * @param itemStatusTo the itemStatusTo to set
	 */
	public void setItemStatusTo(String itemStatusTo) {
		this.itemStatusTo = itemStatusTo;
	}

	

}
