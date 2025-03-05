package net.syscon.s4.inst.institutionalactivities.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.VOffenderProgramProfiles;
import net.syscon.s4.inst.institutionalactivities.OcupaoffRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OcupaoffRepositoryImpl
 * 
 */
@Repository
public class OcupaoffRepositoryImpl extends RepositoryBase implements OcupaoffRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcupaoffRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vOffenderProgramProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).put("OFFENDER_NAME", new FieldMapper("offenderName"))
			.put("AGE", new FieldMapper("age")).put("OFF_PRGREF_ID", new FieldMapper("offPrgrefId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("FIRST_NAME", new FieldMapper("firstName")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("SEX_CODE", new FieldMapper("sexCode")).put("RACE_CODE", new FieldMapper("raceCode"))
			.put("OFFENDER_ALERT", new FieldMapper("offenderAlert"))
			.put("OFFENCE_TYPES", new FieldMapper("offenceTypes"))
			.put("OFFENDER_PROGRAM_STATUS", new FieldMapper("offenderProgramStatus"))
			.put("OFFENDER_START_DATE", new FieldMapper("offenderStartDate"))
			.put("OFFENDER_END_DATE", new FieldMapper("offenderEndDate"))
			.put("SUSPENDED_FLAG", new FieldMapper("suspendedFlag"))

			.build();

	/**
	 * Creates new OcupaoffRepositoryImpl class Object
	 */
	public OcupaoffRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffenderProgramProfiles
	 *
	 * @return List<VOffenderProgramProfiles>
	 *
	 */
	public List<VOffenderProgramProfiles> vOffPrgProfilesExecuteQuery(final VOffenderProgramProfiles objSearchDao) {
		final String sql = getQuery("OCUPAOFF_VOFFPRGPROFILES_FIND_V_OFFENDER_PROGRAM_PROFILES");
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE CRS_ACTY_ID =:crsActyId AND OFFENDER_PROGRAM_STATUS = 'ALLOC' AND ");
			inParameterSource.addValue("crsActyId", objSearchDao.getCrsActyId());
		}
		if (objSearchDao.getCrsActyId() != null) {
			sqlQuery.append(
					" ((CRS_ACTY_ID = :crsActyId AND OFFENDER_PROGRAM_STATUS = 'ALLOC' AND   ((SUSPENDED_FLAG='N' AND OFFENDER_END_DATE IS NULL) OR ((OFFENDER_END_DATE) > (current_timestamp))))"
							+ " AND (CRS_ACTY_ID = :crsActyId AND OFFENDER_PROGRAM_STATUS = 'ALLOC'  AND (SUSPENDED_FLAG='N' OR (OFFENDER_END_DATE) > (current_timestamp)))) "
							+ " AND ");
			inParameterSource.addValue("crsActyId", objSearchDao.getCrsActyId());
		}
		if (objSearchDao.getOffenderName() != null && !objSearchDao.getOffenderName().trim().equals("")) {
			sqlQuery.append(" OFFENDER_NAME = :OFFENDER_NAME ");
			inParameterSource.addValue("OFFENDER_NAME", objSearchDao.getOffenderName());
		}
		preparedSql = sqlQuery.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ORDER BY OFFENDER_NAME ASC";
		final RowMapper<VOffenderProgramProfiles> VOffenderProgramProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderProgramProfiles.class, vOffenderProgramProfilesMapping);
		List<VOffenderProgramProfiles> returnList = new ArrayList<VOffenderProgramProfiles>();
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, inParameterSource,
					VOffenderProgramProfilesRowMapper);
		} catch (Exception e) {
			logger.error("vOffPrgProfilesExecuteQuery", e);
		}

		return returnList;

	}

	public List<VOffenderProgramProfiles> postQuery(final VOffenderProgramProfiles object) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		List<VOffenderProgramProfiles> returnValue = null;
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_GENDER_CODE", OracleTypes.VARCHAR),
				new SqlParameter("P_ETHNICITY_CODE", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_GENDER_DESC", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_ETHNICITY_DESC", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCUPAOFF").withFunctionName("GET_OFFENDER_DETAILS").declareParameters(sqlParameters);
		inParamMap.put("P_GENDER_CODE", object.getSexCode());
		inParamMap.put("P_ETHNICITY_CODE", object.getRaceCode());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		returnValue = simpleJDBCCall.executeFunction(List.class, inParameter);
		return returnValue;
	}
}
