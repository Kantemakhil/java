package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.trust.trustaccounts.OtdhiremRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtdhiremService;

/**
 * Class OtdhiremServiceImpl
 */
@Service
public class OtdhiremServiceImpl extends BaseBusiness implements OtdhiremService {

	@Autowired
	private OtdhiremRepository otdhiremRepository;

	/**
	 * Creates new OtdhiremServiceImpl class Object
	 */
	public OtdhiremServiceImpl() {
		// OtdhiremServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Dual> cgwhenNewFormInstance(final SysDual paramBean) {
		return otdhiremRepository.cgwhenNewFormInstance(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions searchRecord) {
		return otdhiremRepository.offTxnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TXN
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offTxnCommit(final OffenderTransactionsCommitBean CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return otdhiremRepository.sysPflExecuteQuery(searchRecord);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

}