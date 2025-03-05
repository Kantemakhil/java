package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.offendertransactions.beans.PaymentPlanTransaction;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderBeneficiariesCommitBean;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.trustaccounts.OcuovrobRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OcuovrobService;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.pkgs.financial.FinancialService;
import net.syscon.s4.pkgs.ocdpayob.OcdpayobPkgService;
import net.syscon.s4.pkgs.trust.TrustService;
import net.syscon.s4.triggers.OffenderBeneficiariesT1Service;
import net.syscon.s4.triggers.OffenderBeneficiariesT2Service;
import net.syscon.s4.triggers.OffenderDeductionsHty;
import net.syscon.s4.triggers.OffenderDeductionsT2Service;
import net.syscon.s4.triggers.OffenderDeductionsThtyService;
import net.syscon.s4.triggers.OffenderDeductionsTjnService;

@Service
public class OcuovrobServiceImpl extends BaseBusiness implements OcuovrobService {

	public OcuovrobServiceImpl() {
		// OcuovrobServiceImpl
	}

	@Autowired
	private OcuovrobRepository ocuovrobRepository;
	@Autowired
	private EliteDateService eliteDateService;
	@Autowired
	private OcdpayobPkgService ocdpayobPkgService;
	@Autowired
	private TrustService trustService;
	@Autowired
	private FinancialService financialService;
	@Autowired
	private OffenderBeneficiariesT1Service offenderBeneficiariesT1Service;
	@Autowired
	private OffenderBeneficiariesT2Service offenderBeneficiariesT2Service;
	@Autowired
	private OffenderDeductionsTjnService offenderDeductionsTjnService;
	@Autowired
	private OffenderDeductionsThtyService offenderDeductionsThtyService;
	@Autowired
	private OffenderDeductionsT2Service offenderDeductionsT2Service;
	
	public static final Integer CHECKCOUNT = 5;

	/**
	 * Creates new OcuovrobServiceImpl class Object
	 */

