package net.syscon.s4.inst.automatedcounts.impl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.automatedcounts.OidcountRepository;
import net.syscon.s4.inst.automatedcounts.OidcountService;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyReportingLocs;
import net.syscon.s4.inst.automatedcounts.beans.LockedModules;
import net.syscon.s4.inst.automatedcounts.beans.TempOidcount;
import net.syscon.s4.inst.demographicsbiometrics.OcuverifRepository;
import net.syscon.s4.pkgs.oidcount.OidcountPkgService;
import net.syscon.s4.triggers.AgencyLocationCountsT1Service;

/**
 * Class OidcountServiceImpl
 */
@Service
public class OidcountServiceImpl extends BaseBusiness implements OidcountService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidcountServiceImpl.class.getName());


	@Autowired
	private OidcountRepository oidcountRepos;

	@Autowired
	private AgencyLocationCountsT1Service agencyLocationCountsT1Service;

	@Autowired
	private OidcountPkgService oidcountService;
	
	@Autowired
	private OcuverifRepository ocuverifRepo; 

	/**
	 * Creates new OidcountServiceImpl class Object
	 */
	public OidcountServiceImpl() {

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public String cgwhenNewFormInstanceLockedModules(final Integer sessionId, final String caseLoadId, final String userId) {
		final String returnValue = oidcountRepos.cgwhenNewFormInstanceLockedModules();
		AgencyLocations returnList = new AgencyLocations();
		Integer tempValue = 0;
		if (returnValue == null) {
			tempValue = 0;
		} else {
			tempValue = Integer.parseInt(returnValue);
		}
		final String user = oidcountRepos.cgwhenNewFormInstanceCgSessionId(sessionId);
		if (tempValue == 0 || (user != null && user.equalsIgnoreCase("OMS_OWNER"))) {
			final String defaultAgyLocId = oidcountRepos.defaultAgyLocSessionId(sessionId);
			if (defaultAgyLocId == null) {
				returnList = oidcountRepos.defaultAgyLocGlobalCaseload(caseLoadId);

			} else {
				returnList = oidcountRepos.defaultAgyLocGlobalCaseloadElseCondtion(defaultAgyLocId);
			}
			if ("null".equalsIgnoreCase(returnList.getAgyLocId())) {
				return returnList.getAgyLocId();
			} else {
				final Integer claCount = oidcountRepos.getCountWhenNewFormInstanceFromCaseloadAgencyLocations(userId);
				if (claCount == 1) {
					final String claAgyLocId = oidcountRepos.cgwhenNewFormInstanceCgCaseload(userId);
					final LockedModules lockedBean = new LockedModules();
					lockedBean.setAgyLocId(claAgyLocId);
					lockedBean.setSessionId(sessionId);
					final Integer lmCount = oidcountRepos.cgwhenNewFormInstanceCgLockedModules(lockedBean);
					if (lmCount != 0) {
						final String countValidation = "count";
						return countValidation;
					}

				}
			}
		}
		return returnValue;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<AgencyCountTypes> cgwhenNewItemInstance(final AgencyCountTypes paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<AgencyReportingLocs> createRecountRecords(final AgencyReportingLocs paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	@Transactional
	public Integer deleteInitiateRecords(final AgencyCountTypes paramBean) {
		Integer repLocId = 0;
		if(paramBean.getCountTypeId()==null) {
			List<TempOidcount>  tempOidCounts = this.checkExistingCountSession(paramBean.getSessionId().intValue(), paramBean.getCaseLoadId());
			if(tempOidCounts!=null && tempOidCounts.size()>0) {
				paramBean.setCountTypeId(tempOidCounts.get(0).getCountTypeId());
			}
		}
		repLocId = oidcountRepos.deleteInitiateRecords(paramBean);
		if (repLocId != null && repLocId != 0) {
			oidcountRepos.deleteInitiateRecordsOfAgencyLocationCounts(repLocId, paramBean.getCreateUserId());
			oidcountRepos.deleteInitiateRecordsOfAgencyCounts(repLocId, paramBean.getCreateUserId());
			oidcountRepos.deleteInitiateRecordsOfLockedModules(paramBean);
			oidcountService.cancelCount( paramBean.getSessionId(),paramBean.getCreateUserId());
		}
		return repLocId;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<TempOidcount> checkExistingCountSession(final Integer sessionId, final String caseLoadId) {
		final Integer tempOidCount = oidcountRepos.checkExistingGetCount(sessionId);
		List<TempOidcount> returnList = new ArrayList<>();
		if (tempOidCount > 0) {
			returnList = oidcountRepos.checkExistingCountSession(sessionId);
		}
		return returnList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<TempOidcount> refreshCountOfTempOidCount(final Integer sessionId) {
		List<TempOidcount> returnList = oidcountRepos.refreshCountOfTempOidCount(sessionId);
		
		String userId = null;
		String userName = null;
		for (TempOidcount list : returnList) {
			userId = list.getEnteredByUserid();
			if(userId != null) {
				userName = ocuverifRepo.getUserName(userId);
				list.setEnteredByUserid(userName);
			}
			
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkAgyLocIdRecordGroup(final String caseLoadId) {
		return oidcountRepos.cgfkAgyLocIdRecordGroup(caseLoadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkCountTypesRecordGroup(final String agyLocId) {
		return oidcountRepos.cgfkCountTypesRecordGroup(agyLocId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyCountTypes> cgfkScheduledTimeRecordGroup(final String agyLocId, final String countTypeId) {
		final List<AgencyCountTypes> returnList = oidcountRepos.cgfkScheduledTimeRecordGroup(agyLocId,
				countTypeId);
		for (final AgencyCountTypes obj : returnList) {
			obj.setCode(obj.getCountTypeId().toString());
			obj.setDescription(obj.getScheduledTime());
			if(obj.getDescription() == null) {
				obj.setDescription("");
			}
		}
		return returnList;

	}

	/**
	 * to get session id from DB
	 * 
	 * @return
	 */
	public Long getGlobalSessionId() {
		return oidcountRepos.getGlobalSessionId();
	}

	/**
	 * to get count from DB params agyLocId,sessionId) return Integer
	 */
	@Transactional
	public Map<String, Object> countLockedMoulesCursor(final AgencyCountTypes bean) {
		Map<String, Object> returnMap = new HashedMap<>();
		Integer returnCount = 0;
		final AgencyCounts agencyCountsbean = new AgencyCounts();
		agencyCountsbean.setCreateUserId(bean.getCreateUserId());
		final AgencyLocationCounts alcBean = new AgencyLocationCounts();
		alcBean.setCreateUserId(bean.getCreateUserId());
		String lockedUserId = oidcountRepos.countLockedMoulesCursor(bean);
		if (lockedUserId!=null) {
			returnCount = 1;
			returnMap.put("lockedCount", returnCount);
			returnMap.put("lockedUserId", lockedUserId);
			return returnMap;
		}
		if (bean.getCountTypeId() != null) {
			returnCount = oidcountRepos.countAgencyReportinglocsCursor(bean);
			if (returnCount == 0) {
				returnCount = 2;
				returnMap.put("lockedCount", returnCount);
				return returnMap;
			}
		}
			returnCount = oidcountRepos.countAgencyCountsTypesCursor(bean);
			if (returnCount > 0) {
				returnCount = 3;
				returnMap.put("lockedCount", returnCount);
				return returnMap;
			}
		String checkExistsFlag = oidcountRepos.checkCountExistsFunction(bean);
		if (checkExistsFlag == null) {
			checkExistsFlag = "N";
		} else {
			checkExistsFlag = "Y";
		}
		if (!"Y".equals(checkExistsFlag)) {
			returnCount = 4;
			returnMap.put("lockedCount", returnCount);
			return returnMap;
		}
		if (bean.getCountTypeId() != null) {
			agencyCountsbean.setCountTypeId(bean.getCountTypeId());
			AgencyCounts acReturnValues = new AgencyCounts();
			acReturnValues = oidcountRepos.createInitiatedRecords(agencyCountsbean);
			if (acReturnValues != null && acReturnValues.getOutcome() != null) {
				if (acReturnValues.getOutcome().equalsIgnoreCase("RE COUNT")) {
					agencyCountsbean.setParentReportingLocId(acReturnValues.getReportingLocId());
				}
			}
			String data = "1";
			if (acReturnValues != null && acReturnValues.getReportingLocId() != null) {
				data = "2";
			}
			String val = "1";
			if (acReturnValues != null && "Y".equals(acReturnValues.getCountInProgress())) {
				val = "2";
			}
			if ("1".equals(val) || acReturnValues.getOutcome() != null
					|| "1".equals(data)) {
				oidcountRepos.insertTheDataInLockedModules(bean);
				final Integer repotingLocId = oidcountRepos.gettingMaxValueOfReportinglocidInAgenctLocations();
				agencyCountsbean.setReportingLocId(repotingLocId);
				agencyCountsbean.setConductedByUserid(bean.getCreateUserId());
				oidcountRepos.insertTheDataInAgencyCounts(agencyCountsbean);
				final List<Integer> agySeq = oidcountRepos.preInsertOfAagencyLocationCounts(agencyCountsbean);
				alcBean.setCountTypeId(Long.valueOf(bean.getCountTypeId().toString()));
				alcBean.setReportingLocId(Long.valueOf(repotingLocId.toString()));
				for (int i = 0; i < agySeq.size(); i++) {
					alcBean.setAgySeq(Long.valueOf(agySeq.get(i).toString()));
					alcBean.setEnteredByUserid(bean.getCreateUserId());
					oidcountRepos.insertTheDataInAgencyLocationCounts(alcBean);
					TempOidcount tempOidcoun=new TempOidcount();
					tempOidcoun.setAgySeq(alcBean.getAgySeq() != null ? alcBean.getAgySeq().intValue():null);
					tempOidcoun.setCountTypeId(alcBean.getCountTypeId() != null ? alcBean.getCountTypeId().intValue():null);
					tempOidcoun.setReportingLocId(alcBean.getReportingLocId() != null ? alcBean.getReportingLocId().intValue() : null);
					tempOidcoun.setReportedCount(alcBean.getReportedCount() != null ? alcBean.getReportedCount().intValue() : null);
					agencyLocationCountsT1Service.agencyLocationCountsT1Trigger(tempOidcoun, alcBean.getRcntInProgressFlag());			
				}
				returnMap.put("acReturnValues", acReturnValues);
				returnMap.put("lockedCount", repotingLocId);
				return returnMap;
			}
		}
		returnCount = 6;
		returnMap.put("lockedCount", returnCount);
		return returnMap;

	}
	@Transactional
	public List<TempOidcount> initiateCountSetup(final AgencyCountTypes paramBean) {
		AgencyCounts acReturnValues = paramBean.getAgencyCounts();
		if ("true".equalsIgnoreCase(paramBean.getCheckInitiate())) {
			String data = "1";
			if (acReturnValues != null && acReturnValues.getReportingLocId() != null) {
				data = "2";
			}
			String val = "1";
			if (acReturnValues != null && "Y".equals(acReturnValues.getCountInProgress())) {
				val = "2";
			}
			if ("1".equals(val) || (acReturnValues != null && acReturnValues.getOutcome() != null)
					|| "1".equals(data)) {
				setTempOidCount(paramBean);
			}
		} else {
			setTempOidCount(paramBean);
		}
		return checkExistingCountSession(Integer.valueOf(paramBean.getSessionId().toString()), paramBean.getCaseLoadId());
	}
	@Transactional
	public List<TempOidcount> setTempOidCount(final AgencyCountTypes paramBean) {
		long start = System.currentTimeMillis();
		Integer lCancelflag = 0;
		Integer lTerminatedSessionFlag = null;
		Integer lActualCount;
		Integer lTotalMale = 0;
		Integer lTotalFeMale;
		Integer lTotalOther;
		Integer lOutTotal;
		Integer lTotalMaleOut;
		Integer lOutTotalFemaleOut;
		Integer lTotalOtherOut;
		Integer lCountExisting=oidcountRepos.getlCountExistingValue(paramBean.getSessionId());
		if(lCountExisting > 0){
			oidcountRepos.insertCancelCountValue(paramBean.getSessionId());
			oidcountRepos.deleteCancelRecords(paramBean.getSessionId(), paramBean.getCreateUserId());
		}
		List<TempOidcount> listOfRecordsTempCountData = oidcountRepos.getLTempCountCurser(paramBean.getSessionId());
		if (listOfRecordsTempCountData.size() > 0) {
			oidcountRepos.deleteRecordFromOidTempCount(paramBean.getSessionId(), paramBean.getCreateUserId());
		}
		if(paramBean!=null && paramBean.getScheduledTime()==null) {
			paramBean.setScheduledTime("NA");
		}
		oidcountRepos.insertOidTempCountBean(paramBean);
		List<Long> reportingLocationList = new ArrayList<>();
		reportingLocationList=oidcountRepos.getListReportingLocId(paramBean.getSessionId());
		String lAllCompleteFlag="N";
		List<TempOidcount> listOfRecordsTempCount= new ArrayList<>();
		listOfRecordsTempCount = oidcountRepos.getLTempCountCurser(paramBean.getSessionId());
		for (TempOidcount tempOidcount : listOfRecordsTempCount) {
			lCancelflag = oidcountRepos.getlCountExistingValueCancel(paramBean.getSessionId());
			if(lCancelflag > 0){
				oidcountRepos.deleteRecordFromOidTempCount(paramBean.getSessionId(), paramBean.getCreateUserId());
				lAllCompleteFlag ="Y";
			}
			lTerminatedSessionFlag = oidcountRepos.getLtermintaedSessionFlag(paramBean.getSessionId());
			if(lTerminatedSessionFlag == 0){
				oidcountRepos.deleteRecordFromOidTempCount(paramBean.getSessionId(), paramBean.getCreateUserId());
				if (reportingLocationList.size() > 0) {
					oidcountRepos.deleteAgencyLocationCount(reportingLocationList.get(0), paramBean.getCreateUserId());
					oidcountRepos.deleteAgencyCounts(reportingLocationList.get(0), paramBean.getCreateUserId());
				}
				lAllCompleteFlag ="Y";
			}
			if(tempOidcount.getActualCount()==null){
				if("INIT".equals(tempOidcount.getLocationType())){
					lActualCount =0;
					lActualCount = oidcountRepos.getAgencyLcountInitCurser(tempOidcount.getLowestLocationId(),tempOidcount.getAgyLocId());
				} else {
					lActualCount =0;
					lActualCount = oidcountRepos.getLcountLivingUnitCurser(tempOidcount.getLowestLocationId(),tempOidcount.getAgyLocId());
				}
				oidcountRepos.updateTempOidcount(lActualCount,tempOidcount.getRowId());
			}

			if(tempOidcount.getTotalMale() == null){
				if("INIT".equals(tempOidcount.getLocationType())){
					lTotalMale =0;
					lTotalMale = oidcountRepos.getLcountMaleInitCurser(tempOidcount.getLowestLocationId(),tempOidcount.getAgyLocId());
				} else {
					lTotalMale = oidcountRepos.getLcountLivingMaleInitCurser(tempOidcount.getLowestLocationId(),tempOidcount.getAgyLocId());
				}
				oidcountRepos.updateLcountMaleOidcount(lTotalMale,tempOidcount.getRowId());
			}

			if(tempOidcount.getTotalFemale() == null){
				if("INIT".equals(tempOidcount.getLocationType())){
					lTotalFeMale = 0;
					lTotalFeMale = oidcountRepos.getLcountFeMaleInitCurser(tempOidcount.getLowestLocationId(),tempOidcount.getAgyLocId());
				} else {
					lTotalFeMale = 0;
					lTotalFeMale = oidcountRepos.getLcountLivingFeMaleInitCurser(tempOidcount.getLowestLocationId(),tempOidcount.getAgyLocId());
				}
				oidcountRepos.updateLcountFeMaleOidcount(lTotalFeMale,tempOidcount.getRowId());
			}
			if(tempOidcount.getTotalOther() == null){
				if("INIT".equals(tempOidcount.getLocationType())){
					lTotalOther = 0;
					lTotalOther = oidcountRepos.getLcountOtherInitCurser(tempOidcount.getLowestLocationId(),tempOidcount.getAgyLocId());
				} else {
					lTotalOther = 0;
					lTotalOther = oidcountRepos.getLcountLivingOtherInitCurser(tempOidcount.getLowestLocationId(),tempOidcount.getAgyLocId());
				}
				oidcountRepos.updateLcountOtherOidcount(lTotalOther,tempOidcount.getRowId());
			}

			if(tempOidcount.getOutTotal() == null){
				if("INIT".equals(tempOidcount.getLocationType())){
					lOutTotal = 0;
					lOutTotal = oidcountRepos.getLcountLoutInitCurser(tempOidcount.getLowestLocationId(),tempOidcount.getAgyLocId());
				} else {
					lOutTotal = 0;
					lOutTotal = oidcountRepos.getLcountLivingLoutInitCurser(tempOidcount.getLowestLocationId(),tempOidcount.getAgyLocId());
				}
				oidcountRepos.updateLcountLoutOidcount(lOutTotal,tempOidcount.getRowId());
			}

			if(tempOidcount.getTotalMaleOut() == null){
				if("INIT".equals(tempOidcount.getLocationType())){
					lTotalMaleOut = 0;
					lTotalMaleOut = oidcountRepos.getLcountLTotalMaleOutInitCurser(tempOidcount.getLowestLocationId(),tempOidcount.getAgyLocId());
				} else {
					lTotalMaleOut = 0;
					lTotalMaleOut = oidcountRepos.getLcountLivingLTotalMaleOutInitCurser(tempOidcount.getLowestLocationId(),tempOidcount.getAgyLocId());
				}
				oidcountRepos.updateLcountLTotalMaleOutOidcount(lTotalMaleOut,tempOidcount.getRowId());
			}

			if(tempOidcount.getTotalFemaleOut() == null){
				if("INIT".equals(tempOidcount.getLocationType())){
					lOutTotalFemaleOut = 0;
					lOutTotalFemaleOut = oidcountRepos.getLcountLTotalFemaleOutInitCurser(tempOidcount.getLowestLocationId(),tempOidcount.getAgyLocId());
				} else {
					lOutTotalFemaleOut = 0;
					lOutTotalFemaleOut = oidcountRepos.getLcountLivingLTotalFemaleOutInitCurser(tempOidcount.getLowestLocationId(),tempOidcount.getAgyLocId());
				}
				oidcountRepos.updateLcountLTotalFemaleOutOidcount(lOutTotalFemaleOut,tempOidcount.getRowId());
			}

			if(tempOidcount.getTotalOtherOut() == null){
				if("INIT".equals(tempOidcount.getLocationType())){
					lTotalOtherOut = 0;
					lTotalOtherOut = oidcountRepos.getLcountLTotalOtherOutInitCurser(tempOidcount.getLowestLocationId(),tempOidcount.getAgyLocId());
				} else {
					lTotalOtherOut = 0;
					lTotalOtherOut = oidcountRepos.getLcountLivingLTotalOtherOutInitCurser(tempOidcount.getLowestLocationId(),tempOidcount.getAgyLocId());
				}
				oidcountRepos.updateLcountLTotalOtherOutOidcount(lTotalOtherOut,tempOidcount.getRowId());
			}
		}
		if("N".equals(lAllCompleteFlag)){
			lCancelflag = oidcountRepos.getlCountExistingValueCancel(paramBean.getSessionId());
			if(lCancelflag > 0){	
				oidcountRepos.deleteRecordFromOidTempCount(paramBean.getSessionId(), paramBean.getCreateUserId());
			}
			lTerminatedSessionFlag = oidcountRepos.getLtermintaedSessionFlag(paramBean.getSessionId());
			if (lTerminatedSessionFlag == 0) {
				oidcountRepos.deleteRecordFromOidTempCount(paramBean.getSessionId(), paramBean.getCreateUserId());
				if (reportingLocationList.size() > 0) {
					oidcountRepos.deleteAgencyLocationCount(reportingLocationList.get(0), paramBean.getCreateUserId());
					oidcountRepos.deleteAgencyCounts(reportingLocationList.get(0), paramBean.getCreateUserId());
				}
			}
		}
		long end = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		logger.info("setTempOidCount Execution time is " + formatter.format((end - start) / 1000d));
		return listOfRecordsTempCountData;

	}

	public Integer getCountTypeIdFromDb(final AgencyCountTypes bean) {
		return oidcountRepos.getCountTypeIdFromDb(bean);
	}

	public List<TempOidcount> cgwhenNewFormInstanceCgPsessionId(final String caseloadId) {
		return oidcountRepos.cgwhenNewFormInstanceCgPsessionId(caseloadId);
	}
	@Transactional
	public Integer checkRemoveDeadJobsProcedure(final Integer sessionId,String modifyUserId) {
		final AgencyCountTypes param = new AgencyCountTypes();
		param.setSessionId(Long.valueOf(sessionId.toString()));
		oidcountService.checkRemoveDeadJobs(sessionId != null ? sessionId.toString():null,modifyUserId);
		oidcountRepos.deleteInitiateRecordsOfLockedModules(param);
		return 0;
	}

	public TempOidcount refreshCount(final Integer sessionId) {
		return oidcountRepos.refreshCount(sessionId);

	}

	@Transactional
	public Integer submitCountSetTempOidcount(final AgencyCountTypes paramBean) {
		return oidcountService.submitCountJob(paramBean);
	}
	public String refreshCountUserCancelledCur(final Integer sessionId, final String userId) {
		return oidcountRepos.refreshCountUserCancelledCur(sessionId,userId);
	}

	@Override
	public Integer getTimerValue() {
		return oidcountRepos.getTimerValue();
	}

}