package net.syscon.s4.inst.transportation.maintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripParameters;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripParametersCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTrips;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripsCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OidgenstController {
	private static Logger logger = LogManager.getLogger(OidgenstController.class.getName());
	@Autowired
	private OidgenstService oidgenstService;
	@Autowired
	private CommonService commonService;

	/**
	 * getting rgRoute LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidgenst/rgRouteRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRouteRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidgenstService.rgRouteRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidgenst/schPlannerExecuteQuery", method = RequestMethod.POST)
	public List<ScheduledTripParameters> schPlannerExecuteQuery(@RequestBody ScheduledTripParameters searchBean) {
		List<ScheduledTripParameters> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oidgenstService.schPlannerExecuteQuery(searchBean);
		} catch (Exception e) {
			ScheduledTripParameters bean = new ScheduledTripParameters();
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidgenst/schPlannerCommit", method = RequestMethod.POST)
	public @ResponseBody Integer schPlannerCommit(@RequestBody ScheduledTripParametersCommitBean commitBean) {
		int liReturn = 0;
		if (!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidgenstService.schPlannerCommit(commitBean);
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidgenst/scheduledTripsExecuteQuery", method = RequestMethod.POST)
	public List<ScheduledTrips> scheduledTripsExecuteQuery(@RequestBody ScheduledTrips searchBean) {
		List<ScheduledTrips> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oidgenstService.scheduledTripsExecuteQuery(searchBean);
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidgenst/scheduledTripsCommit", method = RequestMethod.POST)
	public @ResponseBody ScheduledTrips scheduledTripsCommit(@RequestBody ScheduledTripsCommitBean commitBean) {
		ScheduledTrips liReturn = null;
		if (!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidgenstService.scheduledTripsCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidgenst/scheduledTripsvalidate", method = RequestMethod.POST)
	public @ResponseBody ScheduledTrips scheduledTripsvalidate(@RequestBody ScheduledTrips bean) {
		ScheduledTrips liReturn = null;
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			liReturn = oidgenstService.scheduledTripsvalidate(bean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
		
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidgenst/scheduledGenerateCommit", method = RequestMethod.POST)
	public ScheduledTrips scheduledGenerateCommit(@RequestBody List<ScheduledTripParameters> list) {
		ScheduledTrips liReturn = null;
		if (!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			if (list != null && list.size() > 0 && !list.isEmpty()) {
				list.forEach(obj -> {
					obj.setCreateUserId(userName);
				});
			}
			liReturn = oidgenstService.scheduledGenerateCommit(list);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role, "OIDGENST");
	}

}
