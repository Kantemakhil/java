package net.syscon.s4.cm.intakeclosure.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.deductions.OcdofaccRepository;
import net.syscon.s4.cf.deductions.OcdofaccService;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
//import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
//import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.cm.intakeclosure.OcdaltccRepository;
import net.syscon.s4.cm.intakeclosure.OcdsupstRepository;
import net.syscon.s4.cm.intakeclosure.OcdsupstService;
import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.OffSupervisionStsHty;
import net.syscon.s4.common.beans.OffSupervisionStsHtyCommitBean;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtdcntacService;
import net.syscon.s4.pkgs.trust.TrustService;
import net.syscon.s4.triggers.OffFeeAccountProfileT1AndT2Service;

/**
 * Class OcdsupstServiceImpl@Service
 */
@Service
public class OcdsupstServiceImpl extends BaseBusiness implements OcdsupstService {
	@Autowired
	private OcdsupstRepository ocdsupstRepository;
	@Autowired
	private OcdaltccRepository ocdaltccRepository;
	@Autowired
	private OcdofaccRepository ocdofaccRepository;
	@Autowired
	private EliteDateService eliteDateService;
	@Autowired
	private OcdofaccService ocdofaccService;
	@Autowired
	private OtdcntacService otdcntacService;
	@Autowired
	private TrustService trustService;
	@Autowired
	private OffFeeAccountProfileT1AndT2Service offfeeaccountprofilet1andt2service;
	@Override
	public List<OffenderBookingAgyLocs> offBkgAgyLocExecuteQuery(final OffenderBookingAgyLocs searchBean) {
		final List<OffenderBookingAgyLocs> returnList = ocdsupstRepository.offBkgAgyLocExecuteQuery(searchBean);
		for (final OffenderBookingAgyLocs bean : returnList) {
			final String dspDescr = ocdaltccRepository.cgfkchkOffagyAgencyLocatio(bean.getAgyLocId());
			bean.setAgyLocDescription(dspDescr);

		}
		return returnList;
	}

	@Override
	public String getProfileValue() {
		return ocdsupstRepository.getProfileValue();
	}

	@Override
	public ReferenceCodes getBillableFlag(final String code) {
		return ocdsupstRepository.getBillableFlag(code);
	}

