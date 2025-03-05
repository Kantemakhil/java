package net.syscon.s4.inst.offenderissuestracking.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.offenderissuestracking.OiigrievRepository;
import net.syscon.s4.inst.offenderissuestracking.beans.VGrievanceInquiry;

@Repository
public class OiigrievRepositoryImpl extends RepositoryBase implements OiigrievRepository {

	private static Logger logger = LogManager.getLogger(OiigrievRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vGrievanceInquiryMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("GRIEV_TYPE", new FieldMapper("grievType"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("REPORT_DATE", new FieldMapper("reportDate"))
			.put("GRIEVANCE_ID", new FieldMapper("grievanceId"))
			.put("ASSIGNED_STAFF_ID", new FieldMapper("assignedStaffId"))
			.put("DAYS_REM", new FieldMapper("daysRem"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("OFF_NAME", new FieldMapper("offName"))			
			.put("GRIEV_REASON_CODE_DESC", new FieldMapper("grievReasonCodeDesc"))
			.put("GRIEV_REASON_CODE", new FieldMapper("grievReasonCode"))
			.put("TXN_TYPE", new FieldMapper("txnType"))
			.put("TXN_TYPE_DESC", new FieldMapper("txnTypeDesc"))
			.put("GRIEV_LEVEL", new FieldMapper("grievLevel")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("GRIEV_TYPE", new FieldMapper("grievType "))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("STAFF_ID", new FieldMapper("staffId"))
			.put("LAST_NAME||'", new FieldMapper("lastName||' "))
			.put("'||FIRST_NAME", new FieldMapper("'||firstName"))
			.build();

	/**
	 * Creates new OiigrievRepositoryImpl class Object
	 */
	public OiigrievRepositoryImpl() {
		
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VGrievanceInquiry
	 *
	 * @return List<VGrievanceInquiry>
	 *
	 *
	 */
	public List<VGrievanceInquiry> grieDetExecuteQuery(VGrievanceInquiry objSearchDao) {
		final String sql = getQuery("OIIGRIEV_GRIEDET_FIND_V_GRIEVANCE_INQUIRY");
		String preparedSql = null;
		StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		if (objSearchDao != null) {
			sqlQuery.append(sql);
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getAgyLocId() != null && !objSearchDao.getAgyLocId().trim().equals("")) {
				sqlQuery.append(" AGY_LOC_ID = :agyLocId AND ");
				params.addValue("agyLocId", objSearchDao.getAgyLocId());
			}
			if (objSearchDao.getFromDate() != null) {
				sqlQuery.append(" REPORT_DATE >= :fromDate AND ");
				params.addValue("fromDate", new java.sql.Date(objSearchDao.getFromDate().getTime()));
			}
			if (objSearchDao.getToDate() != null) {
				sqlQuery.append(" REPORT_DATE <(:toDate ::date + '1 day'::interval) AND ");
				params.addValue("toDate", new java.sql.Date(objSearchDao.getToDate().getTime()));
			}
			if (objSearchDao.getGrievType() != null && !objSearchDao.getGrievType().trim().equals("")) {
				sqlQuery.append(" GRIEV_TYPE = :grievType AND ");
				params.addValue("grievType", objSearchDao.getGrievType());
			}
			if (objSearchDao.getGrievReasonCode() != null && !objSearchDao.getGrievReasonCode().trim().equals("")) {
				sqlQuery.append(" GRIEV_REASON_CODE = :grievReasonCode AND ");
				params.addValue("grievReasonCode", objSearchDao.getGrievReasonCode());
			}
			if (objSearchDao.getTxnType() != null && !objSearchDao.getTxnType().trim().equals("")) {
				sqlQuery.append(" TXN_TYPE = :txnType AND ");
				params.addValue("txnType", objSearchDao.getTxnType());
			}
			if (objSearchDao.getAssignedStaffId() != null) {
				sqlQuery.append(" ASSIGNED_STAFF_ID = :assignedStaffId AND ");
				params.addValue("assignedStaffId", objSearchDao.getAssignedStaffId());
			}
			if (objSearchDao.getUserInvolvement() != null && !objSearchDao.getUserInvolvement().trim().equals("")) {
				sqlQuery.append(" grievance_id = (SELECT grievance_id FROM offender_griev_staffs ogs WHERE "
						+ " ogs.grievance_id = v_grievance_inquiry.grievance_id AND ogs.staff_id::text = :userInvolvement::text) AND ");
				params.addValue("userInvolvement", objSearchDao.getUserInvolvement());
			}
			if (objSearchDao.getGrievLevel() != null && !objSearchDao.getGrievLevel().trim().equals("")) {
				sqlQuery.append(" GRIEV_LEVEL = :grievLevel AND ");
				params.addValue("grievLevel", objSearchDao.getGrievLevel());
			}
			if (objSearchDao.getGrievanceId() != null) {
				sqlQuery.append(" GRIEVANCE_ID = :grievanceId AND ");
				params.addValue("grievanceId", objSearchDao.getGrievanceId());
			}
			if ((objSearchDao.getSupervisorReviewed() != null && objSearchDao.getSupervisorReviewed().equals("Y"))
					|| (objSearchDao.getNoSupervisorReviewed() != null
							&& objSearchDao.getNoSupervisorReviewed().equals("Y"))) {
				sqlQuery.append(
						" GRIEVANCE_ID = (SELECT DISTINCT grievance_id FROM offender_grievance_txns offgt WHERE "
								+ " offgt.grievance_id = v_grievance_inquiry.grievance_id AND offgt.txn_type = 'IG_REVIEW' ");
				if (objSearchDao.getNoSupervisorReviewed() != null
						|| objSearchDao.getNoSupervisorReviewed().equals('N')) {
					sqlQuery.append(" ) ");
				} else if (objSearchDao.getNoSupervisorReviewed().equals('Y')) {
					sqlQuery.append(" AND NOT EXISTS ( SELECT 1 FROM offender_grievance_txns offgt1 "
							+ " WHERE offgt1.grievance_id = v_grievance_inquiry.grievance_id "
							+ " AND offgt1.txn_type = 'IG_RESP')) ");
				}
				sqlQuery.append(" AND ");
			}
			sqlQuery.append(" exists (select 'Y' from GRIEVANCE_TYPES_PERMISSIONS GTP where GTP.GRIEV_TYPE = v_grievance_inquiry.GRIEV_TYPE and GTP.GRIEV_REASON_CODE in (select OG.GRIEV_REASON_CODE from OFFENDER_GRIEVANCES OG where OG.grievance_id = v_grievance_inquiry.grievance_id) and GTP.VIEW_FLAG = 'Y' and GTP.ROLE_ID in( select SMR.ROLE_ID from STAFF_MEMBER_ROLES SMR where SMR.STAFF_ID = ( select SM.STAFF_ID from STAFF_MEMBERS SM where LOWER(SM.USER_ID) = LOWER(:user))) )");
			params.addValue("user", objSearchDao.getCreateUserId());
			preparedSql = sqlQuery.toString().trim();
			if (preparedSql.endsWith("WHERE")) {
				preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
			}
			if (preparedSql.endsWith("AND")) {
				preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
			}

		}
		final RowMapper<VGrievanceInquiry> VGrievanceInquiryRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				VGrievanceInquiry.class, vGrievanceInquiryMapping);
		final List<VGrievanceInquiry> returnList = namedParameterJdbcTemplate.query(preparedSql, params,
				VGrievanceInquiryRowMapper);
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
	public List<AgencyLocations> rgAgyRecordGroup() {
		final String sql = getQuery("OIIGRIEV_FIND_RGAGY");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("OIIGRIEV_FIND_RGAGY", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgGrieTypeRecordGroup(String user) {
		final String sql = getQuery("OIIGRIEV_FIND_RGGRIETYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("user", user), mRowMapper);
		} catch (Exception e) {
			logger.error("OIIGRIEV_FIND_RGGRIETYPE", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<StaffMembers> rgStaffAsgRecordGroup() {
		final String sql = getQuery("OIIGRIEV_FIND_RGSTAFFASG");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("OIIGRIEV_FIND_RGSTAFFASG", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgLevelRecordGroup() {
		final String sql = getQuery("OIIGRIEV_FIND_RGLEVEL");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("OIIGRIEV_FIND_RGLEVEL", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<StaffMembers> rgStaffInvRecordGroup() {
		final String sql = getQuery("OIIGRIEV_FIND_RGSTAFFINV");
		final RowMapper<StaffMembers> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMRowMapper);
		} catch (Exception e) {
			logger.error("OIIGRIEV_FIND_RGSTAFFINV", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobals(OmsModules paramBean) {
		final String sql = getQuery("OIIGRIEV_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);
		OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	public String whenNewRecordInstance(final String userId) {
		final String sql = getQuery("WHEN_NEW_RECORD_INSTANCE");
		String result = "";
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("USER_ID",userId), String.class);
		} catch (Exception e) {
			logger.error("WHEN_NEW_RECORD_INSTANCE", e);
			result = "";
		}
		return result;
	}

	@Override
	public Long getResponceDays(BigDecimal id) {
		final String sql = getQuery("OIIGRIEV_DAYS_RESPONDS");
		Long result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("id", id), Long.class);
		} catch (Exception e) {
			result = null;
			logger.error(e);
		}

		return result;
	}

	@Override
	public List<ReferenceCodes> rgGrievReasonRecordGroup(String grievType) {
		final String sql = getQuery("OIIGRIEV_FIND_RGGRIEVREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("grievType", grievType), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<ReferenceCodes> rgGrieTransactionRecordGroup(String grievType) {
		final String sql = getQuery("OIIGRIEV_FIND_RGGRIEVTRANSACTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("grievType", grievType), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

}