	public Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR, 0);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			calender.set(Calendar.MILLISECOND, 0);
			final Date returnDate = calender.getTime();
			return returnDate;
		}
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderBeneficiaries> offBncExecuteQuery(final OffenderBeneficiaries searchRecord) {
		List<OffenderBeneficiaries> returnList = new ArrayList<>();
		searchRecord.setCaseLoadType(ocuovrobRepository.getCaseLoadType(searchRecord.getCreateUserId()));
		String whereCond = ocdpayobPkgService.setDefaultWhereOffBnc(searchRecord);
		returnList = ocuovrobRepository.offBncExecuteQuery(searchRecord,whereCond);
		for (final OffenderBeneficiaries returnBean : returnList) {
			BigDecimal adjAmount = null;
			BigDecimal drvAmount = null;
			adjAmount = ocuovrobRepository.getDrvAmount(returnBean);
			drvAmount = ((returnBean.getAmount() != null ? returnBean.getAmount() : new BigDecimal(0)).subtract(
					(returnBean.getReceivedAmount() != null ? returnBean.getReceivedAmount() : new BigDecimal(0))))
							.subtract((adjAmount != null ? adjAmount : new BigDecimal(0)));
			returnBean.setDrvAmount(drvAmount);
			if (returnBean.getCorporateId() != null) {
				String corporateName = ocuovrobRepository.getCorporateNameFromId(returnBean);
				returnBean.setCorporateName(corporateName != null ? corporateName.toUpperCase() : null);
			}
			OffenderDeductions deducBean = new OffenderDeductions();
			deducBean.setOffenderDeductionId(returnBean.getOffenderDeductionId());
			deducBean = ocuovrobRepository.cgfkchkOffBncOffBncOff(deducBean);
			returnBean.setDeductionType(deducBean.getDeductionType());
			returnBean.setEffectiveDate(deducBean.getEffectiveDate());
			returnBean.setMaxTotalAmount(
					deducBean.getMaxTotalAmount() != null ? deducBean.getMaxTotalAmount() : new BigDecimal(0));
			returnBean.setMaxRecursiveAmount(
					deducBean.getMaxRecursiveAmount() != null ? deducBean.getMaxRecursiveAmount() : new BigDecimal(0));
			returnBean.setInformationNumber(deducBean.getInformationNumber());
			if (returnBean.getPersonId() != null) {
				Persons personBean = new Persons();
				personBean = ocuovrobRepository.cgfkchkOffBncOffBncPer(returnBean);
				returnBean.setLastName(personBean.getLastName() != null ? personBean.getLastName() : null);
				returnBean.setFirstName(personBean.getFirstName() != null ? personBean.getFirstName() : null);
			}
			returnBean.setModuleName(searchRecord.getModuleName());
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_BNC
	 *
	 */
	@Transactional
	public Integer offBncCommit(final OffenderBeneficiariesCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final OffenderBeneficiaries returnBean : commitBean.getUpdateList()) {
				returnBean.setCreateUserId(commitBean.getCreateUserId());
				if (returnBean.getOverrideAmount() != null
						&& returnBean.getOverrideAmount().compareTo(BigDecimal.ZERO) != 0
						&& "OCDCPPAY".equals(returnBean.getModuleName())) {
					String cfppSetUpFlag = ocuovrobRepository.checkCfppsetUp(returnBean.getOffenderDeductionId());
					if (cfppSetUpFlag != null && "N".equals(cfppSetUpFlag)) {
						return 1773;
					}

				}
			}
			if (!commitBean.getUpdateList().isEmpty()
					&& "OCDCPPAY".equals(commitBean.getUpdateList().get(0).getModuleName())) {
				commitBean.getUpdateList().forEach(obj->{ obj.setCreateUserId(commitBean.getCreateUserId()); });
				Integer cpayCount = ocdcppayCommit(commitBean.getUpdateList());
				if (cpayCount == 5) {
					return CHECKCOUNT;
				}
				for (final OffenderBeneficiaries bean : commitBean.getUpdateList()) {
					BigDecimal recAmount = ocuovrobRepository.getReceivedAmount(bean);
					if (recAmount != null) {
						bean.setReceivedAmount(recAmount);
					}
					bean.setOverrideAmount(null);
				}
			}
			
			for (final OffenderBeneficiaries returnBean : commitBean.getUpdateList()) {
				List<OffenderBeneficiaries> oldRecList = ocuovrobRepository.getOffenderBenOldRec(returnBean.getBeneficiaryId());
				OffenderBeneficiaries oldRec = new OffenderBeneficiaries();
				if (oldRecList != null) {
					oldRec = oldRecList.get(0);
				}
				offenderBeneficiariesT1Service.offenderBeneficiariesT1Trigger(returnBean, oldRec);
				offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(returnBean, "UPDATE");
			}
			liReturn = ocuovrobRepository.offBncUpdateOffenderBeneficiaries(commitBean.getUpdateList());
		}
		return liReturn;
	}

	public Integer ocdcppayCommit(List<OffenderBeneficiaries> updateList) {
		Integer returnCount = 0;
		Integer lvTxnSeq = 0;
		String overrideAmount = null;
		Date sysdate = trunc(eliteDateService.getDBTime());
		if (!updateList.isEmpty() && updateList.size() > 0) {
			for (final OffenderBeneficiaries bean : updateList) {
				//Procedure Call
				Map<String, Object> statusMap = trustService.chkAccountStatus(bean.getCaseloadId(),
						bean.getOffenderId());
				String accountStatus = String.valueOf(statusMap.get("P_OPEN_AN_ACCOUNT"));
				if ("Y".equals(accountStatus)) {
					return CHECKCOUNT;
				}
				Long lvDeductionId = bean.getOffenderDeductionId();
				String lvCaseNumber = ocuovrobRepository.getCaseNumber(lvDeductionId);
				if (bean.getOverrideAmount() != null) {
					overrideAmount = bean.getOverrideAmount().toString().replace(".00", "");
				}
				String lvTxnDesc = "PRIOR PAYMENTS <" + lvCaseNumber + "> <$" + bean.getOverrideAmount() + "> <"
						+ bean.getLocation() + "> <" + bean.getCommentText() + ">";
				if (lvTxnSeq == 0) {
					lvTxnSeq = 1;
				} else {
					lvTxnSeq = lvTxnSeq + 1;
				}
				OffenderDeductions lvDedRec = ocuovrobRepository.getDeductionInfoCur(lvDeductionId);
				String lvCrAccCode = ocuovrobRepository.getCrAccCodeCur(bean.getCaseloadId(),
						lvDedRec.getDeductionType());
				String lvTransOprRec = ocuovrobRepository.getTransDetailCur(bean.getCaseloadId());
				String lvSubAccountType = ocuovrobRepository.getSubAccountTypeCur(lvTransOprRec);
				OffenderBookings bkgBean = ocuovrobRepository.getOffenderBookIdCur(bean.getOffenderId());
				// create_gl_transactions
			     ocuovrobRepository.getPostingTypeCur(lvCrAccCode);
				Integer lvTxnId = ocuovrobRepository.getTxnId();
				//Procedure Call
//				ocuovrobRepository.trustInsertGltransNew("DR", 1, lvCrAccCode, lvTxnPostUsage, bean.getCaseloadId(),
//						lvTxnDesc, lvTxnSeq, bean.getOffenderId(), bkgBean.getOffenderBookId(), lvCaseNumber,
//						bean.getPersonId(), bean.getCorporateId(), lvDeductionId, lvTxnId, sysdate);
				
				GlTransactions obj = new GlTransactions();
				obj.setTxnId(lvTxnId == null ? null : lvTxnId.longValue());
				obj.setTxnEntrySeq(lvTxnSeq == null ? null : lvTxnSeq.longValue());
				obj.setGlEntrySeq(Long.valueOf(1));
				obj.setAccountCode(new BigDecimal(lvCrAccCode));
				obj.setTxnEntryDate(sysdate);
				obj.setTxnType("DEDC");
				obj.setTxnPostUsage("DR");
				obj.setCaseloadId(bean.getCaseloadId());
				obj.setOffenderId(bean.getOffenderId());
				obj.setOffenderBookId(bkgBean.getOffenderBookId() != null ? new BigDecimal(bkgBean.getOffenderBookId()) : null);
				obj.setTxnEntryAmount(BigDecimal.valueOf(0));
				obj.setTxnEntryDesc(lvTxnDesc);
				obj.setTxnReferenceNumber(null);
				obj.setReconClearFlag("N");
				obj.setTxnReversedFlag("N");
				obj.setCreateUserId(bean.getCreateUserId());
				obj.setPayeePersonId(bean.getPersonId());
				obj.setCreateDate(sysdate);
				obj.setPayeeCorporateId(bean.getCorporateId());
				obj.setPayeeNameText(null);
				obj.setReversedTxnId(null);
				obj.setReversedTxnEntrySeq(null);
				obj.setReversedGlEntrySeq(null);
				obj.setDeductionId(lvDeductionId != null ? new BigDecimal(lvDeductionId + "") : null);
				obj.setTxnEntryTime(sysdate);
				obj.setInfoNumber(lvCaseNumber);
				trustService.insertGlTransNew(obj);
				
				//Procedure Call
//				ocuovrobRepository.trustInsertGltransNew("CR", 2, lvCrAccCode, lvTxnPostUsage, bean.getCaseloadId(),
//						lvTxnDesc, lvTxnSeq, bean.getOffenderId(), bkgBean.getOffenderBookId(), lvCaseNumber,
//						bean.getPersonId(), bean.getCorporateId(), lvDeductionId, lvTxnId, sysdate);
				
				GlTransactions data=new GlTransactions();
				data.setTxnId(lvTxnId == null ? null : lvTxnId.longValue());
				data.setTxnEntrySeq(lvTxnSeq == null ? null : lvTxnSeq.longValue());
				data.setGlEntrySeq(Long.valueOf(2));
				data.setAccountCode(new BigDecimal(lvCrAccCode));
				data.setTxnEntryDate(sysdate);
				data.setTxnType("DEDC");
				data.setTxnPostUsage("CR");
				data.setCaseloadId(bean.getCaseloadId());
				data.setOffenderId(bean.getOffenderId());
				data.setOffenderBookId(bkgBean.getOffenderBookId()!=null?new BigDecimal(bkgBean.getOffenderBookId()):null);
				data.setTxnEntryAmount(BigDecimal.valueOf(0));
				data.setTxnEntryDesc(lvTxnDesc);
				data.setTxnReferenceNumber(null);
				data.setReconClearFlag("N");
				data.setTxnReversedFlag("N");
				data.setCreateUserId(bean.getCreateUserId());
				data.setPayeePersonId(bean.getPersonId());
				data.setCreateDate(sysdate);
				data.setPayeeCorporateId(bean.getCorporateId());
				data.setPayeeNameText(null);
				data.setReversedTxnId(null);
				data.setReversedTxnEntrySeq(null);
				data.setReversedGlEntrySeq(null);
				data.setDeductionId(lvDeductionId!=null?new BigDecimal(lvDeductionId+""):null);
				data.setTxnEntryTime(sysdate);
				data.setInfoNumber(lvCaseNumber);
				trustService.insertGlTransNew(data);
				
				ocuovrobRepository.insertIntoBenTransactions(lvTxnId, lvTxnSeq, lvDeductionId, bean.getPersonId(),
						bean.getCorporateId(), bean.getUnknownBenId(), lvCrAccCode, lvTxnDesc, bean.getCaseloadId(),
						bean.getBeneficiaryId(),bean.getCreateUserId());
				String lvPaymentPlan = ocuovrobRepository.checkPaymentPlan();
				if (lvPaymentPlan != null && "Y".equals(lvPaymentPlan) && lvDedRec.getGroupId() != null) {
					//Procedure Call
//					ocuovrobRepository.updatePaymentPlanSchedules(bkgBean.getOffenderId(), bean.getOverrideAmount(), 0,
//							lvDedRec.getInformationNumber(), lvDedRec.getGroupId());
					
					PaymentPlanTransaction pl = new PaymentPlanTransaction();
					pl.setTransactionAmount(bean.getOverrideAmount());
					pl.setInformationNumber(lvDedRec.getInformationNumber());
					pl.setGroupId(lvDedRec.getGroupId());
					pl.setOffenderId(bkgBean.getOffenderId());
					financialService.updatePaymentPlanSchedules(pl, 0l, bean.getCreateUserId());
					
					//Procedure Call
					OffenderTransactions offTranBean = new OffenderTransactions();
					offTranBean.setTxnId(lvTxnId);
					offTranBean.setTxnEntrySeq(lvTxnSeq);
					offTranBean.setOffenderId(bkgBean.getOffenderId()!=null ? bkgBean.getOffenderId().longValue():null);
					offTranBean.setCaseloadId(bean.getCaseloadId());
					offTranBean.setOffenderBookId(bkgBean.getOffenderBookId());
					offTranBean.setTxnPostingType("DR");
					offTranBean.setTxnType("DEDC");
					offTranBean.setTxnEntryDesc(lvTxnDesc);
					offTranBean.setTxnEntryDate(eliteDateService.getDBTime());
					offTranBean.setSubAccountType(lvSubAccountType);
					offTranBean.setSlipPrintedFlag("N");
					offTranBean.setDeductionFlag("Y");
					offTranBean.setCreateUserId(bean.getCreateUserId());
					offTranBean.setDeductionType(lvDedRec.getDeductionType());
					offTranBean.setPayeeCorporateId(bean.getCorporateId()!=null?bean.getCorporateId().intValue():null);
					offTranBean.setPayeePersonId(bean.getPersonId()!= null ? bean.getPersonId().intValue(): null);
					offTranBean.setDeductionType(lvDedRec.getDeductionType());
					offTranBean.setInfoNumber(lvDedRec.getInformationNumber());
					offTranBean.setTxnEntryAmount(0.0);
//					ocuovrobRepository.insertIntoOffenderTrans(lvTxnId, lvTxnSeq, bean.getCaseloadId(),
//							bkgBean.getOffenderId(), bkgBean.getOffenderBookId(), "DR", "DEDC", lvTxnDesc, 0, sysdate,
//							lvSubAccountType, "Y", bean.getOverrideAmount(), lvDedRec.getDeductionType(),
//							bean.getCorporateId(), bean.getPersonId(), lvDedRec.getInformationNumber());
					
					trustService.insertIntoOffenderTrans(offTranBean);
					
					bean.setTxnEntrySeq(lvTxnSeq);
					bean.setTxnId(lvTxnId);
					ocuovrobRepository.insOffCrPriorPayments(bean);
					returnCount = ocuovrobRepository.updtOffenderDeductions(bean);
					//OFFENDER_DEDUCTIONS_TJN 
					OffenderDeductions newRec = new OffenderDeductions();
					OffenderDeductions oldRec = new OffenderDeductions();
					oldRec = ocuovrobRepository.getoffenderDedOldRec(bean.getOffenderDeductionId());
					//offenderDeductionsTjnService.offenderDeductionsTjn(newRec, oldRec, "UPDATE");
					//OFFENDER_DEDUCTIONS_THTY
					OffenderDeductionsHty offenderDeductionsThty = new OffenderDeductionsHty();
					BeanUtils.copyProperties(bean, offenderDeductionsThty);
					offenderDeductionsThty.setCreateUserId(bean.getCreateUserId());
					offenderDeductionsThty.setJnOracleUser(bean.getCreateUserId());
					offenderDeductionsThtyService.OffenderDeductionsThtyTrigger(offenderDeductionsThty, "UPDATE");
					//OFFENDER_DEDUCTIONS_T2
					newRec.setOffenderDeductionId(bean.getOffenderDeductionId());
					newRec.setInformationNumber(bean.getInformationNumber());
					newRec.setDeductionType(bean.getDeductionType());
					newRec.setMaxTotalAmount(bean.getMaxTotalAmount());
					BeanUtils.copyProperties(bean, newRec);
					offenderDeductionsT2Service.offenderDeductionsT2Trigger(newRec, oldRec, "UPDATE");
				}

			}
		}
		return returnCount;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(SystemProfiles searchRecord) {
		return ocuovrobRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 */
	@Transactional
	public Integer sysPflCommit(SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			// liReturn =
			ocuovrobRepository.sysPflInsertSystemProfiles(commitBean.getInsertList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			// liReturn =
			commitBean.getDeleteList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			ocuovrobRepository.sysPflDeleteSystemProfiles(commitBean.getDeleteList());
		}
		return liReturn;
	}

}