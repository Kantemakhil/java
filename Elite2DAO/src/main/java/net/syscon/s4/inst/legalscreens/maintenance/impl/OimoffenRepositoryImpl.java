package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Offence;
import net.syscon.s4.common.beans.OffenceByStatute;
import net.syscon.s4.common.beans.OffenceIndicator;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.Statutes;
import net.syscon.s4.inst.legalscreens.maintenance.OimoffenRepository;
import net.syscon.s4.inst.legalscreens.maintenance.bean.AllowableOffenceTypes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.HoCodes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenderCharges;

/**
 * Class OimoffenRepositoryImpl
 * 
 */
@Repository
public class OimoffenRepositoryImpl extends RepositoryBase implements OimoffenRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimoffenRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> allowableOffenceTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENCE_TYPE",                              new FieldMapper("offenceType"))
			.put("SEAL_FLAG",							      new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 					      new FieldMapper("createDatetime"))
			.put("'X'",									      new FieldMapper("'x'"))
			.put("MODIFY_DATETIME",					          new FieldMapper("modifyDatetime"))
			.put("INDICATOR_CODE",			                  new FieldMapper("indicatorCode"))
			.put("STATUTE_CODE",							  new FieldMapper("statuteCode"))
			.put("OFFENCE_CODE", 				              new FieldMapper("offenceCode"))
			.put("OFFENCE_ID",								  new FieldMapper("offenceId"))
			.build();
	private final Map<String, FieldMapper> offencesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'X'", 										 new FieldMapper("'x'"))
			.put("UPDATE_ALLOWED_FLAG",							 new FieldMapper("updateAllowedFlag"))
			.put("CHECK_BOX2",									 new FieldMapper("checkBox2"))
			.put("CREATE_DATE",									 new FieldMapper("createDate"))
			.put("EXPIRY_DATE", 								 new FieldMapper("expiryDate"))
			.put("OFFENCE_CODE",								 new FieldMapper("offenceCode"))
			.put("HO_CODE", 									 new FieldMapper("hoCode"))
			.put("STATUTE_CODE",								 new FieldMapper("statuteCode"))
			.put("OFFENCE_ID",									 new FieldMapper("offenceId"))
			.build();
	private final Map<String, FieldMapper> statutesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID",							 new FieldMapper("createUserId"))
			.put("SEAL_FLAG", 								 new FieldMapper("sealFlag"))
			.put("ACTIVE_FLAG",								 new FieldMapper("activeFlag"))
			.put("CREATE_DATETIME",							 new FieldMapper("createDatetime"))
			.put("LEGISLATING_BODY_CODE", 					 new FieldMapper("legislatingBodyCode"))
			.put("MODIFY_USER_ID",							 new FieldMapper("modifyUserId"))
			.put("LIST_SEQ",								 new FieldMapper("listSeq"))
			.put("UPDATE_ALLOWED_FLAG",						 new FieldMapper("updateAllowedFlag"))
			.put("MODIFY_DATETIME",							 new FieldMapper("modifyDatetime"))
			.put("STATUTE_CODE",							 new FieldMapper("statuteCode"))
			.put("EXPIRY_DATE",								 new FieldMapper("expiryDate"))
			.put("DESCRIPTION",								 new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> offenceIndicatorsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG",									 new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",								 new FieldMapper("createDatetime"))
			.put("'X'",											 new FieldMapper("'x'"))
			.put("MODIFY_DATETIME",								 new FieldMapper("modifyDatetime"))
			.put("OFFENCE_INDICATOR_ID",						 new FieldMapper("offenceIndicatorId"))
			.put("INDICATOR_CODE",								 new FieldMapper("indicatorCode"))
			.put("OFFENCE_CODE", 								 new FieldMapper("offenceCode"))
			.put("STATUTE_CODE",								 new FieldMapper("statuteCode"))
			.put("OFFENCE_ID",									 new FieldMapper("offenceId"))
			.put("INDICATOR_CODE_DESCRIPTION",					 new FieldMapper("indicatorCodeDescription"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 							 new FieldMapper("code"))
			.put("HO_CODE", 						 new FieldMapper("hoCode"))
			.put("OFFENCE_CODE", 					 new FieldMapper("offenceCode"))
			.put("STATUTE_CODE", 					 new FieldMapper("statuteCode"))
			.put("DESCRIPTION", 					 new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> offenderChargesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENCE_CODE",        		     new FieldMapper("offenceCode"))
			.put("STATUTE_CODE", 					 new FieldMapper("statuteCode"))
			.build();
	private final Map<String, FieldMapper> dualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("Dual",                             new FieldMapper("dual"))
			.build();
	private final Map<String, FieldMapper> offenceByStatuteMapping = new ImmutableMap.Builder<String, FieldMapper>()
	        .put("CODE",                            new FieldMapper("code"))
	        .put("DESCRIPTION",                     new FieldMapper("description"))
	        .put("ACT",                             new FieldMapper("act"))
	        .put("LEGISLATIVE_BODY",                new FieldMapper("legislativeBody"))
	        .put("HO_CODE",                			new FieldMapper("hoCode"))
	        .put("SEVERITY_RANKING",                new FieldMapper("severityRanking"))
	        .put("OFFENCE_ID",						new FieldMapper("offenceId"))
	        .put("ACTIVE_FLAG",						new FieldMapper("activeFlag"))
	        .build();

	/**
	 * Creates new OimoffenRepositoryImpl class Object
	 */
	public OimoffenRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Statutes
	 *
	 * @return List<Statutes>
	 *
	 */
	public List<Statutes> statExecuteQuery(final Statutes objSearchDao) {
		final String sql = getQuery("OIMOFFEN_STAT_FIND_STATUTES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getStatuteCode() != null) {
				sqlQuery.append(" STATUTE_CODE  = :statuteCode " + " AND ");
				params.addValue("statuteCode", objSearchDao.getStatuteCode());
			}
			sqlQuery.append(" ACTIVE_FLAG = 'Y' ");
			if (objSearchDao.getSealFlag() != null) {
				sqlQuery.append(" AND LEGISLATING_BODY_CODE IN (SELECT code FROM reference_codes "
						+ " WHERE domain = 'LEGISL_BODY' AND DESCRIPTION LIKE :LEGISLATINGID");
				params.addValue("LEGISLATINGID", objSearchDao.getSealFlag());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY LIST_SEQ, STATUTE_CODE ");
		final RowMapper<Statutes> StatutesRowMapper = Row2BeanRowMapper.makeMapping(preSqlQuery, Statutes.class,
				statutesMapping);
		List<Statutes> returnList = new ArrayList<Statutes>();
		try {
			returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, StatutesRowMapper);
		} catch (Exception e) {
			logger.error("statExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *
	 * @return List<Offences>
	 *
	 */
	public List<Offence> ofnExecuteQuery(final Offence objSearchDao) {
		final String sql = getQuery("OIMOFFEN_OFN_FIND_OFFENCES");
		final RowMapper<Offence> OffencesRowMapper = Row2BeanRowMapper.makeMapping(sql, Offence.class, offencesMapping);
		List<Offence> returnList = new ArrayList<Offence>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("STATUTECODE", objSearchDao.getStatuteCode()), OffencesRowMapper);
		} catch (Exception e) {
			logger.error("ofnExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffences
	 *            List<Offences>
	 *
	 * @return List<Integer>
	 *
	 */
	public Offence ofnInsertOffences(final List<Offence> lstOffences) {
		String sql = getQuery("OIMOFFEN_OFN_INSERT_OFFENCES");
		int[] returnArray = new int[] {};
		Offence returnData = new Offence();
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Offence offence : lstOffences) {
			parameters.add(new BeanPropertySqlParameterSource(offence));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("OMS_OWNER")) {
				error = error.substring(error.indexOf("OMS_OWNER."), error.indexOf(")")).replaceFirst("OMS_OWNER.", "");
				final String tableName = errorNameValidation(error);
				returnData.setSealFlag(tableName);
				returnData.setListSeq(BigDecimal.valueOf(2291));
				return returnData;
			}
		}
		if (lstOffences.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffences
	 *            List<Offences>
	 *
	 */
	public Integer ofnUpdateOffences(final List<Offence> lstOffences) {
		String sql = getQuery("OIMOFFEN_OFN_UPDATE_OFFENCES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Offence offences : lstOffences) {
			parameters.add(new BeanPropertySqlParameterSource(offences));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffences.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffences
	 *            List<Offences>
	 *
	 */
	public Offence ofnDeleteOffences(final List<Offence> lstOffences) {
		String sql = getQuery("OIMOFFEN_OFN_DELETE_OFFENCES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		Offence returnData = new Offence();
		for (Offence offences : lstOffences) {
			parameters.add(new BeanPropertySqlParameterSource(offences));
		}
		try {
			batchUpdatePreDeletedRows("OFFENCES", "STATUTE_CODE=:statuteCode  and OFFENCE_CODE  = :offenceCode and OFFENCE_ID = :offenceId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in ofnDeleteOffences"+e);
		}
		
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("OMS_OWNER")) {
				error = error.substring(error.indexOf("OMS_OWNER."), error.indexOf(")")).replaceFirst("OMS_OWNER.", "");
				final String tableName = errorNameValidation(error);
				returnData.setSealFlag(tableName);
				returnData.setListSeq(BigDecimal.valueOf(2292));
				return returnData;
			}
		}
		if (lstOffences.size() == returnArray.length) {
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
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AllowableOffenceTypes
	 *
	 * @return List<AllowableOffenceTypes>
	 *
	 */
	public List<AllowableOffenceTypes> alwOtExecuteQuery(final AllowableOffenceTypes objSearchDao) {
		final String sql = getQuery("OIMOFFEN_ALWOT_FIND_ALLOWABLE_OFFENCE_TYPES");
		final RowMapper<AllowableOffenceTypes> AllowableOffenceTypesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AllowableOffenceTypes.class, allowableOffenceTypesMapping);
		List<AllowableOffenceTypes> returnList = new ArrayList<AllowableOffenceTypes>();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		try {
			if(objSearchDao != null) {
				if(objSearchDao.getOffenceId()!= null) {
					sqlQuery.append(" WHERE  OFFENCE_ID = :OFFENCEID ");
					params.addValue("OFFENCEID", objSearchDao.getOffenceId());
					preparedSql = sqlQuery.toString().trim();
				} else {
					final StringBuffer preSubSql = new StringBuffer();
					preSubSql.append("select offence_id from OFFENCES o WHERE ");
					if(objSearchDao.getStatuteCode()!= null) {
						preSubSql.append("o.statute_code = :statuteCode AND ");
						params.addValue("statuteCode", objSearchDao.getStatuteCode());
					}
					if(objSearchDao.getStatuteCode()!= null) {
						preSubSql.append("o.offence_code = :offenceCode AND ");
						params.addValue("offenceCode", objSearchDao.getOffenceCode());
					}
					String subSql  = preSubSql.toString().trim();
					if (subSql.endsWith("WHERE")) {
						subSql = subSql.substring(0, subSql.length() - 5);
					}
					if (subSql.endsWith("AND")) {
						subSql = subSql.substring(0, subSql.length() - 3);
					}
					if(!subSql.isEmpty()) {
						sqlQuery.append(" WHERE OFFENCE_ID in  ( ");
						sqlQuery.append(subSql + ")");
						preparedSql = sqlQuery.toString().trim();
					} else {
						preparedSql = sql;
					}
				}
			}
				returnList = namedParameterJdbcTemplate.query(preparedSql,params,AllowableOffenceTypesRowMapper);
		} catch (Exception e) {
			logger.error("alwOtExecuteQuery", e);
		}

		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstAllowableOffenceTypes
	 *            List<AllowableOffenceTypes>
	 *
	 * @return List<Integer>
	 */
	public Integer alwOtInsertAllowableOffenceTypes(final List<AllowableOffenceTypes> lstAllowableOffenceTypes) {
		String sql = getQuery("OIMOFFEN_ALWOT_INSERT_ALLOWABLE_OFFENCE_TYPES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AllowableOffenceTypes allowableOffenceTypes : lstAllowableOffenceTypes) {
			parameters.add(new BeanPropertySqlParameterSource(allowableOffenceTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAllowableOffenceTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAllowableOffenceTypes
	 *            List<AllowableOffenceTypes>
	 *
	 */
	public Integer alwOtUpdateAllowableOffenceTypes(final List<AllowableOffenceTypes> lstAllowableOffenceTypes) {
		String sql = getQuery("OIMOFFEN_ALWOT_UPDATE_ALLOWABLE_OFFENCE_TYPES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (AllowableOffenceTypes allowableOffenceTypes : lstAllowableOffenceTypes) {
			parameters.add(new BeanPropertySqlParameterSource(allowableOffenceTypes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error(this.getClass().getName() + " error in alwOtUpdateAllowableOffenceTypes " + e);
			if(e!=null && e.getMessage()!=null && e.getMessage().contains("allowable_offence_types_pk")){
				return 18;
			}
		}
		if (lstAllowableOffenceTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAllowableOffenceTypes
	 *            List<AllowableOffenceTypes>
	 *
	 */
	public Integer alwOtDeleteAllowableOffenceTypes(final List<AllowableOffenceTypes> lstAllowableOffenceTypes) {
		String sql = getQuery("OIMOFFEN_ALWOT_DELETE_ALLOWABLE_OFFENCE_TYPES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (AllowableOffenceTypes allowableOffenceTypes : lstAllowableOffenceTypes) {
			parameters.add(new BeanPropertySqlParameterSource(allowableOffenceTypes));
		}
		try {
			batchUpdatePreDeletedRows("ALLOWABLE_OFFENCE_TYPES", "OFFENCE_TYPE  = :offenceType and OFFENCE_ID = :offenceId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in alwOtDeleteAllowableOffenceTypes"+e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAllowableOffenceTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenceIndicators
	 *
	 * @return List<OffenceIndicators>
	 *
	 */
	public List<OffenceIndicator> offIndExecuteQuery(final OffenceIndicator objSearchDao) {
		final String sql = getQuery("OIMOFFEN_OFFIND_FIND_OFFENCE_INDICATORS");
		final RowMapper<OffenceIndicator> OffenceIndicatorsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenceIndicator.class, offenceIndicatorsMapping);
		List<OffenceIndicator> returnList = new ArrayList<OffenceIndicator>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("OFFENCECODE",
					objSearchDao.getOffenceCode(), "STATUTECODE", objSearchDao.getStatuteCode(), "OFFENCEID", objSearchDao.getOffenceId()),
					OffenceIndicatorsRowMapper);
		} catch (Exception e) {
			logger.error("offIndExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenceIndicators
	 *            List<OffenceIndicators>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer offIndInsertOffenceIndicators(final List<OffenceIndicator> lstOffenceIndicators) {
		String sql = getQuery("OIMOFFEN_OFFIND_INSERT_OFFENCE_INDICATORS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenceIndicator offenceIndicator : lstOffenceIndicators) {
			parameters.add(new BeanPropertySqlParameterSource(offenceIndicator));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenceIndicators.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenceIndicators
	 *            List<OffenceIndicators>
	 *
	 */
	public Integer offIndUpdateOffenceIndicators(final List<OffenceIndicator> lstOffenceIndicators) {
		String sql = getQuery("OIMOFFEN_OFFIND_UPDATE_OFFENCE_INDICATORS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenceIndicator offenceIndicators : lstOffenceIndicators) {
			parameters.add(new BeanPropertySqlParameterSource(offenceIndicators));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenceIndicators.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenceIndicators
	 *            List<OffenceIndicators>
	 *
	 */
	public Integer offIndDeleteOffenceIndicators(final List<OffenceIndicator> lstOffenceIndicators) {
		String sql = getQuery("OIMOFFEN_OFFIND_DELETE_OFFENCE_INDICATORS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenceIndicator offenceIndicators : lstOffenceIndicators) {
			parameters.add(new BeanPropertySqlParameterSource(offenceIndicators));
		}
		try {
			batchUpdatePreDeletedRows("OFFENCE_INDICATORS", "OFFENCE_INDICATOR_ID  = :offenceIndicatorId  and OFFENCE_ID = :offenceId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in offIndDeleteOffenceIndicators"+e);
		}
		
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenceIndicators.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOfnSeverityRankingRecordGroup() {
		final String sql = getQuery("OIMOFFEN_FIND_CGFKOFNSEVERITYRANKING");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkAlwOtOffenceTypeRecordGroup() {
		final String sql = getQuery("OIMOFFEN_FIND_CGFKALWOTOFFENCETYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> offIndLovRecordGroup() {
		final String sql = getQuery("OIMOFFEN_FIND_OFFINDLOV");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> ofnHoOffSubclRecordGroup() {
		final String sql = getQuery("OIMOFFEN_FIND_OFNHOOFFSUBCL");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Statutes> statStatutesCodeRecordGroup() {
		final String sql = getQuery("OIMOFFEN_FIND_STATSTATUTESCODE");
		final RowMapper<Statutes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Statutes.class, mMapping);
		List<Statutes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("statStatutesCodeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<HoCodes> rgHoCodeRecordGroup() {
		final String sql = getQuery("OIMOFFEN_FIND_RGHOCODE");
		final RowMapper<HoCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, HoCodes.class, mMapping);
		List<HoCodes> returnList = new ArrayList<HoCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgHoCodeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * statOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public Offence statOnCheckDeleteMaster(final Offence paramBean) {
		final String sql = getQuery("OIMOFFEN_STAT_ONCHECKDELETEMASTER");
		final RowMapper<Offence> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Offence.class, offencesMapping);
		Offence returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * ofnOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public AllowableOffenceTypes ofnOnCheckDeleteMaster(final AllowableOffenceTypes paramBean) {
		final String sql = getQuery("OIMOFFEN_OFN_ONCHECKDELETEMASTER");
		final RowMapper<AllowableOffenceTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AllowableOffenceTypes.class, allowableOffenceTypesMapping);
		AllowableOffenceTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * ofnOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenceIndicator> ofnOnCheckDeleteMaster(final OffenceIndicator paramBean) {
		final String sql = getQuery("OIMOFFEN_OFN_ONCHECKDELETEMASTER");
		final RowMapper<OffenceIndicator> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenceIndicator.class,
				offenceIndicatorsMapping);
		List<OffenceIndicator> returnList = new ArrayList<OffenceIndicator>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("ofnOnCheckDeleteMaster", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * ofnOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderCharges> ofnOnCheckDeleteMaster(final OffenderCharges paramBean) {
		final String sql = getQuery("OIMOFFEN_OFN_ONCHECKDELETEMASTER");
		final RowMapper<OffenderCharges> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderCharges.class,
				offenderChargesMapping);
		List<OffenderCharges> returnList = new ArrayList<OffenderCharges>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("ofnOnCheckDeleteMaster", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oimoffenPreInsert
	 *
	 * @param params
	 *
	 */
	public Offence oimoffenPreInsert(final Offence paramBean) {
		final String sql = getQuery("OIMOFFEN_OIMOFFEN_PREINSERT");
		final RowMapper<Offence> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Offence.class, offencesMapping);
		Offence returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oimoffenPreInsert
	 *
	 * @param params
	 *
	 */
	public AllowableOffenceTypes oimoffenPreInsert(final AllowableOffenceTypes paramBean) {
		final String sql = getQuery("OIMOFFEN_OIMOFFEN_PREINSERT");
		final RowMapper<AllowableOffenceTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AllowableOffenceTypes.class, allowableOffenceTypesMapping);
		AllowableOffenceTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oimoffenPreInsert
	 *
	 * @param params
	 *
	 */
	public List<OffenceIndicator> oimoffenPreInsert(final OffenceIndicator paramBean) {
		final String sql = getQuery("OIMOFFEN_OIMOFFEN_PREINSERT");
		final RowMapper<OffenceIndicator> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenceIndicator.class,
				offenceIndicatorsMapping);
		List<OffenceIndicator> returnList = new ArrayList<OffenceIndicator>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {

			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * insIndId
	 *
	 * @param params
	 *
	 */
	public Object insIndId(Dual paramBean) {
		final String sql = getQuery("OIMOFFEN_INS_IND_ID");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, dualMapping);
		Dual returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oimoffenPreInsert
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> oimoffenPostquery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIMOFFEN_OIMOFFEN_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				offenceIndicatorsMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {

			logger.error("", e);
		}
		return returnList;
	}

	@Override
	public String gettingLegaslatingId(final String legislatingBodyCode) {
		final String sql = getQuery("OIMOFFEN_OIMOFFEN_POSTQUERY");
		String returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("PCODE", legislatingBodyCode),
					String.class);
		} catch (Exception e) {
			return returnVal;
		}
		return returnVal;
	}

	@Override
	public Long offenceIndicatorId() {
		final String sql = getQuery("OIMOFFEN_INS_IND_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	public Long oimoffenOfnOncheckdeletemasterOffenderCharges(final Offence paramBean) {
		final String sql = getQuery("OIMOFFEN_OFN_ONCHECKDELETEMASTER_OFFENDER_CHARGES");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENCECODE", paramBean.getOffenceCode(), "STATUTECODE", paramBean.getStatuteCode()),
				Long.class);
	}

	@Override
	public Long oimoffenStatOncheckdeletemasterOffences(final Offence paramBean) {
		final String sql = getQuery("OIMOFFEN_STAT_ONCHECKDELETEMASTER_OFFENCES");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("STATUTECODE", paramBean.getStatuteCode()),
				Long.class);

	}
	
	@Override
	public List<OffenceByStatute> getOffencesOnStatute(final Offence paramBean) {
		final String sql = getQuery("GET_OFFENCE_DATA_ON_STATUTE");
		final RowMapper<OffenceByStatute> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenceByStatute.class,
				offenceByStatuteMapping);
		List<OffenceByStatute> returnList = new ArrayList<OffenceByStatute>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("STATUTECODE", paramBean.getStatuteCode()), columnRowMapper);
		} catch (Exception e) {

			logger.error("In getOffencesOnStatute", e);
		}
		return returnList;

	}

	@Override
	public List<OffenceByStatute> getOffencesOnStatuteList() {

		final String sql = getQuery("GET_OFFENCE_DATA_LIST");
		final RowMapper<OffenceByStatute> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenceByStatute.class,
				offenceByStatuteMapping);
		List<OffenceByStatute> returnList = new ArrayList<OffenceByStatute>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {

			logger.error("In getOffencesOnStatuteList", e);
		}
		return returnList;
	
	}
	
	@Override
	public Boolean isChgDependOnOffences(Integer offenceId) {
		final String sql = getQuery("OIMOFFEN_CHK_FOR_DEPENDENT_CHARGES");
		try {
			String result = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenceId",offenceId), String.class);
			if("Y".equalsIgnoreCase(result)) {
				return true;
			}
		} catch (Exception e) {
			logger.error("In isChgDependOnOffences", e);
		}
		return false;
	}
}
