package net.syscon.s4.inst.legals.legalorders;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class EepbiOutComeData extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@JsonProperty("fileSeq")
	private long fileSeq;
	@JsonProperty("lineNo")
	private long lineNo;
	@JsonProperty("ocSeq")
	private long ocSeq	;
	@JsonProperty("ocNo")
	private long ocNo	;
	@JsonProperty("chargeRefNo")
	private long chargeRefNo;
	@JsonProperty("offSeqNo")
	private long offSeqNo	;
	@JsonProperty("offRefNo")
	private long offRefNo	;
	@JsonProperty("ownerFlag")
	private long ownerFlag;
	@JsonProperty("diCode")
	private long diCode;
	@JsonProperty("data")
	private String data	;
	@JsonProperty("processedFlag")
	private String processedFlag	;
	@JsonProperty("offenderBookId")
	private long offenderBookId;
	@JsonProperty("sentenceSeq")
	private long sentenceSeq	;
	public long getFileSeq() {
		return fileSeq;
	}
	public void setFileSeq(long fileSeq) {
		this.fileSeq = fileSeq;
	}
	public long getLineNo() {
		return lineNo;
	}
	public void setLineNo(long lineNo) {
		this.lineNo = lineNo;
	}
	public long getOcSeq() {
		return ocSeq;
	}
	public void setOcSeq(long ocSeq) {
		this.ocSeq = ocSeq;
	}
	public long getOcNo() {
		return ocNo;
	}
	public void setOcNo(long ocNo) {
		this.ocNo = ocNo;
	}
	public long getChargeRefNo() {
		return chargeRefNo;
	}
	public void setChargeRefNo(long chargeRefNo) {
		this.chargeRefNo = chargeRefNo;
	}
	public long getOffSeqNo() {
		return offSeqNo;
	}
	public void setOffSeqNo(long offSeqNo) {
		this.offSeqNo = offSeqNo;
	}
	public long getOffRefNo() {
		return offRefNo;
	}
	public void setOffRefNo(long offRefNo) {
		this.offRefNo = offRefNo;
	}
	public long getOwnerFlag() {
		return ownerFlag;
	}
	public void setOwnerFlag(long ownerFlag) {
		this.ownerFlag = ownerFlag;
	}
	public long getDiCode() {
		return diCode;
	}
	public void setDiCode(long diCode) {
		this.diCode = diCode;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getProcessedFlag() {
		return processedFlag;
	}
	public void setProcessedFlag(String processedFlag) {
		this.processedFlag = processedFlag;
	}
	public long getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public long getSentenceSeq() {
		return sentenceSeq;
	}
	public void setSentenceSeq(long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	
	
}
