package net.syscon.s4.sa.admin.mergeoffenders.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.sa.admin.beans.MergeTransactions;
import net.syscon.s4.sa.admin.mergeoffenders.OuimergeRepository;
import net.syscon.s4.sa.admin.mergeoffenders.OuimergeService;

/**
 * Class OuimergeServiceImpl
 */
@Service
public class OuimergeServiceImpl extends BaseBusiness implements OuimergeService {

	@Autowired
	private OuimergeRepository ouimergeRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<MergeTransactions> transactionsExecuteQuery(final MergeTransactions searchRecord) {
		return ouimergeRepository.transactionsExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgStatusRecordGroup() {
		return ouimergeRepository.rgStatusRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgSourceRecordGroup() {
		return ouimergeRepository.rgSourceRecordGroup();

	}

}