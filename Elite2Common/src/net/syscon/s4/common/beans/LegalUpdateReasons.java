package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the LEGAL_UPDATE_REASONS database table.
 * 
 */
public class LegalUpdateReasons implements Serializable {
	private static final long serialVersionUID = 1L;

	private String updateReasonCode;

	private String activeFlag;

	private String activeType;

	private Object createDatetime;

	private String createUserId;

	private String description;

	private Object effectiveDate;

	private Object expiryDate;

	private BigDecimal listSeq;

	private Object modifyDatetime;

	private String modifyUserId;

	private String reasonCategory;

	private String sealFlag;
	private String code;
	

	//bi-directional many-to-one association to LegalUpdateUsage
	//private List<LegalUpdateUsage> legalUpdateUsages;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LegalUpdateReasons() {
	}

	public String getUpdateReasonCode() {
		return this.updateReasonCode;
	}

	public void setUpdateReasonCode(String updateReasonCode) {
		this.updateReasonCode = updateReasonCode;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getActiveType() {
		return this.activeType;
	}

	public void setActiveType(String activeType) {
		this.activeType = activeType;
	}

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Object createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Object effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Object getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Object expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Object modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getReasonCategory() {
		return this.reasonCategory;
	}

	public void setReasonCategory(String reasonCategory) {
		this.reasonCategory = reasonCategory;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/*public List<LegalUpdateUsage> getLegalUpdateUsages() {
		return this.legalUpdateUsages;
	}

	public void setLegalUpdateUsages(List<LegalUpdateUsage> legalUpdateUsages) {
		this.legalUpdateUsages = legalUpdateUsages;
	}

	public LegalUpdateUsage addLegalUpdateUsage(LegalUpdateUsage legalUpdateUsage) {
		getLegalUpdateUsages().add(legalUpdateUsage);
		legalUpdateUsage.setLegalUpdateReason(this);

		return legalUpdateUsage;
	}

	public LegalUpdateUsage removeLegalUpdateUsage(LegalUpdateUsage legalUpdateUsage) {
		getLegalUpdateUsages().remove(legalUpdateUsage);
		legalUpdateUsage.setLegalUpdateReason(null);

		return legalUpdateUsage;
	}*/

}
