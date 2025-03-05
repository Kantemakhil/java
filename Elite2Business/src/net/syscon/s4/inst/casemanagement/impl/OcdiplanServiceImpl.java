package net.syscon.s4.inst.casemanagement.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.CasePlansCommitBean;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.inst.casemanagement.OcdiplanRepository;
import net.syscon.s4.inst.casemanagement.OcdiplanService;
import net.syscon.s4.inst.casemanagement.beans.CaseReviewPeriods;
import net.syscon.s4.inst.casemanagement.beans.CommunityConditions;
import net.syscon.s4.inst.casemanagement.beans.OffApV1;
import net.syscon.s4.inst.casemanagement.beans.OffApV1CommitBean;
import net.syscon.s4.inst.casemanagement.beans.OffApV2;
import net.syscon.s4.inst.casemanagement.beans.OffApV2CommitBean;
import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditions;
import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditionsCommitBean;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeeds;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeedsCommitBean;
import net.syscon.s4.inst.casemanagement.beans.PlanDetails;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.casemanagement.beans.VSummaryCasePlans;
import net.syscon.s4.inst.casemanagement.beans.VSummaryCasePlansCommitBean;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.inst.classification.beans.StaffMembersV1;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.pkgs.pims_weight.PimsWeightService;
import net.syscon.s4.triggers.Ap2VrIudService;
import net.syscon.s4.triggers.ApVrIudService;
import net.syscon.s4.triggers.CasePlansT1Service;
import net.syscon.s4.triggers.CasePlansT2Service;
import net.syscon.s4.triggers.CasePlansT3Service;
import net.syscon.s4.triggers.CasePlansTwfService;
import net.syscon.s4.triggers.OffenderCaseConditionsT1Service;

/**
 * Class OcdiplanServiceImpl
 */
@Service
public class OcdiplanServiceImpl extends BaseBusiness implements OcdiplanService {

	@Autowired
	private OcdiplanRepository ocdiplanRepository;

	@Autowired
	private PimsWeightService pimsWeightService;

	@Autowired
	private CasePlansT3Service casePlansT3Service;

	@Autowired
	private CasePlansT1Service casePlansT1Service;

	@Autowired
	private CasePlansTwfService casePlansTwfService;

	@Autowired
	private ApVrIudService apVrIudService;

	@Autowired
	private Ap2VrIudService ap2VrIudService;

	@Autowired
	private CasePlansT2Service casePlansT2Service;
	
