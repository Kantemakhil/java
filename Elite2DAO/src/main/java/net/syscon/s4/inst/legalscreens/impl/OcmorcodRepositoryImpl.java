package net.syscon.s4.inst.legalscreens.impl;

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
import net.syscon.s4.im.beans.OffenceResultCodes;
import net.syscon.s4.inst.legalscreens.OcmorcodRepository;

/**
 * Class OcmorcodRepositoryImpl
 */
@Repository
public class OcmorcodRepositoryImpl extends RepositoryBase implements OcmorcodRepository {

	/**
	 * Logger object used to print the log in the file
	 */

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
	 * Creates new OcmorcodRepositoryImpl class Object
	 */
	public OcmorcodRepositoryImpl() {
		// OcmorcodRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenceResultCodes
	 *
	 * @return List<OffenceResultCodes>
	 */
	public List<OffenceResultCodes> resCodExecuteQuery(final OffenceResultCodes objSearchDao) {
		List<OffenceResultCodes> returnList;
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		final String sql = getQuery("OCMORCOD_RESCOD_FIND_OFFENCE_RESULT_CODES");
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE");
			if (objSearchDao.getResultCode() != null) {
				sqlQuery.append(" RESULT_CODE  = :resultCode AND");
				params.addValue("resultCode", objSearchDao.getResultCode());
			}
			if (objSearchDao.getDescription() != null) {
				sqlQuery.append(" DESCRIPTION  = :description AND");
				params.addValue("description", objSearchDao.getDescription());
			}
			if (objSearchDao.getDispositionCode() != null && !"".equals(objSearchDao.getDispositionCode())) {
				sqlQuery.append(" DISPOSITION_CODE  = :dispositionCode AND");
				params.addValue("dispositionCode", objSearchDao.getDispositionCode());
			}
			if (objSearchDao.getChargeStatus() != null && !"".equals(objSearchDao.getChargeStatus())) {
				sqlQuery.append(" CHARGE_STATUS  = :chargeStatus AND");
				params.addValue("chargeStatus", objSearchDao.getChargeStatus());
			}
			if (objSearchDao.getListSeq() != null) {
				sqlQuery.append(" LIST_SEQ  = :listSeq AND");
				params.addValue("listSeq", objSearchDao.getListSeq());
			}
			if (objSearchDao.getActiveFlag() != null) {
				sqlQuery.append(" ACTIVE_FLAG  = :activeFlag AND");
				params.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
			if (objSearchDao.getConvictionFlag() != null) {
				sqlQuery.append(" CONVICTION_FLAG  = :convictionFlag AND");
				params.addValue("convictionFlag", objSearchDao.getConvictionFlag());
			}
			if (objSearchDao.getExpiryDate() != null) {
				sqlQuery.append(" EXPIRY_DATE  = :expiryDate");
				params.addValue("expiryDate", objSearchDao.getExpiryDate());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith(" WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith(" AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY RESULT_CODE ASC");
		final RowMapper<OffenceResultCodes> offResRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenceResultCodes.class, offenceResultCodesMapping);
		returnList = namedParameterJdbcTemplate.query(preparedSql, params, offResRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenceResultCodes
	 *            List<OffenceResultCodes>
	 *
	 * @return List<Integer>
	 */
	public Integer resCodInsertOffenceResultCodes(final List<OffenceResultCodes> lstOffResCodes) {
		final String sql = getQuery("OCMORCOD_RESCOD_INSERT_OFFENCE_RESULT_CODES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenceResultCodes offResCodes : lstOffResCodes) {
			parameters.add(new BeanPropertySqlParameterSource(offResCodes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffResCodes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenceResultCodes
	 *            List<OffenceResultCodes>
	 */
	public Integer resCodUpdateOffenceResultCodes(final List<OffenceResultCodes> lstOffResCodes) {
		final String sql = getQuery("OCMORCOD_RESCOD_UPDATE_OFFENCE_RESULT_CODES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenceResultCodes offResCodes : lstOffResCodes) {
			parameters.add(new BeanPropertySqlParameterSource(offResCodes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffResCodes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	
	public String getChargeStatus(final String chargeCode) {
		final String sql = getQuery("OCMORCOD_GET_CHARGE_STATUS_BASED_ON_RESULT_CODE");
		String returnList =null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("chargeCode",
				chargeCode), String.class);
		return returnList;
	}
	
}
