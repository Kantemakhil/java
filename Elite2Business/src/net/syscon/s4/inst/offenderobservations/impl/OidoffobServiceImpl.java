package net.syscon.s4.inst.offenderobservations.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.intakeclosure.OcdintakRepository;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.careinplacement.beans.OffObsPeriodChecks;
import net.syscon.s4.inst.careinplacement.beans.OffObsPeriodCheckscommitBean;
import net.syscon.s4.inst.offenderobservations.OidoffobRepository;
import net.syscon.s4.inst.offenderobservations.OidoffobService;
import net.syscon.s4.inst.offenderobservations.beans.OffenderObservationPeriods;
import net.syscon.s4.inst.offenderobservations.beans.OffenderObservationPeriodsCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.OimoffobRepository;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsCharacteristics;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsCharacteristicsCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationTypes;

@Service
public class OidoffobServiceImpl extends BaseBusiness implements OidoffobService {
	@Autowired
	private OidoffobRepository oidoffobRepository;

	@Autowired
	private OimoffobRepository oimoffobRepository;
	@Autowired
	private OcdintakRepository ocdintakRepository;
	@Override
	public List<OffenderObservationTypes> getObservatioTypeData(OffenderObservationTypes searchBean) {
		return oidoffobRepository.getObservatioTypeData(searchBean);
	}

	@Override
	public List<OffenderObservationPeriods> getOffenderPeriodExecuteQuery(OffenderObservationPeriods searchBean) {
		return oidoffobRepository.getOffenderPeriodExecuteQuery(searchBean);
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<OffenderObservationPeriods> offenderObservationPeriodDataCommit(
			OffenderObservationPeriodsCommitBean commitBean) {
		final List<OffenderObservationPeriods> liReturnData = new ArrayList<>();
		List<OffObsPeriodChecks> checkInsertList = new ArrayList<OffObsPeriodChecks>();
		final OffenderObservationPeriods sentenceterms = new OffenderObservationPeriods();
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffenderObservationPeriods obj : commitBean.getInsertList()) {
				OffObsPeriodChecks obbcheck = new OffObsPeriodChecks();
				obj.setCreateUserId(commitBean.getCreateUserId());
				obbcheck.setCreateUserId(commitBean.getCreateUserId());
				obbcheck.setOffenderBookId(obj.getOffenderBookId().longValue());

				BigDecimal obsPeriodId = new BigDecimal(oidoffobRepository.getSequence());
				obbcheck.setScheduleDatetime(obj.getScheduleDate());
				obj.setObsPeriodId(obsPeriodId);
				BigDecimal obsPeriodSeq =oidoffobRepository.getMaximumObsPeriodSeqBookId(obj.getOffenderBookId());
				obj.setObsPeriodSeq(obsPeriodSeq);
				obbcheck.setObsPeriodId(obsPeriodId.longValue());
				checkInsertList.add(obbcheck);
			}
			liReturn = oidoffobRepository.offenderObservationPeriodInsertData(commitBean.getInsertList());
			if (liReturn == 1) {
				liReturn = oidoffobRepository.offenderObservationPeriodCheckInsertData(checkInsertList);
			}

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffenderObservationPeriods obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidoffobRepository.offenderObservationPeriodUpdateData(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidoffobRepository.offenderObservationPeriodDeleteData(commitBean.getDeleteList());
		}
		sentenceterms.setReturnedOutput(BigDecimal.valueOf(liReturn));
		liReturnData.add(sentenceterms);
		return liReturnData;
	}

