package net.syscon.s4.inst.automatedcounts.maintenance;

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
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyReportingLocs;
import net.syscon.s4.im.beans.AgencyReportingLocsCommitBean;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypesCommitBean;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;

/**
 * Class OimcountController
 */
@EliteController
public class OimcountController {
	@Autowired
	private OimcountService oimcountService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimcountController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimcount/agencyLocationsExecuteQuery", method = RequestMethod.POST)
	public List<AgencyLocations> agencyLocationsExecuteQuery(@RequestBody final AgencyLocations searchBean) {
		List<AgencyLocations> searchResult = new ArrayList<>();
		try {
			searchResult = oimcountService.agencyLocationsExecuteQuery(searchBean);
		} catch (Exception e) {
			final AgencyLocations bean = new AgencyLocations();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * getting cgfkAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimcount/cgfkAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkAgyLocIdRecordGroup(@RequestParam(value = "caseloadid") final String caseloadid) {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oimcountService.cgfkAgyLocIdRecordGroup(caseloadid);
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("Exception in cgfkAgyLocIdRecordGroup: Oimcount:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkCountTypes LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimcount/cgfkCountTypesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkCountTypesRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oimcountService.cgfkCountTypesRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in cgfkCountTypesRecordGroup: Oimcount:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkHousingLevel1 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimcount/cgfkHousingLevel1RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> cgfkHousingLevel1RecordGroup(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<LivingUnits> recordList = new ArrayList<>();
		try {
			recordList = oimcountService.cgfkHousingLevel1RecordGroup(agyLocId);
		} catch (Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("Exception in cgfkHousingLevel1RecordGroup: Oimcount:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkHousingLevel2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimcount/cgfkHousingLevel2RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> cgfkHousingLevel2RecordGroup(
			@RequestParam(value = "parentField1") final String parentField1) {
		List<LivingUnits> recordList = new ArrayList<>();
		try {
			recordList = oimcountService.cgfkHousingLevel2RecordGroup(parentField1);
		} catch (Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("Exception in cgfkHousingLevel2RecordGroup: Oimcount:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkHousingLevel3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimcount/cgfkHousingLevel3RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> cgfkHousingLevel3RecordGroup(
			@RequestParam(value = "parentField2") final String parentField2) {
		List<LivingUnits> recordList = new ArrayList<>();
		try {
			recordList = oimcountService.cgfkHousingLevel3RecordGroup(parentField2);
		} catch (Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("Exception in cgfkHousingLevel3RecordGroup: Oimcount:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkInitLocCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimcount/cgfkInitLocCodeRecordGroup", method = RequestMethod.GET)
	public List<VIntLocSummaries> cgfkInitLocCodeRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<VIntLocSummaries> recordList = new ArrayList<>();
		try {
			recordList = oimcountService.cgfkInitLocCodeRecordGroup(agyLocId);
		} catch (Exception e) {
			final VIntLocSummaries obj = new VIntLocSummaries();
			logger.error("Exception in cgfkInitLocCodeRecordGroup: Oimcount:", e);
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
	@RequestMapping(value = "/oimcount/agencyCountTypesExecuteQuery", method = RequestMethod.POST)
	public List<AgencyCountTypes> agencyCountTypesExecuteQuery(@RequestBody final AgencyCountTypes searchBean) {
		List<AgencyCountTypes> searchResult = new ArrayList<>();
		try {
			searchResult = oimcountService.agencyCountTypesExecuteQuery(searchBean);
		} catch (Exception e) {
			final AgencyCountTypes bean = new AgencyCountTypes();
			logger.error("Exception in agencyCountTypesExecuteQuery:", e);
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
	@RequestMapping(value = "/oimcount/agencyCountTypesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agencyCountTypesCommit(@RequestBody final AgencyCountTypesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimcountService.agencyCountTypesCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception in agencyCountTypesCommit: Oimcount", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimcount/agencyReportingLocsHousExecuteQuery", method = RequestMethod.POST)
	public List<AgencyReportingLocs> agencyReportingLocsHousExecuteQuery(
			@RequestBody final AgencyReportingLocs searchBean) {
		List<AgencyReportingLocs> searchResult = new ArrayList<>();
		try {
			searchResult = oimcountService.agencyReportingLocsHousExecuteQuery(searchBean);
		} catch (Exception e) {
			final AgencyReportingLocs bean = new AgencyReportingLocs();
			logger.error("Exception in agencyReportingLocsHousExecuteQuery:", e);
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
	@RequestMapping(value = "/oimcount/agencyReportingLocsHousCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agencyReportingLocsHousCommit(
			@RequestBody final AgencyReportingLocsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimcountService.agencyReportingLocsHousCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception in agencyReportingLocsHousCommit: Oimcount", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimcount/agencyReportingLocsInitExecuteQuery", method = RequestMethod.POST)
	public List<AgencyReportingLocs> agencyReportingLocsInitExecuteQuery(
			@RequestBody final AgencyReportingLocs searchBean) {
		List<AgencyReportingLocs> searchResult = new ArrayList<>();
		try {
			searchResult = oimcountService.agencyReportingLocsInitExecuteQuery(searchBean);
		} catch (Exception e) {
			final AgencyReportingLocs bean = new AgencyReportingLocs();
			logger.error("Exception in agencyReportingLocsInitExecuteQuery:", e);
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
	@RequestMapping(value = "/oimcount/agencyReportingLocsInitCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agencyReportingLocsInitCommit(
			@RequestBody final AgencyReportingLocsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimcountService.agencyReportingLocsInitCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception in agencyReportingLocsInitCommit: Oimcount", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimcount/acctypeCheckboxChenged", method = RequestMethod.POST)
	public @ResponseBody Integer acctypeCheckboxChenged(@RequestBody final AgencyCountTypes object) {
		Integer liReturn = 0;
		try {
			liReturn = oimcountService.acctypeCheckboxChenged(object);
		} catch (Exception e) {

			logger.error("Exception in acctypeCheckboxChenged: Oimcount", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimcount/agencyReportingLocsHousWhenNewRecordInstance", method = RequestMethod.POST)
	public @ResponseBody Integer agencyReportingLocsHousWhenNewRecordInstance(
			@RequestBody final AgencyReportingLocs object) {
		Integer liReturn = 0;
		try {
			liReturn = oimcountService.agencyReportingLocsHousWhenNewRecordInstance(object);
		} catch (Exception e) {

			logger.error("Exception : Oimcount", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkHousingLevel3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimcount/livingUnitsQuery", method = RequestMethod.GET)
	public List<LivingUnits> livingUnitsQuery(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<LivingUnits> recordList = new ArrayList<>();
		try {
			recordList = oimcountService.livingUnitsQuery(agyLocId);
		} catch (Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("Exception : Oimcount:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	/**
	 * getting cgfkHousingLevel3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimcount/livingUnitsQueryOne", method = RequestMethod.GET)
	public List<LivingUnits> livingUnitsQueryOne(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<LivingUnits> recordList = new ArrayList<>();
		try {
			recordList = oimcountService.livingUnitsQueryOne(agyLocId);
		} catch (Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("Exception : Oimcount:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkHousingLevel3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimcount/agencyLocationsWhenNewRecordInstance", method = RequestMethod.POST)
	public AgencyLocations agencyLocationsWhenNewRecordInstance(@RequestBody final AgencyLocations object) {
		AgencyLocations recordList = new AgencyLocations();
		try {
			recordList = oimcountService.agencyLocationsWhenNewRecordInstance(object);
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("Exception : Oimcount:", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimcount/agencyCountReportsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agencyCountReportsCommit(@RequestBody final AgencyCountTypesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimcountService.agencyCountReportsCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception : Oimcount", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimcount/livingUnitsQueryTwo", method = RequestMethod.GET)
	public List<LivingUnits> livingUnitsQueryTwo(
			@RequestParam(value = "parentField") final String parentField) {
		List<LivingUnits> recordList = new ArrayList<>();
		try {
			recordList = oimcountService.livingUnitsQueryTwo(parentField);
		} catch (Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("Exception in livingUnitsQueryTwo: Oimcount:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
}