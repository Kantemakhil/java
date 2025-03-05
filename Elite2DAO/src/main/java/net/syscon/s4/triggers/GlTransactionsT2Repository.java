package net.syscon.s4.triggers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.pkgs.TagExceptions;

public interface GlTransactionsT2Repository {
	Integer acCount(String caseloadId, BigDecimal accountCode, Date txnEntryDate) throws Exception;

	String pAcntPosting(Integer accountCode) throws Exception;

	Integer acPdId(Date txnEntryDate) throws Exception;

	Integer acntId() throws Exception;

	Integer integer(List<CaseloadCurrentAccountsTxns> caseCurreAccTxns) throws Exception;
	
	Integer tagExceptions(TagExceptions tagExceptions);
}
