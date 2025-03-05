package net.syscon.s4.inmate.trust.checks.checksmainataince.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.BankAccountsMaintenances;
import net.syscon.s4.im.beans.BankAccountsMaintenancesCommitBean;
import net.syscon.s4.im.beans.CaseloadCurrentAccountsBase;
import net.syscon.s4.im.beans.CaseloadCurrentAccountsBaseCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inmate.trust.checks.checksmainataince.OtmbaccoRepository;
import net.syscon.s4.inmate.trust.checks.checksmainataince.OtmbaccoService;
import net.syscon.s4.triggers.CaseloadCurrentAcctsBaseT1Service;

/**
 * Class OtmbaccoServiceImpl
 */

@Service

public class OtmbaccoServiceImpl extends BaseBusiness implements OtmbaccoService {

	@Autowired
	private OtmbaccoRepository otmbaccoRepository;
	@Autowired
	private CaseloadCurrentAcctsBaseT1Service caseloadCurrentAcctsBaseT1Service;

	/**
	 * Creates new OtmbaccoServiceImpl class Object
	 */

	public OtmbaccoServiceImpl() {
		// OtmbaccoServiceImpl
	}

	/**
	 * Fetch the records from database table csldCabExecuteQuery
	 * 
	 * @param searchRecord
	 */
	public List<CaseloadCurrentAccountsBase> csldCabExecuteQuery(final CaseloadCurrentAccountsBase searchRecord) {
		List<CaseloadCurrentAccountsBase> returnList = otmbaccoRepository.csldCabExecuteQuery(searchRecord);
		for (final CaseloadCurrentAccountsBase caseloadCurrentAccountsBase : returnList) {
			if (caseloadCurrentAccountsBase.getPayeeCorporateId() != null) {
				String corpName = otmbaccoRepository
						.corporeteNameData(caseloadCurrentAccountsBase.getPayeeCorporateId());
				if (corpName != null) {
					caseloadCurrentAccountsBase.setCorporateName(corpName);
				}
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 * 
	 * @param commitBean
	 */
	@Transactional
	public Integer csldCabCommit(final CaseloadCurrentAccountsBaseCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = csldCabInsertCaseloadCurrentAccountsBase(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (CaseloadCurrentAccountsBase obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmbaccoRepository.csldCabUpdateCaseloadCurrentAccountsBase(commitBean.getUpdateList());
			for (CaseloadCurrentAccountsBase obj : commitBean.getUpdateList()) {
				caseloadCurrentAcctsBaseT1Service.caseloadCurrentAcctBaseT1Trigger(obj.getCaseloadId(), obj.getAccountCode(), obj.getBankAccountNumber());
			}
			
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (CaseloadCurrentAccountsBase obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmbaccoRepository.csldCabDeleteCaseloadCurrentAccountsBase(commitBean.getDeleteList());
		}
		return liReturn;
	}

	public Integer csldCabInsertCaseloadCurrentAccountsBase(final List<CaseloadCurrentAccountsBase> lstAccountCodes) {
		Integer resultData = 0;
		for (final CaseloadCurrentAccountsBase caseloadCurrentAccountsBase : lstAccountCodes) {
			Long accountPeriodId = otmbaccoRepository.accountPeriodIdData();
			if (accountPeriodId != null) {
				caseloadCurrentAccountsBase.setAccountPeriodId(accountPeriodId);
			} else {
				resultData = 3;
				return resultData;
			}
			caseloadCurrentAccountsBase.setCurrentBalance(BigDecimal.ZERO);
		}
		resultData = otmbaccoRepository.csldCabInsertCaseloadCurrentAccountsBase(lstAccountCodes);
		return resultData;
	}

	/**
	 * Fetch the records from database table bankAmExecuteQuery
	 * 
	 * @param searchRecord
	 */
	public List<BankAccountsMaintenances> bankAmExecuteQuery(final BankAccountsMaintenances searchRecord) {
		return otmbaccoRepository.bankAmExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table bankAmCommit
	 * 
	 * @param commitBean
	 */
	@Transactional

	public Integer bankAmCommit(final BankAccountsMaintenancesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (BankAccountsMaintenances data : commitBean.getInsertList()) {
				data.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = otmbaccoRepository.bankAmInsertBankAccountsMaintenances(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (BankAccountsMaintenances data : commitBean.getUpdateList()) {
				data.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmbaccoRepository.bankAmUpdateBankAccountsMaintenances(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (BankAccountsMaintenances data : commitBean.getDeleteList()) {
				data.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmbaccoRepository.bankAmDeleteBankAccountsMaintenances(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 * cgfkCsldCabBankAccountTypRecordGroup
	 */
	public List<ReferenceCodes> cgfkCsldCabBankAccountTypRecordGroup() {
		return otmbaccoRepository.cgfkCsldCabBankAccountTypRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * cgfkCsldCabPayeeCorporateRecordGroup
	 */
	public List<Corporates> cgfkCsldCabPayeeCorporateRecordGroup() {
		return otmbaccoRepository.cgfkCsldCabPayeeCorporateRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * cgfkCsldCabAccountCodeRecordGroup
	 */
	public List<AccountCodes> cgfkCsldCabAccountCodeRecordGroup( String str) {
		List<AccountCodes> returnList = otmbaccoRepository.cgfkCsldCabAccountCodeRecordGroup(str);
		for (final AccountCodes accountCodes : returnList) {
			accountCodes.setCode(accountCodes.getAccountCode().toString());
		}
		return returnList;

	}

	@Override
	public String corporeteNameData(final BigDecimal payeeCorporateId) {
		return otmbaccoRepository.corporeteNameData(payeeCorporateId);
	}

}