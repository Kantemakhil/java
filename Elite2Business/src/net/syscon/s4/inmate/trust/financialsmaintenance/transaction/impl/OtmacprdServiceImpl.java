package net.syscon.s4.inmate.trust.financialsmaintenance.transaction.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inmate.beans.AccountPeriods;
import net.syscon.s4.inmate.beans.AccountPeriodsCommitBean;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OtmacprdRepository;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OtmacprdService;

/**
 * Class OtmacprdServiceImpl
 */
@Service
public class OtmacprdServiceImpl extends BaseBusiness implements OtmacprdService {

	@Autowired
	private OtmacprdRepository otmacprdRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<AccountPeriods> acPrdExecuteQuery(final AccountPeriods searchRecord) {
		return otmacprdRepository.acPrdExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAC_PRD
	 *
	 * 
	 */
	@Transactional
	public String acPrdCommit(AccountPeriodsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (AccountPeriods obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmacprdRepository.acPrdInsertAccountPeriods(commitBean.getInsertList());

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (AccountPeriods obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmacprdRepository.acPrdUpdateAccountPeriods(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (AccountPeriods obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otmacprdRepository.acPrdDeleteAccountPeriods(commitBean.getDeleteList());
		}
		return String.valueOf(liReturn);
	}

	public List<AccountPeriods> overlapdates() {
		return otmacprdRepository.overlapdates();
	}

	public Integer duplicateAccountperiodId(Long accountPeriodId) {
		return otmacprdRepository.duplicateAccountperiodId(accountPeriodId);
	}

	@Override
	public Integer duplicateOverlapDate(final String overlapDate) {
		return otmacprdRepository.duplicateOverlapDate(overlapDate);
	}

}