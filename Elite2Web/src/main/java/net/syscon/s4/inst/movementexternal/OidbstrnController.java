package net.syscon.s4.inst.movementexternal;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OidbstrnController {
	@Autowired
	private OidbstrnService oidbstrnService;
	
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidbstrnController.class.getName());

	/**
	 * getting rgReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbstrn/rgReasonRecordGroup", method = RequestMethod.GET)
	public List<MovementReasons> rgReasonRecordGroup() {
		List<MovementReasons> recordList = new ArrayList<MovementReasons>();
		try {
			recordList = oidbstrnService.rgReasonRecordGroup();
		} catch (Exception e) {
			MovementReasons obj = new MovementReasons();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAgyLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbstrn/rgAgyLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocRecordGroup(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oidbstrnService.rgAgyLocRecordGroup(caseLoadId);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAllAgyLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbstrn/rgAllAgyLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAllAgyLocRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oidbstrnService.rgAllAgyLocRecordGroup(agyLocId);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgEscort LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbstrn/rgEscortRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgEscortRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidbstrnService.rgEscortRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgCancelReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidbstrn/rgCancelReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCancelReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidbstrnService.rgCancelReasonRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("", e);
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
	@RequestMapping(value = "/oidbstrn/offAllSchExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderAllSchedules> offAllSchExecuteQuery(@RequestParam(value = "caseLoad") final String caseLoad,
			@RequestBody VOffenderAllSchedules searchBean) {
		List<VOffenderAllSchedules> searchResult = new ArrayList<>();
		try {
			searchResult = oidbstrnService.offAllSchExecuteQuery(caseLoad, searchBean);
		} catch (Exception e) {
			logger.error("", e);
			searchResult.clear();
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
	@RequestMapping(value = "/oidbstrn/offAllSchCommit", method = RequestMethod.POST)
	public @ResponseBody String offAllSchCommit(@RequestBody VOffenderAllSchedulesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		String liReturn = null;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidbstrnService.offAllSchCommit(commitBean);
			if("1".equals(liReturn)) {
				prosmainService.enableTriggers(commitBean, authorization, "5");
			}
		} catch (Exception e) {

			logger.error("", e);
		}
		return liReturn;
	}

}