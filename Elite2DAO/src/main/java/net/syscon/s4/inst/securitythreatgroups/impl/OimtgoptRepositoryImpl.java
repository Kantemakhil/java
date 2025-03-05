package net.syscon.s4.inst.securitythreatgroups.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
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
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.SecurityThreatGroupsHty;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.securitythreatgroups.OimtgoptRepository;

/**
 * Class OimtgoptRepositoryImpl
 */
@Repository
public class OimtgoptRepositoryImpl extends RepositoryBase implements OimtgoptRepository {

	private static Logger logger = LogManager.getLogger(OimtgoptRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> securityThreatGroupsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("STG_ID", new FieldMapper("stgId"))
			.put("TO_P_STG_ID", new FieldMapper("toPStgId")).put("STG_CODE", new FieldMapper("stgCode"))
			.put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("STG_ID", new FieldMapper("stgId"))
			.put("STG_CODE", new FieldMapper("stgCode")).build();

	private final Map<String, FieldMapper> securityThreatGroupsHtyMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_ID", new FieldMapper("stgId")).put("HTY_SEQ", new FieldMapper("htySeq"))
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("EFFECTIVE_DATE", new FieldMapper("effectiveDate"))
			.put("ACTION", new FieldMapper("action")).put("FROM_STG_LEVEL", new FieldMapper("fromStgLevel"))
			.put("TO_STG_LEVEL", new FieldMapper("toStgLevel")).put("FROM_P_STG_ID", new FieldMapper("fromPStgId"))
			.put("TO_P_STG_ID", new FieldMapper("toPStgId")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("STG_ID", new FieldMapper("stgId"))
			.put("STG_CODE", new FieldMapper("stgCode")).build();

	/**
	 * Creates new OimtgoptRepositoryImpl class Object
	 */
	public OimtgoptRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao SecurityThreatGroupsHty
	 *
	 * @return List<SecurityThreatGroupsHty>
	 *
	 * @throws SQLException
	 */
	public List<SecurityThreatGroupsHty> stgHtyExecuteQuery(final SecurityThreatGroupsHty objSearchDao) {
		final String sql = getQuery("OIMTGOPT_STGHTY_FIND_SECURITY_THREAT_GROUPS_HTY");
		final RowMapper<SecurityThreatGroupsHty> SecurityThreatGroupsHtyRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroupsHty.class, securityThreatGroupsHtyMapping);
		List<SecurityThreatGroupsHty> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("STGID", objSearchDao.getStgId()),
				SecurityThreatGroupsHtyRowMapper);
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
	 * @param lstSecurityThreatGroupsHty List<SecurityThreatGroupsHty>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer stgHtyInsertSecurityThreatGroupsHty(final List<SecurityThreatGroupsHty> lstSecurityThreatGroupsHty) {
		final String sql = getQuery("OIMTGOPT_STGHTY_INSERT_SECURITY_THREAT_GROUPS_HTY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SecurityThreatGroupsHty obj : lstSecurityThreatGroupsHty) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSecurityThreatGroupsHty.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao SecurityThreatGroupsHty
	 *
	 * @return List<SecurityThreatGroupsHty>
	 *
	 * @throws SQLException
	 */
	public List<SecurityThreatGroupsHty> stgHty1ExecuteQuery(final SecurityThreatGroupsHty objSearchDao) {
		final String sql = getQuery("OIMTGOPT_STGHTY1_FIND_SECURITY_THREAT_GROUPS_HTY");
		final RowMapper<SecurityThreatGroupsHty> SecurityThreatGroupsHtyRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroupsHty.class, securityThreatGroupsHtyMapping);
		List<SecurityThreatGroupsHty> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), SecurityThreatGroupsHtyRowMapper);
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
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<SecurityThreatGroups> lNationRecordGroup(final Integer parentStgId) {
		final String sql = getQuery("OIMTGOPT_FIND_LNATION");
		final RowMapper<SecurityThreatGroups> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		List<SecurityThreatGroups> returnList = new ArrayList<SecurityThreatGroups>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("PARENTSTGID", parentStgId), mRowMapper);
		} catch (final Exception e) {
			logger.error("lNationRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<SecurityThreatGroups> lGangRecordGroup(final Integer parentStgId) {
		final String sql = getQuery("OIMTGOPT_FIND_LGANG");
		final RowMapper<SecurityThreatGroups> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		List<SecurityThreatGroups> returnList = new ArrayList<SecurityThreatGroups>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("PARENTSTGID", parentStgId), mRowMapper);
		} catch (final Exception e) {
			logger.error("OIMTGOPT_FIND_LGANG", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * stgHty1PostQuery
	 *
	 * @param params
	 *
	 */
	public String stgHty1PostQuery(final BigDecimal param) {
		final String sql = getQuery("OIMTGOPT_STG_HTY1_POSTQUERY");
		String returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("TOPSTGID", param), String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return returnValue;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIMTGOPT_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		List<OmsModules> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgtewhenRadioChanged
	 *
	 * @param params
	 *
	 */
	public BigDecimal cgtewhenRadioChanged(final BigDecimal param) {
		final String sql = getQuery("OIMTGOPT_CGTEWHEN_RADIO_CHANGED");
		final BigDecimal returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("STGID", param),
				BigDecimal.class);
		return returnValue;
	}

	public BigDecimal preInsert(final BigDecimal object) {
		final String sql = getQuery("OIMTGOPT_GET_WORK_FLOW_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("STGID", object), BigDecimal.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgtewhenRadioChanged
	 *
	 * @param params
	 *
	 */
	public SecurityThreatGroups preInsertSecurityThreatGroups(final BigDecimal object) {
		final String sql = getQuery("OIMTGOPT_SECURITY_THREAT_GROUPS_PRE_COMMIT");
		final RowMapper<SecurityThreatGroups> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		SecurityThreatGroups returnList = new SecurityThreatGroups();
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("STGID", object), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param offBook OffenderBookings
	 *
	 * @return Integer
	 */
	public Integer updateSecurityThreatGroups(final SecurityThreatGroupsHty secThrGrp) {
		final String sql = getQuery("OIMTGOPT_UPDATE_SECURITY_THREAT_GROUPS");
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(secThrGrp));
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param offBook OffenderBookings
	 *
	 * @return Integer
	 */
	public Integer updateSecurityThreatGroupsOne(final SecurityThreatGroupsHty secThrGrp) {
		final String sql = getQuery("OIMTGOPT_UPDATE_SECURITY_THREAT_GROUPS_ONE");
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(secThrGrp));
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgtewhenRadioChanged
	 *
	 * @param params
	 *
	 */
	public SecurityThreatGroups toParentStgIdData(final SecurityThreatGroupsHty paramBean) {
		final String sql = getQuery("OIMTGOPT_LV_TO_P_ID_STG_ID");
		final RowMapper<SecurityThreatGroups> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		SecurityThreatGroups returnList = new SecurityThreatGroups();
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("STGID", paramBean.getStgId()),
				columnRowMapper);
		if (returnList != null) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param offBook OffenderBookings
	 *
	 * @return Integer
	 */
	public Integer updateSecurityThreatGroupsTwo(final SecurityThreatGroupsHty secThrGrp) {
		final String sql = getQuery("OIMTGOPT_UPDATE_SECURITY_THREAT_GROUPS_TWO");
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(secThrGrp));
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}
}
