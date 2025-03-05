package net.syscon.s4.triggers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.CommGlTransactions;

public interface CommGlTransactionsT2Repository {

	Integer commCaseloadAcSummariesCount(String caseloadId, Integer accountCode, Date txnEntryDate) throws Exception;

	String getTxnPostingType(Integer accountCode) throws Exception;

	Integer getAccountPeriodId(Date txnEntryDate) throws Exception;

	Integer getComCsldCurrentAccountId() throws Exception;

	Integer insertComCsldCurrentAccountsTxns(List<CaseloadCurrentAccountsTxns> caseCurAccTxList) throws Exception;

	CommGlTransactions getCommGlTransactions(BigDecimal taxId);
}
