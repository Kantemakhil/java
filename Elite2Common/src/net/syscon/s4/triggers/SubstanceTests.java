package net.syscon.s4.triggers;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class SubstanceTests extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long offenderBookId;
	private Integer sampleSeq;
	private Integer testSeq;
	private Date dateTested;
	private Integer testedBy;
	private Integer witness;
	private String results;
	private String disposition;
	private String commentText;
	private Date timeTested;
	private String subtanceTestType;
	private Integer testNumber;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	private String sealFlag;

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Integer getSampleSeq() {
		return sampleSeq;
	}

	public void setSampleSeq(Integer sampleSeq) {
		this.sampleSeq = sampleSeq;
	}

	public Integer getTestSeq() {
		return testSeq;
	}

	public void setTestSeq(Integer testSeq) {
		this.testSeq = testSeq;
	}

	public Date getDateTested() {
		return dateTested;
	}

	public void setDateTested(Date dateTested) {
		this.dateTested = dateTested;
	}

	public Integer getTestedBy() {
		return testedBy;
	}

	public void setTestedBy(Integer testedBy) {
		this.testedBy = testedBy;
	}

	public Integer getWitness() {
		return witness;
	}

	public void setWitness(Integer witness) {
		this.witness = witness;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public String getDisposition() {
		return disposition;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getTimeTested() {
		return timeTested;
	}

	public void setTimeTested(Date timeTested) {
		this.timeTested = timeTested;
	}

	public String getSubtanceTestType() {
		return subtanceTestType;
	}

	public void setSubtanceTestType(String subtanceTestType) {
		this.subtanceTestType = subtanceTestType;
	}

	public Integer getTestNumber() {
		return testNumber;
	}

	public void setTestNumber(Integer testNumber) {
		this.testNumber = testNumber;
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
