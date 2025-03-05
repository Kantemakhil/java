package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.LegalUpdateReasons;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.LegalUpdateUsages;
import net.syscon.s4.inst.legalscreens.maintenance.OimlegsuRepository;

/**
 * Class OimlegsuRepositoryImpl
 */
@Repository
public class OimlegsuRepositoryImpl extends RepositoryBase implements OimlegsuRepository {
	
	private static Logger logger = LogManager.getLogger(OimlegsuRepositoryImpl.class);

	private final Map<String, FieldMapper> legalUpdMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("UPDATE_REASON_CODE", 			new FieldMapper("updateReasonCode"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("ACTIVE_FLAG", 				new FieldMapper("activeFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("LEGAL_CLASS", 				new FieldMapper("legalClass"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("EXPIRY_DATE", 				new FieldMapper("expiryDate"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("GETDESCCODE('LGL_RSN_CAT'", 	new FieldMapper("getdesccode('lglRsnCat'"))
			.put("REASON_CATEGORY", 			new FieldMapper("reasonCategory"))
			.put("GETDESCCODE('ACTIVE_TYPE'", 	new FieldMapper("getdesccode('activeType'"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("UPDATE_REASON_CODE", 			new FieldMapper("updateReasonCode"))
			.put("RC_DESC", 					new FieldMapper("rcDesc"))
			.build();

	/**
	 * Creates new OimlegsuRepositoryImpl class Object
	 */
	public OimlegsuRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            LegalUpdateUsages
	 *
	 * @return List<LegalUpdateUsages>
	 */
	public List<LegalUpdateUsages> lglUpdUsagesExecuteQuery(final LegalUpdateUsages objSearchDao) {
		final String sql = getQuery("OIMLEGSU_LGLUPDUSAGES_FIND_LEGAL_UPDATE_USAGES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		String preSqlQuery = null;
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getLegalClass() != null) {
				sqlQuery.append(" LEGAL_CLASS  = :LEGALCLASS " + " AND ");
				params.addValue("LEGALCLASS", objSearchDao.getLegalClass());
			}
			if (objSearchDao.getUpdateReasonCode() != null) {
				sqlQuery.append(" UPDATE_REASON_CODE  = :UPDATEREASONCODE " + " AND ");
				params.addValue("UPDATEREASONCODE", objSearchDao.getUpdateReasonCode());
			}
			if (objSearchDao.getActiveFlag() != null) {
				if (objSearchDao.getActiveFlag() == "true") {
					objSearchDao.setActiveFlag("Y");
				} else {
					objSearchDao.setActiveFlag("N");
				}
				sqlQuery.append(" ACTIVE_FLAG  = :ACTIVEFLAG " + " AND ");
				params.addValue("ACTIVEFLAG", objSearchDao.getActiveFlag());
			}
			if (objSearchDao.getExpiryDate() != null) {
				sqlQuery.append(" EXPIRY_DATE  = :EXPIRYDATE " + " AND ");
				params.addValue("EXPIRYDATE", objSearchDao.getExpiryDate());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY ACTIVE_FLAG DESC, UPDATE_REASON_CODE ");
		final RowMapper<LegalUpdateUsages> LegalUpdateUsagesRowMapper = Row2BeanRowMapper.makeMapping(preSqlQuery,
				LegalUpdateUsages.class, legalUpdMapping);
		List<LegalUpdateUsages> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, LegalUpdateUsagesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstLegalUpdateUsages
	 *            List<LegalUpdateUsages>
	 *
	 * @return List<Integer>
	 */
	public Integer lglUpdUsagesInsertLegalUpdateUsages(final List<LegalUpdateUsages> lstLegalUpdateUsages) {
		final String sql = getQuery("OIMLEGSU_LGLUPDUSAGES_INSERT_LEGAL_UPDATE_USAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final LegalUpdateUsages legalUpdateUsages : lstLegalUpdateUsages) {
			parameters.add(new BeanPropertySqlParameterSource(legalUpdateUsages));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstLegalUpdateUsages.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstLegalUpdateUsages
	 *            List<LegalUpdateUsages>
	 */
	public Integer lglUpdUsagesUpdateLegalUpdateUsages(final List<LegalUpdateUsages> lstLegalUpdateUsages) {
		final String sql = getQuery("OIMLEGSU_LGLUPDUSAGES_UPDATE_LEGAL_UPDATE_USAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final LegalUpdateUsages legalUpdateUsages : lstLegalUpdateUsages) {
			parameters.add(new BeanPropertySqlParameterSource(legalUpdateUsages));
		}
		try {
			batchUpdatePreDeletedRows("LEGAL_UPDATE_USAGES", "LEGAL_CLASS  = :legalClass AND UPDATE_REASON_CODE  = :updateReasonCode", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in lglUpdUsagesUpdateLegalUpdateUsages"+e);
		}
		
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstLegalUpdateUsages.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstLegalUpdateUsages
	 *            List<LegalUpdateUsages>
	 */
	public LegalUpdateUsages lglUpdUsagesDeleteLegalUpdateUsages(final List<LegalUpdateUsages> lstLegalUpdateUsages) {
		final String sql = getQuery("OIMLEGSU_LGLUPDUSAGES_DELETE_LEGAL_UPDATE_USAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		final LegalUpdateUsages returnData = new LegalUpdateUsages();
		for (final LegalUpdateUsages legalUpdateUsages : lstLegalUpdateUsages) {
			parameters.add(new BeanPropertySqlParameterSource(legalUpdateUsages));
		}
		try {
			String tableName = "LEGAL_UPDATE_USAGES";
			String whereCondition = "LEGAL_CLASS  = :legalClass AND UPDATE_REASON_CODE  = :updateReasonCode";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("OMS_OWNER")) {
				error = error.substring(error.indexOf("OMS_OWNER."), error.indexOf(")")).replaceFirst("OMS_OWNER.", "");
				final String tableName = errorNameValidation(error);
				returnData.setSealFlag(tableName);
				return returnData;
			}
		}
		if (lstLegalUpdateUsages.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}
	}

	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OIMILOCA_CONSTRAINT_VALIDATIONS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CONSTRAINTNAME", errorName),
					String.class);
		} catch (Exception e) {
			returnData = null;
			return returnData;
		}
		return returnData;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgLegalClassRecordGroup() {
		final String sql = getQuery("OIMLEGSU_FIND_RGLEGALCLASS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgIntSchTypeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<LegalUpdateReasons> rgUpdateReasonCodeRecordGroup() {
		final String sql = getQuery("OIMLEGSU_FIND_RGUPDATEREASONCODE");
		final RowMapper<LegalUpdateReasons> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LegalUpdateReasons.class,
				mMapping);
		List<LegalUpdateReasons> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgIntSchTypeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return LegalUpdateUsages
	 */
	public LegalUpdateUsages postQueryData(final String updateReasonCode) {
		final String sql = getQuery("OIMLEGSU_PROCEDURE_DATA");
		final RowMapper<LegalUpdateUsages> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LegalUpdateUsages.class,
				mMapping);
		LegalUpdateUsages returnData = new LegalUpdateUsages();
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("PUPDATEREASONCODE", updateReasonCode), mRowMapper);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public Integer deleteKeyDelRec(final LegalUpdateUsages objSearchDao) {
		final String sql = getQuery("OIMLEGSU_KEY_DEL_REC");
		Integer returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("PREASONCODE",
					objSearchDao.getUpdateReasonCode(), "PLEGALCLASS", objSearchDao.getLegalClass()), Integer.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

}
