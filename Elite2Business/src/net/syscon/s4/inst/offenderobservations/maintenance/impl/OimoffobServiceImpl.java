package net.syscon.s4.inst.offenderobservations.maintenance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.offenderobservations.maintenance.OimoffobRepository;
import net.syscon.s4.inst.offenderobservations.maintenance.OimoffobService;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsCharacteristics;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsCharacteristicsCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsZoneDetails;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsZoneDetailsCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationTypes;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationTypesCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationTypesSaveCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationZones;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationZonesCommitBean;

@Service
public class OimoffobServiceImpl extends BaseBusiness implements OimoffobService {
	@Autowired
	private OimoffobRepository oimoffobRepository;

	@Override
	public List<OffenderObservationTypes> observationTypesExecuteQuery() {
		List<OffenderObservationTypes> observationTypeList = new ArrayList<OffenderObservationTypes>();
		observationTypeList = oimoffobRepository.observationTypesExecuteQuery();
		for (OffenderObservationTypes offenderObservationTypes : observationTypeList) {
			List<OffObsCharacteristics> charecterList = new ArrayList<OffObsCharacteristics>();
			OffObsCharacteristics obj = new OffObsCharacteristics();
			obj.setObservationType(offenderObservationTypes.getObservationType());
			charecterList = observationCharectersticExecuteQuery(obj);
			if (!charecterList.isEmpty()) {
				if ("Y".equals(offenderObservationTypes.getCellConditionFlag())) {
					offenderObservationTypes.setCellConditionList(charecterList.get(0).getCellConditionList());
				}
				if ("Y".equals(offenderObservationTypes.getActivityFlag())) {
					offenderObservationTypes.setActivityList(charecterList.get(0).getActivityList());
				}

				if ("Y".equals(offenderObservationTypes.getDemeanorFlag())) {
					offenderObservationTypes.setCommonDetailsCatList(charecterList.get(0).getCommonDetailsCatList());
				}
				if ("Y".equals(offenderObservationTypes.getNotInCellFlag())) {
					offenderObservationTypes.setNotInCellList(charecterList.get(0).getNotInCellList());
				}
			}
		}
		return observationTypeList;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<OffenderObservationTypes> obserVationTypeCommit(OffenderObservationTypesCommitBean commitBean) {
		final List<OffenderObservationTypes> liReturnData = new ArrayList<>();
		final OffenderObservationTypes sentenceterms = new OffenderObservationTypes();
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffenderObservationTypes obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimoffobRepository.offenderObservationInsertData(commitBean.getInsertList());

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffenderObservationTypes obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimoffobRepository.offenderObservationUpdateData(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (OffenderObservationTypes obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				Integer deleteCount = oimoffobRepository.getObservationPeriodDeleteCount(obj);
				if (deleteCount > 0) {
					sentenceterms.setReturnedOutput(BigDecimal.valueOf(3));
					liReturnData.add(sentenceterms);
					return liReturnData;
				}
			}
			liReturn = oimoffobRepository.offenderObservationDeleteData(commitBean.getDeleteList());
		}
		sentenceterms.setReturnedOutput(BigDecimal.valueOf(liReturn));
		liReturnData.add(sentenceterms);
		return liReturnData;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<OffenderObservationZones> zoneDataSaveForm(OffenderObservationZonesCommitBean commitBean) {
		final List<OffenderObservationZones> liReturnData = new ArrayList<>();
		final OffenderObservationZones sentenceterms = new OffenderObservationZones();
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffenderObservationZones obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimoffobRepository.offenderObservationInsertZoneData(commitBean.getInsertList());

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffenderObservationZones obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimoffobRepository.offenderObservationUpdateZoneData(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimoffobRepository.offenderObservationDeleteZoneData(commitBean.getDeleteList());
		}
		sentenceterms.setReturnedOutput(BigDecimal.valueOf(liReturn));
		liReturnData.add(sentenceterms);
		return liReturnData;
	}

	@Override
	public List<OffenderObservationZones> getZoneDetailsExecuteQuery(OffenderObservationZones searchBean) {
		return oimoffobRepository.getZoneDetailsExecuteQuery(searchBean);
	}

	@Override
	public List<OffObsZoneDetails> getZoneDetailsHousingExecuteQuery(OffObsZoneDetails searchBean) {
		List<OffObsZoneDetails> returnList = oimoffobRepository.getZoneDetailsHousingExecuteQuery(searchBean);
		for (OffObsZoneDetails obj : returnList) {
			List<OffObsZoneDetails> housingCodeDescObject=new ArrayList<OffObsZoneDetails>();
			housingCodeDescObject=oimoffobRepository.getHousingLocDescription(obj.getInternalLocationId());
			if(!housingCodeDescObject.isEmpty()) {
				if(housingCodeDescObject.get(0)!=null && housingCodeDescObject.get(0).getLocationDescription()!=null) {
					 obj.setLocationDescription(housingCodeDescObject.get(0).getLocationDescription());
				}
				if(housingCodeDescObject.get(0)!=null && housingCodeDescObject.get(0).getLocationCode()!=null) {
					 obj.setLocationCode(housingCodeDescObject.get(0).getLocationCode());	
				}
			}
			
		}
		return returnList;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<OffObsZoneDetails> zoneHousingDataCommitForm(OffObsZoneDetailsCommitBean commitBean) {
		final List<OffObsZoneDetails> liReturnData = new ArrayList<>();
		final OffObsZoneDetails sentenceterms = new OffObsZoneDetails();
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffObsZoneDetails obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimoffobRepository.offenderObservationInsertZoneHousingData(commitBean.getInsertList());

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffObsZoneDetails obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimoffobRepository.offenderObservationUpdateZoneHousingData(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimoffobRepository.offenderObservationDeleteZoneHousingData(commitBean.getDeleteList());
		}
		sentenceterms.setReturnedOutput(BigDecimal.valueOf(liReturn));
		liReturnData.add(sentenceterms);
		return liReturnData;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<OffObsCharacteristics> saveCharecterDetails(OffObsCharacteristicsCommitBean commitBean) {
		final List<OffObsCharacteristics> liReturnData = new ArrayList<>();
		final OffObsCharacteristics sentenceterms = new OffObsCharacteristics();
		Integer liReturn = 0;
		if ("N".equals(commitBean.getObservationCheckDetailTypeBean().getCellConditionFlag())) {
			deleteChildRecordCellCondData(commitBean.getObservationCheckDetailTypeBean().getObservationType(),
					"CELL_CNDITNS");
		}
		if ("N".equals(commitBean.getObservationCheckDetailTypeBean().getActivityFlag())) {
			deleteChildRecordCellCondData(commitBean.getObservationCheckDetailTypeBean().getObservationType(),
					"ACTIVITY");
		}
		if ("N".equals(commitBean.getObservationCheckDetailTypeBean().getDemeanorFlag())) {
			deleteChildRecordCellCondData(commitBean.getObservationCheckDetailTypeBean().getObservationType(),
					"COM_DET_CAT");
		}
		if ("N".equals(commitBean.getObservationCheckDetailTypeBean().getNotInCellFlag())) {
			deleteChildRecordCellCondData(commitBean.getObservationCheckDetailTypeBean().getObservationType(),
					"NOT_IN_CELL");
		}
		liReturn = oimoffobRepository.updateCharecterFlagsData(commitBean.getObservationCheckDetailTypeBean());
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffObsCharacteristics obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				liReturn = oimoffobRepository.offObsDeleteCharctData(obj.getObservationType(),
						obj.getCharacteristicsType());
			}

			liReturn = oimoffobRepository.offObsCharacteristicsInsertCharctData(commitBean.getInsertList());

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffObsCharacteristics obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimoffobRepository.offObsCharacteristicsUpdateCharctData(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			liReturn = oimoffobRepository.offObsCharacteristicsDeleteCharctData(commitBean.getDeleteList());
		}
		sentenceterms.setReturnedOutput(BigDecimal.valueOf(liReturn));
		liReturnData.add(sentenceterms);
		return liReturnData;
	}

	public Integer deleteChildRecordCellCondData(String ObservationType, String charectersticType) {
		Integer liReturn = oimoffobRepository.offObsDeleteCharctData(ObservationType, charectersticType);
		return liReturn;
	}

	@Override
	public List<OffObsCharacteristics> observationCharectersticExecuteQuery(OffObsCharacteristics searchBean) {
		List<OffObsCharacteristics> returnList = new ArrayList<OffObsCharacteristics>();
		List<String> cellCondList = new ArrayList<>();
		List<String> notInCellList = new ArrayList<>();
		List<String> activityList = new ArrayList<>();
		List<String> commonDetCatList = new ArrayList<>();
		List<String> officerNotesList = new ArrayList<>();
		returnList = oimoffobRepository.observationCharectersticExecuteQuery(searchBean);
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
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup(String userName) {
		return oimoffobRepository.rgAgyLocRecordGroup(userName);

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<OffenderObservationTypes> saveCommonDetails(OffenderObservationTypesSaveCommitBean commitBean) {
		List<OffenderObservationTypes> returnList=new ArrayList<OffenderObservationTypes>();		
		final OffenderObservationTypes offObsTypeObj = new OffenderObservationTypes();
		List<OffObsCharacteristics> detailTypeCodeList=new ArrayList<OffObsCharacteristics>();
		List<OffObsCharacteristics> detailTypeCodeUpdateList=new ArrayList<OffObsCharacteristics>();
		Integer liReturn = 0;				
			if(commitBean.getOffednerObservationCommitList().getInsertList() != null && !commitBean.getOffednerObservationCommitList().getInsertList().isEmpty()) {
				for (OffenderObservationTypes obj : commitBean.getOffednerObservationCommitList().getInsertList()) {				
					BigDecimal objTypeVesrionId = oimoffobRepository.getObsTypeVersionIdSeq();
					obj.setObsTypeVersionId(objTypeVesrionId);
					obj.setCreateUserId(commitBean.getCreateUserId());
					if(obj.getOffObsCharacteristicsInsertList() != null) {
						for (OffObsCharacteristics charInsertObj : obj.getOffObsCharacteristicsInsertList()) {
							charInsertObj.setObsTypeVersionId(objTypeVesrionId);
							charInsertObj.setCreateUserId(commitBean.getCreateUserId());
							detailTypeCodeList.add(charInsertObj);
						}
					}
					List<OffenderObservationTypes> activeObservationTypeList=new ArrayList<>();
					activeObservationTypeList = oimoffobRepository.getActiveObservationTypeList(obj);
					if(!activeObservationTypeList.isEmpty()) {
						oimoffobRepository.updateActiveFlag(activeObservationTypeList);
					}
					
				}
				liReturn = oimoffobRepository.offenderObservationCommonInsertData(commitBean.getOffednerObservationCommitList().getInsertList());
				if(!detailTypeCodeList.isEmpty()) {
					liReturn = oimoffobRepository.offObsDetailsCommonInsertCharctData(detailTypeCodeList);
				}
				
			}
			
			if(commitBean.getOffednerObservationCommitList().getUpdateList() != null && !commitBean.getOffednerObservationCommitList().getUpdateList().isEmpty()) {
				for (OffenderObservationTypes obj : commitBean.getOffednerObservationCommitList().getUpdateList()) {
					obj.setModifyUserId(commitBean.getCreateUserId());
				}
				Integer val = oimoffobRepository.updateActiveFlag(commitBean.getOffednerObservationCommitList().getUpdateList());
				if(val == 1) {
					for (OffenderObservationTypes obj : commitBean.getOffednerObservationCommitList().getUpdateList()) {				
						BigDecimal objTypeVesrionId = oimoffobRepository.getObsTypeVersionIdSeq();
						obj.setObsTypeVersionId(objTypeVesrionId);
						obj.setCreateUserId(commitBean.getCreateUserId());
						if(obj.getOffObsCharacteristicsUpdateList() != null) {
						for (OffObsCharacteristics charInsertObj : obj.getOffObsCharacteristicsUpdateList()) {
							charInsertObj.setObsTypeVersionId(objTypeVesrionId);
							charInsertObj.setCreateUserId(commitBean.getCreateUserId());
							detailTypeCodeUpdateList.add(charInsertObj);
						}
						}
					}
					liReturn = oimoffobRepository.offenderObservationCommonInsertData(commitBean.getOffednerObservationCommitList().getUpdateList());
					if(!detailTypeCodeUpdateList.isEmpty()) {
						liReturn = oimoffobRepository.offObsDetailsCommonInsertCharctData(detailTypeCodeUpdateList);
					}
					
				}
			}
			
			if(commitBean.getOffednerObservationCommitList().getDeleteList() != null && !commitBean.getOffednerObservationCommitList().getDeleteList().isEmpty()) {
				List<OffObsCharacteristics> deleteList = new ArrayList<OffObsCharacteristics>();
				commitBean.getOffednerObservationCommitList().getDeleteList().forEach(e -> {
					OffObsCharacteristics obj = new OffObsCharacteristics();
					obj.setObsTypeVersionId(e.getObsTypeVersionId());
					obj.setObservationType(e.getObservationType());
					deleteList.add(obj);
				});
				liReturn = oimoffobRepository.offObsCharacteristicsDeleteCharctData(deleteList);
				if(liReturn == 1) {
					commitBean.getOffednerObservationCommitList().getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
					liReturn = oimoffobRepository.offenderObservationDeleteData(commitBean.getOffednerObservationCommitList().getDeleteList());
				}
			}
			offObsTypeObj.setReturnedOutput(BigDecimal.valueOf(liReturn));
			returnList.add(offObsTypeObj);
		return returnList;
	}
}
