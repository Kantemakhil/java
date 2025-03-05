package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EliteViewLog extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("inputSearchResult")
	private List<VHeaderBlock> inputSearchResult;

	@JsonProperty("userId")
	private String userId;

	@JsonProperty("moduleName")
	private String moduleName;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("bookingNo")
	private String bookingNo;


	@JsonProperty("inputSearchParameters")
	private VHeaderBlock inputSearchParameters;
	
	@JsonProperty("auditTime")
	private Date auditTime;

	public List<VHeaderBlock> getInputSearchResult() {
		return inputSearchResult;
	}

	public void setInputSearchResult(List<VHeaderBlock> inputSearchResult) {
		this.inputSearchResult = inputSearchResult;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	public VHeaderBlock getInputSearchParameters() {
		return inputSearchParameters;
	}

	public void setInputSearchParameters(VHeaderBlock inputSearchParameters) {
		this.inputSearchParameters = inputSearchParameters;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	@Override
	public String toString() {
		return "EliteViewLog [inputSearchResult=" + inputSearchResult + ", userId=" + userId + ", moduleName="
				+ moduleName + ", offenderIdDisplay=" + offenderIdDisplay + ", bookingNo=" + bookingNo
				+ ", inputSearchParameters=" + inputSearchParameters + ", auditTime=" + auditTime + "]";
	}

}
