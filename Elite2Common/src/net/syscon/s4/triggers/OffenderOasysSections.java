package net.syscon.s4.triggers;

import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderOasysSections extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long offenderBookId;
	private Integer planSeq;
	private String sectionCode;
	private Integer rawScore;
	private Double summaryRatio;
	private Integer weightedScore;
	private String centrelineGroup;
	private Long version;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	private String sealFlag;
	private BigDecimal objectiveId;
	private Long actionSeq;

	public BigDecimal getObjectiveId() {
		return objectiveId;
	}

	public void setObjectiveId(BigDecimal objectiveId) {
		this.objectiveId = objectiveId;
	}

	public Long getActionSeq() {
		return actionSeq;
	}

	public void setActionSeq(Long actionSeq) {
		this.actionSeq = actionSeq;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Integer getPlanSeq() {
		return planSeq;
	}

	public void setPlanSeq(Integer planSeq) {
		this.planSeq = planSeq;
	}

	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public Integer getRawScore() {
		return rawScore;
	}

	public void setRawScore(Integer rawScore) {
		this.rawScore = rawScore;
	}

	public Double getSummaryRatio() {
		return summaryRatio;
	}

	public void setSummaryRatio(Double summaryRatio) {
		this.summaryRatio = summaryRatio;
	}

	public Integer getWeightedScore() {
		return weightedScore;
	}

	public void setWeightedScore(Integer weightedScore) {
		this.weightedScore = weightedScore;
	}

	public String getCentrelineGroup() {
		return centrelineGroup;
	}

	public void setCentrelineGroup(String centrelineGroup) {
		this.centrelineGroup = centrelineGroup;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
