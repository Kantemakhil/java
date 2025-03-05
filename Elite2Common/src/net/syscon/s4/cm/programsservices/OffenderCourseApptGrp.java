package net.syscon.s4.cm.programsservices;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderCourseApptGrp implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty( "offenderCourseApptGrpId") 
	  private long offenderCourseApptGrpId;
	@JsonProperty( "createDatetime") 
	  private Date createDatetime;
	@JsonProperty( "createUserId") 
	  private String createUserId;
	@JsonProperty( "endDate") 
	  private Date endDate;
	@JsonProperty( "holidayFlag") 
	  private String holidayFlag;
	@JsonProperty( "modifyDatetime") 
	  private Date modifyDatetime;
	@JsonProperty( "modifyUserId") 
	  private String modifyUserId;
	@JsonProperty( "noOfWeek") 
	  private BigDecimal noOfWeek;
	@JsonProperty( "offPrgrefId") 
	  private BigDecimal offPrgrefId;
	@JsonProperty( "sealFlag") 
	  private String sealFlag;
	@JsonProperty( "startDate") 
	  private Date startDate;
	//bi-directional many-to-one association to OffenderCourseApptRule
	//private List<OffenderCourseApptRule> offenderCourseApptRules;

	public OffenderCourseApptGrp() {
	}

	public long getOffenderCourseApptGrpId() {
		return this.offenderCourseApptGrpId;
	}

	public void setOffenderCourseApptGrpId(long offenderCourseApptGrpId) {
		this.offenderCourseApptGrpId = offenderCourseApptGrpId;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getHolidayFlag() {
		return this.holidayFlag;
	}

	public void setHolidayFlag(String holidayFlag) {
		this.holidayFlag = holidayFlag;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public BigDecimal getNoOfWeek() {
		return this.noOfWeek;
	}

	public void setNoOfWeek(BigDecimal noOfWeek) {
		this.noOfWeek = noOfWeek;
	}

	public BigDecimal getOffPrgrefId() {
		return this.offPrgrefId;
	}

	public void setOffPrgrefId(BigDecimal offPrgrefId) {
		this.offPrgrefId = offPrgrefId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
//
//	public List<OffenderCourseApptRule> getOffenderCourseApptRules() {
//		return this.offenderCourseApptRules;
//	}
//
//	public void setOffenderCourseApptRules(List<OffenderCourseApptRule> offenderCourseApptRules) {
//		this.offenderCourseApptRules = offenderCourseApptRules;
//	}
//
//	public OffenderCourseApptRule addOffenderCourseApptRule(OffenderCourseApptRule offenderCourseApptRule) {
//		getOffenderCourseApptRules().add(offenderCourseApptRule);
//		offenderCourseApptRule.setOffenderCourseApptGrp(this);
//
//		return offenderCourseApptRule;
//	}
//
//	public OffenderCourseApptRule removeOffenderCourseApptRule(OffenderCourseApptRule offenderCourseApptRule) {
//		getOffenderCourseApptRules().remove(offenderCourseApptRule);
//		offenderCourseApptRule.setOffenderCourseApptGrp(null);
//
//		return offenderCourseApptRule;
//	}

}



