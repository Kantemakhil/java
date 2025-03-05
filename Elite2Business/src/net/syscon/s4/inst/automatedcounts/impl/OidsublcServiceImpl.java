package net.syscon.s4.inst.automatedcounts.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.automatedcounts.OidsublcRepository;
import net.syscon.s4.inst.automatedcounts.OidsublcService;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCountsCommitBean;
import net.syscon.s4.inst.automatedcounts.beans.AgencyReportingLocs;
import net.syscon.s4.inst.automatedcounts.beans.TempOidcount;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;
import net.syscon.s4.triggers.AgencyLocationCountsT1Service;

/**
 * Class OidsublcServiceImpl
 */
@Service
public class OidsublcServiceImpl extends BaseBusiness implements OidsublcService {

	@Autowired
	private OidsublcRepository oidsublcRepository;

	@Autowired
	private AgencyLocationCountsT1Service agencyLocationCountsT1Service;

	/**
	 * Creates new OidsublcServiceImpl class Object
	 */
	public OidsublcServiceImpl() {
		
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<AgencyLocations> subLocCntWhennewrecordinstance(final AgencyLocations paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<AgencyLocations> subLocCntWhenCreateRecord(final AgencyLocations paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<AgencyLocationCounts> oidsublcKeyExit(final AgencyLocationCounts paramBean) {
		final List<AgencyLocationCounts> returnList = new ArrayList<>();
		final AgencyLocationCounts returnObj = oidsublcRepository.oidsublcKeyExit(paramBean);
		returnList.add(returnObj);
		return returnList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final OmsModules omsModules = oidsublcRepository.createFormGlobalsCreateFormGlobals(paramBean);
		return omsModules;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public AgencyLocations cgwhenNewFormInstance(final AgencyLocations paramBean) {
		final AgencyLocations agencyLocations = oidsublcRepository.cgwhenNewFormInstance(paramBean);
		return agencyLocations;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public AgencyCountTypes getDefaultAgyLoc(String caseloadId) {
		AgencyCountTypes agencyLocations = new AgencyCountTypes();
		List<AgencyCountTypes> agencyLocationsList = oidsublcRepository.defaultAgyLoc(caseloadId);
		if (agencyLocationsList.size() > 1) {
			agencyLocations.setAgyLocId(null);
			agencyLocations.setDescription(null);
			return agencyLocations;
		}
		if (agencyLocations != null) {
			agencyLocations.setCaseLoadId(caseloadId);
			agencyLocations = oidsublcRepository.subLocCntWhenCreateRecord(agencyLocations);
		}
		return agencyLocations;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public AgencyReportingLocs housingLev1Val(final AgencyReportingLocs paramBean) {
		final AgencyReportingLocs returnList = (AgencyReportingLocs) oidsublcRepository
				.housingLev1ValHousingLev1Val(paramBean);
		return returnList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<AgencyReportingLocs> housingLev2Val(final AgencyReportingLocs paramBean) {
		final List<AgencyReportingLocs> returnList = oidsublcRepository.housingLev2ValHousingLev2Val(paramBean);
		return returnList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<AgencyReportingLocs> updateCountRecord(final AgencyReportingLocs paramBean) {
		final List<AgencyReportingLocs> returnList = new ArrayList<>();
		final AgencyReportingLocs returnObj = oidsublcRepository.updateCountRecordUpdateCountRecord(paramBean);
		returnList.add(returnObj);
		return returnList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public SystemProfiles runReport(final SystemProfiles paramBean) {
		final SystemProfiles systemProfiles = oidsublcRepository.runReport(paramBean);
		return systemProfiles;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<AgencyLocationCounts> subLocCntExecuteQuery(final AgencyLocationCounts searchRecord) {
		return oidsublcRepository.subLocCntExecuteQuery(searchRecord);
	}

	/**
	 * update the records from database table
	 *
	 * @param commitBean
	 *
	 * @
	 */
	@Transactional
	public Integer subLocCntCommit(final AgencyLocationCountsCommitBean commitBean) {
		int liReturn = 0;
		Integer agySeq;
		Integer actualCount;
		Integer reportingLocId;
		String lCheckValueData = "N";
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (AgencyLocationCounts bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oidsublcRepository.subLocCntInsertAgencyLocationCounts(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final AgencyLocationCounts obj : commitBean.getUpdateList()) {
                  obj.setModifyUserId(commitBean.getCreateUserId());
				if (obj.getLivingUnitId1() != null) {
					agySeq = oidsublcRepository.getAgySeqWithLivUnitId(obj);
					if (agySeq != null) {
						obj.setAgySeq(Long.parseLong(agySeq.toString()));
					}
					if (obj.getLivingUnitId3() != null) {
						obj.setLivingUnitId(obj.getLivingUnitId3());
					} else if (obj.getLivingUnitId2() != null) {
						obj.setLivingUnitId(obj.getLivingUnitId2());
					} else if (obj.getLivingUnitId1() != null) {
						obj.setLivingUnitId(obj.getLivingUnitId1());
					}
					actualCount = oidsublcRepository.getActualCountWithLivUnitId(obj);
					if (actualCount != null) {
						obj.setActualCount(BigDecimal.valueOf(actualCount));
					}
				} else {
					agySeq = oidsublcRepository.getAgySeqWithIntLocId(obj);
					if (agySeq != null) {
						obj.setAgySeq(Long.parseLong(agySeq.toString()));
					}
					actualCount = oidsublcRepository.getActualCountWithIntLocId(obj);
					if (actualCount != null) {
						obj.setActualCount(BigDecimal.valueOf(actualCount));
					}
				}
				reportingLocId = oidsublcRepository.getMaxReportingId(obj);
				if (reportingLocId != null) {
					obj.setReportingLocId(Long.parseLong(reportingLocId.toString()));
				}
				final String lCheckValue = oidsublcRepository.getUpdateCountLcheckProc(obj);
				if ("Y".equals(lCheckValue)) {
					lCheckValueData = oidsublcRepository.getCountLcheckProc(obj);
					if ("Y".equals(lCheckValueData)) {
						liReturn = oidsublcRepository.resubmitCountDeleteQuery(commitBean.getUpdateList());
						for (AgencyLocationCounts bean : commitBean.getUpdateList()) {
							bean.setModifyUserId(commitBean.getCreateUserId());
							TempOidcount tmpBeean = new TempOidcount();
							tmpBeean.setAgySeq(bean.getAgySeq().intValue());
							tmpBeean.setCountTypeId(bean.getCountTypeId().intValue());
							tmpBeean.setReportingLocId(bean.getReportingLocId().intValue());
							tmpBeean.setReportedCount(bean.getReportedCount().intValue());
							tmpBeean.setDateSubmitted(bean.getDateSubmitted());
							tmpBeean.setEnteredByUserid(bean.getEnteredByUserid());
							tmpBeean.setActualCount(Integer.parseInt(bean.getActualCount().toString()));
							tmpBeean.setModifyUserId(commitBean.getCreateUserId());
							agencyLocationCountsT1Service.agencyLocationCountsT1Trigger(tmpBeean,lCheckValueData);
						}
					} else {
						return liReturn = 2;
					}
				}
				if (obj.getCountTypeId() != null && obj.getAgySeq() != null && obj.getReportingLocId() != null) {
					if (!obj.getActualCount().equals(obj.getReportedCount())) {
						obj.setEnteredByUserid(commitBean.getCreateUserId());
						obj.setModifyUserId(commitBean.getCreateUserId());
						liReturn = oidsublcRepository
								.agencyLocationCountsUpdateCountNotEquals(commitBean.getUpdateList());

						for (AgencyLocationCounts bean : commitBean.getUpdateList()) {
							TempOidcount tmpBeean = new TempOidcount();
							tmpBeean.setAgySeq(bean.getAgySeq().intValue());
							tmpBeean.setCountTypeId(bean.getCountTypeId().intValue());
							tmpBeean.setReportingLocId(bean.getReportingLocId().intValue());
							tmpBeean.setReportedCount(bean.getReportedCount().intValue());
							tmpBeean.setDateSubmitted(bean.getDateSubmitted());
							tmpBeean.setEnteredByUserid(bean.getEnteredByUserid());
							tmpBeean.setActualCount(Integer.parseInt(bean.getActualCount().toString()));
							tmpBeean.setModifyUserId(commitBean.getCreateUserId());
							agencyLocationCountsT1Service.agencyLocationCountsT1Trigger(tmpBeean,lCheckValueData);
						}
						
					} else {
						obj.setEnteredByUserid(commitBean.getCreateUserId());
						obj.setModifyUserId(commitBean.getCreateUserId());
						liReturn = oidsublcRepository.agencyLocationCountsUpdateCountEquals(commitBean.getUpdateList());
						for (AgencyLocationCounts bean : commitBean.getUpdateList()) {
							bean.setModifyUserId(commitBean.getCreateUserId());
							TempOidcount tmpBeean = new TempOidcount();
							tmpBeean.setAgySeq(bean.getAgySeq().intValue());
							tmpBeean.setCountTypeId(bean.getCountTypeId().intValue());
							tmpBeean.setReportingLocId(bean.getReportingLocId().intValue());
							tmpBeean.setReportedCount(bean.getReportedCount().intValue());
							tmpBeean.setDateSubmitted(bean.getDateSubmitted());
							tmpBeean.setEnteredByUserid(bean.getEnteredByUserid());
							tmpBeean.setActualCount(Integer.parseInt(bean.getActualCount().toString()));
							tmpBeean.setModifyUserId(commitBean.getCreateUserId());
							agencyLocationCountsT1Service.agencyLocationCountsT1Trigger(tmpBeean,lCheckValueData);
						}
						return liReturn = 4;
					}
				} else {
					return liReturn = 3;
				}
			}
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<AgencyLocations> cgfkAgyLocIdRecordGroup(final Integer sessionId, final String caseloadId,
			final String userId) {
		List<TempOidcount> returnData = new ArrayList<TempOidcount>();
		List<AgencyLocations> returnListData = new ArrayList<AgencyLocations>();
		String user = oidsublcRepository.getUserId(userId);
		if (caseloadId != null && sessionId != null) {
			List<TempOidcount> sessionIdMain = oidsublcRepository.cgwhenNewFormInstanceCgPsessionId(user, sessionId);
			for (TempOidcount tempOidcount : sessionIdMain) {
				returnData = oidsublcRepository.tempOidcountData(tempOidcount.getSessionId(), caseloadId);
				if (returnData.size() > 0) {
					for (TempOidcount data : returnData) {
						AgencyLocations returnDataTemp = new AgencyLocations();
						returnDataTemp.setAgyLocId(data.getAgyLocId());
						returnDataTemp.setHousingLev1Code(data.getCountTypeCode());
						returnDataTemp.setHousingLev3Code(data.getScheduledTime());
						returnDataTemp.setCode(data.getCountTypeId().toString());
						returnDataTemp.setHousingLev2Code(data.getCountTypeId().toString());
						if (returnDataTemp.getAgyLocId() != null) {
							String description = oidsublcRepository.getDescription(returnDataTemp.getAgyLocId(),
									userId);
							returnDataTemp.setDescription(description);
						}
						returnListData.add(returnDataTemp);
					}
				}
			}
		}
		return returnListData;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> cgfkCountTypesRecordGroup() {
		return oidsublcRepository.cgfkCountTypesRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<AgencyCountTypes> cgfkSchTimeRecordGroup() {
		return oidsublcRepository.cgfkSchTimeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<LivingUnits> cgfkHousingLevel1RecordGroup(final long countTypeCodeId) {
		return oidsublcRepository.cgfkHousingLevel1RecordGroup(countTypeCodeId);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<LivingUnits> cgfkHousingLevel2RecordGroup(final long countTypeCodeId, final Integer livingUnitId) {
		return oidsublcRepository.cgfkHousingLevel2RecordGroup(countTypeCodeId, livingUnitId);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<LivingUnits> cgfkHousingLevel3RecordGroup(final long countTypeCodeId, final Integer livingUnitId) {
		return oidsublcRepository.cgfkHousingLevel3RecordGroup(countTypeCodeId, livingUnitId);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<VIntLocSummaries> cgfkInitLocCodeRecordGroup(final long countTypeCodeId) {
		return oidsublcRepository.cgfkInitLocCodeRecordGroup(countTypeCodeId);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<StaffMembers> cgfkConductedByRecordGroup() {
		Integer listSeq = 1;
		final List<StaffMembers> returnList = oidsublcRepository.cgfkConductedByRecordGroup();
		for (final StaffMembers obj : returnList) {
			obj.setListSeq(listSeq++);
			if (obj.getDescription() == null) {
				obj.setDescription("");
			}
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<StaffMembers> cgfkConductedBy1RecordGroup() {
		return oidsublcRepository.cgfkConductedBy1RecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> cfgkRecountCodeRecordGroup() {
		return oidsublcRepository.cfgkRecountCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public AgencyLocations getHousingLevels(final String caseloadId) {
		AgencyLocations returnData = new AgencyLocations();
		returnData = oidsublcRepository.getHousingLevels(caseloadId);
		if (returnData != null) {
			if (returnData != null && returnData.getHousingLev1Code() != null) {
				String dataOne = oidsublcRepository.getLabelDescription(returnData.getHousingLev1Code());
				if (dataOne != null) {
					returnData.setHousingLev1Code(dataOne);
				}
			}
			if (returnData != null && returnData.getHousingLev2Code() != null) {
				String dataTwo = oidsublcRepository.getLabelDescription(returnData.getHousingLev2Code());
				if (dataTwo != null) {
					returnData.setHousingLev2Code(dataTwo);
				}
			}
			if (returnData != null && returnData.getHousingLev3Code() != null) {
				String dataThree = oidsublcRepository.getLabelDescription(returnData.getHousingLev3Code());
				if (dataThree != null) {
					returnData.setHousingLev3Code(dataThree);
				}
			}
			if (returnData != null && returnData.getHousingLev4Code() != null) {
				String dataFour = oidsublcRepository.getLabelDescription(returnData.getHousingLev4Code());
				if (dataFour != null) {
					returnData.setHousingLev4Code(dataFour);
				}
			}
		}
		return returnData;
	}

	/**
	 * update the records from database table
	 *
	 * @param commitBean
	 *
	 * @
	 */
	@Transactional
	public Integer reSubLocCntCommit(final AgencyLocationCountsCommitBean commitBean, final String userId) {
		int liReturn = 0;
		Integer agySeq;
		Integer actualCount;
		Integer reportingLocId;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final AgencyLocationCounts obj : commitBean.getUpdateList()) {
				if (obj.getLivingUnitId1() != null) {
					agySeq = oidsublcRepository.getAgySeqWithLivUnitId(obj);
					if (agySeq != null) {
						obj.setAgySeq(Long.parseLong(agySeq.toString()));
					}
					if (obj.getLivingUnitId3() != null) {
						obj.setLivingUnitId(obj.getLivingUnitId3());
					} else if (obj.getLivingUnitId2() != null) {
						obj.setLivingUnitId(obj.getLivingUnitId2());
					} else if (obj.getLivingUnitId1() != null) {
						obj.setLivingUnitId(obj.getLivingUnitId1());
					}
					actualCount = oidsublcRepository.getActualCountWithLivUnitId(obj);
					if (actualCount != null) {
						obj.setActualCount(BigDecimal.valueOf(actualCount));
					}
				} else {
					agySeq = oidsublcRepository.getAgySeqWithIntLocId(obj);
					if (agySeq != null) {
						obj.setAgySeq(Long.parseLong(agySeq.toString()));
					}
					actualCount = oidsublcRepository.getActualCountWithIntLocId(obj);
					if (actualCount != null) {
						obj.setActualCount(BigDecimal.valueOf(actualCount));
					}
				}
				reportingLocId = oidsublcRepository.getMaxReportingId(obj);
				if (reportingLocId != null) {
					obj.setReportingLocId(Long.parseLong(reportingLocId.toString()));
				}
				if (agySeq != null) {
					obj.setAgySeq(Long.parseLong(agySeq.toString()));
				}	
				if (actualCount != null) {
					obj.setActualCount(BigDecimal.valueOf(actualCount));
				}
				if (reportingLocId != null) {
					obj.setReportingLocId(Long.parseLong(reportingLocId.toString()));
				}
				obj.setEnteredByUserid(userId);
				obj.setModifyUserId(userId);
			}
			liReturn = oidsublcRepository.resubmitCountUpdateQuery(commitBean.getUpdateList());
			for (AgencyLocationCounts bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				TempOidcount tmpBeean = new TempOidcount();
				tmpBeean.setAgySeq(bean.getAgySeq().intValue());
				tmpBeean.setCountTypeId(bean.getCountTypeId().intValue());
				tmpBeean.setReportingLocId(bean.getReportingLocId().intValue());
				tmpBeean.setReportedCount(bean.getReportedCount().intValue());
				tmpBeean.setEnteredByUserid(bean.getEnteredByUserid());
				if(bean.getActualCount() != null)
					tmpBeean.setActualCount(Integer.parseInt(bean.getActualCount().toString()));
				tmpBeean.setDateSubmitted(bean.getDateSubmitted());
				tmpBeean.setEnteredByUserid(bean.getEnteredByUserid());
				tmpBeean.setModifyUserId(bean.getModifyUserId());
				agencyLocationCountsT1Service.agencyLocationCountsT1Trigger(tmpBeean, bean.getRcntInProgressFlag());
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (final AgencyLocationCounts obj : commitBean.getDeleteList()) {
				if (obj.getLivingUnitId1() != null) {
					agySeq = oidsublcRepository.getAgySeqWithLivUnitId(obj);
					if (agySeq != null) {
						obj.setAgySeq(Long.parseLong(agySeq.toString()));
					}
				} else {
					agySeq = oidsublcRepository.getAgySeqWithIntLocId(obj);
					if (agySeq != null) {
						obj.setAgySeq(Long.parseLong(agySeq.toString()));
					}
				}
			}
			liReturn = oidsublcRepository.resubmitCountDeleteQuery(commitBean.getDeleteList());
			for (AgencyLocationCounts bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				TempOidcount tmpBeean = new TempOidcount();
				tmpBeean.setAgySeq(bean.getAgySeq().intValue());
				tmpBeean.setCountTypeId(bean.getCountTypeId().intValue());
				tmpBeean.setReportingLocId(bean.getReportingLocId().intValue());
				tmpBeean.setReportedCount(bean.getReportedCount().intValue());
				tmpBeean.setEnteredByUserid(bean.getEnteredByUserid());
				if(bean.getActualCount() != null)
					tmpBeean.setActualCount(Integer.parseInt(bean.getActualCount().toString()));
				tmpBeean.setDateSubmitted(bean.getDateSubmitted());
				tmpBeean.setEnteredByUserid(bean.getEnteredByUserid());
				tmpBeean.setModifyUserId(bean.getModifyUserId());
				agencyLocationCountsT1Service.agencyLocationCountsT1Trigger(tmpBeean, bean.getRcntInProgressFlag());
			}
		}
		return liReturn;
	}
}