package net.syscon.s4.inst.workflow.managingworkassignments;

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
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OidcoasiOffenderAssignments;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OidcoasiOffenderAssignmentsCommitBean;

@EliteController
public class OidcoasiController {
	@Autowired
	private OidcoasiService oidcoasiService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidcoasiController.class.getName());

	/**
	 * getting rgAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcoasi/rgAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocIdRecordGroup(@RequestParam("caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oidcoasiService.rgAgyLocIdRecordGroup(caseloadId);
		} catch (final Exception e) {
			logger.error("Exception : Oidcoasi:", e);
		}
		return recordList;
	}

	/**
	 * getting rgLivingUnitCode1 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcoasi/rgLivingUnitCodeOneRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgLivingUnitCodeOneRecordGroup(@RequestParam("agyLocId") final String agyLocId) {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oidcoasiService.rgLivingUnitCodeOneRecordGroup(agyLocId);
		} catch (final Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("Exception : Oidcoasi:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgLivingUnitCode2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcoasi/rgLivingUnitCodeTwoRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgLivingUnitCodeTwoRecordGroup(@RequestParam("agyLocId") final String agyLocId,
			@RequestParam("livingUnitId") final String livingUnitId) {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oidcoasiService.rgLivingUnitCodeTwoRecordGroup(agyLocId, livingUnitId);
		} catch (final Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("Exception : Oidcoasi:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgLivingUnitCode3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcoasi/rgLivingUnitCodeThreeRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgLivingUnitCodeThreeRecordGroup(@RequestParam("agyLocId") final String agyLocId,
			@RequestParam("livingUnitId") final String livingUnitId) {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oidcoasiService.rgLivingUnitCodeThreeRecordGroup(agyLocId, livingUnitId);
		} catch (final Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("Exception : Oidcoasi:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgLivingUnitCode4 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcoasi/rgLivingUnitCodeFourRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgLivingUnitCodeFourRecordGroup(@RequestParam("agyLocId") final String agyLocId,
			@RequestParam("livingUnitId") final String livingUnitId) {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oidcoasiService.rgLivingUnitCodeFourRecordGroup(agyLocId, livingUnitId);
		} catch (final Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("Exception : Oidcoasi:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStaffId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcoasi/rgStaffIdRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffIdRecordGroup(@RequestParam("agyLocId") final String agyLocId) {
		List<StaffMembers> recordList = new ArrayList<>();
		try {
			recordList = oidcoasiService.rgStaffIdRecordGroup(agyLocId);
		} catch (final Exception e) {
			logger.error("Exception : Oidcoasi:", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcoasi/offAsgnExecuteQuery", method = RequestMethod.POST)
	public List<OidcoasiOffenderAssignments> offAsgnExecuteQuery(
			@RequestBody final OidcoasiOffenderAssignments searchBean) {
		List<OidcoasiOffenderAssignments> searchResult = new ArrayList<>();
		try {
			searchResult = oidcoasiService.offAsgnExecuteQuery(searchBean);
		} catch (final Exception e) {
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
	@RequestMapping(value = "/oidcoasi/offAsgnCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offAsgnCommit(@RequestBody final OidcoasiOffenderAssignmentsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oidcoasiService.offAsgnCommit(commitBean);
		} catch (final Exception e) {
			logger.error("Exception : Oidcoasi", e);
		}
		return liReturn;
	}

}