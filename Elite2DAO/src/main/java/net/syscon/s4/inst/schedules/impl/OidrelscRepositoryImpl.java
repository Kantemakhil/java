package net.syscon.s4.inst.schedules.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.inst.schedules.OidrelscRepository;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetailKeyDatesBean;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;

/**
 * Class OidrelscRepositoryImpl
 */
@Repository
public class OidrelscRepositoryImpl extends RepositoryBase implements OidrelscRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidrelscRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> wfLogsMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("0", new FieldMapper("0"))
			.put("MAX", new FieldMapper("max"))
			.put("WORK_FLOW_ID", new FieldMapper("workFlowId"))
			.put("NV", new FieldMapper("nv"))
			.put("COUNT", new FieldMapper("count"))
			.build();
	private final Map<String, FieldMapper> vhederMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'X'", new FieldMapper(" 'x' ")).build();
	private final Map<String, FieldMapper> offndrMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("VERIFIED_FLAG", new FieldMapper("verifiedFlag"))
			.put("APPROVED_RELEASE_DATE", new FieldMapper("approvedReleaseDate"))
			.put("DTO_MID_TERM_DATE", new FieldMapper("dtoMidTermDate"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("DTO_APPROVED_DATE", new FieldMapper("dtoApprovedDate"))
			.put("RELEASE_DATE", new FieldMapper("releaseDate"))
			.put("EVENT_ID", new FieldMapper("eventId"))
			.put("PROPOSED_MVMNT_SEQ", new FieldMapper("proposedMvmntSeq"))
			.put("AUTO_RELEASE_DATE", new FieldMapper("autoReleaseDate"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("EVENT_STATUS", new FieldMapper("eventStatus"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MOVEMENT_TYPE", new FieldMapper("movementType"))
			.put("FORM_IDENTIFIER", new FieldMapper("formIdentifier"))			
			.put("FORM_INFO_JSON",              new FieldMapper("formInfoJson"))
			.put("FORM_INFO_JSON_BLOB",          new FieldMapper("formInfoJsonBlob"))			
			.put("nbt_name",          new FieldMapper("nbtName"))
			.put("DATA_EXIST_FLAG",          new FieldMapper("dataExistFlag"))
			.put("agy_loc_id_desc",          new FieldMapper("agyLocIdDesc"))
			.build();
	private final Map<String, FieldMapper> workFlowsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("0", new FieldMapper("0"))
			.put("MAX", new FieldMapper("max"))
			.put("WORK_FLOW_ID", new FieldMapper("workFlowId"))
			.put("NV", new FieldMapper("nv"))
			.put("COUNT", new FieldMapper("count"))
			.build();
	private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'X'", new FieldMapper(" 'x' "))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("CODE", new FieldMapper("code"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.build();
	private final Map<String, FieldMapper> vheaderBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID",          new FieldMapper("offenderId"))
			.put(" ALIAS_OFFENDER_ID  ", new FieldMapper("aliasOffenderId"))
			.put("OFFENDER_ID_DISPLAY",  new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", 			 new FieldMapper("lastName"))
			.put("FIRST_NAME",           new FieldMapper("firstName"))
			.put("MIDDLE_NAME", 		 new FieldMapper("middleName"))
			.put("SUFFIX", 				 new FieldMapper("suffix"))
			.put("BIRTH_DATE", 			 new FieldMapper("birthDate"))
			.put("OFFENDER_BOOK_ID", 	 new FieldMapper("offenderBookId"))
			.put("BOOKING_NO", 			 new FieldMapper("bookingNo"))
			.put("BOOKING_BEGIN_DATE",   new FieldMapper("bookingBeginDate"))
			.put("BOOKING_END_DATE",     new FieldMapper("bookingEndDate"))
			.put("AGY_LOC_ID",           new FieldMapper("agyLocId"))
			.put("AGENCY_IML_ID",        new FieldMapper("agencyImlId"))
			.put("LIVING_UNIT_ID",       new FieldMapper("livingUnitId"))
			.put("DISCLOSURE_FLAG",      new FieldMapper("disclosureFlag"))
			.put("ACTIVE_FLAG", 		 new FieldMapper("activeFlag"))
			.put("BOOKING_STATUS",		 new FieldMapper("bookingStatus"))
			.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("livingUnitDescription"))
			.put("IN_OUT_STATUS",        new FieldMapper("inOutStatus"))
			.put("STATUS_DISPLAY",       new FieldMapper("statusDisplay"))
			.put("ROOT_OFFENDER_ID",	 new FieldMapper("rootOffenderId"))
			.put("ASSIGNED_STAFF_ID",	 new FieldMapper("assignedStaffId"))
			.put("AGY_LOC_TYPE", 		 new FieldMapper("agyLocType"))
			.put("CREATE_AGY_LOC_ID",	 new FieldMapper("createAgyLocId"))
			.put("BOOKING_TYPE", 		 new FieldMapper("bookingType"))
			.put("BOOKING_CREATED_DATE", new FieldMapper("bookingCreatedDate"))
			.put("LOCATION_CODE", 		 new FieldMapper("locationCode"))
			.put("INTAKE_AGY_LOC_ID",	 new FieldMapper("intakeAgyLocId"))
			.put("COMMUNITY_ACTIVE_FLAG",new FieldMapper("communityActiveFlag"))
			.put("CREATE_INTAKE_AGY_LOC_ID", new FieldMapper("createIntakeAgyLocId"))
			.put("COMMUNITY_STATUS",     new FieldMapper("communityStatus"))
			.put("STATUS_REASON",        new FieldMapper("statusReason"))
			.put("HEADER_STATUS",        new FieldMapper("headerStatus"))
			.put("AGE", 				 new FieldMapper("age"))
			.put("GENDER", 				 new FieldMapper("gender"))
			.put("OFF_ALERTS",      	 new FieldMapper("offAlerts"))
			.put("STATUS_1", 			 new FieldMapper("status1"))
			.put("STATUS_2", 			 new FieldMapper("status2"))
			.put("STATUS_3", 			 new FieldMapper("status3"))
			.put("ETHNICITY",			 new FieldMapper("ethnicity"))
			.put("MOVEMENT_REASON", 	 new FieldMapper("movementReason"))
			.put("IMAGE_ID", 	         new FieldMapper("imageId"))
			.put("OFF_SUP_LEVEL",		 new FieldMapper("offSupLevel")).build();

	/**
	 * Creates new OidrelscRepositoryImpl class Object
	 */
	public OidrelscRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderReleaseDetails
	 *
	 * @return List<OffenderReleaseDetails>
	 *
	 * @throws SQLException
	 */
	public List<OffenderReleaseDetails> offRelDetExecuteQuerys(final OffenderReleaseDetails objSearchDao) {
		final String sql = getQuery("OIDRELSC_OFFRELDET_FIND_OFFENDER_RELEASE_DETAILS");
		final RowMapper<OffenderReleaseDetails> offRowMaper = Row2BeanRowMapper.makeMapping(sql,
				OffenderReleaseDetails.class, offndrMaping);
		return (ArrayList<OffenderReleaseDetails>) namedParameterJdbcTemplate.query(sql, createParams(), offRowMaper);
	}

	public List<OffenderReleaseDetails> offRelDetExecuteQuery(final OffenderReleaseDetails objSearchDao) {
		final String sql = getQuery("OIDRELSC_OFFRELDET_FIND_OFFENDER_RELEASE_DETAILS");
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		final MapSqlParameterSource param = new MapSqlParameterSource();
		if (objSearchDao != null) {
			pSql.append(" WHERE ");

			if (objSearchDao.getDateType() != null) {
				if ("APR".equals(objSearchDao.getDateType())) {
					pSql.append("TRUNC(ord.approved_release_date) BETWEEN  to_date('"
							+ new java.sql.Date(objSearchDao.getFromDate().getTime()) + "','yyyy/MM/dd') and to_date('"
							+ new java.sql.Date(objSearchDao.getToDate().getTime()) + "','yyyy/MM/dd') " + " AND ");
				} else if ("AUTO".equals(objSearchDao.getDateType())) {
					pSql.append("TRUNC(ord.auto_release_date) BETWEEN  to_date('"
							+ new java.sql.Date(objSearchDao.getFromDate().getTime()) + "','yyyy/MM/dd') and to_date('"
							+ new java.sql.Date(objSearchDao.getToDate().getTime()) + "','yyyy/MM/dd') " + " AND ");
				} else if ("DTOMID".equals(objSearchDao.getDateType())) {
					pSql.append("TRUNC(ord.dto_mid_term_date) BETWEEN  to_date('"
							+ new java.sql.Date(objSearchDao.getFromDate().getTime()) + "','yyyy/MM/dd') and to_date('"
							+ new java.sql.Date(objSearchDao.getToDate().getTime()) + "','yyyy/MM/dd') " + " AND ");
				} else if ("DTOAPR".equals(objSearchDao.getDateType())) {
					pSql.append("TRUNC(ord.dto_approved_date) BETWEEN  to_date('"
							+ new java.sql.Date(objSearchDao.getFromDate().getTime()) + "','yyyy/MM/dd') and to_date('"
							+ new java.sql.Date(objSearchDao.getToDate().getTime()) + "','yyyy/MM/dd') " + " AND ");
				} else if ("CONF".equals(objSearchDao.getDateType())) {
					pSql.append("TRUNC(ord.release_date) BETWEEN  to_date('"
							+ new java.sql.Date(objSearchDao.getFromDate().getTime()) + "','yyyy/MM/dd') and to_date('"
							+ new java.sql.Date(objSearchDao.getToDate().getTime()) + "','yyyy/MM/dd') " + " AND ");
				} else {

				}

			}
			if (objSearchDao.getDateType() != null && objSearchDao.getOffenderIdDisplay() != null) {
				pSql.append(" OFFENDER_BOOK_ID IN (SELECT OFFENDER_BOOK_ID FROM V_HEADER_BLOCK_FN(:USERID) v_header_block WHERE offender_id_display = :offenderIdDisplay) " + " AND ");
				param.addValue("offenderIdDisplay", objSearchDao.getOffenderIdDisplay());
				param.addValue("USERID", objSearchDao.getCreateUserId());
				pSql.append(" EXISTS ( SELECT 'X' FROM offender_bookings ob " 
						+ " WHERE ob.offender_book_id = ord.offender_book_id  "
						+ " AND ob.active_flag = 'Y' AND ob.agy_loc_id = :facility) ");
				param.addValue("facility", objSearchDao.getFacility());
			} else if (objSearchDao.getDateType() != null && objSearchDao.getOffenderIdDisplay() == null) {
				pSql.append(" EXISTS ( SELECT 'X' FROM offender_bookings ob " 
						+ " WHERE ob.offender_book_id = ord.offender_book_id  "
						+ " AND ob.active_flag = 'Y' AND ob.agy_loc_id = :facility) ");
				param.addValue("facility", objSearchDao.getFacility());
			} else if (objSearchDao.getDateType() == null && objSearchDao.getOffenderIdDisplay() != null) {
				pSql.append(" ( APPROVED_RELEASE_DATE between to_date('" + new java.sql.Date(objSearchDao.getFromDate().getTime()) + "', 'yyyy/MM/dd')  AND to_date('" + new java.sql.Date(objSearchDao.getToDate().getTime()) + "', 'yyyy/MM/dd')  "
						+ "  OR AUTO_RELEASE_DATE between to_date('" + new java.sql.Date(objSearchDao.getFromDate().getTime()) + "', 'yyyy/MM/dd')  AND to_date('" + new java.sql.Date(objSearchDao.getToDate().getTime()) + "', 'yyyy/MM/dd')     OR "
						+ "   DTO_MID_TERM_DATE between to_date('" + new java.sql.Date(objSearchDao.getFromDate().getTime()) + "', 'yyyy/MM/dd')  AND to_date('" + new java.sql.Date(objSearchDao.getToDate().getTime()) + "', 'yyyy/MM/dd')     OR "
						+ "   RELEASE_DATE between to_date('" + new java.sql.Date(objSearchDao.getFromDate().getTime()) + "', 'yyyy/MM/dd')  AND to_date('" + new java.sql.Date(objSearchDao.getToDate().getTime()) + "', 'yyyy/MM/dd') ) " + " AND ");
				pSql.append(" OFFENDER_BOOK_ID IN (SELECT OFFENDER_BOOK_ID FROM V_HEADER_BLOCK_FN(:USERID) v_header_block WHERE offender_id_display = :offenderIdDisplay) " + " AND ");
				param.addValue("offenderIdDisplay", objSearchDao.getOffenderIdDisplay());
				param.addValue("USERID", objSearchDao.getCreateUserId());
				pSql.append(" EXISTS ( SELECT 'X' FROM offender_bookings ob " 
						+ " WHERE ob.offender_book_id = ord.offender_book_id  "
						+ " AND ob.active_flag = 'Y' AND ob.agy_loc_id = :facility) ");
				param.addValue("facility", objSearchDao.getFacility());
			} else if (objSearchDao.getDateType() == null && objSearchDao.getOffenderIdDisplay() == null) {
				pSql.append(" (APPROVED_RELEASE_DATE between to_date('" + new java.sql.Date(objSearchDao.getFromDate().getTime()) + "', 'yyyy/MM/dd')  AND to_date('" + new java.sql.Date(objSearchDao.getToDate().getTime()) + "', 'yyyy/MM/dd') OR "
						+ " AUTO_RELEASE_DATE between to_date('" + new java.sql.Date(objSearchDao.getFromDate().getTime()) + "', 'yyyy/MM/dd')  AND to_date('" + new java.sql.Date(objSearchDao.getToDate().getTime()) + "', 'yyyy/MM/dd')     OR "
						+ " DTO_MID_TERM_DATE between to_date('" + new java.sql.Date(objSearchDao.getFromDate().getTime()) + "', 'yyyy/MM/dd')  AND to_date('" + new java.sql.Date(objSearchDao.getToDate().getTime()) + "', 'yyyy/MM/dd')     OR "
						+ " DTO_APPROVED_DATE between to_date('" + new java.sql.Date(objSearchDao.getFromDate().getTime()) + "', 'yyyy/MM/dd')  AND to_date('" + new java.sql.Date(objSearchDao.getToDate().getTime()) + "', 'yyyy/MM/dd')     OR "
						+ " RELEASE_DATE between to_date('" + new java.sql.Date(objSearchDao.getFromDate().getTime()) + "', 'yyyy/MM/dd')  AND to_date('" + new java.sql.Date(objSearchDao.getToDate().getTime()) + "', 'yyyy/MM/dd') ) " + " AND ");
				pSql.append(" EXISTS ( SELECT 'X' FROM offender_bookings ob " 
						+ " WHERE ob.offender_book_id = ord.offender_book_id  "
						+ " AND ob.active_flag = 'Y' AND ob.agy_loc_id = :facility) ");
				param.addValue("facility", objSearchDao.getFacility());
				
			}


		}
		preparedSql = pSql.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" order by offender_book_id ");
		final RowMapper<OffenderReleaseDetails> docsrowMaper = Row2BeanRowMapper.makeMapping(preparedSql,
				OffenderReleaseDetails.class, offndrMaping);
		List<OffenderReleaseDetails> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, param, docsrowMaper);
		} catch (Exception e) {

		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderReleaseDetails
	 *            List<OffenderReleaseDetails>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offRelDetInsertOffenderReleaseDetails(final List<OffenderReleaseDetails> list) {

		final String sql = getQuery("OIDRELSC_OFFRELDET_INSERT_OFFENDER_RELEASE_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderReleaseDetails object : list) {
			parameters.add(new BeanPropertySqlParameterSource(object));
			
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderReleaseDetails
	 *            List<OffenderReleaseDetails>
	 *
	 * @throws SQLException
	 */
	public Integer offRelDetUpdateOffenderReleaseDetails(final List<OffenderReleaseDetails> list) {

		final String sql = getQuery("OIDRELSC_OFFRELDET_UPDATE_OFFENDER_RELEASE_DETAILS");

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderReleaseDetails object : list) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgAgyLocationsRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIDRELSC_FIND_RGAGYLOCATIONS");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgAgyLocationsRecordGroup : oidrelsc" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgDateTypeRecordGroup() {
		final String sql = getQuery("OIDRELSC_FIND_RGDATETYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgDateTypeRecordGroup : oidrelsc" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<MovementReasons> rgMovementReasonsRecordGroup() {
		final String sql = getQuery("OIDRELSC_FIND_RGMOVEMENTREASONS");
		final RowMapper<MovementReasons> mRowMapper = Row2BeanRowMapper.makeMapping(sql, MovementReasons.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgMovementReasonsRecordGroup : oidrelsc" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * ctlWhenValidateRecord
	 *
	 * @param params
	 *
	 */
	public Long ctlWhenValidateRecord(final OffenderReleaseDetails object) {
		final String sql = getQuery("OIDRELSC_CTL_WHENVALIDATERECORD");
		Long returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderIdDisplay",
					object.getOffenderIdDisplay(), "caseLoadId", object.getCaseLoadId(),"USERID",object.getCreateUserId()), Long.class);
		} catch (Exception e) {
			logger.error("ctlWhenValidateRecord : oidrelsc" + e);
			return null;
		}
		return returnObj;
	}

	public List<VHeaderBlock> ctlWhenValidateRecord(final VHeaderBlock paramBean) {
		final String sql = getQuery("OIDRELSC_CTL_WHENVALIDATERECORD");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vhederMaping);
		return (ArrayList<VHeaderBlock>) namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * chkWorkFlows
	 *
	 * @param params
	 *
	 */
	public Integer chkWorkFlows(final WorkFlows paramBean) {
		final String sql = getQuery("OIDRELSC_CHK_WORK_FLOWS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OBJECT_CODE", paramBean.getObjectCode(),
				"P_OBJECT_ID", paramBean.getObjectId(), "P_OBJECT_SEQ", paramBean.getObjectSeq()), Integer.class);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * verificationButton
	 *
	 * @param params
	 *
	 */
	public List<WorkFlows> verificationButton(final WorkFlows paramBean) {
		final String sql = getQuery("OIDRELSC_VERIFICATION_BUTTON");
		final RowMapper<WorkFlows> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, WorkFlows.class,
				workFlowsMapping);
		return (ArrayList<WorkFlows>) namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * insWorkFlows
	 *
	 * @param params
	 *
	 */

	public BigDecimal insertWorkFlowEntInsert(final WorkFlows paramBean) {
		final String sql = getQuery("OIDRELSC_INS_WORK_FLOW");

		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OBJ_CODE", paramBean.getObjectCode(), "P_OBJ_ID", paramBean.getObjectId()),
				BigDecimal.class);

	}

	public Long getCountCursor(final WorkFlows paramBean) {
		final String sql = getQuery("OIDRELSC_INS_WORK_FLOWS_COUNTCURSOR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OBJ_CODE", paramBean.getObjectCode(),
				"P_OBJ_ID", paramBean.getObjectId(), "P_OBJ_SEQ", paramBean.getObjectSeq()), Long.class);

	}

	public Long getWorkFlowInsertSequence() {
		final String sql = getQuery("OIDRELSC_INS_WORK_FLOWS_SEQ");

		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);

	}

	public List<WorkFlows> insWorkFlows(final WorkFlows paramBean) {
		final String sql = getQuery("OIDRELSC_INS_WORK_FLOWS");
		final RowMapper<WorkFlows> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, WorkFlows.class,
				workFlowsMapping);
		return (ArrayList<WorkFlows>) namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * updWorkFlows
	 *
	 * @param params
	 *
	 */
	public List<WorkFlows> updWorkFlows(final WorkFlows paramBean) {
		final String sql = getQuery("OIDRELSC_UPD_WORK_FLOWS");
		final RowMapper<WorkFlows> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, WorkFlows.class,
				workFlowsMapping);
		return (ArrayList<WorkFlows>) namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * updWorkFlows
	 *
	 * @param params
	 *
	 */
	public Long updWorkFlows(final WorkFlowLogs paramBean) {
		final String sql = getQuery("OIDRELSC_UPD_WORK_FLOWS");
		final RowMapper<Long> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Long.class,
				wfLogsMaping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("V_WORK_FLOW_ID",paramBean.getWorkFlowId()), columnRowMapper);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkOffAgencyLocation
	 *
	 * @param params
	 *
	 */
	public Object checkOffAgencyLocation(final Offenders paramBean) {
		final String sql = getQuery("OIDRELSC_CHECK_OFF_AGENCY_LOCATION");
		final RowMapper<Offenders> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offendersMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * insertWorkFlowEnt
	 *
	 * @param params
	 *
	 */
	public WorkFlows insertWorkFlowEnt(final WorkFlows paramBean) {
		final String sql = getQuery("OIDRELSC_INSERT_WORK_FLOW_ENT");
		Long returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OBJ_CODE", paramBean.getObjectCode(), "P_OBJ_ID", paramBean.getObjectId()),
					Long.class);
			if (returnObj == null) {
				returnObj = (long) 0;
			}
		} catch (Exception e) {
			returnObj = (long) 0;
		}
		paramBean.setObjectSeq(BigDecimal.valueOf(returnObj));
		return paramBean;
	}

	public WorkFlows getMaxFlowId(final WorkFlows paramBean) {
		final String sql = getQuery("OIDRELSC_INSERT_WORK_FLOW_ENT_MAXFLOWID");
		Long returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OBJ_CODE", paramBean.getObjectCode(), "P_OBJ_ID", paramBean.getObjectId(),
							"P_OBJ_SEQ", paramBean.getObjectSeq()),
					Long.class);
			if (returnObj == null) {
				returnObj = (long) 0;
			}
		} catch (Exception e) {
			returnObj = (long) 0;
		}
		paramBean.setWorkFlowId(returnObj);

		return paramBean;
	}

	

	public Integer insertIntoWorkFlows(final WorkFlows workFlow) {
		final String sql = getQuery("OIDRESC_INSERT_INTO_WORK_FLOWS");
		final Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workFlowId", workFlow.getWorkFlowId());
		paramMap.put("objectCode", workFlow.getObjectCode());
		paramMap.put("objectId", workFlow.getObjectId());
		paramMap.put("objectSeq", workFlow.getObjectSeq());
		paramMap.put("createUserId", workFlow.getCreateUserId());
		Integer returnArray = null;
		try {
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		}catch(DataAccessException e) {
			
		}
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;

	}

	public Integer insertIntoWorkFlowLogs(final WorkFlowLogs workFlow) {
		final String sql = getQuery("OIDRESC_INSERT_INTO_WORK_FLOW_LOGS");
		final Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workFlowId", workFlow.getWorkFlowId());
		paramMap.put("createDate", workFlow.getCreateDate());
		paramMap.put("createUserId", workFlow.getCreateUserId());
		paramMap.put("workActionCode", workFlow.getWorkActionCode());
		paramMap.put("workFlowStatus", workFlow.getWorkFlowStatus());
		paramMap.put("workFlowSeq", workFlow.getWorkFlowSeq());
		
		Integer returnArray = null;
		try {
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		}catch(Exception e) {
			logger.error("insertIntoWorkFlowLogs", e);
		}
		if (returnArray != 0) {
			returnArray = 1;
			return returnArray;
		}
		return returnArray;

	}

	@Override
	public List<VHeaderBlock> getOffenderList(VHeaderBlock paramBean) {
		final String sql = getQuery("OIDRELSC_SELECTED_RECORD");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vheaderBlockMapping);
		List<VHeaderBlock> returnObj = null;
		try {
			
			returnObj = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", paramBean.getOffenderBookId(),"USERID",paramBean.getCreateUserId()),
					columnRowMapper);
		} catch(Exception e){
			
		}
		return returnObj;
	}
	
	@Override
	public String getOffIdDisplay(final Long offBookId,String userId) {
		final String sql = getQuery("OIDRELSC_GET_OFF_BOOK_ID");
		final String sqlOne = getQuery("OIDRELSC_GET_OFF_ID_DISPLAY");
		Long returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offBookId),
					Long.class);
		String offIdDisplay = namedParameterJdbcTemplate.queryForObject(sqlOne, createParams("ROOTOFFENDERID", returnObj,"USERID",userId),
				String.class);
		return offIdDisplay;
	}
	
	public BigDecimal getEventId() {
		final String sql = getQuery("OIDRELSC_GET_EVENT_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}

	@Override
	public List<OffenderReleaseDetailKeyDatesBean> offRelDetLegalExecuteQuery(OffenderReleaseDetails searchBean) {
		String sql = getQuery("OIDRELSC_GET_LEGAL_SUMMERY_DATA_FOR_DATES");
		 ArrayList<OffenderReleaseDetailKeyDatesBean> returnList =new ArrayList<OffenderReleaseDetailKeyDatesBean>();
		final RowMapper<OffenderReleaseDetailKeyDatesBean> uiBeanMapper = Row2BeanRowMapper.makeMapping(sql, OffenderReleaseDetailKeyDatesBean.class,
				offndrMaping);
		final MapSqlParameterSource param = new MapSqlParameterSource();
		try {
			if(searchBean!=null && searchBean.getFacilityList()!=null &&!searchBean.getFacilityList().isEmpty()) {
				param.addValue("facility", searchBean.getFacilityList());
			}
			returnList = (ArrayList<OffenderReleaseDetailKeyDatesBean>) namedParameterJdbcTemplate.query(sql,param, uiBeanMapper);
			if(returnList.isEmpty()) {
				List<OffenderReleaseDetailKeyDatesBean> returnListEx=new ArrayList<OffenderReleaseDetailKeyDatesBean>();
				return returnListEx;
			}
			for (OffenderReleaseDetailKeyDatesBean obj : returnList) {
				obj.setFormInfoJson(new String(obj.getFormInfoJsonBlob()));
			}	
		} catch (Exception e) {
			logger.error("offRelDetLegalExecuteQuery : oidrelsc" + e);
		}
		 
		return returnList;
	}
	
	@Override
	public List<OffenderReleaseDetailKeyDatesBean>  getOffenderKeyDatesBasedOnBookId(BigDecimal offenderBookId){
		String sql = getQuery("OIDRELSC_GET_OFFENDER_BOOK_ID_KEY_DATES");
		 ArrayList<OffenderReleaseDetailKeyDatesBean> returnList =new ArrayList<OffenderReleaseDetailKeyDatesBean>();
		final RowMapper<OffenderReleaseDetailKeyDatesBean> uiBeanMapper = Row2BeanRowMapper.makeMapping(sql, OffenderReleaseDetailKeyDatesBean.class,
				offndrMaping);
		try {
			returnList = (ArrayList<OffenderReleaseDetailKeyDatesBean>) namedParameterJdbcTemplate.query(sql,createParams("offenderBookId",offenderBookId), uiBeanMapper);
			if(returnList.isEmpty()) {
				List<OffenderReleaseDetailKeyDatesBean> returnListEx=new ArrayList<OffenderReleaseDetailKeyDatesBean>();
				return returnListEx;
			}
			for (OffenderReleaseDetailKeyDatesBean obj : returnList) {
				obj.setFormInfoJson(new String(obj.getFormInfoJsonBlob()));
			}	
		} catch (Exception e) {
			logger.error("offRelDetLegalExecuteQuery : oidrelsc" + e);
		}
		 
		return returnList;
	}

	@Override
	public List<ReferenceCodes> getKeyDatesDataLovData(String domainName) {
		final String sql = getQuery("OIDRELSC_FIND_RGKEY_DATE_FUNCTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("domainName", domainName), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("getKeyDatesDataLovData :oidrelsc", e);
			return Collections.emptyList();
		}
	}
	
	
	public Date getReleaseDate(Long offenderBookId) {
		final String sql = getQuery("OIDRELSC_GET_RELEASE_DATE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId), Date.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("getReleaseDate :oidrelsc", e);
			return null;
		}
	}
	
	public WorkFlowLogs getWorkFLowMaxID(Long objectId,Long objectSeq) {
		final String sql = getQuery("OIDRELSC_GET_MAX_WORK_FLOW_ID");
		final RowMapper<WorkFlowLogs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, WorkFlowLogs.class, wfLogsMaping);
		WorkFlowLogs returnList = new WorkFlowLogs();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("objectId", objectId,"objectSeq",objectSeq),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("getWorkFLowMaxID :oidrelsc", e);
			return null;
		}
		return returnList;
	
	}

	@Override
	public Integer updateCommentText(OffenderReleaseDetails offenderReleaseDetails) {
		final String sql = getQuery("OIDRELSC_COMMENT_UPDATE_OFFENDER_RELEASE_DETAILS");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("commentText", offenderReleaseDetails.getCommentText(), "offenderBookId",
							offenderReleaseDetails.getOffenderBookId(), "modifyUserId",
							offenderReleaseDetails.getModifyUserId()));
		} catch (Exception e) {
			logger.error("updateCommentText", e);
		}
		return retVal;
	}
	
}
