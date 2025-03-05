package net.syscon.s4.inst.legalscreens.impl;

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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenceResultCodes;
import net.syscon.s4.im.beans.Statutes;
import net.syscon.s4.inst.legalscreens.OimstatuRepository;

/**
 * Class OimstatuRepositoryImpl
 */
@Repository
public class OimstatuRepositoryImpl extends RepositoryBase implements OimstatuRepository {

	/**
	 * Creates new OimstatuRepositoryImpl class Object
	 */
	public OimstatuRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimstatuRepositoryImpl.class);
	private final Map<String, FieldMapper> statutesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("ACTIVE_FLAG", 			new FieldMapper("activeFlag"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", 				new FieldMapper("listSeq"))
			.put("STATUTE_CODE", 			new FieldMapper("statuteCode"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("LEGISLATING_BODY_CODE", 	new FieldMapper("legislatingBodyCode"))
			.put("UPDATE_ALLOWED_FLAG", 	new FieldMapper("updateAllowedFlag"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("EXPIRY_DATE", 			new FieldMapper("expiryDate"))
			.put("DESCRIPTION", 			new FieldMapper("description")).build();
	
	private final Map<String, FieldMapper> offenceResultCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("RESULT_CODE", 					new FieldMapper("resultCode"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.put("DISPOSITION_CODE", 				new FieldMapper("dispositionCode"))
			.put("CHARGE_STATUS", 					new FieldMapper("chargeStatus"))
			.put("ACTIVE_FLAG", 					new FieldMapper("activeFlag"))
			.put("EXPIRY_DATE", 					new FieldMapper("expiryDate"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
			.put("CONVICTION_FLAG", 				new FieldMapper("convictionFlag"))
			.put("LIST_SEQ", 						new FieldMapper("listSeq"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Statutes
	 *
	 * @return List<Statutes>
	 */
	public List<Statutes> statExecuteQuery(final Statutes objSearchDao) {
		final String sql = getQuery("OIMSTATU_STAT_FIND_STATUTES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		List<Statutes> returnList;
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE");
			if (objSearchDao.getStatuteCode() != null && !objSearchDao.getStatuteCode().trim().equals("")) {
				sqlQuery.append(" STATUTE_CODE = :statuteCode and");
				params.addValue("statuteCode", objSearchDao.getStatuteCode());
			}
			if (objSearchDao.getDescription() != null && !objSearchDao.getDescription().trim().equals("")) {
				sqlQuery.append(" DESCRIPTION = :description and");
				params.addValue("description", objSearchDao.getDescription());
			}
			if (objSearchDao.getLegislatingBodyCode() != null) {
				sqlQuery.append(" LEGISLATING_BODY_CODE = :legislatingBodyCode and");
				params.addValue("legislatingBodyCode", objSearchDao.getLegislatingBodyCode());
			}
			if (objSearchDao.getListSeq() != null) {
				sqlQuery.append(" LIST_SEQ = :listSeq and");
				params.addValue("listSeq", objSearchDao.getListSeq());
			}
			if (objSearchDao.getActiveFlag() != null) {
				if ("true".equals(objSearchDao.getActiveFlag())) {
					objSearchDao.setActiveFlag("Y");
				} else {
					objSearchDao.setActiveFlag("N");
				}
				sqlQuery.append(" ACTIVE_FLAG  = :activeFlag and");
				params.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
			if (objSearchDao.getExpiryDate() != null) {
				sqlQuery.append(" EXPIRY_DATE  = :expiryDate");
				params.addValue("expiryDate", objSearchDao.getExpiryDate());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<Statutes> statutesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, Statutes.class,
				statutesMapping);
		returnList = namedParameterJdbcTemplate.query(preparedSql, params, statutesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstStatutes
	 *            List<Statutes>
	 *
	 * @return List<Integer>
	 */
	public Statutes statInsertStatutes(final List<Statutes> lstStatutes) {
		final String sql = getQuery("OIMSTATU_STAT_INSERT_STATUTES");
		final Statutes returnData = new Statutes();
		int[] returnArray = new int[]{};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final Statutes statutes : lstStatutes) {
			parameters.add(new BeanPropertySqlParameterSource(statutes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			final String errorMsg = "Error : " + e.getMessage();
			returnData.setErrorMessage(errorMsg.toUpperCase());
			logger.error("Exception : OIMSTATU", e);
		}
		if (lstStatutes.size() == returnArray.length) {
			returnData.setSealFlag("1");
		} else {
			returnData.setSealFlag("0");
		}
		return returnData;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstStatutes
	 *            List<Statutes>
	 */
	public Statutes statUpdateStatutes(final List<Statutes> lstStatutes) {
		final Statutes returnData = new Statutes();
		final String sql = getQuery("OIMSTATU_STAT_UPDATE_STATUTES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final Statutes statutes : lstStatutes) {
			parameters.add(new BeanPropertySqlParameterSource(statutes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			final String error = "Error : " + e.getMessage().toUpperCase();
			if (error.contains("OFN_STT_F1")) {
				  returnData.setSealFlag("STATUTE_CODE");
				returnData.setStatuteCode("2291");
				return returnData;
			}
		}
		if (lstStatutes.size() == returnArray.length) {
			returnData.setSealFlag("1");
		} else {
			returnData.setSealFlag("0");
		}
		return returnData;
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstStatutes
	 *            List<Statutes>
	 *
	 */
	public Statutes statDeleteStatutes(final List<Statutes> lstStatutes) {
		final Statutes returnData = new Statutes();
		final String sql = getQuery("OIMSTATU_STAT_DELETE_STATUTES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final Statutes statutes : lstStatutes) {
			parameters.add(new BeanPropertySqlParameterSource(statutes));
		}
		try {
			batchUpdatePreDeletedRows("STATUTES", "ROW_ID = :rowId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in deleteOffederCondTransfer"+e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			final String error = "Error : " + e.getMessage().toUpperCase();
			if (error.contains("OFN_STT_F1")) {
				returnData.setSealFlag("OFFENCES");
				returnData.setStatuteCode("2292");
				return returnData;
			}
		}
		if (lstStatutes.size() == returnArray.length) {
			returnData.setSealFlag("1");
		} else {
			returnData.setSealFlag("0");
		}
		return returnData;

	}

	/**
	 * Error name validation.
	 *
	 * @param errorName
	 *            the error name
	 * @return the string
	 */
	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OIMSTATU_CONSTRAINT_VALIDATIONS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CONSTRAINTNAME", errorName),
					String.class);
		} catch (final Exception e) {
			returnData = null;
			return returnData;
		}
		return returnData;
	}
	
	public List<OffenceResultCodes> getInactiveStatutes(final OffenceResultCodes objSearchDao) {
		final String sql = getQuery("OIMSTATU_GET_INACTIVE_STATUTES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		List<OffenceResultCodes> returnList;
		sqlQuery.append(sql);
		preparedSql = sqlQuery.toString().trim();
		final RowMapper<OffenceResultCodes> statutesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, OffenceResultCodes.class,
				offenceResultCodesMapping);
		returnList = namedParameterJdbcTemplate.query(preparedSql, params, statutesRowMapper);
		return returnList;
	}

}
