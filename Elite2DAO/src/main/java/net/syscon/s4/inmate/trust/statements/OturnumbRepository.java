package net.syscon.s4.inmate.trust.statements;

import java.util.List;

import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.Offenders;

/**
 * 
 * Interface OturnumbRepository
 */

public interface OturnumbRepository {

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objOffenderTransactions);

	Integer offTxnInsertOffenderTransactions(List<OffenderTransactions> lstOffenderTransactions);

	Integer offTxnUpdateOffenderTransactions(List<OffenderTransactions> lstOffenderTransactions);

	Offenders getOffenderDetails(Long offenderBookId);

}
