package net.syscon.s4.inst.incidentsoic.maintenance.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.incidentsoic.maintenance.OicOffenceIndicators;
import net.syscon.s4.inst.incidentsoic.maintenance.OimoicoiRepository;

/**
 * Class OimoicoiRepositoryImpl
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
@Repository
public class OimoicoiRepositoryImpl extends RepositoryBase implements OimoicoiRepository {

	/**
	 * import org.apache.logging.log4j.LogManager; import
	 * org.apache.logging.log4j.Logger; Creates new OimoicoiRepositoryImpl class
	 * Object
	 */
	public OimoicoiRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimoicoiRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> oicOffencesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("END_DATE", new FieldMapper("endDate")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("OIC_OFFENCE_CATEGORY", new FieldMapper("oicOffenceCategory"))
			.put("MAX_PENALTY_DAYS", new FieldMapper("maxPenaltyDays"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("OIC_OFFENCE_ID", new FieldMapper("oicOffenceId"))
			.put("OIC_OFFENCE_CODE", new FieldMapper("oicOffenceCode"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("START_DATE", new FieldMapper("startDate")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("MAX_PENALTY_MONTHS", new FieldMapper("maxPenaltyMonths"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("UPDATE_ALLOWED_FLAG", new FieldMapper("updateAllowedFlag"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("OIC_OFFENCE_TYPE", new FieldMapper("oicOffenceType"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate")).put("DESCRIPTION", new FieldMapper("description"))
			.put("P_START_DATE", new FieldMapper("pStartDate")).put("P_END_DATE", new FieldMapper("pEndDate"))

			.build();
	private final Map<String, FieldMapper> oicOffenceIndicatorsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("OIC_OFFENCE_ID", new FieldMapper("oicOffenceId"))
			.put("OIC_OFFENCE_INDICATOR_ID", new FieldMapper("oicOffenceIndicatorId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("INDICATOR_CODE", new FieldMapper("indicatorCode")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> sysDualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("USER", new FieldMapper("user")).put("SYSDATE", new FieldMapper("sysDate")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OicOffences
	 *
	 * @return List<OicOffences>
	 *
	 * @throws SQLException
	 */
	public List<OicOffences> oicOfnExecuteQuery(OicOffences objSearchDao) {
		final String sql = getQuery("OIMOICOI_OICOFN_FIND_OIC_OFFENCES");
		final RowMapper<OicOffences> OicOffencesRowMapper = Row2BeanRowMapper.makeMapping(sql, OicOffences.class,
				oicOffencesMapping);
		final ArrayList<OicOffences> returnList = (ArrayList<OicOffences>) namedParameterJdbcTemplate.query(sql,
				createParams(), OicOffencesRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOicOffences
	 *            List<OicOffences>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer oicOfnInsertOicOffences(final List<OicOffences> lstOicOffences) {
		String sql = getQuery("OIMOICOI_OICOFN_INSERT_OIC_OFFENCES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OicOffences oicHearings : lstOicOffences) {
			parameters.add(new BeanPropertySqlParameterSource(oicHearings));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("Exception : oicOfnCommit:", e.getMessage().contains( "oic_offences_uk1"));
		}
		if (lstOicOffences.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOicOffences
	 *            List<OicOffences>
	 *
	 * @throws SQLException
	 */
	public Integer oicOfnUpdateOicOffences(final List<OicOffences> lstOicOffences) {
		String sql = getQuery("OIMOICOI_OICOFN_UPDATE_OIC_OFFENCES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OicOffences oicOffences : lstOicOffences) {
			parameters.add(new BeanPropertySqlParameterSource(oicOffences));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOicOffences.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OicOffenceIndicators
	 *
	 * @return List<OicOffenceIndicators>
	 *
	 * @throws SQLException
	 */
	public List<OicOffenceIndicators> oicOffenceIndicatorsExecuteQuery(OicOffenceIndicators objSearchDao) {
		final String sql = getQuery("OIMOICOI_OICOFFENCEINDICATORS_FIND_OIC_OFFENCE_INDICATORS");
		final RowMapper<OicOffenceIndicators> OicOffenceIndicatorsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OicOffenceIndicators.class, oicOffenceIndicatorsMapping);
		final ArrayList<OicOffenceIndicators> returnList = (ArrayList<OicOffenceIndicators>) namedParameterJdbcTemplate
				.query(sql, createParams("oicOffenceId", objSearchDao.getOicOffenceId()),
						OicOffenceIndicatorsRowMapper);
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
	 * @param lstOicOffenceIndicators
	 *            List<OicOffenceIndicators>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer oicOffenceIndicatorsInsertOicOffenceIndicators(
			final List<OicOffenceIndicators> lstOicOffenceIndicators) {
		String sql = getQuery("OIMOICOI_OICOFFENCEINDICATORS_INSERT_OIC_OFFENCE_INDICATORS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OicOffenceIndicators oicOffenceIndicators : lstOicOffenceIndicators) {
			parameters.add(new BeanPropertySqlParameterSource(oicOffenceIndicators));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstOicOffenceIndicators.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOicOffenceIndicators
	 *            List<OicOffenceIndicators>
	 *
	 * @throws SQLException
	 */
	public Integer oicOffenceIndicatorsUpdateOicOffenceIndicators(
			final List<OicOffenceIndicators> lstOicOffenceIndicators) {
		String sql = getQuery("OIMOICOI_OICOFFENCEINDICATORS_UPDATE_OIC_OFFENCE_INDICATORS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OicOffenceIndicators oicOffenceIndicators : lstOicOffenceIndicators) {
			parameters.add(new BeanPropertySqlParameterSource(oicOffenceIndicators));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOicOffenceIndicators.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOicOffenceIndicators
	 *            List<OicOffenceIndicators>
	 *
	 * @throws SQLException
	 */
	public Integer oicOffenceIndicatorsDeleteOicOffenceIndicators(
			final List<OicOffenceIndicators> lstOicOffenceIndicators) {
		String sql = getQuery("OIMOICOI_OICOFFENCEINDICATORS_DELETE_OIC_OFFENCE_INDICATORS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OicOffenceIndicators oicOffenceIndicators : lstOicOffenceIndicators) {
			parameters.add(new BeanPropertySqlParameterSource(oicOffenceIndicators));
		}
		try {
			String tableName = "OIC_OFFENCE_INDICATORS";
			String whereClause = "OIC_OFFENCE_INDICATOR_ID  = :oicOffenceIndicatorId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method oicOffenceIndicatorsDeleteOicOffenceIndicators", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstOicOffenceIndicators.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgOicOffenceCategRecordGroup() {
		final String sql = getQuery("OIMOICOI_FIND_RGOICOFFENCECATEG");
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
	public List<ReferenceCodes> rgOicOffenceTypeRecordGroup() {
		final String sql = getQuery("OIMOICOI_FIND_RGOICOFFENCETYPE");
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
	public List<ReferenceCodes> rgOicOffenceIndicatorsRecordGroup() {
		final String sql = getQuery("OIMOICOI_FIND_RGOICOFFENCEINDICATORS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oicOfnPreDeletePRE-DELETE
	 *
	 * @param params
	 *
	 */
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oicOffenceIndicatorsPreInsert
	 *
	 * @param params
	 *
	 */
	public Integer oicOffenceIndicatorsPreInsert() {
		final String sql = getQuery("OIMOICOI_OIC_OFFENCE_INDICATORS_PREINSERT");
		Integer returnList = 0;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
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
	public OmsModules createFormGlobals(OmsModules paramBean) {
		final String sql = getQuery("OIMOICOI_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				sysDualMapping);
		OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	public Integer checkOffenseCodeExist(OicOffences object) {
		final String sql = getQuery("OIMOICOI_OIC_OFFENCE_CHECK_OFFENCE_CODE");
		Integer returnList = 0;
		returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("oicOffenceCode", object.getOicOffenceCode()), Integer.class);
		return returnList;
	}

	@Override
	public List<OicOffences> oicOfnCheckoverLapping(OicOffences objSearchDao) {
		String startDate = null;
		String endDate = null;
		 ArrayList<OicOffences> returnList=new ArrayList<OicOffences>();
		try {
			
			final String sql = getQuery("OIMOICOI_OIC_OFFENCE_CHECK_OVERLAP_PERIOD");
			final RowMapper<OicOffences> OicOffencesRowMapper = Row2BeanRowMapper.makeMapping(sql, OicOffences.class,
					oicOffencesMapping);
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
			if (objSearchDao.getStartDate() != null) {
				startDate = simpleDateFormat.format(objSearchDao.getStartDate());
			}
			if (objSearchDao.getEndDate() != null) {
				endDate = simpleDateFormat.format(objSearchDao.getEndDate());
			}
			 returnList = (ArrayList<OicOffences>) namedParameterJdbcTemplate.query(sql,
					createParams("oicOffenceId", objSearchDao.getOicOffenceId(), "oicOffenceCode",
							objSearchDao.getOicOffenceCode(), "startDate", startDate, "endDate", endDate),
					OicOffencesRowMapper);
		} catch(Exception e) {
			logger.error("oicOfnCheckoverLapping", e);
		}
		return returnList;
	}
	
	@Override
	public Integer getNextOicOffenceId() {
		final String sql = getQuery("OIMOICOI_GET_NEXT_OFFENCE_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}
}
