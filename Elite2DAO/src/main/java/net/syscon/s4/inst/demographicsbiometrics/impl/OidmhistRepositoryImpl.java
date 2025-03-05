package net.syscon.s4.inst.demographicsbiometrics.impl;

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
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderMilitaryDiscActions;
import net.syscon.s4.im.beans.OffenderMilitaryRecords;
import net.syscon.s4.im.beans.OffenderMilitaryTechSpecs;
import net.syscon.s4.im.beans.OffenderMilitaryWarZones;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.demographicsbiometrics.OidmhistRepository;

/**
 * Class OidmhistRepositoryImpl
 */
@Repository
public class OidmhistRepositoryImpl extends RepositoryBase implements OidmhistRepository {
	
	private static Logger logger = LogManager.getLogger(OidmhistRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offenderMilitaryWarZonesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("WAR_SEQ", 					new FieldMapper("warSeq"))
			.put("WAR_ZONE_CODE", 				new FieldMapper("warZoneCode"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("MILITARY_SEQ", 				new FieldMapper("militarySeq"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDateTime"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDateTime"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> offenderMilitaryTechSpecsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("MILITARY_SEQ", 				new FieldMapper("militarySeq"))
			.put("TECH_SPEC_CODE", 				new FieldMapper("techSpecCode"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDateTime"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDateTime"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> offenderMilitaryRecordsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDateTime"))
			.put("DISCIPLINARY_ACTION_CODE", 	new FieldMapper("disciplinaryActionCode"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDateTime"))
			.put("MILITARY_SEQ", 				new FieldMapper("militarySeq"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("MILITARY_RANK_CODE", 			new FieldMapper("militaryRankCode"))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MILITARY_BRANCH_CODE", 		new FieldMapper("militaryBranchCode"))
			.put("MILITARY_DISCHARGE_CODE", 	new FieldMapper("militaryDischargeCode"))
			.put("DESCRIPTION", 				new FieldMapper(" description "))
			.put("MILITARY_RANK_CODE", 			new FieldMapper("militaryRankCode"))
			.put("MODE", 						new FieldMapper("mode"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 				new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", 				new FieldMapper("profileType"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", 				new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", 				new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", 			new FieldMapper("profileValue2"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 						new FieldMapper("code"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("ACTIVE_FLAG", 				new FieldMapper("activeFlag"))
			.build();
	private final Map<String, FieldMapper> offenderMilitaryDiscActionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MLTY_DISCP_CODE", 			new FieldMapper("mltyDiscpCode"))
			.put("MLTY_DISCP_SEQ", 				new FieldMapper("mltyDiscpSeq"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("MILITARY_SEQ", 				new FieldMapper("militarySeq"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDateTime"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDateTime"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> sysDualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SYSDATE", 					new FieldMapper("sysDate"))
			.put("USER", 						new FieldMapper("user"))
			.build();

	/**
	 * Creates new OidmhistRepositoryImpl class Object
	 */
	public OidmhistRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderMilitaryRecords
	 *
	 * @return List<OffenderMilitaryRecords>
	 *
	 * @throws SQLException
	 */
	public List<OffenderMilitaryRecords> offMrExecuteQuery(final OffenderMilitaryRecords objSearchDao) {
		final String sql = getQuery("OIDMHIST_OFFMR_FIND_OFFENDER_MILITARY_RECORDS");
		final RowMapper<OffenderMilitaryRecords> OffenderMilitaryRecordsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderMilitaryRecords.class, offenderMilitaryRecordsMapping);
		List<OffenderMilitaryRecords> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", objSearchDao.getOffenderBookId()), OffenderMilitaryRecordsRowMapper);
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
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderMilitaryRecords
	 *            List<OffenderMilitaryRecords>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offMrInsertOffenderMilitaryRecords(final List<OffenderMilitaryRecords> lstOffenderMilitaryRecords) {
		String sql = getQuery("OIDMHIST_OFFMR_INSERT_OFFENDER_MILITARY_RECORDS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderMilitaryRecords list : lstOffenderMilitaryRecords) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderMilitaryRecords.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderMilitaryRecords
	 *            List<OffenderMilitaryRecords>
	 *
	 * @throws SQLException
	 */
	public Integer offMrUpdateOffenderMilitaryRecords(final List<OffenderMilitaryRecords> lstOffenderMilitaryRecords) {
		String sql = getQuery("OIDMHIST_OFFMR_UPDATE_OFFENDER_MILITARY_RECORDS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderMilitaryRecords offenderMilitaryRecords : lstOffenderMilitaryRecords) {
			parameters.add(new BeanPropertySqlParameterSource(offenderMilitaryRecords));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderMilitaryRecords.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderMilitaryRecords
	 *            List<OffenderMilitaryRecords>
	 *
	 * @throws SQLException
	 */
	public Integer offMrDeleteOffenderMilitaryRecords(final List<OffenderMilitaryRecords> lstOffenderMilitaryRecords) {
		String sql = getQuery("OIDMHIST_OFFMR_DELETE_OFFENDER_MILITARY_RECORDS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderMilitaryRecords offenderMilitaryRecords : lstOffenderMilitaryRecords) {
			parameters.add(new BeanPropertySqlParameterSource(offenderMilitaryRecords));
		}
		try {
			String tableName = "OFFENDER_MILITARY_RECORDS";
			String whereClause = "OFFENDER_BOOK_ID  = :offenderBookId AND MILITARY_SEQ  = :militarySeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offMrDeleteOffenderMilitaryRecords", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderMilitaryRecords.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderMilitaryDiscActions
	 *
	 * @return List<OffenderMilitaryDiscActions>
	 *
	 * @throws SQLException
	 */
	public List<OffenderMilitaryDiscActions> disActExecuteQuery(final OffenderMilitaryDiscActions objSearchDao) {
		final String sql = getQuery("OIDMHIST_DISACT_FIND_OFFENDER_MILITARY_DISC_ACTIONS");
		final RowMapper<OffenderMilitaryDiscActions> OffenderMilitaryDiscActionsRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderMilitaryDiscActions.class, offenderMilitaryDiscActionsMapping);
		List<OffenderMilitaryDiscActions> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",
				objSearchDao.getOffenderBookId(), "militarySeq", objSearchDao.getMilitarySeq()),
				OffenderMilitaryDiscActionsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderMilitaryDiscActions
	 *            List<OffenderMilitaryDiscActions>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer disActInsertOffenderMilitaryDiscActions(
			final List<OffenderMilitaryDiscActions> lstOffenderMilitaryDiscActions) {
		String sql = getQuery("OIDMHIST_DISACT_INSERT_OFFENDER_MILITARY_DISC_ACTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderMilitaryDiscActions list : lstOffenderMilitaryDiscActions) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderMilitaryDiscActions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderMilitaryDiscActions
	 *            List<OffenderMilitaryDiscActions>
	 *
	 * @throws SQLException
	 */
	public Integer disActUpdateOffenderMilitaryDiscActions(
			final List<OffenderMilitaryDiscActions> lstOffenderMilitaryDiscActions) {
		String sql = getQuery("OIDMHIST_DISACT_UPDATE_OFFENDER_MILITARY_DISC_ACTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderMilitaryDiscActions offenderMilitaryDiscActions : lstOffenderMilitaryDiscActions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderMilitaryDiscActions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderMilitaryDiscActions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderMilitaryDiscActions
	 *            List<OffenderMilitaryDiscActions>
	 *
	 * @throws SQLException
	 */
	public Integer disActDeleteOffenderMilitaryDiscActions(
			final List<OffenderMilitaryDiscActions> lstOffenderMilitaryDiscActions) {
		String sql = getQuery("OIDMHIST_DISACT_DELETE_OFFENDER_MILITARY_DISC_ACTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderMilitaryDiscActions offenderMilitaryDiscActions : lstOffenderMilitaryDiscActions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderMilitaryDiscActions));
		}
		try {
			String tableName = "OFFENDER_MILITARY_DISC_ACTIONS";
			String whereClause = "OFFENDER_BOOK_ID  = :offenderBookId AND MILITARY_SEQ  = :militarySeq AND MLTY_DISCP_SEQ  = :mltyDiscpSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method disActDeleteOffenderMilitaryDiscActions", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderMilitaryDiscActions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderMilitaryTechSpecs
	 *
	 * @return List<OffenderMilitaryTechSpecs>
	 *
	 * @throws SQLException
	 */
	public List<OffenderMilitaryTechSpecs> techSpecExecuteQuery(final OffenderMilitaryTechSpecs objSearchDao) {
		final String sql = getQuery("OIDMHIST_TECHSPEC_FIND_OFFENDER_MILITARY_TECH_SPECS");
		final RowMapper<OffenderMilitaryTechSpecs> OffenderMilitaryTechSpecsRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderMilitaryTechSpecs.class, offenderMilitaryTechSpecsMapping);
		List<OffenderMilitaryTechSpecs> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",
				objSearchDao.getOffenderBookId(), "militarySeq", objSearchDao.getMilitarySeq()),
				OffenderMilitaryTechSpecsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderMilitaryTechSpecs
	 *            List<OffenderMilitaryTechSpecs>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer techSpecInsertOffenderMilitaryTechSpecs(
			final List<OffenderMilitaryTechSpecs> lstOffenderMilitaryTechSpecs) {
		String sql = getQuery("OIDMHIST_TECHSPEC_INSERT_OFFENDER_MILITARY_TECH_SPECS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderMilitaryTechSpecs offenderMilitaryDiscActions : lstOffenderMilitaryTechSpecs) {
			parameters.add(new BeanPropertySqlParameterSource(offenderMilitaryDiscActions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderMilitaryTechSpecs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderMilitaryTechSpecs
	 *            List<OffenderMilitaryTechSpecs>
	 *
	 * @throws SQLException
	 */
	public Integer techSpecUpdateOffenderMilitaryTechSpecs(
			final List<OffenderMilitaryTechSpecs> lstOffenderMilitaryTechSpecs) {
		String sql = getQuery("OIDMHIST_TECHSPEC_UPDATE_OFFENDER_MILITARY_TECH_SPECS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderMilitaryTechSpecs offenderMilitaryTechSpecs : lstOffenderMilitaryTechSpecs) {
			parameters.add(new BeanPropertySqlParameterSource(offenderMilitaryTechSpecs));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderMilitaryTechSpecs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderMilitaryTechSpecs
	 *            List<OffenderMilitaryTechSpecs>
	 *
	 * @throws SQLException
	 */
	public Integer techSpecDeleteOffenderMilitaryTechSpecs(
			final List<OffenderMilitaryTechSpecs> lstOffenderMilitaryTechSpecs) {
		String sql = getQuery("OIDMHIST_TECHSPEC_DELETE_OFFENDER_MILITARY_TECH_SPECS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderMilitaryTechSpecs offenderMilitaryTechSpecs : lstOffenderMilitaryTechSpecs) {
			parameters.add(new BeanPropertySqlParameterSource(offenderMilitaryTechSpecs));
		}
		try {
			String tableName = "OFFENDER_MILITARY_TECH_SPECS";
			String whereClause = "OFFENDER_BOOK_ID  = :offenderBookId AND MILITARY_SEQ  = :militarySeq AND MLTY_TECH_SEQ  = :mltyTechSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method techSpecDeleteOffenderMilitaryTechSpecs", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderMilitaryTechSpecs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderMilitaryWarZones
	 *
	 * @return List<OffenderMilitaryWarZones>
	 *
	 * @throws SQLException
	 */
	public List<OffenderMilitaryWarZones> warZonesExecuteQuery(final OffenderMilitaryWarZones objSearchDao) {
		final String sql = getQuery("OIDMHIST_WARZONES_FIND_OFFENDER_MILITARY_WAR_ZONES");
		final RowMapper<OffenderMilitaryWarZones> OffenderMilitaryWarZonesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderMilitaryWarZones.class, offenderMilitaryWarZonesMapping);
		List<OffenderMilitaryWarZones> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",
				objSearchDao.getOffenderBookId(), "militarySeq", objSearchDao.getMilitarySeq()),
				OffenderMilitaryWarZonesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderMilitaryWarZones
	 *            List<OffenderMilitaryWarZones>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer warZonesInsertOffenderMilitaryWarZones(
			final List<OffenderMilitaryWarZones> lstOffenderMilitaryWarZones) {
		String sql = getQuery("OIDMHIST_WARZONES_INSERT_OFFENDER_MILITARY_WAR_ZONES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderMilitaryWarZones offenderMilitaryWarZones : lstOffenderMilitaryWarZones) {
			parameters.add(new BeanPropertySqlParameterSource(offenderMilitaryWarZones));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderMilitaryWarZones.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderMilitaryWarZones
	 *            List<OffenderMilitaryWarZones>
	 *
	 * @throws SQLException
	 */
	public Integer warZonesUpdateOffenderMilitaryWarZones(
			final List<OffenderMilitaryWarZones> lstOffenderMilitaryWarZones) {
		String sql = getQuery("OIDMHIST_WARZONES_UPDATE_OFFENDER_MILITARY_WAR_ZONES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderMilitaryWarZones offenderMilitaryWarZones : lstOffenderMilitaryWarZones) {
			parameters.add(new BeanPropertySqlParameterSource(offenderMilitaryWarZones));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderMilitaryWarZones.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderMilitaryWarZones
	 *            List<OffenderMilitaryWarZones>
	 *
	 * @throws SQLException
	 */
	public Integer warZonesDeleteOffenderMilitaryWarZones(
			final List<OffenderMilitaryWarZones> lstOffenderMilitaryWarZones) {
		String sql = getQuery("OIDMHIST_WARZONES_DELETE_OFFENDER_MILITARY_WAR_ZONES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderMilitaryWarZones offenderMilitaryWarZones : lstOffenderMilitaryWarZones) {
			parameters.add(new BeanPropertySqlParameterSource(offenderMilitaryWarZones));
		}
		try {
			String tableName = "OFFENDER_MILITARY_WAR_ZONES";
			String whereClause = "OFFENDER_BOOK_ID  = :offenderBookId AND MILITARY_SEQ  = :militarySeq AND WAR_SEQ  = :warSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method warZonesDeleteOffenderMilitaryWarZones", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderMilitaryWarZones.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 * @throws SQLException
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OIDMHIST_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgWarZoneRecordGroup() {
		final String sql = getQuery("OIDMHIST_FIND_RGWARZONE");
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
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgMltyTechRecordGroup() {
		final String sql = getQuery("OIDMHIST_FIND_RGMLTYTECH");
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
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgMilitaryRankRecordGroup() {
		final String sql = getQuery("OIDMHIST_FIND_RGMILITARYRANK");
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
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgMilitaryDischargeRecordGroup() {
		final String sql = getQuery("OIDMHIST_FIND_RGMILITARYDISCHARGE");
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
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgMilitaryBranchRecordGroup() {
		final String sql = getQuery("OIDMHIST_FIND_RGMILITARYBRANCH");
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
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgDisciplinaryActionRecordGroup() {
		final String sql = getQuery("OIDMHIST_FIND_RGDISCIPLINARYACTION");
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
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgHighstRankRecordGroup() {
		final String sql = getQuery("OIDMHIST_FIND_RGHIGHSTRANK");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param paramBean
	 *
	 */
	public List<OffenderMilitaryRecords> offBkgOnCheckDeleteMaster(final OffenderMilitaryRecords paramBean) {
		final String sql = getQuery("OIDMHIST_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderMilitaryRecords> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderMilitaryRecords.class, offenderMilitaryRecordsMapping);
		List<OffenderMilitaryRecords> returnObj = new ArrayList<>();
		returnObj = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offMrPostQuery
	 *
	 * @param paramBean
	 *
	 */
	public ReferenceCodes offMrPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDMHIST_OFF_MR_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offMrOnCheckDeleteMaster
	 *
	 * @param paramBean
	 *
	 */
	public OffenderMilitaryWarZones offMrOnCheckDeleteMaster(final OffenderMilitaryWarZones paramBean) {
		final String sql = getQuery("OIDMHIST_OFF_MR_ONCHECKDELETEMASTER");
		final RowMapper<OffenderMilitaryWarZones> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderMilitaryWarZones.class, offenderMilitaryWarZonesMapping);
		OffenderMilitaryWarZones returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offMrOnCheckDeleteMaster
	 *
	 * @param paramBean
	 *
	 */
	public OffenderMilitaryTechSpecs offMrOnCheckDeleteMaster(final OffenderMilitaryTechSpecs paramBean) {
		final String sql = getQuery("OIDMHIST_OFF_MR_ONCHECKDELETEMASTER");
		final RowMapper<OffenderMilitaryTechSpecs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderMilitaryTechSpecs.class, offenderMilitaryTechSpecsMapping);
		OffenderMilitaryTechSpecs returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offMrOnCheckDeleteMaster
	 *
	 * @param paramBean
	 *
	 */
	public OffenderMilitaryDiscActions offMrOnCheckDeleteMaster(final OffenderMilitaryDiscActions paramBean) {
		final String sql = getQuery("OIDMHIST_OFF_MR_ONCHECKDELETEMASTER");
		final RowMapper<OffenderMilitaryDiscActions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderMilitaryDiscActions.class, offenderMilitaryDiscActionsMapping);
		OffenderMilitaryDiscActions returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * disActPostQuery
	 *
	 * @param paramBean
	 *
	 */
	public ReferenceCodes disActPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDMHIST_DIS_ACT_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * techSpecPostQuery
	 *
	 * @param paramBean
	 *
	 */
	public ReferenceCodes techSpecPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDMHIST_TECH_SPEC_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * warZonesPostQuery
	 *
	 * @param paramBean
	 *
	 */
	public ReferenceCodes warZonesPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDMHIST_WAR_ZONES_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffMrOffMrRef
	 *
	 * @param paramBean
	 *
	 */
	public ReferenceCodes cgfkchkOffMrOffMrRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDMHIST_CGFKCHK_OFF_MR_OFF_MR_REF_4");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param paramBean
	 *
	 */
	public List<Dual> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OIDMHIST_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, sysDualMapping);
		List<Dual> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offmrPreInsertc
	 *
	 * @param paramBean
	 *
	 */
	public Integer offmrPreInsertc(final Long paramBean) {
		final String sql = getQuery("OIDRPITM_OFF_MR_PREINSERT_C");
		String obj = null;
		Integer returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", paramBean), String.class);
		if (obj != null) {
			returnval = Integer.parseInt(obj);
		}
		return returnval;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offdisActPreInsertc
	 *
	 * @param paramBean
	 *
	 */
	public Integer offdisActPreInsertc(final Long paramBean) {
		final String sql = getQuery("OIDRPITM_OFF_DIS_ART_PREINSERT_C");
		String obj = null;
		Integer returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", paramBean), String.class);
		if (obj != null) {
			returnval = Integer.parseInt(obj);
		}
		return returnval;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offtechSpecPreInsertc
	 *
	 * @param paramBean
	 *
	 */
	public Integer offtechSpecPreInsertc(final Long paramBean) {
		final String sql = getQuery("OIDRPITM_OFF_TEC_SPEC_PREINSERT_C");
		String obj = null;
		Integer returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", paramBean), String.class);
		if (obj != null) {
			returnval = Integer.parseInt(obj);
		}
		return returnval;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offwarZonesPreInsertc
	 *
	 * @param paramBean
	 *
	 */
	public Integer offwarZonesPreInsertc(final Long paramBean) {
		final String sql = getQuery("OIDRPITM_OFF_WAR_ZONES_PREINSERT_C");
		String obj = null;
		Integer returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", paramBean), String.class);
		if (obj != null) {
			returnval = Integer.parseInt(obj);
		}
		return returnval;
	}

}
