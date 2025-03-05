package net.syscon.s4.inmate.trust.generalledger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inst.booking.beans.Persons;

public interface OtdagjtrRepository {
	Integer sysPflInsertSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	List<GlTransactions> glTxnExecuteQuery(GlTransactions objGlTransactions);

	List<Corporates> cgfkGlTxnPayeeCorporateIdRecordGroup(Integer corporateId);

	AccountCodes cgfkchkGlTxnGlTxnAcCode(AccountCodes paramBean);

	Persons cgfkchkGlTxnGlTxnPerF7(Persons paramBean);

	TransactionTypes cgfkchkGlTxnGlTxnTxnTyp(TransactionTypes paramBean);

	AccountCodes cgfkchkGlTxn1GlTxnAcCod(AccountCodes paramBean);

	Integer sysPflDeleteSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	Integer glTxnInsertGlTransactions(List<GlTransactions> lstGlTransactions);

	Corporates cgfkchkGlTxnGlTxnCorpF8(Corporates paramBean);

	List<SysDual> cgwhenNewFormInstance(SysDual paramBean);

	List<GlTransactions> cgrichkGlTransactions(GlTransactions paramBean);

	List<TransactionTypes> cgfkGlTxnTxnTypeRecordGroup(String caseloadId, String caseloadType);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	List<Persons> cgfkGlTxnPayeePersonIdRecordGroup(Integer personId);

	Date trustGLReopenClosedPeriod(String caseloadId);

	BigDecimal trustGjGetLastClosedPeriod(String caseloadId);

	BigDecimal trustGjGetAllowedBackDatedPeriod(String caseloadId);

	BigDecimal trustGjGetAccountPeriod(Date txnEntryDate);

	BigDecimal checkPeriodDays(String caseloadId, BigDecimal lvEnterAccountPeriod);

	String cAccountStatus(String caseloadId, BigDecimal lvEnterAccountPeriod);

	Date trustGjGetPeriodStartDate(BigDecimal lvEnterAccountPeriod);

	Date trustGjGetPeriodEndDate(BigDecimal lvLastClosedPeriod);

	Map<String, Object> getTxnOp(String caseloadId, String caseloadType, String txnType);

	Map<String, Object> getAccountTypeAndPostingType(BigDecimal acct, String caseloadType);

	BigDecimal getCurrentBalance(BigDecimal acct, String caseloadId, String userId);

	Map<String, Object> getProductionAndPayeeFlag(String caseloadId, String caseloadType, String txnType);

	Map<String, Object> defaultCorpPerson(String txnType);

	String getPersonName(BigDecimal personId);

	String getCorporateName(BigDecimal corporateId);

	List<GlTransactions> prGetOffsetAccounts(GlTransactions paramBean);

	Integer genTrustTrans(String seqId);

	Integer trustInsertIntoChequeData(GlTransactions glTxnModule);

	void trustGjReopenClosedPeriod(String caseloadId, Date txnEntryDate);

}
