package net.syscon.s4.cm.programsservices.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.OidpwaitRepository;
import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import oracle.jdbc.internal.OracleTypes;

@Repository
public class OidpwaitRepositoryImpl extends RepositoryBase implements OidpwaitRepository {

	public OidpwaitRepositoryImpl(JdbcTemplate jdbcTemplate) {
	}

	private static Logger logger = LogManager.getLogger(OidpwaitRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> serviceMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();

	private final Map<String, FieldMapper> teamResMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("TEAM_ID", new FieldMapper("serverCode"))
			.put("CODE", new FieldMapper("code")).build();

	private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("PROGRAM_ID", new FieldMapper("programId "))
			.put("DESCRIPTION", new FieldMapper("description ")).build();

	public List<ProgramServices> rgProgramServicesRecordGroup() {
		final String sql = getQuery("OIDPWAIT_FIND_RGPROGRAMSERVICES");
		List<ProgramServices> returnList = new ArrayList<ProgramServices>();

		final RowMapper<ProgramServices> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				serviceMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgProgramServicesRecordGroup :", e);
		}
		return returnList;
	}

	public List<Areas> rgRegionRecordGroup() {
		final String sql = getQuery("OIDPWAIT_FIND_RGREGION");

		List<Areas> returnList = new ArrayList<Areas>();
		final RowMapper<Areas> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, serviceMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgRegionRecordGroup :", e);
		}
		return returnList;
	}

	public List<ProgramServices> rgAreasRecordGroup(String caseLoadType, String regionCode) {
		final String sql = getQuery("OIDPWAIT_FIND_RGAREAS");

		List<ProgramServices> returnList = new ArrayList<ProgramServices>();
		final RowMapper<ProgramServices> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				serviceMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CLT", caseLoadType, "RC", regionCode),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgAreasRecordGroup :", e);
		}
		return returnList;
	}

	public List<AgencyLocations> rgAgyLocsRecordGroup(String caseLoadType, String regionCode, String areaCode) {
		final String sql = getQuery("OIDPWAIT_FIND_RGAGYLOCS");
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();

		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				serviceMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("CLT", caseLoadType, "RC", regionCode, "AC", areaCode), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgAgyLocsRecordGroup :", e);
		}
		return returnList;
	}

	public List<Areas> rgAllTeamsRecordGroup() {
		final String sql = getQuery("OIDPWAIT_FIND_RGALLTEAMS");
		List<Areas> returnList = new ArrayList<Areas>();

		final RowMapper<Areas> mMMRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, teamResMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mMMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgAllTeamsRecordGroup :", e);
		}
		return returnList;
	}

	public List<VOffenderPrgObligations> vOffPrgOblExecuteQuery(VOffenderPrgObligations vOffPrgObl) {
		final String sql = getQuery("OIDPWAIT_VOFFPRGOBL_FIND_V_OFFENDER_PRG_OBLIGATIONS");
		List<VOffenderPrgObligations> returnList = new ArrayList<VOffenderPrgObligations>();

		String preparedSql = null;

		final StringBuffer sqlQuery = new StringBuffer(sql);
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();

		if (vOffPrgObl.getNbtActivityCode() != null) {
			sqlQuery.append(" PROGRAM_ID = :PID AND ACTIVITY_CODE = :ACTICODE" + " AND ");
			inParameterSource.addValue("PID", vOffPrgObl.getProgramId());
			inParameterSource.addValue("ACTICODE", vOffPrgObl.getNbtActivityCode());
		}

		if (vOffPrgObl.getNbtAgyLocId() != null && vOffPrgObl.getNbtAgyLocId() != "") {
			sqlQuery.append("  OFFENDER_AGY_LOC_ID  = :ALID" + " AND ");
			inParameterSource.addValue("ALID", vOffPrgObl.getNbtAgyLocId());
		}

		if (vOffPrgObl.getTeamId() != null) {
			sqlQuery.append(
					"  OFFENDER_BOOK_ID IN  (SELECT OFFENDER_BOOK_ID FROM OFFENDER_TEAM_ASSIGNMENTS  WHERE TEAM_ID = :TEAMCODE )"
							+ " AND ");
			inParameterSource.addValue("TEAMCODE", vOffPrgObl.getTeamId());
		} else if (vOffPrgObl.getNbtArea() != null && vOffPrgObl.getNbtArea() != "") {
			if ("INST".equals(vOffPrgObl.getCaseLoadType())) {
				sqlQuery.append(
						"  OFFENDER_AGY_LOC_ID IN ( SELECT AGY_LOC_ID FROM AGENCY_LOCATIONS WHERE AREA_CODE = :AREACODE )"
								+ " AND ");
				inParameterSource.addValue("AREACODE", vOffPrgObl.getNbtArea());
			} else {
				sqlQuery.append(
						" OFFENDER_COMMUNITY_AGY_LOC_ID IN ( SELECT AGY_LOC_ID FROM AGENCY_LOCATIONS WHERE AREA_CODE = :AREACODE )"
								+ " AND ");
				inParameterSource.addValue("AREACODE", vOffPrgObl.getNbtArea());
			}
		} else if (vOffPrgObl.getNbtRegion() != null && vOffPrgObl.getNbtRegion() != "") {
			if ("INST".equals(vOffPrgObl.getCaseLoadType())) {
				sqlQuery.append(
						"  OFFENDER_AGY_LOC_ID IN ( SELECT AGY_LOC_ID FROM AGENCY_LOCATIONS WHERE NOMS_REGION_CODE = :RECODE )"
								+ " AND ");
				inParameterSource.addValue("RECODE", vOffPrgObl.getNbtRegion());
			} else {
				sqlQuery.append(
						" OFFENDER_COMMUNITY_AGY_LOC_ID IN ( SELECT AGY_LOC_ID FROM AGENCY_LOCATIONS WHERE NOMS_REGION_CODE = :RECODE ) "
								+ " AND ");
				inParameterSource.addValue("RECODE", vOffPrgObl.getNbtRegion());
			}
		}

		preparedSql = sqlQuery.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY  OFFENDER_NAME ");

		final RowMapper<VOffenderPrgObligations> VOffenderPrgObligationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderPrgObligations.class, mMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, inParameterSource,
					VOffenderPrgObligationsRowMapper);
		} catch (Exception e) {
			logger.error("vOffPrgOblExecuteQuery :", e);
		}
		return returnList;
	}

	@Override
	public BigDecimal getOffenderIdCur(BigDecimal offenderBookId) {
		final String sql = getQuery("OIDPWAIT_FIND_OFFENDER_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFBOOKID", offenderBookId),
				BigDecimal.class);
	}

	public VOffenderPrgObligations vOffPrgOblUpdateVOffenderPrgObligations(List<VOffenderPrgObligations> vOff) {
		String sql = getQuery("OIDPWAIT_VOFFPRGOBL_UPDATE_V_OFFENDER_PRG_OBLIGATIONS");

		final VOffenderPrgObligations returnData = new VOffenderPrgObligations();

		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (VOffenderPrgObligations vOffenderPrgObligations : vOff) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderPrgObligations));
		}

		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("OMS_OWNER")) {
				error = error.substring(error.indexOf("OMS_OWNER."), error.indexOf(") violated"))
						.replaceFirst("OMS_OWNER.", "");
				final String tableName = errorNameValidationTimes(error);
				returnData.setSealFlag(tableName);
				returnData.setServerCode(2292);
				logger.error("vOffPrgOblUpdateVOffenderPrgObligations :", e);
				return returnData;
			}
		}
		if (vOff.size() == returnArray.length) {
			returnData.setReturnValue(1);
			return returnData;
		} else {
			returnData.setReturnValue(0);
			return returnData;
		}
	}

	public String errorNameValidationTimes(final String errorName) {
		final String sql = getQuery("OIDPWAIT_CONSTRAINT_VALIDATIONS_TIMES");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CONSTRAINTNAME", errorName),
					String.class);
		} catch (Exception e) {
			logger.error("errorNameValidationTimes", e);
			returnData = null;
			return returnData;
		}
		return returnData;
	}

	public List<ReferenceCodes> rgPsPrgAvailRecordGroup() {
		final String sql = getQuery("OIDPWAIT_FIND_RGPSPRGAVAIL");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();

		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				serviceMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgPsPrgAvailRecordGroup :", e);
		}
		return returnList;
	}

	public List<ReferenceCodes> rgRestrictTeamsRecordGroup(String regionCode, String areaCode) {
		final String sql = getQuery("OIDPWAIT_FIND_RGRESTRICTTEAMS");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				teamResMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("RC", regionCode, "AC", areaCode),
					mMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgRestrictTeamsRecordGroup :", e);
		}
		return returnList;
	}

	public Object vOffPrgOblPostQuery(Offenders paramBean) {
		final String sql = getQuery("OIDPWAIT_V_OFF_PRG_OBL_POSTQUERY");
		final RowMapper<Offenders> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offendersMapping);
		Offenders returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	@Override
	public Integer clearTempList() {
		int count = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("NON_ASSOCIATION").withProcedureName("CLEAR_TEMP_LIST");
		try {
			simpleJDBCCall.execute(inParameter);
			count = 1;
		} catch (Exception e) {
			logger.error("clearTempList :", e);
			count = 0;
		}
		return count;
	}

	@Override
	public Integer addToProgramList(VOffenderPrgObligations vOff) {
		int count = 0;
		Map<String, Object> returnObject = null;

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];

		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PRG_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CRS_ACTY_ID", OracleTypes.NUMBER) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("NON_ASSOCIATION").withProcedureName("ADD_TO_PROGRAM_LIST")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_ID", vOff.getOffenderId());
		inParamMap.put("P_OFFENDER_BOOK_ID", vOff.getOffenderBookId());
		inParamMap.put("P_PRG_ID", vOff.getProgramId());
		inParamMap.put("P_CRS_ACTY_ID", null);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				count = 1;
			}
		} catch (Exception e) {
			count = 0;
			logger.error("addToProgramList :", e);
		}
		return count;
	}

	@Override
	public Integer removeFromProgramList(VOffenderPrgObligations vOff) {
		int count = 0;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];

		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PRG_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CRS_ACTY_ID", OracleTypes.NUMBER) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("NON_ASSOCIATION").withProcedureName("REMOVE_FROM_PROGRAM_LIST")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_ID", vOff.getOffenderId());
		inParamMap.put("P_OFFENDER_BOOK_ID", vOff.getOffenderBookId());
		inParamMap.put("P_PRG_ID", vOff.getProgramId());
		inParamMap.put("P_CRS_ACTY_ID", null);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				count = 1;
			}
		} catch (Exception e) {
			count = 0;
			logger.error("removeFromProgramList :", e);
		}
		return count;
	}

	@Override
	public BigDecimal postUpdate(VOffenderPrgObligations vOff) {
		final String sql = getQuery("OIDPWAIT_GET_CHECK_SUM");
		BigDecimal CSUM = null;
		try {
			CSUM = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFPRGOBLD", vOff.getOffenderPrgObligationId()), BigDecimal.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("lvLoginUserStaffId", e);
		}
		return CSUM;
	}

	@Override
	public Integer getSelectedServices() {
		int count = 0;
		Object returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withFunctionName("GET_SELECTED_SERVICES");
		try {
			returnObject = simpleJDBCCall.executeFunction(Object.class, inParamMap);

		} catch (Exception e) {
			logger.error("getSelectedServices", e);
			count = 0;
		}
		return count;
	}

	@Override
	public List<ReferenceCodes> defaultAgency(String curCaseLoadId) {
		Map<String, Object> returnObject = null;
		List<ReferenceCodes> list = new ArrayList<ReferenceCodes>();

		final ReferenceCodes bean = new ReferenceCodes();

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];

		sqlParameters = new SqlParameter[] { new SqlParameter("P_GLOBAL_CASELOAD_ID", OracleTypes.VARCHAR),
				new SqlOutParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlOutParameter("P_DESCRIPTION", OracleTypes.VARCHAR), };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_ESTABLISHMENT").withProcedureName("DEFAULT_AGENCY")
				.declareParameters(sqlParameters);

		inParamMap.put("P_GLOBAL_CASELOAD_ID", curCaseLoadId);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);

			bean.setCode(
					returnObject.get("P_AGY_LOC_ID") != null ? String.valueOf(returnObject.get("P_AGY_LOC_ID")) : null);
			bean.setDescription(
					returnObject.get("P_DESCRIPTION") != null ? String.valueOf(returnObject.get("P_DESCRIPTION"))
							: null);
			list.add(bean);

		} catch (Exception e) {
			logger.error("defaultAgency", e);
		}
		return list;
	}

	public List<ReferenceCodes> getcommareadefault(String curCaseLoadId) {
		final String sql = getQuery("OIDPWAIT_FIND_COMM_AREA_DEFALULT");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();

		final RowMapper<ReferenceCodes> mMMRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				serviceMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", curCaseLoadId), mMMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("getcommareadefault :", e);
		}
		return returnList;
	}

	@Override
	public void allocateCourseToOffender(VOffenderPrgObligations vOff) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];

		sqlParameters = new SqlParameter[] { new SqlParameter("P_PHASE_INSTANCE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_PRG_OBLIGATION_ID", OracleTypes.NUMBER),
				new SqlParameter("P_SESSION_NO", OracleTypes.NUMBER),
				new SqlParameter("P_SCHEDULE_DATE", OracleTypes.DATE) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withProcedureName("ALLOCATE_COURSE_TO_OFFENDER")
				.declareParameters(sqlParameters);
		inParamMap.put("P_PHASE_INSTANCE_ID", vOff.getCoursePhaseId());
		inParamMap.put("P_OFFENDER_BOOK_ID", vOff.getOffenderBookId());
		inParamMap.put("P_OFFENDER_PRG_OBLIGATION_ID", vOff.getOffenderPrgObligationId());
		inParamMap.put("P_SESSION_NO", null);
		inParamMap.put("P_SCHEDULE_DATE", null);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			logger.error("allocateCourseToOffender :", e);
		}
	}

	public Integer asnSerToOffUpdate(VOffenderPrgObligations vOff) {
		List<VOffenderPrgObligations> list = new ArrayList<>();
		list.add(vOff);
		int[] returnArray = new int[] {};
		String sql = getQuery("OIDPWAIT_ASN_SER_TO_OFF_UPDATE");
		List<SqlParameterSource> parameters = new ArrayList<>();

		for (VOffenderPrgObligations vOffenderPrgObligations : list) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderPrgObligations));
		}

		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("asnSerToOffUpdate :", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer lockOffPrgObligationId(VOffenderPrgObligations vOff) {
		int count = 0;
		Map<String, Object> returnObject = new HashMap<String, Object>();

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];

		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_PRG_OBLIGATION_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CHECK_SUM", OracleTypes.NUMBER) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withProcedureName("LOCK_OFF_PRG_OBLIGATION_ID")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_PRG_OBLIGATION_ID", vOff.getOffenderPrgObligationId());
		inParamMap.put("P_CHECK_SUM", vOff.getCheckSum());

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			count = 1;
		} catch (Exception e) {
			logger.error("lockOffPrgObligationId :", e);
			count = 0;
		}
		return count;
	}

	@Override
	public List<OffenderPrgObligations> getOldRecOffenderPrgObligations(final BigDecimal offenderPrgObligationId) {
		final String sql = getQuery("GET_OLD_REC_OFFENDER_PRG_OBLIGATIONS");
		final RowMapper<OffenderPrgObligations> mMMRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPrgObligations.class, serviceMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offenderPrgObligationId", offenderPrgObligationId),
				mMMRowMapper);
	}
}