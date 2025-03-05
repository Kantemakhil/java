package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.legals.beans.AssignReport;
import net.syscon.s4.inst.legals.beans.CourtReport;
import net.syscon.s4.inst.legals.beans.CourtReportCommitBean;
import net.syscon.s4.inst.legals.beans.OrderType;
import net.syscon.s4.inst.legals.beans.StaffDetails;
import net.syscon.s4.inst.legals.beans.TeamResponsible;



@EliteController
public class OcupsrdeController {
	
	@Autowired
	private OcupsrdeService ocupsrdeService;
	
	private static Logger logger = LogManager.getLogger(OcupsrdeController.class.getName());
	
	/***
	 * fetch data for court Report from Order table
	 * @param eventId {@link Integer}
	 * @return a list of the CourtReport {@link CourtReport} for the matched eventId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupsrde/populateReport", method = RequestMethod.GET)
	public List<CourtReport> populateReport(@RequestParam final Integer eventId) {
			List<CourtReport> reportList = new ArrayList<CourtReport>();
		try {
			reportList = ocupsrdeService.populateReport(eventId);
			}
		catch (Exception e) {
			logger.error("populateReport", e);
		}
		return reportList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupsrde/populateReportType", method = RequestMethod.GET)
	public List<OrderType> populateReportType() {
		List<OrderType> result = new ArrayList<OrderType>();
		try {
			result = ocupsrdeService.populateReportType();
		} catch (Exception e) {
			logger.error("orderType", e);
		}
		return result;
	}
		
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupsrde/populateAssignReport", method = RequestMethod.POST)
	public List<AssignReport> populateAssignReport(@RequestBody final CourtReport courtReport) {
		List<AssignReport> assignReportList = new ArrayList<AssignReport>();
		try {
			assignReportList = ocupsrdeService.populateAssignReport(courtReport);
		} catch (Exception e) {
			logger.error("populateAssignReport", e);
		}
		return assignReportList;
	}
		
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupsrde/populateFunctionType", method = RequestMethod.GET)
	public List<OrderType> populateFunctionType() {
		List<OrderType> result = new ArrayList<OrderType>();
		try {
			result = ocupsrdeService.populateFunctionType();
		} catch (Exception e) {
			logger.error("orderType", e);
		}
		return result;
	}
		
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupsrde/populateAreaType", method = RequestMethod.GET)
	public List<OrderType> populateAreaType() {
		List<OrderType> result = new ArrayList<OrderType>();
		try {
			result = ocupsrdeService.populateAreaType();
		} catch (Exception e) {
			logger.error("populateAreaType", e);
		}
		return result;
	}
		
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupsrde/populateArea", method = RequestMethod.GET)
	public List<OrderType> populateArea(@RequestParam final String areaType) {
		List<OrderType> result = new ArrayList<OrderType>();
		try {
			result = ocupsrdeService.populateArea(areaType);
		} catch (Exception e) {
			logger.error("populateArea", e);
		}
		return result;
	}
		
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupsrde/populateTeamResponsible", method = RequestMethod.GET)
	public List<TeamResponsible> populateTeamResponsible(@RequestParam(value = "area") final String areaCode,
			@RequestParam(value = "category") final String functionType) {
		List<TeamResponsible> result = new ArrayList<TeamResponsible>();
		try {
			result = ocupsrdeService.populateTeamResponsible(areaCode, functionType);
		} catch (Exception e) {
			logger.error("populateTeamResponsible", e);
		}
		return result;
	}
		
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupsrde/populateStaffDetails", method = RequestMethod.GET)
	public List<StaffDetails> populateStaffDetails(@RequestParam final Integer teamId) {
		List<StaffDetails> result = new ArrayList<StaffDetails>();
		try {
			result = ocupsrdeService.populateStaffDetails(teamId);
		} catch (Exception e) {
			logger.error("populateStaffDetails", e);
		}
		return result;
	}
		
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupsrde/populateStaffLovDetails", method = RequestMethod.GET)
	public List<TeamResponsible> populateStaffLovDetails(@RequestParam final Integer teamId) {
		List<TeamResponsible> result = new ArrayList<TeamResponsible>();
		try {
			result = ocupsrdeService.populateStaffLovDetails(teamId);
		} catch (Exception e) {
			logger.error("populateStaffDetails", e);
		}
		return result;
	}
		
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupsrde/generateOrderId", method = RequestMethod.GET)
	public Integer generateOrderId() {
		Integer ordervalue = 0;
		try {
			ordervalue = ocupsrdeService.generateOrderId();
		} catch (Exception e) {
			logger.error("generateOrderId", e);
		}
		return ordervalue;
	}
		
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupsrde/getStatusDesc", method = RequestMethod.GET)
	public String getStatusDesc(@RequestParam final String reportStatus) {
		String statusDesc = null;
		try {
			statusDesc = ocupsrdeService.getStatusDesc(reportStatus);
		} catch (Exception e) {
			logger.error("getStatusDesc", e);
		}
		return statusDesc;
	}
		
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocupsrde/insertUpdateDeleteCourtReport", method = RequestMethod.POST)
	public @ResponseBody Integer insertUpdateDeleteCourtReport(
			@RequestBody CourtReportCommitBean courtReportCommitBean) {
		int liReturn = 0;
		try {
			liReturn = ocupsrdeService.insertUpdateDeleteCourtReport(courtReportCommitBean);
		} catch (Exception e) {
			logger.error("insertUpdateDeleteCourtReport", e);
		}
		return liReturn;
	}
		
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupsrde/isReportExist", method = RequestMethod.POST)
	public @ResponseBody Boolean isReportExist(@RequestBody List<CourtReport> courtReport) {
		boolean liReturn = false;
		try {
			liReturn = ocupsrdeService.isReportExist(courtReport.get(0));
		} catch (Exception e) {
			logger.error("isReportExist", e);
		}
		return liReturn;
	}
		
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupsrde/create_team_assign_hty_new", method = RequestMethod.POST)
	public @ResponseBody Integer create_team_assign_hty_new(@RequestBody CourtReport courtReportAssign) {
		int lireturn = 0;
		try {
			lireturn = ocupsrdeService.create_team_assign_hty_new(courtReportAssign);
		} catch (Exception e) {
			logger.error("create_team_assign_hty_new", e);
		}
		return lireturn;

	}
}
	