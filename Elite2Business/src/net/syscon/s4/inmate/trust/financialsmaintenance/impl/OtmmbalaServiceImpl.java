package net.syscon.s4.inmate.trust.financialsmaintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderSubAccountsCommitBean;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtmmbalaRepository;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtmmbalaService;

/**
 * Creates new OtmmbalaServiceImpl class Object
 */
@Service
public class OtmmbalaServiceImpl implements OtmmbalaService {

	@Autowired
	private OtmmbalaRepository otmmbalaRepository;

	/**
	 * Fetch the records from database table offSubaExecuteQuery
	 * offSubaExecuteQuery
	 * 
	 * @param searchRecord
	 * @return List<OffenderSubAccounts>
	 */
	public List<OffenderSubAccounts> offSubaExecuteQuery(OffenderSubAccounts searchRecord) {
		return otmmbalaRepository.offSubaExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table offSubaCommit offSubaCommit
	 * 
	 * @param commitBean
	 * @return Integer
	 */
	@Transactional
	public Integer offSubaCommit(OffenderSubAccountsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = otmmbalaRepository.offSubaUpdateOffenderSubAccounts(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 * cgfkOffSubaTrustAccountCoRecordGroup
	 * 
	 * @return List<AccountCodes>
	 */
	public List<AccountCodes> cgfkOffSubaTrustAccountCoRecordGroup() {
		List<AccountCodes> returnList = otmmbalaRepository.cgfkOffSubaTrustAccountCoRecordGroup();
		for (final AccountCodes accountCodes : returnList) {
			accountCodes.setCode(accountCodes.getAccountCode().toString());
		}
		return returnList;
	}

}