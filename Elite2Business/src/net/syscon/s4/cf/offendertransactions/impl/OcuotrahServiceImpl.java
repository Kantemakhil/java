package net.syscon.s4.cf.offendertransactions.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.offendertransactions.OcuotrahRepository;
import net.syscon.s4.cf.offendertransactions.OcuotrahService;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTransactions;

/**
 * Class OcuotrahServiceImpl
 * 
 **/
@Service
public class OcuotrahServiceImpl extends BaseBusiness implements OcuotrahService {

	@Autowired
	private OcuotrahRepository ocuotrahRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions searchRecord) {
		final List<OffenderTransactions> returnList = ocuotrahRepository.offTxnExecuteQuery(searchRecord);
		returnList.forEach(ele -> {
			if (ele.getCgnbtTxnEntryDate() != null) {
				SimpleDateFormat localDateFormat = new SimpleDateFormat("HH24:mm");
				String time = localDateFormat.format(ele.getCgnbtTxnEntryDate());
				if (time != null) {
					ele.setCgnbtTxnEntryDate(time);
			}
		}

	});
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TXN
	 *
	 */
	@Transactional
	public Integer offTxnCommit(final OffenderTransactionsCommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return ocuotrahRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
	}

}