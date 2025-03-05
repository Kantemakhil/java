package net.syscon.s4.inmate.trust.generalledger.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadCurrentAccounts;
import net.syscon.s4.im.beans.CaseloadCurrentAccountsCommitBean;
import net.syscon.s4.inmate.trust.generalledger.OtiglbalRepository;
import net.syscon.s4.inmate.trust.generalledger.OtiglbalService;

/**
 * Class OtiglbalServiceImpl
 */
@Service
public class OtiglbalServiceImpl extends BaseBusiness implements OtiglbalService {

	@Autowired
	private OtiglbalRepository otiglbalRepository;

	/**
	 * Creates new OtiglbalServiceImpl class Object
	 */
	public OtiglbalServiceImpl() {
		// OtiglbalServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param accountCode
	 * @return AccountCodes
	 */
	public AccountCodes cgfkchkCsldCaCsldAcAcCo(final BigDecimal accountCode) {
		return otiglbalRepository.cgfkchkCsldCaCsldAcAcCo(accountCode);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 * @return List<CaseloadCurrentAccounts>
	 */
	public List<CaseloadCurrentAccounts> csldCaExecuteQuery(final CaseloadCurrentAccounts searchRecord) {
		final List<CaseloadCurrentAccounts> lstCaseloads = (List<CaseloadCurrentAccounts>) otiglbalRepository
				.csldCaExecuteQuery(searchRecord);
		for (final CaseloadCurrentAccounts objCaseloads : lstCaseloads) {
			final AccountCodes objAccount = cgfkchkCsldCaCsldAcAcCo(objCaseloads.getAccountCode());
			objCaseloads.setAccountPartyType(objAccount.getAccountType());
			objCaseloads.setDspTxnPostingType(objAccount.getTxnPostingType());
			objCaseloads.setBankAccountNumber(objAccount.getAccountName());
		}
		return lstCaseloads;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 * @return Integer
	 */
	@Transactional
	public Integer csldCaCommit(final CaseloadCurrentAccountsCommitBean commitBean) {
		return 0;
	}
}