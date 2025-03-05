package net.syscon.s4.inmate.trust.deductions.impl;

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
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.OffenderObligationHty;
import net.syscon.s4.inmate.trust.deductions.OcuobhisRepository;
import net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry.impl.OcicbeneRepositoryImpl;
import oracle.jdbc.OracleTypes;

/**
 * Class OcuobhisRepositoryImpl
 */
@Repository
public class OcuobhisRepositoryImpl extends RepositoryBase implements OcuobhisRepository {

	private final Map<String, FieldMapper> offenderObligationHtyMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADJUSTED_AMOUNT", 				new FieldMapper("adjustedAmount"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("OFFENDER_DEDUCTION_ID", 			new FieldMapper("offenderDeductionId"))
			.put("MAX_TOTAL_AMOUNT", 				new FieldMapper("maxTotalAmount"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("INFORMATION_NUMBER", 				new FieldMapper("informationNumber"))
			.put("MODIFIED_USER_ID", 				new FieldMapper("modifiedUserId"))
			.put("MODIFIED_DATETIME", 				new FieldMapper("modifiedDateTime"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDateTime"))
			.put("DEDUCTION_TYPE", 					new FieldMapper("deductionType"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDateTime"))
			.put("DEDUCTION_SEQ", 					new FieldMapper("deductionSeq"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE", 					new FieldMapper("profileValue"))
			.build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'LAN'", 							new FieldMapper(" 'lan'"))
			.put("REPORT_CODE", 					new FieldMapper("reportCode"))
			.put("DECOD", 							new FieldMapper("decod"))
			.put("'PORTRAIT')", 					new FieldMapper(" 'portrait') "))
			.put("'LANDSCAPE'", 					new FieldMapper(" 'landscape'"))
			.build();
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcicbeneRepositoryImpl.class.getName());

	/**
	 * Creates new OcuobhisRepositoryImpl class Object
	 */
	public OcuobhisRepositoryImpl() {
		// OcuobhisRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderObligationHty
	 *
	 * @return List<OffenderObligationHty>
	 *
	 */
	public List<OffenderObligationHty> offOblHtyExecuteQuery(final OffenderObligationHty objSearchDao) {
		final String sql = getQuery("OCUOBHIS_OFFOBLHTY_FIND_OFFENDER_OBLIGATION_HTY");
		final RowMapper<OffenderObligationHty> offObliRM = Row2BeanRowMapper.makeMapping(sql,
				OffenderObligationHty.class, offenderObligationHtyMapping);
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		List<OffenderObligationHty> lstOffObli = new ArrayList<OffenderObligationHty>();
		if (objSearchDao != null) {
			if (objSearchDao.getOffenderDeductionId() != null) {
				sqlQuery.append(sql);
				sqlQuery.append(" where ");
				sqlQuery.append(
						" OFFENDER_DEDUCTION_ID = :offenderDeductionId ORDER BY MODIFIED_DATETIME DESC, DEDUCTION_SEQ DESC");
				valuesList.addValue("offenderDeductionId", objSearchDao.getOffenderDeductionId());
				preparedSql = sqlQuery.toString().trim();
				lstOffObli = namedParameterJdbcTemplate.query(preparedSql, valuesList,
						offObliRM);
			}
		}
		return lstOffObli;
	}

	/**
	 * @param
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * ocuobhisWhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public SystemProfiles ocuobhisWhenNewFormInstance(final SystemProfiles paramBean) {
		final String sql = getQuery("OCUOBHIS_OCUOBHIS_WHENNEWFORMINSTANCE");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_PROFILE_TYPE",
				paramBean.getProfileType(), "P_PROFILE_CODE", paramBean.getProfileCode()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobalsCREATE_FORM_GLOBALS
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OCUOBHIS_CREATE_FORM_GLOBALS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		final OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * 
	 * @param caseloadId
	 * @return String
	 */
	public String getCaseloadType(final String caseloadId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Map<String, Object> returnObject = null;
		SqlParameter[] sqlParameters = new SqlParameter[5];
		String caseloadType = null;
		sqlParameters = new SqlParameter[] { new SqlParameter("CSLD_ID", OracleTypes.VARCHAR),
				new SqlInOutParameter("CSLD_TYPE", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("GET_CASELOAD_TYPE").declareParameters(sqlParameters);
		inParamMap.put("CSLD_ID", caseloadId);
		inParamMap.put("CSLD_TYPE", "true");
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);

			if (returnObject != null) {
				caseloadType = (String) returnObject.get("CSLD_TYPE");
			}

		} catch (Exception e) {
			logger.error("In method getCaseloadType: ", e);
		}
		return caseloadType;

	}

}
