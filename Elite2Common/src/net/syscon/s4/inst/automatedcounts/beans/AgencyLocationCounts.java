package net.syscon.s4.inst.automatedcounts.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

/**
 * AgencyLocationCounts
 * 
 */
public class AgencyLocationCounts extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal actualCount;

	private String commentText;

	private String conductedByUserid;

	private Date conductedDatetime;

	private Date createDatetime;

	private String createUserId;

	private Date dateSubmitted;

	private String discrepRsnCode;

	private String enteredByUserid;

	private Date modifyDatetime;

	private String modifyUserId;

	private String rcntConductedBy;

	private Date rcntDatetime;

	private String rcntInProgressFlag;

	private String recountRsnCode;

	private BigDecimal recountTotal;

	private BigDecimal reportedCount;

	private Date rsnCodeDatetime;

	private String rsnCodeUserid;

	private String sealFlag;

	private Date verifiedDatetime;

	private String verifiedUserId;

	private Long reportingLocId;

	private Long countTypeId;

	private Long agySeq;
	
	private Integer livingUnitId;
	
	private Integer livingUnitId1;
	
	private Integer livingUnitId2;
	
	private Integer livingUnitId3;

	private String agyLocId;
	
	private Integer internalLocationId;

	public AgencyLocationCounts() {
		// AgencyLocationCounts
	}

	public BigDecimal getActualCount() {
		return this.actualCount;
	}

	public void setActualCount(final BigDecimal actualCount) {
		this.actualCount = actualCount;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public String getConductedByUserid() {
		return this.conductedByUserid;
	}

	public void setConductedByUserid(final String conductedByUserid) {
		this.conductedByUserid = conductedByUserid;
	}

	public Date getConductedDatetime() {
		return this.conductedDatetime;
	}

	public void setConductedDatetime(final Date conductedDatetime) {
		this.conductedDatetime = conductedDatetime;
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

	public Date getDateSubmitted() {
		return this.dateSubmitted;
	}

	public void setDateSubmitted(final Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public String getDiscrepRsnCode() {
		return this.discrepRsnCode;
	}

	public void setDiscrepRsnCode(final String discrepRsnCode) {
		this.discrepRsnCode = discrepRsnCode;
	}

	public String getEnteredByUserid() {
		return this.enteredByUserid;
	}

	public void setEnteredByUserid(final String enteredByUserid) {
		this.enteredByUserid = enteredByUserid;
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

	public String getRcntConductedBy() {
		return this.rcntConductedBy;
	}

	public void setRcntConductedBy(final String rcntConductedBy) {
		this.rcntConductedBy = rcntConductedBy;
	}

	public Date getRcntDatetime() {
		return this.rcntDatetime;
	}

	public void setRcntDatetime(final Date rcntDatetime) {
		this.rcntDatetime = rcntDatetime;
	}

	public String getRcntInProgressFlag() {
		return this.rcntInProgressFlag;
	}

	public void setRcntInProgressFlag(final String rcntInProgressFlag) {
		this.rcntInProgressFlag = rcntInProgressFlag;
	}

	public String getRecountRsnCode() {
		return this.recountRsnCode;
	}

	public void setRecountRsnCode(final String recountRsnCode) {
		this.recountRsnCode = recountRsnCode;
	}

	public BigDecimal getRecountTotal() {
		return this.recountTotal;
	}

	public void setRecountTotal(final BigDecimal recountTotal) {
		this.recountTotal = recountTotal;
	}

	public BigDecimal getReportedCount() {
		return this.reportedCount;
	}

	public void setReportedCount(final BigDecimal reportedCount) {
		this.reportedCount = reportedCount;
	}

	public Date getRsnCodeDatetime() {
		return this.rsnCodeDatetime;
	}

	public void setRsnCodeDatetime(final Date rsnCodeDatetime) {
		this.rsnCodeDatetime = rsnCodeDatetime;
	}

	public String getRsnCodeUserid() {
		return this.rsnCodeUserid;
	}

	public void setRsnCodeUserid(final String rsnCodeUserid) {
		this.rsnCodeUserid = rsnCodeUserid;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Date getVerifiedDatetime() {
		return this.verifiedDatetime;
	}

	public void setVerifiedDatetime(final Date verifiedDatetime) {
		this.verifiedDatetime = verifiedDatetime;
	}

	public String getVerifiedUserId() {
		return this.verifiedUserId;
	}

	public void setVerifiedUserId(final String verifiedUserId) {
		this.verifiedUserId = verifiedUserId;
	}

	public Long getReportingLocId() {
		return this.reportingLocId;
	}

	public void setReportingLocId(final Long reportingLocId) {
		this.reportingLocId = reportingLocId;
	}

	public Long getCountTypeId() {
		return this.countTypeId;
	}

	public void setCountTypeId(final Long countTypeId) {
		this.countTypeId = countTypeId;
	}

	public Long getAgySeq() {
		return this.agySeq;
	}

	public void setAgySeq(final Long agySeq) {
		this.agySeq = agySeq;
	}

	public Integer getLivingUnitId1() {
		return livingUnitId1;
	}

	public void setLivingUnitId1(Integer livingUnitId1) {
		this.livingUnitId1 = livingUnitId1;
	}

	public Integer getLivingUnitId2() {
		return livingUnitId2;
	}

	public void setLivingUnitId2(Integer livingUnitId2) {
		this.livingUnitId2 = livingUnitId2;
	}

	public Integer getLivingUnitId3() {
		return livingUnitId3;
	}

	public void setLivingUnitId3(Integer livingUnitId3) {
		this.livingUnitId3 = livingUnitId3;
	}
	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public Integer getLivingUnitId() {
		return livingUnitId;
	}

	public void setLivingUnitId(Integer livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	public Integer getInternalLocationId() {
		return internalLocationId;
	}

	public void setInternalLocationId(Integer internalLocationId) {
		this.internalLocationId = internalLocationId;
	}
	
}
