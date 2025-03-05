package net.syscon.s4.inst.legals.beans;

import java.util.Date;

public class OffenderSentenceAdjustment {
    private Long offenderOrderAdjustId;
    private String orderAdjustCode;
    private Long offenderBookId;
    private Long objectId;
    private String objectType;
    private Date adjustDate;
    private Integer adjustDays;
    private Integer adjustMonths;
    private String adjustStatus;
    private Date adjustFromDate;
    private Date adjustToDate;
    private String commentText;
    private String sealFlag;
    private Date createDatetime;
    private String createUserId;
    private Date modifyDatetime;
    private String modifyUserId;
    
    //used for utility
    private String recordFlag;
    
    private String adjustCode;
    
    private String usageCode;
    
	private String debitCreditCode;
	
	private Double rdYears;
	private Double rdMonths;
	private Double rdWeeks;
	private Double rdDays;
	private String adjustCodeType;

    public Long getOffenderOrderAdjustId() {
        return offenderOrderAdjustId;
    }

    public void setOffenderOrderAdjustId(Long offenderOrderAdjustId) {
        this.offenderOrderAdjustId = offenderOrderAdjustId;
    }

    public String getOrderAdjustCode() {
        return orderAdjustCode;
    }

    public void setOrderAdjustCode(String orderAdjustCode) {
        this.orderAdjustCode = orderAdjustCode;
    }

    public Long getOffenderBookId() {
        return offenderBookId;
    }

    public void setOffenderBookId(Long offenderBookId) {
        this.offenderBookId = offenderBookId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Date getAdjustDate() {
        return adjustDate;
    }

    public void setAdjustDate(Date adjustDate) {
        this.adjustDate = adjustDate;
    }

    public Integer getAdjustDays() {
        return adjustDays;
    }

    public void setAdjustDays(Integer adjustDays) {
        this.adjustDays = adjustDays;
    }

    public Integer getAdjustMonths() {
        return adjustMonths;
    }

    public void setAdjustMonths(Integer adjustMonths) {
        this.adjustMonths = adjustMonths;
    }

    public String getAdjustStatus() {
        return adjustStatus;
    }

    public void setAdjustStatus(String adjustStatus) {
        this.adjustStatus = adjustStatus;
    }

    public Date getAdjustFromDate() {
        return adjustFromDate;
    }

    public void setAdjustFromDate(Date adjustFromDate) {
        this.adjustFromDate = adjustFromDate;
    }

    public Date getAdjustToDate() {
        return adjustToDate;
    }

    public void setAdjustToDate(Date adjustToDate) {
        this.adjustToDate = adjustToDate;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getSealFlag() {
        return sealFlag;
    }

    public void setSealFlag(String sealFlag) {
        this.sealFlag = sealFlag;
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

    public String getRecordFlag() {
        return recordFlag;
    }

    public void setRecordFlag(String recordFlag) {
        this.recordFlag = recordFlag;
    }

	public String getAdjustCode() {
		return adjustCode;
	}

	public void setAdjustCode(String adjustCode) {
		this.adjustCode = adjustCode;
	}

	public String getUsageCode() {
		return usageCode;
	}

	public void setUsageCode(String usageCode) {
		this.usageCode = usageCode;
	}
    
	
	public String getDebitCreditCode() {
		return debitCreditCode;
	}

	public void setDebitCreditCode(String debitCreditCode) {
		this.debitCreditCode = debitCreditCode;
	}

	public Double getRdYears() {
		return rdYears;
	}

	public void setRdYears(Double rdYears) {
		this.rdYears = rdYears;
	}

	public Double getRdMonths() {
		return rdMonths;
	}

	public void setRdMonths(Double rdMonths) {
		this.rdMonths = rdMonths;
	}

	public Double getRdWeeks() {
		return rdWeeks;
	}

	public void setRdWeeks(Double rdWeeks) {
		this.rdWeeks = rdWeeks;
	}

	public Double getRdDays() {
		return rdDays;
	}

	public void setRdDays(Double rdDays) {
		this.rdDays = rdDays;
	}

	public String getAdjustCodeType() {
		return adjustCodeType;
	}

	public void setAdjustCodeType(String adjustCodeType) {
		this.adjustCodeType = adjustCodeType;
	}

}
