package net.syscon.s4.inmate.trust.generalledger;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadCurrentAccounts;
import net.syscon.s4.im.beans.CaseloadCurrentAccountsCommitBean;

/**
 * Interface OtiglbalService
 */
public interface OtiglbalService {
	List<CaseloadCurrentAccounts> csldCaExecuteQuery(CaseloadCurrentAccounts objCaseloadAcc);

	AccountCodes cgfkchkCsldCaCsldAcAcCo(BigDecimal accountCode);

	Integer csldCaCommit(CaseloadCurrentAccountsCommitBean commitBean);

}
