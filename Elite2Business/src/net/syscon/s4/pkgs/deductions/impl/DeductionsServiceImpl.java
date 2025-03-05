package net.syscon.s4.pkgs.deductions.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmounts;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.SuspendDeductions;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.pkgs.deductions.DeductionsRepository;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.trust.TrustService;
import net.syscon.s4.triggers.OffenderDeductionsHty;
import net.syscon.s4.triggers.OffenderDeductionsT2Service;
import net.syscon.s4.triggers.OffenderDeductionsThtyService;
import net.syscon.s4.triggers.OffenderDeductionsTjnService;

@Service
public class DeductionsServiceImpl implements DeductionsService {

	@Autowired
	private DeductionsRepository deductionsRepository;
	@Autowired
	private TrustService trustService;
	@Autowired
	private OffenderDeductionsTjnService offenderDeductionsTjnService;
	@Autowired
	private OffenderDeductionsT2Service offenderDeductionsT2Service;
	@Autowired
	private OffenderDeductionsThtyService offenderDeductionsThtyService;

	final private static String Y = "Y";
	final private static String N = "N";
	final private static String A = "A";

	private static Logger logger = LogManager.getLogger(DeductionsServiceImpl.class.getName());

	@Override
	@Transactional
	public String chkOffenderDeductions(final String pCsldId, final Long pOffId, final String pTransType,
			final Long pShadowId) {
		String csldType;
		String pDedFlag = null;
		csldType = trustService.getCaseloadType(pCsldId);
		final List<OffenderDeductions> offDed = deductionsRepository.chkDedCur(pOffId, pShadowId, csldType);
		for (final OffenderDeductions offDedtion : offDed) {
			pDedFlag = deductionsRepository.chkReceiptType(offDedtion.getOffenderDeductionId(), pTransType);
			if (pDedFlag.equals(Y)) {
				break;
			}
		}
		if (pDedFlag != null) {
			return pDedFlag;
		} else {
			return "";
		}
	}

	@Override
	public void updateIndigentDate(final Integer accountCode, final Long offenderId, final String caseloadId,
			final String userName) {
		Double minBal = null;
		Double subBal = null;
		Date indDate = null;
		List<OffenderSubAccounts> minSubIndList = null;

		minSubIndList = deductionsRepository.getMinSubIndValues(accountCode, offenderId, caseloadId);
		for (final OffenderSubAccounts offSubAcct : minSubIndList) {
			subBal = offSubAcct.getBalance();
			if(offSubAcct.getMinimumBalance()!=null) {
			minBal = offSubAcct.getMinimumBalance().doubleValue();
			}
			if(offSubAcct.getIndDate()!=null) {
			indDate = offSubAcct.getIndDate();
			}
		}

		if (minBal == null) {
			try {
			minBal = deductionsRepository.getMinBalance(accountCode, offenderId, caseloadId);
			}catch (Exception e) {
				logger.error("getMinBalance"+e);
			}
			if (minBal == null) {
				try {
				minBal = deductionsRepository.getMinBalanceOne(accountCode, caseloadId);
				}catch (Exception e) {
					logger.error("getMinBalanceOne"+e);
				}
			}
			if (minBal == null) {
				minBal = 0.0;
			}
		}
		if (indDate != null && (minBal >= 0) && (subBal > minBal)) {
			deductionsRepository.updateOffenderSubAccountsInd(accountCode, offenderId, caseloadId, userName);
		} else if ((subBal <= minBal) && indDate == null) {
			deductionsRepository.updateOffenderSubAccountsIndOne(accountCode, offenderId, caseloadId, userName);
		}
	}

