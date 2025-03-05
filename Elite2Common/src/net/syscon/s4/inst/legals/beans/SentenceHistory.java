package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SentenceHistory implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("date")
	private Date date;
	
	@JsonProperty("time")
	private String time;
	
	@JsonProperty("calculationReason")
	private String calculationReason;
	
	@JsonProperty("staffName")
	private String staffName;
	
	@JsonProperty("comment")
	private String comment;
	
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("staffId")
	private Integer staffId;
	
	@JsonProperty("offenderSentCalculationId")
	private Long offenderSentCalculationId;

	
	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the calculationReason
	 */
	public String getCalculationReason() {
		return calculationReason;
	}

	/**
	 * @param calculationReason the calculationReason to set
	 */
	public void setCalculationReason(String calculationReason) {
		this.calculationReason = calculationReason;
	}

	/**
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * @param staffName the staffName to set
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the staffId
	 */
	public Integer getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Long getOffenderSentCalculationId() {
		return offenderSentCalculationId;
	}

	public void setOffenderSentCalculationId(Long offenderSentCalculationId) {
		this.offenderSentCalculationId = offenderSentCalculationId;
	}

	
}
