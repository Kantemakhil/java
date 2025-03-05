package net.syscon.s4.inmate.trust.generalledger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OtdmgjtrRepository
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
public interface OtdmgjtrRepository {
	List<GlTransactions> glTxnExecuteQuery(GlTransactions objGlTransactions);

	List<AccountCodes> cgfkGlTxn1AccountCodeRecordGroup(String caseloadType,String caseloadId);

	List<AccountCodes> cgfkGlTxnAccountCodeRecordGroup();

	List<GlTransactions> cgrichkGlTransactions(GlTransactions paramBean);

	List<Corporates> cgfkGlTxnPayeeCorporateIdRecordGroup();

	AccountCodes cgfkchkGlTxnGlTxnAcCode(AccountCodes paramBean);

	// Persons cgfkchkGlTxnGlTxnPerF7(Persons paramBean) ;

	AccountCodes cgfkchkGlTxn1GlTxnAcCod(AccountCodes paramBean);

	Integer glTxnInsertGlTransactions(List<GlTransactions> glList);

	List<Persons> cgfkGlTxnPayeePersonIdRecordGroup();

	Corporates cgfkchkGlTxnGlTxnCorpF8(Corporates paramBean);

	List<AccountCodes> getDescandType(String code,String caseloadType);

	Integer lvlLastclosedPeriod(String caseloadId);
	
	Integer lvAllowedReopenPeriod(String caseloadId);

	Integer lvEnteraccountPeriod(Date txnEntryDate);

	Integer isPeriodValid(String caseloadId, Integer lventerAccountPeriod);

	String lvAccountStatus(Integer lventerAccountPeriod, String caseloadId);

	Date getPeriodStartDate(Integer lventerAccountPeriod);

	Date getperiodEndDate(Integer lvlastClosedPeriod);

	Integer isAccountchecking(String caseloadId, Integer accountCode);

	Integer getTrustTrans();

	void trustGjReopenClosedPeriod(String caseloadId, Date txnEntryDate);

	Integer insertIntoCheckData(GlTransactions obj);


	String cStatusNumber(AccountCodes accountCodes);

	BigDecimal getCurrentBalance(String caseloadId, Integer accountCode);
	

}
