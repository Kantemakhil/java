package net.syscon.s4.inst.demographicsbiometrics;

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
import net.syscon.s4.common.beans.TempLivingUnitProfilesCommitBean;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmuavbedLivUnitsQuery;
import net.syscon.s4.im.beans.TempLivingUnitProfiles;
import net.syscon.s4.inst.movements.housingchanges.OidchlocService;

/**
 * Class OmuavbedController
 */

@EliteController
public class OmuavbedController {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OmuavbedController.class);

	@Autowired
	private OmuavbedService omuavbedService;
	
	@Autowired
	private OidchlocService oidchlocService;

	/**
	 * Fetching the record from database table
	 *
	 * @return List<TempLivingUnitProfiles>
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuavbed/livuprofuforExecuteQuery", method = RequestMethod.POST)
	public List<TempLivingUnitProfiles> livuprofuforExecuteQuery(@RequestBody final TempLivingUnitProfiles searchBean) {
		List<TempLivingUnitProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = omuavbedService.livuprofuforExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("livuprofuforExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuavbed/livuprofuforCommit", method = RequestMethod.POST)
	public @ResponseBody Integer livuprofuforCommit(@RequestBody final TempLivingUnitProfilesCommitBean commitBean) {
		int liReturn = 0;
		try {
			final String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(user);
			liReturn = omuavbedService.livuprofuforCommit(commitBean);
		} catch (Exception e) {
			logger.error("livuprofuforCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting rgLivingUnit2 LOV values
	 * 
	 * @return List<LivingUnits>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuavbed/rgLivingUnitPagyRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgLivingUnitPagyRecordGroup(
			@RequestParam(value = "livingUnitId") final Long livingUnitId,
			@RequestParam(value = "level1Code") final String level1Code) {
		List<LivingUnits> recordList = new ArrayList<>();
		try {
			recordList = omuavbedService.rgLivingUnitPagyRecordGroup(livingUnitId, level1Code);
		} catch (Exception e) {
			logger.error("rgLivingUnitPagyRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgLivingUnit3 LOV values
	 * 
	 * @return List<LivingUnits>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuavbed/rgLivingUnitLocIdRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgLivingUnitLocIdRecordGroup(
			@RequestParam(value = "livingUnitId") final Integer livingUnitId,
			@RequestParam(value = "level2Code") final String level2Code) {
		List<LivingUnits> recordList = new ArrayList<>();
		try {
			recordList = omuavbedService.rgLivingUnitLocIdRecordGroup(livingUnitId, level2Code);
		} catch (Exception e) {
			logger.error("rgLivingUnitLocIdRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgLivingUnit4 LOV values
	 * 
	 * @return List<LivingUnits>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuavbed/rgLivingUnitLevelIdRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgLivingUnitLevelIdRecordGroup(
			@RequestParam(value = "livingUnitId") final Integer livingUnitId,
			@RequestParam(value = "level3Code") final String level3Code) {
		List<LivingUnits> recordList = new ArrayList<>();
		try {
			recordList = omuavbedService.rgLivingUnitLevelIdRecordGroup(livingUnitId, level3Code);
		} catch (Exception e) {
			logger.error("rgLivingUnitLevelIdRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgLivingUnit1 LOV values
	 * 
	 * @return List<LivingUnits>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuavbed/rgLivingUnitRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgLivingUnitRecordGroup(@RequestParam(value = "agencyLocId") final String agencyLocationId) {
		List<LivingUnits> recordList = new ArrayList<>();
		try {
			recordList = omuavbedService.rgLivingUnitRecordGroup(agencyLocationId);
		} catch (Exception e) {
			logger.error("rgLivingUnitRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgLivingUnitType LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuavbed/rgLivingUnitTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgLivingUnitTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = omuavbedService.rgLivingUnitTypeRecordGroup();
		} catch (Exception e) {
			logger.error("rgLivingUnitTypeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgUsedFor LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuavbed/rgUsedForRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgUsedForRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = omuavbedService.rgUsedForRecordGroup();
		} catch (Exception e) {
			logger.error("rgUsedForRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgAttributes LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuavbed/rgAttributesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAttributesRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = omuavbedService.rgAttributesRecordGroup();
		} catch (Exception e) {
			logger.error("rgAttributesRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<TempLivingUnitProfiles>
	 * @Param searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuavbed/livuprofattrExecuteQuery", method = RequestMethod.POST)
	public List<TempLivingUnitProfiles> livuprofattrExecuteQuery(@RequestBody final TempLivingUnitProfiles searchBean) {
		List<TempLivingUnitProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = omuavbedService.livuprofuforExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("livuprofattrExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuavbed/livuprofattrCommit", method = RequestMethod.POST)
	public @ResponseBody Integer livuprofattrCommit(@RequestBody final TempLivingUnitProfilesCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = omuavbedService.livuprofuforCommit(commitBean);
		} catch (Exception e) {
			logger.error("livuprofattrCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<OmuavbedLivUnitsQuery>
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuavbed/livingUnitsExecuteQuery", method = RequestMethod.POST)
	public List<OmuavbedLivUnitsQuery> livingunitsExecuteQuery(@RequestBody final OmuavbedLivUnitsQuery searchBean) {
		List<OmuavbedLivUnitsQuery> searchResult = new ArrayList<>();
		try {
			searchResult = omuavbedService.livingunitsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("livingunitsExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyLocations>
	 * @Param pAgyLocId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuavbed/whenNewFormInstancelevelCur", method = RequestMethod.GET)
	public @ResponseBody List<AgencyLocations> whenNewFormInstancelevelCur(
			final @RequestParam(value = "pAgyLocId") String pAgyLocId) {
		List<AgencyLocations> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = omuavbedService.whenNewFormInstancelevelCur(pAgyLocId);
		} catch (Exception e) {
			logger.error("whenNewFormInstancelevelCur", e);
		}
		return listOfRecords;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuavbed/gettingLabels", method = RequestMethod.GET)
	public AgencyLocations gettingLabels(@RequestParam(value = "agyLocId") final String agencyLocationId) {
		AgencyLocations recordList = new AgencyLocations();
		try {
			recordList = omuavbedService.gettingLabels(agencyLocationId);
		} catch (Exception e) {
			logger.error("rgLivingUnitRecordGroup", e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuavbed/checkAllConficts", method = RequestMethod.POST)
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

}