	@Override
	@Transactional
	public Integer createConditionDeductions(final OffenderSentConditions offSentCon, final String userName) {
		List<CaseloadDeductionProfiles> caloDedProList = new ArrayList<CaseloadDeductionProfiles>();
		List<CaseloadDeductionDetails> clDedDtai = new ArrayList<CaseloadDeductionDetails>();

		String csldType = null;
		final BigDecimal corporateId = null;
		final Date vSysdate = new Date();
		BigDecimal lvGroupId = null;
		BigDecimal vOdp = null;
		Long vDedId = null;
		Long uniqueUi2 = null;
		String existFlag = N;
		Integer count = 0;

		try {
			// TRUST.get_caseload_type(p_csld_id, csld_type); 8569
			csldType = trustService.getCaseloadType(offSentCon.getCaseloadId());

			// c_cond_ded Cursor 8604
			caloDedProList = deductionsRepository.cCondDedCursor(offSentCon.getCaseloadId(),
					offSentCon.getCommConditionType(), offSentCon.getCommConditionCode(), offSentCon.getCategoryType(),
					corporateId, csldType);
			for (final CaseloadDeductionProfiles caloDedPro : caloDedProList) {
				// uniqueUi2 Select query 8606
				uniqueUi2 = deductionsRepository.uniUi2Select(caloDedPro.getCaseloadId(), caloDedPro.getDeductionType(),
						BigDecimal.valueOf(offSentCon.getRootOffenderId()), offSentCon.getCaseInfoNumber());
				if (uniqueUi2 == 0) {
					// v_ded_id 8616
					vDedId = deductionsRepository.vDedIdSelect();
					vOdp = deductionsRepository.vOdpSelect(caloDedPro.getDeductionType(),
							BigDecimal.valueOf(offSentCon.getRootOffenderId()));
					lvGroupId = deductionsRepository.lvGroupIdSelect(caloDedPro.getDeductionType());

					final OffenderDeductions offDed = new OffenderDeductions();
					offDed.setOffenderDeductionId(vDedId);
					offDed.setOffenderId(offSentCon.getRootOffenderId());
					offDed.setDeductionType(caloDedPro.getDeductionType());
					offDed.setInformationNumber(offSentCon.getCaseInfoNumber());
					offDed.setCaseloadId(caloDedPro.getCaseloadId());
					offDed.setDeductionPriority(vOdp);
					offDed.setDeductionStatus(A);
					offDed.setEffectiveDate(caloDedPro.getEffectiveDate());
					offDed.setMaxMonthlyAmount(caloDedPro.getMaxMonthlyAmount());
					offDed.setMaxTotalAmount(offSentCon.getFinancialTotalAmount());
					offDed.setMaxRecursiveAmount(caloDedPro.getMaxRecursiveAmount());
					offDed.setCreditLimit(caloDedPro.getCoLimitAmount());
					offDed.setDeductionAmount(BigDecimal.valueOf(0.0));
					offDed.setFifoFlag(caloDedPro.getFifoFlag());
					offDed.setPayeePersonId(caloDedPro.getPayeePersonId());
					offDed.setPayeeCorporateId(caloDedPro.getPayeeCorporateId());
					offDed.setGroupId(lvGroupId);
					// 8640 insert offender_deductions
					offDed.setCreateUserId(userName);
					deductionsRepository.insertOffenderDeductions(offDed);
					//Trigger OFFENDER_DEDUCTIONS_TJN
					//offenderDeductionsTjnService.offenderDeductionsTjn(offDed, null, "INSERT");
					//Trigger OFFENDER_DEDUCTIONS_T2
					offenderDeductionsT2Service.offenderDeductionsT2Trigger(offDed, null,  "INSERT");
					OffenderDeductionsHty obj=new OffenderDeductionsHty();
					BeanUtils.copyProperties(offDed, obj);
					//Trigger OFFENDER_DEDUCTIONS_THTY--
					offenderDeductionsThtyService.OffenderDeductionsThtyTrigger(obj, "INSERT");
				}
			}
			// INSERT INTO offender_beneficiaries 8720
			deductionsRepository.insertOffenderBeneficiaries(BigDecimal.valueOf(offSentCon.getRootOffenderId()),
					offSentCon.getFinancialTotalAmount(), offSentCon.getCaseloadId(), offSentCon.getCommConditionType(),
					offSentCon.getCommConditionCode(), offSentCon.getCategoryType(), vSysdate, userName);

			// c_receipt cursor 8848
			clDedDtai = deductionsRepository.cReceiptCursor(BigDecimal.valueOf(offSentCon.getRootOffenderId()),
					offSentCon.getCaseloadId(), csldType);
			for (final CaseloadDeductionDetails clDed : clDedDtai) {
				count = deductionsRepository.existFlagSelect(clDed.getOffenderDeductionId(), clDed.getReceiptTxnType());
				if (count > 0) {
					existFlag = Y;
				} else {
					existFlag = N;
				}
				if (N.equals(existFlag)) {
					// INSERT INTO offender_deduction_receipts 8877
					deductionsRepository.insertOffenderDeductionReceipts(clDed.getOffenderDeductionId(),
							clDed.getReceiptTxnType(), clDed.getPercentage(), clDed.getFlatRate(), userName);
				}
			}
		} catch (Exception e) {
			logger.error("createConditionDeductions", e);
			return 0;
		}
		return 1;
	}

