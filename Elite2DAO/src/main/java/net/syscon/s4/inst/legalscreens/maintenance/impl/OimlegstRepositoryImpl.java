package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legalscreens.maintenance.OimlegstRepository;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;
import net.syscon.s4.inst.legalscreens.maintenance.bean.TableColumnNameBean;

/**
 * Class OimlegstRepositoryImpls
 */
@Repository
public class OimlegstRepositoryImpl extends RepositoryBase implements OimlegstRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimlegstRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> lglUpdRsnMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("UPDATE_REASON_CODE", 					new FieldMapper("updateReasonCode"))
			.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("ACTIVE_TYPE", 						new FieldMapper("activeType"))
			.put("LIST_SEQ", 							new FieldMapper("listSeq"))
			.put("REASON_CATEGORY", 					new FieldMapper("reasonCategory"))
			.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
			.put("EFFECTIVE_DATE", 						new FieldMapper("effectiveDate"))
			.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
			.put("EXPIRY_DATE", 						new FieldMapper("expiryDate"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("CODE", 								new FieldMapper("code"))
			.build();
	private final Map<String, FieldMapper> parentMaqpping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TABLE_NAME", 							new FieldMapper("tableName"))
			.put("COLUMN_NAME", 						new FieldMapper("columnName"))
			.build();

	public OimlegstRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            LegalUpdateReasons
	 *
	 * @return List<LegalUpdateReasons>
	 */

	public List<LegalUpdateReasons> updateReasonsExecuteQuery(final LegalUpdateReasons objSearchDao) {
		final String sql = getQuery("OIMLEGST_UPDATEREASONS_FIND_LEGAL_UPDATE_REASONS");
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		final MapSqlParameterSource param = new MapSqlParameterSource();
		if (objSearchDao != null) {
			pSql.append(" WHERE ");
			if (objSearchDao.getUpdateReasonCode() != null && !objSearchDao.getUpdateReasonCode().isEmpty() && !objSearchDao.getUpdateReasonCode().trim().equals("")) {
				pSql.append(" UPDATE_REASON_CODE LIKE :updateReasonCode  AND ");
				param.addValue("updateReasonCode", objSearchDao.getUpdateReasonCode().trim());
			}
			if (objSearchDao.getDescription() != null && !objSearchDao.getDescription().isEmpty() && !objSearchDao.getDescription().trim().equals("")) {
				pSql.append(" DESCRIPTION LIKE :description AND ");
				param.addValue("description", objSearchDao.getDescription().trim());
			}
			if (objSearchDao.getEffectiveDate() != null) {
				pSql.append("EFFECTIVE_DATE = :effectiveDate AND ");
				param.addValue("effectiveDate", objSearchDao.getEffectiveDate());
			}

			if (objSearchDao.getReasonCategory() != null && !objSearchDao.getReasonCategory().isEmpty()) {
				pSql.append(" REASON_CATEGORY LIKE :reasonCategory AND ");
				param.addValue("reasonCategory", objSearchDao.getReasonCategory());
			}

			if (objSearchDao.getActiveType() != null && !objSearchDao.getActiveType().isEmpty()) {
				pSql.append(" ACTIVE_TYPE LIKE :activeType AND ");
				param.addValue("activeType", objSearchDao.getActiveType());
			}

			if (objSearchDao.getListSeq() != null) {
				pSql.append(" LIST_SEQ LIKE :listSeq AND ");
				param.addValue("listSeq", objSearchDao.getListSeq());
			}
			if (objSearchDao.getActiveFlag() != null && !objSearchDao.getActiveFlag().isEmpty()) {
				if ("true".equals(objSearchDao.getActiveFlag())) {
					objSearchDao.setActiveFlag("Y");
				} else {
					objSearchDao.setActiveFlag("N");
				}
				pSql.append(" ACTIVE_FLAG LIKE :activeFlag AND ");
				param.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
			if (objSearchDao.getExpiryDate() != null) {
				pSql.append("EXPIRY_DATE = :expiryDate AND ");
				param.addValue("expiryDate", objSearchDao.getExpiryDate());
			}
		}
		preparedSql = pSql.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" order by active_flag desc, update_reason_code ");
		final RowMapper<LegalUpdateReasons> LegalUpdateReasonsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				LegalUpdateReasons.class, lglUpdRsnMapping);
		List<LegalUpdateReasons> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, param, LegalUpdateReasonsRowMapper);
		} catch (Exception e) {
			logger.error("updateReasonsExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstLegalUpdateReasons
	 *            List<LegalUpdateReasons>
	 *
	 * @return List<Integer>
	 */
	public Integer updateReasonsInsertLegalUpdateReasons(final List<LegalUpdateReasons> lstLegalUpdateReasons) {
		String sql = getQuery("OIMLEGST_UPDATEREASONS_INSERT_LEGAL_UPDATE_REASONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (LegalUpdateReasons legalUpdateReasons : lstLegalUpdateReasons) {
			parameters.add(new BeanPropertySqlParameterSource(legalUpdateReasons));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstLegalUpdateReasons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstLegalUpdateReasons
	 *            List<LegalUpdateReasons>
	 */
	public Integer updateReasonsUpdateLegalUpdateReasons(final List<LegalUpdateReasons> lstLegalUpdateReasons) {
		String sql = getQuery("OIMLEGST_UPDATEREASONS_UPDATE_LEGAL_UPDATE_REASONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (LegalUpdateReasons legalUpdateReasons : lstLegalUpdateReasons) {
			parameters.add(new BeanPropertySqlParameterSource(legalUpdateReasons));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstLegalUpdateReasons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstLegalUpdateReasons
	 *            List<LegalUpdateReasons>
	 */
	public Integer updateReasonsDeleteLegalUpdateReasons(final List<LegalUpdateReasons> lstLegalUpdateReasons) {
		String sql = getQuery("OIMLEGST_UPDATEREASONS_DELETE_LEGAL_UPDATE_REASONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (LegalUpdateReasons legalUpdateReasons : lstLegalUpdateReasons) {
			parameters.add(new BeanPropertySqlParameterSource(legalUpdateReasons));
		}
		try {
			batchUpdatePreDeletedRows("LEGAL_UPDATE_REASONS", "UPDATE_REASON_CODE  = :updateReasonCode", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in updateReasonsDeleteLegalUpdateReasons"+e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstLegalUpdateReasons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgCatRecordGroup() {
		final String sql = getQuery("OIMLEGST_FIND_RGCAT");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				lglUpdRsnMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : Oimlegst:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgStatRecordGroup() {
		final String sql = getQuery("OIMLEGST_FIND_RGSTAT");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				lglUpdRsnMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : Oimlegst:", e);
			return Collections.emptyList();
		}
	}
	/**
	 * Used to to get the Nbt Rason Query
	 * 
	 * @return LegalUpdateReasons
	 */

	public LegalUpdateReasons getNbtReasonCategory(final LegalUpdateReasons legalUpdateReason) {
		final String sql = getQuery("OIMLEGST_GET_NBT_REASON_CATEGORY");
		try {
			String nbtReasonCategory = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("REASONCATEGORY", legalUpdateReason.getReasonCategory()), String.class);
			legalUpdateReason.setNbtReasonCategory(nbtReasonCategory);
		} catch (Exception e) {
			logger.error("setNbtStatus", e);
		}
		return legalUpdateReason;
	}
	/**
	 * Used to to get the getNbtActiveType
	 * 
	 * @return LegalUpdateReasons
	 */
	public LegalUpdateReasons getNbtActiveType(final LegalUpdateReasons legalUpdateReason) {
		final String sql = getQuery("OIMLEGST_GET_NBT_ACTIVE_TYPE");
		try {
			String nbtActiveType = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("ACTIVETYPE", legalUpdateReason.getActiveType()), String.class);
			legalUpdateReason.setNbtActiveType(nbtActiveType);
		} catch (Exception e) {
			logger.error("setNbtStatus", e);
		}
		return legalUpdateReason;
	}

	/**
	 * Performing to get the parent tables list
	 */
	public List<TableColumnNameBean> getTableColumNames() {
		final String sql = getQuery("OIMLEGST_GET_LISTPARENT_TABLES");
		final RowMapper<TableColumnNameBean> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TableColumnNameBean.class,
				parentMaqpping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Performing the if the record is to delete the child record or not count
	 */
	public Integer senCalcKeyDelrec(final String tableName,final String columnName,final String updateReasonCode) {
		final String sql = "SELECT COUNT(*) FROM  "+tableName+" WHERE "+columnName + "=:updateReasonCode  limit 1 ";
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("updateReasonCode", updateReasonCode), Integer.class);
	}
}
