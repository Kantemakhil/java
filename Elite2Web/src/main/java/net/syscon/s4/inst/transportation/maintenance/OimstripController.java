package net.syscon.s4.inst.transportation.maintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripAssignments;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripAssignmentsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTrips;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.Trips;
import net.syscon.s4.inst.transportation.maintenance.beans.TripsCommitBean;
import net.syscon.s4.pkgs.tag_transport.TagTransportService;

/**
 * @author Vrnda Software Technologies
 * @version 1.0
 */
@EliteController
public class OimstripController {

	@Autowired
	private OimstripService oimstripService; // OimstripService

	@Autowired
	private TagTransportService tagTransportService;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimstripController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimstrip/tripsExecuteQuery", method = RequestMethod.POST)
	public List<Trips> tripsExecuteQuery(@RequestBody Trips searchBean) {
		List<Trips> searchResult = new ArrayList<>();
		try {
			searchResult = oimstripService.tripsExecuteQuery(searchBean);
		} catch (Exception e) {
			Trips bean = new Trips();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimstrip/tripsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer tripsCommit(@RequestBody TripsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oimstripService.tripsCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * getting rgTripType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimstrip/rgTripTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTripTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimstripService.rgTripTypeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStaffId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimstrip/rgStaffIdRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffIdRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = oimstripService.rgStaffIdRecordGroup();
		} catch (Exception e) {
			StaffMembers obj = new StaffMembers();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimstrip/scheduledTripsExecuteQuery", method = RequestMethod.POST)
	public List<ScheduledTrips> scheduledTripsExecuteQuery(@RequestBody ScheduledTrips searchBean) {
		List<ScheduledTrips> searchResult = new ArrayList<>();
		try {
			searchResult = oimstripService.scheduledTripsExecuteQuery(searchBean);

		} catch (Exception e) {
			ScheduledTrips bean = new ScheduledTrips();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimstrip/scheduledTripsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer scheduledTripsCommit(@RequestBody ScheduledTripsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oimstripService.scheduledTripsCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimstrip/scheduledTripAssignmentsExecuteQuery", method = RequestMethod.POST)
	public List<ScheduledTripAssignments> scheduledTripAssignmentsExecuteQuery(
			@RequestBody ScheduledTripAssignments searchBean) {
		List<ScheduledTripAssignments> searchResult = new ArrayList<>();
		try {
			searchResult = oimstripService.scheduledTripAssignmentsExecuteQuery(searchBean);
		} catch (Exception e) {
			ScheduledTripAssignments bean = new ScheduledTripAssignments();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimstrip/scheduledTripAssignmentsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer scheduledTripAssignmentsCommit(
			@RequestBody ScheduledTripAssignmentsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oimstripService.scheduledTripAssignmentsCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimstrip/staffAssignmentExecuteQuery", method = RequestMethod.POST)
	public List<ScheduledTripAssignments> staffAssignmentExecuteQuery(
			@RequestBody ScheduledTripAssignments searchBean) {
		List<ScheduledTripAssignments> searchResult = new ArrayList<>();
		try {
			searchResult = oimstripService.staffAssignmentExecuteQuery(searchBean);
		} catch (Exception e) {
			ScheduledTripAssignments bean = new ScheduledTripAssignments();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimstrip/staffAssignmentCommit", method = RequestMethod.POST)
	public @ResponseBody Integer staffAssignmentCommit(@RequestBody ScheduledTripAssignmentsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oimstripService.staffAssignmentCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimstrip/tagtransportCTrip", method = RequestMethod.POST)
	public Integer tagtransportCTrip(@RequestBody String tripcodeval) {
		Integer tripVal = 0;
		try {
			tripVal = oimstripService.tagtransportCTrip(tripcodeval);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return tripVal;

	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimstrip/scheduleGenerateOidgenst", method = RequestMethod.POST)
	public List<ScheduledTrips> scheduleGenerateOidgenst(@RequestBody String tripcodeval) {
		List<ScheduledTrips> searchResult = new ArrayList<>();
		try {
			searchResult = oimstripService.scheduleGenerateOidgenst(tripcodeval);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimstrip/tripsOidgenstInsert", method = RequestMethod.POST)
	public Integer tripsOidgenstInsert(@RequestBody Trips tripsModel) {
		Integer res = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			tripsModel.setCreateUserId(userName);
			res = oimstripService.tripsOidgenstInsert(tripsModel);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return res;
	}

}
