package net.syscon.s4.inst.movements.housingchanges;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.BedAssignmentHistoriesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OidchlocController
 */
@EliteController
public class OidchlocController {
	@Autowired
	private OidchlocService oidchlocService;
	
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidchlocController.class.getName());

	/**
	 * getting rgAssignmentReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidchloc/rgAssignmentReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAssignmentReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidchlocService.rgAssignmentReasonRecordGroup();
		} catch (Exception e) {
			logger.error("rgAssignmentReasonRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidchloc/bedAhExecuteQuery", method = RequestMethod.POST)
	public List<BedAssignmentHistories> bedAhExecuteQuery(@RequestBody final BedAssignmentHistories searchBean) {
		List<BedAssignmentHistories> searchResult = new ArrayList<>();
		try {
			searchResult = oidchlocService.bedAhExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("bedAhExecuteQuery", e);
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
	@RequestMapping(value = "/oidchloc/bedAhCommit", method = RequestMethod.POST)
	public @ResponseBody Integer bedAhCommit(@RequestBody final BedAssignmentHistoriesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidchlocService.bedAhCommit(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "74");
			}
		} catch (Exception e) {
			logger.error("bedAhCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidchloc/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidchlocService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("sysPflExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidchloc/offBkgOnCheckDeleteMaster", method = RequestMethod.GET)
	public @ResponseBody List<Object> offBkgOnCheckDeleteMaster(@RequestBody final BedAssignmentHistories paramBean) {
		List<Object> dataObj = null;
		try {
			dataObj = oidchlocService.offBkgOnCheckDeleteMaster(paramBean);
		} catch (Exception e) {
			logger.error("offBkgOnCheckDeleteMaster:", e);
		}
		return dataObj;
	}

	/**
	 * Method for get Seq value from DB
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidchloc/bedAhPreInsert", method = RequestMethod.POST)
	public @ResponseBody Integer bedAhPreInsert(@RequestBody final BedAssignmentHistories paramBean) {
		Integer dataObj = null;
		try {
			dataObj = oidchlocService.bedAhPreInsert(paramBean);
		} catch (Exception e) {
			logger.error("bedAhPreInsert:", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidchloc/cgfkchkBedAhBedAhVLiv", method = RequestMethod.POST)
	public @ResponseBody LivingUnits cgfkchkBedAhBedAhVLiv(@RequestBody final LivingUnits paramBean) {
		LivingUnits dataObj = new LivingUnits();
		try {
			dataObj = oidchlocService.cgfkchkBedAhBedAhVLiv(paramBean);
		} catch (Exception e) {
			logger.error("cgfkchkBedAhBedAhVLiv:", e);
		}
		return dataObj;
	}

	/**
	 * Perfomring basic Oracle form functions i.e.update in the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidchloc/offBookingUpdate", method = RequestMethod.POST)
	public @ResponseBody Integer offBookingUpdate(@RequestBody final VHeaderBlock commitBean) {
		Integer liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidchlocService.offBookingUpdate(commitBean);
		} catch (Exception e) {

			logger.error("offBookingCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the Date and time from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidchloc/getMovementDateAndTime", method = RequestMethod.POST)
	public BedAssignmentHistories getMovementDateAndTime(@RequestBody final BedAssignmentHistories searchBean) {
		BedAssignmentHistories searchResult = new BedAssignmentHistories();
		try {
			searchResult = oidchlocService.getMovementDateAndTime(searchBean);
		} catch (Exception e) {
			logger.error("getMovementDateAndTime", e);
		}
		return searchResult;
	}

	/**
	 * below method is used to execute DB procedure to check Conflicts param
	 * objSearchDao returns BedAssignmentHistories
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidchloc/checkAllConficts", method = RequestMethod.POST)
	public BedAssignmentHistories checkAllConficts(@RequestBody final BedAssignmentHistories searchBean) {
		BedAssignmentHistories searchResult = new BedAssignmentHistories();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = oidchlocService.checkAllConficts(searchBean);
		} catch (Exception e) {
			logger.error("checkAllConficts", e);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidchloc/checkNonIndGangConficts", method = RequestMethod.POST)
	public List<BedAssignmentHistories> checkNonIndGangConficts(@RequestBody final List<BedAssignmentHistories> searchList) {
		List<BedAssignmentHistories> searchResult = new ArrayList<>();
		try {
			searchResult = oidchlocService.checkNonIndGangConficts(searchList);
		} catch (Exception e) {
			logger.error("checkAllConficts", e);
		}
		return searchResult;
	}
}
