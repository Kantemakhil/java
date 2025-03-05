package net.syscon.s4.inst.careinplacement.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffObsAddCheckDetails extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("offenderBookId")
	private long offenderBookId;
	
	@JsonProperty("obsPeriodId")
	private long obsPeriodId;
	
	@JsonProperty("checkId")
	private long checkId;	
	
	@JsonProperty("detailType")
	private String detailType;
	
	@JsonProperty("detail")
	private String detail;	
	
	@JsonProperty("detailDate")
	private Date detailDate;
	
	@JsonProperty("reportedUser")
	private String reportedUser;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	public OffObsAddCheckDetails() {
		
	}

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getObsPeriodId() {
		return obsPeriodId;
	}

	public void setObsPeriodId(final long obsPeriodId) {
		this.obsPeriodId = obsPeriodId;
	}

	public long getCheckId() {
		return checkId;
	}

	public void setCheckId(final long checkId) {
		this.checkId = checkId;
	}

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(final String detailType) {
		this.detailType = detailType;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(final String detail) {
		this.detail = detail;
	}

	public Date getDetailDate() {
		return detailDate;
	}

	public void setDetailDate(final Date detailDate) {
		this.detailDate = detailDate;
	}

	public String getReportedUser() {
		return reportedUser;
	}

	public void setReportedUser(final String reportedUser) {
		this.reportedUser = reportedUser;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
