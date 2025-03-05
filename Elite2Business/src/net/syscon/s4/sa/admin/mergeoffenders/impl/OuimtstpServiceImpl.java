package net.syscon.s4.sa.admin.mergeoffenders.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.sa.admin.beans.VMergeTransactionProcesses;
import net.syscon.s4.sa.admin.mergeoffenders.OuimtstpRepository;
import net.syscon.s4.sa.admin.mergeoffenders.OuimtstpService;

/**
 * Class OuimtstpServiceImpl
 */
@Service
public class OuimtstpServiceImpl extends BaseBusiness implements OuimtstpService {

	@Autowired
	private OuimtstpRepository ouimtstpRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VMergeTransactionProcesses> mergTxnProcExecuteQuery(final VMergeTransactionProcesses searchRecord) {
		return ouimtstpRepository.mergTxnProcExecuteQuery(searchRecord);

	}

}