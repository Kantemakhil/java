package net.syscon.s4.inst.casemanagement.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.casemanagement.OciiplanRepository;
import net.syscon.s4.inst.casemanagement.beans.CasePlans;
import oracle.jdbc.OracleTypes;

@Repository
public class OciiplanRepositoryImpl extends RepositoryBase implements OciiplanRepository {
	
	private final Map<String, FieldMapper> casePlansMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("END_DATE", 							new FieldMapper("endDate"))
			.put("VERIFIED_FLAG", 						new FieldMapper("verifiedFlag"))
			.put("ROLE", 								new FieldMapper("role"))
			.put("INST_SAC_STAFF_ID", 					new FieldMapper("instSacStaffId"))
			.put("INST_FROM_DATE", 						new FieldMapper("instFromDate"))
			.put("POSITION", 							new FieldMapper("position"))
			.put("CASELOAD_TYPE", 						new FieldMapper("caseloadType"))
			.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
			.put("AUTO_CONDITION_MODIFY_DATETIME", 		new FieldMapper("autoConditionModifyDatetime"))
			.put("CHANGES", 							new FieldMapper("changes"))
			.put("NEXT_REVIEW_DATE", 					new FieldMapper("nextReviewDate"))
			.put("OFFENDER_BOOK_ID", 					new FieldMapper("offenderBookId"))
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("CASE_PLAN_ID", 						new FieldMapper("casePlanId"))
			.put("INST_CAL_AGY_LOC_ID", 				new FieldMapper("instCalAgyLocId"))
			.put("FROM_DATE", 							new FieldMapper("fromDate"))
			.put("INST_ROLE", 							new FieldMapper("instRole"))
			.put("SAC_STAFF_ID", 						new FieldMapper("sacStaffId"))
			.put("SUPERVISION_LEVEL", 					new FieldMapper("supervisionLevel"))
			.put("START_DATE", 							new FieldMapper("startDate"))
			.put("CREATION_USER", 						new FieldMapper("creationUser"))
			.put("AUTO_ASSESS_MODIFY_DATETIME", 		new FieldMapper("autoAssessModifyDatetime"))
			.put("CAL_AGY_LOC_ID", 						new FieldMapper("calAgyLocId"))
			.put("INST_POSITION", 						new FieldMapper("instPosition"))
			.put("CREATION_DATE", 						new FieldMapper("creationDate"))
			.put("CASE_PLAN_STATUS", 					new FieldMapper("casePlanStatus"))
			.put("AGY_LOC_ID", 							new FieldMapper("agyLocId"))
			.put("INST_STAFF_NAME", 					new FieldMapper("instStaffName"))
			.put("COMM_STAFF_NAME", 					new FieldMapper("commStaffName"))
			.put("OFFENDER_NAME", 						new FieldMapper("offenderName"))
			.put("pcoCaseNoteDate", 				    new FieldMapper("lastPcoDate"))
			.put("OFFENDER_ID_DISPLAY", 				new FieldMapper("offenderIdDisplay"))
			.put("CP_OWNER_NAME", 						new FieldMapper("cpOwnerName")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("CODE", 								new FieldMapper("code")).build();
	
	
	private final Map<String, FieldMapper> vHeaderMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("root_offender_id", 					new FieldMapper("rootOffenderId"))
			.put("last_name", 							new FieldMapper("lastName"))
			.put("first_name", 							new FieldMapper("firstName"))
			.put("agy_loc_id", 							new FieldMapper("agyLocId"))
			.put("offender_book_id", 					new FieldMapper("offenderBookId"))
			.put("living_unit_id", 						new FieldMapper("livingUnitId"))
			.build();

	/**
	 * Creates new OciiplanRepositoryImpl class Object
	 */
	public OciiplanRepositoryImpl() {
		// OciiplanRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CasePlans
	 *
	 * @return List<CasePlans>
	 *
	 */
	private static Logger logger = LogManager.getLogger(OciiplanRepositoryImpl.class.getName());

	public List<CasePlans> casePlansExecuteQuery(CasePlans objSearchDao) {
		final String sql = getQuery("OCIIPLAN_CASEPLANS_FIND_CASE_PLANS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (sqlQuery != null) {
			sqlQuery.append(" CP.CASE_PLAN_STATUS = 'ACTIVE'  ");

			if (objSearchDao.getCalAgyLocId() != null && !objSearchDao.getCalAgyLocId().trim().equals("")) {
				sqlQuery.append(" AND  CP.CAL_AGY_LOC_ID = :calAgyLocId");
				valuesList.addValue("calAgyLocId", objSearchDao.getCalAgyLocId());
			}
			if (objSearchDao.getInstCalAgyLocId() != null && !objSearchDao.getInstCalAgyLocId().trim().equals("")) {
				sqlQuery.append(" AND CP.INST_CAL_AGY_LOC_ID = :instCalAgyLocId ");
				valuesList.addValue("instCalAgyLocId", objSearchDao.getInstCalAgyLocId());
			}
			if (objSearchDao.getFromDate() != null || objSearchDao.getNextReviewDate() != null) {
				if(objSearchDao.getFromDate() != null && objSearchDao.getNextReviewDate() != null) {
					sqlQuery.append(" AND ((CP.NEXT_REVIEW_DATE) BETWEEN ");
					sqlQuery.append(" (:fromDate) AND ((:toDate)::date+INTERVAL '1 day')) ");
					valuesList.addValue("fromDate", objSearchDao.getFromDate());
					valuesList.addValue("toDate", objSearchDao.getNextReviewDate());
				}
				else {
					if(objSearchDao.getFromDate() != null) {
						sqlQuery.append(" AND ((CP.NEXT_REVIEW_DATE) >= (:fromDate)) ");
						valuesList.addValue("fromDate", objSearchDao.getFromDate());
					}
					else {
						sqlQuery.append(" AND ((CP.NEXT_REVIEW_DATE) <=((:toDate)::date+INTERVAL '1 day'))");
						valuesList.addValue("toDate", objSearchDao.getNextReviewDate());
					}
				}

			}
			if (objSearchDao.getVerifiedFlag() != null && !objSearchDao.getVerifiedFlag().trim().equals("")) {
				if (objSearchDao.getVerifiedFlag().trim().equals("Yes")) {
					sqlQuery.append(" AND CP.VERIFIED_FLAG = 'Y' ");
				}
				if (objSearchDao.getVerifiedFlag().trim().equals("No")) {
					sqlQuery.append(" AND CP.VERIFIED_FLAG = 'N' ");
				}
			}

			sqlQuery.append(" ORDER BY OSINAMES_GET_OFFENDER_NAME (CP.OFFENDER_BOOK_ID), CP.NEXT_REVIEW_DATE ");
		}
		preparedSql = sqlQuery.toString().trim();
		final RowMapper<CasePlans> CasePlansRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, CasePlans.class,
				casePlansMapping);
		final ArrayList<CasePlans> returnList = (ArrayList<CasePlans>) namedParameterJdbcTemplate.query(preparedSql,
				valuesList, CasePlansRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 *
	 * 
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgInstAgyLocRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OCIIPLAN_FIND_RGINSTAGYLOC");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		List<AgencyLocations> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("caseLoadId", caseLoadId), mRowMapper);
		} catch (Exception e) {
			logger.error("rgInstAgyLocRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> comInstAgyLocRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OCIIPLAN_FIND_COMINSTAGYLOC");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		List<AgencyLocations> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("caseLoadId", caseLoadId), mRowMapper);
		} catch (Exception e) {
			logger.error("comInstAgyLocRecordGroup", e);
		}
		return returnList;
	}

