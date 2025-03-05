package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffAllowPayDetails extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("detailId")
	private Integer detailId;
	@JsonProperty("offenderBookId")
    private BigDecimal offenderBookId;
	@JsonProperty("offAllowanceId")
	private BigDecimal offAllowanceId;
	@JsonProperty("offAllowanceDay")
	private Date offAllowanceDay;
	@JsonProperty("allowanceType")
	private String allowanceType;
	@JsonProperty("versionNo")
	private Integer versionNo;
	@JsonProperty("allowanceVersionUnit")
	private String allowanceVersionUnit;
	@JsonProperty("allowanceVersionMaxUnit")
	private Integer allowanceVersionMaxUnit;
	@JsonProperty("allowanceVersionRate")
	private Integer allowanceVersionRate;
	@JsonProperty("payActualRate")
	private Integer payActualRate;
	@JsonProperty("payActualAmount")
	private Integer payActualAmount;
	@JsonProperty("batchId")
	private Integer batchId;
	@JsonProperty("payFlag")
	private String payFlag;
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
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("paySystemRate")
	private BigDecimal paySystemRate;
	
	@JsonProperty("preAllowanceVersionRate")
	private Integer preAllowanceVersionRate;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("commentText")
	private String hiddenCommentText;
	
	public Integer getDetailId() {
		return detailId;
	}
	public Integer getPreAllowanceVersionRate() {
		return preAllowanceVersionRate;
	}
	public void setPreAllowanceVersionRate(Integer preAllowanceVersionRate) {
		this.preAllowanceVersionRate = preAllowanceVersionRate;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public BigDecimal getOffAllowanceId() {
		return offAllowanceId;
	}
	public void setOffAllowanceId(BigDecimal offAllowanceId) {
		this.offAllowanceId = offAllowanceId;
	}
	public Date getOffAllowanceDay() {
		return offAllowanceDay;
	}
	public void setOffAllowanceDay(Date offAllowanceDay) {
		this.offAllowanceDay = offAllowanceDay;
	}
	public String getAllowanceType() {
		return allowanceType;
	}
	public void setAllowanceType(String allowanceType) {
		this.allowanceType = allowanceType;
	}
	public Integer getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}
	public String getAllowanceVersionUnit() {
		return allowanceVersionUnit;
	}
	public void setAllowanceVersionUnit(String allowanceVersionUnit) {
		this.allowanceVersionUnit = allowanceVersionUnit;
	}
	public Integer getAllowanceVersionMaxUnit() {
		return allowanceVersionMaxUnit;
	}
	public void setAllowanceVersionMaxUnit(Integer allowanceVersionMaxUnit) {
		this.allowanceVersionMaxUnit = allowanceVersionMaxUnit;
	}
	public Integer getAllowanceVersionRate() {
		return allowanceVersionRate;
	}
	public void setAllowanceVersionRate(Integer allowanceVersionRate) {
		this.allowanceVersionRate = allowanceVersionRate;
	}
	public Integer getPayActualRate() {
		return payActualRate;
	}
	public void setPayActualRate(Integer payActualRate) {
		this.payActualRate = payActualRate;
	}
	public Integer getPayActualAmount() {
		return payActualAmount;
	}
	public void setPayActualAmount(Integer payActualAmount) {
		this.payActualAmount = payActualAmount;
	}
	public Integer getBatchId() {
		return batchId;
	}
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	public String getPayFlag() {
		return payFlag;
	}
	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
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
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public BigDecimal getPaySystemRate() {
		return paySystemRate;
	}
	public void setPaySystemRate(BigDecimal paySystemRate) {
		this.paySystemRate = paySystemRate;
	}
	
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	public String getHiddenCommentText() {
		return hiddenCommentText;
	}
	public void setHiddenCommentText(String hiddenCommentText) {
		this.hiddenCommentText = hiddenCommentText;
	}
}
