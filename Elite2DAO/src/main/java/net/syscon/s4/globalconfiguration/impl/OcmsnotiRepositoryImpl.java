package net.syscon.s4.globalconfiguration.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globalconfiguration.OcmsnotiRepository;
import net.syscon.s4.im.beans.SanctionNotices;

/**
 * Class OcmsnotiRepositoryImpl
 */
@Repository
public class OcmsnotiRepositoryImpl extends RepositoryBase implements OcmsnotiRepository {

	

	/**
	 * Logger object used to print the log in the file
	 */
	
	private final Map<String, FieldMapper> sanctionNoticesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SANCTION_NOTICE_CODE", new FieldMapper("sanctionNoticeCode"))
			.put("LATE_DAYS", new FieldMapper("lateDays"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("SEQ_NUM", new FieldMapper("seqNum"))
			.put("UPDATE_ALLOWED_FLAG", new FieldMapper("updateAllowedFlag"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))

			.build();

	
	/**
	 * Creates new OcmsnotiRepositoryImpl class Object
	 */
	public OcmsnotiRepositoryImpl() {
		//OcmsnotiRepositoryImpl
	}
	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SanctionNotices
	 *
	 * @return List<SanctionNotices>
	 *
	 * @throws SQLException
	 */
	public List<SanctionNotices> sanNotExecuteQuery(final SanctionNotices objSearchDao) {
		final String sql = getQuery("OCMSNOTI_SANNOT_FIND_SANCTION_NOTICES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		List<SanctionNotices> returnList;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE");
			if (objSearchDao.getSanctionNoticeCode() != null
					&& !objSearchDao.getSanctionNoticeCode().trim().equals("")) {
				sqlQuery.append(" SANCTION_NOTICE_CODE = :sanctionNoticeCode and");
				params.addValue("sanctionNoticeCode", objSearchDao.getSanctionNoticeCode());
			}
			if (objSearchDao.getDescription() != null
					&& !objSearchDao.getDescription().trim().equals("")) {
				sqlQuery.append(" DESCRIPTION = :description and");
				params.addValue("description", objSearchDao.getDescription());
			}
			if (objSearchDao.getIssuePeriod()!=null ) {
				sqlQuery.append(" LATE_DAYS = :lateDays and");
				params.addValue("lateDays", objSearchDao.getLateDays());
			}
			if (objSearchDao.getSeqNum()!=null) {
				sqlQuery.append(" SEQ_NUM = :seqNum and");
				params.addValue("seqNum", objSearchDao.getSeqNum());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat("  ORDER BY SANCTION_NOTICE_CODE ,SEQ_NUM,LATE_DAYS ");
		final RowMapper<SanctionNotices> sanctionNoticesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				SanctionNotices.class, sanctionNoticesMapping);
		returnList = (ArrayList<SanctionNotices>) namedParameterJdbcTemplate.query(preparedSql, params,
				sanctionNoticesRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstSanctionNotices
	 *            List<SanctionNotices>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer sanNotInsertSanctionNotices(final List<SanctionNotices> lstSanctionNotices) {
		final String sql = getQuery("OCMSNOTI_SANNOT_INSERT_SANCTION_NOTICES");
		int[] returnArray ;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final SanctionNotices sanction : lstSanctionNotices) {
			parameters.add(new BeanPropertySqlParameterSource(sanction));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSanctionNotices.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstSanctionNotices
	 *            List<SanctionNotices>
	 *
	 * @throws SQLException
	 */
	public Integer sanNotUpdateSanctionNotices(final List<SanctionNotices> lstSanctionNotices) {
		final String sql = getQuery("OCMSNOTI_SANNOT_UPDATE_SANCTION_NOTICES");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final SanctionNotices sanctionNotices : lstSanctionNotices) {
			parameters.add(new BeanPropertySqlParameterSource(sanctionNotices));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSanctionNotices.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	

	

}
