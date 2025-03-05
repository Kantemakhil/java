package net.syscon.s4.inst.booking.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the OFFENDER_SUBSTANCE_DETAILS database table.
 * 
 */
public class OffenderSubstanceDetails extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String caseloadType;

	private String commentText;

	private Date createDatetime;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal rootOffenderId;

	private String sealFlag;

	private String sourceOfInfo;

	private String useLevel;

	private String usePeriod;
	private String nbtCaseloadType;

	private OffenderSubstanceUses offenderSubstanceUs;

	private long offenderBookId;

	private String substanceType;

	private long seqNumber;

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getSubstanceType() {
		return substanceType;
	}

	public void setSubstanceType(final String substanceType) {
		this.substanceType = substanceType;
	}

	public long getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(final long seqNumber) {
		this.seqNumber = seqNumber;
	}

	public OffenderSubstanceDetails() {
	}

	public String getCaseloadType() {
		return this.caseloadType;
	}

	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getNbtCaseloadType() {
		return nbtCaseloadType;
	}

	public void setNbtCaseloadType(final String nbtCaseloadType) {
		this.nbtCaseloadType = nbtCaseloadType;
	}

	public BigDecimal getRootOffenderId() {
		return this.rootOffenderId;
	}

	public void setRootOffenderId(final BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getSourceOfInfo() {
		return this.sourceOfInfo;
	}

	public void setSourceOfInfo(final String sourceOfInfo) {
		this.sourceOfInfo = sourceOfInfo;
	}

	public String getUseLevel() {
		return this.useLevel;
	}

	public void setUseLevel(final String useLevel) {
		this.useLevel = useLevel;
	}

	public String getUsePeriod() {
		return this.usePeriod;
	}

	public OffenderSubstanceUses getOffenderSubstanceUs() {
		return offenderSubstanceUs;
	}

	public void setOffenderSubstanceUs(final OffenderSubstanceUses offenderSubstanceUs) {
		this.offenderSubstanceUs = offenderSubstanceUs;
	}

	public void setUsePeriod(final String usePeriod) {
		this.usePeriod = usePeriod;
	}

}
