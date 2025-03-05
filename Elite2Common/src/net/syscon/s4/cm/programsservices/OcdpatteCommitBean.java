package net.syscon.s4.cm.programsservices;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;

public class OcdpatteCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("offCrsCommitBean")
	private OffenderCourseAttendancesCommitBean offCrsCommitBean;
	@JsonProperty("crsSchCommitBean")
	private CourseScheduleStaffsCommitBean crsSchCommitBean;
	@JsonProperty("deliveryDetailsModel")
	private CourseSchedules deliveryDetailsModel;
	
	public OffenderCourseAttendancesCommitBean getOffCrsCommitBean() {
		return offCrsCommitBean;
	}
	public void setOffCrsCommitBean(OffenderCourseAttendancesCommitBean offCrsCommitBean) {
		this.offCrsCommitBean = offCrsCommitBean;
	}
	public CourseScheduleStaffsCommitBean getCrsSchCommitBean() {
		return crsSchCommitBean;
	}
	public void setCrsSchCommitBean(CourseScheduleStaffsCommitBean crsSchCommitBean) {
		this.crsSchCommitBean = crsSchCommitBean;
	}
	public CourseSchedules getDeliveryDetailsModel() {
		return deliveryDetailsModel;
	}
	public void setDeliveryDetailsModel(CourseSchedules deliveryDetailsModel) {
		this.deliveryDetailsModel = deliveryDetailsModel;
	}
	
}	

