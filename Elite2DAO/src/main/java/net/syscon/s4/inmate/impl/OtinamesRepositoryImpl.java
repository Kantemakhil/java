package net.syscon.s4.inmate.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.inmate.OtinamesRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OtinamesRepositoryImpl
 */

@Repository
public class OtinamesRepositoryImpl extends RepositoryBase implements OtinamesRepository {

	private final Map<String, FieldMapper> vTrustHeaderMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", 			new FieldMapper("offenderIdDisplay"))
			.put("OFFICER", 						new FieldMapper("officer"))
			.put("SUFFIX", 							new FieldMapper("suffix"))
			.put("PRISON_LOCATION", 				new FieldMapper("prisonLocation"))
			.put("DISCLOSURE_FLAG", 				new FieldMapper("disclosureFlag"))
			.put("ROOT_OFFENDER_ID", 				new FieldMapper("rootOffenderId"))
			.put("LIVING_UNIT_ID", 					new FieldMapper("livingUnitId"))
			.put("COMMUNITY_STATUS", 				new FieldMapper("communityStatus"))
			.put("COMMISSARY_TRUST_CASELOAD", 		new FieldMapper("commissaryTrustCaseload"))
			.put("MIDDLE_NAME", 					new FieldMapper("middleName"))
			.put("STATUS_3", 						new FieldMapper("status3"))
			.put("LIVING_UNIT_DESCRIPTION", 		new FieldMapper("livingUnitDescription"))
			.put("IN_OUT_STATUS", 					new FieldMapper("inOutStatus"))
			.put("OFFENDER_ID", 					new FieldMapper("offenderId"))
			.put("LAST_NAME", 						new FieldMapper("lastName"))
			.put("STATUS_1", 						new FieldMapper("status1"))
			.put("INTAKE_AGY_LOC_ID", 				new FieldMapper("intakeAgyLocId"))
			.put("MOVEMENT_REASON", 				new FieldMapper("movementReason"))
			.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
			.put("INDIGENT_FLAG", 					new FieldMapper("indigentFlag"))
			.put("ACTIVE_FLAG", 					new FieldMapper("activeFlag"))
			.put("BOOKING_BEGIN_DATE", 				new FieldMapper("bookingBeginDate"))
			.put("TRUST_CASELOAD_ID", 				new FieldMapper("trustCaseloadId"))
			.put("STATUS_2", 						new FieldMapper("status2"))
			.put("STATUS_REASON", 					new FieldMapper("statusReason"))
			.put("STATUS_DISPLAY", 					new FieldMapper("statusDisplay"))
			.put("OFF_SUP_LEVEL", 					new FieldMapper("offSupLevel"))
			.put("ALIAS_OFFENDER_ID", 				new FieldMapper("aliasOffenderId"))
			.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
			.put("BOOKING_TYPE", 					new FieldMapper("bookingType"))
			.put("BOOKING_END_DATE", 				new FieldMapper("bookingEndDate"))
			.put("COMMUNITY_ACTIVE_FLAG", 			new FieldMapper("communityActiveFlag"))
			.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
			.put("ACCOUNT_CLOSED_FLAG", 			new FieldMapper("accountClosedFlag"))
			.put("BOOKING_NO", 						new FieldMapper("bookingNo"))
			.put("COMMUNITY_TRUST_CASELOAD", 		new FieldMapper("communityTrustCaseload"))
			.put("HEADER_STATUS", 					new FieldMapper("headerStatus"))
			.put("AGY_LOC_TYPE", 					new FieldMapper("agyLocType"))
			.put("GENDER", 							new FieldMapper("gender"))
			.put("BIRTH_DATE", 						new FieldMapper("birthDate"))
			.put("FIRST_NAME", 						new FieldMapper("firstName"))
			.put("BOOKING_STATUS", 					new FieldMapper("bookingStatus"))
			.put("CREATE_INTAKE_AGY_LOC_ID", 		new FieldMapper("createIntakeAgyLocId"))
			.put("AGE", 							new FieldMapper("age"))
			.put("OFF_ALERTS", 						new FieldMapper("offAlerts"))
			.put("ETHNICITY", 						new FieldMapper("ethnicity"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 					new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", 					new FieldMapper("profileType"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", 					new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", 					new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", 				new FieldMapper("profileValue2"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> offenderTrustAccountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_CLOSED_FLAG", 			new FieldMapper("accountClosedFlag"))
			.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
			.put("ROOT_OFFENDER_ID", 				new FieldMapper("rootOffenderId"))
			.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
			.put("CURRENT_BALANC", 					new FieldMapper("currentBalanc"))
			.build();

	/**
	 * Creates new OtinamesRepositoryImpl class Object
	 */
	public OtinamesRepositoryImpl() {
		// OtinamesRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VTrustHeader
	 *
	 * @return List<VTrustHeader>
	 *
	 * @throws SQLException
	 */
	public List<VTrustHeader> vThaExecuteQuery(final VTrustHeader objSearchDao,String deffWhere) {
		final String sql = getQuery("OTINAMES_VTHA_FIND_V_TRUST_HEADER");
		final RowMapper<VTrustHeader> VTrustHeaderRowMapper = Row2BeanRowMapper.makeMapping(sql, VTrustHeader.class,
				vTrustHeaderMapping);
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao != null && objSearchDao.getLastName() != null
					&& !objSearchDao.getLastName().trim().equals("")) {
				sqlQuery.append("LAST_NAME  like :lastName  " + " and");
				params.addValue("lastName", objSearchDao.getLastName().trim() + "%");
			}
			if (objSearchDao != null && objSearchDao.getFirstName() != null
					&& !objSearchDao.getFirstName().trim().equals("")) {
				sqlQuery.append("  FIRST_NAME  like :firstName " + " and");
				params.addValue("firstName", objSearchDao.getFirstName().trim() + "%");
			}
			if (objSearchDao != null && objSearchDao.getOffenderIdDisplay() != null
					&& !objSearchDao.getOffenderIdDisplay().trim().equals("")) {
				sqlQuery.append(" OFFENDER_ID_DISPLAY  =:offenderIdDispaly " + " and");
				params.addValue("offenderIdDispaly", objSearchDao.getOffenderIdDisplay().trim());
			}
			if (objSearchDao != null && objSearchDao.getBookingNo() != null
					&& !objSearchDao.getBookingNo().trim().equals("")) {
				sqlQuery.append("  BOOKING_NO  =:bookingNo " + " and");
				params.addValue("bookingNo", objSearchDao.getBookingNo().trim());
			}
			if (objSearchDao.getModuleName() != null && !"".equals(objSearchDao.getModuleName().trim())){
				if ("OODOSALE".equals(objSearchDao.getModuleName())  || "OODORETU".equals(objSearchDao.getModuleName())||
						"OOIOSALE".equals(objSearchDao.getModuleName()) || "OOMOCRES".equals(objSearchDao.getModuleName())){
			//		final String deffWhere = omsOwnerTrustHeaderQuery(objSearchDao.getCaseloadId(), objSearchDao.getModuleName().replaceFirst("/", ""));
					if (deffWhere != null && !deffWhere.isEmpty()) {
						sqlQuery.append(" " + deffWhere + " " );
					}
				} else if (vscMtCur(objSearchDao.getCaseloadId()) >0) {
			//		final String deffWhere = omsOwnerTrustHeaderQuery(objSearchDao.getCaseloadId(), "OODOSALE");
					if (deffWhere != null && !deffWhere.isEmpty()) {
						sqlQuery.append(" " + deffWhere + " " );
					}
				} else {
			//		final String deffWhere = omsOwnerTrustHeaderQuery(objSearchDao.getCaseloadId(), "OTINAMES");
					
					if (deffWhere != null && !deffWhere.isEmpty()) {
						sqlQuery.append(" " + deffWhere + " " );
					}
				}
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat("  ORDER BY \r\n"
				+ "     last_name, first_name)A\r\n"
				+ "  left join (SELECT OFF_TA.CURRENT_BALANCE, OFF_TA.ACCOUNT_CLOSED_FLAG, OFF_TA.OFFENDER_ID, OFF_TA.CASELOAD_ID FROM OFFENDER_TRUST_ACCOUNTS OFF_TA)B on\r\n"
				+ "    A.root_offender_id=B.OFFENDER_ID and\r\n"
				+ "    EXISTS (SELECT '1' FROM CASELOADS CSLD WHERE CSLD.CASELOAD_ID = A.caseload_id AND ( ( CSLD.COMMISSARY_FLAG = 'Y' AND CSLD.COMMISSARY_TRUST_CASELOAD IS NOT NULL AND B.CASELOAD_ID = CSLD.COMMISSARY_TRUST_CASELOAD AND CSLD.CASELOAD_ID = A.caseload_id) OR ( CSLD.COMMISSARY_FLAG = 'Y' AND CSLD.COMMISSARY_TRUST_CASELOAD IS NULL AND B.CASELOAD_ID IN (SELECT CSLD1.CASELOAD_ID FROM CASELOADS CSLD1, CASELOAD_AGENCY_LOCATIONS CA WHERE CSLD1.TRUST_COMMISSARY_CASELOAD = CSLD.CASELOAD_ID AND CA.CASELOAD_ID = CSLD1.CASELOAD_ID AND CA.AGY_LOC_ID = A.agy_loc_id) AND CSLD.CASELOAD_ID = A.caseload_id) OR ( CSLD.TRUST_ACCOUNTS_FLAG = 'Y' AND B.CASELOAD_ID = CSLD.CASELOAD_ID AND CSLD.CASELOAD_ID = A.caseload_id)))  ");
		List<VTrustHeader> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, VTrustHeaderRowMapper);
		return returnList;
	}
	public String omsOwnerTrustHeaderQuery(String caseloadId, String moduleName) {
		SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlParameter("P_CASELOAD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_FORM_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_DEF_WHERE", OracleTypes.VARCHAR)
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST_MAIN").withProcedureName("TRUST_HEADER_QUERY").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CASELOAD_ID", caseloadId);
		inParamMap.put("P_FORM_NAME", moduleName);
		
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
		if(returnObject.get("P_DEF_WHERE") != null) {
			return returnObject.get("P_DEF_WHERE").toString();
		}
		return null;
	}
	public Integer vscMtCur(final String csldId) {
		final String sql = getQuery("OTINAMES_SC_MT_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("v_caseload_id",csldId), Integer.class);
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
		final String sql = getQuery("OTINAMES_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkVThaVThaOffTaF2
	 *
	 * @param params
	 *
	 */
	public OffenderTrustAccounts cgfkchkVThaVThaOffTaF(final OffenderTrustAccounts paramBean) {
		final String sql = getQuery("OTINAMES_CGFKCHK_V_THA_V_THA_OFF_TA_F2");
		final RowMapper<OffenderTrustAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTrustAccounts.class, offenderTrustAccountsMapping);
		OffenderTrustAccounts returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<Dual> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OTINAMES_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class,
				offenderTrustAccountsMapping);
		final ArrayList<Dual> returnList = (ArrayList<Dual>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public OffenderTrustAccounts vThaPostQuery(final VTrustHeader vSearchData) {
		final String sql = getQuery("OTINAMES_DIALOG_DATA");
		final RowMapper<OffenderTrustAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTrustAccounts.class, offenderTrustAccountsMapping);
		OffenderTrustAccounts returnList = new OffenderTrustAccounts();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("ROOTOFFENDERID",
					vSearchData.getRootOffenderId(), "AGYLOCID", vSearchData.getAgyLocId(),"CASELOADID",vSearchData.getCaseloadId()), columnRowMapper);

		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

}
