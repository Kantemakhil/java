package net.syscon.s4.inst.property.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
@XmlRootElement
public class Corporate extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("corporateId")
	@NotNull
	private long corporateId;

	@JsonProperty("accountTermCode")
	@Size(max = 60)
	private String accountTermCode;

	@JsonProperty("activeFlag")
	@Size(max = 1)
	@NotNull
	private String activeFlag;

	@JsonProperty("caseloadId")
	@Size(max = 6)
	private String caseloadId;

	@JsonProperty("commentText")
	@Size(max = 240)
	private String commentText;

	@JsonProperty("contactPersonName")
	@Size(max = 40)
	private String contactPersonName;

	@JsonProperty("corporateName")
	@Size(max = 40)
	private String corporateName;

	@JsonProperty("createDatetime")
	@NotNull
	private Object createDatetime;

	@JsonProperty("createUserId")
	@Size(max = 32)
	@NotNull
	private String createUserId;

	@JsonProperty("createdDate")
	@NotNull
	private Object createdDate;

	@JsonProperty("expiryDate")
	private Object expiryDate;

	@JsonProperty("feiNumber")
	@Size(max = 40)
	private String feiNumber;

	@JsonProperty("maximumPurchaseAmount")
	private BigDecimal maximumPurchaseAmount;

	@JsonProperty("memoText")
	@Size(max = 40)
	private String memoText;

	@JsonProperty("minimumPurchaseAmount")
	private BigDecimal minimumPurchaseAmount;

	@JsonProperty("modifyDatetime")
	private Object modifyDatetime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("shippingTermCode")
	@Size(max = 60)
	private String shippingTermCode;

	@JsonProperty("startDate")
	private Object startDate;

	@JsonProperty("suspendedDate")
	private Object suspendedDate;

	@JsonProperty("suspendedFlag")
	@Size(max = 1)
	@NotNull
	private String suspendedFlag;

	@JsonProperty("taxNo")
	@Size(max = 12)
	private String taxNo;

	@JsonProperty("updatedDate")
	private Object updatedDate;

	@JsonProperty("userId")
	@Size(max = 32)
	private String userId;
	
	public Corporate() {
		
		//Corporate
	}

	public long getCorporateId() {
		return this.corporateId;
	}

	public void setCorporateId(final long corporateId) {
		this.corporateId = corporateId;
	}

	public String getAccountTermCode() {
		return this.accountTermCode;
	}

	public void setAccountTermCode(final String accountTermCode) {
		this.accountTermCode = accountTermCode;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getCaseloadId() {
		return this.caseloadId;
	}

	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(final String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getCorporateName() {
		return this.corporateName;
	}

	public void setCorporateName(final String corporateName) {
		this.corporateName = corporateName;
	}

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Object createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Object getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(final Object createdDate) {
		this.createdDate = createdDate;
	}

	public Object getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(final Object expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getFeiNumber() {
		return this.feiNumber;
	}

	public void setFeiNumber(final String feiNumber) {
		this.feiNumber = feiNumber;
	}

	public BigDecimal getMaximumPurchaseAmount() {
		return this.maximumPurchaseAmount;
	}

	public void setMaximumPurchaseAmount(final BigDecimal maximumPurchaseAmount) {
		this.maximumPurchaseAmount = maximumPurchaseAmount;
	}

	public String getMemoText() {
		return this.memoText;
	}

	public void setMemoText(final String memoText) {
		this.memoText = memoText;
	}

	public BigDecimal getMinimumPurchaseAmount() {
		return this.minimumPurchaseAmount;
	}

	public void setMinimumPurchaseAmount(final BigDecimal minimumPurchaseAmount) {
		this.minimumPurchaseAmount = minimumPurchaseAmount;
	}

	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Object modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getShippingTermCode() {
		return this.shippingTermCode;
	}

	public void setShippingTermCode(final String shippingTermCode) {
		this.shippingTermCode = shippingTermCode;
	}

	public Object getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final Object startDate) {
		this.startDate = startDate;
	}

	public Object getSuspendedDate() {
		return this.suspendedDate;
	}

	public void setSuspendedDate(final Object suspendedDate) {
		this.suspendedDate = suspendedDate;
	}

	public String getSuspendedFlag() {
		return this.suspendedFlag;
	}

	public void setSuspendedFlag(final String suspendedFlag) {
		this.suspendedFlag = suspendedFlag;
	}

	public String getTaxNo() {
		return this.taxNo;
	}

	public void setTaxNo(final String taxNo) {
		this.taxNo = taxNo;
	}

	public Object getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(final Object updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}
}
