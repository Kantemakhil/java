package net.syscon.s4.cf.offendertransactions;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.im.beans.TransactionOperation;

/**
 * Interface OcdcrefuRepository
 */
public interface OcdcrefuRepository {

	Double offTxnExecuteQuery(OffenderTransactions objOffenderTransactions);

	Integer offTxnInsertOffenderTransactions(OffenderTransactions commitBean);

	String getDescription();

	OffenderTransactions offTxnValidateQuery(OffenderTransactions searchBean);

	Double offTxngetMinPayment(OffenderTransactions searchBean);

	Integer genTrustTrans(String seqId);

	Integer processGlTransNew(OffenderTransactions commitBean);

	OffenderTransactions updateOffenderBalance(OffenderTransactions commitBean, Date tansDate);

	TransactionOperation ocdcrefuChkProdflagAcccode(OffenderTransactions offenderTransactions);

	void insertIntoChkData(OffenderTransactions commitBean);

	void insertBankChequeBeneficiaries(OffenderTransactions commitBean);
}
