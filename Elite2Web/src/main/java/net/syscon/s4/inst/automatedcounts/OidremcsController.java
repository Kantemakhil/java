package net.syscon.s4.inst.automatedcounts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocationCounts;
import net.syscon.s4.im.beans.AgencyLocationCountsCommitBean;
import net.syscon.s4.im.beans.LivingUnits;

/**
 * Class OidremcsController
 */
@EliteController
public class OidremcsController {
	@Autowired
	private OidremcsService oidremcsService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidremcsController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidremcs/subRemCntExecuteQuery", method = RequestMethod.POST)
	public List<AgencyLocationCounts> subRemCntExecuteQuery(@RequestBody final AgencyLocationCounts searchBean) {
		List<AgencyLocationCounts> searchResult = new ArrayList<>();
		try {
			searchResult = oidremcsService.subRemCntExecuteQuery(searchBean);
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
	@RequestMapping(value = "/oidremcs/subRemCntCommit", method = RequestMethod.POST)
	public @ResponseBody Integer subRemCntCommit(@RequestBody final AgencyLocationCountsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidremcsService.subRemCntCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkHousingLevel1 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidremcs/cgfkHousingLevel1RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> cgfkHousingLevel1RecordGroup(
			@RequestParam(value = "countTypeId") final Integer countTypeId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidremcsService.cgfkHousingLevel1RecordGroup(countTypeId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkHousingLevel2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidremcs/cgfkHousingLevel2RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> cgfkHousingLevel2RecordGroup(
			@RequestParam(value = "countTypeId") final Integer countTypeId,
			@RequestParam(value = "livingUnitId") final Integer livingUnitId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidremcsService.cgfkHousingLevel2RecordGroup(countTypeId, livingUnitId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkHousingLevel3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidremcs/cgfkHousingLevel3RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> cgfkHousingLevel3RecordGroup(
			@RequestParam(value = "countTypeId") final Integer countTypeId,
			@RequestParam(value = "livingUnitId") final Integer livingUnitId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidremcsService.cgfkHousingLevel3RecordGroup(countTypeId, livingUnitId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkInitLocCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidremcs/cgfkInitLocCodeRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> cgfkInitLocCodeRecordGroup(
			@RequestParam(value = "countTypeId") final Integer countTypeId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidremcsService.cgfkInitLocCodeRecordGroup(countTypeId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkConductedBy LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidremcs/cgfkConductedByRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> cgfkConductedByRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = oidremcsService.cgfkConductedByRecordGroup();
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
	@RequestMapping(value = "/oidremcs/changeHousingLevelOne", method = RequestMethod.GET)
	public Map<String, Object> changeHousingLevelOne(@RequestParam(value = "livingUnitId") final Integer livingUnitId) {
		Map<String, Object> searchResult = new HashMap<>();
		try {
			searchResult = oidremcsService.changeHousingLevelOne(livingUnitId);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidremcs/changeHousingLevelTwo", method = RequestMethod.GET)
	public Map<String, Object> changeHousingLevelTwo(
			@RequestParam(value = "livingUnitIdOne") final Integer livingUnitIdOne,
			@RequestParam(value = "livingUnitIdTwo") final Integer livingUnitIdTwo) {
		Map<String, Object> searchResult = new HashMap<>();
		try {
			searchResult = oidremcsService.changeHousingLevelTwo(livingUnitIdOne, livingUnitIdTwo);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidremcs/getInternalLocationCount", method = RequestMethod.GET)
	public Integer getInternalLOcationCount(@RequestParam(value = "livingUnitId") final Integer livingUnitId) {
		Integer searchResult = 0;
		try {
			searchResult = oidremcsService.getInternalLocationCount(livingUnitId);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidremcs/getHousingLocationLovNames", method = RequestMethod.GET)
	public Map<String, Object> getHousingLocationLovNames(
			@RequestParam(value = "countTypeId") final Integer countTypeId) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap = oidremcsService.getHousingLocationLovNames(countTypeId);
		} catch (Exception e) {
			logger.error(e);
		}
		return resultMap;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidremcs/calculateActualCount", method = RequestMethod.GET)
	public int calculateActualCount(@RequestParam(value = "livingUnitId") final Integer livingUnitId) {
		int count = 0;
		try {
			count = oidremcsService.calculateActualCount(livingUnitId);
		} catch (Exception e) {
			logger.error(e);
		}
		return count;
	}

	public static void main(String[] args) {
		
	}
}