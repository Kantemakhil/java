package net.syscon.s4.pkgs.otddisbu.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OtddisbuProcessTransactionsBean;
import net.syscon.s4.im.beans.BankChequeData;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.otddisbu.OtddisbuPkgRepository;
import net.syscon.s4.pkgs.otddisbu.OtddisbuPkgService;
import net.syscon.s4.pkgs.trust.TrustService;

@Service
public class OtddisbuPkgServiceImpl implements OtddisbuPkgService {

	@Autowired
	private OtddisbuPkgRepository otddisbuRepository;

	@Autowired
	private TrustService trustService;
	@Autowired
	private DeductionsService deductionsService;

	private static final String OTDDISBU = "OTDDISBU";
	private static final String M = "M";
	private static final String Y = "Y";
	private static final String X = "X";
	private static final String D = "D";
	private static final String O = "O";

	@Override
	@Transactional
	public Map<String, Object> processTransactions(final OtddisbuProcessTransactionsBean proTxn,
			final String userName) {
		final Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> chkAccStsMap = null;
		Map<String, Object> subActTypeMap = null;
		Map<String, Object> chkOverdrnMap = null;
		final OffenderTransactions offTrans = new OffenderTransactions();
		String lFlag = null;
		String lSubAccountType = null;
		String lTxnPostingType = null;
		Long lOffenderBookId = null;
		String lCheckInd = null;
		// final String lReceiptNumber = proTxn.getpReceiptNum();
		final Date cToday = new Date();
		String pErrorMessage = proTxn.getpErrorMessage();
		Integer lGlSeqNo = 0;
		Long entSeq = null;

		// Begin 104
		pErrorMessage = null;
		if (proTxn.getpTxnEntryAmount().intValue() <= 0) {
			pErrorMessage = "Error: Txn Amount must be greater than zero.";
		}
		checkErrorMessage(pErrorMessage); // 118

		// TRUST.chk_account_status 128
		chkAccStsMap = trustService.chkAccountStatus(proTxn.getpCaseloadId(),
				BigDecimal.valueOf(proTxn.getpOffenderId()));
		if (chkAccStsMap != null) {
			lFlag = (String) chkAccStsMap.get("P_OPEN_AN_ACCOUNT");
		}
		// 130 to 144
		if (Y.equals(lFlag)) {
			pErrorMessage = "Error: Offender has closed account.";
		} else if (X.equals(lFlag)) {
			pErrorMessage = "Error: Offender does not have an account.";
		} else if (M.equals(lFlag)) {
			pErrorMessage = "Error: Offender has multiple accounts.";
		}
		checkErrorMessage(pErrorMessage); // 149

		// get_sub_act_type 159
		proTxn.setpModuleName("OTDDISBU");
		if (O.equals(proTxn.getpTxnUsage())){
			proTxn.setpTxnUsage(D);
		}
		subActTypeMap = trustService.getSubActType(proTxn);
		if (subActTypeMap != null) {
			lTxnPostingType = (String) subActTypeMap.get("P_TXN_POST_TYPE");
			lSubAccountType = (String) subActTypeMap.get("P_SUB_ACT_TYPE");
		}

		if (lTxnPostingType == null || lSubAccountType == null) {
			pErrorMessage = "Error: Sub account/Posting type not found.";
		}
		checkErrorMessage(pErrorMessage); // 180

		// l_offender_book_cur 230
		lOffenderBookId = otddisbuRepository.lOffenderBookCur(proTxn.getpOffenderId(), proTxn.getpCaseloadId());

		if (lOffenderBookId == null) {
			// l_offender_inact_cur 237
			lOffenderBookId = otddisbuRepository.lOffenderInactCur(proTxn.getpOffenderId(), proTxn.getpCaseloadId());
		}
		if (lOffenderBookId == null) {
			pErrorMessage = "Error: No active booking found for offender.";
		}
		checkErrorMessage(pErrorMessage); // 256

		// TRUST.chk_overdrawn 270
		// chk_overdrawn procedure pending
	//	 chkOverdrnMap = chkOverdrawnService.chkOverdrawn(proTxn.getpCaseloadId(),
//		 BigDecimal.valueOf(proTxn.getpOffenderId()) ,
//		 lSubAccountType, proTxn.getpTxnEntryAmount(), proTxn.getpTxnType(),
//		 proTxn.getpTxnEntrySeq(), lCheckInd,
//		 "OTDDISBU", proTxn.getpTxnId(), BigDecimal.valueOf(lOffenderBookId),
//		 proTxn.getpTxnFee().intValue(), null);
		
		chkOverdrnMap=	trustService.chkOverdrawn(proTxn.getpCaseloadId(),
				 BigDecimal.valueOf(proTxn.getpOffenderId()) ,
				 lSubAccountType, proTxn.getpTxnEntryAmount(), proTxn.getpTxnType(),
				 proTxn.getpTxnEntrySeq().longValue(), lCheckInd,
				 "OTDDISBU",  BigDecimal.valueOf(proTxn.getpTxnId()), BigDecimal.valueOf(lOffenderBookId),
				 proTxn.getpTxnFee()==null?null:proTxn.getpTxnFee().intValue(), null,proTxn.getCreateUserId());

		if (chkOverdrnMap != null) {
			//proTxn.setpTxnEntrySeq((Integer) chkOverdrnMap.get("SEQ_NO"));
			Long seq = (Long) chkOverdrnMap.get("SEQ_NO");
			proTxn.setpTxnEntrySeq(seq.intValue());
			lCheckInd = (String) chkOverdrnMap.get("CHECK_IND");
		}
		checkErrorMessage(pErrorMessage);// 290 && 335

		// Insert record into Offender_Transactions table 342
		proTxn.setOffenderBookId(lOffenderBookId);
		proTxn.setSubActType(lSubAccountType);
		proTxn.setTxnPostType(lTxnPostingType);
		proTxn.setCreateUserId(userName);
		otddisbuRepository.insertOffenderTransactions(proTxn);

		checkErrorMessage(pErrorMessage);// 408

		// Update offender balance 418
		offTrans.setCaseloadId(proTxn.getpCaseloadId());
		offTrans.setOffenderId(proTxn.getpOffenderId());
		offTrans.setTxnPostingType(proTxn.getTxnPostType());
		offTrans.setTxnEntryDate(cToday);
		offTrans.setTxnId(proTxn.getpTxnId());
		offTrans.setTxnType(proTxn.getpTxnType());
		offTrans.setTxnEntryAmount(proTxn.getpTxnEntryAmount().doubleValue());
		offTrans.setSubAccountType(proTxn.getSubActType());
		offTrans.setCaseloadType(proTxn.getCaseLoadType());
		offTrans.setTxnEntrySeq(proTxn.getpTxnEntrySeq());
		offTrans.setOffenderBookId(proTxn.getOffenderBookId());
		offTrans.setTxnUsage(proTxn.getpTxnUsage());
		offTrans.setCreateUserId(proTxn.getCreateUserId());
	
		trustService.updateOffenderBalance(offTrans, userName);

		checkErrorMessage(pErrorMessage);// 435

		// Insert gl trans 442
		lGlSeqNo = trustService.processGlTransNew(proTxn.getpCaseloadId(), proTxn.getpTxnType(), null,
				proTxn.getpTxnEntryAmount().doubleValue(), proTxn.getpTxnId(), cToday, proTxn.getpTxnDesc(),
				proTxn.getpTxnEntrySeq(), OTDDISBU, proTxn.getpOffenderId().intValue(), proTxn.getOffenderBookId(),
				proTxn.getSubActType(), null, proTxn.getpPayeePersonId(), proTxn.getpPayeeCorpId(),
				proTxn.getpPayeeNameText(), lGlSeqNo, null,offTrans.getCreateUserId());

		checkErrorMessage(pErrorMessage);// 473

		// TRUST.process_transaction_fee waiting for commit
		// entSeq = processTransactionFeeService.processTransactionFee();
	 entSeq=  trustService.processTransactionFee(offTrans, "OTDDISBU");
		proTxn.setpTxnEntrySeq(entSeq.intValue());
		checkErrorMessage(pErrorMessage);// 501

		// deductions.get_ac_and_set_ind_date 516
		deductionsService.getAcAndSetIndDate(proTxn.getpOffenderId(), proTxn.getpCaseloadId(), userName);
		checkErrorMessage(pErrorMessage);// 526

		// Create beneficiary cheque 533
		if ("Y".equals(proTxn.getpChequeProdFlag())) {
			final Integer lFlagCount = otddisbuRepository.lChequeExistsCur(proTxn.getpTxnId());
			if (lFlagCount > 0) {
				// bank_cheque_data 549
				otddisbuRepository.updateBankChequeData(proTxn.getpTxnId(), proTxn.getpTxnEntryAmount(), userName);
			} else {
				// insert_into_cheque_data 554
				final BankChequeData bankCheqData = new BankChequeData();
				bankCheqData.setCaseloadId(proTxn.getpCaseloadId());
				bankCheqData.setTxnId(proTxn.getpTxnId().longValue());
				bankCheqData.setTxnEntryAmount(proTxn.getpTxnEntryAmount());
				bankCheqData.setChequeFlag(O);
				bankCheqData.setStartTxnId(BigDecimal.valueOf(proTxn.getpTxnId()));
				bankCheqData.setEndTxnId(BigDecimal.valueOf(proTxn.getpTxnId()));
				bankCheqData.setPayeePersonId(proTxn.getpPayeePersonId()!=null?BigDecimal.valueOf(proTxn.getpPayeePersonId()):null);
				bankCheqData.setPayeeCorporateId(proTxn.getpPayeeCorpId()!=null?BigDecimal.valueOf(proTxn.getpPayeeCorpId()):null);
				bankCheqData.setPayeeNameText(proTxn.getpPayeeNameText());
				bankCheqData.setOffenderId(BigDecimal.valueOf(proTxn.getpOffenderId()));
				bankCheqData.setSingleEntryFlag("1");
				bankCheqData.setModuleName("OTDDISBU");
				bankCheqData.setTxnType(proTxn.getpTxnType());
				bankCheqData.setBankAccountCode(null);
				bankCheqData.setCreateUserId(proTxn.getCreateUserId());

				trustService.insertIntoChequeData(bankCheqData,userName);
			}
		}

		returnMap.put("P_TXN_ENTRY_SEQ", proTxn.getpTxnEntrySeq());
		returnMap.put("P_ERROR_MESSAGE", pErrorMessage);
		return returnMap;
	}

	private void checkErrorMessage(final String pErrorMessage) {
		if (pErrorMessage != null) {
			throw new IllegalArgumentException();
		}
	}
}
