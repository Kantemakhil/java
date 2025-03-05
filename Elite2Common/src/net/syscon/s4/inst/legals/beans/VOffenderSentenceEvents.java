package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class VOffenderSentenceEvents  extends BaseModel implements Serializable  {
		private static final long serialVersionUID = 1L;
		
		
		private BigDecimal programId;
		private BigDecimal eventId	;
		private Date eventDate	;
		private Date startTime	;
		private Date endTime	;
		private Date inTime	;
		private Date outTime	;
		private String eventClass;
		private String eventType	;
		private String eventTypeDesc;
		private String eventSubType	;
		private String eventSubTypeDesc;
		private BigDecimal crsActyId	;
		private String eventStatus	;
		private String eventOutcome;
		private String eventOutcomeDesc;
		private String commentText	;
		private String agyLocId	;
		private String agyLocDesc	;
		private BigDecimal offenderBookId	;
		private BigDecimal sentenceSeq	;
		private BigDecimal offenderSentConditionId	;
		private String recordSource	;
		private String location	;
		private String condition;
		
		
		public BigDecimal getProgramId() {
			return programId;
		}
		public void setProgramId(BigDecimal programId) {
			this.programId = programId;
		}
		public BigDecimal getEventId() {
			return eventId;
		}
		public void setEventId(BigDecimal eventId) {
			this.eventId = eventId;
		}
		public Date getEventDate() {
			return eventDate;
		}
		public void setEventDate(Date eventDate) {
			this.eventDate = eventDate;
		}
		public Date getStartTime() {
			return startTime;
		}
		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}
		public Date getEndTime() {
			return endTime;
		}
		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
		public Date getInTime() {
			return inTime;
		}
		public void setInTime(Date inTime) {
			this.inTime = inTime;
		}
		public Date getOutTime() {
			return outTime;
		}
		public void setOutTime(Date outTime) {
			this.outTime = outTime;
		}
		public String getEventClass() {
			return eventClass;
		}
		public void setEventClass(String eventClass) {
			this.eventClass = eventClass;
		}
		public String getEventType() {
			return eventType;
		}
		public void setEventType(String eventType) {
			this.eventType = eventType;
		}
		public String getEventTypeDesc() {
			return eventTypeDesc;
		}
		public void setEventTypeDesc(String eventTypeDesc) {
			this.eventTypeDesc = eventTypeDesc;
		}
		public String getEventSubType() {
			return eventSubType;
		}
		public void setEventSubType(String eventSubType) {
			this.eventSubType = eventSubType;
		}
		public String getEventSubTypeDesc() {
			return eventSubTypeDesc;
		}
		public void setEventSubTypeDesc(String eventSubTypeDesc) {
			this.eventSubTypeDesc = eventSubTypeDesc;
		}
		public BigDecimal getCrsActyId() {
			return crsActyId;
		}
		public void setCrsActyId(BigDecimal crsActyId) {
			this.crsActyId = crsActyId;
		}
		public String getEventStatus() {
			return eventStatus;
		}
		public void setEventStatus(String eventStatus) {
			this.eventStatus = eventStatus;
		}
		public String getEventOutcome() {
			return eventOutcome;
		}
		public void setEventOutcome(String eventOutcome) {
			this.eventOutcome = eventOutcome;
		}
		public String getEventOutcomeDesc() {
			return eventOutcomeDesc;
		}
		public void setEventOutcomeDesc(String eventOutcomeDesc) {
			this.eventOutcomeDesc = eventOutcomeDesc;
		}
		public String getCommentText() {
			return commentText;
		}
		public void setCommentText(String commentText) {
			this.commentText = commentText;
		}
		public String getAgyLocId() {
			return agyLocId;
		}
		public void setAgyLocId(String agyLocId) {
			this.agyLocId = agyLocId;
		}
		public String getAgyLocDesc() {
			return agyLocDesc;
		}
		public void setAgyLocDesc(String agyLocDesc) {
			this.agyLocDesc = agyLocDesc;
		}
		public BigDecimal getOffenderBookId() {
			return offenderBookId;
		}
		public void setOffenderBookId(BigDecimal offenderBookId) {
			this.offenderBookId = offenderBookId;
		}
		public BigDecimal getSentenceSeq() {
			return sentenceSeq;
		}
		public void setSentenceSeq(BigDecimal sentenceSeq) {
			this.sentenceSeq = sentenceSeq;
		}
		public BigDecimal getOffenderSentConditionId() {
			return offenderSentConditionId;
		}
		public void setOffenderSentConditionId(BigDecimal offenderSentConditionId) {
			this.offenderSentConditionId = offenderSentConditionId;
		}
		public String getRecordSource() {
			return recordSource;
		}
		public void setRecordSource(String recordSource) {
			this.recordSource = recordSource;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getCondition() {
			return condition;
		}
		public void setCondition(String condition) {
			this.condition = condition;
		}
		
		

}
