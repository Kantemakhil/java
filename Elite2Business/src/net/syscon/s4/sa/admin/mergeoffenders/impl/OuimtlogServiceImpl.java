package net.syscon.s4.sa.admin.mergeoffenders.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.sa.admin.beans.MergeTransactionLogs;
import net.syscon.s4.sa.admin.mergeoffenders.OuimtlogRepository;
import net.syscon.s4.sa.admin.mergeoffenders.OuimtlogService;

/**
 * Class OuimtlogServiceImpl
 */
@Service
public class OuimtlogServiceImpl extends BaseBusiness implements OuimtlogService {

	@Autowired
	private OuimtlogRepository ouimtlogRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<MergeTransactionLogs> mergeLogExecuteQuery(final MergeTransactionLogs searchRecord) {
		return ouimtlogRepository.mergeLogExecuteQuery(searchRecord);

	}
}