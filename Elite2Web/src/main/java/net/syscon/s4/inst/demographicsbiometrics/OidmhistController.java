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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderMilitaryDiscActions;
import net.syscon.s4.im.beans.OffenderMilitaryDiscActionsCommitBean;
import net.syscon.s4.im.beans.OffenderMilitaryRecords;
import net.syscon.s4.im.beans.OffenderMilitaryRecordsCommitBean;
import net.syscon.s4.im.beans.OffenderMilitaryTechSpecs;
import net.syscon.s4.im.beans.OffenderMilitaryTechSpecsCommitBean;
import net.syscon.s4.im.beans.OffenderMilitaryWarZones;
import net.syscon.s4.im.beans.OffenderMilitaryWarZonesCommitBean;

/**
 * Class OidmhistController
 */
@EliteController
public class OidmhistController {
	@Autowired
	private OidmhistService oidmhistService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidmhistController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmhist/rgWarZoneRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgWarZoneRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidmhistService.rgWarZoneRecordGroup();
		} catch (Exception e) {
			logger.error("rgWarZoneRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmhist/rgMltyTechRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgMltyTechRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidmhistService.rgMltyTechRecordGroup();
		} catch (Exception e) {
			logger.error("rgMltyTechRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmhist/rgMilitaryRankRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgMilitaryRankRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidmhistService.rgMilitaryRankRecordGroup();
		} catch (Exception e) {
			logger.error("rgMilitaryRankRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmhist/rgMilitaryDischargeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgMilitaryDischargeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidmhistService.rgMilitaryDischargeRecordGroup();
		} catch (Exception e) {
			logger.error("rgMilitaryDischargeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmhist/rgMilitaryBranchRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgMilitaryBranchRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidmhistService.rgMilitaryBranchRecordGroup();
		} catch (Exception e) {
			logger.error("rgMilitaryBranchRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmhist/rgDisciplinaryActionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgDisciplinaryActionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidmhistService.rgDisciplinaryActionRecordGroup();
		} catch (Exception e) {
			logger.error("rgDisciplinaryActionRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmhist/rgHighstRankRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgHighstRankRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidmhistService.rgHighstRankRecordGroup();
		} catch (Exception e) {
			logger.error("rgHighstRankRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<OffenderMilitaryRecords>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmhist/offMrExecuteQuery", method = RequestMethod.POST)
	public List<OffenderMilitaryRecords> offMrExecuteQuery(@RequestBody final OffenderMilitaryRecords searchBean) {
		List<OffenderMilitaryRecords> searchResult = new ArrayList<>();
		try {
			searchResult = oidmhistService.offMrExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offMrExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidmhist/offMrCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offMrCommit(@RequestBody final OffenderMilitaryRecordsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidmhistService.offMrCommit(commitBean);
		} catch (Exception e) {
			logger.error("offMrCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 * @return List<OffenderMilitaryDiscActions>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmhist/disActExecuteQuery", method = RequestMethod.POST)
	public List<OffenderMilitaryDiscActions> disActExecuteQuery(
			@RequestBody final OffenderMilitaryDiscActions searchBean) {
		List<OffenderMilitaryDiscActions> searchResult = new ArrayList<>();
		try {
			searchResult = oidmhistService.disActExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("disActExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidmhist/disActCommit", method = RequestMethod.POST)
	public @ResponseBody Integer disActCommit(@RequestBody final OffenderMilitaryDiscActionsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidmhistService.disActCommit(commitBean);
		} catch (Exception e) {
			logger.error("disActCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 * @return List<OffenderMilitaryTechSpecs>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmhist/techSpecExecuteQuery", method = RequestMethod.POST)
	public List<OffenderMilitaryTechSpecs> techSpecExecuteQuery(
			@RequestBody final OffenderMilitaryTechSpecs searchBean) {
		List<OffenderMilitaryTechSpecs> searchResult = new ArrayList<>();
		try {
			searchResult = oidmhistService.techSpecExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("techSpecExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidmhist/techSpecCommit", method = RequestMethod.POST)
	public @ResponseBody Integer techSpecCommit(@RequestBody final OffenderMilitaryTechSpecsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidmhistService.techSpecCommit(commitBean);
		} catch (Exception e) {
			logger.error("techSpecCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 * @return List<OffenderMilitaryWarZones>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmhist/warZonesExecuteQuery", method = RequestMethod.POST)
	public List<OffenderMilitaryWarZones> warZonesExecuteQuery(@RequestBody final OffenderMilitaryWarZones searchBean) {
		List<OffenderMilitaryWarZones> searchResult = new ArrayList<>();
		try {
			searchResult = oidmhistService.warZonesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("warZonesExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidmhist/warZonesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer warZonesCommit(@RequestBody final OffenderMilitaryWarZonesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidmhistService.warZonesCommit(commitBean);
		} catch (Exception e) {
			logger.error("warZonesCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 * @return List<SystemProfiles>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmhist/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidmhistService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("sysPflExecuteQuery", e);
		}
		return searchResult;
	}

}