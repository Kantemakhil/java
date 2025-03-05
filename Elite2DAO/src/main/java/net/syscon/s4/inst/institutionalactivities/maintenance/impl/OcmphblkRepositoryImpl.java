package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmphblkRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OcmphblkRepositoryImpl
 */
@Repository
public class OcmphblkRepositoryImpl extends RepositoryBase implements OcmphblkRepository {

	/**
	 * Creates new OcmphblkRepositoryImpl class Object
	 */
	public OcmphblkRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmphblkRepositoryImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            ProgramServices
	 *
	 * @return List<ProgramServices>
	 *
	 * 
	 */

	private final Map<String, FieldMapper> programServicesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("PARENT_PROGRAM_ID", new FieldMapper("parentProgramId")).build();

	public List<ProgramServices> prgSrvExecuteQuery(final ProgramServices objSearchDao) {
		final String sql = getQuery("OCMPHBLK_PRGSRV_FIND_PROGRAM_SERVICES");
		final RowMapper<ProgramServices> programServicesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ProgramServices.class, programServicesMapping);
		List<ProgramServices> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("programId", objSearchDao.getParentProgramId()), programServicesRowMapper);
		} catch (final Exception e) {
			logger.error("prgSrvExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstProgramServices
	 *            List<ProgramServices>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer prgSrvInsertProgramServices(final List<ProgramServices> lstProgramServices) {
		final String sql = getQuery("OCMPHBLK_PRGSRV_INSERT_PROGRAM_SERVICES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final ProgramServices programServices : lstProgramServices) {
			parameters.add(new BeanPropertySqlParameterSource(programServices));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstProgramServices.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstProgramServices
	 *            List<ProgramServices>
	 *
	 * 
	 */
	public Integer prgSrvUpdateProgramServices(final List<ProgramServices> lstProgramServices) {
		final String sql = getQuery("OCMPHBLK_PRGSRV_UPDATE_PROGRAM_SERVICES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final ProgramServices programServices : lstProgramServices) {
			parameters.add(new BeanPropertySqlParameterSource(programServices));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstProgramServices.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstProgramServices
	 *            List<ProgramServices>
	 *
	 * 
	 */
	public Integer prgSrvDeleteProgramServices(final List<ProgramServices> lstProgramServices) {
		final String sql = getQuery("OCMPHBLK_PRGSRV_DELETE_PROGRAM_SERVICES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final ProgramServices programServices : lstProgramServices) {
			parameters.add(new BeanPropertySqlParameterSource(programServices));
		}
		try {
			String tableName = "PROGRAM_SERVICES";
			String whereClause = "PROGRAM_ID=:programId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method prgSrvDeleteProgramServices", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstProgramServices.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public Integer getNextPrgSrvListSeq(final ProgramServices object) {
		Map<String, Object> returnObject = null;
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<>();
		final SqlParameter[] sqlParameters = new SqlParameter[] { new SqlInOutParameter("RETURN_VALUE", OracleTypes.NUMBER),
				new SqlParameter("P_PARENT_PROG_ID", OracleTypes.NUMBER), };

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE").withFunctionName("GET_NEXT_PRG_SRV_LIST_SEQ")
				.declareParameters(sqlParameters);
		inParamMap.put("RETURN_VALUE", 0);
		inParamMap.put("P_PARENT_PROG_ID", object.getParentProgramId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (!returnObject.isEmpty() && returnObject.get("RETURN_VALUE") != null) {
				final BigDecimal seqVal = (BigDecimal) returnObject.get("RETURN_VALUE");
				returnValue = seqVal.intValue();
			}
		} catch (final Exception e) {
			returnValue = 0;
		}
		return returnValue;

	}

	public Integer checkNextPrgSrvSeqUnique(final ProgramServices object) {
		Map<String, Object> returnObject = null;
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<>();
		final SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_PARENT_PROG_ID", OracleTypes.NUMBER),
				new SqlParameter("P_LIST_SEQ", OracleTypes.NUMBER), };

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE").withFunctionName("CHECK_NEXT_PRG_SRV_SEQ_UNIQUE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_PARENT_PROG_ID", object.getParentProgramId());
		inParamMap.put("P_LIST_SEQ", object.getListSeq());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
		}
		return returnValue;

	}

	public Integer getProgramIdSeq(final ProgramServices object) {
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<>();
		final SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.NUMBER), };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE").withFunctionName("GET_PROGRAM_ID_SEQ").declareParameters(sqlParameters);
		inParamMap.put("RETURN_VALUE", 0);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		BigDecimal returnValue1 = simpleJDBCCall.executeFunction(BigDecimal.class, inParameter);
		returnValue = returnValue1.intValue();
		return returnValue;
	}

	@Override
	public Integer getCheckNextPrgSrvSeqUnique(final ProgramServices object) {
		final String sql = getQuery("OCMPHBLK_CHECK_NEXT_PRG_SRV_SEQ_UNIQUE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pParentProgId", object.getParentProgramId(), "pListSeq", object.getListSeq()),
					Integer.class);
		} catch (final Exception e) {
			logger.error("getCheckNextPrgSrvSeqUnique", e);
			return null;
		}
	}

}
