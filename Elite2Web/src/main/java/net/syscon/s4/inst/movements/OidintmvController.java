package net.syscon.s4.inst.movements;

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
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;
import net.syscon.s4.inst.movements.beans.VIntLocUsageLocations;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

@EliteController
public class OidintmvController {
	@Autowired
	private OidintmvService oidintmvService;
	
	@Autowired
	private OiintlocService oiintlocService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidintmvController.class.getName());

	/**
	 * getting rgEstablishment LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/rgEstablishmentRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgEstablishmentRecordGroup(
			@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		final AgencyLocations obj = new AgencyLocations();
		try {
			recordList = oidintmvService.rgEstablishmentRecordGroup(caseLoadId);
		} catch (Exception e) {
			logger.error("rgEstablishmentRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFromHlocLevel1 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/rgFromHlocLevel1RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgFromHlocLevel1RecordGroup(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidintmvService.rgFromHlocLevelOneRecordGroup(agyLocId);
		} catch (Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("rgFromHlocLevel1RecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFromHlocLevel2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/rgFromHlocLevel2RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgFromHlocLevelTwoRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId,
			@RequestParam(value = "fromLocLevelOne") final String fromLocLevelOne) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidintmvService.rgFromHlocLevelTwoRecordGroup(agyLocId, fromLocLevelOne);
		} catch (Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("rgFromHlocLevel2RecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFromHlocLevel3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/rgFromHlocLevel3RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgFromHlocLevelThreeRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId,
			@RequestParam(value = "fromLocLevelTwo") final String fromLocLevelTwo) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oidintmvService.rgFromHlocLevelThreeRecordGroup(agyLocId, fromLocLevelTwo);
		} catch (Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("rgFromHlocLevel3RecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFromIlocLevel1 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/rgFromIlocLevel1RecordGroup", method = RequestMethod.GET)
	public List<VIntLocSummaries> rgFromIlocLevelOneRecordGroup(
			@RequestParam(value = "agyLocId") final String agyLocId) {
		List<VIntLocSummaries> recordList = new ArrayList<VIntLocSummaries>();
		try {
			recordList = oidintmvService.rgFromIlocLevelOneRecordGroup(agyLocId);
		} catch (Exception e) {
			final VIntLocSummaries obj = new VIntLocSummaries();
			logger.error("rgFromIlocLevel1RecordGroup:", e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFromIlocLevel2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/rgFromIlocLevel2RecordGroup", method = RequestMethod.GET)
	public List<VIntLocSummaries> rgFromIlocLevelTwoRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId,
			@RequestParam(value = "fromILocLevelOneId") final String fromILocLevelOneId) {
		List<VIntLocSummaries> recordList = new ArrayList<VIntLocSummaries>();
		try {
			recordList = oidintmvService.rgFromIlocLevelTwoRecordGroup(agyLocId, fromILocLevelOneId);
		} catch (Exception e) {
			final VIntLocSummaries obj = new VIntLocSummaries();
			logger.error("rgFromIlocLevel2RecordGroup:", e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFromIlocLevel3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/rgFromIlocLevel3RecordGroup", method = RequestMethod.GET)
	public List<VIntLocSummaries> rgFromIlocLevelThreeRecordGroup(
			@RequestParam(value = "agyLocId") final String agyLocId,
			@RequestParam(value = "fromILocLevelTwoId") final String fromILocLevelTwoId) {
		List<VIntLocSummaries> recordList = new ArrayList<VIntLocSummaries>();
		try {
			recordList = oidintmvService.rgFromIlocLevelThreeRecordGroup(agyLocId, fromILocLevelTwoId);
		} catch (Exception e) {
			final VIntLocSummaries obj = new VIntLocSummaries();
			logger.error("rgFromIlocLevel3RecordGroup", e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgToIlocLevel1 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/rgToIlocLevel1RecordGroup", method = RequestMethod.GET)
	public List<VIntLocSummaries> rgToIlocLevelOneRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId,
			@RequestParam(value = "fromILocLevelOneId") final String fromILocLevelOneId,
			@RequestParam(value = "fromHLocLevelOne") final String fromHLocLevelOne) {
		List<VIntLocSummaries> recordList = new ArrayList<VIntLocSummaries>();
		try {
			recordList = oidintmvService.rgToIlocLevelOneRecordGroup(agyLocId, fromILocLevelOneId, fromHLocLevelOne);
		} catch (Exception e) {
			final VIntLocSummaries obj = new VIntLocSummaries();
			logger.error("rgToIlocLevel1RecordGroup: ", e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgToIlocLevel2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/rgToIlocLevel2RecordGroup", method = RequestMethod.GET)
	public List<VIntLocSummaries> rgToIlocLevelTwoRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId,
			@RequestParam(value = "toILocLevelOneId") final String toILocLevelOneId) {
		List<VIntLocSummaries> recordList = new ArrayList<VIntLocSummaries>();
		try {
			recordList = oidintmvService.rgToIlocLevelTwoRecordGroup(agyLocId, toILocLevelOneId);
		} catch (Exception e) {
			final VIntLocSummaries obj = new VIntLocSummaries();
			logger.error("rgToIlocLevel2RecordGroup: ", e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgToIlocLevel3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/rgToIlocLevel3RecordGroup", method = RequestMethod.GET)
	public List<VIntLocSummaries> rgToIlocLevelThreeRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId,
			@RequestParam(value = "toILocLevelTwoId") final String toILocLevelTwoId) {
		List<VIntLocSummaries> recordList = new ArrayList<VIntLocSummaries>();
		try {
			recordList = oidintmvService.rgToIlocLevelThreeRecordGroup(agyLocId, toILocLevelTwoId);
		} catch (Exception e) {
			final VIntLocSummaries obj = new VIntLocSummaries();
			logger.error("rgToIlocLevel3RecordGroup: ", e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSchType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/rgSchTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSchTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidintmvService.rgSchTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgSchTypeRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSchReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/rgSchReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSchReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidintmvService.rgSchReasonRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgSchReasonRecordGroup: ", e);
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
	@RequestMapping(value = "/oidintmv/offBlkExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderAllSchedules> offBlkExecuteQuery(@RequestBody final VOffenderAllSchedules searchBean) {
		List<VOffenderAllSchedules> searchResult = new ArrayList<>();
		try {
			searchResult = oidintmvService.offBlkExecuteQuery(searchBean);
		} catch (Exception e) {
			final VOffenderAllSchedules bean = new VOffenderAllSchedules();
			logger.error("offBlkExecuteQuery: ", e);
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/offBlkCommit", method = RequestMethod.POST)
	public @ResponseBody List<VOffenderAllSchedules> offBlkCommit(
			@RequestBody final VOffenderAllSchedulesCommitBean commitBean) {
		List<VOffenderAllSchedules> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidintmvService.offBlkCommit(commitBean);
		} catch (Exception e) {
			logger.error("offBlkCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting labelsrgToIlocLevelTwoRecordGroup
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/getLabels", method = RequestMethod.GET)
	public LivingUnits getLabels(@RequestParam(value = "agyLocId") final String agyLocId) {
		LivingUnits recordList = new LivingUnits();
		try {
			recordList = oidintmvService.getLabels(agyLocId);
		} catch (Exception e) {
			logger.error("getLabels: ", e);
		}
		return recordList;
	}

	/**
	 * isOffenderExists
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/isOffenderExists", method = RequestMethod.GET)
	public Integer isOffenderExists(@RequestParam(value = "offIdDisplay") final String offIdDisplay) {
		Integer recordList = 0;
		try {
			recordList = oidintmvService.isOffenderExists(offIdDisplay);
		} catch (Exception e) {
			logger.error("getLabels: ", e);
		}
		return recordList;
	}

	/**
	 * getOffenderFullDetails
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/getOffenderFullDetails", method = RequestMethod.POST)
	public VHeaderBlock getOffenderDetails(@RequestBody final VHeaderBlock paramBean) {
		VHeaderBlock recordList = new VHeaderBlock();
		try {
			recordList = oidintmvService.getOffenderDetails(paramBean);
		} catch (Exception e) {
			logger.error("getOffenderDetails: ", e);
			recordList = new VHeaderBlock();
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/rgMovmentTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgMovmentTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidintmvService.rgMovmentTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}
	
	/**
	 * getting rgUsages LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/rgUsagesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgUsagesRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oiintlocService.rgUsagesRecordGroup();
		} catch (Exception e) {
			logger.error("rgUsagesRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link VIntLocUsageLocations}
	 * @return a list of the VIntLocUsageLocations {@link VIntLocUsageLocations} for the matched VIntLocUsageLocations
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidintmv/intLocExecuteQuery", method = RequestMethod.POST)
	public List<VIntLocUsageLocations> intLocExecuteQuery(@RequestBody final VIntLocUsageLocations searchBean) {
		List<VIntLocUsageLocations> searchResult = new ArrayList<>();
		final VIntLocUsageLocations bean = new VIntLocUsageLocations();
		try {
			searchResult = oiintlocService.intLocExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("intLocExecuteQuery: ", e);
			searchResult.add(bean);
		}
		return searchResult;
	}
}