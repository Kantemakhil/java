package net.syscon.s4.inst.incidentsoic.impl;

import java.sql.SQLException;
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
import net.syscon.s4.common.beans.AgencyIncidentAssoTostg;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.incidentsoic.OiustgasRepository;

@Repository
public class OiustgasRepositoryImpl extends RepositoryBase implements OiustgasRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiustgasRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> agencyIncidentAssoTostgMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
			.put("NVL(MAX(SEQ_NO)", new FieldMapper(" nvl(max(seqNo)")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime")).put("SEQ_NO", new FieldMapper("seqNo"))
			.put("'Y'", new FieldMapper(" 'y' ")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("STG_ID", new FieldMapper("stgId")).build();
	private final Map<String, FieldMapper> securityThreatGroupsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_CODE", new FieldMapper("stgCode")).put("STG_ID", new FieldMapper("stgId"))
			.put("DESCRIPTION", new FieldMapper("description")).put("DESCRIPTION1", new FieldMapper("description1"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE", new FieldMapper("profileValue")).build();

	/**
	 * Creates new OiustgasRepositoryImpl class Object
	 */
	public OiustgasRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao AgencyIncidentAssoTostg
	 *
	 * @return List<AgencyIncidentAssoTostg>
	 *
	 * @throws SQLException
	 */
	public List<AgencyIncidentAssoTostg> agencyIncidentAssoTostgExecuteQuery(
			final AgencyIncidentAssoTostg objSearchDao) {
		final String sql = getQuery("OIUSTGAS_AGENCYINCIDENTASSOTOSTG_FIND_AGENCY_INCIDENT_ASSO_TOSTG");

		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			if (objSearchDao.getAgencyIncidentId() != null) {
				sqlQuery.append("AGENCY_INCIDENT_ID =  :agencyIncidentId " + " and ");
				valuesList.addValue("agencyIncidentId", objSearchDao.getAgencyIncidentId());
			}
			if (objSearchDao.getSeqNo() != null) {
				sqlQuery.append("SEQ_NO =  :_seqNo " + " and ");
				valuesList.addValue("_seqNo", objSearchDao.getSeqNo());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<AgencyIncidentAssoTostg> AgencyIncidentAssoTostgRowMapper = Row2BeanRowMapper
				.makeMapping(preparedSql, AgencyIncidentAssoTostg.class, agencyIncidentAssoTostgMapping);
		final ArrayList<AgencyIncidentAssoTostg> returnList = (ArrayList<AgencyIncidentAssoTostg>) namedParameterJdbcTemplate
				.query(preparedSql, valuesList, AgencyIncidentAssoTostgRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAgencyIncidentAssoTostg List<AgencyIncidentAssoTostg>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer agencyincidentassotostgInsertAgencyIncidentAssoTostg(
			final List<AgencyIncidentAssoTostg> lstAgencyIncidentAssoTostg) {

		final String sql = getQuery("OIUSTGAS_AGENCYINCIDENTASSOTOSTG_INSERT_AGENCY_INCIDENT_ASSO_TOSTG");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidentAssoTostg list : lstAgencyIncidentAssoTostg) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyIncidentAssoTostg.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgencyIncidentAssoTostg List<AgencyIncidentAssoTostg>
	 *
	 * @throws SQLException
	 */
	public Integer agencyIncidentAssoTostgUpdateAgencyIncidentAssoTostg(
			final List<AgencyIncidentAssoTostg> lstAgencyIncidentAssoTostg) {
		final String sql = getQuery("OIUSTGAS_AGENCYINCIDENTASSOTOSTG_UPDATE_AGENCY_INCIDENT_ASSO_TOSTG");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidentAssoTostg agencyIncidentAssoTostg : lstAgencyIncidentAssoTostg) {
			parameters.add(new BeanPropertySqlParameterSource(agencyIncidentAssoTostg));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyIncidentAssoTostg.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAgencyIncidentAssoTostg List<AgencyIncidentAssoTostg>
	 *
	 * @throws SQLException
	 */
	public Integer agencyIncidentAssoTostgDeleteAgencyIncidentAssoTostg(
			final List<AgencyIncidentAssoTostg> lstAgencyIncidentAssoTostg) {
		final String sql = getQuery("OIUSTGAS_AGENCYINCIDENTASSOTOSTG_DELETE_AGENCY_INCIDENT_ASSO_TOSTG");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidentAssoTostg agencyIncidentAssoTostg : lstAgencyIncidentAssoTostg) {
			parameters.add(new BeanPropertySqlParameterSource(agencyIncidentAssoTostg));
		}
		try {
			String tableName = "AGENCY_INCIDENT_ASSO_TOSTG";
			String whereClause = "AGENCY_INCIDENT_ID  = :agencyIncidentId AND SEQ_NO  = :seqNo";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method agencyIncidentAssoTostgDeleteAgencyIncidentAssoTostg", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyIncidentAssoTostg.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<SecurityThreatGroups>
	 */
	public List<SecurityThreatGroups> rgStgRecordGroup() {
		final String sql = getQuery("OIUSTGAS_FIND_RGSTG2");
		final RowMapper<SecurityThreatGroups> securityThreatGroupsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", ""), securityThreatGroupsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgStgRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<SecurityThreatGroups>
	 */
	public List<SecurityThreatGroups> rgStgORecordGroup() {
		final String sql = getQuery("OIUSTGAS_FIND_RGSTG1");
		final RowMapper<SecurityThreatGroups> securityThreatGroupsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", ""), securityThreatGroupsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgStgORecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<SecurityThreatGroups>
	 */
	public List<SecurityThreatGroups> rgStgLRecordGroup() {
		final String sql = getQuery("OIUSTGAS_FIND_RGSTG3");
		final RowMapper<SecurityThreatGroups> securityThreatGroupsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", ""), securityThreatGroupsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgStgLRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<SecurityThreatGroups>
	 */
	public List<SecurityThreatGroups> stgGrpRecordGroup() {
		final String mode = "ENTER-QUERY";
		final String sql = getQuery("OIUSTGAS_FIND_STGGRP");
		final RowMapper<SecurityThreatGroups> securityThreatGroupsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", mode), securityThreatGroupsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method stgGrpRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyIncidentAssoTostgPreInsertPRE-INSERT
	 *
	 * @param params
	 *
	 */
	public Object agencyIncidentAssoTostgPreInsertInc(final AgencyIncidentAssoTostg paramBean) {
		final String sql = getQuery("OIUSTGAS_AGENCY_INCIDENT_ASSO_TOSTG_PREINSERTPRE_INSERT");
		final Object returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("AGENCYINCIDENTID", paramBean.getAgencyIncidentId()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyIncidentAssoTostgPreInsert
	 *
	 * @param params
	 *
	 */
	public AgencyIncidentAssoTostg agencyIncidentAssoTostgPreInsert(final AgencyIncidentAssoTostg paramBean) {
		final String sql = getQuery("OIUSTGAS_AGENCY_INCIDENT_ASSO_TOSTG_PREINSERT");
		final RowMapper<AgencyIncidentAssoTostg> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyIncidentAssoTostg.class, agencyIncidentAssoTostgMapping);
		AgencyIncidentAssoTostg returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIUSTGAS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				systemProfilesMapping);
		final OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateRg
	 *
	 * @param params
	 *
	 */
	public SystemProfiles populateRg() {
		final String sql = getQuery("OIUSTGAS_POPULATE_RG");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}
}
