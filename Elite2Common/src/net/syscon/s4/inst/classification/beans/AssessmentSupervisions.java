package net.syscon.s4.inst.classification.beans;

import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;



/**
 * The persistent class for the ASSESSMENT_SUPERVISIONS database table.
 * 
 */
public class AssessmentSupervisions extends BaseModel  {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private Date createDatetime;

	private String createUserId;

	private Date effectiveDate;

	private Date expiryDate;

	private BigDecimal listSeq;

	private BigDecimal maxScore;

	private BigDecimal miniScore;

	private Date  modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	private String updateAllowedFlag;
	
	private long assessmentId;
	
	private String supervisionLevelType;
	
	private String superVsnCode;
	
	private long parentAssessmentId;
	
	public String getSuperVsnCode() {
		return superVsnCode;
	}

	public void setSuperVsnCode(String superVsnCode) {
		this.superVsnCode = superVsnCode;
	}

	private BigDecimal score;

	//bi-directional many-to-one association to Assessment

	public AssessmentSupervisions() {
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	
	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public BigDecimal getMaxScore() {
		return this.maxScore;
	}

	public void setMaxScore(BigDecimal maxScore) {
		this.maxScore = maxScore;
	}

	public BigDecimal getMiniScore() {
		return this.miniScore;
	}

	public void setMiniScore(BigDecimal miniScore) {
		this.miniScore = miniScore;
	}

	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}

	

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getUpdateAllowedFlag() {
		return this.updateAllowedFlag;
	}

	public void setUpdateAllowedFlag(String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}

	public long getAssessmentId() {
		return this.assessmentId;
	}
	public void setAssessmentId(long assessmentId) {
		this.assessmentId = assessmentId;
	}
	public String getSupervisionLevelType() {
		return this.supervisionLevelType;
	}
	public void setSupervisionLevelType(String supervisionLevelType) {
		this.supervisionLevelType = supervisionLevelType;
	}

	/**
	 * @return the score
	 */
	public BigDecimal getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public long getParentAssessmentId() {
		return parentAssessmentId;
	}

	public void setParentAssessmentId(long parentAssessmentId) {
		this.parentAssessmentId = parentAssessmentId;
	}


}
