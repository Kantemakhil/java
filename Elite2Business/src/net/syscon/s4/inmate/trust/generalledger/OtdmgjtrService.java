package net.syscon.s4.inmate.trust.generalledger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OtdmgjtrService
 */
public interface OtdmgjtrService {

	Integer glTxnCommit(GlTransactionsCommitBean commitBean);


	List<GlTransactions> glTxnExecuteQuery(GlTransactions objGlTransactions);


	List<AccountCodes> cgfkGlTxn1AccountCodeRecordGroup(String caseloadType,String caseloadId);

	List<AccountCodes> cgfkGlTxnAccountCodeRecordGroup();

	List<Corporates> cgfkGlTxnPayeeCorporateIdRecordGroup();


	List<Persons> cgfkGlTxnPayeePersonIdRecordGroup();

	List<AccountCodes> getDescandType(String code,String caseloadType);

	Integer lvlLastclosedPeriod(String caseloadId);
	
	Integer lvAllowedReopenPeriod(String caseloadId);

	Integer lvEnteraccountPeriod(Long txnEntryDate);

	Integer isPeriodValid(String caseloadId, Integer lventerAccountPeriod);

	String lvAccountStatus(Integer lventerAccountPeriod, String caseloadId);

	Date getPeriodStartDate(Integer lventerAccountPeriod);

	Date getperiodEndDate(Integer lvlastClosedPeriod);

	Integer isAccountchecking(String caseloadId, Integer accountCode);

	AccountCodes cStatus(AccountCodes accountCodes);

	BigDecimal getCurrentBalance(String caseloadId, Integer accountCode, String userName);

}
