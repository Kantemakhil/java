package net.syscon.s4.inst.demographicsbiometrics.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderAlerts;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.casemanagement.beans.CasePlans;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.inst.demographicsbiometrics.OcuverifRepository;
/**
 * Class OcuverifRepositoryImpl
 */

@Repository
public class OcuverifRepositoryImpl extends RepositoryBase implements OcuverifRepository{

	private static Logger logger = LogManager.getLogger(OcuverifRepositoryImpl.class.getName());

private final Map<String, FieldMapper> wrkFlLogMapping = new ImmutableMap.Builder<String, FieldMapper>()
      .put("WORK_FLOW_STATUS", 				new FieldMapper("workFlowStatus"))
      .put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
      .put("ACTION_USER_ID", 						new FieldMapper("actionUserId"))
      .put("LOCATE_AGY_LOC_ID", 				new FieldMapper("locateAgyLocId"))
      .put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
      .put("WORK_FLOW_SEQ", 						new FieldMapper("workFlowSeq"))
      .put("SEAL_FLAG", 						            new FieldMapper("sealFlag"))
      .put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
      .put("WORK_FLOW_ID", 						new FieldMapper("workFlowId"))
      .put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
      .put("WORK_ACTION_DATE", 				new FieldMapper("workActionDate"))
      .put("CREATE_DATE", 						        new FieldMapper("createDate"))
      .put("WORK_ACTION_CODE", 				new FieldMapper("workActionCode"))
      .build();
private final Map<String, FieldMapper> wrkFlsMapping = new ImmutableMap.Builder<String, FieldMapper>()
      .put("OBJECT_CODE", new FieldMapper("objectCode"))
      .put("WORK_FLOW_ID", new FieldMapper("workFlowId"))
      .put("OBJECT_SEQ", new FieldMapper("objectSeq"))
      .put("OBJECT_ID", new FieldMapper("objectId"))
      .build();
private final Map<String, FieldMapper> sysDuMapping = new ImmutableMap.Builder<String, FieldMapper>()
      .put("SYSDATE",                 new FieldMapper("sysDate"))
      .put("USER",                    new FieldMapper("user"))
      .build();

	/**
	 * Creates new OcuverifRepositoryImpl class Object
	 */
	public OcuverifRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            WorkFlowLogs
	 *
	 * @return List<WorkFlowLogs>
	 *
	 */
	public List<WorkFlowLogs> workFlExecuteQuery(final WorkFlows objSearchDao) {
		final String sql = getQuery("OCUVERIF_WORKFL_FIND_WORK_FLOW_LOGS");
		final RowMapper<WorkFlowLogs> workFlowRM = Row2BeanRowMapper.makeMapping(sql, WorkFlowLogs.class,
				wrkFlLogMapping);
		List<WorkFlowLogs> list = null;
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null ) {
			sqlQuery.append(" where ");
			if (objSearchDao.getWorkFlowId() > 0) {
				sqlQuery.append(" WORK_FLOW_ID =  :workflowId ORDER BY WORK_FLOW_SEQ DESC ");
				valuesList.addValue("workflowId", objSearchDao.getWorkFlowId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		list =  namedParameterJdbcTemplate.query(preparedSql, valuesList, workFlowRM);
		return list;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderAlerts
	 *
	 * @return List<WorkFlows>
	 *
	 */
	public List<WorkFlows> workFlowsExecuteQuery(final OffenderAlerts objSearchDao) {
		final String sql = getQuery("OCUVERIF_WORK_FLOW_ID");
		final RowMapper<WorkFlows> workFlowRM = Row2BeanRowMapper.makeMapping(sql, WorkFlows.class, wrkFlsMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("V_BOOK_ID", objSearchDao.getOffenderBookId(), "V_ALERT_SEQ", objSearchDao.getAlertSeq()),
				workFlowRM);
	}

	/**
	 * @param
	 *
	 *
	 */
	public Integer preInsert(final WorkFlowLogs object) {
		final String sql = getQuery("OCUVERIF_GET_WORK_FLOW_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("WORK_FLOW_ID", object.getWorkFlowId()),
				Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<SysDual> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OCUVERIF_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, sysDuMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is used to update the records in the data base tables based
	 * on
	 *
	 * @param listObject
	 *            List<WorkFlowLogs>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer workFlCommit(final List<WorkFlowLogs> listObject) {
		final String sql = getQuery("OCUVERIF_WORK_FLOW_COMMIT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WorkFlowLogs obj : listObject) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObject.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the records in the data base tables based
	 * on
	 *
	 * @param listObject
	 *            List<WorkFlowLogs>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer offAlertUpdate(final List<OffenderAlerts> listObject) {
		final String sql = getQuery("OCUVERIF_OFF_ALERT_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderAlerts obj : listObject) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObject.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public String getUserName(String userId) {
		final String sql = getQuery("OCUVERIF_GET_USER_NAME");
		String userName = null;
		try {
			userName = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getUserName");
			return userName;
		}
		return userName;

	}
	
	public Integer workFlUpdate(final List<WorkFlowLogs> listObject) {
		final String sql = getQuery("OCUVERIF_OFF_VERIFY_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WorkFlowLogs obj : listObject) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObject.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public Integer workFlInsert(final List<WorkFlowLogs> listObject) {
		final String sql = getQuery("OCUVERIF_WORK_FLOW_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WorkFlowLogs obj : listObject) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObject.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateNextReviewDate(CasePlans casePlans) {
		final String sql = getQuery("OCUVERIF_UPDATE_CASEPLAN_NEXTREVIEW_DATE");
		Integer result = null;
		try {
			result = namedParameterJdbcTemplate.update(sql,
					createParams("nextReviewDate", casePlans.getNextReviewDate(), "modifyUserId",
							casePlans.getModifyUserId(), "modifyDatetime", casePlans.getModifyDatetime(),
							"verifiedFlag", casePlans.getVerifiedFlag(), "offenderBookId",
							casePlans.getOffenderBookId(), "casePlanId", casePlans.getCasePlanId()));
		} catch (Exception e) {
			logger.error(e);
		}

		return result;
	}

	@Override
	public String getOffenderSecurityLevel(Long offenderBookId, String userName) {
		final String sql = getQuery("OCUVERIF_OFFENDER_SECURITY_LEVEL");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("createUserId", userName, "offenderBookId", offenderBookId), String.class);
		} catch (Exception e) {
			logger.error(e);
		}
		if (result == null) {
			final String sql2 = getQuery("OCUVERIF_CASE_PLANS_UN_CLASS");
			try {
				result = namedParameterJdbcTemplate.queryForObject(sql2, createParams(), String.class);
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return result;
	}

	@Override
	public Integer getReviewDays(String securityLevel) {
		Integer reviewDays = null;
		final String sql2 = getQuery("OCUVERIF_CASE_PLANS_PREINSERT");
		try {
			reviewDays = namedParameterJdbcTemplate.queryForObject(sql2,
					createParams("supervisionLevel", securityLevel), Integer.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return reviewDays;
	}
}
