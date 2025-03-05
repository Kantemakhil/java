package net.syscon.s4.sa.admin.mergeoffenders.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.beans.VMergeTransactionProcesses;
import net.syscon.s4.sa.admin.mergeoffenders.OuimtstpRepository;

/**
 * Class OuimtstpRepositoryImpl
 * 
 */
@Repository
public class OuimtstpRepositoryImpl extends RepositoryBase implements OuimtstpRepository {

	private final Map<String, FieldMapper> vTransPrcsMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("END_DATE", new FieldMapper("endDate"))
			.put("PROCESS_DESCRIPTION", new FieldMapper("processDescription"))
			.put("PROCESS_NAME", new FieldMapper("processName"))
			.put("BEGIN_TIME", new FieldMapper("beginTime"))
			.put("PROCESS_ID", new FieldMapper("processId"))
			.put("TIMEFRAME_FLAG", new FieldMapper("timeframeFlag"))
			.put("BEGIN_DATE", new FieldMapper("beginDate"))
			.put("TRANSACTION_TYPE", new FieldMapper("transactionType"))
			.put("MANDATORY_ON_FLAG", new FieldMapper("mandatoryOnFlag"))
			.put("TRANSFER_FLAG", new FieldMapper("transferFlag"))
			.put("DEFAULT_ON_FLAG", new FieldMapper("defaultOnFlag"))
			.put("MERGE_TRANSACTION_ID", new FieldMapper("mergeTransactionId"))
			.put("END_TIME", new FieldMapper("endTime"))
			.build();

	/**
	 * Creates new OuimtstpRepositoryImpl class Object
	 */
	public OuimtstpRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VMergeTransactionProcesses
	 *
	 * @return List<VMergeTransactionProcesses>
	 *
	 * @throws SQLException
	 */
	public List<VMergeTransactionProcesses> mergTxnProcExecuteQuery(final VMergeTransactionProcesses objSearchDao) {
		final String sql = getQuery("OUIMTSTP_MERGTXNPROC_FIND_V_MERGE_TRANSACTION_PROCESSES");
		final RowMapper<VMergeTransactionProcesses> vMergeRowMaping = Row2BeanRowMapper.makeMapping(sql,
				VMergeTransactionProcesses.class, vTransPrcsMaping);
		final ArrayList<VMergeTransactionProcesses> returnList = (ArrayList<VMergeTransactionProcesses>) namedParameterJdbcTemplate
				.query(sql, createParams("mergeTransactionId", objSearchDao.getMergeTransactionId()), vMergeRowMaping);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public String createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OUIMTSTP_CREATE_FORM_GLOBALS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean), String.class);
	}

}