	@Override
	public Boolean ociiplanTagMainGetOffender(String caseLoadId, String caseLoadType, String offenderIdDisplay,String userName) {
		
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		SqlParameter[] sqlParameters = new SqlParameter[] 
		{
				new SqlParameter("P_FORM_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_DEF_WHERE", OracleTypes.VARCHAR)
		};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_MAIN").withProcedureName("DEF_BKG_WHERE").declareParameters(sqlParameters);
		inParamMap.put("P_FORM_NAME", "OCDIPLAN");
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		Map<String, Object> returnObject = null;
		Boolean returnBoolValue = false;
		try {
			  returnObject = simpleJDBCCall.execute(inParameter);
			  if(returnObject.get("P_DEF_WHERE") != null) {
					final String whereClause = returnObject.get("P_DEF_WHERE").toString().toUpperCase().replaceAll(":GLOBAL.CASELOAD_TYPE", ":caseLoadType").replaceAll(":GLOBAL.CASELOAD_ID", ":caseLoadId");
					final String sql = getQuery("OCIIPLAN_CASEPLANS_TAG_MAIN_GET_OFFENDER").replace("#whereclause", whereClause);
					final RowMapper<VHeaderBlock> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
							vHeaderMapping);
					List<VHeaderBlock> returnList = namedParameterJdbcTemplate.query(sql, createParams("caseLoadId", caseLoadId, "caseLoadType", caseLoadType, "offenderIdDisplay", offenderIdDisplay,"USERID",userName), mRowMapper);
					if(returnList.size() > 0) {
						returnBoolValue =  true;
					}else {
						returnBoolValue = false;
					}
				}
		} catch (Exception e) {
			logger.error(e);
		}
		
		return returnBoolValue;
	}

	@Override
	public List<VHeaderBlock> ociiplanCaseplansTagMainGetOffender(String whereClause, String caseLoadId,
			String caseLoadType, String offenderIdDisplay,String userName) {
		final String sql = getQuery("OCIIPLAN_CASEPLANS_TAG_MAIN_GET_OFFENDER").replace("#whereclause", whereClause);
		final RowMapper<VHeaderBlock> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHeaderMapping);
		List<VHeaderBlock> returnList = namedParameterJdbcTemplate.query(sql, createParams("caseLoadId", caseLoadId, "caseLoadType", caseLoadType, "offenderIdDisplay", offenderIdDisplay,"USERID",userName), mRowMapper);
		return returnList;
	}
}
