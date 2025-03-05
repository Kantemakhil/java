package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.triggers.CaseloadCurrentAccountsTxns;
import net.syscon.s4.triggers.GlTransactionsT2Repository;
import net.syscon.s4.triggers.GlTransactionsT2Service;

@Service
public class GlTransactionsT2ServiceImpl implements GlTransactionsT2Service {
	Logger logger = LogManager.getLogger(GlTransactionsT2ServiceImpl.class);
	@Autowired
	GlTransactionsT2Repository glTransactionsT2Repository;

	@Override
	public String glTransactionsT2Trigger(GlTransactions glTransactions) {
		Integer acCount;
		Integer acPdId;
		Integer acCurPdId;
		Integer acntId;
		String pAcntPosting;
		BigDecimal pstngAmount = null;
		try {
			acCount = glTransactionsT2Repository.acCount(glTransactions.getCaseloadId(),
					glTransactions.getAccountCode(), glTransactions.getTxnEntryDate());
			if (acCount == 0) {
				return "Account Code not found in Caseload Account Summaries for account "
						+ String.valueOf(glTransactions.getAccountCode()) + "  and caseload "
						+ String.valueOf(glTransactions.getCaseloadId());
			}
			pAcntPosting = glTransactionsT2Repository.pAcntPosting(glTransactions.getAccountCode().intValue());
			if(pAcntPosting.equals(glTransactions.getTxnPostUsage())) {
				pstngAmount = glTransactions.getTxnEntryAmount();
			} else {
				pstngAmount = new BigDecimal("-1.0").multiply(glTransactions.getTxnEntryAmount());
				glTransactions.setTxnEntryAmount(pstngAmount);
			}
			acPdId = glTransactionsT2Repository.acPdId(glTransactions.getTxnEntryDate());
			acntId = glTransactionsT2Repository.acntId();
			final CaseloadCurrentAccountsTxns obj = new CaseloadCurrentAccountsTxns();
			obj.setCaseloadCurrentAccountId(acntId.longValue());
			obj.setCaseloadId(glTransactions.getCaseloadId());
			obj.setAccountCode(glTransactions.getAccountCode().intValue());
			obj.setAccountPeriodId(acPdId);
			obj.setCurrentBalance(glTransactions.getTxnEntryAmount());
			obj.setCreateUserId(glTransactions.getCreateUserId());
			obj.setTxnId(glTransactions.getTxnId());
			obj.setTxnEntrySeq(null);
			obj.setGlEntrySeq(null);
			final List<CaseloadCurrentAccountsTxns> finalList = new ArrayList<CaseloadCurrentAccountsTxns>();
			finalList.add(obj);
			Integer retval = glTransactionsT2Repository.integer(finalList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
