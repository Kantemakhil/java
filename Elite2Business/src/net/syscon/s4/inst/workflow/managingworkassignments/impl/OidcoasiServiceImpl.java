package net.syscon.s4.inst.workflow.managingworkassignments.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OidcoasiOffenderAssignments;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OidcoasiOffenderAssignmentsCommitBean;
import net.syscon.s4.inst.workflow.managingworkassignments.OidcoasiRepository;
import net.syscon.s4.inst.workflow.managingworkassignments.OidcoasiService;
import net.syscon.s4.pkgs.oidcoasi.OidcoasiPkgService;

/**
 * Class OidcoasiServiceImpl
 */
@Service
public class OidcoasiServiceImpl extends BaseBusiness implements OidcoasiService {

	@Autowired
	private OidcoasiRepository oidcoasiRepository;
	
	@Autowired
	OidcoasiPkgService oidcoasiPkgService;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OidcoasiOffenderAssignments> offAsgnExecuteQuery(final OidcoasiOffenderAssignments searchRecord) {
		return oidcoasiPkgService.offenderAssignmentsQuery(searchRecord);
		//return oidcoasiRepository.offAsgnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_ASGN
	 *
	 */
	@Transactional
	public Integer offAsgnCommit(final OidcoasiOffenderAssignmentsCommitBean commitBean) {
		int liReturn = 1;
		int offenderCaseOfficersCount = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (final OidcoasiOffenderAssignments offenderAssignments : commitBean.getUpdateList()) {
				offenderAssignments.setCreateUserId(commitBean.getCreateUserId());
				offenderCaseOfficersCount = oidcoasiRepository.getOffenderCaseOfficersCount(offenderAssignments);
				if (offenderCaseOfficersCount == 0 && offenderAssignments.getConfirmationFlag().equals("Y")) {
					oidcoasiRepository.deleteOffenderCaseOfficers(offenderAssignments.getOffenderBookingId(),commitBean.getCreateUserId());
					liReturn = oidcoasiRepository.insertOffenderCaseOfficers(offenderAssignments);
				}
			}
		}

		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyLocations> rgAgyLocIdRecordGroup(final String caseloadId) {
		return oidcoasiRepository.rgAgyLocIdRecordGroup(caseloadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyLocations> rgLivingUnitCodeOneRecordGroup(final String agyLocId) {
		return oidcoasiRepository.rgLivingUnitCodeOneRecordGroup(agyLocId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyLocations> rgLivingUnitCodeTwoRecordGroup(final String agyLocId, final String livingUnitId) {
		return oidcoasiRepository.rgLivingUnitCodeTwoRecordGroup(agyLocId, livingUnitId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyLocations> rgLivingUnitCodeThreeRecordGroup(final String agyLocId, final String livingUnitId) {
		return oidcoasiRepository.rgLivingUnitCodeThreeRecordGroup(agyLocId, livingUnitId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyLocations> rgLivingUnitCodeFourRecordGroup(final String agyLocId, final String livingUnitId) {
		return oidcoasiRepository.rgLivingUnitCodeFourRecordGroup(agyLocId, livingUnitId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<StaffMembers> rgStaffIdRecordGroup(final String agyLocId) {
		return oidcoasiRepository.rgStaffIdRecordGroup(agyLocId);

	}

}