	@Override
	public String getAcAndSetIndDate(final Long offenderId, final String caseloadId, final String userName) {
		String csldType = null;
		Integer lvTrustAccCd = null;
		String lvIndigencyLevel = null;
		String lvIndDayLmt = null;
		Date lvIndigentDate = null;
		Double lvSubAcctBalance = null;
		Integer lvCsldIdExists = null;
		List<OffenderSubAccounts> offSubAccList = null;
		final Date pAdmissionDate = null;

		// 9488
		csldType = trustService.getCaseloadType(caseloadId);
		lvCsldIdExists = deductionsRepository.chkCaseloadC(caseloadId);

		if (lvCsldIdExists > 0) {
			// chk_ind_ac_c 9499
			offSubAccList = deductionsRepository.chkIndAcC(offenderId, caseloadId, csldType);
			for (final OffenderSubAccounts offSubAccs : offSubAccList) {
				// update_indigent_date 9501
				updateIndigentDate(offSubAccs.getTrustAccountCode().intValue(), offenderId, caseloadId, userName);
			}
		} else {
			// system_profile_c 9508
			final List<SystemProfiles> lvIndList = deductionsRepository.systemProfileC();
			for (SystemProfiles systemProfiles : lvIndList) {
				lvIndigencyLevel = systemProfiles.getProfileValue();
				lvIndDayLmt = systemProfiles.getProfileValue2();
			}
			// get_trust_account_code_c 9513
			lvTrustAccCd = deductionsRepository.getTrustAccountCodeC();
			// sum_off_sub_act_bal_c 9519
			lvSubAcctBalance = deductionsRepository.sumOffSubActBalC(lvTrustAccCd, offenderId);

			if (lvSubAcctBalance!=null && lvIndigencyLevel!=null && lvSubAcctBalance <= Double.parseDouble(lvIndigencyLevel)) {
				if (pAdmissionDate == null) {
					// max_ind_date_c 9540
					lvIndigentDate = deductionsRepository.maxIndDateC(lvTrustAccCd, offenderId);
					if (lvIndigentDate == null) {
						lvIndigentDate = new Date();
					}
				}
				// update offender_sub_accounts 9553
				deductionsRepository.updateOffenderSubAccounts(lvIndigentDate, lvIndDayLmt, lvTrustAccCd, offenderId,
						userName);
			} else {
				deductionsRepository.updateOffenderSubAccountsOne(lvTrustAccCd, offenderId, userName);
			}
		}
		return null;
	}

