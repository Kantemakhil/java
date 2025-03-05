package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.MinimumPayableBalances;
import net.syscon.s4.common.beans.MinimumPayableBalancescommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Statutes;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OcmmpbalRepository;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OcmmpbalService;

@Service
public class OcmmpbalServiceImpl extends BaseBusiness implements OcmmpbalService {

	@Autowired
	private OcmmpbalRepository ocmmpbalRepository;
	/**
	 * Logger object used to print the log in the file
	 */
//	private static Logger logger = LogManager.getLogger(OcmmpbalServiceImpl.class.getName());

	/**
	 * Creates new OcmmpbalServiceImpl class Object
	 */
	public OcmmpbalServiceImpl() {
		// OcmmpbalServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<AccountCodes> CgfkchkMinPbMinPbAcCode(AccountCodes paramBean) {
		List<AccountCodes> accountCodes = ocmmpbalRepository.cgfkchkMinPbMinPbAcCode(paramBean);
		return accountCodes;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> CgwhenNewFormInstance() {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<MinimumPayableBalances> minPbExecuteQuery(MinimumPayableBalances searchRecord) {
		return ocmmpbalRepository.minPbExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstMIN_PB
	 *
	 * @throws SQLException
	 */
	@Transactional
	public String minPbCommit(MinimumPayableBalancescommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (MinimumPayableBalances obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());	
			}
			liReturn = ocmmpbalRepository.minPbInsertMinimumPayableBalances(commitBean.getInsertList());

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (MinimumPayableBalances obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmmpbalRepository.minPbUpdateMinimumPayableBalances(commitBean.getUpdateList());
		}

		return String.valueOf(liReturn);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AccountCodes> cgfkMinPbAccountCodeRecordGroup(final String caseloadType) {
		return ocmmpbalRepository.cgfkMinPbAccountCodeRecordGroup(caseloadType);

	}

	public List<AccountCodes> minPbAccountCodeRecordGroup(final String caseloadType) {
		List<AccountCodes> resultData = ocmmpbalRepository.cgfkMinPbAccountCodeRecordGroup(caseloadType);
		if (resultData != null) {
			resultData.forEach(ele -> {
				ele.setAccountName(ele.getDescription());
				ele.setDescription(ele.getCode());
			});
		}
		return resultData;

	}

}