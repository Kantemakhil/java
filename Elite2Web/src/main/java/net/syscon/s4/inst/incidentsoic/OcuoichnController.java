package net.syscon.s4.inst.incidentsoic;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.incidentsoic.beans.OicHearingNotices;
import net.syscon.s4.im.incidentsoic.beans.OicHearingNoticesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.OicHearings;
import net.syscon.s4.im.incidentsoic.beans.OicHearingsCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OcuoichnController {

	@Autowired
	private OcuoichnService ocuoichnService;
	
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuoichnController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoichn/oicHearExecuteQuery", method = RequestMethod.POST)
	public List<OicHearings> oicHearSearchOicHearings(@RequestBody final OicHearings searchBean) {
		List<OicHearings> searchResult = new ArrayList<>();
		try {
			searchResult = ocuoichnService.oicHearSearchOicHearings(searchBean);
		} catch (Exception e) {
			logger.error("oicHearSearchOicHearings:", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update in the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoichn/oicHearCommit", method = RequestMethod.POST)
	public @ResponseBody Integer oicHearCommit(@RequestBody final OicHearingsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocuoichnService.oicHearCommit(commitBean);
		} catch (Exception e) {
			logger.error("oicHearCommit:", e);
		}
		return liReturn;
	}

	/**
	 * getting rgAgyIncpStaffId LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoichn/rgAgyIncpStaffIdRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgAgyIncpStaffIdRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		final StaffMembers obj = new StaffMembers();
		try {
			recordList = ocuoichnService.rgAgyIncpStaffIdRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("rgAgyIncpStaffIdRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgHearingType LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoichn/rgHearingTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgHearingTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocuoichnService.rgHearingTypeRecordGroup();
		} catch (Exception e) {
			logger.error("rgHearingTypeRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgInternalLocations LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoichn/rgInternalLocationsRecordGroup", method = RequestMethod.GET)
	public List<AgencyInternalLocations> rgInternalLocationsRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyInternalLocations> recordList = new ArrayList<AgencyInternalLocations>();
		final AgencyInternalLocations obj = new AgencyInternalLocations();
		try {
			recordList = ocuoichnService.rgInternalLocationsRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("rgInternalLocationsRecordGroup:", e);
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
	@RequestMapping(value = "/ocuoichn/oicHearNotiExecuteQuery", method = RequestMethod.POST)
	public List<OicHearingNotices> oicHearNotiSearchOicHearingNotices(@RequestBody final OicHearingNotices searchBean) {
		List<OicHearingNotices> searchResult = new ArrayList<>();
		final OicHearingNotices bean = new OicHearingNotices();
		try {
			searchResult = ocuoichnService.oicHearNotiSearchOicHearingNotices(searchBean);
		} catch (Exception e) {
			logger.error("oicHearNotiSearchOicHearingNotices:", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update in the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoichn/oicHearNotiCommit", method = RequestMethod.POST)
	public @ResponseBody Integer oicHearNotiCommit(@RequestBody final OicHearingNoticesCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocuoichnService.oicHearNotiCommit(commitBean);
		} catch (Exception e) {
			logger.error("oicHearNotiCommit:", e);
		}
		return liReturn;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoichn/oicHearOnCheckDeleteMasterOicHearNotiCur", method = RequestMethod.POST)
	public @ResponseBody List<Object> oicHearOnCheckDeleteMasterOicHearNotiCur(
			@RequestBody final OicHearingNotices paramBean) {
		List<Object> dataObj = null;
		try {
			dataObj = ocuoichnService.oicHearOnCheckDeleteMasteroicHearNotiCur(paramBean);
		} catch (Exception e) {
			logger.error("oicHearOnCheckDeleteMasterOicHearNotiCur:", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoichn/oicHearPreInsertgetEventIdCur", method = RequestMethod.GET)
	public @ResponseBody Object oicHearPreInsertgetEventIdCur(@RequestBody final Dual paramBean) {
		Object dataObj = new Object();
		try {
			dataObj = ocuoichnService.oicHearPreInsertgetEventIdCur(paramBean);
		} catch (Exception e) {
			logger.error("oicHearPreInsertgetEventIdCur:", e);
		}
		return dataObj;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoichn/oicHearCheckScheduleConflict", method = RequestMethod.POST)
	public @ResponseBody Integer oicHearCheckScheduleConflict(@RequestBody final OicHearings searchBean) {
		Integer liReturn = 0;
		try {
			liReturn = ocuoichnService.oicHearCheckScheduleConflict(searchBean);
		} catch (Exception e) {
			logger.error("In method offSchCheckScheduleConflict", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoichn/checkNonAssociations", method = RequestMethod.POST)
	public String serarchNonAssociationOffender(@RequestBody final OicHearingsCommitBean commitBean) {
		logger.info(this.getClass().getName() + commitBean != null ? commitBean.toString() : null);
		String liReturn = "";
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocuoichnService.checkNonAssociations(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in crtEveCommit : ", e);
		}
		return liReturn;
		
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUOICHN");
	}
	
}