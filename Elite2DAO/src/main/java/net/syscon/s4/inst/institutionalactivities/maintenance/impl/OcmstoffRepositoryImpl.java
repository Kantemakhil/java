package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmstoffRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramServicesProfiles;

/**
 * Class OcmstoffRepositoryImpl
 */
@Repository
public class OcmstoffRepositoryImpl extends RepositoryBase implements OcmstoffRepository {

	private final Map<String, FieldMapper> pgmSvcPfleMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("PROGRAM_PROFILE_CODE", new FieldMapper("programProfileCode"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("PROGRAM_ID", new FieldMapper("programId"))
			.put("PROGRAM_PROFILE_TYPE", new FieldMapper("programProfileType"))
			.put("PROFILE_GENDER_CODE", new FieldMapper("profileGenderCode"))
			.put("PROFILE_ETI_CITY_CODE", new FieldMapper("profileEtiCityCode"))
			.put("PROFILE_AGE_RANGE_CODE", new FieldMapper("profileAgeRangeCode"))
			.put("PROFILE_FACILITY_CODE", new FieldMapper("profileFacilityCode"))
			.put("PROFILE_IN_GROUP_CODE", new FieldMapper("profileInGroupCode"))
			.put("PROFILE_EX_GROUP_CODE", new FieldMapper("profileExGroupCode"))

			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmstoffRepositoryImpl.class.getName());

	/**
	 * Creates new OcmstoffRepositoryImpl class Object
	 */
	public OcmstoffRepositoryImpl() {
		// constructor
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            ProgramServicesProfiles
	 *
	 * @return List<ProgramServicesProfiles>
	 *
	 * @throws SQLException
	 */
	public List<ProgramServicesProfiles> prgPrfGdExecuteQuery(final ProgramServicesProfiles objSearchDao) {
		final String sql = getQuery("OCMSTOFF_PRGPRFGD_FIND_PROGRAM_SERVICES_PROFILES");
		final RowMapper<ProgramServicesProfiles> pgmSvcePfleMaper = Row2BeanRowMapper.makeMapping(sql,
				ProgramServicesProfiles.class, pgmSvcPfleMaping);
		return (ArrayList<ProgramServicesProfiles>) namedParameterJdbcTemplate.query(sql, createParams("programId",
				objSearchDao.getProgramId(), "programProfileType", objSearchDao.getProgramProfileType()),
				pgmSvcePfleMaper);
	}

	public List<ProgramServicesProfiles> prgPrfRcExecuteQuery(final ProgramServicesProfiles objSearchDao) {
		final String sql = getQuery("OCMSTOFF_PRGPRFFR_FIND_PROGRAM_SERVICES_PROFILES");
		final RowMapper<ProgramServicesProfiles> pgmSvcePfleMaper = Row2BeanRowMapper.makeMapping(sql,
				ProgramServicesProfiles.class, pgmSvcPfleMaping);
		return (ArrayList<ProgramServicesProfiles>) namedParameterJdbcTemplate.query(sql, createParams("programId",
				objSearchDao.getProgramId(), "programProfileType", objSearchDao.getProgramProfileType()),
				pgmSvcePfleMaper);

	}

	public List<ProgramServicesProfiles> prgPrfIgExecuteQuery(final ProgramServicesProfiles objSearchDao) {
		final String sql = getQuery("OCMSTOFF_PRGPRFIG_FIND_PROGRAM_SERVICES_PROFILES");
		final RowMapper<ProgramServicesProfiles> pgmSvcePfleMaper = Row2BeanRowMapper.makeMapping(sql,
				ProgramServicesProfiles.class, pgmSvcPfleMaping);
		return (ArrayList<ProgramServicesProfiles>) namedParameterJdbcTemplate.query(sql, createParams("programId",
				objSearchDao.getProgramId(), "programProfileType", objSearchDao.getProgramProfileType()),
				pgmSvcePfleMaper);

	}

	public List<ProgramServicesProfiles> prgPrfXgExecuteQuery(final ProgramServicesProfiles objSearchDao) {
		final String sql = getQuery("OCMSTOFF_PRGPRFXG_FIND_PROGRAM_SERVICES_PROFILES");
		final RowMapper<ProgramServicesProfiles> pgmSvcePfleMaper = Row2BeanRowMapper.makeMapping(sql,
				ProgramServicesProfiles.class, pgmSvcPfleMaping);
		return (ArrayList<ProgramServicesProfiles>) namedParameterJdbcTemplate.query(sql, createParams("programId",
				objSearchDao.getProgramId(), "programProfileType", objSearchDao.getProgramProfileType()),
				pgmSvcePfleMaper);

	}

	public List<ProgramServicesProfiles> prgPrfFaExecuteQuery(final ProgramServicesProfiles objSearchDao) {
		final String sql = getQuery("OCMSTOFF_PRGPRFFA_FIND_PROGRAM_SERVICES_PROFILES");
		final RowMapper<ProgramServicesProfiles> pgmSvcePfleMaper = Row2BeanRowMapper.makeMapping(sql,
				ProgramServicesProfiles.class, pgmSvcPfleMaping);
		return (ArrayList<ProgramServicesProfiles>) namedParameterJdbcTemplate.query(sql, createParams("programId",
				objSearchDao.getProgramId(), "programProfileType", objSearchDao.getProgramProfileType()),
				pgmSvcePfleMaper);
	}

	public List<ProgramServicesProfiles> prgPrfAgExecuteQuery(final ProgramServicesProfiles objSearchDao) {
		final String sql = getQuery("OCMSTOFF_PRGPRFAG_FIND_PROGRAM_SERVICES_PROFILES");
		final RowMapper<ProgramServicesProfiles> pgmSvcePfleMaper = Row2BeanRowMapper.makeMapping(sql,
				ProgramServicesProfiles.class, pgmSvcPfleMaping);
		return (ArrayList<ProgramServicesProfiles>) namedParameterJdbcTemplate.query(sql, createParams("programId",
				objSearchDao.getProgramId(), "programProfileType", objSearchDao.getProgramProfileType()),
				pgmSvcePfleMaper);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstProgramServicesProfiles
	 *            List<ProgramServicesProfiles>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public ProgramServicesProfiles prgPrfGdInsertProgramServicesProfiles(final List<ProgramServicesProfiles> list) {
		final ProgramServicesProfiles returnData = new ProgramServicesProfiles();
		final String sql = getQuery("OCMSTOFF_PRGPRFGD_INSERT_PROGRAM_SERVICES_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ProgramServicesProfiles object : list) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}

		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (list.size() == returnArray.length) {
			returnData.setReturnValue(1);
			return returnData;
		} else {
			returnData.setReturnValue(0);
			return returnData;
		}

	}

	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OCMSTOFF_CONSTRAINT_VALIDATIONS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CONSTRAINTNAME", errorName),
					String.class);
		} catch (Exception e) {
			logger.error("errorNameValidation", e);
			returnData = null;
			return returnData;
		}
		return returnData;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstProgramServicesProfiles
	 *            List<ProgramServicesProfiles>
	 *
	 * @throws SQLException
	 */
	public Integer prgPrfGdUpdateProgramServicesProfiles(final List<ProgramServicesProfiles> list) {
		final String sql = getQuery("OCMSTOFF_PRGPRFGD_UPDATE_PROGRAM_SERVICES_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ProgramServicesProfiles object : list) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstProgramServicesProfiles
	 *            List<ProgramServicesProfiles>
	 *
	 * @throws SQLException
	 */
	public ProgramServicesProfiles prgPrfGdDeleteProgramServicesProfiles(final List<ProgramServicesProfiles> list) {
		final ProgramServicesProfiles returnData = new ProgramServicesProfiles();
		final String sql = getQuery("OCMSTOFF_PRGPRFGD_DELETE_PROGRAM_SERVICES_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ProgramServicesProfiles object : list) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		try {
			String tableName = "PROGRAM_SERVICES_PROFILES";
			String whereClause = "PROGRAM_ID  = :programId AND PROGRAM_PROFILE_TYPE  = :programProfileType AND PROGRAM_PROFILE_CODE  = :programProfileCode";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method prgPrfGdDeleteProgramServicesProfiles", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("constraint")) {
				error = error.substring(error.indexOf("constraint"), error.indexOf("\" on"))
						.replaceFirst("constraint", "").trim();
				final String tableName = errorNameValidation(error.substring(1, error.length()));
				returnData.setSealFlag(tableName);
				returnData.setServerCode(2292);
				logger.error("agyVisitSlotsDeleteAgencyVisitSlots", e);
				return returnData;
			}
		}

		if (list.size() == returnArray.length) {
			returnData.setReturnValue(1);
			return returnData;
		} else {
			returnData.setReturnValue(0);
			return returnData;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPsSexRecordGroup() {
		final String sql = getQuery("OCMSTOFF_FIND_RGPSSEX");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgPsSexRecordGroup : ocmstoff", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgEthnicityRecordGroup() {
		final String sql = getQuery("OCMSTOFF_FIND_RGETHNICITY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgEthnicityRecordGroup : ocmstoff", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPsNeedsRecordGroup() {
		final String sql = getQuery("OCMSTOFF_FIND_RGPSNEEDS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgPsNeedsRecordGroup : ocmstoff", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPsAgeRangeRecordGroup() {
		final String sql = getQuery("OCMSTOFF_FIND_RGPSAGERANGE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgPsAgeRangeRecordGroup : ocmstoff", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPsOffGrpsRecordGroup() {
		final String sql = getQuery("OCMSTOFF_FIND_RGPSOFFGRPS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgPsOffGrpsRecordGroup : ocmstoff", e);
			return Collections.emptyList();
		}
	}

	public Integer getProfileExist(final ProgramServicesProfiles objSearchDaos) {
		final String sql = getQuery("OCMSTOFF_PROFILES_EXIST_COUNT");

		Integer data = null;
		try {
			data = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_code", objSearchDaos.getpCode(),
					"p_type", objSearchDaos.getpType(), "p_program_id", objSearchDaos.getProgramId()), Integer.class);
		} catch (Exception e) {
			logger.error("getProfileExist : ocmstoff", e);
			return data;
		}

		return data;
	}

}