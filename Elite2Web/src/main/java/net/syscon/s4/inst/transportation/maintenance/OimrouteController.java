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
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.transportation.maintenance.beans.AgyLocFeedDetails;
import net.syscon.s4.inst.transportation.maintenance.beans.RouteStopDetails;
import net.syscon.s4.inst.transportation.maintenance.beans.RouteStopDetailsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.Routes;
import net.syscon.s4.inst.transportation.maintenance.beans.RoutesCommitBean;

/**
 * @author Vrnda Software Technologies
 * @version 1.0
 */

@EliteController
public class OimrouteController {

	@Autowired
	private OimrouteService oimrouteService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimrouteController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimroute/routesExecuteQuery", method = RequestMethod.GET)
	public List<Routes> routesExecuteQuery() {
		List<Routes> searchResult = new ArrayList<>();
		try {
			searchResult = oimrouteService.routesExecuteQuery();
		} catch (Exception e) {
			Routes bean = new Routes();
			logger.error("Exception occured in " + this.getClass().getName() + " in routesExecuteQuery() method", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic insert,delete, update in the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimroute/routesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer routesCommit(@RequestBody RoutesCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oimrouteService.routesCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in routesCommit() method", e);
		}
		return liReturn;
	}

	/**
	 * getting rgAgyLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimroute/rgAgyLocRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAgyLocRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimrouteService.rgAgyLocRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception occured in " + this.getClass().getName() + " in rgAgyLocRecordGroup() method", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param routes
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimroute/routeStopDetailsExecuteQuery", method = RequestMethod.POST)
	public List<RouteStopDetails> routeStopDetailsExecuteQuery(@RequestBody Routes routes) {
		List<RouteStopDetails> searchResult = new ArrayList<>();
		try {
			searchResult = oimrouteService.routeStopDetailsExecuteQuery(routes.getRouteName());
		} catch (Exception e) {
			RouteStopDetails bean = new RouteStopDetails();
			logger.error("Exception occured in " + this.getClass().getName() + " in routeStopDetailsExecuteQuery() method", e);
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
	@RequestMapping(value = "/oimroute/routeStopDetailsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer routeStopDetailsCommit(@RequestBody RouteStopDetailsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oimrouteService.routeStopDetailsCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in routeStopDetailsCommit() method", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param routestopdetails
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimroute/agyLocFeedDetailsExecuteQuery", method = RequestMethod.POST)
	public List<AgyLocFeedDetails> agyLocFeedDetailsExecuteQuery(@RequestBody RouteStopDetails routestopdetails) {
		List<AgyLocFeedDetails> searchResult = new ArrayList<>();
		try {
			searchResult = oimrouteService.agyLocFeedDetailsExecuteQuery(routestopdetails.getAgyLocId());
		} catch (Exception e) {
			AgyLocFeedDetails bean = new AgyLocFeedDetails();
			logger.error("Exception occured in " + this.getClass().getName() + " in agyLocFeedDetailsExecuteQuery() method", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}