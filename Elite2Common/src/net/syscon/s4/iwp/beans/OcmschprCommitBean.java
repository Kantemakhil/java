package net.syscon.s4.iwp.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRulesCommitBean;
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OcmschprCommitBean extends BaseModel{
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("vacpscheduleCommitBean")
	private VAcpSchedulesCommitBean vacpscheduleCommitBean;
	@JsonProperty("crsschedulerulCommitBean")
    private CourseScheduleRulesCommitBean crsschedulerulCommitBean;
	@JsonProperty("crsActCommitBean")
	 private CourseActivitiesCommitBean crsActCommitBean;
	
	public CourseActivitiesCommitBean getCrsActCommitBean() {
		return crsActCommitBean;
	}
	public void setCrsActCommitBean(CourseActivitiesCommitBean crsActCommitBean) {
		this.crsActCommitBean = crsActCommitBean;
	}
	public VAcpSchedulesCommitBean getVacpscheduleCommitBean() {
		return vacpscheduleCommitBean;
	}
	public void setVacpscheduleCommitBean(VAcpSchedulesCommitBean vacpscheduleCommitBean) {
		this.vacpscheduleCommitBean = vacpscheduleCommitBean;
	}
	public CourseScheduleRulesCommitBean getCrsschedulerulCommitBean() {
		return crsschedulerulCommitBean;
	}
	public void setCrsschedulerulCommitBean(CourseScheduleRulesCommitBean crsschedulerulCommitBean) {
		this.crsschedulerulCommitBean = crsschedulerulCommitBean;
	}
	
	
	
	
	
}
