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
import net.syscon.s4.sa.admin.beans.MergeTransactionLogs;
import net.syscon.s4.sa.admin.mergeoffenders.OuimtlogRepository;

/**
 * Class OuimtlogRepositoryImpl
 * 
 */
@Repository
public class OuimtlogRepositoryImpl extends RepositoryBase implements OuimtlogRepository {

	private final Map<String, FieldMapper> mergeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("LOG_LEVEL", new FieldMapper("logLevel"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MERGE_TRANSACTION_LOG_ID", new FieldMapper("mergeTransactionLogId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("LOG_TIMESTAMP", new FieldMapper("logTimestamp"))
			.put("MERGE_TRANSACTION_ID", new FieldMapper("mergeTransactionId"))
			.put("LOG_TEXT", new FieldMapper("logText"))
			.put("LOG_MSG_PART", new FieldMapper("logMsgPart"))
			.build();

	/**
	 * Creates new OuimtlogRepositoryImpl class Object
	 */
	public OuimtlogRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            MergeTransactionLogs
	 *
	 * @return List<MergeTransactionLogs>
	 *
	 * @throws SQLException
	 */
	public List<MergeTransactionLogs> mergeLogExecuteQuery(final MergeTransactionLogs objSearchDao) {
		final String sql = getQuery("OUIMTLOG_MERGELOG_FIND_MERGE_TRANSACTION_LOGS");
		final RowMapper<MergeTransactionLogs> mergeTransMapper = Row2BeanRowMapper.makeMapping(sql,
				MergeTransactionLogs.class, mergeMapping);
		return (ArrayList<MergeTransactionLogs>) namedParameterJdbcTemplate.query(sql,
				createParams("mergeTransactionLogId", objSearchDao.getMergeTransactionLogId()), mergeTransMapper);
	}
}
