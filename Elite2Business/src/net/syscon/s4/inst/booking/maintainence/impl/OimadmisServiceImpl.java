package net.syscon.s4.inst.booking.maintainence.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CaseloadAdmAlertProfiles;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.SystemMessages;
import net.syscon.s4.inst.booking.beans.CaseloadAdmAlertProfilesCommitBean;
import net.syscon.s4.inst.booking.beans.CaseloadAdmOtherProfilesCommitBean;
import net.syscon.s4.inst.booking.maintainence.OimadmisRepository;
import net.syscon.s4.inst.booking.maintainence.OimadmisService;
import net.syscon.s4.pkgs.tag_utils.TagUtilsService;

/**
 * Class OimadmisServiceImpl
 */
@Service
public class OimadmisServiceImpl extends BaseBusiness implements OimadmisService {

	@Autowired
	private OimadmisRepository oimadmisRepository;
	@Autowired
	private TagUtilsService tagUtilsService;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<SystemMessages> caseloadAdmAlertProfilesPostQuery(final SystemMessages paramBean) {
		return oimadmisRepository.caseloadAdmAlertProfilesPostQueryPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<SystemMessages> caseloadAdmOtherProfilesPostQuery(final SystemMessages paramBean) {
		return oimadmisRepository.caseloadAdmOtherProfilesPostQuery(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CaseloadAdmAlertProfiles> caseloadAdmAlertProfilesExecuteQuery(
			final CaseloadAdmAlertProfiles searchRecord) {
		return oimadmisRepository.caseloadAdmAlertProfilesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCASELOAD_ADM_ALERT_PROFILES
	 *
	 * @throws SQLException
	 */
	@Transactional
	public String caseloadAdmAlertProfilesCommit(final CaseloadAdmAlertProfilesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (CaseloadAdmAlertProfiles obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			
			}
			liReturn = oimadmisRepository
					.caseloadAdmAlertProfilesInsertCaseloadAdmAlertProfiles(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (CaseloadAdmAlertProfiles obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimadmisRepository
					.caseloadAdmAlertProfilesUpdateCaseloadAdmAlertProfiles(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oimadmisRepository
					.caseloadAdmAlertProfilesDeleteCaseloadAdmAlertProfiles(commitBean.getDeleteList());
		}
		return String.valueOf(liReturn);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CaseloadAdmOtherProfiles> caseloadAdmOtherProfilesExecuteQuery(
			final CaseloadAdmOtherProfiles searchRecord) {
		final List<CaseloadAdmOtherProfiles> caseloadAdmOtherProfilesList = oimadmisRepository
				.caseloadAdmOtherProfilesExecuteQuery(searchRecord);
		if (!caseloadAdmOtherProfilesList.isEmpty()) {
			caseloadAdmOtherProfilesList.forEach(action -> {
				if (action != null && action.getAgyLocId() != null) {
					final String nbtLivingUnit = tagUtilsService.getLivingUnitDescp(action.getLivingUnitId());
					final Optional<String> ostr = Optional.ofNullable(nbtLivingUnit).filter(s -> !s.isEmpty());
					if (ostr.isPresent()) {
						action.setLivingUnitDesc(nbtLivingUnit);
					}
				}
				if (action != null && action.getLivingUnitId() != null) {
					action.setAvalibleBedsInLocation(
							oimadmisRepository.getAvalibleBedInTheLocation(action.getLivingUnitId()));
				}
			});
		}
		return caseloadAdmOtherProfilesList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCASELOAD_ADM_OTHER_PROFILES
	 *
	 * @throws SQLException
	 */
	@Transactional
	public String caseloadAdmOtherProfilesCommit(final CaseloadAdmOtherProfilesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			
			for (CaseloadAdmOtherProfiles obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				
			}
			liReturn = oimadmisRepository
					.caseloadAdmOtherProfilesInsertCaseloadAdmOtherProfiles(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (CaseloadAdmOtherProfiles obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimadmisRepository
					.caseloadAdmOtherProfilesUpdateCaseloadAdmOtherProfiles(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oimadmisRepository
					.caseloadAdmOtherProfilesDeleteCaseloadAdmOtherProfiles(commitBean.getDeleteList());
		}
		return String.valueOf(liReturn);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<SystemMessages> rgSystemMsgRecordGroup() {
		return oimadmisRepository.rgSystemMsgRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<SystemMessages> rgOtherSystemMsgRecordGroup() {
		final List<SystemMessages> systemMessagesList = oimadmisRepository.rgOtherSystemMsgRecordGroup();
		if (!systemMessagesList.isEmpty()) {
			systemMessagesList.forEach(action -> {
				if (action != null && action.getMessageNumber() != null) {
					action.setCode(action.getMessageNumber().toString());
				}
			});
		}
		return systemMessagesList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> rgAgencyLocationsRecordGroup(final String caseloadId) {
		final List<AgencyLocations> agencyLocationsList = oimadmisRepository.rgAgencyLocationsRecordGroup(caseloadId);
		if (!agencyLocationsList.isEmpty()) {
			agencyLocationsList.forEach(action -> {
				if (action.getAgyLocId() != null) {
					action.setCode(action.getAgyLocId());
				}
				if (action != null && action.getSealFlag() != null && action.getSealFlag().equals("Y")) {
					action.setCanDisplay(true);
				} else {
					action.setCanDisplay(false);
				}
			});
		}
		return agencyLocationsList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<LivingUnits> rgLivingUnitsRecordGroup() {
		return oimadmisRepository.rgLivingUnitsRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgAlertRecordGroup() {
		return oimadmisRepository.rgAlertRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgAlertCodeRecordGroup(final String alerType) {
		 List<ReferenceCodes> refList = oimadmisRepository.rgAlertCodeRecordGroup(alerType);
		if(Optional.ofNullable(refList).isPresent()) {
			refList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return refList;
	}

}
