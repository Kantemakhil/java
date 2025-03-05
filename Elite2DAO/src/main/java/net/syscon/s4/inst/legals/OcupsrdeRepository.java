package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.inst.legals.beans.AssignReport;
import net.syscon.s4.inst.legals.beans.CourtReport;
import net.syscon.s4.inst.legals.beans.OrderType;
import net.syscon.s4.inst.legals.beans.StaffDetails;
import net.syscon.s4.inst.legals.beans.TeamResponsible;

public interface OcupsrdeRepository {
	
	List<CourtReport> populateReport(Integer eventId);

	List<OrderType> populateReportType();

	List<AssignReport> populateAssignReport(CourtReport courtReport);

	Object setPostQueryReportType(String reportType);

	Object setPostQueryAgyDesc(String agyLocId);

	Object setPostQueryCourtSeriousnessLevel(String courtSeriousnessLevel);

	Object setPostQueryReportstatus(String reportStatus);

	List<OrderType> populateFunctionType();

	Integer insertNewCourtReport(List<CourtReport> insertedRecord);

	Integer updateCourtReport(List<CourtReport> updatedList);

	Integer deleteCourtReport(List<CourtReport> deletedList);

	Integer getPreInsertOrderId();

	Boolean isReportExist(CourtReport courtReport);

	Integer create_team_assign_hty_new(CourtReport courtReportAssign);

	String getStatusDesc(String reportStatus);

	List<OrderType> populateAreaType();

	List<OrderType> populateArea(String areaType);
	
	List<TeamResponsible> populateTeamResponsible(String areaCode, String functionType);
	
	List<StaffDetails> populateStaffDetails(Integer teamId);

	List<TeamResponsible> populateStaffLovDetails(Integer teamId);
	
    Integer getStaffId(Integer teamMemberId);
	
	Integer getWorkId();
	
	}
