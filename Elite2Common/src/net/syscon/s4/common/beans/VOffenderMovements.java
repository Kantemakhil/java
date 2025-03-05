package net.syscon.s4.common.beans;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VOffenderMovements {
	
	@JsonProperty( "agyLocId") 
	  private String agyLocId;
	@JsonProperty( "assignment") 
	  private String assignment;
	@JsonProperty( "commentText") 
	  private String commentText;
	@JsonProperty( "movementDate") 
	  private Object movementDate;
	@JsonProperty( "movementTime") 
	  private Object movementTime;
	@JsonProperty( "movementType") 
	  private String movementType;
	@JsonProperty( "offenderBookId") 
	  private BigDecimal offenderBookId;
	public String getAgyLocId() {
		return agyLocId;
	}
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}
	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public Object getMovementDate() {
		return movementDate;
	}
	public void setMovementDate(Object movementDate) {
		this.movementDate = movementDate;
	}
	public Object getMovementTime() {
		return movementTime;
	}
	public void setMovementTime(Object movementTime) {
		this.movementTime = movementTime;
	}
	public String getMovementType() {
		return movementType;
	}
	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	
}
