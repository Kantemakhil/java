package net.syscon.s4.inst.casemanagement.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.inst.casemanagement.OcustfasRepository;
import net.syscon.s4.inst.casemanagement.beans.OffApV1;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeeds;
import net.syscon.s4.inst.casemanagement.beans.StaffMembersV2;
import oracle.jdbc.OracleTypes;

@Repository
public class OcustfasRepositoryImpl extends RepositoryBase implements OcustfasRepository {

	private final Map<String, FieldMapper> casePlansMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FROM_DATE", 					new FieldMapper("fromDate"))
			.put("INST_ROLE", 					new FieldMapper("instRole"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.put("SAC_STAFF_ID", 				new FieldMapper("sacStaffId"))
			.put("SUPERVISION_LEVEL", 			new FieldMapper("supervisionLevel"))
			.put("0", 							new FieldMapper("0"))
			.put("START_DATE", 					new FieldMapper("startDate"))
			.put("CREATION_USER", 				new FieldMapper("creationUser"))
			.put("AGY_LOC_ID", 					new FieldMapper("agyLocId"))
			.put("REVIEW_PERIOD", 				new FieldMapper("  reviewPeriod "))
			.put("AUTO_ASSESS_MODIFY_DATETIME", new FieldMapper("autoAssessModifyDatetime"))
			.put("CAL_AGY_LOC_ID", 				new FieldMapper("calAgyLocId"))
			.put("INST_POSITION", 				new FieldMapper("instPosition"))
			.put("CREATION_DATE", 				new FieldMapper("creationDate"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDateTime"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDateTime"))
			.put("'1'", 						new FieldMapper(" '1' "))
			.put("CASE_PLAN_STATUS", 			new FieldMapper("casePlanStatus"))
			.build();
	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAST_NAME||'", 				new FieldMapper("lastName||'"))
			.put("CAL_AGY_LOC_ID", 				new FieldMapper("calAgyLocId"))
			.put("LAST_NAME", 					new FieldMapper("lastName"))
			.put("FIRST_NAME", 					new FieldMapper("firstName"))
			.put("SAC_STAFF_ID", 				new FieldMapper("sacStaffId"))
			.put("STAFF_ID", 					new FieldMapper("staffId"))
			.build();
	private final Map<String, FieldMapper> staffMembersV2Mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FROM_DATE", 					new FieldMapper("fromDate"))
			.put("INST_CAL_AGY_LOC_ID", 		new FieldMapper("instCalAgyLocId"))
			.put("MODE", 						new FieldMapper("mode"))
			.put("ROLE", 						new FieldMapper("role"))
			.put("POSITION", 					new FieldMapper("position"))
			.put("INST_ROLE", 					new FieldMapper("instRole"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("INST_POSITION", 				new FieldMapper("instPosition"))
			.put("CAL_AGY_LOC_ID", 				new FieldMapper("calAgyLocId"))
			.put("INST_FROM_DATE", 				new FieldMapper("instFromDate"))
			.put("INST_SAC_STAFF_ID", 			new FieldMapper("instSacStaffId"))
			.put("SAC_STAFF_ID", 				new FieldMapper("sacStaffId"))
			.build();
	private final Map<String, FieldMapper> offenderCriminogenicNeedsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MAX(MODIFY_DATETIME)", new FieldMapper(" max(modifyDatetime) "))
			.put("TARGET_DATE", new FieldMapper("targetDate")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("ASSESSED_NEED_CODE", new FieldMapper("assessedNeedCode"))
			.put("STATUS_CODE", new FieldMapper("statusCode")).put("CASE_PLAN_ID", new FieldMapper("casePlanId"))
			.put("ROW_ID", new FieldMapper("rowId")).build();
	/**
	 * Creates new OcustfasRepositoryImpl class Object
	 */
	public OcustfasRepositoryImpl() {
		// OcustfasRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CasePlans
	 *
	 * @return List<CasePlans>
	 */
	public List<CasePlans> casePlansExecuteQuery(final CasePlans objSearchDao) {
		final String sql = getQuery("OCUSTFAS_CASEPLANS_FIND_CASE_PLANS");
		final RowMapper<CasePlans> CasePlansRowMapper = Row2BeanRowMapper.makeMapping(sql, CasePlans.class,
				casePlansMapping);
		final ArrayList<CasePlans> returnList = (ArrayList<CasePlans>) namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", objSearchDao.getOffenderBookId()), CasePlansRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstCasePlans
	 *            List<CasePlans>
	 *
	 * @return List<Integer>
	 */
	public Integer casePlansInsertCasePlans(final List<CasePlans> lstCasePlans) {
		String sql = getQuery("OCUSTFAS_CASEPLANS_INSERT_CASE_PLANS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CasePlans casePlans : lstCasePlans) {
			parameters.add(new BeanPropertySqlParameterSource(casePlans));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCasePlans.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCasePlans
	 *            List<CasePlans>
	 *
	 * @throws SQLException
	 */
	public Integer casePlansUpdateCasePlans(final List<CasePlans> lstCasePlans) {
		String sql = getQuery("OCUSTFAS_CASEPLANS_UPDATE_CASE_PLANS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CasePlans casePlans : lstCasePlans) {
			parameters.add(new BeanPropertySqlParameterSource(casePlans));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			return null;
		}
		if (lstCasePlans.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<StaffMembers> rgStaffNameRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OCUSTFAS_FIND_RGSTAFFNAME");
		final RowMapper<StaffMembers> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", caseLoadId),
				referenceCodeRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * casePlansPreInsert
	 *
	 * @param params
	 *
	 */
	public Integer casePlansPreInsert(final CasePlans paramBean) {
		final String sql = getQuery("OCUSTFAS_CASE_PLANS_PREINSERT");
		Integer returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("supervisionLevel", paramBean.getSupervisionLevel()), Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * casePlansPreInsert
	 *
	 * @param params
	 *
	 */
	public Integer casePlansPreInsertMaxAssSeq(final CasePlans paramBean) {
		final String sql = getQuery("OCUSTFAS_CASE_PLANS_MAX_ASS_SEQ");
		Integer returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",
					paramBean.getOffenderBookId(), "caseloadType", paramBean.getCaseloadType()), Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * casePlansPreInsert
	 *
	 * @param params
	 *
	 */
	public String casePlansPreInsertSuperVisionLevel(final Integer paramBean, final Long offenderBookId) {
		final String sql = getQuery("OCUSTFAS_CASE_PLANS_REVIEW_SUP_LEVEL_TYPE");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("assessmentSeq", paramBean, "offenderBookId", offenderBookId), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * casePlansPreInsert
	 *
	 * @param params
	 *
	 */
	public String casePlansPreInsertUnClass() {
		final String sql = getQuery("OCUSTFAS_CASE_PLANS_UN_CLASS");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		}catch (Exception e) {
			return null;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkReassignment
	 *
	 * @param params
	 *
	 */
	public CasePlans checkReassignment(final CasePlans paramBean) {
		final String sql = getQuery("OCUSTFAS_CHECK_REASSIGNMENT");
		final RowMapper<CasePlans> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CasePlans.class,
				casePlansMapping);
		CasePlans returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
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
		final String sql = getQuery("OCUSTFAS_CASE_PLANS_PREINSERT_CASE_PLAN_ID");
		String obj = null;
		Integer returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", paramBean), String.class);
		if (obj != null) {
			returnval = Integer.parseInt(obj);
		}
		return returnval;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offmrPreInsertc
	 *
	 * @param paramBean
	 *
	 */
	public Integer offmrSacStaffIdInsertc(final String lastName, final String firstName, final String userId) {
		final String sql = getQuery("OCUSTFAS_CASE_PLANS_STAFF_ID_PREINSERT");
		String obj = null;
		Integer returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("lastName", lastName, "firstName", firstName,"userId", userId), String.class);
		if (obj != null) {
			returnval = Integer.parseInt(obj);
		}
		return returnval;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfkchkOffBkgsOffBkgStafc
	 *
	 * @param params
	 *
	 */
	public StaffMembers cgfkchkOffBkgsOffBkgStafc(final StaffMembers paramBean) {
		final String sql = getQuery("OCUSTFAS_CASE_PLANS_OFFICER_PREINSERT");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		StaffMembers returnList = new StaffMembers();
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("staffId", paramBean.getStaffId()),
				columnRowMapper);
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
	public String agencyLocations(final String agyLocId) {
		final String sql = getQuery("OCUSTFAS_AGENCY_LOCATIONS");
		String obj = null;
		String returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("AGYLOCID", agyLocId), String.class);
		if (obj != null) {
			returnval = obj;
		}
		return returnval;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offmrPreInsertc
	 *
	 * @param paramBean
	 *
	 */
	public Integer casePlansCount(final Long offenderBookId) {
		final String sql = getQuery("OCUSTFAS_CASE_PLANS_COUNT");
		Integer returnval = null;
		returnval = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offenderBookId),
				Integer.class);
		return returnval;
	}

	/*
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSchPreInsert
	 *
	 *
	 */
	public String casePlansNextReviewDatePreInsert(final CasePlans offSch) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("PIMS_WEIGHT").withFunctionName("GET_SUP_LEVEL").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", offSch.getOffenderBookId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final String value = simpleJDBCCall.executeFunction(String.class, inParameter);
		try {
		} catch (Exception e) {
			return value;
		}
		return value;
	}

		public StaffMembersV2 casPlnPreQuerySacStaffId(final CasePlans paramBean) {
		final String sql = getQuery("OCUSTFAS_CASE_PLANS_INST_FROM_DATE");
		StaffMembersV2 returnObj = new StaffMembersV2();
		final RowMapper<StaffMembersV2> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembersV2.class,
				staffMembersV2Mapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("SACSTAFFID", paramBean.getInstSacStaffId(), "CALAGYLOCID", paramBean.getAgyLocId(),
							"POSITION", paramBean.getInstPosition(), "ROLE", paramBean.getInstRole()),
					columnRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public List<OffenderCriminogenicNeeds> getOldDataOffenderCriminogenicNeeds(Long offenderBookId) {
		final String sql = getQuery("OCUSTFAS_GET_OLD_DATAOFFENDERCRIMINOGENICNEEDS");
		final RowMapper<OffenderCriminogenicNeeds> OffenderCriminogenicNeedsRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderCriminogenicNeeds.class, offenderCriminogenicNeedsMapping);
		final ArrayList<OffenderCriminogenicNeeds> returnList = (ArrayList<OffenderCriminogenicNeeds>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderBookId", offenderBookId), OffenderCriminogenicNeedsRowMapper);
		return returnList;
	}

	@Override
	public List<OffApV1> getOldDataPlanOfActon(long offCrimNeedId) {
		final String sql = getQuery("OCUSTFAS_GET_OLD_DATAPlanOfAction");
		final RowMapper<OffApV1> OffApV1 = Row2BeanRowMapper
				.makeMapping(sql, OffApV1.class, offenderCriminogenicNeedsMapping);
		final ArrayList<OffApV1> returnList = (ArrayList<OffApV1>) namedParameterJdbcTemplate
				.query(sql, createParams("offCrimNeedId", offCrimNeedId), OffApV1);
		return returnList;
	}
	public Integer getCasePlanconditions(final Long offenderBookId) {
		final String sql = getQuery("OCUSTFAS_GET_CONDITIONS");
		Integer returnval = null;
		returnval = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offenderBookId),
				Integer.class);
		return returnval;
	}

	@Override
	public Integer caseplansUpdateCasePlansStatus(Integer offenderBookId, String userId) {
			String sql = getQuery("OCUSTFAS_CASEPLANS_UPDATE_CASE_PLANS_STATUS");
			Integer intObj=null;
			try {	
			intObj= namedParameterJdbcTemplate.update(sql, createParams("offender_book_id",offenderBookId,"modifyUserId",userId));
			}catch (Exception e) {
				return intObj;
			}
		return intObj;
	}
	
	public String getCasePlanPreInsertCommClass() {
		final String sql = getQuery("OCUSTFAS_CASE_PLANS_COMM_CLASS");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		}catch (Exception e) {
			return null;
		}
		return returnObj;
	}
}