	@Override
	public Date createDefaultDeductions(final String pCsldId, final Integer pOffId, final String userName) {

		String csldType = null;
		final BigDecimal corporateId = BigDecimal.ZERO;
		final Date vSysdate = new Date();
		BigDecimal lvOdp = BigDecimal.ZERO;
		String existFlag = N;
		Integer exFlgCount = 0;
		String cfExistFlag = N;
		Integer cfExFlgCount = 0;

		List<CaseloadDeductionProfiles> csldDedList = new ArrayList<CaseloadDeductionProfiles>();
		List<CaseloadDeductionDetails> csldDedDetisList = new ArrayList<CaseloadDeductionDetails>();
		List<OffenderDeductions> offDedList = new ArrayList<OffenderDeductions>();
		List<TieredTransactionFeeAmounts> tidTranAmts = new ArrayList<TieredTransactionFeeAmounts>();
		try {
			// Getting CaseLoadType
			csldType = trustService.getCaseloadType(pCsldId);

			// default_deduction Cursor 8027
			csldDedList = deductionsRepository.defaultDeductionCursor(pCsldId, pOffId, csldType, corporateId);
			for (final CaseloadDeductionProfiles eachDed : csldDedList) {
				// max_odp cursor 8030
				lvOdp = deductionsRepository.maxOdpCursor(eachDed.getDeductionType(), pOffId);
				lvOdp = lvOdp.add(BigDecimal.ONE);

				// offender_deductions Insert 8035
				eachDed.setpOffId(pOffId);
				eachDed.setLvOdp(lvOdp);
				eachDed.setvSysdat(vSysdate);
				deductionsRepository.offenderDeductionsInser(eachDed, userName);
				
				OffenderDeductionsHty obj=new OffenderDeductionsHty();
				OffenderDeductions newRec=new OffenderDeductions();
				eachDed.setCreateUserId(userName);
				BeanUtils.copyProperties(eachDed, obj);
				BeanUtils.copyProperties(eachDed, newRec);
				//Trigger OFFENDER_DEDUCTIONS_TJN
				//offenderDeductionsTjnService.offenderDeductionsTjn(newRec, null, "INSERT");
				//Trigger OFFENDER_DEDUCTIONS_T2
				offenderDeductionsT2Service.offenderDeductionsT2Trigger(newRec, null,  "INSERT");			
				//Trigger OFFENDER_DEDUCTIONS_THTY--
				offenderDeductionsThtyService.OffenderDeductionsThtyTrigger(obj, "INSERT");
			}

			// offender_beneficiaries Insert 8107
			deductionsRepository.offenderBeneficiariesInsert(pCsldId, pOffId, vSysdate, userName);

			// offender_beneficiaries Insert 8178
			deductionsRepository.offenderBeneficiariesInsertOne(pCsldId, pOffId, vSysdate, userName);

			// c_receipt cursor 8292
			csldDedDetisList = deductionsRepository.cReceiptCursor(pCsldId, pOffId, csldType);
			for (final CaseloadDeductionDetails csldDedDtais : csldDedDetisList) {
				// offender_deduction_receipts select 8295
				exFlgCount = deductionsRepository.offenderDeductionReceiptsSelectOperation(
						csldDedDtais.getOffenderDeductionId(), csldDedDtais.getReceiptTxnType());
				if (exFlgCount > 0) {
					existFlag = Y;
				}
				if (N.equals(existFlag)) {
					// offender_deduction_receipts Insert 8322
					deductionsRepository.offenderDeductionReceiptsInsert(csldDedDtais, userName);
				}
			}
			// c_col_fee Cursor 8369
			offDedList = deductionsRepository.cColFeeCursor(pOffId, pCsldId, csldType);
			for (final OffenderDeductions offDeds : offDedList) {
				// offender_txn_fee_details select 8400
				cfExFlgCount = deductionsRepository.offenderTxnFeeDetailsSelect(offDeds.getOffenderDeductionId(),
						offDeds.getReceiptDeductionType());
				if (cfExFlgCount > 0) {
					cfExistFlag = Y;
				}
				if (N.equals(cfExistFlag)) {
					// offender_txn_fee_details insert 8427
					deductionsRepository.offenderTxnFeeDetailsInsert(offDeds.getOffenderDeductionId(),
							offDeds.getReceiptDeductionType(), userName);
				}
			}
			// c_fee_amt cursor 8477
			tidTranAmts = deductionsRepository.cFeeAmtCursor(pOffId, pCsldId);
			for (final TieredTransactionFeeAmounts tieredTranFeeAms : tidTranAmts) {
				// offender_tier_txn_fee_amounts insert 8480
				tieredTranFeeAms.setCreateUserId(userName);
				deductionsRepository.offenderTierTxnFeeAmountsInsert(tieredTranFeeAms);
			}
		} catch (Exception e) {
			logger.error("createDefaultDeductions", e);
		}
		return vSysdate;
	}

	@Override
	public Integer updateDeductionStatus(final SuspendDeductions obj, final String userName) {
		return deductionsRepository.updateDeductionStatus(obj, userName);
	}

	@Override
	public Integer suspendOffDeductions(final String userName) {
		try {
			final List<SuspendDeductions> retList = deductionsRepository.getSuspendDeductionsDetails();
			for (final SuspendDeductions obj : retList) {
				updateDeductionStatus(obj, userName);
			}
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

}
