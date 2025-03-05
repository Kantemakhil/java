package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderPayrolEvent extends BaseModel implements Serializable {
    @NotBlank
    private Long offenderBookId;
    private Long paroleEventId;
    private Date eventDate;
    private String paroleEvent;
    private String comment;
    private Date createDatetime;
    private String createUserId;
    private Date modifyDatetime;
    private String modifyUserId;
    private String calcReason;
	private String sealFlag;

    public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	// used for utility
    private List<OffenderSentenceAdjustment> listOffenderSentenceAdjustment;
    // used for utility
    private String recordFlag;

    public Long getOffenderBookId() {
        return offenderBookId;
    }

    public void setOffenderBookId(Long offenderBookId) {
        this.offenderBookId = offenderBookId;
    }

    public Long getParoleEventId() {
        return paroleEventId;
    }

    public void setParoleEventId(Long paroleEventId) {
        this.paroleEventId = paroleEventId;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getParoleEvent() {
        return paroleEvent;
    }

    public void setParoleEvent(String paroleEvent) {
        this.paroleEvent = paroleEvent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public List<OffenderSentenceAdjustment> getListOffenderSentenceAdjustment() {
        return listOffenderSentenceAdjustment;
    }

    public void setListOffenderSentenceAdjustment(List<OffenderSentenceAdjustment> listOffenderSentenceAdjustment) {
        this.listOffenderSentenceAdjustment = listOffenderSentenceAdjustment;
    }

    public String getRecordFlag() {
        return recordFlag;
    }

    public void setRecordFlag(String recordFlag) {
        this.recordFlag = recordFlag;
    }

	public String getCalcReason() {
		return calcReason;
	}

	public void setCalcReason(String calcReason) {
		this.calcReason = calcReason;
	}

}
