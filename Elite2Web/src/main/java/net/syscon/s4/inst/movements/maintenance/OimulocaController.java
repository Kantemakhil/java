package net.syscon.s4.inst.movements.maintenance;

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
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.property.bean.InternalLocationUsages;
import net.syscon.s4.inst.property.bean.InternalLocationUsagesCommitBean;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocationsCommitBean;

/**
 * Class OimulocaController
 */
@EliteController
public class OimulocaController {
	@Autowired
	private OimulocaService oimulocaService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimulocaController.class.getName());

	/**
	 * getting rgLocationUsage LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimuloca/rgLocationUsageRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgLocationUsageRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oimulocaService.rgLocationUsageRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oimuloca:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAgyLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimuloca/rgAgyLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocRecordGroup(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oimulocaService.rgAgyLocRecordGroup(caseLoadId);
		} catch (Exception e) {
			logger.error("Exception : Oimuloca:", e);
		}
		return recordList;
	}

	/**
	 * getting rgLevelType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimuloca/rgLevelTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgLevelTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oimulocaService.rgLevelTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oimuloca:", e);
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
	@RequestMapping(value = "/oimuloca/usagesExecuteQuery", method = RequestMethod.POST)
	public List<InternalLocationUsages> usagesExecuteQuery(@RequestBody final InternalLocationUsages searchBean) {
		List<InternalLocationUsages> searchResult = new ArrayList<>();
		try {
			searchResult = oimulocaService.usagesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/oimuloca/usagesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer usagesCommit(@RequestBody final InternalLocationUsagesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oimulocaService.usagesCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception : Oimuloca", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimuloca/intLocL1ExecuteQuery", method = RequestMethod.POST)
	public List<IntLocUsageLocations> intLocL1ExecuteQuery(@RequestBody final IntLocUsageLocations searchBean) {
		List<IntLocUsageLocations> searchResult = new ArrayList<>();
		try {
			searchResult = oimulocaService.intLocL1ExecuteQuery(searchBean);
		} catch (Exception e) {
			final IntLocUsageLocations bean = new IntLocUsageLocations();
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
	@RequestMapping(value = "/oimuloca/intLocL1Commit", method = RequestMethod.POST)
	public @ResponseBody Integer intLocL1Commit(@RequestBody final IntLocUsageLocationsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oimulocaService.intLocL1Commit(commitBean);
		} catch (Exception e) {

			logger.error("Exception : Oimuloca", e);
		}
		return liReturn;
	}

	/**
	 * @params const0
	 * @return SystemProfiles
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimuloca/getInternalLocationRecords", method = RequestMethod.GET)
	public @ResponseBody AgencyInternalLocations getInternalLocationRecords(
			@RequestParam(value = "intLocId") final Integer intLocId) {
		AgencyInternalLocations dataObj = new AgencyInternalLocations();
		try {
			dataObj = oimulocaService.getInternalLocationRecords(intLocId);
		} catch (Exception e) {
			logger.error("getInternalLocationRecords", e);
		}
		return dataObj;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimuloca/intLocOneLov", method = RequestMethod.GET)
	public @ResponseBody List<AgencyInternalLocations> intLocOneLov(
			@RequestParam(value = "agyLocId") final String agyLocId) {
		List<AgencyInternalLocations> dataObj = new ArrayList<>();
		try {
			dataObj = oimulocaService.intLocOneLov(agyLocId);
		} catch (Exception e) {
			logger.error("intLocOneLov", e);
		}
		return dataObj;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimuloca/intLocTwoLov", method = RequestMethod.GET)
	public @ResponseBody List<AgencyInternalLocations> intLocTwoLov(
			@RequestParam(value = "agyIntLocId") final String agyIntLocId) {
		List<AgencyInternalLocations> dataObj = new ArrayList<>();
		try {
			dataObj = oimulocaService.intLocTwoLov(agyIntLocId);
		} catch (Exception e) {
			logger.error("intLocTwoLov", e);
		}
		return dataObj;
	}
}