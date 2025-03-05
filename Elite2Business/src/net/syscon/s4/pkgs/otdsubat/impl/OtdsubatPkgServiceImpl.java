package net.syscon.s4.pkgs.otdsubat.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.im.beans.BankChequeData;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.otdsubat.OtdsubatPkgRepository;
import net.syscon.s4.pkgs.otdsubat.OtdsubatPkgService;
import net.syscon.s4.pkgs.trust.TrustService;

@Service
public class OtdsubatPkgServiceImpl implements OtdsubatPkgService {

	@Autowired
	private OtdsubatPkgRepository otdsubatRepository;
	@Autowired
	private TrustService trustService;
	@Autowired
	private DeductionsService deductionsService;

	private static Logger logger = LogManager.getLogger(OtdsubatPkgServiceImpl.class.getName());

	private static final String Y = "Y";
	private static final String N = "N";
	private static final String OTDSUBAT = "OTDSUBAT";
	private static final String DR = "DR";
	private static final String CR = "CR";

	@Override
	@Transactional
	public Integer processTransaction(final List<OffenderTransactions> list, final String userName) {
		Long lOffenderBookId;
		Long lGlEntrySeq;
		String pOkayFlag = Y;
		Integer returnValue = 0;
		Integer pTxnEntrySeq = 1;
		final Date cTruncSysdate = new Date();
		final Map<String, Object> map = new HashMap<String, Object>();
		for (final OffenderTransactions offenderTransactions : list) {
			offenderTransactions.setCreateUserId(userName);
			try {
					lOffenderBookId = otdsubatRepository.getOffBookIdProcTxn(offenderTransactions.getOffenderId(),
						offenderTransactions.getCaseloadId());
				if (lOffenderBookId == null) {
					offenderTransactions.setErrorMessage("Offender Book ID not found.");
					pOkayFlag = N;
				}
				if (Y.equals(pOkayFlag)) {

					final OffenderTransactions offTrns = new OffenderTransactions();
					offTrns.setTxnId(offenderTransactions.getTxnId());
					offTrns.setTxnEntrySeq(pTxnEntrySeq);
					offTrns.setCaseloadId(offenderTransactions.getCaseloadId());
					offTrns.setOffenderId(offenderTransactions.getOffenderId());
					offTrns.setOffenderBookId(lOffenderBookId);
					offTrns.setTxnPostingType(DR);
					offTrns.setTxnType(offenderTransactions.getTxnType());
					offTrns.setTxnEntryDesc(offenderTransactions.getTxnEntryDesc());
					offTrns.setTxnEntryAmount(offenderTransactions.getTxnEntryAmount());
 					offTrns.setTxnEntryDate(cTruncSysdate);				 
					offTrns.setSubAccountType(offenderTransactions.getFmSubAccountType());
					offTrns.setDeductionFlag(null);
					offTrns.setPreWithholdAmount(null);
					offTrns.setDeductionType(null);
					offTrns.setPayeeCorporateId(null);
					offTrns.setPayeePersonId(null);
					offTrns.setInfoNumber(null);
					offTrns.setSlipPrintedFlag(N);
					offTrns.setCreateUserId(offenderTransactions.getCreateUserId());
					trustService.insertIntoOffenderTrans(offTrns);
					otdsubatRepository.updateOffenderTransactions(offenderTransactions.getTxnId().longValue(),
							pTxnEntrySeq, userName);
				}
				if (Y.equals(pOkayFlag)) {
					pTxnEntrySeq = pTxnEntrySeq + 1;
					final OffenderTransactions offTrns = new OffenderTransactions();
					offTrns.setTxnId(offenderTransactions.getTxnId());
					offTrns.setTxnEntrySeq(pTxnEntrySeq);
					offTrns.setCaseloadId(offenderTransactions.getCaseloadId());
					offTrns.setOffenderId(offenderTransactions.getOffenderId());
					offTrns.setOffenderBookId(lOffenderBookId);
					offTrns.setTxnPostingType(CR);
					offTrns.setTxnType(offenderTransactions.getTxnType());
					offTrns.setTxnEntryDesc(offenderTransactions.getTxnEntryDesc());
					offTrns.setTxnEntryAmount(offenderTransactions.getTxnEntryAmount());
					offTrns.setTxnEntryDate(cTruncSysdate);
					offTrns.setToSubAccountType(offenderTransactions.getToSubAccountType());
					offTrns.setDeductionFlag(null);
					offTrns.setPreWithholdAmount(null);
					offTrns.setDeductionType(null);
					offTrns.setPayeeCorporateId(null);
					offTrns.setPayeePersonId(null);
					offTrns.setInfoNumber(null);
					offTrns.setSlipPrintedFlag(N);
					offTrns.setSubAccountType(offenderTransactions.getFmSubAccountType());
					offTrns.setCreateUserId(offenderTransactions.getCreateUserId());
					try {
					trustService.insertIntoOffenderTrans(offTrns);
					otdsubatRepository.updateOffenderTransactions(offenderTransactions.getTxnId().longValue(),
							offTrns.getTxnEntrySeq(), userName);
					}catch (Exception e) {
						logger.error("insertIntoOffenderTrans"+e);
					}
				}
				if (Y.equals(pOkayFlag)) {
					pTxnEntrySeq = 1;
					lGlEntrySeq = 0l;
					try {
						//procedure call
					trustService.processGlTransNew(offenderTransactions.getCaseloadId(),
							offenderTransactions.getTxnType(), null, offenderTransactions.getTxnEntryAmount(),
							offenderTransactions.getTxnId(), cTruncSysdate, offenderTransactions.getTxnEntryDesc(),
							pTxnEntrySeq, OTDSUBAT, offenderTransactions.getOffenderId().intValue(), lOffenderBookId,
							offenderTransactions.getFmSubAccountType(), offenderTransactions.getToSubAccountType(),
							null, null, null, lGlEntrySeq.intValue(), null,offenderTransactions.getCreateUserId());
					}catch (Exception e) {
						logger.error("processGlTransNew"+e);
					}
				}
				if (Y.equals(pOkayFlag)) {
					if (Y.equals(offenderTransactions.getChequeProductionFlag())) {
						final BankChequeData obj = new BankChequeData();
						obj.setCaseloadId(offenderTransactions.getCaseloadId());
						obj.setTxnId(offenderTransactions.getTxnId().longValue());
						obj.setTxnEntryAmount(BigDecimal.valueOf(offenderTransactions.getTxnEntryAmount()));
						obj.setChequeFlag(Y);
						obj.setStartTxnId(BigDecimal.valueOf(offenderTransactions.getTxnId()));
						obj.setEndTxnId(BigDecimal.valueOf(offenderTransactions.getTxnId()));
						obj.setPayeePersonId(null);
						obj.setPayeeCorporateId(BigDecimal.valueOf(offenderTransactions.getPayeePersonId()));
						obj.setPayeeNameText(offenderTransactions.getPayeeName());
						obj.setSingleEntryFlag("O");
						obj.setBankAccountCode(offenderTransactions.getCrAccountCode().longValue());
						obj.setModuleName(OTDSUBAT);
						obj.setTxnType(offenderTransactions.getTxnType());
						trustService.insertIntoChequeData(obj,userName);
					}
				}
				if (Y.equals(pOkayFlag)) {
					deductionsService.getAcAndSetIndDate(offenderTransactions.getOffenderId(),
							offenderTransactions.getCaseloadId(), userName);
				}
				if (Y.equals(pOkayFlag)) {
					// commit operation happening here
					pOkayFlag = Y;
				} else {
					// rollback operation happening here
					throw new ArithmeticException();
				}

				map.put("P_TXN_ID", offenderTransactions.getTxnId());
				map.put("P_TXN_ENTRY_SEQ", pTxnEntrySeq);
				map.put("P_OKAY_FLAG", pOkayFlag);
				map.put("P_ERROR_MESSAGE", offenderTransactions.getErrorMessage());

				if (map != null)
					returnValue = 1;

			} catch (Exception e) {
				logger.error("processTransaction :" + e);
			}
		}
		return returnValue;
	}

}