	@Autowired
	private OffenderCaseConditionsT1Service offCaseConditionsT1Service;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdiplanServiceImpl.class.getName());

	/**
	 * Creates new OcdiplanServiceImpl class Object
	 */
	public OcdiplanServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<CasePlans> offBkgOnCheckDeleteMaster(final CasePlans paramBean) {
		final List<CasePlans> casePlansList = null;
		return casePlansList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OffenderCriminogenicNeeds casPlnOnCheckDeleteMaster(final OffenderCriminogenicNeeds paramBean) {
		final OffenderCriminogenicNeeds offenderCriminogenicNeeds = null;
		return offenderCriminogenicNeeds;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffApV1> offCriNeedsOnCheckDeleteMaster(final OffApV1 paramBean) {
		final List<OffApV1> offApV1List = null;
		return offApV1List;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> offCriNeedsPostQuery(final ReferenceCodes paramBean) {
		final List<ReferenceCodes> referenceCodesList = null;
		return referenceCodesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<CommunityConditions> offCaseCondsPostQuery(final CommunityConditions paramBean) {
		final List<CommunityConditions> communityConditionsList = null;
		return communityConditionsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffApV2> offCaseCondsOnCheckDeleteMaster(final OffApV2 paramBean) {
		final List<OffApV2> offApV2List = null;
		return offApV2List;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public StaffMembersV1 GetStaffId(final StaffMembersV1 paramBean) {
		final StaffMembersV1 staffMembersV1 = new StaffMembersV1();
		return staffMembersV1;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<CaseReviewPeriods> SetCasPlnDefaults(final CaseReviewPeriods paramBean) {
		final List<CaseReviewPeriods> caseReviewPeriodsList = new ArrayList<>();
		return caseReviewPeriodsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> CgfklkpCasPlnCasPlnStatu(final ReferenceCodes paramBean) {
		final List<ReferenceCodes> referenceCodesList = new ArrayList<>();
		return referenceCodesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<AgencyLocations> CgfklkpCasPlnCasPlnAlFk(final AgencyLocations paramBean) {
		final List<AgencyLocations> agencyLocationsList = new ArrayList<>();
		return agencyLocationsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public PlanDetails GetSequenceNo(final PlanDetails paramBean) {
		final PlanDetails planDetails = null;
		return planDetails;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Integer checkReviewDate(final String supervisionLevel) {
		return ocdiplanRepository.checkReviewDate(supervisionLevel);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public SystemProfiles GetCnoteAllupdProfile(final SystemProfiles paramBean) {
		final SystemProfiles systemProfiles = null;
		return systemProfiles;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public CaseReviewPeriods NextReviewDate(final CaseReviewPeriods paramBean) {
		final CaseReviewPeriods caseReviewPeriods = null;
		return caseReviewPeriods;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public WorkFlows Verification(final WorkFlows paramBean) {
		final WorkFlows workFlows = null;
		return workFlows;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public CasePlans InsertCaseplanRecord(final CasePlans paramBean) {
		final CasePlans casePlans = null;
		return casePlans;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<CasePlans> casPlnExecuteQuery(final CasePlans searchRecord) {
		List<CasePlans> casePlanList = new ArrayList<>();

		try {
			casePlanList = ocdiplanRepository.casPlnExecuteQuery(searchRecord);
			
			for (final CasePlans casePlanBean : casePlanList) {
				if (casePlanBean.getVerifiedFlag() != null) {
					casePlanBean.setReqReview(casePlanBean.getVerifiedFlag().equals("Y") ? "N" : "Y");
				}
				if(casePlanBean.getStaffList()!=null) {
					List<Integer> StIdList=new ArrayList<Integer>();
					String[] stList=casePlanBean.getStaffList().split(",");
					for (String staffId : stList) {
						StIdList.add(Integer.valueOf(staffId));
					}
					casePlanBean.setStaffIdList(StIdList);
				}
			}
			}catch (Exception e) {
				return casePlanList;
		}
		
		
		return casePlanList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCAS_PLN
	 *
	 * 
	 */
	@Transactional
	public Integer casPlnCommit(final CasePlansCommitBean commitBean) {
		int liReturn = 0;
		CasePlans plans = null;
		Calendar cal = Calendar.getInstance();
		List<CasePlans> updateOldList = new ArrayList<>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bean ->{ bean.setCreateUserId(commitBean.getCreateUserId());
			 bean.setModifyUserId(commitBean.getCreateUserId());  });
			for (final CasePlans obj : commitBean.getInsertList()) {
				plans = obj;
				

				if ("Y".equals(obj.getVerifiedFlag())) {
					Long nxtCasePlanId = ocdiplanRepository.getNxtCasePlanId(obj);
					obj.setCasePlanId(nxtCasePlanId);
					String supervisionLevel = pimsWeightService.getSupLevel(obj.getOffenderBookId(),commitBean.getCreateUserId());
					obj.setSupervisionLevel(supervisionLevel != null ? supervisionLevel : null);
					int daysCount = checkReviewDate(supervisionLevel);
					cal.setTime(obj.getCreationDate());
					cal.add(Calendar.DATE, daysCount);
					obj.setNextReviewDate(cal.getTime());
					obj.setModifyUserId(commitBean.getCreateUserId());
					updateOldList.add(obj);
				}
			}
			if (updateOldList != null && updateOldList.size() > 0) {
				liReturn = ocdiplanRepository.casePlanOldRecUpdate(updateOldList);
				casePlansT1Service.casePlansT1(plans);
				casePlansTwfService.casePlansTwf(plans);
				liReturn = ocdiplanRepository.casePlanInsert(updateOldList);
				casePlansT2Service.casePlansT2(plans, plans.getCreateUserId());
				casePlansT3Service.casePlansT3(plans, plans.getCreateUserId());
				casePlansT1Service.casePlansT1(plans);
			}
		} else if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocdiplanRepository.casePlanUpdate(commitBean.getUpdateList());
			casePlansT1Service.casePlansT1(plans);

		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffenderCriminogenicNeeds> offCriNeedsExecuteQuery(final OffenderCriminogenicNeeds searchRecord) {
		List<OffenderCriminogenicNeeds> criminogenicList = new ArrayList<>();
		criminogenicList = ocdiplanRepository.offCriNeedsExecuteQuery(searchRecord);
		for (final OffenderCriminogenicNeeds rowData : criminogenicList) {
			rowData.setLatestDate((ocdiplanRepository.offCriNeedsGetLatestDatePostQuery(rowData.getCasePlanId(),
					rowData.getOffenderBookId())).getModifyDatetime());
		}
		return criminogenicList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_CRI_NEEDS
	 *
	 * 
	 */
	@Transactional
	public Integer offCriNeedsCommit(final OffenderCriminogenicNeedsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		List<OffenderCriminogenicNeeds> returnData = new ArrayList<>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bean -> {bean.setCreateUserId(commitBean.getCreateUserId());
			                                            bean.setModifyUserId(commitBean.getCreateUserId());});
			for( final OffenderCriminogenicNeeds obj: commitBean.getInsertList()){
				returnData.clear();
				obj.setOffCrimNeedId(ocdiplanRepository.getoffCrimNeedIdSeq());
				returnData.add(obj);
				liReturn = ocdiplanRepository.offCriNeedsInsertOffenderCriminogenicNeeds(returnData);
				ocdiplanRepository.whenNextbuttonUpdates(obj);
			}
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> bean.setModifyUserId(commitBean.getCreateUserId()));

			for( final OffenderCriminogenicNeeds obj: commitBean.getUpdateList()){
				returnData.clear();
				returnData.add(obj);
				liReturn = ocdiplanRepository.offCriNeedsUpdateOffenderCriminogenicNeeds(returnData);
				ocdiplanRepository.whenNextbuttonUpdates(obj);
			}		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffApV1> offActionPlansV1ExecuteQuery(final OffApV1 searchRecord) {
		return ocdiplanRepository.offActionPlansV1ExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_ACTION_PLANS_V1
	 *
	 * 
	 */
	@Transactional
	public Integer offActionPlansV1Commit(final OffApV1CommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffApV1 offApV1 : commitBean.getInsertList()) {
				offApV1.setCreateUserId(commitBean.getCreateUserId());
				offApV1.setModifyUserId(commitBean.getCreateUserId());
				liReturn = apVrIudService.apVrIudTrigger(offApV1, "INSERTING");
			}
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffApV1 offApV1 : commitBean.getUpdateList()) {
				offApV1.setModifyUserId(commitBean.getCreateUserId());
				liReturn = apVrIudService.apVrIudTrigger(offApV1, "UPDATING");
			}
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			//			liReturn = ocdiplanRepository.offActionPlansV1DeleteOffApV1(commitBean.getDeleteList());
			for (OffApV1 offApV1 : commitBean.getDeleteList()) {
				liReturn = apVrIudService.apVrIudTrigger(offApV1, "DELETING");
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffenderCaseConditions> offCaseCondsExecuteQuery(final OffenderCaseConditions searchRecord) {
		List<OffenderCaseConditions> returnList = new ArrayList<>();
		returnList = ocdiplanRepository.offCaseCondsExecuteQuery(searchRecord);
		
		if("ACTIVE".equalsIgnoreCase( searchRecord.getCasePlanStatusDesc())) {
			List<OffenderSentConditions> sentCondList = new ArrayList<>();
			sentCondList = ocdiplanRepository.getOffSentConditions(searchRecord.getOffenderBookId());
			List<OffenderSentConditions> insertList = new ArrayList<>();
			if((returnList== null || returnList.isEmpty()) && (sentCondList!=null && !sentCondList.isEmpty())) {
				insertList.addAll(sentCondList);
			} else if(sentCondList!=null && !sentCondList.isEmpty()) {
				for(OffenderSentConditions obj : sentCondList) {
					//If condition is not in the Case conditions
					List<OffenderCaseConditions> filterList = returnList.stream().filter(e -> new BigDecimal(obj.getOffenderSentConditionId()).equals(e.getOffenderSentConditionId())).collect(Collectors.toList());
					if(filterList == null || filterList.isEmpty()) {
						insertList.add(obj);
					}
				}
			}
			if(insertList!=null && insertList.size()>0) {
				for (OffenderSentConditions inObj : insertList ){
					offCaseConditionsT1Service.offenderCaseConditionsT1(inObj);
				}		
				returnList = ocdiplanRepository.offCaseCondsExecuteQuery(searchRecord);
			}
		}
		for (final OffenderCaseConditions returnObj : returnList) {
			CommunityConditions communityConditionsBean = new CommunityConditions();
			communityConditionsBean = ocdiplanRepository.offCaseCondsPostQueryCode(returnObj);
			if (communityConditionsBean.getDescription() != null) {
				returnObj.setDescription(communityConditionsBean.getDescription());
			}
			OffenderCaseConditions returnBean = ocdiplanRepository
					.offCaseCondsGetLatestDatePostQuery(returnObj.getCasePlanId(), returnObj.getOffenderBookId());
			if (returnBean.getModifyDatetime() != null) {
				returnObj.setLatestDatetime(returnBean.getModifyDatetime());
			}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_CASE_CONDS
	 *
	 * 
	 */
	@Transactional
	public Integer offCaseCondsCommit(final OffenderCaseConditionsCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocdiplanRepository.offCaseCondsUpdateOffenderCaseConditions(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffApV2> offActionPlansV2ExecuteQuery(final OffApV2 searchRecord) {
		return ocdiplanRepository.offActionPlansV2ExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_ACTION_PLANS_V2
	 *
	 * 
	 */
	@Transactional
	public Integer offActionPlansV2Commit(final OffApV2CommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			//			liReturn = ocdiplanRepository.offActionPlansV2InsertOffApV2(commitBean.getInsertList());
			for (OffApV2 offApV2 : commitBean.getInsertList()) {
				offApV2.setCreateUserId(commitBean.getCreateUserId());
				offApV2.setModifyUserId(commitBean.getCreateUserId());

				liReturn = ap2VrIudService.ap2VrIudTrigger(offApV2,"INSERTING");
			}

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
//						liReturn = ocdiplanRepository.offActionPlansV2UpdateOffApV2(commitBean.getUpdateList());
			for (OffApV2 offApV2 : commitBean.getUpdateList()) {
				offApV2.setModifyUserId(commitBean.getCreateUserId());
				liReturn = ap2VrIudService.ap2VrIudTrigger(offApV2,"UPDATING");
			}

		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			//			liReturn = ocdiplanRepository.offActionPlansV2DeleteOffApV2(commitBean.getDeleteList());
			for (OffApV2 offApV2 : commitBean.getDeleteList()) {
				liReturn = ap2VrIudService.ap2VrIudTrigger(offApV2,"DELETING");
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VSummaryCasePlans> vSummaryCasePlanExecuteQuery(final VSummaryCasePlans searchRecord) {
		return ocdiplanRepository.vSummaryCasePlanExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_SUMMARY_CASE_PLAN
	 *
	 * 
	 */
	@Transactional
	public Integer vSummaryCasePlanCommit(final VSummaryCasePlansCommitBean CommitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgCaseplanAssRecordGroup() {
		return ocdiplanRepository.rgCaseplanAssRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<Dual> rgCaseInfoRecordGroup() {
		return ocdiplanRepository.rgCaseInfoRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> cgfkCasPlnDspDescriptionRecordGroup() {
		return ocdiplanRepository.cgfkCasPlnDspDescriptionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<AgencyLocations> cgfkCasPlnDspDescription4RecordGroup() {
		return ocdiplanRepository.cgfkCasPlnDspDescription4RecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgCrimNeedsStsRecordGroup() {
		return ocdiplanRepository.rgCrimNeedsStsRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgCaseworkRecordGroup() {
		return ocdiplanRepository.rgCaseworkRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgPrgCategoryRecordGroup() {
		return ocdiplanRepository.rgPrgCategoryRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ProgramServices> rgProgramIdRecordGroup(final String programCategory) {
		return ocdiplanRepository.rgProgramIdRecordGroup(programCategory);

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ProgramServices> rgProgramId2RecordGroup() {
		return ocdiplanRepository.rgProgramId2RecordGroup();

	}

	@Override
	public Integer insertUpdateFlagQuery(final CasePlans searchBean) {
		Integer StaffId = null;
		Integer sacStaffId = null;
		Integer instStaffId = null;
		Integer exist = 0;
		Integer profileVal = null;
		Integer returnVal = 0;
		StaffId = ocdiplanRepository.getStaffId(searchBean.getUserId());
		CasePlans caseplanUser = ocdiplanRepository.getAutherizedUser(searchBean);
		if (caseplanUser != null) {
			sacStaffId = Integer.valueOf(caseplanUser.getSacStaffId().toString());
			instStaffId = Integer.valueOf(caseplanUser.getInstSacStaffId().toString());
		}
		profileVal = ocdiplanRepository.getRoleValue();
		if (profileVal != null) {
			exist = ocdiplanRepository.getverifyUserRole(profileVal, searchBean.getUserId());
		}
		if ("ACTIVE".equals(searchBean.getCasePlanStatus())) {
			if (StaffId.equals(sacStaffId) || StaffId.equals(instStaffId) || exist != 0) {
				returnVal = 1;
			} else {
				returnVal = 0;
			}
		} else {
			returnVal = 0;
		}
		return returnVal;
	}

	@Override
	public List<WorkFlowLogs> workFlExecuteQuery(final CasePlans searchBean) {
		List<WorkFlowLogs> returnList = new ArrayList<>();
		final Long workFlowId = ocdiplanRepository.getworkFlowId(searchBean);
		if (workFlowId != null) {
			searchBean.setWorkFlowId(workFlowId);
			returnList = ocdiplanRepository.workFlExecuteQuery(searchBean);
		}
		return returnList;
	}

	@Override
	public String getStaffName(String userId) {
		return ocdiplanRepository.getStaffName(userId);
	}
	
	@Override
	public List<String> getUserIdOfAssignedStaff(CasePlans searchBean) {
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		List<String> userIdList = new ArrayList<String>();
		try {
			returnList = ocdiplanRepository.getUserIdOfAssignedStaff(searchBean.getOffenderBookId(),searchBean.getCasePlanId());
			returnList.forEach(bean->{
				userIdList.add(bean.getUserId());
			});
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getUserIdOfAssignedStaff method call "+e);
		}
		return userIdList;
	}

	@Override
	public List<String> getUserIdOfAssignedStaffForCpOwn(CasePlans searchBean) {
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		List<String> userIdList = new ArrayList<String>();
		try {
			returnList = ocdiplanRepository.getUserIdOfAssignedStaffForCpOwn(searchBean.getOffenderBookId(),searchBean.getCasePlanId());
			returnList.forEach(bean->{
				userIdList.add(bean.getUserId());
			});
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getUserIdOfAssignedStaffForCpOwn method call "+e);
		}
		return userIdList;
	}	
	
}
