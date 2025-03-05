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
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCountsCommitBean;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;

/**
 * Class OidsublcController
 */
@EliteController
public class OidsublcController {
	@Autowired
	private OidsublcService oidsublcService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidsublcController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsublc/subLocCntExecuteQuery", method = RequestMethod.POST)
	public List<AgencyLocationCounts> subLocCntExecuteQuery(@RequestBody final AgencyLocationCounts searchBean) {
		List<AgencyLocationCounts> searchResult = new ArrayList<>();
		try {
			searchResult = oidsublcService.subLocCntExecuteQuery(searchBean);
		} catch (Exception e) {
			final AgencyLocationCounts bean = new AgencyLocationCounts();
			logger.error("In method subLocCntExecuteQuery", e);
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
	@RequestMapping(value = "/oidsublc/subLocCntCommit", method = RequestMethod.POST)
	public @ResponseBody Integer subLocCntCommit(@RequestBody final AgencyLocationCountsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidsublcService.subLocCntCommit(commitBean);
		} catch (Exception e) {
			logger.error("In method subLocCntCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsublc/cgfkAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkAgyLocIdRecordGroup(@RequestParam(value = "sessionId") final Integer sessionId,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = oidsublcService.cgfkAgyLocIdRecordGroup(sessionId, caseloadId, userName);
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("In method cgfkAgyLocIdRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkCountTypes LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsublc/cgfkCountTypesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkCountTypesRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidsublcService.cgfkCountTypesRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method cgfkCountTypesRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkSchTime LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsublc/cgfkSchTimeRecordGroup", method = RequestMethod.GET)
	public List<AgencyCountTypes> cgfkSchTimeRecordGroup() {
		List<AgencyCountTypes> recordList = new ArrayList<AgencyCountTypes>();
		try {
			recordList = oidsublcService.cgfkSchTimeRecordGroup();
		} catch (Exception e) {
			final AgencyCountTypes obj = new AgencyCountTypes();
			logger.error("In method cgfkSchTimeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkHousingLevel1 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsublc/cgfkHousingLevel1RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> cgfkHousingLevel1RecordGroup(
			@RequestParam(value = "countTypeCodeId") final long countTypeCodeId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidsublcService.cgfkHousingLevel1RecordGroup(countTypeCodeId);
		} catch (Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("In method cgfkHousingLevel1RecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkHousingLevel2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsublc/cgfkHousingLevel2RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> cgfkHousingLevel2RecordGroup(
			@RequestParam(value = "countTypeCodeId") final long countTypeCodeId,
			@RequestParam(value = "livingUnitId") final Integer livingUnitId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidsublcService.cgfkHousingLevel2RecordGroup(countTypeCodeId, livingUnitId);
		} catch (Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("In method cgfkHousingLevel2RecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkHousingLevel3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsublc/cgfkHousingLevel3RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> cgfkHousingLevel3RecordGroup(
			@RequestParam(value = "countTypeCodeId") final long countTypeCodeId,
			@RequestParam(value = "livingUnitId") final Integer livingUnitId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidsublcService.cgfkHousingLevel3RecordGroup(countTypeCodeId, livingUnitId);
		} catch (Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("In method cgfkHousingLevel3RecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkInitLocCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsublc/cgfkInitLocCodeRecordGroup", method = RequestMethod.GET)
	public List<VIntLocSummaries> cgfkInitLocCodeRecordGroup(
			@RequestParam(value = "countTypeCodeId") final long countTypeCodeId) {
		List<VIntLocSummaries> recordList = new ArrayList<VIntLocSummaries>();
		try {
			recordList = oidsublcService.cgfkInitLocCodeRecordGroup(countTypeCodeId);
		} catch (Exception e) {
			final VIntLocSummaries obj = new VIntLocSummaries();
			logger.error("In method cgfkInitLocCodeRecordGroup", e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkConductedBy LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsublc/cgfkConductedByRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> cgfkConductedByRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = oidsublcService.cgfkConductedByRecordGroup();
		} catch (Exception e) {
			final StaffMembers obj = new StaffMembers();
			logger.error("In method cgfkConductedByRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkConductedBy1 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsublc/cgfkConductedBy1RecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> cgfkConductedBy1RecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = oidsublcService.cgfkConductedBy1RecordGroup();
		} catch (Exception e) {
			final StaffMembers obj = new StaffMembers();
			logger.error("In method cgfkConductedBy1RecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cfgkRecountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsublc/cfgkRecountCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cfgkRecountCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidsublcService.cfgkRecountCodeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method cfgkRecountCodeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsublc/getHousingLevels", method = RequestMethod.GET)
	public AgencyLocations getHousingLevels(@RequestParam(value = "caseloadId") final String caseloadId) {
		AgencyLocations returnList = new AgencyLocations();
		try {
			returnList = oidsublcService.getHousingLevels(caseloadId);
		} catch (Exception e) {
			logger.error("In method getHousingLevels", e);
		}
		return returnList;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidsublc/reSubLocCntCommit", method = RequestMethod.POST)
	public @ResponseBody Integer reSubLocCntCommit(@RequestBody final AgencyLocationCountsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			liReturn = oidsublcService.reSubLocCntCommit(commitBean,userName);
		} catch (Exception e) {
			logger.error("In method reSubLocCntCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsublc/getDefaultAgyLoc", method = RequestMethod.GET)
	public AgencyCountTypes getDefaultAgyLoc(@RequestParam(value = "caseloadId") final String caseloadId) {
		AgencyCountTypes agencyLocations = new AgencyCountTypes();
		try {
			agencyLocations = oidsublcService.getDefaultAgyLoc(caseloadId);
		} catch (Exception e) {
			logger.error("In method getDefaultAgyLoc", e);
		}
		return agencyLocations;
	}
}