	@Transactional
	public Integer suphstyCommit(final OffSupervisionStsHtyCommitBean commitBean) {
		int liReturn = 0;
		Integer overlapCount = 0;
		// updateRecords
		if (commitBean.getInsertList() == null && commitBean.getUpdateList() != null
				&& !commitBean.getUpdateList().isEmpty()) {
			liReturn = ocdsupstRepository.suphstyUpdateQuery(commitBean.getUpdateList());
		}
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			
		commitBean.getInsertList().forEach(bean -> bean.setCreateUserId(commitBean.getCreateUserId()));
			
//			for (OffSupervisionStsHty bean : commitBean.getInsertList()) {
//				if ("N".equals(bean.getErrorFlag())) {
//					overlapCount = ocdsupstRepository.validateStartDate(bean);
//					if (overlapCount > 0) {
//						return 2;
//					}
//				}
//				// pre-update
//				// ocdsupstRepository.preUpdateSupvAccount(bean);
//			}
			if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
				liReturn = ocdsupstRepository.suphstyUpdateQuery(commitBean.getUpdateList());
			}
			liReturn = ocdsupstRepository.suphstyInsertQuery(commitBean.getInsertList());
			if (liReturn == 2) {
				return liReturn;
			}
			for (OffSupervisionStsHty supbean : commitBean.getInsertList()) {
				supbean.setCreateUserId(commitBean.getCreateUserId());
				if ("N".equals(supbean.getErrorFlag()) && supbean.getTrustAccount()) {
					updateFeeAccounts(commitBean.getInsertList());
				}
				ReferenceCodes returnObj = ocdsupstRepository.getBillableFlag(supbean.getSupStatus());
				if ("N".equals(supbean.getErrorFlag()) && returnObj != null && "B".equals(returnObj.getParentCode())) {
					if (supbean.getTrustAccount()) {
						createTrustAccount(supbean);
						//String trustAccStatus = ocdsupstRepository.checkAccountSatus(supbean);
						//caseLoadId, final BigDecimal offenderId
						 Map<String, Object> map =  trustService.chkAccountStatus(supbean.getCaseloadId(), BigDecimal.valueOf( supbean.getOffenderId()));
						 String trustAccStatus = map!=null &&  map.get("P_OPEN_AN_ACCOUNT")!=null ?(String) map.get("P_OPEN_AN_ACCOUNT"):null;
						if (trustAccStatus != null && !"X".equals(trustAccStatus)) {
				autoCreateFeeAccounts(supbean.getOffenderBookId(), supbean.getCaseloadId(),
							supbean.getStartDatetime(), supbean.getCreateUserId());
						}
//						generateAutoFeeAccBills(supbean.getOffenderBookId(),supbean.getUserId(),"OCDSUPST");
					} else {
						createTrustAccount(supbean);
						//String trustAccStatus = ocdsupstRepository.checkAccountSatus(supbean);
					   Map<String, Object> mapOne = trustService.chkAccountStatus(supbean.getCaseloadId(), BigDecimal.valueOf( supbean.getOffenderId()));
					   String trustAccStatus = mapOne!=null && mapOne.get("P_OPEN_AN_ACCOUNT")!=null ?  (String) mapOne.get("P_OPEN_AN_ACCOUNT") : null;
						if (trustAccStatus != null && !"X".equals(trustAccStatus)) {
						autoCreateFeeAccounts(supbean.getOffenderBookId(), supbean.getCaseloadId(),
								supbean.getStartDatetime(), supbean.getCreateUserId());
						}
//						generateAutoFeeAccBills(supbean.getOffenderBookId(),supbean.getUserId(),"OCDSUPST");
					}
				}
			}
		}

		return liReturn;
	}

	public Integer updateFeeAccounts(final List<OffSupervisionStsHty> listObj) {
		String getAutoFeeCr = ocdsupstRepository.getProfileValue();
		if ("Y".equals(getAutoFeeCr)) {
			for (OffSupervisionStsHty bean : listObj) {
				List<OffSupervisionStsHty> returnList = getSupvQueryData(bean);
				if ("Y".equals(bean.getBillableFlag()) && ((returnList.size() == 1)
						|| (returnList.size() > 1 && "Y".equals(returnList.get(1).getErrorFlag())))) {
					updateBillbleFeeAccounts(bean);
				} else if (returnList.size() > 1 && "N".equals(bean.getBillableFlag())
						&& "Y".equals(returnList.get(1).getBillableFlag())) {
					updateNonBillbleFeeAccounts(bean);
				} else if (returnList.size() > 1 && "Y".equals(bean.getBillableFlag())
						&& "N".equals(returnList.get(1).getBillableFlag())) {
					updateBillbleFeeAccounts(bean);
				} else {
					return 1;
				}
			}
		}
		return 1;

	}

	@Override
	public List<OffSupervisionStsHty> getSupvQueryData(final OffSupervisionStsHty searchBean) {
		List<OffSupervisionStsHty> returnList = ocdsupstRepository.supHisExecuteQuery(searchBean);
		for (OffSupervisionStsHty bean : returnList) {
			ReferenceCodes returnObj = ocdsupstRepository.getBillableFlag(bean.getSupStatus());
			if (returnObj.getParentCode() != null) {
				bean.setBillableFlag("Y");
				bean.setBillableFlagValue("Y");
			} else {
				bean.setBillableFlag("N");
				bean.setBillableFlagValue("N");
			}
		}
		return returnList;
	}

	@Override
	public List<OffSupervisionStsHty> supHistoryExecuteQuery(final OffSupervisionStsHty searchBean) {
		List<OffSupervisionStsHty> returnList = ocdsupstRepository.supHistoryExecuteQuery(searchBean);
		for (OffSupervisionStsHty bean : returnList) {
			ReferenceCodes returnObj = ocdsupstRepository.getBillableFlag(bean.getSupStatus());
			if (returnObj.getParentCode() != null) {
				bean.setBillableFlag("Y");
				bean.setBillableFlagValue("Y");
			} else {
				bean.setBillableFlag("N");
				bean.setBillableFlagValue("N");
			}
		}
		return returnList;
	}

	public FeeAccountProfiles getsupStartDate(final VHeaderBlock bean) {
		return ocdsupstRepository.getsupStartDate(bean.getOffenderBookId());
	}

	public Integer updateNonBillbleFeeAccounts(final OffSupervisionStsHty bean) {
		List<FeeAccountProfiles> feeUpdatelist = new ArrayList<>();
		Integer liReturn = 0;
		List<FeeAccountProfiles> feelist = ocdsupstRepository
				.getCurrentSupData(BigDecimal.valueOf((bean.getOffenderBookId())));
		for (FeeAccountProfiles feeBean : feelist) {
			List<CaseloadDeductionProfiles> returnData = ocdofaccRepository.getBackBill(feeBean.getFeeCode(),
					feeBean.getCaseloadId());
			if (!returnData.isEmpty() && "Y".equals(returnData.get(0).getNonBillableStatus())
					&& ("A".equals(feeBean.getFeeActStatus()) || "P".equals(feeBean.getFeeActStatus()))) {
				feeBean.setFeeActStatus("S");
				feeBean.setStatusEffectiveDate(bean.getStartDatetime());
				feeUpdatelist.add(feeBean);
			} else if (!returnData.isEmpty() && !"Y".equals(returnData.get(0).getNonBillableStatus())
					&& ("A".equals(feeBean.getFeeActStatus()) || "P".equals(feeBean.getFeeActStatus())
							|| "S".equals(feeBean.getFeeActStatus()))) {
				feeBean.setFeeActStatus("C");
				feeBean.setStatusEffectiveDate(bean.getStartDatetime());
				feeUpdatelist.add(feeBean);
			}
		}

		if (!feeUpdatelist.isEmpty()) {
			liReturn = ocdsupstRepository.updateFeeAccounts(feeUpdatelist);
		}
		return liReturn;
	}

	public Integer updateBillbleFeeAccounts(final OffSupervisionStsHty bean) {
		List<FeeAccountProfiles> feeUpdatelist = new ArrayList<>();
		Integer liReturn = 0;
		FeeAccountProfiles feeprBean = new FeeAccountProfiles();
		feeprBean.setOffenderBookId(Long.valueOf(bean.getOffenderBookId()));
		List<FeeAccountProfiles> feelist = ocdofaccService.offDedExecuteQuery(feeprBean);
		for (FeeAccountProfiles feeBean : feelist) {
			feeBean.setUserId(bean.getUserId());
			List<CaseloadDeductionProfiles> returnData = ocdofaccRepository.getBackBill(feeBean.getFeeCode(),
					feeBean.getCaseloadId());
			if (!returnData.isEmpty() && "Y".equals(returnData.get(0).getNonBillableStatus())
					&& "Y".equals(returnData.get(0).getFoAlAllOffenderFlag())
					&& ("S".equals(feeBean.getFeeActStatus()) || "C".equals(feeBean.getFeeActStatus()))) {
				if(bean.getCaseloadId().equals(feeBean.getCaseloadId())) {
					feeBean.setFeeActStatus("A");
					feeBean.setStatusEffectiveDate(bean.getStartDatetime());
					feeBean.setEffectiveDate(bean.getStartDatetime());
					feeUpdatelist.add(feeBean);
				}
//				Integer prepaidCount = ocdsupstRepository.feeOverrodeExists(feeBean.getFeeCode());
//				if (prepaidCount == 1 && returnData.get(0).getMaxTotalAmount() != null) {
//					feeBean.setFeeActStatus("P");
//				} else {
				
//				}
				
			} else if (!returnData.isEmpty() && "Y".equals(returnData.get(0).getFoAlAllOffenderFlag())
					&& "C".equals(feeBean.getFeeActStatus())) {
				if(bean.getCaseloadId().equals(feeBean.getCaseloadId())) {
				feeBean.setFeeActStatus("A");
				feeBean.setStatusEffectiveDate(bean.getStartDatetime());
				feeBean.setEffectiveDate(new Date());
				feeUpdatelist.add(feeBean);
				}
			}

		}
		if (!feeUpdatelist.isEmpty()) {
			liReturn = ocdsupstRepository.updateFeeAccounts(feeUpdatelist);
		}
		return liReturn;
	}

	@Override
	public Integer getIntakeRevCount(final VHeaderBlock bean) {
		return ocdsupstRepository.getIntakeRevCount(bean);
	}

	@Override
	public List<FeeAccountProfiles> getFeeAccountsData(Integer offenderBookId) {
		return ocdsupstRepository.getCurrentSupData(BigDecimal.valueOf(offenderBookId));
	}

	@Override
	public Integer autoCreateFeeAccounts(Integer offenderBookId, String caseloadId, Date startDate, String userName) {
		Integer liReturn = 0;
		List<FeeAccountProfiles> longSupvData=new ArrayList<FeeAccountProfiles>();
		FeeAccountProfiles longSupvObj=new FeeAccountProfiles();
		longSupvObj.setOffenderBookId(Long.valueOf(offenderBookId));
		String getAutoFeeCr = ocdsupstRepository.getProfileValue();
		if (!"Y".equals(getAutoFeeCr) || getAutoFeeCr == null) {
			return liReturn;
		}
		Integer supvRecCount = ocdsupstRepository.getSupvRecCount(offenderBookId,caseloadId);
		if (supvRecCount > 0) {
			return liReturn;
		}
		List<CaseloadDeductionProfiles> profileData = ocdsupstRepository.caseloadDedProfExecuteQuery(caseloadId);
		longSupvData = ocdofaccRepository.sysLongSupPflExecuteQuery(longSupvObj);
		if (!profileData.isEmpty()) {
			for (CaseloadDeductionProfiles profBean : profileData) {
				FeeAccountProfiles beanObj = new FeeAccountProfiles();
				CaseloadDedBeneficiaries dedBen = new CaseloadDedBeneficiaries();
				CaseloadDeductionDetails dedRec = new CaseloadDeductionDetails();
				List<FeeAccountProfiles> feeInsertlist = new ArrayList<>();
				BigDecimal offenderFeeId = ocdofaccRepository.offdedPreInsert();
				beanObj.setOffenderFeeId(offenderFeeId);
				beanObj.setCaseloadId(caseloadId);
				beanObj.setOffenderBookId(Long.valueOf(offenderBookId));
				beanObj.setFeeCode(profBean.getDeductionType());
				beanObj.setFeeActStatus("A");
				if (profBean.getMaxTotalAmount() == null) {
					beanObj.setAmount(BigDecimal.ZERO);
				} else {
					beanObj.setAmount(profBean.getMaxTotalAmount());
				}
				if (profBean.getDayOfMonth() != null) {
					beanObj.setDayOfMonth(Integer.valueOf(profBean.getDayOfMonth().toString()));
				}
				if(profBean.getFrequencyType() != null && "ONE".equals(profBean.getFrequencyType())) {
					beanObj.setExpiryDate(startDate);	
				}
				beanObj.setStartDate(startDate);
				beanObj.setOdp(ocdsupstRepository.getMaxOdp(offenderBookId, profBean.getDeductionType(),profBean.getCaseloadId()));
				beanObj.setStatusEffectiveDate(startDate);
				if("Y".equals(profBean.getNonBillableStatus())) {				
					beanObj.setEffectiveDate(startDate);
					if(!longSupvData.isEmpty() && longSupvData.get(0).getLongestSupvExpDate()!=null) {
						beanObj.setExpiryDate(longSupvData.get(0).getLongestSupvExpDate());
					}
				}
				/*
				 * if ("RECUR".equals(profBean.getFrequencyType()) &&
				 * "MONTHLY".equals(profBean.getFrequencyCode())) { if (profBean.getDayOfMonth()
				 * != null && profBean.getDayOfMonth() != 0) { feeInsertlist.add(beanObj); } }
				 * else { feeInsertlist.add(beanObj); }
				 */
				beanObj.setCreateUserId(userName);
				feeInsertlist.add(beanObj);
				dedBen.setCaseloadId(caseloadId);
				dedBen.setDeductionType(profBean.getDeductionType());
				dedRec.setCaseloadId(caseloadId);
				dedRec.setDeductionType(profBean.getDeductionType());
				List<CaseloadDedBeneficiaries> benfList = ocdofaccRepository.caseloadDedBenficExecuteQuery(dedBen);
				List<CaseloadDeductionDetails> dedRecList = ocdofaccRepository.caseloadDedDetExecuteQuery(dedRec);
				for (CaseloadDedBeneficiaries benfBean : benfList) {
					benfBean.setOffenderFeeId(offenderFeeId);
					benfBean.setCreateUserId(userName);
				}
				for (CaseloadDeductionDetails dedRecBean : dedRecList) {
					dedRecBean.setOffenderFeeId(offenderFeeId);
					////dedRecBean.setCreateUserId(userName);
				}
				if (!feeInsertlist.isEmpty()) {
					ocdofaccRepository.offdedInsertQuery(feeInsertlist);
					//Trigger call OFF_FEE_ACCOUNT_PROFILE_T1 and OFF_FEE_ACCOUNT_PROFILE_T2
					feeInsertlist.forEach(bo->{
						offfeeaccountprofilet1andt2service.offFeeAccountProfileT1AndT2(bo);						
					});
					ocdofaccRepository.cslddbenInsertQuery(benfList);
					
					ocdofaccRepository.csldddInsertQuery(dedRecList);
				}
			}
		}
		return liReturn;
	}

	public void generateAutoFeeAccBills(Integer offenderBookId, String userId, String moduleName) {
		/*
		 * String getAutoFeeCr = ocdsupstRepository.getProfileValue(); if
		 * (!"Y".equals(getAutoFeeCr) || getAutoFeeCr == null) { return; } if
		 * ("OCDSUPST".equals(moduleName)) { Integer feeBillCount =
		 * ocdsupstRepository.getFeeBillCount(offenderBookId); if (feeBillCount > 0) {
		 * return; } } FeeAccountProfiles feeprBean = new FeeAccountProfiles();
		 * feeprBean.setOffenderBookId(Long.valueOf(offenderBookId));
		 * List<FeeAccountProfiles> feeaccList =
		 * ocdofaccService.offDedExecuteQuery(feeprBean); // List<FeeAccountProfiles>
		 * feeaccList =
		 * ocdsupstRepository.getCurrentSupData(BigDecimal.valueOf(offenderBookId)); for
		 * (FeeAccountProfiles bean : feeaccList) { if
		 * (!"S".equals(bean.getFeeActStatus()) && !"C".equals(bean.getFeeActStatus()))
		 * { OffFeeBills ofFeeBillBean = new OffFeeBills(); OffFeeBillTransactions
		 * ofFeeBillTrnBean = new OffFeeBillTransactions(); Integer staffId =
		 * ocdsupstRepository.getstaffId(userId); String billId =
		 * getfeeBillCaslSeq(bean.getCaseloadId());
		 * 
		 * // insert data into off_fee_bills & off_fee_bill_trx
		 * ofFeeBillBean.setBillDate(eliteDateService.getDBTime());
		 * ofFeeBillBean.setBillId(billId);
		 * ofFeeBillBean.setOffenderFeeId(bean.getOffenderFeeId());
		 * ofFeeBillBean.setBillGenerateDatetime(eliteDateService.getDBTime());
		 * ofFeeBillBean.setBillGenerateStaffId(staffId);
		 * ofFeeBillBean.setBillGenerateAmount(bean.getAmount());
		 * ofFeeBillBean.setBillGenerateStatus("AR");
		 * ofFeeBillBean.setBillGenerateUser(userId);
		 * ofFeeBillBean.setStatementGeneratedFlag("N");
		 * 
		 * //// // set data into OffFeeBillTransactions table
		 * ofFeeBillTrnBean.setBillId(billId);
		 * ofFeeBillTrnBean.setBillTxnNo(ocdsupstRepository.getBillTranId(billId));
		 * ofFeeBillTrnBean.setBillTxnDatetime(eliteDateService.getDBTime());
		 * ofFeeBillTrnBean.setBillTxnUser(userId);
		 * ofFeeBillTrnBean.setBillStatus("AR");
		 * ofFeeBillTrnBean.setTxnStatementGeneratedFlag("N");
		 * ofFeeBillTrnBean.setBillTxnType("CBILL");
		 * ofFeeBillTrnBean.setBillTxnAmount(bean.getAmount());
		 * ofFeeBillTrnBean.setBillTxnStaffId(staffId);
		 * ofFeeBillTrnBean.setBillTxnUser(userId); //// // // set data into
		 * OFF_BILLING_STATEMENTS table //// // offBillingStatements stmtBean = new
		 * offBillingStatements(); //// //
		 * stmtBean.setOffenderBookId(Integer.valueOf(bean.getOffenderBookId().toString(
		 * ))); //// // stmtBean.setCaseloadId(bean.getCaseloadId()); //// //
		 * stmtBean.setStatementGenerateStaffId(staffId); //// //
		 * stmtBean.setStatementGenerateDatetime(eliteDateService.getDBTime()); //// //
		 * stmtBean.setBillingCycleStartDate(eliteDateService.getDBTime()); //// //
		 * stmtBean.setBillingCycleEndDate(eliteDateService.getDBTime()); //// //
		 * stmtBean.setStatementGenerateUser(bean.getUserId()); //// //
		 * stmtBean.setBeginingBalanceAmount(); //// //
		 * stmtBean.setPaymentsCreditsAmount(); //// //
		 * stmtBean.setEndingBalanceAmount(); //// // stmtBean.setBillingsAmount(); ////
		 * // stmtBean.setCasePlanId(); if ("ONE".equals(bean.getFrequencyType())) { if
		 * (bean.getExpiryDate() != null &&
		 * bean.getExpiryDate().compareTo(eliteDateService.getDBTime()) <= 0) {
		 * ofFeeBillBean.setBillDate(bean.getExpiryDate()); } //// // insert in
		 * off_fee_bills ocdsupstRepository.offFeeBillsInsertQuery(ofFeeBillBean); ////
		 * // insert in OffFeeBillTransactions
		 * ocdsupstRepository.offFeeBillTransInsertQuery(ofFeeBillTrnBean); //// //
		 * update fee account status bean.setFeeActStatus("C");
		 * ocdsupstRepository.updFeeAccounts(bean); } else if
		 * ("RECUR".equals(bean.getFrequencyType())) { if
		 * ("MONTHLY".equals(bean.getFrequencyCode())) { //// if
		 * (ofFeeBillTrnBean.getBillTxnDatetime().compareTo(eliteDateService.getDBTime()
		 * ) == 0) { BigDecimal overrideAmount =
		 * ocdsupstRepository.feeOverrodeExists(bean.getOffenderFeeId()); if
		 * (overrideAmount != null) {
		 * ofFeeBillBean.setBillGenerateAmount(overrideAmount);
		 * ofFeeBillTrnBean.setBillTxnAmount(overrideAmount); } //// } } else if
		 * ("DAILY".equals(bean.getCode())) { if (bean.getExpiryDate() != null &&
		 * bean.getExpiryDate().compareTo(eliteDateService.getDBTime()) != 0) { return;
		 * } ofFeeBillBean.setBillDate(eliteDateService.getDBTime()); }
		 * ofFeeBillBean.setBillDate(eliteDateService.getDBTime()); //// // update fee
		 * account status List<CaseloadDeductionProfiles> returnData =
		 * ocdofaccRepository.getBackBill(bean.getFeeCode(), bean.getCaseloadId()); if
		 * ("P".equals(bean.getFeeActStatus()) && bean.getExpiryDate() != null &&
		 * bean.getExpiryDate().compareTo(eliteDateService.getDBTime()) <= 0) {
		 * prepaidAccFlow(bean); } else if ("A".equals(bean.getFeeActStatus()) &&
		 * bean.getExpiryDate() != null &&
		 * bean.getExpiryDate().compareTo(eliteDateService.getDBTime()) <= 0) { if
		 * (!returnData.isEmpty() &&
		 * "Y".equals(returnData.get(0).getNonBillableStatus())) {
		 * bean.setFeeActStatus("S"); } else { bean.setFeeActStatus("C"); }
		 * ocdsupstRepository.updFeeAccounts(bean); }
		 * 
		 * } // // insert in off_fee_bills
		 * ocdsupstRepository.offFeeBillsInsertQuery(ofFeeBillBean); // // insert in
		 * OffFeeBillTransactions
		 * ocdsupstRepository.offFeeBillTransInsertQuery(ofFeeBillTrnBean); } }
		 */}

	public String getfeeBillCaslSeq(final String caseloadId) {
		Integer caseloadSeq = ocdsupstRepository.getFeeBillSeq("SEQUENCE_FEE_BILL_" + caseloadId);
		if (caseloadSeq != 0) {
			String num = caseloadSeq.toString();
			while (num.length() < 10) {
				num = "0" + num;
			}
			return caseloadId + num;
		}
		return null;
	}

	public void prepaidAccFlow(final FeeAccountProfiles feeBean) {
		List<CaseloadDeductionProfiles> returnData = ocdofaccRepository.getBackBill(feeBean.getFeeCode(),
				feeBean.getCaseloadId());
		if (!returnData.isEmpty() && "Y".equals(returnData.get(0).getNonBillableStatus())
				&& "Y".equals(returnData.get(0).getFoAlAllOffenderFlag())
				&& ("S".equals(feeBean.getFeeActStatus()) || "C".equals(feeBean.getFeeActStatus()))) {
			Integer prepaidCount = ocdsupstRepository.feeOverrodeExists(feeBean.getFeeCode());
			if (prepaidCount == 1 && returnData.get(0).getMaxTotalAmount() != null) {
				feeBean.setFeeActStatus("P");
			} else {

				feeBean.setFeeActStatus("A");
			}
			feeBean.setEffectiveDate(eliteDateService.getDBTime());
		}
		ocdsupstRepository.updFeeAccounts(feeBean);
	}

	public void createTrustAccount(final OffSupervisionStsHty bean) {
		List<OffenderTransactions> offTxnInsertList = new ArrayList<>();
		OffenderTransactionsCommitBean commitBean = new OffenderTransactionsCommitBean();
		OffenderTransactions offtxnModel = new OffenderTransactions();
		//String trustAccStatus = ocdsupstRepository.checkAccountSatus(bean);
		Map<String, Object> map = trustService.chkAccountStatus(bean.getCaseloadId(), bean.getOffenderId()!=null?BigDecimal.valueOf( bean.getOffenderId()):null );
		String trustAccStatus = map!=null && map.get("P_OPEN_AN_ACCOUNT")!=null ?(String) map.get("P_OPEN_AN_ACCOUNT"):null;
		if ("X".equals(trustAccStatus)) {
			offtxnModel.setOffenderId(bean.getOffenderId());
			offtxnModel.setOffenderBookId(bean.getOffenderBookId()!=null?Long.valueOf(bean.getOffenderBookId()):null);
			offtxnModel.setCaseloadId(bean.getCaseloadId());
			offtxnModel.setTxnPostingType("CR");
			offtxnModel.setSlipPrintedFlag("N");
			offtxnModel.setReceiptPrintedFlag("Y");
			offtxnModel.setDeductionFlag("Y");
			offtxnModel.setTxnAdjustedFlag("Y");
			offtxnModel.setHoldClearFlag("Y");
			offtxnModel.setAdjustTxnEntryId(99);
			offtxnModel.setTxnEntryDate(eliteDateService.getDBTime());
			offtxnModel.setModifyDate(eliteDateService.getDBTime());
			offtxnModel.setCreateDatetime(eliteDateService.getDBTime());
			offtxnModel.setCreateUserId(bean.getCreateUserId());
			offtxnModel.setModifyUserId(bean.getUserId());
			offtxnModel.setTxnEntryAmount(0.00);
			offTxnInsertList.add(offtxnModel);
			commitBean.setInsertList(offTxnInsertList);
			otdcntacService.offTxnCommit(commitBean);
		}
	}
}