	@Override
	public List<OffObsPeriodChecks> getOffenderPeriodCheckExecuteQuery(OffObsPeriodChecks searchBean) {
		return oidoffobRepository.getOffenderPeriodCheckExecuteQuery(searchBean);
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<OffObsPeriodChecks> offenderObservationCheckDataCommit(OffObsPeriodCheckscommitBean commitBean) {
		final List<OffObsPeriodChecks> liReturnData = new ArrayList<>();
		List<OffObsPeriodChecks> checkInsertList = new ArrayList<OffObsPeriodChecks>();
		OffObsPeriodChecks obbcheck = new OffObsPeriodChecks();
		final OffObsPeriodChecks sentenceterms = new OffObsPeriodChecks();
		Integer liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffObsPeriodChecks obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				obbcheck.setOffenderBookId(obj.getOffenderBookId());
				obbcheck.setCreateUserId(obj.getCreateUserId());
				obbcheck.setScheduleDatetime(obj.getNextScheduleDate());
				obbcheck.setObsPeriodId(obj.getObsPeriodId());
				checkInsertList.add(obbcheck);
				Integer staffId = ocdintakRepository.oldContactGetStaffId(commitBean.getCreateUserId());
				obj.setPerformingStaffId(BigDecimal.valueOf(staffId));
			}

			liReturn = oidoffobRepository.offenderObservationCheckUpdateData(commitBean.getUpdateList());
			if (liReturn == 1) {
				liReturn = oidoffobRepository.offenderObservationPeriodCheckInsertData(checkInsertList);
			}
		}
		sentenceterms.setReturnedOutput(BigDecimal.valueOf(liReturn));
		liReturnData.add(sentenceterms);
		return liReturnData;

	}
	
	@Override
	public List<OffObsCharacteristics> additionalCheckCharxecuteQuery(OffObsCharacteristics searchBean) {
		List<OffObsCharacteristics> returnList = new ArrayList<OffObsCharacteristics>();
		List<String> cellCondList = new ArrayList<>();
		List<String> notInCellList = new ArrayList<>();
		List<String> activityList = new ArrayList<>();
		List<String> commonDetCatList = new ArrayList<>();
		List<String> officerNotesList = new ArrayList<>();
		returnList=oidoffobRepository.getAdditionalCherctersticsExecuteQuery(searchBean);
		/*
		 * if(returnList.isEmpty()) { returnList =
		 * oimoffobRepository.observationCharectersticExecuteQuery(searchBean); }
		 */ 	
		if(!returnList.isEmpty()) {
			
			for (OffObsCharacteristics offObsCharacteristics : returnList) {
				if ("CELL_CNDITNS".equals(offObsCharacteristics.getDetailType())) {
					cellCondList.add(offObsCharacteristics.getDetailCode());
				} else if ("NOT_IN_CELL".equals(offObsCharacteristics.getDetailType())) {
					notInCellList.add(offObsCharacteristics.getDetailCode());
					
				} else if ("ACTIVITY".equals(offObsCharacteristics.getDetailType())) {
					activityList.add(offObsCharacteristics.getDetailCode());
				} else if ("COM_DET_CAT".equals(offObsCharacteristics.getDetailType())) {
					commonDetCatList.add(offObsCharacteristics.getDetailCode());
					
				} else if ("OFICER_NOTES".equals(offObsCharacteristics.getDetailType())) {
					officerNotesList.add(offObsCharacteristics.getDetailCode());
				}
				offObsCharacteristics.setCellConditionList(cellCondList);
				offObsCharacteristics.setNotInCellList(notInCellList);
				offObsCharacteristics.setActivityList(activityList);
				offObsCharacteristics.setCommonDetailsCatList(commonDetCatList);
				offObsCharacteristics.setOfficerNotesList(officerNotesList);
			}
		}
		return returnList;
	}

	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<OffObsCharacteristics> saveAdditionalCharecterData(OffObsCharacteristicsCommitBean commitBean) {
		final List<OffObsCharacteristics> liReturnData = new ArrayList<>();
		final OffObsCharacteristics sentenceterms = new OffObsCharacteristics();
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffObsCharacteristics obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				Integer staffId = ocdintakRepository.oldContactGetStaffId(commitBean.getCreateUserId());
				obj.setReportingStaffId(BigDecimal.valueOf(staffId));
			}
			liReturn = oidoffobRepository.offObsDeleteAdditionalCharctData(commitBean.getInsertList().get(0).getCheckId(),commitBean.getCreateUserId());

			liReturn = oidoffobRepository.offObsCharacteristicsInsertCharctData(commitBean.getInsertList());

		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidoffobRepository.offObsDeleteAddCharData(commitBean.getDeleteList());
		}
		// updateRecords
		sentenceterms.setReturnedOutput(BigDecimal.valueOf(liReturn));
		liReturnData.add(sentenceterms);
		return liReturnData;
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer saveOffenderObservationCheckComment(OffObsPeriodCheckscommitBean commitBean) {
		Integer liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffObsPeriodChecks obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				Integer staffId = ocdintakRepository.oldContactGetStaffId(commitBean.getCreateUserId());
				obj.setPerformingStaffId(BigDecimal.valueOf(staffId));
			}

			liReturn = oidoffobRepository.saveOffenderObservationCheckComment(commitBean.getUpdateList());
		}
		return liReturn;
	}

	@Override
	public List<OffObsPeriodChecks> getCommentExecuteQuery(OffObsPeriodChecks searchBean) {
		return oidoffobRepository.getCommentExecuteQuery(searchBean);
	}

	@Override
	public List<ReferenceCodes> cellCondiLinkDomainRecordGroup(String observationType) {
		return oidoffobRepository.cellCondiLinkDomainRecordGroup(observationType);
	}
	
	@Override
	public List<ReferenceCodes> activityLinkDomainRecordGroup(String observationType) {
		return oidoffobRepository.activityLinkDomainRecordGroup(observationType);
	}
	
	@Override
	public List<ReferenceCodes> commDetailLinkDomainRecordGroup(String observationType) {
		return oidoffobRepository.commDetailLinkDomainRecordGroup(observationType);
	}
	
	@Override
	public List<ReferenceCodes> notInLinkDomainRecordGroup(String observationType) {
		return oidoffobRepository.notInLinkDomainRecordGroup(observationType);
	}
	
	@Override
	public Integer getOffenderLivningUnitIdCount(BigDecimal offenderBookId) {
		return oidoffobRepository.getOffenderLivningUnitIdCount(offenderBookId);
	}

	@Override
	public List<OffenderObservationTypes> getObservationTypeRecordGroup() {
		List<OffenderObservationTypes> returnList=new ArrayList<OffenderObservationTypes>();
		returnList = oidoffobRepository.getObservationTypeRecordGroup();
		for (OffenderObservationTypes referenceCodes : returnList) {
			if("Y".equals(referenceCodes.getActiveFlag())) {
				referenceCodes.setCanDisplay(true);
			} else {
				referenceCodes.setCanDisplay(false);
			}
		}
		return returnList;
	}
	
	@Override
	public Integer getOffenderLivningUnitIdCountNotInLocation(BigDecimal offenderBookId, String userId, String agyLocId) {
		return oidoffobRepository.getOffenderLivningUnitIdCountNotInLocation(offenderBookId,userId, agyLocId);
	}

	@Override
	public Integer getCurrentStaffId(String userName) {
		return oidoffobRepository.getCurrentStaffId(userName);
	}
	
	@Override
	public List<SystemProfiles> sysPflExecuteQuery() {
		return oidoffobRepository.sysPflExecuteQuery();

	}
}
