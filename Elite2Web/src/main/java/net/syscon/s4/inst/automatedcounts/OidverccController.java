package net.syscon.s4.inst.automatedcounts;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypesCommitBean;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCounts;
import net.syscon.s4.inst.automatedcounts.beans.VReportingLocations;
import net.syscon.s4.inst.automatedcounts.beans.VReportingLocationsCommitBean;

/**
 * Class OidverccController
 */
@EliteController
public class OidverccController {
	@Autowired
	private OidverccService oidverccService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidverccController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvercc/agencyCountTypesExecuteQuery", method = RequestMethod.POST)
	public List<AgencyCountTypes> agencyCountTypesExecuteQuery(@RequestBody final AgencyCountTypes searchBean) {
		List<AgencyCountTypes> searchResult = new ArrayList<>();
		try {
			searchResult = oidverccService.agencyCountTypesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
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
	@RequestMapping(value = "/oidvercc/agencyCountTypesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agencyCountTypesCommit(@RequestBody final AgencyCountTypesCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = oidverccService.agencyCountTypesCommit(commitBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvercc/cgfkAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkAgyLocIdRecordGroup(@RequestParam(value="caseLoadId") final String caseLoadId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidverccService.cgfkAgyLocIdRecordGroup(caseLoadId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkCountTypes LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvercc/cgfkCountTypesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkCountTypesRecordGroup(@RequestParam(value="agyLocId") final String agyLocId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidverccService.cgfkCountTypesRecordGroup(agyLocId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkScheduledTime LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvercc/cgfkScheduledTimeRecordGroup", method = RequestMethod.GET)
	public List<AgencyCountTypes> cgfkScheduledTimeRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId,
			@RequestParam(value = "countTypeCode") final String countTypeCode) {
		List<AgencyCountTypes> recordList = new ArrayList<AgencyCountTypes>();
		try {
			recordList = oidverccService.cgfkScheduledTimeRecordGroup(agyLocId,countTypeCode);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvercc/reportingLocationsExecuteQuery", method = RequestMethod.POST)
	public List<VReportingLocations> reportingLocationsExecuteQuery(@RequestBody final VReportingLocations searchBean) {
		List<VReportingLocations> searchResult = new ArrayList<>();
		try {
			searchResult = oidverccService.reportingLocationsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
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
	@RequestMapping(value = "/oidvercc/reportingLocationsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer reportingLocationsCommit(@RequestBody final VReportingLocationsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oidverccService.reportingLocationsCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}
	/**
	 * Used to update the record in the data base
	 * @param bean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidvercc/updateAgencyLocationCounts", method = RequestMethod.POST)
	public @ResponseBody Integer updateAgencyLocationCounts(@RequestBody final AgencyLocationCounts bean) {
		Integer liReturn = 0;
		try {
			if (bean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				bean.setModifyUserId(userName);
			}
			liReturn = oidverccService.updateAgencyLocationCounts(bean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}
	
	@RequestMapping(value = "/oidvercc/getUserNameByCreatedUserId", method = RequestMethod.GET)
	public String getUserName( final String createUserId) {
		return oidverccService.getUserName(createUserId);
		
	}
}