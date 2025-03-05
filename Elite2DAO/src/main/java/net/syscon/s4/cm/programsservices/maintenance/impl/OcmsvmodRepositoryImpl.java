package net.syscon.s4.cm.programsservices.maintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.maintenance.OcmsvmodRepository;
import net.syscon.s4.cm.programsservices.maintenance.VProgramModules;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;

@Repository
public class OcmsvmodRepositoryImpl extends RepositoryBase implements OcmsvmodRepository {

	/**
	 * Creates new OcmsvmodRepositoryImpl class Object
	 */
	public OcmsvmodRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmsvmodRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vProgramModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VProgramModules
	 *
	 * @return List<VProgramModules>
	 *
	 * @throws SQLException
	 */
	public List<VProgramModules> vPrgMdlsExecuteQuery(VProgramModules objSearchDao) {
		final String sql = getQuery("OCMSVMOD_VPRGMDLS_FIND_V_PROGRAM_MODULES");
		final RowMapper<VProgramModules> VProgramModulesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VProgramModules.class, vProgramModulesMapping);
		final ArrayList<VProgramModules> returnList = (ArrayList<VProgramModules>) namedParameterJdbcTemplate.query(sql,
				createParams("programPhaseId", objSearchDao.getProgramPhaseId()), VProgramModulesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstVProgramModules List<VProgramModules>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	@Override
	public VProgramModules vPrgMdlsInsertVProgramModules(List<VProgramModules> lstVProgramModules) {
		int[] returnArray = new int[] {};
		String query = getQuery("OCMSVMOD_INSERT_MODULES");
		VProgramModules returnData = new VProgramModules();
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VProgramModules vProgMod : lstVProgramModules) {
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
	 * @param lstVProgramModules List<VProgramModules>
	 *
	 * @return VProgramModules
	 */
	public VProgramModules vPrgMdlsUpdateVProgramModules(final List<VProgramModules> lstVProgramModules) {
		int[] returnArray = new int[] {};
		String sql = getQuery("OCMSVMOD_VPRGMDLS_UPDATE_V_PROGRAM_MODULES");
		VProgramModules returnData = new VProgramModules();
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VProgramModules vProgramModules : lstVProgramModules) {
			parameters.add(new BeanPropertySqlParameterSource(vProgramModules));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("vPrgMdlsUpdateVProgramModules:", e);
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
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstVProgramModules List<VProgramModules>
	 *
	 * @return VProgramModules
	 */
	public VProgramModules vPrgMdlsDeleteVProgramModules(final List<VProgramModules> lstVProgramModules) {
		final String sql = getQuery("OCMSVMOD_VPRGMDLS_DELETE_V_PROGRAM_MODULES");
		VProgramModules returnData = new VProgramModules();
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VProgramModules vProgramModules : lstVProgramModules) {
			parameters.add(new BeanPropertySqlParameterSource(vProgramModules));
		}
		try {
			String tableName = "program_services";
			String whereClause = "PROGRAM_ID=:programModuleId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method vPrgMdlsDeleteVProgramModules", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("violates foreign key constraint")) {
				final String tableName = error.substring(error.lastIndexOf("is still referenced from table \""), error.lastIndexOf("\".")).replaceFirst("is still referenced from table \"", "");
				returnData.setSealFlag(tableName.toUpperCase());
				returnData.setSeqOne(BigDecimal.valueOf(2292));
				return returnData;
			}
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
	 * This method is used to get the programModuleId from data base tables based on
	 * 
	 * @return int
	 */
	public Integer getProgramIdSeq() {
		final String sql = getQuery("OCMSVMOD_GETPROGRAMID");
		Integer returnVal1 = 0;
		returnVal1 = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return returnVal1;

	}

	/**
	 * This method is used to get the getSessionCount from data base tables based on
	 * 
	 * @param objSearchDao
	 * @return int
	 */

	public Integer getSessionCount(VProgramModules objSearchDao) {
		final String sql = getQuery("OCMSVMOD_GET_SESSION_COUNT");
		int returnVal1 = 0;
		try {
			returnVal1 = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("programPhaseId", objSearchDao.getProgramPhaseId()), Integer.class);
		} catch (final Exception e) {
			return returnVal1;
		}
		return returnVal1;

	}

	/**
	 * This method is used to updating noOfSession in v_program_phase the data base
	 * tables based on
	 *
	 * @param programPhaseId,sessionCount
	 *
	 * @return int
	 */
	public int doUpdateOnPhase(int programPhaseId, int sessionCount) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		int returnValue = 0;
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter(" p_program_phase_id", Types.INTEGER),
				new SqlParameter("p_total", Types.INTEGER), new SqlOutParameter("updateValue", Types.INTEGER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("oms_owner")
				.withCatalogName("TAG_SERVICE").withProcedureName("DO_UPDATE_ON_PHASE")
				.declareParameters(sqlParameters);

		inParamMap.put("p_program_phase_id", programPhaseId);
		inParamMap.put("p_total", sessionCount);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			simpleJDBCCall.execute(inParameter);
			returnValue = 1;
		} catch (final Exception e) {
			logger.error(e);
			returnValue = 0;
		}
		return returnValue;
	}

	@Override
	public Integer checkUniqueListSeq(VProgramModules objSearchDao) {
		Integer returnVal1 = 0;
		final String sql = getQuery("OCMSVMOD_CHECKUNIQUE_LIST_SEQ");
		returnVal1 = namedParameterJdbcTemplate.queryForObject(
				sql, createParams("programPhaseId", objSearchDao.getProgramPhaseId(), "listSeq",
						objSearchDao.getListSeq(), "programModuleId", objSearchDao.getProgramModuleId()),
				Integer.class);
		return returnVal1;
	}

	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OCMSVMOD_CONSTRAINT_VALIDATIONS");
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
	 * This method is used to get max count of listSeq
	 * 
	 * @param programPhaseId
	 * @return Integer
	 */
	public Integer getListSeqMaxCount(Integer programPhaseId) {
		final String sql = getQuery("OCMSVMOD_LISTSEQ");
		int returnVal1 = 0;
		try {
			returnVal1 = namedParameterJdbcTemplate.queryForObject(sql, createParams("programPhaseId", programPhaseId),
					Integer.class);
		} catch (final Exception e) {
			logger.error(e);
			return returnVal1;
		}
		return returnVal1;

	}

}
