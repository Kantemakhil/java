package net.syscon.s4.cm.programsservices.maintenance.impl;

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
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.maintenance.OcmsvphaRepository;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.VProgramPhases;

/**
 * Class OcmsvphaRepositoryImpl
 */
@Repository
public class OcmsvphaRepositoryImpl extends RepositoryBase implements OcmsvphaRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmsvphaRepositoryImpl.class);

	private final Map<String, FieldMapper> vProgramPhasesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();

	/**
	 * Creates new OcmsvphaRepositoryImpl class Object
	 */
	public OcmsvphaRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VProgramPhases
	 *
	 * @return List<VProgramPhases>
	 */
	public List<VProgramPhases> vPrgPhssExecuteQuery(final VProgramPhases objSearchDao) {
		final String sql = getQuery("OCMSVPHA_VPRGPHSS_FIND_V_PROGRAM_PHASES");
		final RowMapper<VProgramPhases> VProgramPhasesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VProgramPhases.class, vProgramPhasesMapping);
		List<VProgramPhases> returnList = new ArrayList<VProgramPhases>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("programId", objSearchDao.getProgramId()),
				VProgramPhasesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstVProgramPhases List<VProgramPhases>
	 *
	 * @return List<Integer>
	 */
	@Override
	public VProgramPhases vPrgPhssInsertVProgramPhases(List<VProgramPhases> lstVProgramModules) {
		int[] returnArray = new int[] {};
		String query = getQuery("OCMSVPHA_VPRGPHSS_INSERT_V_PROGRAM_PHASES");
		VProgramPhases returnData = new VProgramPhases();
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VProgramPhases vProgMod : lstVProgramModules) {
			parameters.add(new BeanPropertySqlParameterSource(vProgMod));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(query, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("vPrgMdlsInsertVProgramModules:", e);
		}
		if (lstVProgramModules.size() == returnArray.length) {
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
	 * @param lstVProgramPhases List<VProgramPhases>
	 */
	public VProgramPhases vPrgPhssUpdateVProgramPhases(final List<VProgramPhases> lstVProgramPhases) {
		int[] returnArray = new int[] {};
		String sql = getQuery("OCMSVPHA_VPRGPHSS_UPDATE_V_PROGRAM_PHASES");
		VProgramPhases returnData = new VProgramPhases();
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VProgramPhases vProgramPhases : lstVProgramPhases) {
			parameters.add(new BeanPropertySqlParameterSource(vProgramPhases));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("vPrgMdlsUpdateVProgramModules:", e);
		}
		if (lstVProgramPhases.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstVProgramPhases List<VProgramPhases>
	 */
	public VProgramPhases vPrgPhssDeleteVProgramPhases(final List<VProgramPhases> lstVProgramPhases) {
		final String sql = getQuery("OCMSVPHA_VPRGPHSS_DELETE_V_PROGRAM_PHASES");
		VProgramPhases returnData = new VProgramPhases();
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VProgramPhases vProgramPhases : lstVProgramPhases) {
			parameters.add(new BeanPropertySqlParameterSource(vProgramPhases));
		}
		try {
			String tableName = "program_services";
			String whereClause = "PROGRAM_ID=:programPhaseId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method vPrgPhssDeleteVProgramPhases", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("constraint")) {
				error = error.substring(error.indexOf("constraint"), error.indexOf("\" on"))
						.replaceFirst("constraint", "").trim();
				final String constraint  = error.substring(1, error.length());
				returnData.setSealFlag(constraint);
				returnData.setSeqOne(BigDecimal.valueOf(2292));
				return returnData;
			}
		}
		if (lstVProgramPhases.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgPsModTypeRecordGroup() {
		final String sql = getQuery("OCMSVPHA_FIND_RGPSMODTYPE");
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
	public List<ReferenceCodes> rgPsPhaseRecordGroup() {
		final String sql = getQuery("OCMSVPHA_FIND_RGPSPHASE");
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
	 * @return String
	 *
	 * @param sessionLength
	 *
	 */
	public String getDescription(final String code) {
		final String sql = getQuery("OCMSVPHA_GET_DESCRIPTION");
		String returnList = new String();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("CODE", code), String.class);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return String
	 *
	 * @param sessionLength
	 *
	 */
	public String getDescriptionOne(final String code) {
		final String sql = getQuery("OCMSVPHA_GET_DESCRIPTION_ONE");
		String returnList = new String();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("CODE", code), String.class);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is used to get max count of listSeq
	 * 
	 * @param programPhaseId
	 * @return Integer
	 */
	public Integer getListSeqMaxCount(BigDecimal programId) {
		final String sql = getQuery("OCMSVPHA_GET_NEXT_PHASE_LIST_SEQ");
		int returnVal1 = 0;
		try {
			returnVal1 = namedParameterJdbcTemplate.queryForObject(sql, createParams("programId", programId),
					Integer.class);
		} catch (final Exception e) {
			logger.error(e);
			return returnVal1;
		}
		return returnVal1;

	}

	public Integer getCourceActivityCount(BigDecimal programId) {
		final String sql = getQuery("OCMSVPHA_COUNT_OF_COURCE_ACTIVITY");
		int returnVal1 = 0;
		try {
			returnVal1 = namedParameterJdbcTemplate.queryForObject(sql, createParams("programId", programId),
					Integer.class);
		} catch (final Exception e) {
			logger.error(e);
			return returnVal1;
		}
		return returnVal1;

	}

	/**
	 * This method is used to get the programPhaseId from data base tables based on
	 * 
	 * @return int
	 */
	public BigDecimal getProgramIdSeq() {
		final String sql = getQuery("OCMSVPHA_PROGRAM_ID");
		BigDecimal returnVal1 = null;
		try {
			returnVal1 = namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
		} catch (Exception e) {
			logger.error(e);
			return returnVal1;
		}
		return returnVal1;

	}

	public String getModuleTypeDescription(final String code) {
		final String sql = getQuery("OCMSVPHA_MODULE_TYPE");
		String returnList = new String();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("CODE", code), String.class);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OCMSVPHA_CONSTRAINT_VALIDATIONS");
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

}
