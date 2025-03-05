package net.syscon.s4.pkgs.common.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inst.classification.beans.OiiclassClassInquiry;
import net.syscon.s4.inst.legals.au.VCbSentTerms;
import net.syscon.s4.inst.legals.au.VOffBalCals;
import net.syscon.s4.inst.legalscreens.sentenceadministration.beans.VCbCustodyPeriod;
import net.syscon.s4.pkgs.calculate_balances.CalculateBalancesService;
import net.syscon.s4.pkgs.common.CommonRepository;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.pkgs.lock_unlock_module.LockUnlockModuleService;
import net.syscon.s4.pkgs.tag_workflow_adm.TagWorkflowAdmRepository;
import net.syscon.s4.pkgs.trust.TrustService;
import net.syscon.s4.triggers.FarCsldCurrentAcctsTxnsT1Service;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private CommonRepository commonRepository;
	@Autowired
	private LockUnlockModuleService lockUnlockModuleService;
	@Autowired
	private TrustService trustService;
	@Autowired
	private FarCsldCurrentAcctsTxnsT1Service farCsldCurrentAcctsTxnsT1Service;
	@Autowired
	private TagWorkflowAdmRepository tagWorkflowAdmRepository;

	private static Logger logger = LogManager.getLogger(CommonServiceImpl.class.getName());

	private static final String NO = "N";
	private static final String OCDCBENE = "OCDCBENE";
	private static final String HOA = "HOA";
	private static final String DR = "DR";
	private static final String OTDHOLDT = "OTDHOLDT";
	private static final String OVERDUE = "OVERDUE";
	private static final String DUE_FOR_INITIAL = "DUE FOR INITIAL";
	private static final String DUE_FOR_REVIEW = "DUE FOR REVIEW";
    private long vAgyLoc;

	@Autowired
	private CalculateBalancesService calcuateBalancesService;

	// This function checks if the image exits for physical identifier block
	@Override
	public String imageMarkExists(final BigDecimal pOffenderBookId, final String pImageObjectType,
			final String pMarkType, final String pBodyPart, final BigDecimal pObjectSeq) {

		final String lvColumnName;
		String lvResult = NO;
		// return the COLUMN_NAME from ALL_TAB_COLUMNS table
		lvColumnName = commonRepository.getImageColumnCur();
		// If it finds an image return Y and if it cannot find it return N.
		lvResult = commonRepository.getResultImageMarkExist(pOffenderBookId, pImageObjectType, pMarkType, pBodyPart,
				pObjectSeq, lvColumnName);
		return lvResult;
	}

	@Override
	public Integer createAgencyTab() {
		String vFloorsql;
		String vLocsql;
		Integer vFlrPlan;
		Integer vAgyHotspot;
		Integer result = 1;
		try {
			vFlrPlan = commonRepository.getCountForCreateTabOne();
			if ((vFlrPlan != null ? vFlrPlan : 0) == 0) {
				vFloorsql = "CREATE TABLE FLOOR_PLAN" + "(FLOOR_PLAN_ID NUMBER(20,0) NOT NULL ENABLE, "
						+ "IMAGE_ID NUMBER(10,0), " + "ROOT_FLOOR_PLAN CHAR(1 BYTE), "
						+ "PARENT_FLOOR_PLAN NUMBER(20,0), " + "AGY_LOC_ID VARCHAR2(20 BYTE), "
						+ "BREAD_CRUMB_NAV_ID NUMBER(20,0) " + ")";

				commonRepository.createAgencyTab(vFloorsql);
			}
			vAgyHotspot = commonRepository.getCountForCreateTabTwo();
			if ((vAgyHotspot != null ? vAgyHotspot : 0) == 0) {
				vLocsql = "CREATE TABLE AGENCY_INTERNAL_LOC_HOTSPOT"
						+ "(INTERNAL_LOC_HOTSPOT_ID NUMBER(20, 0) NOT NULL ,"
						+ "INTERNAL_LOCATION_ID NUMBER(20, 0) NOT NULL ," + "X_COORDINATE NUMBER ,"
						+ "Y_COORDINATE NUMBER ," + "FLOOR_PLAN_ID NUMBER(20, 0) ," + "FLOOR_PLAN_NEXT_ID NUMBER(20, 0)"
						+ ")";
				commonRepository.createAgencyTab(vLocsql);
			}

		} catch (Exception e) {
			result = 0;
			logger.error("createAgencyTab :" + e);
		}
		return result;
	}

	@Override
	@Transactional
	public void unlockFormModule(final String caseloadId, final String currentUser) {
		final String pModuleName = OCDCBENE;
		commonRepository.unlockFormModule(caseloadId, pModuleName, currentUser);
	}

	@Override
	public void lockFormModule(final String caseloadId, final String currentUser) {
		try {
			final String pModuleName = OCDCBENE;
			lockUnlockModuleService.lockFormModule(pModuleName, caseloadId, currentUser, null, null, currentUser);
		} catch (Exception e) {
			logger.error("lockFormModule :" + e);
		}

	}

	@Override
	public void processHold(final Integer txnId, final String caseloadId, final Long offenderId, final String txnType,
			final Integer holdDays, final String subAccountType, final Double txnEntryAmount, final String txnEntryDesc,
			final String txnRefNo, final Integer txnnum, final Integer holdNumbers, final String userName) {
		Long pOffBookId;
		Long pTxnEntrySeq;
		Long pGlSeq;
		String pTxnPostingType;
		String pTxnType;
		String pTxnAdjustedFlag;
		String pHoldClearFlag;
		Date pHoldUntilDate;

		pTxnEntrySeq = 0l;
		pTxnType = HOA;
		pTxnAdjustedFlag = NO;
		pHoldClearFlag = NO;
		pTxnPostingType = DR;

		pOffBookId = commonRepository.getMaxOffBookId(offenderId);
		pTxnEntrySeq = commonRepository.getMaxTxnEntrySeq(txnId);
		pGlSeq = 0l;

		pHoldUntilDate = commonRepository.getHoldUntilDate(holdDays);
		commonRepository.insertOffenderTransactions(txnId, pTxnEntrySeq, caseloadId, offenderId, pOffBookId,
				pTxnPostingType, pTxnType, txnEntryDesc, txnEntryAmount, subAccountType, pTxnAdjustedFlag,
				pHoldClearFlag, holdNumbers, pHoldUntilDate, txnRefNo, txnType, userName);

		final OffenderTransactions obj = new OffenderTransactions();
		obj.setCaseloadId(caseloadId);
		obj.setOffenderId(offenderId);
		obj.setTxnPostingType(pTxnPostingType);
		obj.setTxnEntryDate(new Date());
		obj.setTxnId(txnId);
		obj.setTxnType(pTxnType);
		obj.setTxnEntryAmount(txnEntryAmount);
		obj.setSubAccountType(subAccountType);
		trustService.updateOffenderBalance(obj, userName);

		trustService.processGlTransNew(caseloadId, pTxnType, null, txnEntryAmount, txnId, new Date(), txnEntryDesc,
				pTxnEntrySeq.intValue(), OTDHOLDT, offenderId.intValue(), pOffBookId, subAccountType, null, null, null,
				null, pGlSeq.intValue(), null,userName);

		commonRepository.updateOffenderTrustAccounts(txnEntryAmount, offenderId, caseloadId, userName);
		commonRepository.updateOffenderSubAccounts(txnEntryAmount, offenderId, caseloadId, subAccountType, userName);
	}

	@Override
	public List<OiiclassClassInquiry> oiiclassClassInquiry(final OiiclassClassInquiry objSearchDao) {
		// below result or never used
		if (objSearchDao.getpSearchType() != null) {
			if (OVERDUE.equals(objSearchDao.getpSearchType())) {
				objSearchDao.setResultSet(commonRepository.oiiclassClassInquiryFirstSelect(objSearchDao));
			} else if (DUE_FOR_INITIAL.equals(objSearchDao.getpSearchType())) {
				objSearchDao.setResultSet(commonRepository.oiiclassClassInquirySecondSelect(objSearchDao));
			} else if (DUE_FOR_REVIEW.equals(objSearchDao.getpSearchType())) {
				objSearchDao.setResultSet(commonRepository.oiiclassClassInquiryThirdSelect(objSearchDao));
			}
		}
		return objSearchDao.getResultSet();
	}

	@Override
	public String changeWorkingCaseload(final String pCaseLoadId, final String userName) {
		String result = null;
		final String lvWorkCaseloadId = commonRepository.getCurrentCur(userName);
		if (lvWorkCaseloadId != pCaseLoadId) {
			commonRepository.updateStaffMembers(pCaseLoadId, userName);
			result = "TRUE";
		} else {
			result = null;
		}
		return result;
	}

	@Override
	@Transactional
	public Integer otmoncoaGenAccountCodes(final String pCaseloadId, final Integer pAccountCode,
			final String createUserId) {
		final Date cSysdate = new Date();
		Integer genSeq = 0;
		Long lCurrentPeriodId;
		Long lLastClosedPeriod;

		lCurrentPeriodId = commonRepository.getLCurrentPeriodCur(cSysdate);

		final List<Caseloads> caseLoadList = commonRepository.getLCaseloadsCur(pCaseloadId);
		for (final Caseloads bean : caseLoadList) {
			lLastClosedPeriod = commonRepository.getLLastClosedCur(bean.getCaseloadId());

			final List<AccountCodes> baseList = commonRepository.getLCsldCurrAcctBaseCur(bean.getCaseloadId(),
					pAccountCode, lLastClosedPeriod);
			for (final AccountCodes ac : baseList) {
				commonRepository.insertCaseloadCurrentAccountsBase(ac.getCaseloadId(), ac.getAccountCode(),
						lCurrentPeriodId, cSysdate, createUserId);
			}

			final List<AccountCodes> txnsList = commonRepository.getLCsldCurrAcctTxnsCur(bean.getCaseloadId(),
					pAccountCode, lLastClosedPeriod, lCurrentPeriodId, createUserId);
			for (final AccountCodes txns : txnsList) {
				txns.setCreateUserId(createUserId);
				commonRepository.insertCaseloadCurrentAccountsTxns(txns);
				// Trigger Call FAR_CSLD_CURRENT_ACCTS_TXNS_T1
				farCsldCurrentAcctsTxnsT1Service.farCsldCurrentAcctsTxnsT1Trigger(txns);
			}
			final List<AccountCodes> periodList = commonRepository.getLCsldAcctPeriodsCur(bean.getCaseloadId(),
					lLastClosedPeriod, createUserId);
			for (final AccountCodes periods : periodList) {
				periods.setCreateUserId(createUserId);
				commonRepository.insertCaseloadAccountPeriods(periods);
			}
			final List<AccountCodes> summList = commonRepository.getLCsldAcctSummCur(bean.getCaseloadId(), pAccountCode,
					lLastClosedPeriod, createUserId);
			for (final AccountCodes summ : summList) {
				summ.setCreateUserId(createUserId);
				commonRepository.insertCaseloadAccountSummaries(summ);
			}
		}
		genSeq = 2;
		return genSeq;
	}

	@Override
	public List<VCbSentTerms> procVCbSentTerms(final Long pOffenderId, final Long pOffBookId,
			final Date pEffectiveDate) {
		final VOffBalCals param = new VOffBalCals();
		List<VCbSentTerms> returnList = null;

		try {
			param.setOffenderId(BigDecimal.valueOf(pOffenderId));
			param.setOffenderBookId(pOffenderId);
			param.setEffectiveDate(pEffectiveDate);
			calcuateBalancesService.setOffenderId(param);
			returnList = tagWorkflowAdmRepository.coCursor();
		} catch (final Exception e) {
			logger.error("procVCbCustodyPeriod :", e);
			return Collections.emptyList();
		}
		return returnList;
	}

	@Override
	public List<VCbCustodyPeriod> procVCbCustodyPeriod(final Long pOffenderId, final Long pOffBookId,
			final Date pEffectiveDate) {
		final VOffBalCals param = new VOffBalCals();
		final List<VCbCustodyPeriod> retList;
		try {
			param.setOffenderId(BigDecimal.valueOf(pOffenderId));
			param.setOffenderBookId(pOffenderId);
			param.setEffectiveDate(pEffectiveDate);
			calcuateBalancesService.setOffenderId(param);
			retList = tagWorkflowAdmRepository.coCursorOne();
		} catch (final Exception e) {
			logger.error("procVCbCustodyPeriod :", e);
			return Collections.emptyList();
		}
		return retList;
	}

	@Override
	public String billStatementExists(String billId) {
		return commonRepository.getbillStatementExists(billId);
	}
	
	@Override
	public Integer insIntoBedAssHist(BedAssignmentHistories bean) {
		List<BedAssignmentHistories> list = new ArrayList<BedAssignmentHistories>();
		Integer bedAssignSeq = commonRepository.getBedAssSeqCur(bean.getOffenderBookId());
		bean.setBedAssignSeq(bedAssignSeq);
		list.add(bean);
		return commonRepository.bedAhInsertBedAssignmentHistories(list);
	}
	
	@Override
	public void insertAgencyData() {
		 vAgyLoc=commonRepository.agencyLocationsCount();
		if(vAgyLoc==0) {
			try {
				commonRepository.saveAgencylocations();
			}catch (Exception e) {
				vAgyLoc=0;
				logger.error("insertAgencyData :", e);
			}
		}
	}

	/*
	 * Check the Call form access to the Parent Module
	*/
	@Override
	public Boolean checkCallFormAccess(String userId, String role, String callForm) {
		List<String> accessPrivilegeList = new ArrayList<String>();
		if (role.equals("full")) {
			accessPrivilegeList.add("A");
		} else {
			accessPrivilegeList.add("Q");
			accessPrivilegeList.add("A");
		}
		return commonRepository.checkCallFormAccess(userId, accessPrivilegeList,callForm);
	}
	/*
	 * Check whether user has access to any of the offender specific screen
	 * to execute the global queries like offender search
	*/
	@Override
	public Boolean checkOffenderSpecificScreenAccess(String userId, String role) {
		List<String> accessPrivilegeList = new ArrayList<String>();
		if (role.equals("full")) {
			accessPrivilegeList.add("A");
		} else {
			accessPrivilegeList.add("Q");
			accessPrivilegeList.add("A");
		}
		return commonRepository.checkOffenderSpecificScreenAccess(userId, accessPrivilegeList);
	}

	
}
