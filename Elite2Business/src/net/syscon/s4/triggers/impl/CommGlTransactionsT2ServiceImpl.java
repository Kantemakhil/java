package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.CommGlTransactions;
import net.syscon.s4.pkgs.TagExceptions;
import net.syscon.s4.pkgs.tag_exceptions.impl.TagExceptionsServiceImpl;
import net.syscon.s4.triggers.CaseloadCurrentAccountsTxns;
import net.syscon.s4.triggers.CommGlTransactionsT2Repository;
import net.syscon.s4.triggers.CommGlTransactionsT2Service;

@Service
public class CommGlTransactionsT2ServiceImpl implements CommGlTransactionsT2Service {
	@Autowired
	TagExceptionsServiceImpl tagExceptionsService;
	@Autowired
	private CommGlTransactionsT2Repository commGlTransactionsT2Repository;

	Logger logger = LogManager.getLogger(CommGlTransactionsT2ServiceImpl.class);
	
	@Override
	public String commGlTransactionsT2(CommGlTransactions newCommGlTransactions) {
		final TagExceptions tagExceptions = new TagExceptions();
		Integer accountCode = newCommGlTransactions.getAccountCode();
		Integer acCount;
		try {
			acCount = commGlTransactionsT2Repository.commCaseloadAcSummariesCount(newCommGlTransactions.getCaseloadId(),
					newCommGlTransactions.getAccountCode(), newCommGlTransactions.getTxnEntryDate());
			if (acCount == 0) {
				return "Account Code not found in Caseload Account Summaries for account "
						+ String.valueOf(newCommGlTransactions.getAccountCode()) + "  and caseload "
						+ String.valueOf(newCommGlTransactions.getCaseloadId());
			}
		} catch (Exception e) {
			tagExceptions.setProcedureName("COMM_GL_TRANSACTIONS_T2");
			tagExceptions.setErrorMessage(e.getMessage());
			tagExceptions.setErrorLocation("COMM_GL_TRANSACTIONS_T2 TRIGGER INNER EXCEPTION..1");
			tagExceptionsService.tagExceptionsTrigger(tagExceptions);
			return "Account Code not found in Caseload Account Summaries for account "
			+ String.valueOf(newCommGlTransactions.getAccountCode()) + "  and caseload "
			+ String.valueOf(newCommGlTransactions.getCaseloadId());
		}
		
		String pAcntPosting;
		try {
			pAcntPosting = commGlTransactionsT2Repository.getTxnPostingType(newCommGlTransactions.getAccountCode());
			if (pAcntPosting == null) {
				tagExceptions.setProcedureName("COMM_GL_TRANSACTIONS_T2");
				tagExceptions.setErrorMessage("Account code " + newCommGlTransactions.getAccountCode() + " does not exist.");
				tagExceptions.setErrorLocation("COMM_GL_TRANSACTIONS_T2 NO_DATA_FOUND EXCEPTION..1");
				tagExceptionsService.tagExceptionsTrigger(tagExceptions);
				return "Account code " + newCommGlTransactions.getAccountCode() + " does not exist.";
			}
			if ("twoManyRows".equals(pAcntPosting)) {
				tagExceptions.setProcedureName("COMM_GL_TRANSACTIONS_T2");
				tagExceptions.setErrorMessage("Error: More than one  TXN_POSTING_TYPE exists for Account code " + newCommGlTransactions.getAccountCode());
				tagExceptions.setErrorLocation("COMM_GL_TRANSACTIONS_T2 TOO_MANY_ROWS EXCEPTION..1");
				tagExceptionsService.tagExceptionsTrigger(tagExceptions);
				return "Error: More than one  TXN_POSTING_TYPE exists for Account code " + newCommGlTransactions.getAccountCode();
			}
		} catch (Exception e) {
			tagExceptions.setProcedureName("COMM_GL_TRANSACTIONS_T2");
			tagExceptions.setErrorMessage("Error: GL_TRANSACTIONS_T2.  " + e.getMessage());
			tagExceptions.setErrorLocation("COMM_GL_TRANSACTIONS_T2 INNER EXCEPTION..2");
			tagExceptionsService.tagExceptionsTrigger(tagExceptions);
			logger.error("Error: GL_TRANSACTIONS_T2.  " + e.getMessage());
			return "Error: GL_TRANSACTIONS_T2.  " + e.getMessage();
		}
		BigDecimal pstngAmount=null;
		if(pAcntPosting.equals(newCommGlTransactions.getTxnPostUsage())) {
			pstngAmount=newCommGlTransactions.getTxnEntryAmount();
		}else {
			pstngAmount = new BigDecimal("-1.0").multiply(newCommGlTransactions.getTxnEntryAmount());
		}
		Integer acPdId;
		try {
			acPdId = commGlTransactionsT2Repository.getAccountPeriodId(newCommGlTransactions.getTxnEntryDate());
			if (acPdId == 0) {
				tagExceptions.setProcedureName("COMM_GL_TRANSACTIONS_T2");
				tagExceptions.setErrorMessage("Error: Could not find account Period Id in Account_Periods table.");
				tagExceptions.setErrorLocation("COMM_GL_TRANSACTIONS_T2 NO_DATA_FOUND EXCEPTION..2");
				tagExceptionsService.tagExceptionsTrigger(tagExceptions);
				return "Error: Could not find account Period Id in Account_Periods table.";
			}
		} catch (Exception e) {
			tagExceptions.setProcedureName("COMM_GL_TRANSACTIONS_T2");
			tagExceptions.setErrorMessage("Error: GL_TRANSACTIONS_T2.  " + e.getMessage());
			tagExceptions.setErrorLocation("COMM_GL_TRANSACTIONS_T2 TRIGGER INNER EXCEPTION..3");
			tagExceptionsService.tagExceptionsTrigger(tagExceptions);
			return "Error: GL_TRANSACTIONS_T2.  " + e.getMessage();
		}
		
		Long acntId = null;
		try {
			acPdId = commGlTransactionsT2Repository.getComCsldCurrentAccountId();
			if (acntId==null || acntId == 0) {
				tagExceptions.setProcedureName("COMM_GL_TRANSACTIONS_T2");
				tagExceptions.setErrorMessage("Error: COM_CSLD_CURRENT_ACCOUNT_ID Sequence Not Found.");
				tagExceptions.setErrorLocation("COMM_GL_TRANSACTIONS_T2 NO_DATA_FOUND EXCEPTION..3");
				tagExceptionsService.tagExceptionsTrigger(tagExceptions);
				return "Error: COM_CSLD_CURRENT_ACCOUNT_ID Sequence Not Found.";
			}
		} catch (Exception e) {
			tagExceptions.setProcedureName("COMM_GL_TRANSACTIONS_T2");
			tagExceptions.setErrorMessage("Error: GL_TRANSACTIONS_T2.  " + e.getMessage());
			tagExceptions.setErrorLocation("COMM_GL_TRANSACTIONS_T2 TRIGGER INNER EXCEPTION..4");
			tagExceptionsService.tagExceptionsTrigger(tagExceptions);
			return "Error: GL_TRANSACTIONS_T2.  " + e.getMessage();
		}
		try {
			List<CaseloadCurrentAccountsTxns> caseCurAccTxList =new ArrayList<CaseloadCurrentAccountsTxns>();
			CaseloadCurrentAccountsTxns caseloadCurrentAccountsTxns=new CaseloadCurrentAccountsTxns();
			caseloadCurrentAccountsTxns.setCaseloadCurrentAccountId(acntId);
			caseloadCurrentAccountsTxns.setCaseloadId(newCommGlTransactions.getCaseloadId());
			caseloadCurrentAccountsTxns.setAccountCode(newCommGlTransactions.getAccountCode());
			caseloadCurrentAccountsTxns.setAccountPeriodId(acCount);
			caseloadCurrentAccountsTxns.setCurrentBalance(pstngAmount);
			caseCurAccTxList.add(caseloadCurrentAccountsTxns);
			commGlTransactionsT2Repository.insertComCsldCurrentAccountsTxns(caseCurAccTxList);
		} catch (Exception e) {
			tagExceptions.setProcedureName("COMM_GL_TRANSACTIONS_T2");
			tagExceptions.setErrorMessage("Error: GL_TRANSACTIONS_T2.  " + e.getMessage());
			tagExceptions.setErrorLocation("COMM_GL_TRANSACTIONS_T2 TRIGGER INNER EXCEPTION..5");
			tagExceptionsService.tagExceptionsTrigger(tagExceptions);
			return e.getMessage();
		}
		return null;
	}
}
