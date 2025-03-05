package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.common.beans.HoldsCommitBean;
import net.syscon.s4.inst.legals.beans.AssignReport;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.legals.beans.CourtReport;
import net.syscon.s4.inst.legals.beans.CourtReportCommitBean;
import net.syscon.s4.inst.legals.beans.HoldStatus;
import net.syscon.s4.inst.legals.beans.OrderType;
import net.syscon.s4.inst.legals.beans.StaffDetails;
import net.syscon.s4.inst.legals.beans.TeamResponsible;

public interface OcupsrdeService {

	List<CourtReport> populateReport(Integer eventId);

	List<OrderType> populateReportType();

	List<AssignReport> populateAssignReport(CourtReport courtReport);

	List<OrderType> populateFunctionType();

	Integer insertUpdateDeleteCourtReport(CourtReportCommitBean courtReportCommitBean);

	Boolean isReportExist(CourtReport courtReport);

	Integer generateOrderId();

	String getStatusDesc(String reportstatus);

	List<OrderType> populateAreaType();

	List<OrderType> populateArea(String areaType);
	
	List<TeamResponsible> populateTeamResponsible(String areaCode, String functionType);

	List<StaffDetails> populateStaffDetails(Integer teamId);
	
	Integer create_team_assign_hty_new(CourtReport courtReportAssign);

	List<TeamResponsible> populateStaffLovDetails(Integer teamId);
	
}
