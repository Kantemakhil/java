package net.syscon.s4.sa.audit.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.sa.audit.JournalTableView;
import net.syscon.s4.sa.audit.OmsaljntRepository;

/**
 * Class OmsaljntRepositoryImpl
 */
@Repository
public class OmsaljntRepositoryImpl extends RepositoryBase implements OmsaljntRepository {

	private final Map<String, FieldMapper> journalTableViewMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'Y'", 				new FieldMapper("'y'"))
			.put("TABLE_NAME", 			new FieldMapper("tableName"))
			.put("STATUS", 				new FieldMapper("status"))
			.put("DECODE(STATUS", 		new FieldMapper(" decode(status"))
			.put("'ENABLED'", 			new FieldMapper("'enabled'"))
			.put("'N')", 				new FieldMapper("'n') "))
			.build();

	/**
	 * Creates new OmsaljntRepositoryImpl class Object
	 */
	public OmsaljntRepositoryImpl() {
		// OmsaljntRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao JournalTableView
	 *
	 * @return List<JournalTableView>
	 */
	public List<JournalTableView> journalTableViewExecuteQuery(final JournalTableView journalTableView) {
		final String sql = getQuery("OMSALJNT_JOURNALTABLEVIEW_FIND_JOURNAL_TABLE_VIEW");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (journalTableView != null) {
			sqlQuery.append(" WHERE ");
			if (journalTableView.getTableName() != null && !journalTableView.getTableName().equals("")) {
				sqlQuery.append(" TABLE_NAME  = :tableName " + " AND ");
				params.addValue("tableName", journalTableView.getTableName());
			}
			if (journalTableView.getStatus() != null && !journalTableView.getStatus().equals("")) {
				sqlQuery.append(" STATUS  = :status " + " AND ");
				params.addValue("status", journalTableView.getStatus());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY TABLE_NAME");
		final RowMapper<JournalTableView> JournalTableViewRowMapper = Row2BeanRowMapper.makeMapping(preSqlQuery,
				JournalTableView.class, journalTableViewMapping);
		return namedParameterJdbcTemplate.query(preSqlQuery, params, JournalTableViewRowMapper);
	}

		/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstJournalTableView List<JournalTableView>
	 */
	public Integer journalTableViewUpdateJournalTableView(final List<JournalTableView> lstJournalTableView) {
		String sql = getQuery("OMSALJNT_JOURNALTABLEVIEW_UPDATE_JOURNAL_TABLE_VIEW");
		for (JournalTableView journalTableView : lstJournalTableView) {
			namedParameterJdbcTemplate.execute(sql+journalTableView.getStatus(),new PreparedStatementCallback<Boolean>() {
			    @Override
			    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
			        return ps.execute();
			    }	
			});
		}
	return 1;
	}

	
}
